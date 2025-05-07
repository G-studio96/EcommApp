package com.lauder.app.ecommapp.repo;



import com.lauder.app.ecommapp.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IOrderStatusRepo  extends JpaRepository<OrderStatus, Long> {

    @Override
    Optional<OrderStatus> findById(Long Long);

    List<OrderStatus> findByStatus(Set<OrderStatus.OrdStatus> status);
}
