package com.lauder.app.ecommapp.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PaymentAuditAspect {

    private final Logger logger = LoggerFactory.getLogger(PaymentAuditAspect.class);

    @Around("execution(* com.lauder.app.ecommapp.repo.ICheckoutRepo.*(..))")
    public Object logPaymentAccess(ProceedingJoinPoint joinPoint) throws  Throwable {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth != null ? auth.getName() : "anonymous";

        logger.info("User {} accessed payment data via method {}",
                username, joinPoint.getSignature().getName());

        return joinPoint.proceed();
    }

}
