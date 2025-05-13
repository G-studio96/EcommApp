package com.lauder.app.ecommapp.mapper.rebateMapper;

import com.lauder.app.ecommapp.dto.request.rebate.RebateEarnedRequest;
import com.lauder.app.ecommapp.dto.response.rebate.RebateEarnedResponse;
import com.lauder.app.ecommapp.model.RebateEarned;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RebateEarnedMapper {

    public RebateEarned toEntity(RebateEarnedRequest request, RebateEarned existingEntity) {
        if (request == null) {
            return null;
        }

        RebateEarned response = existingEntity != null ?
                existingEntity : new RebateEarned();

        if (response.getId() == null && request.getId() != null) {
            response.setId(request.getId());
        }

        if (response.getPromoter() == null) {
            response.setPromoter(request.getPromoter());
        }

        if (response.getPromotion() == null) {
            response.setPromotion(request.getPromotionCode());
        }

        if (response.getMonth() == null && request.getMonth() != null) {
            response.setMonth(response.getMonth());
        }
        if (request.getEarnings() != null) {
            BigDecimal currentEarnings = response.getEarnings() != null
                    ? response.getEarnings() : BigDecimal.ZERO;

            response.setEarnings(currentEarnings.add(request.getEarnings()));

        }

        response.setPromotion(request.getPromotionCode());


        return response;
    }

    public RebateEarnedResponse toResponse(RebateEarned modal) {
        if (modal == null) {
            return null;
        }

        RebateEarnedResponse response = new RebateEarnedResponse();

        response.setId(modal.getId());
        response.setEarnings(modal.getEarnings());
        response.setMonth(modal.getMonth());
        response.setStartMonth(modal.getStartMonth());
        response.setEndMonth(modal.getEndMonth());


        return response;

    }
}
