package com.lauder.app.ecommapp.repo;


import com.lauder.app.ecommapp.model.Promoter;
import com.lauder.app.ecommapp.model.RebateEarned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface IRebateRepo extends JpaRepository<RebateEarned, Long> {
    Optional<RebateEarned> findByPromoterAndIdAndMonth(Promoter promoter, Long id, String month);


    @Query("SELECT r FROM RebateEarned r WHERE r.promoter = :promoter AND r.month = :month AND r.earnings = :earnings")
    Optional<RebateEarned> countRebateEarnedByPromoterAndMonth(@Param("promoter") Promoter promoter, @Param("month") String month, @Param("earnings") BigDecimal earnings);

}
