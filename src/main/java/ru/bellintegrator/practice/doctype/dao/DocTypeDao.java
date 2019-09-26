package ru.bellintegrator.practice.doctype.dao;

import ru.bellintegrator.practice.doctype.model.DocType;

public interface DocTypeDao {
    DocType loadById(int id);

    DocType loadByName(String docName);
}
