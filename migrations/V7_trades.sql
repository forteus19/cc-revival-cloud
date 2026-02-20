create table trades
(
    uuid           uuid primary key,
    offerer_uuid   uuid      not null,
    returner_uuid  uuid      not null,
    start_time     timestamp not null,
    end_time       timestamp,
    result_ordinal int       not null,
    offer_stacks   uuid[]    not null,
    return_stacks  uuid[]    not null,
    foreign key (offerer_uuid) references players (uuid) on delete cascade,
    foreign key (returner_uuid) references players (uuid) on delete cascade
);

create index idx_trades_offerer_uuid on trades (offerer_uuid);
create index idx_trades_returner_uuid on trades (returner_uuid);
