package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.Organization;

import java.util.List;

public interface OrganizationDao {

    Organization loadById(Integer id);

    void update(Organization organization);

    void save(Organization organization);

    List<Organization> listByFilter(String name, String inn, Boolean active);
}
