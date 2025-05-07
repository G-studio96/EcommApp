package com.lauder.app.ecommapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "order_statuses")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATUS_ID")
    private Long statusId;


    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Set<OrdStatus> status;

    public enum OrdStatus {
        PENDING,
        
        SHIPPING,
        
        IN_PROGESS,
        
        DELAYED,
        DELIVERED,
        COMPLETED
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Set<OrdStatus> getStatus() {
        return status;

    }

    public void setStatus(Set<OrdStatus> status) {
        this.status = status;
    }
}
