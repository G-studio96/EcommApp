package com.lauder.app.ecommapp.service;

import com.lauder.app.ecommapp.model.Promoter;
import com.lauder.app.ecommapp.model.PromotionModel;
import com.lauder.app.ecommapp.model.RebateEarned;
import com.lauder.app.ecommapp.repo.IRebateRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RebateService {

    private final Logger logger = LoggerFactory.getLogger(RebateService.class);

    private final IRebateRepo rebateRepo;


    RebateService(IRebateRepo rebateRepo) {
        this.rebateRepo = rebateRepo;
    }

    public BigDecimal calculateEarnings(Promoter promoter, PromotionModel promotionModel) {
        logger.info("Calculating earnings based of promoters promotion code being used: ID: {}, Code {}: ", promoter.getPromotersId(), promotionModel.getPromoCode());
        int codesCount = promotionModel.getPromoCodeCount();
        BigDecimal rate = BigDecimal.valueOf(promotionModel.getDiscount());
        return rate.multiply(BigDecimal.valueOf(codesCount));
    }

    public void updateEarnings(Promoter promoter, PromotionModel promotionModel) {
        logger.info("Update earnings for promoter {} ", promoter);
        BigDecimal earnings = calculateEarnings(promoter, promotionModel);
        RebateEarned rebate = new RebateEarned();
        rebate.setPromoter(promoter);
        rebate.setPromotion(promotionModel);
        rebate.setEarnings(earnings);


    }
}
