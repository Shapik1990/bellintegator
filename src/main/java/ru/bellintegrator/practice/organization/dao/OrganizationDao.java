package ru.bellintegrator.practice.organization.dao;

import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;

public interface OrganizationDao {

    /**
     * Получить объект Organization по идентификатору
     *
     * @param id идентификатор Organization
     * @return {@link Organization}
     */
    Organization loadById(Integer id);

    /**
     * Сохранить новвый объект Organization
     *
     * @param organization объект для сохранения
     */
    void save(Organization organization);

    /**
     * Получить список объектов Organization по фильтрам
     *
     * @param name название организации (NotNull)
     * @param inn инн организации
     * @param active активность организации
     * @return список {@link Organization}
     */
    List<Organization> listByFilter(String name, String inn, Boolean active);
}
