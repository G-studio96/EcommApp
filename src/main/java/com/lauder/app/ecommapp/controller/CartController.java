package com.lauder.app.ecommapp.controller;


import com.lauder.app.ecommapp.dto.request.cart.CartRequest;
import com.lauder.app.ecommapp.dto.response.cart.CartResponse;
import com.lauder.app.ecommapp.service.CartService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin
public class CartController {

    private final Logger logger = LoggerFactory.getLogger(CartController.class);
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartResponse>> getCarts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("getting all cart info with pagination: page={}, size={}", page, size);

        List<CartResponse> responses = cartService.getAllCartResponses( page, size);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable Long id) {
        try {
             CartResponse resId = cartService.getCart(id);
             logger.info("getting cart Id");
             return  ResponseEntity.ok(resId);

        } catch (ResourceNotFoundException e) {
            logger.info("Cart info not found by id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CartResponse> createCart(@Valid @RequestBody CartRequest cartModel) {
        logger.info("Creating new cart");
        CartResponse createdCart = cartService.createCart(cartModel);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCart.getCartId())
                .toUri();
        return ResponseEntity.created(location).body(createdCart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartResponse> updateCart(@Valid @RequestBody CartRequest request, @PathVariable Long id) {
        logger.info("Updating cart with id: {} ", id);
        try {
            CartResponse updatedCart = cartService.updateCart(id, request);
            return ResponseEntity.ok(updatedCart);
        } catch (ResourceNotFoundException e) {
            logger.warn("Cart not found with: {}", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.info("Error updating cart with id: " + id, e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        logger.info("Deleting cart with id: {}", id );
        try {
            cartService.deleteCart(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.info("Error deleting cart with id: {} " + id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartResponse> addProductToCart(@PathVariable Long cartId, @RequestParam Long productId, @RequestParam(defaultValue = "1") int quantity) {
        logger.info("Adding product {} to cart {} with quantity {}", productId, cartId, quantity);

        try {
            logger.info("add to new item to the cart successfully... ");
            CartResponse updatedCart = cartService.addProductToCart(cartId, productId, quantity);
            return ResponseEntity.ok(updatedCart);
        } catch (ResourceNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {

            logger.info("Error adding product to cart", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<CartResponse> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId ) {
        logger.info("Removing product {} from cart {} ", cartId, productId);

        try {
            CartResponse updatedCart = cartService.removeProductFromCart(cartId, productId);
            return ResponseEntity.ok(updatedCart);
        } catch (ResourceNotFoundException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.info("Error removing product from cart", e);
          return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
