package com.lauder.app.ecommapp.controller;



import com.lauder.app.ecommapp.dto.request.order.OrderRequest;
import com.lauder.app.ecommapp.dto.response.order.OrderResponse;
import com.lauder.app.ecommapp.model.PaymentModel;
import com.lauder.app.ecommapp.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class
OrdersController {

    private final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    private final OrderService orderService;
    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        OrderResponse createdOrder = orderService.createOrder(request);

        return  new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderId(@PathVariable Long id) {
        OrderResponse order = orderService.getOrderById(id);

        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        OrderResponse updatedOrder = orderService.updateOrder(id, orderRequest);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/admin/orders")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderResponse>> getAllOrder() {
        List<OrderResponse> orders = orderService.getAllOrder();

        return ResponseEntity.ok(orders);
    }


    @GetMapping("/paginated")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<OrderResponse>> getOrdersPaginated(Pageable pageable) {
        Page<OrderResponse> orders = orderService.getOrdersPaginated(pageable);
        return ResponseEntity.ok(orders);

    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<OrderResponse> orders = orderService.getOrdersByCustomersId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/date-range")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderResponse>> getOrdersByDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<OrderResponse> orders = orderService.getOrdersByDateRange(startDate, endDate);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/price-range")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<List<OrderResponse>> getOrdersByPriceRange(@RequestParam BigDecimal minAmount, @RequestParam BigDecimal maxAmount) {
        List<OrderResponse> orders = orderService.getOrdersByPriceRange(minAmount, maxAmount);

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/transaction/{transactionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderResponse>> getOrdersByTransactionId(@PathVariable PaymentModel transactionId) {
        List<OrderResponse> orders = orderService.getOrdersByTransactionId(transactionId);
        return ResponseEntity.ok(orders);
    }
}
