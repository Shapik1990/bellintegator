package ru.bellintegrator.practice.office.dao;

import ru.bellintegrator.practice.office.model.Office;

import java.util.List;

public interface OfficeDao {

    /**
     * Получить объект Office по идентификатору
     *
     * @param id идентификатор Office
     * @return {@link Office}
     */
    Office loadById(Integer id);

    /**
     * Получить спиок объектов Office согласно фильтрам
     *
     * @param orgId идентификатор организации к которой принадлежит офис (NotNull)
     * @param name название офиса
     * @param phone телефон офиса
     * @param active активность офиса
     * @return список {@link Office}
     */
    List<Office> listByFilter(int orgId, String name, String phone, Boolean active);
}
