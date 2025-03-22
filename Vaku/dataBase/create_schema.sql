CREATE TABLE departments
(
    depa_id   VARCHAR(2),
    depa_name VARCHAR(56),
    CONSTRAINT NN_DEPA_NAME CHECK ( depa_name IS NOT NULL ),
    CONSTRAINT PK_DEPA_ID PRIMARY KEY (depa_id)
);

CREATE TABLE citys
(
    city_id   SERIAL,
    city_code VARCHAR(5),
    city_name VARCHAR(27),
    depa_id   VARCHAR(2),
    CONSTRAINT NN_CITY_CODE CHECK ( city_CODE IS NOT NULL ),
    CONSTRAINT NN_CITY_NAME CHECK ( city_name IS NOT NULL ),
    CONSTRAINT FK_CITY_DEPA_ID FOREIGN KEY (depa_id) REFERENCES departments (depa_id),
    CONSTRAINT PK_CITY_ID PRIMARY KEY (city_id)
);

CREATE TABLE persons
(
    pers_id         SERIAL,
    pers_names      VARCHAR(50),
    pers_last_names VARCHAR(50),
    pers_document   VARCHAR(10),
    pers_sex        VARCHAR(1),
    pers_address    VARCHAR(50),
    pers_date_birth DATE,
    pers_role       VARCHAR(20),
    pers_phone      VARCHAR(10),
    pers_email      VARCHAR(80),
    pers_password   VARCHAR(100),
    city_id         INT,
    CONSTRAINT NN_PERS_NAMES CHECK ( pers_names IS NOT NULL ),
    CONSTRAINT NN_PERS_LAST_NAMES CHECK ( pers_last_names IS NOT NULL ),
    CONSTRAINT NN_PERS_DOCUMENT CHECK ( pers_document IS NOT NULL ),
    CONSTRAINT NN_PERS_SEX CHECK ( pers_sex IS NOT NULL ),
    CONSTRAINT NN_PERS_ADDRESS CHECK ( pers_address IS NOT NULL ),
    CONSTRAINT NN_PERS_DATE_BIRTH CHECK ( pers_date_birth IS NOT NULL ),
    CONSTRAINT PK_PERS_ID PRIMARY KEY (pers_id),
    CONSTRAINT UQ_PERS_DOCUMENT UNIQUE (pers_document),
    CONSTRAINT UQ_PERS_EMAIL UNIQUE (pers_email),
    CONSTRAINT UQ_PERS_PHONE UNIQUE (pers_phone),
    CONSTRAINT FK_PERS_CITY_ID FOREIGN KEY (city_id) REFERENCES citys (city_id)
);

CREATE TABLE childrens
(
    chil_id    SERIAL,
    chil_token VARCHAR(255),
    pers_id    INT,
    CONSTRAINT NN_CHIL_TOKEN CHECK ( chil_token IS NOT NULL ),
    CONSTRAINT PK_CHIL_ID PRIMARY KEY (chil_id),
    CONSTRAINT UQ_CHIL_TOKEN UNIQUE (chil_token),
    CONSTRAINT FK_CHIL_PERS_ID FOREIGN KEY (pers_id) REFERENCES persons (pers_id)
);

CREATE TABLE parents
(
    pare_id    SERIAL,
    pare_token VARCHAR(255),
    pers_id    INT,
    CONSTRAINT NN_PARE_TOKEN CHECK ( pare_token IS NOT NULL ),
    CONSTRAINT PK_PARE_ID PRIMARY KEY (pare_id),
    CONSTRAINT UQ_PARE_TOKEN UNIQUE (pare_token),
    CONSTRAINT FK_PARE_PERS_ID FOREIGN KEY (pers_id) REFERENCES persons (pers_id)
);

CREATE TABLE childrens_parents
(
    chpa_id SERIAL,
    chil_id INT,
    pare_id INT,
    CONSTRAINT NN_CHPA_CHIL_ID CHECK ( chil_id IS NOT NULL ),
    CONSTRAINT NN_CHPA_PARE_ID CHECK ( pare_id IS NOT NULL ),
    CONSTRAINT PK_CHPA_ID PRIMARY KEY (chpa_id),
    CONSTRAINT FK_CHPA_CHIL_ID FOREIGN KEY (chil_id) REFERENCES childrens (chil_id),
    CONSTRAINT FK_CHPA_PARE_ID FOREIGN KEY (pare_id) REFERENCES parents (pare_id)
);

