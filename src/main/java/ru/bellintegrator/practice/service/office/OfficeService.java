package ru.bellintegrator.practice.service.office;

import ru.bellintegrator.practice.dto.OfficeDto;
import ru.bellintegrator.practice.view.DataResponseView;
import ru.bellintegrator.practice.view.SuccessResponceView;

public interface OfficeService {

    SuccessResponceView update(OfficeDto officeDto);

    SuccessResponceView save(OfficeDto officeDto);

    DataResponseView<OfficeDto> getOfficesListByFilter(OfficeDto officeDto);

    DataResponseView<OfficeDto> getOfficeById(int id);
}
