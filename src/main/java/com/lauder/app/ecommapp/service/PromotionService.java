package com.lauder.app.ecommapp.service;

import com.lauder.app.ecommapp.dto.response.promotion.PromotionResponse;
import com.lauder.app.ecommapp.mapper.promotionMapper.PromotionMapper;
import com.lauder.app.ecommapp.repo.IPromotionRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PromotionService {

    private final IPromotionRepo iPromotionRepo;
    private final PromotionMapper promotionMapper;
    private final Logger logger = LoggerFactory.getLogger(PromotionService.class);

    @Autowired
    public PromotionService(IPromotionRepo iPromotionRepo, PromotionMapper promotionMapper) {
        this.iPromotionRepo = iPromotionRepo;
        this.promotionMapper = promotionMapper;
    }

    @Cacheable("PromoCodes")
    public List<PromotionResponse> getPromoCodes() {
        logger.info("Fetching all Promo codes...");
        return iPromotionRepo.findAll()
                .stream()
                .map(promotionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Cacheable("Influencers")
    public PromotionResponse getSocialHandle(String socialHandle) {
        logger.info("Fetching Influencer by social handle: {}", socialHandle);
        return iPromotionRepo.findBySocialHandle(socialHandle)
                .map(promotionMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("No influencer found with handle: " + socialHandle));
    }

    @Cacheable("AudiencePerInfluencers")
    public List<PromotionResponse> findFollowersBetween(int min, int max) {
        logger.info("Fetching influencers with followers between {} and {}", min, max);
        return iPromotionRepo.findByFollowersBetween(min, max)
                .stream()
                .map(promotionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Cacheable("DiscountAppliedToCodes")
    public List<PromotionResponse> findDiscountBetween(float min, float max) {
        logger.info("Fetching influencers with discount between {}% and {}%", min, max);
        return iPromotionRepo.findByDiscountBetween(min, max)
                .stream()
                .map(promotionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Cacheable("CountPromoCodesByInfluencers")
    public long countPromoCodesByHandle(String socialHandle) {
        logger.info("Counting promo codes for social handle: {}", socialHandle);
        return iPromotionRepo.countBySocialHandle(socialHandle);
    }

    @Cacheable("CountByPromoCode")
    public long countByPromoCode(Set<String> promoCode) {
        logger.info("Counting usage of promo code: {}", promoCode);
        return iPromotionRepo.countByPromoCode(promoCode);
    }
    @Cacheable("Validation")
    public PromotionResponse validatePromo(Set<String> promoCode) {
        logger.info("Validating the customers promo Code: {}", promoCode);

        return iPromotionRepo.findByPromoCode(promoCode)
                .stream()
                .map(promotionMapper::toResponse).findFirst()
                .orElseThrow(() -> new RuntimeException("Promo Code does not exist"));

    }

    @Cacheable("ListOfInfluencersAndPromoCode")
    public List<PromotionResponse> getAllPromotersAndPromoCodes(String influencers, Set<String> promoCode) {
        logger.info("Displaying all influencers {} and there Promo Codes {}", influencers, promoCode);
        return iPromotionRepo.findAllBySocialHandleAndPromoCode(influencers, promoCode).stream().map(promotionMapper::toResponse).collect(Collectors.toList());
    }
}
