package com.lauder.app.ecommapp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    private final Logger logger = LoggerFactory.getLogger(AuditService.class);

    public void  logPasswordChange(Long userId, String message) {

        logger.info( "Users: {}, Message: {}", userId, message);


    }
}
