package com.lauder.app.ecommapp.controller;

import com.lauder.app.ecommapp.dto.response.promotion.PromotionResponse;
import com.lauder.app.ecommapp.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/promotions")
public class PromotionController {

    final Logger logger = LoggerFactory.getLogger(PromotionController.class);

    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("/validate")
    public PromotionResponse validatePromo(@RequestParam Set<String> promoCode) {
        logger.info("Validating Promo Code... {} ", promoCode);
        return promotionService.validatePromo(promoCode);
    }

    @GetMapping("/admin/list")
    public List<PromotionResponse> getAllInfluencersAndPromoCodes(@RequestParam String socialHandle, @RequestParam Set<String> promoCode ) {
        logger.info("Fetching all the promoters {} and their codes {}", socialHandle, promoCode);
        return promotionService.getAllPromotersAndPromoCodes(socialHandle, promoCode);
    }


}
