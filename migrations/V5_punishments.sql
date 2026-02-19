create table punishment_records
(
    uuid         uuid primary key,
    player_uuid  uuid      not null,
    issuer       text      not null,
    type_ordinal int       not null,
    reason       text      not null,
    start_time   timestamp not null,
    end_time     timestamp not null,
    foreign key (player_uuid) references players (uuid) on delete cascade
);

create index idx_punishment_records_player_uuid on punishment_records (player_uuid);
