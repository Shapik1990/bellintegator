package ru.bellintegrator.practice.service.office;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dto.OfficeDto;
import ru.bellintegrator.practice.view.DataResponseView;
import ru.bellintegrator.practice.view.SuccessResponceView;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Override
    public SuccessResponceView update(OfficeDto officeDto) {
        return null;
    }

    @Override
    public SuccessResponceView save(OfficeDto officeDto) {
        return null;
    }

    @Override
    public DataResponseView<OfficeDto> getOfficesListByFilter(OfficeDto officeDto) {
        return null;
    }

    @Override
    public DataResponseView<OfficeDto> getOfficeById(int id) {
        return null;
    }
}
