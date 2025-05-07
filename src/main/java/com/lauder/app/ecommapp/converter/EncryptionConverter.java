package com.lauder.app.ecommapp.converter;

import com.lauder.app.ecommapp.util.CryptoHelper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EncryptionConverter implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attr) {
        return CryptoHelper.encrypt(attr);
    }

    @Override
    public String convertToEntityAttribute(String dbBase) {
        return CryptoHelper.decrypt(dbBase);
    }
}
