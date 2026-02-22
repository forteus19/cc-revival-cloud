create table player_case_stats
(
    player_uuid    uuid not null,
    case_item_id   int  not null,
    result_item_id int  not null,
    count          int  not null,
    primary key (player_uuid, case_item_id, result_item_id),
    foreign key (player_uuid) references players (uuid) on delete cascade
);

create index idx_player_case_stats_player_uuid on player_case_stats (player_uuid);
