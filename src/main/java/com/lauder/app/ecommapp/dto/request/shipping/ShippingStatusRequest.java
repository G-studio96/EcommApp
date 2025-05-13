package com.lauder.app.ecommapp.dto.request.shipping;

import com.lauder.app.ecommapp.model.Shipping;
import com.lauder.app.ecommapp.model.ShippingStatus;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

public class ShippingStatusRequest {

    @NotBlank(message = "required Shipping Id ")
    private Long shippingId;

    @NotBlank(message = "required status")
    private Set<ShippingStatus.Status> status;

    @NotBlank(message = "required Shipping info ")
    private Shipping shipping;

    @NotBlank(message = "required created at time ")
    @DateTimeFormat
    private LocalDateTime createdAt;

    @NotBlank(message = "required updated at time")
    @DateTimeFormat
    private LocalDateTime updatedAt;

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public Set<ShippingStatus.Status> getStatus() {
        return status;
    }

    public void setStatus(Set<ShippingStatus.Status> status) {
        this.status = status;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
