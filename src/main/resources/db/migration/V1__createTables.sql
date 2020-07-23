DROP TABLE IF EXISTS workshop_registrations;
DROP TABLE IF EXISTS workshop_speakers;
DROP TABLE IF EXISTS session_speakers;
DROP TABLE IF EXISTS session_tags;
DROP TABLE IF EXISTS attendee_tickets;
DROP TABLE IF EXISTS session_schedule;
DROP TABLE IF EXISTS time_slots;
DROP TABLE IF EXISTS ticket_prices;
DROP TABLE IF EXISTS attendees;
DROP TABLE IF EXISTS ticket_types;
DROP TABLE IF EXISTS pricing_categories;
DROP TABLE IF EXISTS discount_codes;
DROP TABLE IF EXISTS sessions;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS speakers;
DROP TABLE IF EXISTS workshops;

CREATE TABLE attendees
(
    attendee_id  SERIAL,
    first_name   VARCHAR(30) NOT NULL,
    last_name    VARCHAR(30) NOT NULL,
    title        VARCHAR(40) NULL,
    company      VARCHAR(50) NULL,
    email        VARCHAR(80) NOT NULL,
    phone_number VARCHAR(20) NULL,
    CONSTRAINT attendee_pkey PRIMARY KEY (attendee_id)
);

CREATE TABLE ticket_types
(
	  id  SERIAL,
    ticket_type_code  VARCHAR(1) NOT NULL,
    ticket_type_name  VARCHAR(30)  NOT NULL,
    description       VARCHAR(100) NOT NULL,
    includes_workshop boolean      NOT NULL, 
    CONSTRAINT ticket_types_pkey PRIMARY KEY (id)
);

CREATE TABLE pricing_categories
(
    id  SERIAL,
    pricing_category_code VARCHAR(1) NOT NULL,
    pricing_category_name VARCHAR(20) NOT NULL,
    pricing_start_date    date        NOT NULL,
    pricing_end_date      date        NOT NULL,
    CONSTRAINT pricing_categories_pkey PRIMARY KEY (id)
);

CREATE TABLE ticket_prices
(
    ticket_price_id  SERIAL,
    ticket_type_id      INTEGER    NOT NULL REFERENCES ticket_types (id),
    pricing_id INTEGER    NOT NULL REFERENCES pricing_categories (id),
    base_price            NUMERIC(8, 2) NOT NULL,
    CONSTRAINT ticket_prices_pkey PRIMARY KEY (ticket_price_id)
);

CREATE TABLE discount_codes
(
    discount_code_id SERIAL,
    discount_code    VARCHAR(20)   NOT NULL,
    discount_name    VARCHAR(30)   NOT NULL,
    discount_type    VARCHAR(1)    NOT NULL,
    discount_amount  NUMERIC(8, 2) NOT NULL,
    CONSTRAINT discount_codes_pkey PRIMARY KEY (discount_code_id)
);

CREATE TABLE attendee_tickets (
	attendee_ticket_id SERIAL,
	attendee_id INTEGER NOT NULL,
	ticket_price_id INTEGER NOT NULL,
	discount_code_id INTEGER NULL,
	net_price NUMERIC(8,2) NOT NULL,
	CONSTRAINT attendee_tickets_pkey PRIMARY KEY (attendee_ticket_id),
	CONSTRAINT attendee_tickets_attendee_id_fkey FOREIGN KEY (attendee_id) REFERENCES attendees(attendee_id),
	CONSTRAINT attendee_tickets_discount_code_id_fkey FOREIGN KEY (discount_code_id) REFERENCES discount_codes(discount_code_id),
	CONSTRAINT attendee_tickets_ticket_id_fkey FOREIGN KEY (ticket_price_id) REFERENCES ticket_prices(ticket_price_id)
);

CREATE TABLE time_slots
(
    time_slot_id SERIAL,
    time_slot_date DATE  NOT NULL,
    start_time time without time zone NOT NULL,
    end_time  time without time zone NOT NULL,
    is_keynote_time_slot boolean default false  NOT NULL,
	CONSTRAINT  time_slots_pkey PRIMARY KEY (time_slot_id)
);

CREATE TABLE sessions
(
    session_id          SERIAL,
    session_name        VARCHAR(80)   NOT NULL,
    session_description VARCHAR(1024) NOT NULL,
    session_length      INTEGER       NOT NULL,
	CONSTRAINT  sessions_pkey PRIMARY KEY (session_id)
);

CREATE TABLE session_schedule (
	schedule_id SERIAL,
	time_slot_id INTEGER NOT NULL,
	session_id INTEGER NOT NULL,
	room VARCHAR(30) NOT NULL,
 	CONSTRAINT session_schedule_pkey PRIMARY KEY (schedule_id),
 	CONSTRAINT session_schedule_session_id_fkey FOREIGN KEY (session_id) REFERENCES sessions(session_id),
 	CONSTRAINT session_schedule_time_slot_id_fkey FOREIGN KEY (time_slot_id) REFERENCES time_slots(time_slot_id)
);

CREATE TABLE tags
(
    tag_id      SERIAL,
    description VARCHAR(30) NOT NULL,
	CONSTRAINT tag_pkey PRIMARY KEY (tag_id)
);

CREATE TABLE session_tags (
	session_id INTEGER NOT NULL,
	tag_id INTEGER NOT NULL
);

CREATE TABLE speakers
(
    speaker_id    SERIAL,
    first_name    VARCHAR(30)   NOT NULL,
    last_name     VARCHAR(30)   NOT NULL,
    title         VARCHAR(40)   NOT NULL,
    company       VARCHAR(50)   NOT NULL,
    speaker_bio   VARCHAR(2000) NOT NULL,
    speaker_photo BYTEA   NULL,
	CONSTRAINT speakers_pkey  PRIMARY KEY(speaker_id)
);

CREATE TABLE session_speakers (
	session_id INTEGER NOT NULL,
	speaker_id INTEGER NOT NULL
);

CREATE TABLE workshops
(
    workshop_id   SERIAL,
    workshop_name VARCHAR(60)   NOT NULL,
    description   VARCHAR(1024) NOT NULL,
    requirements  VARCHAR(1024) NOT NULL,
    room          VARCHAR(30)   NOT NULL,
    capacity      INTEGER       NOT NULL,
	  CONSTRAINT workshop_pkey PRIMARY KEY(workshop_id)
);

CREATE TABLE workshop_speakers (
	workshop_id INTEGER NOT NULL,
	speaker_id INTEGER NOT NULL
);

CREATE TABLE workshop_registrations
(
    workshop_id INTEGER NOT NULL,
    attendee_ticket_id INTEGER NOT NULL
);
