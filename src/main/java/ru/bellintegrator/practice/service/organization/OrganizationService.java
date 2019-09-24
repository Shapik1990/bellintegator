package ru.bellintegrator.practice.service.organization;

import ru.bellintegrator.practice.dto.OrganizationDto;
import ru.bellintegrator.practice.view.DataResponseView;


import java.util.List;

public interface OrganizationService {

    void update(OrganizationDto organizationDto);

    DataResponseView getOrganizationById(Integer id);

    DataResponseView getOrganizationsListByFilter(OrganizationDto organizationDto);

    void save(OrganizationDto organizationDto);
}
