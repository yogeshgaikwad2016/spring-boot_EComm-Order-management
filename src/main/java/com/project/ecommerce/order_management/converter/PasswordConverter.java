package com.project.ecommerce.order_management.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Converter
public class PasswordConverter implements AttributeConverter<String, String> {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String convertToDatabaseColumn(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public String convertToEntityAttribute(String encoded) {
        return encoded;
    }
}
