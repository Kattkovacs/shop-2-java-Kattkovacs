ALTER TABLE IF EXISTS ONLY public.supplier DROP CONSTRAINT IF EXISTS supplier_pk CASCADE;
ALTER TABLE IF EXISTS ONLY public.product_category DROP CONSTRAINT IF EXISTS product_category_pk CASCADE;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS supplier_id_fk CASCADE;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS product_category_id_fk CASCADE;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS product_pk CASCADE;


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

