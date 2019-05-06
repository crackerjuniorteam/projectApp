create table user_subscriptions
(
    channel_id uuid not null references usr,
    subscriber_id uuid not null references usr,
    primary key (channel_id, subscriber_id)
);