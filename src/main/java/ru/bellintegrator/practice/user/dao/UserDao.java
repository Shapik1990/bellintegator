package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.user.model.User;

import java.util.List;

public interface UserDao {
    User loadById(int id);

    List<User> listByFilter(Integer officeId, String firstName, String secondName, String middleName, String position, Integer docCode, Integer citizenshipCode);
}
