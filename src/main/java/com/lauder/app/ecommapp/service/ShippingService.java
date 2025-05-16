package com.lauder.app.ecommapp.service;

import com.lauder.app.ecommapp.dto.request.shipping.ShippingRequest;
import com.lauder.app.ecommapp.dto.response.shipping.ShippingResponse;
import com.lauder.app.ecommapp.mapper.shippingMapper.ShippingMapper;
import com.lauder.app.ecommapp.model.Shipping;
import com.lauder.app.ecommapp.repo.IShippingRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShippingService {

    final Logger logger = LoggerFactory.getLogger(ShippingService.class);

    final IShippingRepo shippingRepo;

    final ShippingMapper shippingMapper;

    final Set<ShippingResponse> setRecentUpdated = new HashSet<>();

    final Set<ShippingResponse> completedOrder = new HashSet<>();

    @Autowired
    public ShippingService(IShippingRepo shippingRepo, ShippingMapper shippingMapper) {
        this.shippingRepo = shippingRepo;
        this.shippingMapper = shippingMapper;

    }

    public ShippingResponse findCustomerOrderByShippingId(ShippingRequest request) {
        logger.info("Customer Shipping Id inputted... {}", request.getShippingId());

        List<Shipping> findCustomerShipment = shippingRepo.findByShippingId(request.getShippingId());

        if (findCustomerShipment.isEmpty()) {
            logger.info("Id {} is not in uses check the Id number again ", request.getShippingId());

        }

        Shipping shipping = findCustomerShipment.getFirst();

        ShippingResponse response = shippingMapper.toResponse(shipping);

        return response;

    }

    public List<ShippingResponse> findShippingByStatus(ShippingRequest request) {
        logger.info("Finding shipping records with given status...");

        if (request.getShippingStatuses() == null || request.getShippingStatuses().isEmpty()) {
            logger.info("no shipping records with given statuses");
            return Collections.emptyList();
        }

        List<Shipping> shippings = shippingRepo.findByShippingStatuses(request.getShippingStatuses());

        return shippings.stream().map(shippingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = "updatingStatus")
    public List<ShippingResponse> updatingShippingStatuses(ShippingRequest request) {
        logger.info("Checking the the status of the order: ID {}", request.getTrackerNumber());


        List<Shipping> shippings = shippingRepo.findByShippingId(request.getShippingId());

        for (Shipping shipping : shippings) {
            shipping.setShippingStatuses(request.getShippingStatuses());
            shipping.setTrackingNumber(request.getTrackerNumber());
            shipping.setUpdatedAt(LocalDateTime.now());
        }

        shippingRepo.saveAll(shippings);

        return shippings.stream()
                .map(shippingMapper::toResponse)
                .collect(Collectors.toList());

    }

    @Cacheable(cacheNames = "recentlyUpdated")
    public Set<ShippingResponse> recentlyUpdated(ShippingRequest request) {
        logger.info("recently updated shipping status: {}", request.getTrackerNumber());

        Shipping shipping = shippingRepo.findShippingWithMostRecentlyUpdatedStatus();

        if (shipping != null) {
            shipping.setShippingStatuses(request.getShippingStatuses());
            shipping.setTrackingNumber(request.getTrackerNumber());
            shipping.setUpdated(LocalDateTime.now());
        }

        Shipping updated = shippingRepo.save(shipping);

        ShippingResponse response = shippingMapper.toResponse(updated);

        setRecentUpdated.removeIf(r -> r.getShippingId().equals(response.getShippingId()));

        setRecentUpdated.add(response);

        boolean isDelivered = request.getShippingStatuses()
                .stream().anyMatch(status -> status.getStatus().equals("DELIVERED"));

        if (isDelivered) {

            completedOrder.add(response);
            setRecentUpdated.remove(response);
        }

        return setRecentUpdated;
    }

    @Cacheable(cacheNames = "completedOrder")
    public Set<ShippingResponse> completedOrder(ShippingRequest request) {
        logger.info("completed Order {}", request.getTrackerNumber());

        Shipping shipping = shippingRepo.findAllCompletedOrders();

        if (shipping != null) {
            shipping.setTrackingNumber(request.getTrackerNumber());
            shipping.setShippingStatuses(request.getShippingStatuses());
            shipping.setUpdatedAt(LocalDateTime.now());
        }

        Shipping updated = shippingRepo.save(shipping);

        ShippingResponse response = shippingMapper.toResponse(updated);

        completedOrder.add(response);

        return completedOrder;

    }
}
