INSERT INTO supplier(name, description) VALUES ('Nescafe', 'For the moments that matter.');
INSERT INTO supplier(name, description) VALUES ('Tchibo', 'A really good coffee.');
INSERT INTO supplier(name, description) VALUES ('Java', 'Write once, run anywhere. :)');
INSERT INTO supplier(name, description) VALUES ('Starbucks', 'Get together. Over coffee.');

INSERT INTO product_category(name, description, department) VALUES ('Iced Coffee', 'Coffee', 'Iced coffees become very popular in the summer time due to its ice cubes in it :)');
INSERT INTO product_category(name, description, department) VALUES ('Espresso', 'Coffee', 'Also known as a short black, is apr. one ounce of highly concentrated coffee.');
INSERT INTO product_category(name, description, department) VALUES ('Macchiato', 'Coffee', 'The word macchiato means mark or stain. This is in reference to the mark that steamed milk leaves on the surface of the espresso as it is dashed into the drink.');
INSERT INTO product_category(name, description, department) VALUES ('Cappuccino', 'Coffee', 'A creamy coffee drink is with thick foam layer and additional flavorings that can be added to it.');
INSERT INTO product_category(name, description, department) VALUES ('Latte', 'Coffee', 'Cafe lattes are considered to be a coffee with a big amount of milk in the beverage. Flavoring syrups are added to the latte for those who enjoy sweeter drinks.');

INSERT INTO product(name, description, default_price, default_currency, supplier_id, product_category_id) VALUES ('Ice Cold Cappuccino', 'An ice cold cappuccino developed by our developer team.', 9.9, 'USD', 1, 3);
INSERT INTO product(name, description, default_price, default_currency, supplier_id, product_category_id) VALUES ('Amazon Fire', 'Fantastic reboot to your brain. Large content coffeine. Helpful technical support.', 4.9, 'USD', 2, 1);
INSERT INTO product(name, description, default_price, default_currency, supplier_id, product_category_id) VALUES ('Codecoolers dream', 'A sweet, bug safe drink to take a break.', 5.0, 'USD' , 4, 2);
INSERT INTO product(name, description, default_price, default_currency, supplier_id, product_category_id) VALUES ('Go To Cheers', 'Take a break.', 8.0, 'USD', 2, 5);
INSERT INTO product(name, description, default_price, default_currency, supplier_id, product_category_id) VALUES ('The cold roof', 'When you believe compiler ignores all your comments.', 5.9, 'USD', 1, 2);
INSERT INTO product(name, description, default_price, default_currency, supplier_id, product_category_id) VALUES ('PA vulcano', 'When you really need the energy and the power.', 6.0, 'USD', 3, 4);
INSERT INTO product(name, description, default_price, default_currency, supplier_id, product_category_id) VALUES('Consultation friend', 'A cold and sweet friend always.', 8.0, 'USD', 1, 1);
INSERT INTO product(name, description, default_price, default_currency, supplier_id, product_category_id) VALUES ('JavaCode', 'This is the java, if you don''t know it, then google!', 8.0, 'USD', 4, 4);
INSERT INTO product(name, description, default_price, default_currency, supplier_id, product_category_id) VALUES ('Mentoring session', 'A good coffee to develop in knowledge.', 6.0, 'USD',  1, 5);
INSERT INTO product(name, description, default_price, default_currency, supplier_id, product_category_id) VALUES ('Demo break', 'When you only have 5 minutes..:)', 4.9, 'USD', 2, 3);