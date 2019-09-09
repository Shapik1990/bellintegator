INSERT INTO Doc (code, name) VALUES (03, 'Свидетельство о рождении ');

INSERT INTO Doc (code, name) VALUES (07, 'Военный билет');

INSERT INTO Doc (code, name) VALUES (08, 'Временное удостоверение, выданное взамен военного билета');

INSERT INTO Doc (code, name) VALUES (10, 'Паспорт иностранного гражданина');

INSERT INTO Doc (code, name) VALUES (11, 'Свидетельство о рассмотрении ходатайства о признании лица
беженцем на территории Российской Федерации по существу');

INSERT INTO Doc (code, name) VALUES (12, 'Вид на жительство в Российской Федерации');

INSERT INTO Doc (code, name) VALUES (13, 'Удостоверение беженца');

INSERT INTO Doc (code, name) VALUES (15, 'Разрешение на временное проживание в Российской Федерации');

INSERT INTO Doc (code, name) VALUES (18, 'Свидетельство о предоставлении временного убежища на территории
Российской Федерации');

INSERT INTO Doc (code, name) VALUES (21, 'Паспорт гражданина РФ');

INSERT INTO Doc (code, name) VALUES (23, 'Свидетельство о рождении, выданное уполномоченным органом
иностранного государства');

INSERT INTO Doc (code, name) VALUES (24, 'Удостоверение личности военнослужащего Российской Федерации');

INSERT INTO Doc (code, name) VALUES (91, 'Иные документы');

INSERT INTO Country (code, name) VALUES (643, 'Российская Федерация');

INSERT INTO Country (code, name) VALUES (050, 'Народная Республика Бангладеш');

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, is_active)
            VALUES (1, 0, 'Газпром', 'ООО Газпром', 1234567890, 123456789, 'ул.Цюрупы, 16', true);

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active)
            VALUES (2, 0, 'Роснефть', 'ООО Роснефть', 1334567890, 123456789, 'ул.Лунина, 7', '+74951234567', true);

INSERT INTO Office (id, version, organization_id, name, is_active) VALUES (1, 0, 1, 'Офис газпрома', true);

INSERT INTO Office (id, version, organization_id, is_active) VALUES (2, 0, 2, true);

INSERT INTO Office (id, version, organization_id, name, address, phone, is_active)
            VALUES (3, 0, 1, 'Офис газпрома на Цюрупы', 'ул.Цюрупы, 16', '+74957654123', true);

INSERT INTO User (id, version, first_name, second_name, middle_name, position, doc_code, citizenship_code,  is_identified, office_id)
            VALUES (1, 0, 'Василий', 'Иванович', 'Чапаев',  'Директор офиса', 10, 50, true, 3);

INSERT INTO User (id, version, first_name, position, doc_code,  is_identified, office_id)
            VALUES (2, 0, 'Светлана', 'Секретарь', 03, false, 1);

INSERT INTO User (id, version, first_name, phone, citizenship_code, position, is_identified, office_id)
            VALUES (3, 0, 'Игорь', '+79099055555', 643, 'Инженер', true, 1);

INSERT INTO User (id, version, first_name, doc_code, doc_number, doc_date, position, is_identified, office_id)
            VALUES (4, 0, 'Александр', 21, 7456548796, '2006-05-12', 'Клининг менеджер', true, 2);



