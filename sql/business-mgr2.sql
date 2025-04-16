--
-- PostgreSQL database dump
--

-- Dumped from database version 16.8 (Ubuntu 16.8-0ubuntu0.24.04.1)
-- Dumped by pg_dump version 16.4

-- Started on 2025-04-15 12:09:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 238 (class 1255 OID 106496)
-- Name: isvalidsubscription(date, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.isvalidsubscription(subscriptiondate date, duration integer) RETURNS boolean
    LANGUAGE plpgsql
    AS $$

DECLARE expirationdate date;
BEGIN
	expirationdate = subscriptiondate + duration;

	RETURN expirationdate > now();

END;
$$;


ALTER FUNCTION public.isvalidsubscription(subscriptiondate date, duration integer) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 98309)
-- Name: alldata; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alldata (
    id bigint NOT NULL,
    firstname character varying(255) NOT NULL,
    lastname character varying(255) NOT NULL,
    address character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    phonenumber character varying(50),
    course character varying(255),
    inscriptiondate date,
    duration integer,
    amount double precision,
    account1 double precision,
    solde1 double precision,
    account2 double precision,
    solde2 double precision,
    account3 double precision,
    solde3 double precision,
    solditems character varying(255),
    email character varying(100)
);


ALTER TABLE public.alldata OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 98304)
-- Name: alldata_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.alldata_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.alldata_id_seq OWNER TO postgres;

--
-- TOC entry 3452 (class 0 OID 0)
-- Dependencies: 215
-- Name: alldata_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.alldata_id_seq OWNED BY public.alldata.id;


--
-- TOC entry 221 (class 1259 OID 98317)
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    id bigint NOT NULL,
    firstname character varying(255) NOT NULL,
    lastname character varying(255) NOT NULL,
    address character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    phonenumber character varying(50) NOT NULL,
    picture character varying(255),
    barcodevalue character varying(50),
    email character varying(100) NOT NULL
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 98305)
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.customer_id_seq OWNER TO postgres;

--
-- TOC entry 3453 (class 0 OID 0)
-- Dependencies: 216
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- TOC entry 226 (class 1259 OID 106498)
-- Name: event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.event (
    id bigint NOT NULL,
    event_name character varying(100) NOT NULL,
    event_type character varying(100) NOT NULL,
    event_date date NOT NULL,
    amount double precision NOT NULL,
    end_date date
);


ALTER TABLE public.event OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 106497)
-- Name: event_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.event_id_seq OWNER TO postgres;

--
-- TOC entry 3454 (class 0 OID 0)
-- Dependencies: 225
-- Name: event_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.event_id_seq OWNED BY public.event.id;


--
-- TOC entry 222 (class 1259 OID 98325)
-- Name: member; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.member (
    id bigint NOT NULL,
    firstname character varying NOT NULL,
    lastname character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL,
    active boolean DEFAULT false NOT NULL
);


ALTER TABLE public.member OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 98306)
-- Name: member_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.member_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.member_id_seq OWNER TO postgres;

--
-- TOC entry 3455 (class 0 OID 0)
-- Dependencies: 217
-- Name: member_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.member_id_seq OWNED BY public.member.id;


--
-- TOC entry 224 (class 1259 OID 98340)
-- Name: payment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment (
    id bigint NOT NULL,
    subscription_id bigint NOT NULL,
    amount double precision NOT NULL,
    payment_date date NOT NULL
);


ALTER TABLE public.payment OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 98308)
-- Name: payment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.payment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.payment_id_seq OWNER TO postgres;

--
-- TOC entry 3456 (class 0 OID 0)
-- Dependencies: 219
-- Name: payment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.payment_id_seq OWNED BY public.payment.id;


--
-- TOC entry 223 (class 1259 OID 98334)
-- Name: subscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subscription (
    id bigint NOT NULL,
    customer_id bigint NOT NULL,
    duration integer NOT NULL,
    taekwondo boolean,
    kickboxing boolean,
    taekibodo boolean,
    amount double precision NOT NULL,
    subscription_date date NOT NULL
);


ALTER TABLE public.subscription OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 98307)
-- Name: subscription_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.subscription_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.subscription_id_seq OWNER TO postgres;

--
-- TOC entry 3457 (class 0 OID 0)
-- Dependencies: 218
-- Name: subscription_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.subscription_id_seq OWNED BY public.subscription.id;


--
-- TOC entry 3273 (class 2604 OID 98312)
-- Name: alldata id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alldata ALTER COLUMN id SET DEFAULT nextval('public.alldata_id_seq'::regclass);


