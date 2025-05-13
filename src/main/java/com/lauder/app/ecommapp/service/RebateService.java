package com.lauder.app.ecommapp.service;

import com.lauder.app.ecommapp.dto.request.rebate.RebateEarnedRequest;
import com.lauder.app.ecommapp.dto.response.rebate.RebateEarnedResponse;
import com.lauder.app.ecommapp.mapper.rebateMapper.RebateEarnedMapper;
import com.lauder.app.ecommapp.model.RebateEarned;
import com.lauder.app.ecommapp.repo.IRebateRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RebateService {

    private final Logger logger = LoggerFactory.getLogger(RebateService.class);

    private final IRebateRepo rebateRepo;

    private final RebateEarnedMapper rebateEarnedMapper;

    @Autowired
    RebateService(IRebateRepo rebateRepo, RebateEarnedMapper rebateEarnedMapper) {

        this.rebateRepo = rebateRepo;
        this.rebateEarnedMapper = rebateEarnedMapper;
    }

    public RebateEarnedResponse countEarnings(RebateEarnedRequest request) {
        logger.info("Calculating earnings based of promoters promotion code being used: ID: {}, Code {}: ",
                request.getPromoter().getPromotersId(),
                request.getPromotionCode().getPromoCode());

        Optional<RebateEarned> countEarnings = rebateRepo.countRebateEarnedByPromoterAndMonth(
                request.getPromoter(),
                request.getMonth(),
                request.getEarnings());

        RebateEarned entityToSave = rebateEarnedMapper.toEntity(request, countEarnings.orElse(null));

        RebateEarned savedEntity = rebateRepo.save(entityToSave);

        return rebateEarnedMapper.toResponse(savedEntity);
    }

    public RebateEarnedResponse updateEarnings(RebateEarnedRequest request) {
        logger.info("Update earnings for promoter {} ", request.getEarnings());

        Optional<RebateEarned> existingRecord = rebateRepo.findByPromoterAndIdAndMonth(
                request.getPromoter(),
                request.getId(),
                request.getMonth()
        );


        RebateEarned entityToSave = rebateEarnedMapper.toEntity(request, existingRecord.orElse(null));

        RebateEarned savedEntity = rebateRepo.save(entityToSave);

        return rebateEarnedMapper.toResponse(savedEntity);

    }
}
