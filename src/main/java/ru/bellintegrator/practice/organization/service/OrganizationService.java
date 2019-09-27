package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {

    /**
     * Обновить данные организации
     *
     * @param organizationDto данные для обновления обернутые в {@link OrganizationDto}
     */
    void update(OrganizationDto organizationDto);

    /**
     * Получить организацию по идентификатору
     *
     * @param id идентификатор организации
     * @return {@link OrganizationDto}
     */
    OrganizationDto getOrganizationById(Integer id);

    /**
     * Получить список организаций по фильтрам
     *
     * @param organizationDto данные для фильтрации обернутые в {@link OrganizationDto}
     * @return список {@link OrganizationDto}
     */
    List<OrganizationDto> getOrganizationsListByFilter(OrganizationDto organizationDto);

    /**
     * Сохранить новую организацию
     *
     * @param organizationDto данные для сохранения обернутые в {@link OrganizationDto}
     */
    void save(OrganizationDto organizationDto);
}
