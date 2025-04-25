package com.quocdoansam.schoolsystem.util;

import java.io.Serializable;
import java.time.Year;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UserIdGenerator implements IdentifierGenerator {
    private static final String PREFIX = "EDURA" + Year.now().getValue();

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        String query = "SELECT MAX(u.id) FROM User u";
        String maxId = (String) session.createQuery(query).uniqueResult();

        int nextId = 1;
        if (maxId != null && maxId.startsWith(PREFIX)) {
            int current = Integer.parseInt(maxId.substring(PREFIX.length()));
            nextId = current + 1;
        }

        return PREFIX + String.format("%05d", nextId);
    }
}
