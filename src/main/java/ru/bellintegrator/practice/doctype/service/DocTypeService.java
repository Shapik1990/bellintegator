package ru.bellintegrator.practice.doctype.service;

import ru.bellintegrator.practice.doctype.dto.DocTypeDto;

import java.util.List;

public interface DocTypeService {

    /**
     * Получить список типов документов из справочной таблицы
     *
     * @return список типов документов обернутых в {@link DocTypeDto}
     */
    List<DocTypeDto> getAllDocTypes();

}
