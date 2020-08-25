ALTER TABLE IF EXISTS ONLY public.supplier DROP CONSTRAINT IF EXISTS supplier_pk CASCADE;
ALTER TABLE IF EXISTS ONLY public.product_category DROP CONSTRAINT IF EXISTS product_category_pk CASCADE;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS supplier_id_fk CASCADE;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS product_category_id_fk CASCADE;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS product_pk CASCADE;
ALTER TABLE IF EXISTS ONLY public.user DROP CONSTRAINT IF EXISTS user_pk CASCADE;
ALTER TABLE IF EXISTS ONLY public.order DROP CONSTRAINT IF EXISTS order_pk CASCADE;


DROP TABLE IF EXISTS public.supplier;
CREATE TABLE public.supplier (
                                id serial NOT NULL
                                   constraint supplier_pk
                                       primary key,
                                name text NOT NULL,
                                description text NOT NULL
                             );


DROP TABLE IF EXISTS public.product_category;
CREATE TABLE public.product_category (
                                 id serial NOT NULL
                                     constraint product_category_pk
                                         primary key,
                                 name text NOT NULL,
                                 description text NOT NULL,
                                 department text NOT NULL
);

DROP TABLE IF EXISTS public.product;
CREATE TABLE public.product (
                                 id serial NOT NULL
                                     constraint product_pk
                                         primary key,
                                 name text NOT NULL,
                                 description text NOT NULL,
                                 default_price float8 NOT NULL,
                                 default_currency text NOT NULL,
                                 supplier_id integer NOT NULL
                                     constraint supplier_id_fk
                                         references supplier
                                            on delete cascade,
                                 product_category_id integer NOT NULL
                                     constraint product_category_id_fk
                                         references product_category
                                            on delete cascade
);

DROP TABLE IF EXISTS public.user;
CREATE TABLE public.user (
                                id serial NOT NULL
                                   constraint user_pk
                                       primary key,
                                first_name text NOT NULL,
                                last_name text NOT NULL,
                                email text NOT NULL,
                                password text NOT NULL
);

DROP TABLE IF EXISTS public.order;
CREATE TABLE public.order (
                                id serial NOT NULL
                                   constraint order_pk
                                       primary key,
                                phone text NOT NULL,
                                address text NOT NULL,
                                city text NOT NULL,
                                state text NOT NULL,
                                zip int NOT NULL,
                                address2 text NOT NULL,
                                city2 text NOT NULL,
                                state2 text NOT NULL,
                                zip2 int NOT NULL

);



