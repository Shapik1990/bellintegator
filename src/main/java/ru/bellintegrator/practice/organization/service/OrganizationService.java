package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.dto.OrganizationDto;
import ru.bellintegrator.practice.view.DataResponseView;

public interface OrganizationService {

    void update(OrganizationDto organizationDto);

    DataResponseView getOrganizationById(Integer id);

    DataResponseView getOrganizationsListByFilter(OrganizationDto organizationDto);

    void save(OrganizationDto organizationDto);
}