CREATE TABLE employees
(
    empl_id             SERIAL,
    empl_date_admission DATE,
    empl_state          BOOLEAN,
    empl_token          VARCHAR(255),
    pers_id             INT,
    CONSTRAINT NN_EMPL_DATE_ADMISSION CHECK ( empl_date_admission IS NOT NULL ),
    CONSTRAINT NN_EMPL_STATE CHECK ( empl_state IS NOT NULL ),
    CONSTRAINT NN_EMPL_TOKEN CHECK ( empl_token IS NOT NULL ),
    CONSTRAINT PK_EMPL_ID PRIMARY KEY (empl_id),
    CONSTRAINT UQ_EMPL_TOKEN UNIQUE (empl_token),
    CONSTRAINT FK_EMPL_PERS_ID FOREIGN KEY (pers_id) REFERENCES persons (pers_id)
);


CREATE TABLE inventories
(
    inve_id         SERIAL,
    inve_laboratory VARCHAR(100),
    inve_lot        VARCHAR(15),
    inve_quantity   INT,
    inve_date       DATE,
    inve_token      VARCHAR(255),
    CONSTRAINT NN_INVE_LABORATORY CHECK ( inve_laboratory IS NOT NULL ),
    CONSTRAINT NN_INVE_LOT CHECK ( inve_lot IS NOT NULL ),
    CONSTRAINT NN_INVE_QUANTITY CHECK ( inve_quantity IS NOT NULL ),
    CONSTRAINT NN_INVE_TOKEN CHECK ( inve_token IS NOT NULL ),
    CONSTRAINT PK_INVE_ID PRIMARY KEY (inve_id),
    CONSTRAINT UQ_INVE_TOKEN UNIQUE (inve_token)
);

CREATE TABLE vaccines
(
    vacc_id       SERIAL,
    vacc_name     VARCHAR(36),
    vacc_age_dose VARCHAR(20),
    vacc_dosage   VARCHAR(16),
    inve_id       INT,
    CONSTRAINT NN_VACC_NAME CHECK ( vacc_name IS NOT NULL ),
    CONSTRAINT NN_VACC_AGE_DOSE CHECK ( vacc_age_dose IS NOT NULL ),
    CONSTRAINT NN_VACC_DOSAGE CHECK ( vacc_dosage IS NOT NULL ),
    CONSTRAINT PK_VACC_ID PRIMARY KEY (vacc_id),
    CONSTRAINT FK_VACC_INVE_ID FOREIGN KEY (inve_id) REFERENCES inventories (inve_id)
);

CREATE TABLE vaccines_applied
(
    vaap_id                    SERIAL,
    vaap_next_appointment_date DATE,
    vaap_applied               BOOLEAN,
    vaap_date_application      DATE,
    vaap_time_application      TIME,
    vaap_token                 VARCHAR(255),
    vacc_id                    INT,
    chil_id                    INT,
    empl_id                    INT,
    CONSTRAINT NN_VAAP_NEXT_APPOINTMENT_DATE CHECK ( vaap_next_appointment_date IS NOT NULL ),
    CONSTRAINT NN_VAAP_TOKEN CHECK ( vaap_token IS NOT NULL ),
    CONSTRAINT PK_VAAP_ID PRIMARY KEY (vaap_id),
    CONSTRAINT UQ_VAAP_TOKEN UNIQUE (vaap_token),
    CONSTRAINT FK_VAAP_VACC_ID FOREIGN KEY (vacc_id) REFERENCES vaccines (vacc_id),
    CONSTRAINT FK_VAAP_CHIL_ID FOREIGN KEY (chil_id) REFERENCES childrens (chil_id),
    CONSTRAINT FK_VAAP_EMPL_ID FOREIGN KEY (empl_id) REFERENCES employees (empl_id)
);

