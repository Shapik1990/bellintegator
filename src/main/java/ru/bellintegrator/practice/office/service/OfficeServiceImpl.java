package ru.bellintegrator.practice.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.exception.NotEntityException;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.dto.OfficeDto;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private OfficeDao officeDao;
    private OrganizationDao organizationDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrganizationDao organizationDao) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeDto officeDto) {
        Office office = officeDao.loadById(officeDto.getId());

        if (office == null){
            throw new NotEntityException("Не найден офис с id " + officeDto.getId());
        }

        transform(officeDto, office);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OfficeDto officeDto) {
        Organization organization = organizationDao.loadById(officeDto.getOrgId());
        Office office = new Office();

        if (organization == null){
            throw new NotEntityException("Не найдена организация с orgId " + officeDto.getOrgId());
        }

        transform(officeDto, office);
        office.setOrgId(organization);
        organization.getOfficeSet().add(office);

    }

    /**
     *{@inheritDoc}
     */
    @Override
    @Transactional
    public List<OfficeDto> getOfficesListByFilter(OfficeDto officeDto) {
        List<Office> officeList = officeDao.listByFilter(officeDto.getOrgId(),officeDto.getName(), officeDto.getPhone(), officeDto.isActive());

        if (officeList.isEmpty()){
            throw new NotEntityException("Не найдены офисы с указанными параметрами");
        }

        List<OfficeDto> dtoList = new ArrayList<>();

        for (Office office : officeList){
            dtoList.add(new OfficeDto(office));
        }

        return dtoList;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeDto getOfficeById(int id) {
        Office office = officeDao.loadById(id);

        if (office == null){
            throw new NotEntityException("Не найден офис с id" + id);
        }

        return new OfficeDto(office);
    }

    private void transform(OfficeDto officeDto, Office office) {
        if (officeDto.getName() != null) {
            office.setName(officeDto.getName());
        }
        if (officeDto.getAddress() != null) {
            office.setAddress(officeDto.getAddress());
        }
        if (officeDto.getPhone() != null){
            office.setPhone(officeDto.getPhone());
        }
        if (officeDto.isActive() != null){
            office.setActive(officeDto.isActive());
        }
    }
}
