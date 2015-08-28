package com.adamantium.dao;

import com.adamantium.common.HibernateUtil;
import com.adamantium.entity.Company;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CompanyDaoImpl implements CompanyDAO {
    @Override
    public List<Company> getAll() {
        // Create a Hibernate query (HQL)
        Query query = getSession().createQuery("FROM  com.adamantium.entity.Company");
        // Retrieve all
        List<Company> result = query.list();
        getSession().close();
        return result;
    }

    @Override
    public Company get(Integer id) {
        // Retrieve existing person first
        Company company = (Company) getSession().get(Company.class, id);
        getSession().close();
        return company;
    }

    @Override
    public String getNameById(Integer id) {
        Query query = getSession().createQuery("SELECT name FROM  com.adamantium.entity.Company where id=?");
        query.setString(0, id.toString());
        String name = (String) query.uniqueResult();
        getSession().close();
        return name;
    }

    @Override
    public String getDescriptionById(Integer id) {
        Query query = getSession().createQuery("SELECT description FROM  com.adamantium.entity.Company where id=?");
        query.setString(0, id.toString());
        String description = (String) query.uniqueResult();
        getSession().close();
        return description;
    }

    @Override
    public String getAddressById(Integer id) {
        Query query = getSession().createQuery("SELECT address FROM  com.adamantium.entity.Company where id=?");
        query.setString(0, id.toString());
        String address = (String) query.uniqueResult();
        getSession().close();
        return address;
    }

    @Override
    public String getPhoneById(Integer id) {
        Query query = getSession().createQuery("SELECT phone FROM  com.adamantium.entity.Company where id=?");
        query.setString(0, id.toString());
        String phone = (String) query.uniqueResult();
        getSession().close();
        return phone;
    }

    @Override
    public void add(Company company) {
        // Save
        Transaction transaction = getSession().beginTransaction();
        getSession().save(company);
        transaction.commit();
        getSession().close();
    }

    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
