package ru.bellintegrator.practice.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.exception.NotEntityException;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.dto.OrganizationDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrganizationServiceImpl implements OrganizationService{

    private OrganizationDao dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationDto organizationDto) {
        Organization organization = dao.loadById(organizationDto.getId());

        if (organization == null){
            throw new NotEntityException("Не найдена организация с id " + organizationDto.getId());
        }

        transform(organization, organizationDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrganizationDto getOrganizationById(Integer id) {
        Organization organization = dao.loadById(id);

        if (organization == null) {
            throw new NotEntityException("Не найдена организация с id " + id);
        }

        return new OrganizationDto(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<OrganizationDto> getOrganizationsListByFilter(OrganizationDto organizationDto) {
        List<Organization> organizationList = dao.listByFilter(organizationDto.getName(), organizationDto.getInn(), organizationDto.isActive());

        if (organizationList.isEmpty()){
            throw new NotEntityException("Не найдены организации с указанными параметрами");
        }

//        List<OrganizationDto> dtoList = new ArrayList<>();
//
//        for (Organization org : organizationList){
//            dtoList.add(new OrganizationDto(org));
//        }

        List<OrganizationDto> dtoList = organizationList.stream().
                map(x -> new OrganizationDto(x)).
                collect(Collectors.toList());

        return dtoList;
    }

    /**
     * {@inheritDoc}
     */
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
