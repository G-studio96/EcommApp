package com.lauder.app.ecommapp.mapper.promotionMapper;

import com.lauder.app.ecommapp.dto.request.promotion.PromotionRequest;
import com.lauder.app.ecommapp.dto.response.promotion.PromotionResponse;
import com.lauder.app.ecommapp.model.PromotionModel;
import org.springframework.stereotype.Component;

@Component
public class PromotionMapper {

    public PromotionResponse toResponse(PromotionModel model) {
        if (model == null) {
            return null;
        }

        PromotionResponse response = new PromotionResponse();
        response.setPromoCode(model.getPromoCode());
        response.setFollowers(model.getFollowers());
        response.setDiscount(model.getDiscount());
        response.setPromoCodeCount(model.getPromoCodeCount());

        return response;
    }


    public PromotionModel toEntity(PromotionRequest request) {
        if (request == null) {
            return null;
        }

        PromotionModel model = new PromotionModel();


        PromotionModel.Platform platform = request.getPlatform();

        if (platform == null) {
            platform = PromotionModel.determinePlatformFromHandle(request.getSocialHandle());
        }

        model.setFollowers(request.getFollowers());

        if (request.getPromoCode() != null && !request.getPromoCode().isEmpty()) {

            model.setPromoCode(request.getPromoCode());
        } else {
            model.generatePromotion(request.getSocialHandle(), platform, request.getFollowers());
        }
        return model;
    }
}
