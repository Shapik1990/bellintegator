package ru.bellintegrator.practice.office.service;

import ru.bellintegrator.practice.office.dto.OfficeDto;

import java.util.List;


public interface OfficeService {

    /**
     * Обновить данные офиса
     *
     * @param officeDto данные для обновления обернутые в {@link OfficeDto}
     */
    void update(OfficeDto officeDto);

    /**
     * Сохранить новый офис
     *
     * @param officeDto данные для сохранения обернутые в {@link OfficeDto}
     */
    void save(OfficeDto officeDto);

    /**
     * Получить список офисов по фильтрам
     *
     * @param officeDto данные для фильтра обернутые в {@link OfficeDto}
     * @return список {@link OfficeDto}
     */
    List<OfficeDto> getOfficesListByFilter(OfficeDto officeDto);

    /**
     * Получить офис по идентификатору
     *
     * @param id идентификатор офиса
     * @return данные офиса обернутые {@link OfficeDto}
     */
    OfficeDto getOfficeById(int id);
}
