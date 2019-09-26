package ru.bellintegrator.practice.office.dao;

import ru.bellintegrator.practice.office.model.Office;

import java.util.List;

public interface OfficeDao {

    Office loadById(Integer id);

    List<Office> listByFilter(int orgId, String name, String phone, Boolean active);
}
