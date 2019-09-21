package ru.bellintegrator.practice.service.organization;

import ru.bellintegrator.practice.dto.OrganizationDto;
import ru.bellintegrator.practice.view.DataResponseView;
import ru.bellintegrator.practice.view.SuccessResponceView;

public interface OrganizationService {

    SuccessResponceView update(OrganizationDto organizationDto);

    DataResponseView<OrganizationDto> getOrganizationById(Integer id);

    DataResponseView<OrganizationDto> getOrganizationsListByFilter(OrganizationDto organizationDto);

    SuccessResponceView save(OrganizationDto organizationDto);
}
