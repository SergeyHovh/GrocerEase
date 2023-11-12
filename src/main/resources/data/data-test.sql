-- Insert sample clients
INSERT INTO clients (username, dob)
VALUES ('john_doe', '1990-05-15'),
       ('jane_smith', '1985-12-03');

insert into baskets (client_id)
values (1),
       (1),
       (2);

insert into products (price, product_name)
values (1, 'bread'),
       (1.5, 'milk'),
       (2.5, 'eggs')