--
-- TOC entry 3274 (class 2604 OID 98320)
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- TOC entry 3279 (class 2604 OID 106501)
-- Name: event id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event ALTER COLUMN id SET DEFAULT nextval('public.event_id_seq'::regclass);


--
-- TOC entry 3275 (class 2604 OID 98328)
-- Name: member id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member ALTER COLUMN id SET DEFAULT nextval('public.member_id_seq'::regclass);


--
-- TOC entry 3278 (class 2604 OID 98343)
-- Name: payment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment ALTER COLUMN id SET DEFAULT nextval('public.payment_id_seq'::regclass);


--
-- TOC entry 3277 (class 2604 OID 98337)
-- Name: subscription id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription ALTER COLUMN id SET DEFAULT nextval('public.subscription_id_seq'::regclass);


--
-- TOC entry 3440 (class 0 OID 98309)
-- Dependencies: 220
-- Data for Name: alldata; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alldata (id, firstname, lastname, address, city, phonenumber, course, inscriptiondate, duration, amount, account1, solde1, account2, solde2, account3, solde3, solditems, email) FROM stdin;
\.


--
-- TOC entry 3441 (class 0 OID 98317)
-- Dependencies: 221
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (id, firstname, lastname, address, city, phonenumber, picture, barcodevalue, email) FROM stdin;
1	Pierre	Dupont	12 rue de la Montagne	Montréal	0102030405	\N	\N	pierre.dupont@email.com
2	Marie	Martin	15 avenue des Pins	Montréal	0607080910	\N	\N	marie.martin@email.com
3	Jacques	Lefevre	23 boulevard Saint-Laurent	Montréal	0145567890	\N	\N	jacques.lefevre@email.com
4	Sophie	Durand	10 rue Saint-Jean	Québec	0123456789	\N	\N	sophie.durand@email.com
5	Alain	Bernard	42 rue de l'Université	Trois-Rivières	0178392910	\N	\N	alain.bernard@email.com
6	Claire	Robert	56 rue de la Paix	Laval	0321564789	\N	\N	claire.robert@email.com
7	Thomas	Petit	78 rue de Verdun	Montréal	0612345678	\N	\N	thomas.petit@email.com
8	Lucie	Roux	34 rue de l'Église	Gatineau	0739281940	\N	\N	lucie.roux@email.com
9	Michel	Lefevre	67 rue des Érables	Longueuil	0987654321	\N	\N	michel.lefevre@email.com
10	Emma	Marchand	89 avenue Cartier	Sherbrooke	0156321478	\N	\N	emma.marchand@email.com
11	Marc	Lefevre	123 rue des Laurentides	Laval	0147362850	\N	\N	marc.lefevre@email.com
12	Isabelle	Laurent	33 rue du Parc	Montréal	0624567834	\N	\N	isabelle.laurent@email.com
13	Nicolas	Dupuis	45 rue de la Rivière	Lévis	0687439821	\N	\N	nicolas.dupuis@email.com
14	Alice	Lemoine	56 rue de l'Industrie	Montréal	0156472910	\N	\N	alice.lemoine@email.com
15	Pierre	Boucher	77 rue des Tilleuls	Saint-Jean-sur-Richelieu	0981264785	\N	\N	pierre.boucher@email.com
16	Céline	Barbier	32 rue du Vieux-Port	Québec	0778123456	\N	\N	celine.barbier@email.com
17	Didier	Robert	23 avenue de la Gare	Trois-Rivières	0156345987	\N	\N	didier.robert@email.com
18	Sophie	Rousseau	55 rue des Bois	Laval	0624738294	\N	\N	sophie.rousseau@email.com
19	François	Martin	102 rue des Lilas	Gatineau	0148529304	\N	\N	francois.martin@email.com
20	Hélène	Lefevre	67 rue du Vieux-Montréal	Montréal	0623761987	\N	\N	helene.lefevre@email.com
21	Xavier	Durand	89 rue de l'Acier	Shawinigan	0712564390	\N	\N	xavier.durand@email.com
22	Camille	Lefevre	11 rue des Fleurs	Trois-Rivières	0692318475	\N	\N	camille.lefevre@email.com
23	Laurent	Lambert	23 rue du Parc	Laval	0783921846	\N	\N	laurent.lambert@email.com
24	Julie	Lefevre	54 rue de la Rivière	Montréal	0778391023	\N	\N	julie.lefevre@email.com
25	Thomas	Robert	34 rue des Pins	Québec	0657382917	\N	\N	thomas.robert@email.com
26	Pierre	Lefevre	15 rue du Parc	Gatineau	0603128479	\N	\N	pierre.lefevre@email.com
27	Claire	Dupuis	101 rue du Lac	Saint-Hyacinthe	0168523947	\N	\N	claire.dupuis@email.com
28	Lucien	Bernard	47 avenue du Mont-Royal	Montréal	0987412365	\N	\N	lucien.bernard@email.com
29	Éric	Marchand	32 rue de la Ville	Trois-Rivières	0773892651	\N	\N	eric.marchand@email.com
30	Isabelle	Lefevre	57 rue des Mimosas	Laval	0683748291	\N	\N	isabelle.lefevre@email.com
31	Claire	Lefevre	22 rue de Saint-Jean	Québec	0661529370	\N	\N	claire.lefevre@email.com
32	Thierry	Robert	39 rue de la Montagne	Gatineau	0159234738	\N	\N	thierry.robert@email.com
33	Anne	Lefevre	 102 rue des Pins	Laval	0783921045	\N	\N	anne.lefevre@email.com
34	Michel	 Durand	14 rue des Érables	Longueuil	0705674812	\N	\N	michel.durand@email.com
35	Valérie	Lefevre	121 rue de l'Université	Québec	0612089347	\N	\N	valerie.lefevre@email.com
36	Olivier	Dupont	13 rue de la Plage	Rimouski	0783019835	\N	\N	olivier.dupont@email.com
37	Gérard	Rousseau	55 avenue du Parc	Montréal	0619238476	\N	\N	gerard.rousseau@email.com
38	Thomas	GERARD	1 rue du Lac	Montréal	514-111-222	\N	202503250038	thomas.gerard@email.com
\.


