package com.lauder.app.ecommapp.controller;

import com.lauder.app.ecommapp.dto.request.shipping.ShippingRequest;
import com.lauder.app.ecommapp.dto.response.shipping.ShippingResponse;
import com.lauder.app.ecommapp.service.ShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/shipping")
public class ShippingController {

    final Logger logger = LoggerFactory.getLogger(ShippingController.class);
    final ShippingService shippingService;

    @Autowired
    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }


    @PostMapping("/by-shipping-id")
    public ResponseEntity<ShippingResponse> findShippingById(@RequestBody ShippingRequest request) {
        logger.info("logging the customer shipping Id: {}", request.getShippingId());

        ShippingResponse response = shippingService.findCustomerOrderByShippingId(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/by-status")
    public ResponseEntity<List<ShippingResponse>> getShippingByStatus(@RequestBody ShippingRequest request) {


        List<ShippingResponse> responsesList = shippingService.findShippingByStatus(request);

        return ResponseEntity.ok(responsesList);
    }

    @PostMapping("/update-status")
    public ResponseEntity<List<ShippingResponse>> updateStatus(@RequestBody ShippingRequest request) {
        List<ShippingResponse> updated = shippingService.updatingShippingStatuses(request);

        return ResponseEntity.ok(updated);
    }

    @PostMapping("/recently-updated")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<ShippingResponse>> getRecentlyUpdate(@RequestBody ShippingRequest request) {
        Set<ShippingResponse> recent = shippingService.recentlyUpdated(request);
        return ResponseEntity.ok(recent);
    }

    @PostMapping("/completed")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<ShippingResponse>> getCompletedOrders(@RequestBody ShippingRequest request) {
        Set<ShippingResponse> completed = shippingService.completedOrder(request);
        return ResponseEntity.ok(completed);
    }


}
