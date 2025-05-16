package com.lauder.app.ecommapp.repo;


import com.lauder.app.ecommapp.model.Promoter;
import com.lauder.app.ecommapp.model.RebateEarned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface IRebateRepo extends JpaRepository<RebateEarned, Long> {
    Optional<RebateEarned> findByPromoterAndIdAndMonth(Promoter promoter, Long id, YearMonth month);


    @Query("SELECT r FROM RebateEarned r WHERE r.promoter = :promoter AND r.month = :month AND r.earnings = :earnings")
    Optional<RebateEarned> countRebateEarnedByPromoterAndMonth(@Param("promoter") Promoter promoter, @Param("month") YearMonth month, @Param("earnings") BigDecimal earnings);


    @Query("SELECT r FROM RebateEarned r WHERE r.startMonth >= :fromDate")
    List<RebateEarned> findAllByEarningsFromCurrentPeriod(@Param("fromDate") LocalDateTime fromDate);

    @Query("SELECT r FROM RebateEarned r WHERE r.promoter.promotersId = :promtersId AND r.startMonth >= :fromDate")
    List<RebateEarned> findPromoterEarningsFromCurrentPeriod(@Param("promoterId") Long promoterId, @Param("fromDate") LocalDateTime fromDate);
}
