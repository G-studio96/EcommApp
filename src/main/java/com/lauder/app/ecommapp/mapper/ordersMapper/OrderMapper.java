package com.lauder.app.ecommapp.mapper.ordersMapper;


import com.lauder.app.ecommapp.dto.request.order.OrderRequest;
import com.lauder.app.ecommapp.dto.response.order.OrderItemResponse;
import com.lauder.app.ecommapp.dto.response.order.OrderResponse;
import com.lauder.app.ecommapp.model.UsersModel;
import com.lauder.app.ecommapp.model.OrderItem;
import com.lauder.app.ecommapp.model.OrderModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderModel toEntity(OrderRequest request) {

        if (request == null) {
            return null;
        }
        OrderModel orderModel = new OrderModel();

        orderModel.setOrderId(request.getOrderId());

        if(request.getCustomerId() != null) {

            UsersModel customer = new UsersModel();
            customer.setId(customer.getId());
            orderModel.setCustomer(customer);
        }

        orderModel.setOrderDate(request.getOrderDate());
        orderModel.setTotalAmount(request.getTotalAmount());

        if(orderModel.getOrderItems() == null) {
            orderModel.setOrderItems(new HashSet<>());
        }

       return orderModel;

    }


    public OrderResponse toResponse(OrderModel model) {

        if (model == null) {
            return null;
        }
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setOrderId(model.getOrderId());
        orderResponse.setCustomer(model.getCustomer());
        orderResponse.setOrderDate(model.getOrderDate());

        if (model.getOrderItems() != null) {
            Set<OrderItemResponse> orderItems = model.getOrderItems()
                    .stream().map(this::mapOrderItemToResponse)
                    .collect(Collectors.toSet());

            orderResponse.setOrderItems(orderItems);
        }

        if (model.getOrderStatus() != null) {

            orderResponse.setStatue(model.getOrderStatus());
        }



        orderResponse.setTotalAmount(model.getTotalAmount());

        return  orderResponse;


    }



    private OrderItemResponse mapOrderItemToResponse(OrderItem orderItem) {

        if (orderItem == null) {
            return null;
        }
        OrderItemResponse orderItemResponse = new OrderItemResponse();

        orderItemResponse.setOrderItemId(orderItem.getOrderItemId());
        orderItemResponse.setOrderId(orderItem.getOrderId());
        orderItemResponse.setProductId(orderItem.getProductId());
        orderItemResponse.setQuantity(orderItem.getQuantity());

        orderItemResponse.setPrice(orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));

        return orderItemResponse;

    }


}