CREATE TABLE inventories_employees
(
    inem_id   SERIAL,
    empl_id   INT,
    inve_id   INT,
    inem_date DATE,
    CONSTRAINT NN_INEM_EMPL_ID CHECK ( empl_id IS NOT NULL ),
    CONSTRAINT NN_INEM_INVE_ID CHECK ( inve_id IS NOT NULL ),
    CONSTRAINT PK_INEM_ID PRIMARY KEY (inem_id),
    CONSTRAINT FK_INEM_INVE_ID FOREIGN KEY (inve_id) REFERENCES inventories (inve_id),
    CONSTRAINT FK_INEM_EMPL_ID FOREIGN KEY (empl_id) REFERENCES employees (empl_id)
);

--TRIGGER PARA RESTAR 1 AL INVENTARIO
CREATE OR REPLACE FUNCTION update_inventory_after_vaccine_applied()
    RETURNS TRIGGER AS
$$
BEGIN
    -- Restar 1 a la cantidad en el inventario de la vacuna aplicada
    UPDATE inventories
    SET inve_quantity = inve_quantity - 1
    WHERE inve_id = (SELECT inve_id
                     FROM vaccines
                     WHERE vacc_id = NEW.vacc_id);

    -- Verificar si la cantidad en el inventario es menor que 0 (opcional)
    IF (SELECT inve_quantity
        FROM inventories
        WHERE inve_id = (SELECT inve_id
                         FROM vaccines
                         WHERE vacc_id = NEW.vacc_id)) < 0 THEN
        RAISE EXCEPTION 'No hay suficientes vacunas en el inventario.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_after_insert_vaccine_applied
    AFTER INSERT
    ON vaccines_applied
    FOR EACH ROW
EXECUTE FUNCTION update_inventory_after_vaccine_applied();


--HISTORIAL
CREATE TABLE inventory_history
(
    inhi_id         SERIAL PRIMARY KEY,                  -- ID del registro de historial
    inhi_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Fecha y hora del cambio
    inve_laboratory VARCHAR(100),                        -- Laboratorio del inventario
    inve_id         INT,                                 -- ID del inventario
    inve_lot        VARCHAR(15),                         -- Lote del inventario
    inve_quantity   INT,                                 -- Cantidad actualizada en el inventario
    inventory_date  DATE,                                -- Fecha del inventario (renombrado)
    vacc_id         INT,                                 -- ID de la vacuna relacionada
    CONSTRAINT FK_INHI_INVE_ID FOREIGN KEY (inve_id) REFERENCES inventories (inve_id),
    CONSTRAINT FK_INHI_VACC_ID FOREIGN KEY (vacc_id) REFERENCES vaccines (vacc_id)
);


CREATE OR REPLACE FUNCTION log_inventory_changes()
    RETURNS TRIGGER AS
$$
DECLARE
    v_vacc_id INT;
BEGIN
    -- Mensaje de depuración: Verificar los valores de NEW
    RAISE NOTICE 'Nuevos valores: inve_id = %, inventory_date = %, inve_quantity = %', NEW.inve_id, NEW.inve_date, NEW.inve_quantity;

    -- Obtener el ID de la vacuna asociada al inventario
    v_vacc_id := (SELECT vacc_id FROM vaccines WHERE inve_id = NEW.inve_id LIMIT 1);

    -- Mensaje de depuración: Verificar el vacc_id obtenido
    RAISE NOTICE 'Vacuna ID obtenida: %', v_vacc_id;

    -- Insertar un registro en el historial cuando se actualiza el inventario
    INSERT INTO inventory_history (inve_laboratory, inve_id, inve_lot, inve_quantity, inventory_date, vacc_id)
    VALUES (NEW.inve_laboratory, -- Laboratorio del inventario
            NEW.inve_id, -- ID del inventario
            NEW.inve_lot, -- Lote del inventario
            NEW.inve_quantity, -- Cantidad actualizada
            NEW.inve_date, -- Fecha del inventario
            v_vacc_id -- ID de la vacuna relacionada
           );

    -- Mensaje de depuración: Confirmar que la inserción se realizó
    RAISE NOTICE 'Registro insertado en inventory_history';

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_after_update_inventories
    AFTER UPDATE
    ON inventories
    FOR EACH ROW
EXECUTE FUNCTION log_inventory_changes();
