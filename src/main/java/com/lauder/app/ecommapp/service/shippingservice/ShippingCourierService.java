package com.lauder.app.ecommapp.service.shippingservice;

import com.lauder.app.ecommapp.repo.ShippingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ShippingCourierService {

    private final Map<String, ShippingStrategy> courierStrategy;

    private final List<String> couriers = Arrays.asList(
            "An Post", "DPD", "UPS"
    );

    @Autowired
    public ShippingCourierService(Map<String, ShippingStrategy> courierStrategy) {
        this.courierStrategy = courierStrategy;
    }


}
