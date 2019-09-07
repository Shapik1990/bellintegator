CREATE TABLE IF NOT EXISTS Organization (
    id          BIGINT                  COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version     INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    name        VARCHAR(30) NOT NULL    COMMENT 'Название организации',
    full_name   VARCHAR(50) NOT NULL    COMMENT 'Полное название оранизации',
    inn         BIGINT(10)  NOT NULL    COMMENT 'ИНН',
    kpp         INTEGER(9)  NOT NULL    COMMENT 'КПП',
    address     VARCHAR(50) NOT NULL    COMMENT 'Адрес организации',
    phone     VARCHAR(12)             COMMENT 'Телефон организации',
    is_active   BOOLEAN     NOT NULL    COMMENT 'Активность организации'
);

CREATE TABLE IF NOT EXISTS Office (
    id                  BIGINT                  COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version             INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    organization_id     BIGINT      NOT NULL    COMMENT 'Ид организации к которой принадлежит офис' REFERENCES Organization(id),
    name                VARCHAR(30)             COMMENT 'Название офиса',
    address             VARCHAR(50)             COMMENT 'Адрес офиса',
    phone               VARCHAR(12)             COMMENT 'Телефон офиса',
    is_active           BOOLEAN     NOT NULL    COMMENT 'Активность офиса'
);

CREATE TABLE IF NOT EXISTS User (
    id                  BIGINT                  COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version             INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    first_name          VARCHAR(20) NOT NULL    COMMENT 'Имя',
    second_name         VARCHAR(35)             COMMENT 'Фамилия',
    middle_name         VARCHAR(25)             COMMENT 'Отчество',
    position            VARCHAR(30) NOT NULL    COMMENT 'Должность',
    phone               VARCHAR(12)             COMMENT 'Телефон',
    doc_code            INTEGER                 COMMENT 'Код документа',
    doc_name            VARCHAR(50)             COMMENT 'Название документа',
    doc_number          BIGINT                  COMMENT 'Номер документа',
    doc_date            DATE                    COMMENT 'Дата выдачи документа',
    citizenship_code    INTEGER                 COMMENT 'Код страны',
    is_identified       BOOLEAN     NOT NULL    COMMENT 'Идентификация пользователя',
    office_id           BIGINT      NOT NULL    COMMENT 'ИД офиса клиента' REFERENCES Office(id)
);

CREATE TABLE IF NOT EXISTS Doc (
    code                INTEGER                  COMMENT 'Код документа' PRIMARY KEY,
    name                VARCHAR(50) NOT NULL     COMMENT 'Название документа',
);

CREATE TABLE IF NOT EXISTS Country (
    code                INTEGER                  COMMENT 'Код страны' PRIMARY KEY,
    name                VARCHAR(50) NOT NULL     COMMENT 'Название страны',
);


CREATE INDEX IX_Office_organization_id ON Office (organization_id);

CREATE INDEX IX_User_office_id  ON User (office_id );

