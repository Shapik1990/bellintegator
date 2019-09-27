package ru.bellintegrator.practice.doctype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.doctype.dao.DocTypeDao;
import ru.bellintegrator.practice.doctype.dto.DocTypeDto;
import ru.bellintegrator.practice.doctype.model.DocType;
import ru.bellintegrator.practice.exception.NotEntityException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocTypeServiceImpl implements DocTypeService {

    private DocTypeDao dao;

    @Autowired
    public DocTypeServiceImpl(DocTypeDao dao) {
        this.dao = dao;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    @Transactional
    public List<DocTypeDto> getAllDocTypes() {
        List<DocType> docTypeList =  dao.loadAll();

        if (docTypeList.isEmpty()) {
            throw new NotEntityException("Документы в справочнике не найдены");
        }

        List<DocTypeDto> dtoList = new ArrayList<>();

        for (DocType docType : docTypeList){
            dtoList.add(new DocTypeDto(docType));
        }

        return dtoList;
    }
}
