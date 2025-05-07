package com.lauder.app.ecommapp.controller;

import com.lauder.app.ecommapp.dto.response.promotion.PromotionResponse;
import com.lauder.app.ecommapp.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/influencers/dashboard")
public class InfluencersDashboardController {

    final Logger logger = LoggerFactory.getLogger(InfluencersDashboardController.class);

    private final PromotionService promotionService;

    @Autowired
    public InfluencersDashboardController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("/{socialHandle}/tracking")
    public PromotionResponse trackMyPromotion(@PathVariable String socialHandle) {
        logger.info("tracking all Promo codes");
        return promotionService.getSocialHandle(socialHandle);
    }

    @GetMapping("/{socialHandle}/orders/tracking/range/{startDate}/{endDate}")
    public List<PromotionResponse> trackingPromotionus(@PathVariable String socialHandle, @PathVariable Date startDate, @PathVariable Date endDate) {
        logger.info("Range of orders using influencers Promotion code ");

        PromotionResponse promoter = promotionService.getSocialHandle(socialHandle);

        return null;
    }


}




