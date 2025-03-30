package com.project.ecommerce.order_management.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class SOIdGenerator implements IdentifierGenerator {

    private static final String PREFIX = "SO";
    private static final int MIN_ID = 100000;
    private static final int MAX_ID = 999999;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object entity) {
        int randomId = new Random().nextInt(MAX_ID - MIN_ID + 1) + MIN_ID;
        return PREFIX + randomId;
    }
}
