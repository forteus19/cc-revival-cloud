alter table players add column score int not null default 0;

create index idx_players_score on players (score);
