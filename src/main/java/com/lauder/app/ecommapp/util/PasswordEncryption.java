package com.lauder.app.ecommapp.util;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryption implements PasswordEncoder {

    private final ConfigurablePasswordEncryptor passwordEncryptor;

    public PasswordEncryption() {
        this.passwordEncryptor = new ConfigurablePasswordEncryptor();
        this.passwordEncryptor.setAlgorithm("SHA-256");
        this.passwordEncryptor.setPlainDigest(false);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncryptor.encryptPassword(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncryptor.checkPassword(rawPassword.toString(), encodedPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
