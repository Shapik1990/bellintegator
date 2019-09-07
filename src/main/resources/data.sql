INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, is_active)
            VALUES (1, 0, 'Газпром', 'ООО Газпром', 1234567890, 123456789, 'ул.Цюрупы, 16', true);

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active)
            VALUES (2, 0, 'Роснефть', 'ООО Роснефть', 1334567890, 123456789, 'ул.Лунина, 7', '+74951234567', true);

INSERT INTO Office (id, version, organization_id, name, is_active) VALUES (1, 0, 1, 'Офис газпрома', true);

INSERT INTO Office (id, version, organization_id, is_active) VALUES (2, 0, 2, true);

INSERT INTO Office (id, version, organization_id, name, address, phone, is_active)
            VALUES (3, 0, 1, 'Офис газпрома на Цюрупы', 'ул.Цюрупы, 16', '+74957654123', true);

INSERT INTO User (id, version, first_name, second_name, middle_name, position, is_identified, office_id)
            VALUES (1, 0, 'Василий', 'Иванович', 'Чапаев', 'Директор офиса', true, 3);

INSERT INTO User (id, version, first_name, position, is_identified, office_id)
            VALUES (2, 0, 'Светлана', 'Секретарь', false, 1);

INSERT INTO User (id, version, first_name, phone, citizenship_code, position, is_identified, office_id)
            VALUES (3, 0, 'Игорь', '+79099055555', 643, 'Инженер', true, 1);

INSERT INTO User (id, version, first_name, doc_code, doc_name, doc_number, doc_date, position, is_identified, office_id)
            VALUES (4, 0, 'Александр', 21, 'Паспорт гражданина РФ', 7456548796, '2006-05-12', 'Клининг менеджер', true, 2);

INSERT INTO Doc (code, name) VALUES (21, 'Паспорт гражданина РФ');

INSERT INTO Country (code, name) VALUES (643, 'Российская Федерация');

