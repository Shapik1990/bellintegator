package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.Organization;

public interface OrganizationDao {

    Organization loadById(Integer id);

    void update(Organization organization);
}
