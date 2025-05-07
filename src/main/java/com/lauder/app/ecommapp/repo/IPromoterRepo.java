package com.lauder.app.ecommapp.repo;

import com.lauder.app.ecommapp.model.Promoter;
import com.lauder.app.ecommapp.model.Role;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IPromoterRepo extends JpaRepository<Promoter, Long> {

    Optional<Promoter> findPromotersByEmail(String email);

    Optional<Promoter> findPromotersByRoles(Set<Role.Roles> roles);

    Optional<Promoter> findPromoterByName(String name);

    Optional<Promoter> findPromoterBySocialHandle(Set<String> socialHandle);

    Optional<Promoter> findBySocialHandle(Set<String> socialHandle);

    Optional<Promoter> findPromotersByEmailAndSocialHandle(@Email String email, Set<String> socialHandle);

    @Query("SELECT COUNT(p) FROM PromotionModel p WHERE p.promoter.promotersId = :promotersId")
    long countPromotionsByPromotersId(@Param("id") Long promoterId);

    @Query("SELECT COALESCE(SUM(p.earnedAmount), 0) FROM PromotionModel p WHERE p.promoter.promotersId = :id")
    BigDecimal sumEarningsByPromoterId(Long promoterId);


}
