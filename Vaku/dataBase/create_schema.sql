CREATE TABLE departments
(
    depa_id   VARCHAR(2),
    depa_name VARCHAR(56),
    CONSTRAINT NN_DEPA_NAME CHECK ( depa_name IS NOT NULL ),
    CONSTRAINT PK_DEPA_ID PRIMARY KEY (depa_id)
)

CREATE TABLE citys
(
    city_id   VARCHAR(5),
    city_name VARCHAR(27),
    depa_id   VARCHAR(2),
    CONSTRAINT NN_CITY_NAME CHECK ( city_name IS NOT NULL ),
    CONSTRAINT FK_CITY_DEPA_ID FOREIGN KEY (depa_id) REFERENCES departments (depa_id),
    CONSTRAINT PK_CITY_ID PRIMARY KEY (city_id, depa_id)
)

CREATE TABLE documents_type
(
    doty_id   SERIAL,
    doty_name VARCHAR(20),
    CONSTRAINT NN_doty_NAME CHECK ( doty_name IS NOT NULL ),
    CONSTRAINT PK_doty_ID PRIMARY KEY (doty_id)
)

CREATE TABLE persons
(
    pers_id         SERIAL,
    pers_names      VARCHAR(50),
    pers_last_names VARCHAR(50),
    pers_document   VARCHAR(10),
    pers_sex        VARCHAR(1),
    pers_address    VARCHAR(50),
    pers_date_birth DATE,
    pers_token      VARCHAR(255),
    doty_id         INT,
    city_id         VARCHAR(5),
    CONSTRAINT NN_PERS_NAMES CHECK ( pers_names IS NOT NULL ),
    CONSTRAINT NN_PERS_LAST_NAMES CHECK ( pers_last_names IS NOT NULL ),
    CONSTRAINT NN_PERS_DOCUMENT CHECK ( pers_document IS NOT NULL ),
    CONSTRAINT NN_PERS_SEX CHECK ( pers_sex IS NOT NULL ),
    CONSTRAINT NN_PERS_ADDRESS CHECK ( pers_address IS NOT NULL ),
    CONSTRAINT NN_PERS_DATE_BIRTH CHECK ( pers_date_birth IS NOT NULL ),
    CONSTRAINT NN_PERS_TOKEN CHECK ( pers_token IS NOT NULL ),
    CONSTRAINT PK_PERS_ID PRIMARY KEY (pers_id),
    CONSTRAINT UQ_PERS_DOCUMENT UNIQUE (pers_document),
    CONSTRAINT UQ_PERS_TOKEN UNIQUE (pers_token),
    CONSTRAINT FK_PERS_DOTY_ID FOREIGN KEY (doty_id) REFERENCES documents_type (doty_id),
    CONSTRAINT FK_PERS_CITY_ID FOREIGN KEY (city_id) REFERENCES citys (city_id)
)

CREATE TABLE childrens
(
    chil_id     SERIAL,
    child_token VARCHAR(255),
    pers_id     INT,
    CONSTRAINT NN_CHIL_TOKEN CHECK ( child_token IS NOT NULL ),
    CONSTRAINT PK_CHIL_ID PRIMARY KEY (chil_id),
    CONSTRAINT UQ_CHIL_TOKEN UNIQUE (child_token),
    CONSTRAINT FK_CHIL_PERS_ID FOREIGN KEY (pers_id) REFERENCES persons (pers_id)
)

CREATE TABLE parents
(
    pare_id    SERIAL,
    pare_email VARCHAR(80),
    pare_phone VARCHAR(10),
    pare_token VARCHAR(255),
    pers_id    INT,
    CONSTRAINT NN_PARE_EMAIL CHECK ( pare_email IS NOT NULL ),
    CONSTRAINT NN_PARE_PHONE CHECK ( pare_phone IS NOT NULL ),
    CONSTRAINT NN_PARE_TOKEN CHECK ( pare_token IS NOT NULL ),
    CONSTRAINT PK_PARE_ID PRIMARY KEY (pare_id),
    CONSTRAINT UQ_PARE_EMAIL UNIQUE (pare_email),
    CONSTRAINT UQ_PARE_PHONE UNIQUE (pare_phone),
    CONSTRAINT UQ_PARE_TOKEN UNIQUE (pare_token),
    CONSTRAINT FK_PARE_PERS_ID FOREIGN KEY (pers_id) REFERENCES persons (pers_id)
)

CREATE TABLE roles
(
    role_id   SERIAL,
    role_name VARCHAR(18),
    CONSTRAINT NN_ROLE_NAME CHECK ( role_name IS NOT NULL ),
    CONSTRAINT PK_ROLE_ID PRIMARY KEY (role_id)
)

