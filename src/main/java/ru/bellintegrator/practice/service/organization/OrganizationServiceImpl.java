package ru.bellintegrator.practice.service.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.exception.NotEntityException;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.dto.OrganizationDto;
import ru.bellintegrator.practice.view.DataResponseView;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrganizationServiceImpl implements OrganizationService{
    private OrganizationDao dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void update(OrganizationDto organizationDto) {
        Organization organization = dao.loadById(organizationDto.getId());
        if (organization == null){
            throw new NotEntityException("Отсутствует организация с id " + organizationDto.getId());
        }
        else {
            transform(organization, organizationDto);
        }
    }

    @Override
    public DataResponseView getOrganizationById(Integer id) {
        Organization organization = dao.loadById(id);
        if (organization != null) {
            return new DataResponseView(new OrganizationDto(organization));
        }
        else {
            throw new NotEntityException("Отсутствует организация с id " + id);
        }
    }

    @Override
    public DataResponseView getOrganizationsListByFilter(OrganizationDto organizationDto) {
        List<OrganizationDto> dtoList = new ArrayList<>();
        List<Organization> organizationList = dao.listByFilter(organizationDto.getName(), organizationDto.getInn(), organizationDto.isActive());

        for (Organization org : organizationList){
            dtoList.add(new OrganizationDto(org));
        }

        if (dtoList.isEmpty()){
            throw new NotEntityException("Отсутствуют организации с заданными параметрами");
        }

        return new DataResponseView(dtoList);
    }

    @Override
    @Transactional
    public void save(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        transform(organization, organizationDto);
        dao.save(organization);
    }

    private void transform(Organization organization, OrganizationDto organizationDto){
        organization.setName(organizationDto.getName());
        organization.setFullName(organizationDto.getFullName());
        organization.setInn(organizationDto.getInn());
        organization.setKpp(organizationDto.getKpp());
        organization.setAddress(organizationDto.getAddress());
        if (organizationDto.getPhone() != null){
            organization.setPhone(organizationDto.getPhone());
        }
        if (organizationDto.isActive() != null){
            organization.setActive(organizationDto.isActive());
        }
    }
}
