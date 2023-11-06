-- Insert sample clients
INSERT INTO clients (username, dob)
VALUES ('john_doe', '1990-05-15'),
       ('jane_smith', '1985-12-03'),
       ('alice_jackson', '1988-03-20'),
       ('bob_adams', '1995-08-11'),
       ('lisa_brown', '1992-11-30');

insert into baskets (client_id)
values (1),
       (1),
       (2),
       (3);

insert into products (price, product_name)
values (1, 'bread'),
       (1.5, 'milk'),
       (2.5, 'eggs')