create table players
(
    uuid           uuid primary key,
    username       text not null,
    trophies       int  not null,
    kills          int  not null,
    deaths         int  not null,
    games          int  not null,
    first_bloods   int  not null,
    match_karma    int  not null,
    minutes_played int  not null,
    exp            int  not null,
    emeralds       int  not null,
    mood           text
);

create table player_friends
(
    player_uuid uuid not null,
    friend_uuid uuid not null,
    primary key (player_uuid, friend_uuid),
    foreign key (player_uuid) references players (uuid) on delete cascade
);

create table player_friend_requests
(
    player_uuid    uuid not null,
    requester_uuid uuid not null,
    primary key (player_uuid, requester_uuid),
    foreign key (player_uuid) references players (uuid) on delete cascade
);

create table player_inventories
(
    uuid       uuid primary key,
    owner_uuid uuid    not null,
    item_id    int     not null,
    nametag    text,
    equipped   boolean not null,
    foreign key (owner_uuid) references players (uuid) on delete cascade
);

create index idx_player_inventories_owner_uuid on player_inventories (owner_uuid);
