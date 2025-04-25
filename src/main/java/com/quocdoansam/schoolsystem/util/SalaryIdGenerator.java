package com.quocdoansam.schoolsystem.util;

import java.io.Serializable;
import java.time.Year;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class SalaryIdGenerator implements IdentifierGenerator {
    private static final String PREFIX = "SA" + Year.now().getValue();

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        String query = "SELECT MAX(s.id) FROM Salary s";
        String maxId = (String) session.createQuery(query).uniqueResult();

        int nextId = 1;
        if (maxId != null && maxId.startsWith(PREFIX)) {
            int current = Integer.parseInt(maxId.substring(PREFIX.length()));
            nextId = current + 1;
        }

        return PREFIX + String.format("%05d", nextId);
    }
}
