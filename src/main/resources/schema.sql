CREATE TABLE IF NOT EXISTS Organization (
    id          INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version     INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    name        VARCHAR(30) NOT NULL    COMMENT 'Название организации',
    full_name   VARCHAR(50) NOT NULL    COMMENT 'Полное название оранизации',
    inn         VARCHAR(11) NOT NULL    COMMENT 'ИНН',
    kpp         VARCHAR(9)  NOT NULL    COMMENT 'КПП',
    address     VARCHAR(50) NOT NULL    COMMENT 'Адрес организации',
    phone       VARCHAR(12)             COMMENT 'Телефон организации',
    is_active   BOOLEAN     NOT NULL    COMMENT 'Активность организации'
);

CREATE TABLE IF NOT EXISTS Office (
    id                  INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version             INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    organization_id     INTEGER      NOT NULL    COMMENT 'Ид организации к которой принадлежит офис' REFERENCES Organization(id),
    name                VARCHAR(30)             COMMENT 'Название офиса',
    address             VARCHAR(50)             COMMENT 'Адрес офиса',
    phone               VARCHAR(12)             COMMENT 'Телефон офиса',
    is_active           BOOLEAN     NOT NULL    COMMENT 'Активность офиса'
);

CREATE TABLE IF NOT EXISTS Doc_type (
    code                INTEGER                  COMMENT 'Код документа' PRIMARY KEY,
    version             INTEGER      NOT NULL    COMMENT 'Служебное поле hibernate',
    name                VARCHAR(113) NOT NULL     COMMENT 'Название документа',
);

CREATE TABLE IF NOT EXISTS Country (
    code                INTEGER                  COMMENT 'Код страны' PRIMARY KEY,
    version             INTEGER      NOT NULL    COMMENT 'Служебное поле hibernate',
    name                VARCHAR(130) NOT NULL     COMMENT 'Название страны',
);

CREATE TABLE IF NOT EXISTS User (
    id                  INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version             INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    first_name          VARCHAR(20) NOT NULL    COMMENT 'Имя',
    second_name         VARCHAR(35)             COMMENT 'Фамилия',
    middle_name         VARCHAR(25)             COMMENT 'Отчество',
    position            VARCHAR(30) NOT NULL    COMMENT 'Должность',
    phone               VARCHAR(12)             COMMENT 'Телефон',
    citizenship_code    INTEGER                 COMMENT 'Код страны'   REFERENCES Country(code),
    is_identified       BOOLEAN     NOT NULL    COMMENT 'Идентификация пользователя',
    office_id           INTEGER      NOT NULL    COMMENT 'ID офиса клиента' REFERENCES Office(id)
);

CREATE TABLE IF NOT EXISTS User_document(
    user_id             INTEGER                 COMMENT 'ID документа совпадающий с ID юзера' PRIMARY KEY REFERENCES User(id),
    version             INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    doc_code            INTEGER                 COMMENT 'Код документа' REFERENCES Doc_type(code),
    doc_number          VARCHAR(20)             COMMENT 'Номер документа',
    doc_date            DATE                    COMMENT 'Дата выдачи документа',
);





CREATE INDEX IX_Office_organization_id ON Office (organization_id);

CREATE INDEX IX_User_office_id  ON User (office_id);

CREATE INDEX IX_User_citizenship_code  ON User (citizenship_code);

CREATE INDEX UX_User_document_user_id  ON User_Document (user_id);

