package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.user.model.User;

import java.util.List;

public interface UserDao {
    /**
     * Получить объект User по идентификатору
     *
     * @param id идентификатор User
     * @return {@link User}
     */
    User loadById(int id);

    /**
     * Получить списоку объектов User по фильтрам
     *
     * @param officeId идентификатор офиса к которому прикреплен пользователь (NotNull)
     * @param firstName имя пользователя
     * @param secondName фамилия пользователя
     * @param middleName отчество пользователя
     * @param position должность пользователя
     * @param docCode код документа пользователя
     * @param citizenshipCode код страны пользователя
     * @return список {@link User}
     */
    List<User> listByFilter(Integer officeId, String firstName, String secondName, String middleName, String position, Integer docCode, Integer citizenshipCode);
}
