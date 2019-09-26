INSERT INTO Doc_type (code, version, name) VALUES (03, 0, 'Свидетельство о рождении');

INSERT INTO Doc_type (code, version, name) VALUES (07, 0, 'Военный билет');

INSERT INTO Doc_type (code, version, name) VALUES (08, 0, 'Временное удостоверение, выданное взамен военного билета');

INSERT INTO Doc_type (code, version, name) VALUES (10, 0, 'Паспорт иностранного гражданина');

INSERT INTO Doc_type (code, version, name) VALUES (11, 0, 'Свидетельство о рассмотрении ходатайства о признании лица
беженцем на территории Российской Федерации по существу');

INSERT INTO Doc_type (code, version, name) VALUES (12, 0, 'Вид на жительство в Российской Федерации');

INSERT INTO Doc_type (code, version, name) VALUES (13, 0, 'Удостоверение беженца');

INSERT INTO Doc_type (code, version, name) VALUES (15, 0, 'Разрешение на временное проживание в Российской Федерации');

INSERT INTO Doc_type (code, version, name) VALUES (18, 0, 'Свидетельство о предоставлении временного убежища на территории
Российской Федерации');

INSERT INTO Doc_type (code, version, name) VALUES (21, 0, 'Паспорт гражданина РФ');

INSERT INTO Doc_type (code, version, name) VALUES (23, 0, 'Свидетельство о рождении, выданное уполномоченным органом
иностранного государства');

INSERT INTO Doc_type (code, version, name) VALUES (24, 0, 'Удостоверение личности военнослужащего Российской Федерации');

INSERT INTO Doc_type (code, version, name) VALUES (91, 0, 'Иные документы');

INSERT INTO Country (code, version, name) VALUES (643, 0, 'Российская Федерация');

INSERT INTO Country (code, version, name) VALUES (050, 0, 'Народная Республика Бангладеш');

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, is_active)
            VALUES (1, 0, 'Газпром', 'ООО Газпром', '1234567890', '123456789', 'ул.Цюрупы, 16', true);

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active)
            VALUES (2, 0, 'Роснефть', 'ООО Роснефть', '1334567890', '123456789', 'ул.Лунина, 7', '+74951234567', true);

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, is_active)
            VALUES (3, 0, 'РЖД', 'ООО Российские Железные дороги', '1234599890', '123456789', 'ул.Цюрупы, 22', false);

INSERT INTO Office (id, version, organization_id, name, is_active) VALUES (1, 0, 1, 'Офис газпрома', true);

INSERT INTO Office (id, version, organization_id, is_active) VALUES (2, 0, 2, false);

INSERT INTO Office (id, version, organization_id, name, address, phone, is_active)
            VALUES (3, 0, 1, 'Офис газпрома на Цюрупы', 'ул.Цюрупы, 16', '+74957654123', true);

INSERT INTO User (id, version, first_name, second_name, middle_name, position, citizenship_code,  is_identified, office_id)
            VALUES (1, 0, 'Василий', 'Иванович', 'Чапаев',  'Директор офиса', 50, true, 3);

INSERT INTO User (id, version, first_name, position, is_identified, office_id)
            VALUES (2, 0, 'Светлана', 'Секретарь', false, 1);

INSERT INTO User (id, version, first_name, phone, citizenship_code, position, is_identified, office_id)
            VALUES (3, 0, 'Игорь', '+79099055555', 643, 'Инженер', true, 1);

INSERT INTO User (id, version, first_name, position, is_identified, office_id)
            VALUES (4, 0, 'Александр', 'Клининг менеджер', true, 2);

INSERT INTO Document (user_id, version, doc_code, doc_number, doc_date) VALUES (3, 0, 03, '1234567890', '2007-10-15');

INSERT INTO Document (user_id, version, doc_code) VALUES (1, 0, 10);

INSERT INTO Document (user_id, version, doc_code) VALUES (4, 0, 13);