CREATE TABLE employees
(
    empl_id             SERIAL,
    empl_email          VARCHAR(80),
    empl_date_admission DATE,
    empl_state          BOOLEAN,
    empl_token          VARCHAR(255),
    pers_id             INT,
    role_id             INT,
    CONSTRAINT NN_EMPL_EMAIL CHECK ( empl_email IS NOT NULL ),
    CONSTRAINT NN_EMPL_DATE_ADMISSION CHECK ( empl_date_admission IS NOT NULL ),
    CONSTRAINT NN_EMPL_STATE CHECK ( empl_state IS NOT NULL ),
    CONSTRAINT NN_EMPL_TOKEN CHECK ( empl_token IS NOT NULL ),
    CONSTRAINT PK_EMPL_ID PRIMARY KEY (empl_id),
    CONSTRAINT UQ_EMPL_EMAIL UNIQUE (empl_email),
    CONSTRAINT UQ_EMPL_TOKEN UNIQUE (empl_token),
    CONSTRAINT FK_EMPL_PERS_ID FOREIGN KEY (pers_id) REFERENCES persons (pers_id),
    CONSTRAINT FK_EMPL_ROLE_ID FOREIGN KEY (role_id) REFERENCES roles (role_id)
)

CREATE TABLE vaccinnes
(
    vacc_id       SERIAL,
    vacc_name     VARCHAR(36),
    vacc_age_dose VARCHAR(20),
    vacc_dosage   VARCHAR(16),
    CONSTRAINT NN_VACC_NAME CHECK ( vacc_name IS NOT NULL ),
    CONSTRAINT NN_VACC_AGE_DOSE CHECK ( vacc_age_dose IS NOT NULL ),
    CONSTRAINT NN_VACC_DOSAGE CHECK ( vacc_dosage IS NOT NULL ),
    CONSTRAINT PK_VACC_ID PRIMARY KEY (vacc_id)
)

CREATE TABLE vaccines_applied
(
    vaap_id                    SERIAL,
    vaap_next_appointment_date DATE,
    vaap_token                 VARCHAR(255),
    vacc_id                    INT,
    chil_id                   INT,
    empl_id                    INT,
    CONSTRAINT NN_VAAP_NEXT_APPOINTMENT_DATE CHECK ( vaap_next_appointment_date IS NOT NULL ),
    CONSTRAINT NN_VAAP_TOKEN CHECK ( vaap_token IS NOT NULL ),
    CONSTRAINT PK_VAAP_ID PRIMARY KEY (vaap_id),
    CONSTRAINT UQ_VAAP_TOKEN UNIQUE (vaap_token),
    CONSTRAINT FK_VAAP_VACC_ID FOREIGN KEY (vacc_id) REFERENCES vaccinnes (vacc_id),
    CONSTRAINT FK_VAAP_CHIL_ID FOREIGN KEY (chil_id) REFERENCES childrens (chil_id),
    CONSTRAINT FK_VAAP_EMPL_ID FOREIGN KEY (empl_id) REFERENCES employees (empl_id)
)

CREATE TABLE inventories
(
    inve_id         SERIAL,
    inve_laboratory VARCHAR(100),
    inve_lot        VARCHAR(15),
    inve_quantity   VARCHAR(5),
    inve_token      VARCHAR(255),
    vacc_id         INT,
    CONSTRAINT NN_INVE_LABORATORY CHECK ( inve_laboratory IS NOT NULL ),
    CONSTRAINT NN_INVE_LOT CHECK ( inve_lot IS NOT NULL ),
    CONSTRAINT NN_INVE_QUANTITY CHECK ( inve_quantity IS NOT NULL ),
    CONSTRAINT NN_INVE_TOKEN CHECK ( inve_token IS NOT NULL ),
    CONSTRAINT PK_INVE_ID PRIMARY KEY (inve_id),
    CONSTRAINT UQ_INVE_TOKEN UNIQUE (inve_token),
    CONSTRAINT FK_INVE_VACC_ID FOREIGN KEY (vacc_id) REFERENCES vaccinnes (vacc_id)
)

CREATE TABLE inventories_vaccinnes
(
    empl_id INT,
    inve_id INT,
    CONSTRAINT NN_INVA_EMPL_ID CHECK ( empl_id IS NOT NULL ),
    CONSTRAINT NN_INVA_INVE_ID CHECK ( inve_id IS NOT NULL ),
    CONSTRAINT FK_INVA_INVE_ID FOREIGN KEY (inve_id) REFERENCES inventories (inve_id),
    CONSTRAINT FK_INVA_EMPL_ID FOREIGN KEY (empl_id) REFERENCES employees (empl_id)
)

INSERT INTO role (role_name)
VALUES('Jefe de enfermería'),('Enfermero/a')