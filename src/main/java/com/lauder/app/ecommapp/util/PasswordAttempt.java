package com.lauder.app.ecommapp.util;

import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountLockedException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PasswordAttempt {
    private final Map<String, Integer> attemptsCache = new ConcurrentHashMap<>();

        private static  final int MAX_ATTEMPTS = 5;

        public void checkRateLimit(String userName) throws AccountLockedException {
            if (attemptsCache.getOrDefault(userName, 0) >= MAX_ATTEMPTS) {
                throw new AccountLockedException("Too many failed attempts. Account temporarily locked");
            }
        }

        public void recordFailedAttempt(String username) {
            attemptsCache.put(username, attemptsCache.getOrDefault(username, 0) +
                    1);
        }

        public void resetFailedAttempts(String username) {
            attemptsCache.remove(username);
        }

}

