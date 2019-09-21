package ru.bellintegrator.practice.service.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.dto.OrganizationDto;
import ru.bellintegrator.practice.view.DataResponseView;
import ru.bellintegrator.practice.view.SuccessResponceView;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    private OrganizationDao dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public SuccessResponceView update(OrganizationDto organizationDto) {
        return new SuccessResponceView(true);
    }

    @Override
    public DataResponseView<OrganizationDto> getOrganizationById(Integer id) {
        Organization organization = dao.loadById(id);
        return new DataResponseView<OrganizationDto>(new OrganizationDto(organization));
    }

    @Override
    public DataResponseView<OrganizationDto> getOrganizationsListByFilter(OrganizationDto organizationDto) {
        return null;
    }

    @Override
    public SuccessResponceView save(OrganizationDto organizationDto) {
        return null;
    }
}
