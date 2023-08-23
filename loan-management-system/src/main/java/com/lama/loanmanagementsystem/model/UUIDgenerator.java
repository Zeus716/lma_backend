package com.lama.loanmanagementsystem.model;

import org.hibernate.HibernateException;
import org.hibernate.SharedSessionContract;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class UUIDgenerator implements IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        UUID randomUUIDd = UUID.randomUUID();
        String uniqueId = randomUUIDd.toString().substring(0,6);
        return uniqueId;
    }
}
