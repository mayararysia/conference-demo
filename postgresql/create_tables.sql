CREATE TABLE attendees
(
    attendee_id  SERIAL PRIMARY KEY,
    first_name   varchar(30) NOT NULL,
    last_name    varchar(30) NOT NULL,
    title        varchar(40) NULL,
    company      varchar(50) NULL,
    email        varchar(80) NOT NULL,
    phone_number varchar(20) NULL
);

CREATE TABLE ticket_types
(
	id  SERIAL PRIMARY KEY,
    ticket_type_code  varchar(1) NOT NULL,
    ticket_type_name  varchar(30)  NOT NULL,
    description       varchar(100) NOT NULL,
    includes_workshop boolean      NOT NULL
);

CREATE TABLE pricing_categories
(
	id  SERIAL PRIMARY KEY,
    pricing_category_code varchar(1) NOT NULL,
    pricing_category_name varchar(20) NOT NULL,
    pricing_start_date    date        NOT NULL,
    pricing_end_date      date        NOT NULL
);

CREATE TABLE ticket_prices
(
    ticket_price_id  SERIAL PRIMARY KEY,
    ticket_type_id      integer    NOT NULL REFERENCES ticket_types (id),
    pricing_id integer    NOT NULL REFERENCES pricing_categories (id),
    base_price            numeric(8, 2) NOT NULL
);


CREATE TABLE discount_codes
(
    discount_code_id SERIAL PRIMARY KEY,
    discount_code    varchar(20)   NOT NULL,
    discount_name    varchar(30)   NOT NULL,
    discount_type    varchar(1)    NOT NULL,
    discount_amount  numeric(8, 2) NOT NULL
);

CREATE TABLE attendee_tickets (
	attendee_ticket_id serial NOT NULL,
	attendee_id integer NOT NULL,
	ticket_price_id integer NOT NULL,
	discount_code_id integer NULL,
	net_price numeric(8,2) NOT NULL,
	CONSTRAINT attendee_tickets_pkey PRIMARY KEY (attendee_ticket_id),
	CONSTRAINT attendee_tickets_attendee_id_fkey FOREIGN KEY (attendee_id) REFERENCES attendees(attendee_id),
	CONSTRAINT attendee_tickets_discount_code_id_fkey FOREIGN KEY (discount_code_id) REFERENCES discount_codes(discount_code_id),
	CONSTRAINT attendee_tickets_ticket_id_fkey FOREIGN KEY (ticket_price_id) REFERENCES ticket_prices(ticket_price_id)
);


CREATE TABLE time_slots
(
    time_slot_id         SERIAL not null,
    time_slot_date       date                   NOT NULL,
    start_time           time without time zone NOT NULL,
    end_time             time without time zone NOT NULL,
    is_keynote_time_slot boolean default false  NOT NULL,
	CONSTRAINT  time_slots_pkey PRIMARY KEY (time_slot_id)
);

CREATE TABLE sessions
(
    session_id          SERIAL not null,
    session_name        varchar(80)   NOT NULL,
    session_description varchar(1024) NOT NULL,
    session_length      integer       NOT NULL,
	CONSTRAINT  sessions_pk PRIMARY KEY (session_id)
);

CREATE TABLE session_schedule (
	schedule_id serial NOT NULL,
	time_slot_id integer NOT NULL,
	session_id integer NOT NULL,
	room varchar(30) NOT NULL,
	CONSTRAINT session_schedule_pkey PRIMARY KEY (schedule_id),
	CONSTRAINT session_schedule_session_id_fkey FOREIGN KEY (session_id) REFERENCES sessions(session_id),
	CONSTRAINT session_schedule_time_slot_id_fkey FOREIGN KEY (time_slot_id) REFERENCES time_slots(time_slot_id)
);

CREATE TABLE tags
(
    tag_id      SERIAL NOT NULL,
    description varchar(30) NOT NULL,
	CONSTRAINT tag_pk PRIMARY KEY (tag_id)
);

CREATE TABLE session_tags (
	session_id integer NOT NULL,
	tag_id integer NOT NULL,
	CONSTRAINT session_tags_session_id_fkey FOREIGN KEY (session_id) REFERENCES sessions(session_id),
	CONSTRAINT session_tags_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES tags(tag_id)
);



CREATE TABLE speakers
(
    speaker_id    SERIAL  NOT NULL,
    first_name    varchar(30)   NOT NULL,
    last_name     varchar(30)   NOT NULL,
    title         varchar(40)   NOT NULL,
    company       varchar(50)   NOT NULL,
    speaker_bio   varchar(2000) NOT NULL,
    speaker_photo BYTEA   NULL,
	CONSTRAINT speakers_pk  PRIMARY KEY(speaker_id)
);


CREATE TABLE session_speakers (
	session_id integer NOT NULL,
	speaker_id integer NOT NULL,
	CONSTRAINT session_speakers_speaker_id_fk FOREIGN KEY (speaker_id) REFERENCES speakers(speaker_id),
	CONSTRAINT session_speakers_session_id_fk FOREIGN KEY (session_id) REFERENCES sessions(session_id)
);


CREATE TABLE workshops
(
    workshop_id   SERIAL NOT NULL,
    workshop_name varchar(60)   NOT NULL,
    description   varchar(1024) NOT NULL,
    requirements  varchar(1024) NOT NULL,
    room          varchar(30)   NOT NULL,
    capacity      integer       NOT NULL,
	CONSTRAINT workshop_pk PRIMARY KEY(workshop_id)
);

CREATE TABLE workshop_speakers (
	workshop_id integer NOT NULL,
	speaker_id integer NOT NULL,
	CONSTRAINT workshop_speakers_speaker_id_fkey FOREIGN KEY (speaker_id) REFERENCES speakers(speaker_id),
	CONSTRAINT workshop_speakers_workshop_id_fkey FOREIGN KEY (workshop_id) REFERENCES workshops(workshop_id)
);

CREATE TABLE workshop_registrations
(
    workshop_id        integer NOT NULL REFERENCES workshops (workshop_id),
    attendee_ticket_id integer NOT NULL REFERENCES attendee_tickets (attendee_ticket_id)
);
