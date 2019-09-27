package ru.bellintegrator.practice.doctype.dao;

import ru.bellintegrator.practice.doctype.model.DocType;

import java.util.List;

public interface DocTypeDao {

    /**
     * Получить объект DocType по идентификатору
     *
     * @param id идентификатор объекта DocType
     * @return {@link DocType}
     */
    DocType loadById(int id);

    /**
     * Получить объект DocType по названию документа
     *
     * @param docName название документа
     * @return {@link DocType}
     */
    DocType loadByName(String docName);

    /**
     * Получить все объекты DocType
     *
     * @return список {@link DocType}
     */
    List<DocType> loadAll();

}
