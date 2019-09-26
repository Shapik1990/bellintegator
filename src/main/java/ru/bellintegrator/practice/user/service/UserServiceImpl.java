package ru.bellintegrator.practice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.country.dao.CountryDao;
import ru.bellintegrator.practice.doctype.dao.DocTypeDao;
import ru.bellintegrator.practice.exception.DocumentDataValidationException;
import ru.bellintegrator.practice.exception.NotEntityException;
import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.doctype.model.DocType;
import ru.bellintegrator.practice.user.model.Document;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.dto.UserDto;
import ru.bellintegrator.practice.view.DataResponseView;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private OfficeDao officeDao;
    private CountryDao countryDao;
    private DocTypeDao docTypeDao;

    @Autowired
    public UserServiceImpl(UserDao dao, OfficeDao officeDao, CountryDao countryDao, DocTypeDao docTypeDao) {
        this.userDao = dao;
        this.officeDao = officeDao;
        this.countryDao = countryDao;
        this.docTypeDao = docTypeDao;
    }

    @Override
    public DataResponseView getUsersListByFilter(UserDto userDto) {
        List<User> userList = userDao.listByFilter(userDto.getOfficeId(), userDto.getFirstName(), userDto.getSecondName(),
                userDto.getMiddleName(), userDto.getPosition(), userDto.getDocCode(), userDto.getCitizenshipCode());

        if (userList.isEmpty()) {
            throw new NotEntityException("Пользователи с указанными параметрами не найдены");
        }

        List<UserDto> dtoList = new ArrayList<>();

        for (User user : userList) {
            dtoList.add(new UserDto(user));
        }

        return new DataResponseView(dtoList);
    }

    @Override
    public DataResponseView getUserById(int id) {
        User user = userDao.loadById(id);

        if (user != null){
            return new DataResponseView(new UserDto(user));
        }

        throw new NotEntityException("Не найден пользователь с id " + id);
    }

    @Transactional
    @Override
    public void update(UserDto userDto) {
        User user = userDao.loadById(userDto.getId());
        if (user == null){
            throw new NotEntityException("Не найден пользователь с id" + userDto.getId());
        }

        if ((user.getDocumentId() == null && userDto.getDocCode() == null && userDto.getDocName() == null) && (userDto.getDocDate() != null ||
                userDto.getDocNumber() != null)) {
            throw new DocumentDataValidationException("У пользователя отсутствует документ, для сохранения данных документа необходимо указать docName или docCode");
        }

        transform(user, userDto);
    }

    @Transactional
    @Override
    public void save(UserDto userDto) {
        Office office = officeDao.loadById(userDto.getOfficeId());

        if (office == null) {
            throw new NotEntityException("Не найден офис с officeId " + userDto.getOfficeId());
        }

        if ((userDto.getDocDate() != null || userDto.getDocNumber() != null) && (userDto.getDocName() == null && userDto.getDocCode() == null)) {
            throw new DocumentDataValidationException("Для сохранения данных документа необходимо указать docCode или docName");
        }

        User user = new User();
        transform(user, userDto);
        user.setOfficeId(office);
        office.getUserSet().add(user);

    }

    private void transform(User user, UserDto userDto) {
        user.setFirstName(userDto.getFirstName());
        if (userDto.getSecondName() != null){
            user.setSecondName(userDto.getSecondName());
        }
        if (userDto.getMiddleName() != null){
            user.setMiddleName(userDto.getMiddleName());
        }
        user.setPosition(userDto.getPosition());
        if (userDto.getPhone() != null){
            user.setPhone(userDto.getPhone());
        }
        if (userDto.isIdentified() != null){
            user.setIdentified(userDto.isIdentified());
        }

        if (userDto.getCitizenshipCode() != null){
            Country country = countryDao.loadById(userDto.getCitizenshipCode());
            if (country != null){
                user.setCitizenshipCode(country);
            }
            else {
                throw new NotEntityException("Не найдена страна с citizenshipCode " + userDto.getCitizenshipCode());
            }
        }

        if (userDto.getDocCode() != null){
            DocType docType = docTypeDao.loadById(userDto.getDocCode());

            if (docType == null){
                throw new NotEntityException("Не найден тип документа с docCode " + userDto.getDocCode());
            }

            if (userDto.getDocName() != null && (!docType.getName().equals(userDto.getDocName()))) {
                throw new DocumentDataValidationException("docCode документа не совпадает с docName");
            }

            addDocumentToUser(user, userDto, docType);

        }
        else if (userDto.getDocName() != null){
            DocType docType = docTypeDao.loadByName(userDto.getDocName());
            if (docType == null){
                throw new NotEntityException("Не найден тип документа с docName " + userDto.getDocName());
            }

            addDocumentToUser(user, userDto, docType);
        }
        else if (user.getDocumentId() != null) {
            if (userDto.getDocNumber() != null) {
                user.getDocumentId().setDocNumber(userDto.getDocNumber());
            }
            if (userDto.getDocDate() != null) {
                user.getDocumentId().setDocDate(userDto.getDocDate());
            }
        }

    }

    private void addDocumentToUser (User user, UserDto userDto, DocType docType){
        Document document = null;
        if (user.getDocumentId() == null){
            document = new Document();
            document.setUser(user);
            user.setDocumentId(document);
        }
        else {
            document = user.getDocumentId();
        }

        document.setDocCode(docType);
        document.setDocDate(userDto.getDocDate());
        document.setDocNumber(userDto.getDocNumber());

    }
}
