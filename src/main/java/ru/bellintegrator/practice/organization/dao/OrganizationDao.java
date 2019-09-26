package ru.bellintegrator.practice.organization.dao;

import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;

public interface OrganizationDao {

    Organization loadById(Integer id);

    void save(Organization organization);

    List<Organization> listByFilter(String name, String inn, Boolean active);
}
