package com.adamantium.dao;

import com.adamantium.entity.Company;

import java.util.List;

public interface CompanyDAO {

    public List<Company> getAll();

    public Company get(Integer id);

    public String getNameById(Integer id);

    public String getDescriptionById(Integer id);

    public String getAddressById(Integer id);

    public String getPhoneById(Integer id);

    public void add(Company company);

}
