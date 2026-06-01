create table profile
(
    id         bigserial not null,
    email      varchar(255) not null unique,
    password   varchar(255) not null,
    name       varchar(255) not null,
    nickname   varchar(255) not null unique,
    avatar_url varchar(255),
    created_at timestamp(6),
    updated_at timestamp(6),
    primary key (id)
);

create table prediction_group
(
    id          bigserial not null,
    owner_id    bigint,
    name        varchar(255) not null unique,
    description varchar(255),
    invite_code varchar(255) not null unique,
    password    varchar(255),
    is_private  boolean,
    created_at  timestamp(6),
    updated_at  timestamp(6),

    primary key (id),

    constraint fk_prediction_group_profile
        foreign key (owner_id)
            references profile(id)
);

create table group_member
(
    id         bigserial not null,
    group_id   bigint not null,
    profile_id bigint not null,
    role       varchar(255) check (role in ('OWNER', 'ADMIN', 'MEMBER')),
    joined_at  timestamp(6),

    primary key (id),

    constraint fk_group_member_group
        foreign key (group_id)
            references prediction_group(id),

    constraint fk_group_member_profile
        foreign key (profile_id)
            references profile(id),

    constraint uk_group_member_group_profile
        unique (group_id, profile_id)
);