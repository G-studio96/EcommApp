package com.lauder.app.ecommapp.service;

import com.lauder.app.ecommapp.dto.request.order.OrderRequest;
import com.lauder.app.ecommapp.dto.request.promoter.PromoterRequest;
import com.lauder.app.ecommapp.dto.request.promotion.PromotionRequest;
import com.lauder.app.ecommapp.dto.response.order.OrderResponse;
import com.lauder.app.ecommapp.dto.response.promoter.PromoterResponse;
import com.lauder.app.ecommapp.dto.response.users.UsersResponse;
import com.lauder.app.ecommapp.mapper.promoterMapper.PromoterMapper;
import com.lauder.app.ecommapp.mapper.usersMapper.UsersMapper;
import com.lauder.app.ecommapp.mapper.promotionMapper.PromotionMapper;
import com.lauder.app.ecommapp.model.Promoter;
import com.lauder.app.ecommapp.model.PromotionModel;
import com.lauder.app.ecommapp.repo.IPromoterRepo;
import com.lauder.app.ecommapp.repo.IUsersRepo;
import com.lauder.app.ecommapp.repo.IPromotionRepo;
import com.lauder.app.ecommapp.repo.IRoleRepo;
import com.lauder.app.ecommapp.util.PasswordEncryption;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminService {

    final Logger logger = LoggerFactory.getLogger(AdminService.class);
    final IUsersRepo customerRepo;

    final IPromoterRepo promoterRepo;

    final PromoterMapper promoterMapper;

    final  IPromotionRepo promotionRepo;

    final PromotionMapper promotionMapper;

    final UsersMapper usersMapper;
    final IRoleRepo roleRepo;



    final PasswordEncryption passwordEncryption;

    @Autowired
    public AdminService(IUsersRepo customerRepo, UsersMapper usersMapper,
                        IRoleRepo roleRepo, PasswordEncryption passwordEncryption,
                        IPromoterRepo promoterRepo, PromoterMapper promoterMapper,
                        IPromotionRepo promotionRepo, PromotionMapper promotionMapper) {
        this.customerRepo = customerRepo;
        this.roleRepo = roleRepo;
        this.passwordEncryption = passwordEncryption;
        this.usersMapper = usersMapper;
        this.promoterRepo = promoterRepo;
        this.promoterMapper= promoterMapper;
        this.promotionRepo = promotionRepo;
        this.promotionMapper = promotionMapper;



    }

    @Cacheable("CollectingAllUsers")
    public List<UsersResponse> getAllUsers() {
        logger.info("Fetching all users...");
        return customerRepo.findAll().stream()
                .map(usersMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Cacheable("CollectyingAllPromoters")
    public List<PromoterResponse> getAllPromoters() {
        logger.info("Fetching all promoters...");
        return promoterRepo.findAll().stream()
                .map(promoterMapper::toResponse)
                .collect(Collectors.toList());


    }

    @Cacheable("CreateNewPromoter")
    public PromoterResponse createNewPromoter(PromoterRequest promoterRequest, PromotionRequest promotionRequest) {
        logger.info("Checking if promoter already exists email: {} and socialHandle {}", promoterRequest.getEmail(), promoterRequest.getSocialHandle());

        Optional<Promoter> existingPromoter = promoterRepo.findPromotersByEmailAndSocialHandle(promoterRequest.getEmail(), promoterRequest.getSocialHandle());

        if (existingPromoter.isPresent()) {

            logger.info("Promoter already exists. Returning existing promoter");
            return promoterMapper.toResponse(existingPromoter.get());
        }


        logger.info("Create new promoter profile");

        Promoter promoter = promoterMapper.toEntity(promoterRequest);
        Promoter savedPromoter = promoterRepo.save(promoter);


        PromotionModel promotion = promotionMapper.toEntity(promotionRequest);
        promotion.setPromoter(savedPromoter);
        promotion.generatePromotion(promoter.getSocialHandle().toString(), PromotionModel.Platform.valueOf(promoter.getPlatform().toString()), promotion.getFollowers());
        promotionRepo.save(promotion);

        return  promoterMapper.toResponse(savedPromoter);

    }

    @Cacheable("AddMoreSocialHandles")
    public List<PromoterResponse> addMorePlatform(PromoterRequest request){
        logger.info("Adding more social to the promoters account ID: {}", request.getPromotersId());

        Optional<Promoter> promoter = promoterRepo.findById(request.getPromotersId());

        if(promoter.isEmpty()) {
            logger.info("Checking for promoters account ID: {}", request.getSocialHandle());
            throw new RuntimeException("Can't find the promoters account ");
        }


        if (request.getSocialHandle() != null && !request.getSocialHandle().isEmpty()) {
            promoter.get().getSocialHandle().addAll(request.getSocialHandle());
        }

        if (request.getPlatform() != null && !request.getPlatform().isEmpty()) {
            promoter.get().getPlatform().addAll(request.getPlatform());
        }


        return promoter.map(promoterMapper::toResponse).stream().collect(Collectors.toList());



    }

    @Cacheable("DeletePromoter")
    public  PromoterResponse deletePromoter(PromoterRequest request) {
        logger.info("Deleting new promoters {}", request.getName());

        Optional<Promoter> promoter = promoterRepo.findPromoterByName(request.getName());

        if (promoter.isEmpty()) {
            logger.info("Name of promoter is not found {}. Checking email address: {}", request.getName(), request.getEmail());

            promoter = promoterRepo.findPromotersByEmail(request.getEmail());
        }

        if  (promoter.isEmpty()) {
            logger.info("Email addreess is not found {}. Checking promoter Social Handle", request.getSocialHandle());
            promoter = promoterRepo.findBySocialHandle(request.getSocialHandle());
        }

        Promoter req = promoter.orElseThrow(() -> new RuntimeException("Promoter not found by name, email and socialHandle"));

        promoterRepo.delete(req);

        return  promoterMapper.toResponse(req);
    }

    @Cacheable("OrdersMade")
    public OrderResponse orderMade(OrderRequest request) {

        /// create better Orders
        return null;
    }

    @Cacheable("RevenueGenerate")
    public OrderResponse revenueGenerate(OrderRequest request) {
        return null;
    }



}