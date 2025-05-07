package com.lauder.app.ecommapp.converter;

import com.lauder.app.ecommapp.util.PasswordEncryption;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Converter
public class PasswordEncryptionConverter implements AttributeConverter<String, String> {

    private PasswordEncryption passwordEncryption;



    public PasswordEncryptionConverter() {

    }

    @Override
    public String convertToDatabaseColumn(String attribute)  {
        return  attribute != null ? passwordEncryption.encode(attribute) : null;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {

        if (dbData == null) {
            return null;
        }

        return dbData;
    }


}
