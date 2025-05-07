package com.lauder.app.ecommapp.util;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PasswordHistory {

    private final Map<Long, String> passwordStore = new ConcurrentHashMap<Long, String>();

    PasswordHistory(PasswordEncryption passwordEncryption) {
    }


    public boolean isPasswordReused(Long userId, String newPassword) {




        return false;

    }

    public void savePassword(Long userId,  String password) {

        passwordStore.put(userId, password);
    }





}
