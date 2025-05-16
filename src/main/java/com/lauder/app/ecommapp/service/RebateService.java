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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class RebateService {

    private final Logger logger = LoggerFactory.getLogger(RebateService.class);

    private final IRebateRepo rebateRepo;

    private final RebateEarnedMapper rebateEarnedMapper;

    private final Map<RebateEarned, BigDecimal> currentEarningForTheMonth = new HashMap<>();

    @Autowired
    RebateService(IRebateRepo rebateRepo, RebateEarnedMapper rebateEarnedMapper) {
        this.rebateRepo = rebateRepo;
        this.rebateEarnedMapper = rebateEarnedMapper;
    }

    public RebateEarnedResponse countEarnings(RebateEarnedRequest request) {
        logger.info("Calculating earnings based of promoters promotion code being used: ID: {}, Code {}: ",
                request.getPromoter().getPromotersId(),
                request.getPromotionCode().getPromoCode());


        Optional<RebateEarned> countEarnings = rebateRepo.findByPromoterAndIdAndMonth(
                request.getPromoter(),
                request.getId(),
                request.getMonth());

        RebateEarned entityToSave = rebateEarnedMapper.toEntity(request, countEarnings.orElse(null));

        RebateEarned savedEntity = rebateRepo.save(entityToSave);

        return rebateEarnedMapper.toResponse(savedEntity);
    }

    public RebateEarnedResponse updateEarnings(RebateEarnedRequest request) {
        logger.info("Update earnings for promoter {} ", request.getEarnings());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thirtyDaysAgo = now.minusDays(30);

        if (request.getStartMonth().isBefore(thirtyDaysAgo)) {
            throw new IllegalArgumentException("Request is outside of current period");
        }

        Optional<RebateEarned> existingRecord = rebateRepo.findByPromoterAndIdAndMonth(
                request.getPromoter(),
                request.getId(),
                request.getMonth()
        );

        RebateEarned entityToSave = existingRecord.map(existing -> {
            existing.setEarnings(existing.getEarnings().add(request.getEarnings()));
            return existing;
        }).orElseGet(() -> rebateEarnedMapper.toEntity(request, null));

        RebateEarned savedEntity = rebateRepo.save(entityToSave);

        currentEarningForTheMonth.entrySet().forEach(entry -> {
            if (entry.getKey().equals(savedEntity)) {
                entry.setValue(savedEntity.getEarnings());
            }
        });

        return rebateEarnedMapper.toResponse(savedEntity);
    }

    public Set<RebateEarnedResponse> fetchCurrentPeriodEarningsForPromoter(RebateEarnedRequest request) {
        logger.info("fetching the for the current period for the promoters: {}", request.getPromoter().getPromotersId());
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);

        List<RebateEarned> earnings = rebateRepo.findPromoterEarningsFromCurrentPeriod(
                request.getPromoter().getPromotersId(),
                thirtyDaysAgo);

        return earnings.stream()
                .map(rebateEarnedMapper::toResponse)
                .collect(Collectors.toSet());
    }

    public List<RebateEarnedResponse> fetchAllCurrentEarningsFromPromoter() {
        logger.info("fetching all date for current earnings for all Promoters, Earnings");
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);

        List<RebateEarned> earnings = rebateRepo.findAllByEarningsFromCurrentPeriod(thirtyDaysAgo);

        return earnings.stream()
                .map(rebateEarnedMapper::toResponse)
                .collect(Collectors.toList());
    }
}
