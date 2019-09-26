package ru.bellintegrator.practice.office.service;

import ru.bellintegrator.practice.office.dto.OfficeDto;
import ru.bellintegrator.practice.view.DataResponseView;


public interface OfficeService {

    void update(OfficeDto officeDto);

    void save(OfficeDto officeDto);

    DataResponseView getOfficesListByFilter(OfficeDto officeDto);

    DataResponseView getOfficeById(int id);
}
