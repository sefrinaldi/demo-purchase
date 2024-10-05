CREATE TABLE IF NOT EXISTS users
(
    id              bigserial      NOT NULL,
    first_name      text,
    last_name       text,
    email           varchar(255),
    phone           varchar(255),
    created_by      varchar(255)   NOT NULL,
    updated_by      varchar(255),
    created_datetime   timestamp      NOT NULL,
    updated_datetime   timestamp,
    CONSTRAINT users_PK PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS items
(
    id              bigserial       NOT NULL,
    name            text,
    description     text,
    price           integer,
    cost            integer,
    created_by      varchar(255)   NOT NULL,
    updated_by      varchar(255),
    created_datetime   timestamp      NOT NULL,
    updated_datetime   timestamp,
    CONSTRAINT items_PK PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS po_h
(
    id              bigserial       NOT NULL,
    datetime        timestamp,
    description     text,
    total_price     integer,
    total_cost      integer,
    created_by      varchar(255)   NOT NULL,
    updated_by      varchar(255),
    created_datetime   timestamp      NOT NULL,
    updated_datetime   timestamp,
    CONSTRAINT po_h_PK PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS po_d
(
    id              bigserial       NOT NULL,
    poh_id          bigserial       NOT NULL,
    item_id         bigserial       NOT NULL,
    item_qty        integer,
    item_cost       integer,
    item_price      integer,
    CONSTRAINT po_d_PK PRIMARY KEY (id),
    CONSTRAINT po_h_fkey FOREIGN KEY (poh_id) REFERENCES po_h(id),
    CONSTRAINT items_fkey FOREIGN KEY (item_id) REFERENCES items(id)
);