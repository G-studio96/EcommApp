package com.lauder.app.ecommapp.mapper.shippingMapper;

import com.lauder.app.ecommapp.dto.request.shipping.ShippingRequest;
import com.lauder.app.ecommapp.dto.response.shipping.ShippingResponse;
import com.lauder.app.ecommapp.model.Shipping;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ShippingMapper {


    public Shipping toEntity(ShippingRequest request) {
        if (request == null) {
            return null;
        }

        Shipping newEntity = new Shipping();

        if (request.getShippingId() != null) {
            newEntity.setShippingId(request.getShippingId());
        }

        if (request.getCustomers() != null && request.getCustomers().getId() != null) {
            newEntity.setCustomers(request.getCustomers());
        }

        if (request.getOrder() != null) {
            newEntity.setOrder(request.getOrder());
        }

        if (request.getProducts() != null) {
            newEntity.setProducts(request.getProducts());
        }

        if (request.getShippingStatuses() != null) {
            newEntity.setShippingStatuses(request.getShippingStatuses());
        }

        newEntity.setProductName(request.getProductName());
        newEntity.setQuantity(request.getQuantity());
        newEntity.setPhoneNumber(request.getPhoneNumber());
        newEntity.setAddressLine1(request.getAddressLine1());
        newEntity.setAddressLine2(request.getAddressLine2());
        newEntity.setAddressLine3(request.getAddressLine3());
        newEntity.setCity(request.getCity());
        newEntity.setCounty(request.getCounty());
        newEntity.setCountry(request.getCountry());
        newEntity.setEircode(request.getEircode());

        if (request.getTrackerNumber() != null) {
            newEntity.setTrackingNumber(request.getTrackerNumber());
        }

        newEntity.setCreated(LocalDateTime.now());
        newEntity.setUpdated(LocalDateTime.now());

        return newEntity;
    }


    public ShippingResponse toResponse(Shipping model) {
        if (model == null) {
            return null;
        }

        ShippingResponse response = new ShippingResponse();

        response.setShippingId(model.getShippingId());
        response.setCustomers(model.getCustomers());
        response.setPhoneNumber(model.getPhoneNumber());
        response.setAddressLine1(model.getAddressLine1());
        response.setAddressLine2(model.getAddressLine2());
        response.setAddressLine3(model.getAddressLine3());
        response.setCity(model.getCity());
        response.setCounty(model.getCounty());
        response.setCountry(model.getCountry());
        response.setEircode(model.getEircode());

        response.setOrder(model.getOrder());
        response.setProducts(model.getProducts());
        response.setQuantity(model.getQuantity());
        response.setShippingStatuses(model.getShippingStatuses());
        response.setCreatedAt(model.getCreated());
        response.setUpdatedAt(LocalDateTime.now());
        response.setTrackingNumber(model.getTrackingNumber());
        response.setProductName(model.getProductName());

        return response;
    }


}
