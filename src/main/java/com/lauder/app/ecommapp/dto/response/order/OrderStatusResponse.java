package com.lauder.app.ecommapp.dto.response.order;


import com.lauder.app.ecommapp.model.OrderStatus;

import java.util.Set;

public class OrderStatusResponse {

    private Long statusId;

    private Set<OrderStatus.OrdStatus> status;

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Set<OrderStatus.OrdStatus> getStatus() {
        return status;
    }

    public void setStatus(Set<OrderStatus.OrdStatus> status) {
        this.status = status;
    }
}
