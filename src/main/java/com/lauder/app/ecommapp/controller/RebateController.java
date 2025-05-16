package com.lauder.app.ecommapp.controller;

import com.lauder.app.ecommapp.dto.request.rebate.RebateEarnedRequest;
import com.lauder.app.ecommapp.dto.response.rebate.RebateEarnedResponse;
import com.lauder.app.ecommapp.service.RebateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/rebate")
public class RebateController {

    final Logger logger = LoggerFactory.getLogger(RebateController.class);

    final RebateService rebateService;

    @Autowired
    public RebateController(RebateService rebateService) {
        this.rebateService = rebateService;
    }

    @GetMapping("/count-earnings/{request}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<RebateEarnedResponse> countEarnings(@RequestBody @PathVariable RebateEarnedRequest request) {

        RebateEarnedResponse response = rebateService.countEarnings(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-earnings")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RebateEarnedResponse> updateEarnings(@RequestBody RebateEarnedRequest request) {

        RebateEarnedResponse response = rebateService.updateEarnings(request);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/fetch-earnings")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RebateEarnedResponse>> fetchingAllCurrentPeriodEarningsForAllPromoter() {

        List<RebateEarnedResponse> response = rebateService.fetchAllCurrentEarningsFromPromoter();

        return ResponseEntity.ok(response);

    }

    @PostMapping("/fetch-earnings/{}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<RebateEarnedResponse>> fetchingEarningsFromCurrentEarningsForPromoters(@RequestBody RebateEarnedRequest request) {

        Set<RebateEarnedResponse> responses = rebateService.fetchCurrentPeriodEarningsForPromoter(request);

        return ResponseEntity.ok(responses);
    }


}
