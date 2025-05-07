package com.lauder.app.ecommapp.service;


import com.lauder.app.ecommapp.dto.request.cart.CartRequest;
import com.lauder.app.ecommapp.dto.response.cart.CartResponse;
import com.lauder.app.ecommapp.mapper.cartMapper.CartMapper;
import com.lauder.app.ecommapp.model.CartModel;
import com.lauder.app.ecommapp.model.ProductModel;
import com.lauder.app.ecommapp.repo.ICartRepo;
import com.lauder.app.ecommapp.repo.IProductRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class CartService {


    private final Logger  logger = LoggerFactory.getLogger(CartService.class);

    private final ICartRepo cartRepo;
    private final CartMapper cartMapper;

    private final IProductRepo productRepo;

    @Autowired
    public CartService(ICartRepo cartRepo, CartMapper cartMapper, IProductRepo iProductRepo) {
        this.cartRepo = cartRepo;
        this.cartMapper = cartMapper;
        this.productRepo = iProductRepo;

    }

    @Transactional
    public CartResponse createCart(CartRequest request) {

        logger.info("Creating new cart  for users: {}",
                request.getCustomerId() );

        CartModel cartModel = cartMapper.toEntity(request);
        CartModel savedCart = cartRepo.save(cartModel);
        return  cartMapper.toResponse(savedCart);
    }

    @Transactional
    @Cacheable(value = "carts", key = "#id" )
    public CartResponse getCart(Long id) {
        logger.info("Fetching cart with id: {}", id);

        CartModel cartModel = cartRepo.findByCartId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found: " + id));

        return cartMapper.toResponse(cartModel);
    }


    public CartResponse updateCart(Long id, CartRequest cartRequest) {
        logger.info("Updating cart with id: {}", id);
        CartModel existingCart = cartRepo.findByCartId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " +
                        id));

        CartModel updatedCart = cartMapper.toEntity(cartRequest);
        updatedCart.setCartId(existingCart.getCartId());
        CartModel savedCart = cartRepo.save(updatedCart);

        return cartMapper.toResponse(savedCart);
    }

    public CartResponse addProductToCart(Long cartId, Long productId, int quantity) {
        logger.info("Adding product {} to cart {} with quantity {}", productId, cartId, quantity);

        CartModel cartModel = cartRepo.findByCartId(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cartId));

        ProductModel productModel = productRepo.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));



        cartModel.addProduct(productModel, quantity);

        CartModel updatedCart =  cartRepo.save(cartModel);
        return cartMapper.toResponse(updatedCart);


    }

    public  CartResponse removeProductFromCart(Long cartId, Long productId) {
        logger.info("Removing product {} from cart {}", productId, cartId);
        CartModel cartModel = cartRepo.findByCartId(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cartId));

        cartModel.removeProduct(productId);

        CartModel updatedCart = cartRepo.save(cartModel);
        return cartMapper.toResponse(updatedCart);
    }


    public void deleteCart(Long id) {
        logger.info("Deleting cart with id: {}", id);
        cartRepo.deleteById(id);
    }


    public List<CartResponse> getAllCartResponses(int page, int size) {
        logger.info("response with pagination: page={}, page={}", page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<CartModel> cartPage = cartRepo.findAll(pageable);

        return cartPage.getContent().stream()
                .map(cartMapper::toResponse)
                .collect(Collectors.toList());

    }
}