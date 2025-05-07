package com.lauder.app.ecommapp.mapper.promoterMapper;

import com.lauder.app.ecommapp.dto.request.promoter.PromoterRequest;
import com.lauder.app.ecommapp.dto.response.promoter.PromoterResponse;
import com.lauder.app.ecommapp.model.Promoter;
import org.springframework.stereotype.Component;

@Component
public class PromoterMapper {

    public PromoterResponse toResponse(Promoter model) {
        if (model == null) {
            return null;
        }

        PromoterResponse response = new PromoterResponse();

        model.setPromotersId(response.getPromotersId());
        model.setName(response.getName());
        model.setSocialHandle(response.getSocialHandle());
        model.setEmail(response.getEmail());
        model.setPassword(response.getPassword());
        model.setRoles(model.getRoles());
        model.setPlatform(model.getPlatform());
        model.setAddressLineOne(model.getAddressLineOne());
        model.setAddressLineTwo(model.getAddressLineTwo());
        model.setAddressLineThree(model.getAddressLineThree());
        model.setCity(model.getCity());
        model.setCounty(model.getCounty());
        model.setCountry(model.getCountry());
        model.setCountry(model.getCountry());

        return response;

    }

    public Promoter toEntity(PromoterRequest request) {
        if (request == null ) {
            return null;
        }


        Promoter model = new Promoter();
        model.setPromotersId(request.getPromotersId());
        model.setName(request.getName());
        model.setEmail(request.getEmail());
        model.setPassword(request.getPassword());
        model.setRoles(request.getRoles());
        model.setSocialHandle(request.getSocialHandle());
        model.setPlatform(request.getPlatform());
        model.setAddressLineOne(request.getAddressLineOne());
        model.setAddressLineTwo(request.getAddressLineTwo());
        model.setAddressLineThree(request.getAddressLineThree());
        model.setCity(request.getCity());
        model.setCounty(request.getCounty());
        model.setPostCode(request.getPostCode());
        model.setCountry(request.getCountry());
        return model;

    }
}
