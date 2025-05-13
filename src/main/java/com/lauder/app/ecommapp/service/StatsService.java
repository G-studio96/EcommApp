package com.lauder.app.ecommapp.service;

import com.lauder.app.ecommapp.dto.response.admin.AdminStatsResponse;
import com.lauder.app.ecommapp.dto.response.promoter.PromoterStatsResponse;
import com.lauder.app.ecommapp.dto.response.users.CustomerStatsResponse;
import com.lauder.app.ecommapp.mapper.cartMapper.CartMapper;
import com.lauder.app.ecommapp.mapper.ordersMapper.OrderMapper;
import com.lauder.app.ecommapp.mapper.paymentsMapper.PaymentMapper;
import com.lauder.app.ecommapp.mapper.promoterMapper.PromoterMapper;
import com.lauder.app.ecommapp.mapper.usersMapper.UsersMapper;
import com.lauder.app.ecommapp.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Transactional
public class StatsService {

    final Logger logger = LoggerFactory.getLogger(StatsService.class);

    final IUsersRepo usersRepo;

    final UsersMapper usersMapper;

    final IOrderRepo orderRepo;

    final OrderMapper orderMapper;
    final ICheckoutRepo paymentRepo;

    final PaymentMapper paymentMapper;

    final IPromoterRepo promoterRepo;

    final PromoterMapper promoterMapper;

    final ICartRepo cartRepo;

    final CartMapper cartMapper;

    private LocalDate startDate;

    private LocalDate endDate;



    @Autowired
    public StatsService(IUsersRepo usersRepo, IOrderRepo orderRepo,
                        ICheckoutRepo paymentRepo, IPromoterRepo promoterRepo,
                        UsersMapper usersMapper, OrderMapper orderMapper,
                        PaymentMapper paymentMapper, PromoterMapper promoterMapper,
                        CartMapper cartMapper, ICartRepo cartRepo)
    {
        this.usersRepo = usersRepo;
        this.orderRepo = orderRepo;
        this.paymentRepo = paymentRepo;
        this.promoterRepo = promoterRepo;
        this.usersMapper = usersMapper;
        this.orderMapper = orderMapper;
        this.paymentMapper = paymentMapper;
        this.promoterMapper = promoterMapper;
        this.cartRepo = cartRepo;
        this.cartMapper = cartMapper;

    }

    @Cacheable("AdminStats")
    public AdminStatsResponse getAdminStats() {
        long totalUsers = usersRepo.count();
        long totalOrders = orderRepo.count();
        BigDecimal totalRevenue = paymentRepo.sumAllPayment();
        long totalPromoters = promoterRepo.count();

        return new AdminStatsResponse(totalUsers, totalOrders, totalRevenue, totalPromoters);


    }

    @Cacheable("PromoterStats")
    public PromoterStatsResponse getPromoterStats(Long promoterId) {
        long totalPromotions = promoterRepo.countPromotionsByPromotersId(promoterId);
        BigDecimal totalEarnings = promoterRepo.sumEarningsByPromoterId(promoterId);

        return new PromoterStatsResponse(totalPromotions, totalEarnings);
    }

    @Cacheable("CustomerStats")
    public CustomerStatsResponse getCustomerStats(Long customerId) {
        long ordersMade = orderRepo.countByCustomerId(customerId);
        BigDecimal amountSpent = paymentRepo.sumPaymentsByCustomerId(customerId);
        int cartItems = cartRepo.countByCustomerId(customerId);

        return  new CustomerStatsResponse(ordersMade, amountSpent, cartItems);
    }


}
