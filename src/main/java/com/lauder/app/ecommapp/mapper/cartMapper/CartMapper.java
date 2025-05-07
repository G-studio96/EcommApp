package com.lauder.app.ecommapp.mapper.cartMapper;


import com.lauder.app.ecommapp.dto.request.cart.CartRequest;
import com.lauder.app.ecommapp.dto.response.cart.CartItemResponse;
import com.lauder.app.ecommapp.dto.response.cart.CartResponse;
import com.lauder.app.ecommapp.model.CartItemModel;
import com.lauder.app.ecommapp.model.CartModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartModel toEntity(CartRequest request){
        CartModel model = new CartModel();

        model.setCartId(request.getCartId());
        model.setCustomerId(request.getCustomerId());

        if (model.getCartItemModels() == null) {
            model.setCartItemModels(new HashSet<>());
        }

        model.updateTotal();

        return model;
    }


    public CartResponse toResponse(CartModel model) {
        CartResponse response = new CartResponse();
        response.setCartId(model.getCartId());
        response.setCustomerId(model.getCustomerId());

        Set<CartItemResponse> itemResponses = model.getCartItemModels()
                .stream().map(this::mapCartItemToResponse)
                .collect(Collectors.toSet());

        response.setItems(itemResponses);
        response.setTotal(model.getTotal());

        return response;

    }


    private CartItemResponse mapCartItemToResponse(CartItemModel itemModel) {
        CartItemResponse itemResponse = new CartItemResponse();
        itemResponse.setProductId(itemModel.getProduct().getProductId());
        itemResponse.setProductName(itemModel.getProduct().getProductName());
        itemResponse.setQuantity(itemModel.getQuantity());
        itemResponse.setPrice(itemModel.getPrice());

        itemResponse.setTotal(itemModel.getPrice().multiply(BigDecimal.valueOf(itemModel.getQuantity())));

        return itemResponse;
    }

    public CartResponse modelToResponse(CartRequest request) {
        CartModel intermediateModal = toEntity(request);

        return toResponse(intermediateModal);
    }


}
