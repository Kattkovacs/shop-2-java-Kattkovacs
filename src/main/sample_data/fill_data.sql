INSERT INTO supplier(name, description) VALUES ('Nescafe', 'For the moments that matter.');
INSERT INTO supplier(name, description) VALUES ('Tchibo', 'A really good coffee.');
INSERT INTO supplier(name, description) VALUES ('Java', 'Write once, run anywhere. :)');
INSERT INTO supplier(name, description) VALUES ('Starbucks', 'Get together. Over coffee.');

INSERT INTO product_category(name, description, department) VALUES ("Iced Coffee", "Coffee", "Iced coffees become very popular in the summer time due to its ice cubes in it :)");
INSERT INTO product_category(name, description, department) VALUES ("Espresso", "Coffee", "Also known as a short black, is apr. one ounce of highly concentrated coffee.");
INSERT INTO product_category(name, description, department) VALUES ("Macchiato", "Coffee", "The word macchiato means mark or stain. This is in reference to the mark that steamed milk leaves on the surface of the espresso as it is dashed into the drink.");
INSERT INTO product_category(name, description, department) VALUES ("Cappuccino", "Coffee", "A creamy coffee drink is with thick foam layer and additional flavorings that can be added to it.");
INSERT INTO product_category(name, description, department) VALUES ("Latte", "Coffee", "Cafe lattes are considered to be a coffee with a big amount of milk in the beverage. Flavoring syrups are added to the latte for those who enjoy sweeter drinks.");

INSERT INTO product(name, description, default_price, default_currency) VALUES ("Ice Cold Cappuccino", 9.9f, "USD", "An ice cold cappuccino developed by our developer team.", icedCoffee, java));
INSERT INTO product(name, description, default_price, default_currency) VALUES ("Amazon Fire", 4.9f, "USD", "Fantastic reboot to your brain. Large content coffeine. Helpful technical support.", espresso, nescafe));
INSERT INTO product(name, description, default_price, default_currency) VALUES ("Codecoolers dream", 5.0f, "USD", "A sweet, bug safe drink to take a break.", cappuccino, tchibo));
INSERT INTO product(name, description, default_price, default_currency) VALUES ("Go To Cheers", 8.0f, "USD", "Take a break.", latte, tchibo ));
INSERT INTO product(name, description, default_price, default_currency) VALUES ("The cold roof", 5.9f, "USD", "When you believe compiler ignores all your comments.", icedCoffee, tchibo ));
INSERT INTO product(name, description, default_price, default_currency) VALUES ("PA vulcano", 6.0f, "USD", "When you really need the energy and the power.", macchiato, starbucks ));
INSERT INTO product(name, description, default_price, default_currency) VALUES ("Consultation friend", 8.0f, "USD", "A cold and sweet friend always.", icedCoffee, nescafe ));
INSERT INTO product(name, description, default_price, default_currency) VALUES ("JavaCode", 8.0f, "USD", "This is the java, if you don't know it, then google!", cappuccino, starbucks ));
INSERT INTO product(name, description, default_price, default_currency) VALUES ("Mentoring session", 6.0f, "USD", "A good coffee to develop in knowledge.", latte, nescafe ));
INSERT INTO product(name, description, default_price, default_currency) VALUES ("Demo break", 4.9f, "USD", "When you only have 5 minutes..:)", espresso, java ));