--
-- TOC entry 3446 (class 0 OID 106498)
-- Dependencies: 226
-- Data for Name: event; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.event (id, event_name, event_type, event_date, amount, end_date) FROM stdin;
1	Gala Kickboxing ICEMAN Gatineau	Compétition	2025-05-25	0	\N
2	Rencontre LEVY World Kobudo	Festival - congrès	2025-05-02	0	\N
\.


--
-- TOC entry 3442 (class 0 OID 98325)
-- Dependencies: 222
-- Data for Name: member; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.member (id, firstname, lastname, email, password, active) FROM stdin;
1	Admin	MYBUSINESS	admin@mybusiness.ca	8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918	t
\.


--
-- TOC entry 3444 (class 0 OID 98340)
-- Dependencies: 224
-- Data for Name: payment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payment (id, subscription_id, amount, payment_date) FROM stdin;
\.


--
-- TOC entry 3443 (class 0 OID 98334)
-- Dependencies: 223
-- Data for Name: subscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subscription (id, customer_id, duration, taekwondo, kickboxing, taekibodo, amount, subscription_date) FROM stdin;
1	38	90	t	t	t	1200	2025-03-25
\.


--
-- TOC entry 3458 (class 0 OID 0)
-- Dependencies: 215
-- Name: alldata_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.alldata_id_seq', 1, false);


--
-- TOC entry 3459 (class 0 OID 0)
-- Dependencies: 216
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_id_seq', 38, true);


--
-- TOC entry 3460 (class 0 OID 0)
-- Dependencies: 225
-- Name: event_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.event_id_seq', 2, true);


--
-- TOC entry 3461 (class 0 OID 0)
-- Dependencies: 217
-- Name: member_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.member_id_seq', 1, true);


--
-- TOC entry 3462 (class 0 OID 0)
-- Dependencies: 219
-- Name: payment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.payment_id_seq', 2, true);


--
-- TOC entry 3463 (class 0 OID 0)
-- Dependencies: 218
-- Name: subscription_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subscription_id_seq', 1, true);


--
-- TOC entry 3281 (class 2606 OID 98316)
-- Name: alldata alldata_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alldata
    ADD CONSTRAINT alldata_pkey PRIMARY KEY (id);


--
-- TOC entry 3283 (class 2606 OID 98324)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- TOC entry 3291 (class 2606 OID 106503)
-- Name: event event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_pkey PRIMARY KEY (id);


--
-- TOC entry 3285 (class 2606 OID 98333)
-- Name: member member_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_pkey PRIMARY KEY (id);


--
-- TOC entry 3289 (class 2606 OID 98345)
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);


--
-- TOC entry 3287 (class 2606 OID 98339)
-- Name: subscription subscription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscription_pkey PRIMARY KEY (id);


-- Completed on 2025-04-15 12:09:44

--
-- PostgreSQL database dump complete
--

