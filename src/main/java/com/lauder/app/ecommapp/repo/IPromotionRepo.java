package com.lauder.app.ecommapp.repo;

import com.lauder.app.ecommapp.model.PromotionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IPromotionRepo extends JpaRepository<PromotionModel, Long> {

    Optional<PromotionModel> findByPromotersId(Long promotersId);


    Optional<PromotionModel> findBySocialHandle(String socialHandle);

    Optional<PromotionModel> findAllBySocialHandleAndPromoCode(String socialHandle, Set<String> promoCode);

    List<PromotionModel> findByFollowersBetween(int min, int max);

    List<PromotionModel> findByDiscountBetween(float min, float max);

    long countBySocialHandle(String socialHandle);

    long countByPromoCode(Set<String> promoCode);

    Optional<PromotionModel> findByPromoCode(Set<String> promoCode);



}
