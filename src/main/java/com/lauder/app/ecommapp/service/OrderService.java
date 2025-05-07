package com.lauder.app.ecommapp.service;


import com.lauder.app.ecommapp.dto.request.order.OrderRequest;
import com.lauder.app.ecommapp.dto.response.order.OrderResponse;
import com.lauder.app.ecommapp.mapper.ordersMapper.OrderMapper;
import com.lauder.app.ecommapp.model.UsersModel;
import com.lauder.app.ecommapp.model.OrderModel;
import com.lauder.app.ecommapp.model.OrderStatus;
import com.lauder.app.ecommapp.model.PaymentModel;
import com.lauder.app.ecommapp.repo.IUsersRepo;
import com.lauder.app.ecommapp.repo.IOrderRepo;
import com.lauder.app.ecommapp.repo.IOrderStatusRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final IOrderRepo orderRepo;
    private final IUsersRepo customerRepo;

    private final IOrderStatusRepo orderStatusRepo;

    private  final OrderMapper orderMapper;

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);


    public OrderService(IOrderRepo orderRepo, IUsersRepo customerRepo, IOrderStatusRepo orderStatusRepo, OrderMapper orderMapper) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.orderStatusRepo = orderStatusRepo;
        this.orderMapper = orderMapper;

    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        logger.info("Creating new order for users Id: {}", orderRequest.getCustomerId());

        UsersModel usersModel = customerRepo.findCustomerModelById(orderRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with Id: " + orderRequest.getCustomerId()));


        OrderModel orderModel = orderMapper.toEntity(orderRequest);

        if (orderModel.getOrderStatus() == null) {

            OrderStatus.OrdStatus defaultSt = OrderStatus.OrdStatus.PENDING;

            List<OrderStatus> defaultStatus = orderStatusRepo.findByStatus(Collections.singleton(defaultSt));

            orderModel.setOrderStatus(new HashSet<>(defaultStatus));
        }

        if(orderModel.getOrderDate() == null) {
            orderModel.setOrderDate(LocalDateTime.now());

        }

        OrderModel savedOrder = orderRepo.save(orderModel);
        logger.info("Order created successfully with ID: {}",
                savedOrder.getOrderId());

        return orderMapper.toResponse(savedOrder);
    }

    public  OrderResponse getOrderById(Long orderId) {
        logger.info("Fetching order with ID: {}", orderId);

        OrderModel order = orderRepo.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        return orderMapper.toResponse(order);

    }

    public OrderResponse updateOrder(Long orderId, OrderRequest orderRequest) {
        logger.info("Updating order with Id: {}, ", orderId);


        OrderModel existingOrder = orderRepo.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with Id: " + orderId));

        logger.info("existing Order with Id {}, ", existingOrder.getOrderId());

        OrderModel updatedOrder = orderMapper.toEntity(orderRequest);
        updatedOrder.setOrderId(orderId);

        OrderModel savedOrder = orderRepo.save(updatedOrder);

        logger.info("Order updated successfully with ID: {}", savedOrder.getOrderId());

        return orderMapper.toResponse(savedOrder);

    }

    public void deleteOrder(Long orderId) {
        logger.info("Deleting order with ID: {}", orderId);

        if (!orderRepo.existsById(orderId)) {
            throw new ResourceNotFoundException("Order not found with ID: " + orderId);
        }

        orderRepo.deleteById(orderId);
        logger.info("Order deleted successfully with ID: {}", orderId);

    }

    public List<OrderResponse> getAllOrder() {
        logger.info("Fetching all orders");
        return orderRepo.findAll().stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }


    public Page<OrderResponse> getOrdersPaginated(Pageable pageable) {
        logger.info("Fetching orders with pagination: {}", pageable);
        return orderRepo.findAll(pageable)
                .map(orderMapper::toResponse);

    }


    public List<OrderResponse> getOrdersByCustomersId(Long customerId) {
        logger.info("Fetching orders for users ID: {}", customerId);

        if(!customerRepo.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer not found with Id: " + customerId);

        }

        return orderRepo.findByCustomerId(customerId)
                .stream().map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }


    public List<OrderResponse> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        logger.info("Fetching orders between {} and {}", startDate, endDate);

        return orderRepo.findByOrderDateBetween(startDate, endDate)
                .stream().map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }


    public List<OrderResponse> getOrdersByPriceRange(BigDecimal minAmount, BigDecimal maxAmount) {
        logger.info("Fetching orders with total amount between {} and {}", minAmount, maxAmount);

        return orderRepo.findByTotalAmountBetween(minAmount, maxAmount)
                .stream().map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> getOrdersByTransactionId(PaymentModel transactionId) {
        logger.info("Fetching orders with transaction ID: {}", transactionId);

        return orderRepo.findByPaymentId(transactionId)
                .stream().map(orderMapper::toResponse)
                .collect(Collectors.toList());

    }

    /**

    public Optional<OrderResponse> getOrdersByStatus(OrderStatus.OrdStatus status) {
        logger.info("Fetching orders with status:  {}", status);
        return orderStatusRepo.findByStatus(status)
                .stream().map(orderMapper::)
                .collect(Collectors.toList());
    }
     */


}
