package com.lauder.app.ecommapp.controller;

import com.lauder.app.ecommapp.dto.request.promoter.PromoterRequest;
import com.lauder.app.ecommapp.dto.request.promotion.PromotionRequest;
import com.lauder.app.ecommapp.dto.request.users.UsersRequest;
import com.lauder.app.ecommapp.dto.response.promoter.PromoterResponse;
import com.lauder.app.ecommapp.dto.response.promotion.PromotionResponse;
import com.lauder.app.ecommapp.dto.response.users.UsersResponse;
import com.lauder.app.ecommapp.repo.IPromotionRepo;
import com.lauder.app.ecommapp.repo.IRoleRepo;
import com.lauder.app.ecommapp.service.AdminService;
import com.lauder.app.ecommapp.service.UsersService;
import com.lauder.app.ecommapp.service.PromotionService;
import com.lauder.app.ecommapp.util.PasswordEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/dashboard")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashboardController {

    final Logger logger = LoggerFactory.getLogger(AdminDashboardController.class);

     final PromotionService promotionService;

     final UsersService usersService;

     final AdminService adminService;

    final IPromotionRepo iPromotionRepo;

    final IRoleRepo roleRepo;

    final PasswordEncryption passwordEncryption;

    @Autowired
    public AdminDashboardController(PromotionService promotionService, UsersService usersService, IRoleRepo roleRepo,
                                    IPromotionRepo iPromotionRepo, PasswordEncryption passwordEncryption, AdminService adminService) {

        this.promotionService = promotionService;
        this.usersService = usersService;
        this.roleRepo = roleRepo;
        this.iPromotionRepo = iPromotionRepo;
        this.passwordEncryption = passwordEncryption;
        this.adminService = adminService;
    }

    @GetMapping("/all-promotions")
    public List<PromotionResponse> getAllPromotions(){
        logger.info("Fetching all promotions codes... {}", "");
        return promotionService.getPromoCodes();
    }

    @GetMapping("/discount-range")
    public List<PromotionResponse> getPromotionByDiscount(@RequestParam float min, @RequestParam float max) {
        logger.info("Fetching discount with promo codes... Min {} and Max {}", min, max );
        return promotionService.findDiscountBetween(min, max);
    }

    @GetMapping("/count-by-handle/{socialHandle}")
    public Long countBySocialHandle(@PathVariable String socialHandle) {
        logger.info("Count the how many time the promo codes are used... count: {}", socialHandle );
        return promotionService.countPromoCodesByHandle(socialHandle);
    }

    @GetMapping("/count-by-/{promoCode}")
    public Long countByPromoCode(@PathVariable Set<String> promoCode) {
        logger.info("Count the amount of time a promo code used by {}", promoCode );
        return promotionService.countByPromoCode(promoCode);
    }





    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin-create-influencer")
    public ResponseEntity<PromoterResponse> createPromoter(@RequestBody PromotionRequest promotionRequest, @RequestBody PromoterRequest promoterRequest) {
        logger.info("Creating a new Promoter. adding to the db {}", promoterRequest);

      PromoterResponse createdPromoter = adminService.createNewPromoter(
              promoterRequest, promotionRequest);

      return ResponseEntity.ok(createdPromoter);

    }



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin-delete-influencers")
    public ResponseEntity<PromoterResponse> deletePromoter(@RequestBody PromoterRequest promoterRequest) {
        logger.info("Deleting a Promoter from the DB. ID: {}", promoterRequest.getPromotersId());

        PromoterResponse deletePromoter = adminService.deletePromoter(promoterRequest);

        return  ResponseEntity.ok(deletePromoter);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin-delete-customer")
    public ResponseEntity<UsersResponse> deleteCustomer(@RequestBody UsersRequest usersRequest) throws Exception {
        logger.info("Deleting a customer from the DB. ID: {}", usersRequest.getCustomerId());

        UsersResponse deleteCustomer = usersService.deleteUsers(usersRequest);

        return ResponseEntity.ok(deleteCustomer);
    }







}
