--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-05-15 21:07:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 241 (class 1259 OID 20023)
-- Name: accesslog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accesslog (
    id bigint NOT NULL,
    date date NOT NULL,
    customer_number character varying NOT NULL,
    firstname character varying NOT NULL,
    lastname character varying NOT NULL,
    granted boolean NOT NULL
);


ALTER TABLE public.accesslog OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 20022)
-- Name: accesslog_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.accesslog_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.accesslog_id_seq OWNER TO postgres;

--
-- TOC entry 4954 (class 0 OID 0)
-- Dependencies: 240
-- Name: accesslog_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.accesslog_id_seq OWNED BY public.accesslog.id;


--
-- TOC entry 217 (class 1259 OID 19890)
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
-- TOC entry 218 (class 1259 OID 19895)
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
-- TOC entry 4955 (class 0 OID 0)
-- Dependencies: 218
-- Name: alldata_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.alldata_id_seq OWNED BY public.alldata.id;


--
-- TOC entry 245 (class 1259 OID 20041)
-- Name: article; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.article (
    id bigint NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    price double precision NOT NULL
);


ALTER TABLE public.article OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 20040)
-- Name: article_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.article_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.article_id_seq OWNER TO postgres;

--
-- TOC entry 4956 (class 0 OID 0)
-- Dependencies: 244
-- Name: article_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.article_id_seq OWNED BY public.article.id;


--
-- TOC entry 219 (class 1259 OID 19896)
-- Name: batch_job_execution; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batch_job_execution (
    job_execution_id bigint NOT NULL,
    version bigint,
    job_instance_id bigint NOT NULL,
    create_time timestamp without time zone NOT NULL,
    start_time timestamp without time zone,
    end_time timestamp without time zone,
    status character varying(10),
    exit_code character varying(2500),
    exit_message character varying(2500),
    last_updated timestamp without time zone
);


ALTER TABLE public.batch_job_execution OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 19901)
-- Name: batch_job_execution_context; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batch_job_execution_context (
    job_execution_id bigint NOT NULL,
    short_context character varying(2500) NOT NULL,
    serialized_context text
);


ALTER TABLE public.batch_job_execution_context OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 19906)
-- Name: batch_job_execution_params; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batch_job_execution_params (
    job_execution_id bigint NOT NULL,
    parameter_name character varying(100) NOT NULL,
    parameter_type character varying(100) NOT NULL,
    parameter_value character varying(2500),
    identifying character(1) NOT NULL
);


ALTER TABLE public.batch_job_execution_params OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 19911)
-- Name: batch_job_execution_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.batch_job_execution_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.batch_job_execution_seq OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 19912)
-- Name: batch_job_instance; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batch_job_instance (
    job_instance_id bigint NOT NULL,
    version bigint,
    job_name character varying(100) NOT NULL,
    job_key character varying(32) NOT NULL
);


ALTER TABLE public.batch_job_instance OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 19915)
-- Name: batch_job_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.batch_job_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.batch_job_seq OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 19916)
-- Name: batch_step_execution; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batch_step_execution (
    step_execution_id bigint NOT NULL,
    version bigint NOT NULL,
    step_name character varying(100) NOT NULL,
    job_execution_id bigint NOT NULL,
    create_time timestamp without time zone NOT NULL,
    start_time timestamp without time zone,
    end_time timestamp without time zone,
    status character varying(10),
    commit_count bigint,
    read_count bigint,
    filter_count bigint,
    write_count bigint,
    read_skip_count bigint,
    write_skip_count bigint,
    process_skip_count bigint,
    rollback_count bigint,
    exit_code character varying(2500),
    exit_message character varying(2500),
    last_updated timestamp without time zone
);


ALTER TABLE public.batch_step_execution OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 19921)
-- Name: batch_step_execution_context; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batch_step_execution_context (
    step_execution_id bigint NOT NULL,
    short_context character varying(2500) NOT NULL,
    serialized_context text
);


ALTER TABLE public.batch_step_execution_context OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 19926)
-- Name: batch_step_execution_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.batch_step_execution_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.batch_step_execution_seq OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 19927)
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    id bigint NOT NULL,
    firstname character varying(255) NOT NULL,
    lastname character varying(255) NOT NULL,
    address character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    phonenumber character varying(50),
    picture character varying(255),
    barcodevalue character varying(50),
    email character varying(100),
    title character varying(30),
    level character varying(100),
    height integer,
    weight integer,
    active boolean DEFAULT true NOT NULL,
    birthdate date
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 19932)
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
-- TOC entry 4957 (class 0 OID 0)
-- Dependencies: 229
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- TOC entry 230 (class 1259 OID 19933)
-- Name: event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.event (
    id bigint NOT NULL,
    event_name character varying NOT NULL,
    event_type character varying NOT NULL,
    event_date date NOT NULL,
    end_date date,
    amount double precision DEFAULT 0.0 NOT NULL
);


ALTER TABLE public.event OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 19939)
-- Name: event_attendee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.event_attendee (
    id bigint NOT NULL,
    event_id bigint NOT NULL,
    customer_id bigint NOT NULL,
    payed boolean,
    amount double precision
);


ALTER TABLE public.event_attendee OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 19942)
-- Name: event_attendee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.event_attendee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.event_attendee_id_seq OWNER TO postgres;

--
-- TOC entry 4958 (class 0 OID 0)
-- Dependencies: 232
-- Name: event_attendee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.event_attendee_id_seq OWNED BY public.event_attendee.id;


--
-- TOC entry 233 (class 1259 OID 19943)
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
-- TOC entry 4959 (class 0 OID 0)
-- Dependencies: 233
-- Name: event_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.event_id_seq OWNED BY public.event.id;


--
-- TOC entry 234 (class 1259 OID 19944)
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
-- TOC entry 235 (class 1259 OID 19950)
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
-- TOC entry 4960 (class 0 OID 0)
-- Dependencies: 235
-- Name: member_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.member_id_seq OWNED BY public.member.id;


--
-- TOC entry 236 (class 1259 OID 19951)
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
-- TOC entry 237 (class 1259 OID 19954)
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
-- TOC entry 4961 (class 0 OID 0)
-- Dependencies: 237
-- Name: payment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.payment_id_seq OWNED BY public.payment.id;


--
-- TOC entry 247 (class 1259 OID 20050)
-- Name: purchase; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchase (
    id bigint NOT NULL,
    customer_id bigint NOT NULL,
    article_id bigint NOT NULL,
    number integer NOT NULL,
    discount double precision DEFAULT 0 NOT NULL,
    purchase_date timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.purchase OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 20049)
-- Name: purchase_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.purchase_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.purchase_id_seq OWNER TO postgres;

--
-- TOC entry 4962 (class 0 OID 0)
-- Dependencies: 246
-- Name: purchase_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.purchase_id_seq OWNED BY public.purchase.id;


--
-- TOC entry 238 (class 1259 OID 19955)
-- Name: subscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subscription (
    id bigint NOT NULL,
    customer_id bigint NOT NULL,
    duration integer NOT NULL,
    taekwondo boolean,
    kickboxing boolean,
    taekibodo boolean,
    amount double precision,
    subscription_date date
);


ALTER TABLE public.subscription OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 19958)
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
-- TOC entry 4963 (class 0 OID 0)
-- Dependencies: 239
-- Name: subscription_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.subscription_id_seq OWNED BY public.subscription.id;


--
-- TOC entry 243 (class 1259 OID 20032)
-- Name: weight_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.weight_category (
    id bigint NOT NULL,
    name character varying NOT NULL,
    gender character varying NOT NULL,
    sport character varying NOT NULL,
    federation character varying NOT NULL,
    above_limit character varying NOT NULL,
    below_limit character varying NOT NULL
);


ALTER TABLE public.weight_category OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 20031)
-- Name: weight_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.weight_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.weight_category_id_seq OWNER TO postgres;

--
-- TOC entry 4964 (class 0 OID 0)
-- Dependencies: 242
-- Name: weight_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.weight_category_id_seq OWNED BY public.weight_category.id;


--
-- TOC entry 4728 (class 2604 OID 20026)
-- Name: accesslog id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accesslog ALTER COLUMN id SET DEFAULT nextval('public.accesslog_id_seq'::regclass);


--
-- TOC entry 4718 (class 2604 OID 19959)
-- Name: alldata id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alldata ALTER COLUMN id SET DEFAULT nextval('public.alldata_id_seq'::regclass);


--
-- TOC entry 4730 (class 2604 OID 20044)
-- Name: article id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article ALTER COLUMN id SET DEFAULT nextval('public.article_id_seq'::regclass);


--
-- TOC entry 4719 (class 2604 OID 19960)
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- TOC entry 4721 (class 2604 OID 19961)
-- Name: event id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event ALTER COLUMN id SET DEFAULT nextval('public.event_id_seq'::regclass);


--
-- TOC entry 4723 (class 2604 OID 19962)
-- Name: event_attendee id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event_attendee ALTER COLUMN id SET DEFAULT nextval('public.event_attendee_id_seq'::regclass);


--
-- TOC entry 4724 (class 2604 OID 19963)
-- Name: member id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member ALTER COLUMN id SET DEFAULT nextval('public.member_id_seq'::regclass);


--
-- TOC entry 4726 (class 2604 OID 19964)
-- Name: payment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment ALTER COLUMN id SET DEFAULT nextval('public.payment_id_seq'::regclass);


--
-- TOC entry 4731 (class 2604 OID 20053)
-- Name: purchase id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase ALTER COLUMN id SET DEFAULT nextval('public.purchase_id_seq'::regclass);


--
-- TOC entry 4727 (class 2604 OID 19965)
-- Name: subscription id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription ALTER COLUMN id SET DEFAULT nextval('public.subscription_id_seq'::regclass);


--
-- TOC entry 4729 (class 2604 OID 20035)
-- Name: weight_category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.weight_category ALTER COLUMN id SET DEFAULT nextval('public.weight_category_id_seq'::regclass);


--
-- TOC entry 4942 (class 0 OID 20023)
-- Dependencies: 241
-- Data for Name: accesslog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.accesslog (id, date, customer_number, firstname, lastname, granted) FROM stdin;
1	2025-05-05	202504020416	Abderrahi	BOUDOUMA	t
\.


--
-- TOC entry 4918 (class 0 OID 19890)
-- Dependencies: 217
-- Data for Name: alldata; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alldata (id, firstname, lastname, address, city, phonenumber, course, inscriptiondate, duration, amount, account1, solde1, account2, solde2, account3, solde3, solditems, email) FROM stdin;
1	Med amin	GARRAOUI	8262RUE LONARDO	SLN	514-4414609	TAE KWON DO	0022-09-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
2	Rassi	ABDELBARI	8107 RUEDE NICE	STL	7254260 /  6382935	TAE KWON DO	0002-10-18	365	750	750	\N	\N	\N	\N	\N	\N	\N
3	Sadi	ABDELBARI	8107 RUEDE NICE	STL	7254260 /  6382935	TAE KWON DO	0002-10-18	365	750	750	\N	\N	\N	\N	\N	\N	\N
4	Aram	ABDELRAHMANNE	12554 64ÉAVENUE	MTL	514-3239892	KICK BOXING	0001-02-24	365	950	500	\N	\N	\N	\N	\N	\N	\N
5	Hssain	ABDESSAMAD	11515 BL LANGELIER	MN	514-2602912	KICK BOXING	0020-09-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
6	Angel	ACETO	855 CREMARIEST	MTL	514-205-9134	KICK BOXING	0001-08-05	365	488	244	\N	\N	\N	\N	\N	\N	\N
7	Kaei aiss	ADAM	10178 KARL LEVEQUE	MTL	438-464-2108	KICK BOXING	0011-09-23	365	900	300	\N	\N	\N	\N	\N	\N	\N
8	Ada	ADDIYA	12180 16E,AVENUE	MTL	514-9629008	TAE KWON DO	0026-11-21	90	320	320	\N	\N	\N	\N	\N	\N	\N
9	Loua	AHMIME	12050 AV PIERRE BLANCHET	MTL	438-8336250	TAE KWON DO	0009-11-23	365	900	500	\N	\N	\N	\N	\N	\N	\N
10	Ani	AIT SI MOHAMED	12530 FERDINAND BASTIEN	MTL	438-396-9219	TAEKWONDO	0001-12-23	365	950	500	\N	\N	\N	\N	\N	\N	\N
11	Youce	AIT-HAMMOUDI	2096351 BLV Joseph Renaud	MTL	438-509-9110	TAE KWON DO	0007-09-23	365	900	450	\N	\N	\N	\N	\N	\N	\N
12	Mani razin	AIT-HAMMOUDI	2096351 BLV Joseph Renaud	MTL	438-509-9110	TAE KWON DO	0007-09-23	365	900	0	\N	\N	\N	\N	\N	\N	\N
13	Yousse	AJROUDI	10953 AVENUDROPEAU	MN	514-5571211	TAE KWON DO	0010-09-19	365	750	400	\N	\N	\N	\N	\N	\N	\N
14	Mera	AMAZICH	6955 26É AVENUE	MTL	514-2366046	KICK BOXING	0015-11-22	365	850	610	\N	\N	\N	\N	\N	\N	\N
15	Amyass	AMGHAR	7351 B. MORICE-DUPLISSIS	MTL	438-8703632	TAE KWON DO	0020-02-20	365	750	200	\N	\N	\N	\N	\N	\N	\N
16	Debora	APOLLON ROY	12,315 J.A. Rouleau	MTL	8810334 /  917-4987	TAE KWON DO	0001-09-09	365	500	500	\N	\N	\N	\N	\N	\N	\N
17	Kati	ARAB	7380 AV DE L,ALSACE	ANJOU	3519523 /  7972040	TAE KWON DO	0001-10-17	365	750	440	\N	\N	\N	\N	\N	\N	\N
18	Rachid	ARRID	8440-25/AVENU-N5	M-N	514-5935668	TAE KWON DO	0016-04-16	365	850	850	\N	\N	\N	\N	\N	\N	\N
19	Ri	ARRID	8440-25/AVENU-N5	M-N	514-5935668	TAE KWON DO	0016-04-16	365	750	750	\N	\N	\N	\N	\N	\N	\N
20	Steven	AUGUSTE	12758 PLACE LOUIS CHARTIER	MTL	514-2951041	KICK BOXING	0001-11-23	365	950	300	\N	\N	\N	\N	\N	\N	\N
21	Anso	AZIMYT	12155RUE PHILIPPE LEBON	MTL	2946536 /  5017016	KICK BOXING	0008-02-17	365	800	800	\N	\N	\N	\N	\N	\N	\N
22	Kari	BACHAND	11482 AV OLIER	MTL	438-9676170	KICK BOXING	0011-01-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
23	Nadha el iman	BAHEDDI	7960 AVE TREMOLIERES	ANJOU	438-9209118	KICK BOXING	0025-10-19	365	750	250	\N	\N	\N	\N	\N	\N	\N
24	Isla	BAHOUCHE	11427 RUE ALLARD	MTL	438-2278922	TAE KWON DO	0007-12-21	90	325	300	\N	\N	\N	\N	\N	\N	\N
25	Ilia	BAKHLUCH		MTL	3542166 /  6520700	KICK BOXING	0012-06-21	365	800	800	\N	\N	\N	\N	\N	\N	\N
26	Nassi	BARAKAT	64580 18ÉAVENU	MTL	514-5816019	KICK BOXING	0003-07-20	365	0	300	\N	\N	\N	\N	\N	\N	\N
27	Bria	BARDI	8385BOUL GOUIN EST	MTL	514-4944068	KICK BOXING	0001-01-24	365	850	500	\N	\N	\N	\N	\N	\N	\N
28	Ana	BEGOUG	9792  3E RUE	MTL	438-5226190	KICK BOXING	0001-05-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
29	Youce	BELDJOUDI	9184 RUE MASSE	STL	514-8849159	KICK BOXING	0008-08-23	365	950	340	\N	\N	\N	\N	\N	\N	\N
30	Djame	BELIAOVAR	6393 RUE BELLEAU	MTL	514-9619615	TAE KI BO DO	0005-07-21	365	0	0	\N	\N	\N	\N	\N	\N	\N
31	Ahre	BELKASSEM	1164 BOULEVARD LACORDAIRE	MTL	 /  5538190	KICK BOXING	0012-09-23	365	900	900	\N	\N	\N	\N	\N	\N	\N
32	Baha eddin	BEN MASOUD	7489 RUE MONPETIT	STL	514-7915621	KICK BOXING	0020-11-23	90	340	340	\N	\N	\N	\N	\N	\N	\N
33	Ahme	BEN SALAH	9015 RUE BOISSONNEAULT	MTL	514-	TAE KWON DO	0015-03-18	365	750	250	\N	\N	\N	\N	\N	\N	\N
34	Mohamed amin	BEN SALAH	9015 RUE BOISSONNEAULT	MTL	514-	TAE KWON DO	0015-03-18	365	750	250	\N	\N	\N	\N	\N	\N	\N
35	Amin	BENAMARA	12174 RUE CATELLI	MTL	438-8379538	KICK BOXING	0024-04-24	30	120	120	\N	\N	\N	\N	\N	\N	\N
36	Yasmin	BENHAOUA	8792RUE DARRAS	st leo	7255713 /  5466022	TAE KWON DO	0030-04-15	365	650	0	\N	\N	\N	\N	\N	\N	\N
37	Mohame	BENISSE	4220 RUE D'HIRELLE	MTL	438-9201314	TAE KWON DO	0011-10-18	365	750	300	\N	\N	\N	\N	\N	\N	\N
38	Rafae	BENTO	12341 4É AVENUE	MTL	514-6487502	TAEKIBODO	0008-02-23	365	900	0	\N	\N	\N	\N	\N	\N	\N
39	Kamili	BENYAMINA	6363 BLV ROBER	SL	438-8739437	TAE KI BO DO	0022-11-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
40	Zineb khadij	BENYAMINA	6363 BLV ROBER	SL	438-8739437	TAE KI BO DO	0022-11-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
41	Aksi	BERKANE	6240 RENOIRE A 15	MTL	438-3085488	KICK BOXING	0023-01-25	90	370	370	\N	\N	\N	\N	\N	\N	\N
42	Missa	BERROUCHE	12131 61E AVENUE	MTL	438-9934213	TAE KWON DO	0025-09-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
43	Fah	BETTACH	12005 6E AVENU	MTL	514-5130402	TAE KWON DO	0016-10-22	365	850	800	\N	\N	\N	\N	\N	\N	\N
44	Kevi	BOSSE	8581RUE JEROME-LALEMANT	MTL	438-3896662	KICK BOXING	0012-10-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
45	Yassin	BOUALLA	12535RUEALBER MORIN	MTL	438-4093911	KICK BOXING	0016-01-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
46	Ade	BOUAMEUR	12258ELIE BEUREGARDI	MTL	438-4091295	KICK BOXING	0002-08-24	365	850	450	\N	\N	\N	\N	\N	\N	\N
47	Ayou	BOUDHINA	11677 BOUL GOUMSIN	MTL	514-4970272	KICK BOXING	0013-06-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
48	Ada	BOUJRAD	660 PLACE DISRALI	LAV	438-4948293	TAE KWON DO	0030-09-16	365	650	0	\N	\N	\N	\N	\N	\N	\N
49	Foua	BOUJRAD	660 PLACE DISRALI	LAV	438-4948293	TAE KWON DO	0030-09-16	365	650	200	\N	\N	\N	\N	\N	\N	\N
50	Mohamed rid	BOUJRAD	660 PLACE DISRALI	LAV	438-4948293	TAE KWON DO	0030-09-16	365	650	0	\N	\N	\N	\N	\N	\N	\N
51	Ylia	BOUSLAH	11956 RUE MATTE N8	MTL	438-9901345	KICK BOXING	0019-04-19	365	750	330	\N	\N	\N	\N	\N	\N	\N
52	Ismai	BOUTERFA	8466LEVRAVEL	STL	514-3269221	KICK BOXING	0030-10-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
53	Mala	BOUTOUILI	5800ARTHURE-CHEVIER	MTL	2983282 /  3262268	KICK BOXING	0004-02-23	90	320	320	\N	\N	\N	\N	\N	\N	\N
54	Ismai	BOUZAIR	8363 BOUL CHTEAU NEUF	MTL	438-5242142	TAE  KICK	0015-09-21	365	0	600	\N	\N	\N	\N	\N	\N	\N
55	Zy	BRIZARD	141CAR DU RUISSELET	TERR	514-7039989	TAE KWON DO	0003-02-20	365	750	400	\N	\N	\N	\N	\N	\N	\N
56	Méliss	BRUNELLE	150 RUE DES SORBIERS	MTL	514-4240241	KICK BOXING	0001-11-19	365	750	200	\N	\N	\N	\N	\N	\N	\N
57	Michae	CADEMARTORI	3840 Villeray	MTL	514-9964179	KICK BOXING	0007-01-14	365	500	500	\N	\N	\N	\N	\N	\N	\N
58	Anthon	CALABRETTA	8375 DANIEL DONY	MTL	514-9270133	KICK BOXING	0001-11-22	90	320	0	\N	\N	\N	\N	\N	\N	\N
59	Ahme	CANLI	12300 AV HURTEAU	MTL	438-4020304	KICK BOXING	0025-10-23	90	340	340	\N	\N	\N	\N	\N	\N	\N
60	Ismai	CANLI	12300 AV HURTEAU	MTL	438-4020304	KICK BOXING	0025-10-23	90	340	340	\N	\N	\N	\N	\N	\N	\N
121	Nawfe	FARAJ	8991 GIOVANNI-CABOTO	SL	514-9297663	TAEKIBODO	0001-10-18	365	750	200	\N	\N	\N	\N	\N	\N	\N
61	Antoni	CAPPELLO	3882boul-chomedy	lAV	9445717 /  6619346	KICK BOXING	0005-08-22	90	350	230	\N	\N	\N	\N	\N	\N	\N
62	Madiso	CARRERA	12535 57AVE	MTL	514-473-3584	KICK BOXING	0005-09-21	365	800	800	\N	\N	\N	\N	\N	\N	\N
63	Anthon	CARRERA	12077 70 E AVENUE	MTL	514-7727706	KICK BOXING	0011-04-23	365	850	400	\N	\N	\N	\N	\N	\N	\N
64	Emm	CARRERA	12077 70 E AVENUE	MTL	514-7727706	KICK BOXING	0011-04-23	365	850	400	\N	\N	\N	\N	\N	\N	\N
65	Simo	CHAKUVINGN	12148 AV ALLARD	MN	514-6071872	TAE KWON DO	0009-06-23	365	850	400	\N	\N	\N	\N	\N	\N	\N
66	Hug	CHARPENTIER	6901RUE MARIE-VICTORIN	MTL	336-85291536	KICK BOXING	0009-03-22	90	320	320	\N	\N	\N	\N	\N	\N	\N
67	Raia	CHBICHER	9610 PERRAS	MTL	514-6259052	TAE KWON DO	0013-10-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
68	Sam	CHEKFIAI	4403 RUE DELASE	MTL	438-9236885	KICK BOXING	0016-09-22	90	320	320	\N	\N	\N	\N	\N	\N	\N
69	Abdl-kari	CHEMLAL	11761 BOUL LANGELIER	MTL	438-9932157	KICK BOXING	0009-10-21	90	320	160	\N	\N	\N	\N	\N	\N	\N
70	Aksi	CHERIFI	5592MARCEAL MONETTE	MTL	438-5215927	KICK BOXING	0005-03-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
71	Arri	CHERRAT	12143 AV CHARLES RENARD	MTL	514-972-4949	TAE KWON DO	0012-01-22	365	850	425	\N	\N	\N	\N	\N	\N	\N
72	Massi	CHERRAT	12143 AV CHARLES RENARD	MTL	514-972-4949	TAE KWON DO	0012-01-22	365	850	375	\N	\N	\N	\N	\N	\N	\N
73	Arri	CHERRAT	12143 AV CHARLES RENARD	MTL	514-972-4949	TAE KWON DO	0001-01-22	365	850	450	\N	\N	\N	\N	\N	\N	\N
74	Masi	CHERRAT	12143 AV CHARLES RENARD	MTL	514-972-4949	TAE KWON DO	0001-01-22	365	850	0	\N	\N	\N	\N	\N	\N	\N
75	Ade	CHRIGUI	8570 25É AVENUE N3	MTL	514-3264702	TAE KWON DO	0006-04-19	365	750	750	\N	\N	\N	\N	\N	\N	\N
76	Ram	COIRAZZA	10313RUE DES EGLLANTIERS	MTL	514-8928748	TAE KWON DO	0009-04-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
77	Letizi	COMPARSI	11835 MARCEL.SAVA	MTL	8819081 /  8084137	KICK BOXING	0006-11-05	90	160	160	\N	\N	\N	\N	\N	\N	\N
78	Wali	DAKTOURI	8483 HUGUET-LATOUR	MTL	438-9925920	KICK BOXING	0006-11-23	365	950	950	\N	\N	\N	\N	\N	\N	\N
79	Claud	DAKVIN	5528 CHARLEVOI	MTL	514-6089539	KICK BOXING	0026-08-20	365	750	155	\N	\N	\N	\N	\N	\N	\N
80	Giann	DE VIVO	11614 EDITH-SEREI	MTL	514-7071765	TAE KWON DO	0016-08-23	365	900	900	\N	\N	\N	\N	\N	\N	\N
81	Fabric	DECOSSE	8970 AV DE BRETAGE	MTL	514-5865522	KICK BOXING	0012-01-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
82	Emir hamz	DEMIRCI	12701-3LONGELIER	MN	438-8268707	TAE KWON DO	0002-08-23	365	850	400	\N	\N	\N	\N	\N	\N	\N
83	Yigit ef	DEMIRCI	12701-3LONGELIER	MN	438-8268707	KICK BOXING	0002-08-23	365	850	400	\N	\N	\N	\N	\N	\N	\N
84	Samue	DERIENZO	5160 LEO OUELETTE	SLO	7213809 /  4388280158	TAE KWON DO	0022-10-19	365	750	350	\N	\N	\N	\N	\N	\N	\N
85	Victori	DERIENZO	5160 LEO OUELETTE	SLO	7213809 /  4388280158	TAE KWON DO	0010-02-20	365	500	0	\N	\N	\N	\N	\N	\N	\N
86	Pascal	DES SANTOS	12210JEAN-YVES BIGRAS	MTL	514-8380444	KICK BOXING	0008-08-23	365	900	895	\N	\N	\N	\N	\N	\N	\N
87	D	DI PALOA	7141rue Merlot	LAV	450-936-1241	TAE KWON DO	0021-08-21	365	800	800	\N	\N	\N	\N	\N	\N	\N
88	Linn	DI PALOA	7141 rue Merlot	LAV	450-936-1241	TAE KWON DO	0021-08-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
89	Valentin	DI PISA	12592  48É AVNUE	MTL	514-4634540	TAE KWON DO	0001-11-23	365	950	475	\N	\N	\N	\N	\N	\N	\N
90	Emma-le	DIGIOVANNI	11901 PAUL EMILE LAMARCHE	MTL	4945152 /  8084277	TAE KWONDO	0005-05-10	365	550	550	\N	\N	\N	\N	\N	\N	\N
91	Giovann	DIGIOVANNI	11901 PAUL EMILE LAMARCHE	MTL	4945152 /  8084277	TAE KWONDO	0005-05-10	365	550	550	\N	\N	\N	\N	\N	\N	\N
92	Michae	DITILO	3210CHAGALL	LAVAL	514-7122257	TAEJITSU	0002-05-23	365	900	0	\N	\N	\N	\N	\N	\N	\N
93	Junior dibri	DJAMBOU	12037 55EME AVE	MTL	514-8623894	TAE KWON DO	0001-09-17	365	650	500	\N	\N	\N	\N	\N	\N	\N
94	Leonel yva	DJAMBOU	12037 55EME AVE	MTL	514-8623894	TAE KWON DO	0001-09-17	365	650	0	\N	\N	\N	\N	\N	\N	\N
95	Muriell	DJAMBOU	12037 55EME AVE	MTL	514-8623894	TAE KWON DO	0001-09-17	365	650	500	\N	\N	\N	\N	\N	\N	\N
96	Ada	DJEBLI	5222 BOUL DES GRANDS PRAIRIS	STL	3262758 /  5865509	TAE KWON DO	0024-11-24	365	950	350	\N	\N	\N	\N	\N	\N	\N
97	Ry	DJEBLI	5222 BOUL DES GRANDS PRAIRIS	STL	3262758 /  5865509	TAE KWON DO	0005-01-23	365	850	800	\N	\N	\N	\N	\N	\N	\N
98	Nicola	DOMIANOVICH	12065 AV COPOMIS	MTL	514-5769397	KICK BOXING	0010-07-23	365	900	200	\N	\N	\N	\N	\N	\N	\N
99	Fare	DRIDI	4487RUE PLINGUET	SL	438-9923690	TAE KWON DO	0015-09-21	365	800	800	\N	\N	\N	\N	\N	\N	\N
100	Jessic	DU ROSEAU	43 rue Gilles Boisbriand	MTL	4333320 /  830-5843	TAE KWON DO	0031-12-04	365	800	800	\N	\N	\N	\N	\N	\N	\N
101	Fede	DURSO	9267LAUIS DESSUNES	MTL	514-8244158	KICK BOXING	0018-03-22	90	320	320	\N	\N	\N	\N	\N	\N	\N
102	Janett	EL BADAOUI	6362 RUE ARTHUR-CHVIER	MTL	438-8781135	TAE KWON DO	0018-09-20	365	750	250	\N	\N	\N	\N	\N	\N	\N
103	Raya	EL BADAOUI	6362 RUE ARTHUR-CHVIER	MTL	438-8781135	TAE KWON DO	0018-09-20	365	750	250	\N	\N	\N	\N	\N	\N	\N
104	Yasmin	EL HABIBI	8235 PIERRE MOQUET	MTL	514-7708987	TAE KWON DO	0016-09-21	365	800	320	\N	\N	\N	\N	\N	\N	\N
105	Yousse	EL HABIBI	8235 PIERRE MOQUET	MTL	514-7708987	TAE KWON DO	0016-09-21	365	800	320	\N	\N	\N	\N	\N	\N	\N
106	Mohamed amin	EL HADJ MOUSSA	6943 RUE DETOMAINE	MTL	514-8035620	KICK BOXING	0019-12-17	365	750	400	\N	\N	\N	\N	\N	\N	\N
107	Nadi	EL HADJ MOUSSA	6220 RUE DE LA VILLANELLE	MTL	514-9245695	KICK BOXING	0015-06-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
108	Kari	EL JUNDI	2458 RUE SAINT-ZOTIQUE	MTL	438-8884436	KICK BOXING	0005-11-21	365	850	850	\N	\N	\N	\N	\N	\N	\N
109	Salm	EL MAATAOUI	9161AV EMILE LEGAULT	MTL	514-9742824	KICK BOXING	0023-11-22	90	320	200	\N	\N	\N	\N	\N	\N	\N
110	Ibrahi	ELASSAAD	11830 LOUIS-SICOTTE	MTL	438-4510311	KICK BOXING	0004-08-24	90	350	350	\N	\N	\N	\N	\N	\N	\N
111	Moaa	ELBANNAOUI	12312 6É AVENU	MTL	514-3276902	TAE KI BO DO	0005-02-20	365	850	200	\N	\N	\N	\N	\N	\N	\N
112	Ada	ELKHARRAZ	12002BOUL LACORDAIRE	MTL	438-9783096	KICK BOXING	0020-08-23	90	320	320	\N	\N	\N	\N	\N	\N	\N
113	Zine	ELMELHAOUI	6945 AV CHAMPCHEVRIER	ANJOU	514-7185823	TAE KWON DO	0023-09-22	365	820	820	\N	\N	\N	\N	\N	\N	\N
114	Ay	ELOUELLAD	6815RUE ETIENNE BOUCHARD	MTL	514-5182765	TAE KWON DO	0001-03-20	365	750	150	\N	\N	\N	\N	\N	\N	\N
115	Ilyas	ELOUELLAD	6815RUE ETIENNE BOUCHARD	MTL	514-5182765	TAE KWON DO	0001-03-20	365	750	150	\N	\N	\N	\N	\N	\N	\N
116	Filipp	ENEA	12062PL.PIERRE BLANCHET	MTL	8811562 /  7307000	TAE KWONDO	0008-08-22	365	850	750	\N	\N	\N	\N	\N	\N	\N
117	Ami	ESSAFI	11514 AV BOXUET	MTL	514-5501882	KICK BOXING	0010-11-22	365	900	900	\N	\N	\N	\N	\N	\N	\N
118	Tra	EUGENE	11529 RUE ALLARD	MTL	514-8273378	TAE KWON DO	0029-03-25	365	0	0	\N	\N	\N	\N	\N	\N	\N
119	Kimi	EVRAIRE-MBOYO	1891-N3 RUEBOSSVET	MTL	438-4975147	TAE KWON DO	0020-06-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
120	Male	FAJRAOUI	11559 AV BRUNET	MTL	514-3275013	KICK BOXING	0020-08-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
122	Chad	FARAZDAG	12369 LAPIERRE 305	MN	514-2450083	TAE KWON DO	0001-10-20	365	750	300	\N	\N	\N	\N	\N	\N	\N
123	Sam	FARAZDAG	12369 LAPIERRE 305	MN	514-2450083	TAE KWON DO	0001-10-20	365	750	300	\N	\N	\N	\N	\N	\N	\N
124	Ayman	FARESS	11611 LACORDAIRE N3	MTL	438-9365350	KICK BOXING	0003-10-18	365	750	250	\N	\N	\N	\N	\N	\N	\N
125	Ala	FATTAH	4937 AMOS	MTL	438-8383994	TAE KWON DO	0006-02-19	365	750	500	\N	\N	\N	\N	\N	\N	\N
126	Ine	FATTAH	4937 AMOS	MTL	438-8383994	TAE KWON DO	0006-02-19	365	750	500	\N	\N	\N	\N	\N	\N	\N
127	Ismae	FATTAH	4937 AMOS	MTL	438-8383994	TAE KWON DO	0006-02-19	365	750	480	\N	\N	\N	\N	\N	\N	\N
128	Yanni	FEKHAR	7881AV JEAN VINCENT	MTL	514-6046810	KICK BOXING	0026-02-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
129	Pasgualin	FRAGOLA	12085 CHARKS DIONNE	MTL	514-9491952	KICK BOXING	0001-11-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
130	Anthon	FRAGOLA	12085 CHARKS DIONNE	MTL	514-9491952	KICK BOXING	0001-11-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
131	Nour el houd	FREJ	9072 RUE DEL,AIGLAN	st leo	514-2508942	TAE KWON DO	0028-09-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
132	Carmill	FRESCO	7380RAFPHAEL-BELLMARE	MTL	514-2421444	KICK BOXING	0031-03-25	30	120	120	\N	\N	\N	\N	\N	\N	\N
133	Soni	FRESCO	7380RAFPHAEL-BELLMARE	MTL	514-2421444	TAE KWON DO	0009-03-23	90	0	0	\N	\N	\N	\N	\N	\N	\N
134	Noh	FRIKHA	12269 RUE MARCELL GOVEAUD	MTL	514-2094839	KICK BOXING	0010-11-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
135	Souleiman	GADIRI	6641 RUEFRONCOIS BOIVIN	MTL	514-9957313	KICK BOXING	0015-11-23	365	900	400	\N	\N	\N	\N	\N	\N	\N
136	Ana	GADIRI	6641 RUEFRONCOIS BOIVIN	MTL	514-5617313	KICK BOXING	0018-02-23	90	350	350	\N	\N	\N	\N	\N	\N	\N
137	Yacin	GAOUA	1617RUEPERCEVAL	REP	438-9901866	TAE KWON DO	0021-09-23	365	850	500	\N	\N	\N	\N	\N	\N	\N
138	Ada	GAROVALO	12290 J.A.ROULEAU	RDP	514-4944967	TAE KWON DO	0015-11-18	365	0	600	\N	\N	\N	\N	\N	\N	\N
139	Yassin	GARRAOUI	8262 RUE LEORD	SAT	514-4414609	TAE KWON DO	0022-09-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
140	Mohame	GARRAOUI	8262 RUE LEVRARD	SAT	514-4414609	TAE KWON DO	0001-09-24	365	850	850	\N	\N	\N	\N	\N	\N	\N
141	Vite	GERMAN	9730 BOULPERRAS	MTL	514-4314401	KICK BOXING	0014-06-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
142	Don gerso	GERMINAL	9085 shabarouke est    n1	MTL	438-2330799	KICK BOXING	0001-09-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
143	Al	GHAIS	8332 AV NICOLAS LEBLANC	MTL	438-5078096	TAE KWON DO	0021-06-24	365	950	450	\N	\N	\N	\N	\N	\N	\N
144	Mari	GHARIANI	7515 MARIANA-JODIN	MTL	438-4016516	TAE KWON DO	0023-09-19	365	660	100	\N	\N	\N	\N	\N	\N	\N
145	Sirin	GHARIANI	7515 MARIANA-JODIN	MTL	438-4016516	TAE KWON DO	0023-09-19	365	660	100	\N	\N	\N	\N	\N	\N	\N
146	Domenic	GIUNTA			514-3286991	TAE KWON DO	0032-03-07	365	620	100	\N	\N	\N	\N	\N	\N	\N
147	Santiag	GRISELDA	12050 5E AVENUE	MTL	514-560-0087	KICK BOXING	0001-01-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
148	Mohamed amin	GUEMAUNI	8080RUE CHTAIGNIERS	LAV	438-4092724	KICK BOXING	0015-02-23	365	890	890	\N	\N	\N	\N	\N	\N	\N
149	Wissam jona	GUEMMAH                           WISSAM	12093 AV PIERRE BLANCHET	MTL	2974734 /  4389924750	TAE KWON DO	0021-09-24	365	850	850	\N	\N	\N	\N	\N	\N	\N
150	Samir	HAGUI	12100 BLV LACOR	MN	514-6638519	KICK BOXING	0014-01-23	365	900	300	\N	\N	\N	\N	\N	\N	\N
151	Ahme	HAMADENI	4669VALCOURT	SL	514-6492388	TAE KWON DO	0020-11-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
152	Myria	HAMADENI	4669VALCOURT	SL	514-6492388	TAE KWON DO	0010-05-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
153	Ait ahme	HAMID ABDELHAC	11740 FERNAND GAUTHIER 6	MTL	514-882-8080	KICK BOXING	0009-09-22	365	850	500	\N	\N	\N	\N	\N	\N	\N
154	Dal	HAMMOUCHE	9710 3É RUE	MTL	438-4904983	KICK BOXING	0016-10-23	90	340	340	\N	\N	\N	\N	\N	\N	\N
155	Mohame	HANINE	11991 AVE LAPENE	MTL	514-3295144	TAE KWON DO	0014-04-19	365	750	300	\N	\N	\N	\N	\N	\N	\N
156	Eba	HASSAN	8477DECLUY-	STL	514-4585541	TAE KWON DO	0001-09-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
157	Zhacri	HAYA	2685RUE DES FAUCONS	MTL	514-2662695	KICK BOXING	0010-06-23	365	900	950	\N	\N	\N	\N	\N	\N	\N
158	Maria	HIJAZI	6155BOUL LEGO N2	MTL	514-4312200	TAE KWON DO	0015-10-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
159	Zeina	HIJAZI	6155BOUL LEGO N2	MTL	514-4312200	TAE KWON DO	0015-10-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
160	Fatim	HIJAZI	6155BOUL LEGO N2	MTL	514-4312200	TAE KWON DO	0015-10-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
161	Ada	HLEL	11426 Avenue Alfred	MNR	514-827-5578	TAE KWON DO	0006-01-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
162	Neil	HLEL	11426 Avenue Alfred	MNR	514-827-5578	TAE KWON DO	0025-09-22	365	850	0	\N	\N	\N	\N	\N	\N	\N
163	Tra	HUNG VAN	12018 BOUL ARMAND BAMBARDIER	MTL	514-8828200	KICK BOXING	0028-08-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
164	Ann	IACONO	8235 NANETS	st leo	514-5020377	KICK BOXING	0001-08-24	365	925	925	\N	\N	\N	\N	\N	\N	\N
165	Antoin	IACONO	12120PIERRE-BAILLARGEAN	MTL	9777235 /  5853294	TAE KWON DO	0015-10-24	90	350	350	\N	\N	\N	\N	\N	\N	\N
166	Luc	IAZURLO	3426AVE DESAMBASSADEUR	LAV	514-5919628	TAE KWON DO	0016-06-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
167	Carl	IOANNONE	12114PIERRE BLANCHET	MTL	6485699 /  2125344	KICK BOXING	0010-10-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
168	Othman	JAAFOUR	11461 AVE ROME	MN	514-3272193	KICK BOXING	0018-09-21	365	800	400	\N	\N	\N	\N	\N	\N	\N
169	Wassi	JELIDI	7842AVE PIGEON	MTL	438-8603048	TAE KWON DO	0014-10-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
170	Ada	JULID	693 RUE BLAISE	MTL	514-7587963	KICK BOXING	0028-03-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
171	Mohame	KAHLI	8777RUE NARBONNE	MTL	438-8366194	TAE KWON DO	0009-08-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
172	Hib	KAHLI	8777RUE NARBONNE	MTL	438-8366194	TAE KWON DO	0009-08-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
173	Ale	KAREL	102DULUTH EST	MTL	514-6214747	BOXE	0002-06-23	30	120	120	\N	\N	\N	\N	\N	\N	\N
174	Ahmed ram	KETTOUCHE		MTL	514-7754416	KICK BOXING	0010-04-19	365	750	300	\N	\N	\N	\N	\N	\N	\N
175	Moua	KHAIR	7478RUE JOSEP-MORIN	MTL	438-6805810	KICK BOXING	0001-03-24	90	320	320	\N	\N	\N	\N	\N	\N	\N
176	Al	KHALAF	8840 MAURICE DUPLISSIS  8	MTL	4946634 /  9197766	KICK BOXING	0008-10-18	365	750	500	\N	\N	\N	\N	\N	\N	\N
177	Mohame	KHALAF	8840 MAURICE DUPLISSIS  8	MTL	4946634 /  9197766	KICK BOXING	0008-10-18	365	750	250	\N	\N	\N	\N	\N	\N	\N
178	Oma	KHLALBEL	4015SAINT-JOSEPH	MTL	438-7771015	KICK BOXING	0022-01-20	365	750	170	\N	\N	\N	\N	\N	\N	\N
179	Beyre	KOBBI	12636 AV CORBEIL	MTL	514-8922565	KICK BOXING	0023-10-22	365	900	900	\N	\N	\N	\N	\N	\N	\N
180	Bouberagi	LAETICIA	12047 ELIE-BEAUREGARD	MTL	438-9234208	KICK BOXING	0011-05-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
181	Lin	LAHLOLI	12454 BO.ROLLAND	MTL	5854313 /  2984039	TAE KWON DO	0002-11-22	365	850	800	\N	\N	\N	\N	\N	\N	\N
182	Lili	LAHLOLI	12454 BO.ROLLAND	MTL	4414542 /  2984039	TAE KWON DO	0010-11-22	365	800	500	\N	\N	\N	\N	\N	\N	\N
183	Lorenz	LAMOLINARA	3430 N-301 PIE IX	LAV	514-2428992	TAE KWON DO	0001-12-23	365	950	950	\N	\N	\N	\N	\N	\N	\N
184	Fadi skanda	LARIBI	12152 5É AVENU	MTL	514-466-9660	BOXE	0014-06-21	365	750	750	\N	\N	\N	\N	\N	\N	\N
185	Ja	LARIBI	12152 5É AVENU	MTL	514-4674675	KICK BOXING	0014-01-19	365	750	350	\N	\N	\N	\N	\N	\N	\N
186	Aureli	LATELLA	8930 LAVERDIERE	st leo	514-9999666	KICK BOXING	0024-09-07	365	450	450	\N	\N	\N	\N	\N	\N	\N
187	Bogi	LOICLE	11830LANGELIER	MN	438-5261170	KICK BOXING	0020-06-23	365	850	520	\N	\N	\N	\N	\N	\N	\N
188	Jenna	MAHDI	6020 AV DES ANGEVINS	ANJOU	514-9425789	TAE KWON DO	0002-12-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
189	Akra	MAKHLOUF	1061 rue noiseux	MTL	514-2344301	TAE KWON DO	0008-02-24	365	900	0	\N	\N	\N	\N	\N	\N	\N
190	Christia	MARINELLI	3830 MAJISTAT	LAV	514-9832100	TAE KWON DO	0016-02-22	90	325	325	\N	\N	\N	\N	\N	\N	\N
191	Dann	MARTINS	12215 ALEXIS CORROLL	MTL	514-889599	BOXE	0011-08-21	365	950	900	\N	\N	\N	\N	\N	\N	\N
192	Mali	MASNOUR TUIZO	12740	MTL	514-8721693	KICK BOXING	0012-01-20	365	750	105	\N	\N	\N	\N	\N	\N	\N
193	Fabi	MATAS	12550 58 AVENU	MTL	514-8851442	KICK BOXING	0019-09-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
194	Arew	MAYOUF	12-12055 BOUL ARMAND BOMBA	MTL	514-8032633	TAE KWON DO	0005-11-23	365	850	800	\N	\N	\N	\N	\N	\N	\N
195	Mohamed yacou	MAYOUF	12-12055 BOUL ARMAND BOMBA	MTL	514-8032633	TAE KWON DO	0005-11-23	365	850	700	\N	\N	\N	\N	\N	\N	\N
196	Moamed luo	MAYOUF	12-12055 BOUL ARMAND BOMBA	MTL	514-8032633	TAE KWON DO	0005-11-23	365	800	0	\N	\N	\N	\N	\N	\N	\N
197	Mohame	MAYOUF	12-12055 BOUL ARMAND BOMBA	MTL	514-8032633	TAE KWON DO	0005-11-23	365	400	0	\N	\N	\N	\N	\N	\N	\N
198	Raya	MAZOUZ	6361 RUE NORMANDIE	MNR	3212515 /  5469141	TAE KWON DO	0001-10-18	365	850	500	\N	\N	\N	\N	\N	\N	\N
199	Syrin	MAZOUZ	6361 RUE NORMANDIE	MNR	3212515 /  5469141	TAE KWON DO	0001-10-18	365	850	500	\N	\N	\N	\N	\N	\N	\N
200	Pedr	MCKELVEY		MTL	514-4654054	KICK BOXING	0012-04-07	90	160	160	\N	\N	\N	\N	\N	\N	\N
201	Jess	MECCIA	6014 MAURICE-DEPESSIS	MTL	514-9092368	TAE KWON DO	0013-10-23	365	900	300	\N	\N	\N	\N	\N	\N	\N
202	Mustaph	MERAD	12189 AV GILBERT-BARBIER	MTL	438-8711034	TAE KWON DO	0010-01-23	365	850	400	\N	\N	\N	\N	\N	\N	\N
203	Eanz	MERAD	12189 AV GILBERT-BARBIER	MTL	9681034 /  8711034	TAE KWON DO	0015-03-22	365	750	750	\N	\N	\N	\N	\N	\N	\N
204	Emm	MERAD	12189 AV GILBERT-BARBIER	MTL	9681034 /  8711034	TAE KWON DO	0015-03-22	365	750	750	\N	\N	\N	\N	\N	\N	\N
205	Mohamed djame	MERZOUG	12144 RUE ALLAND	MTL	8151492 /  4620019	TAE KWON DO	0005-10-22	365	850	300	\N	\N	\N	\N	\N	\N	\N
206	Ada	MEZARI	4515 RUE BOYA	SL	514-3460906	TAE KWON DO	0019-10-21	365	800	800	\N	\N	\N	\N	\N	\N	\N
207	Abdalla	MEZHOUD	5651JOSEPHE DUFRIME	M NO	8859260 /  3899160	TAE KWON DO	0029-03-19	365	750	750	\N	\N	\N	\N	\N	\N	\N
208	Yace	MEZHOUD	5651JOSEPHE DUFRIME	M NO	8859260 /  3899160	TAE KWON DO	0002-10-23	365	0	0	\N	\N	\N	\N	\N	\N	\N
209	Lu	MINH TUAN	12435 A 4É	MTL	514-7131502	TAE KWON DO	0005-11-22	365	850	450	\N	\N	\N	\N	\N	\N	\N
210	Jonatha	MIRARCHI	331 KINDERSLEY	MTL	7393270 /  916-1162	TAE KWON DO	0025-09-13	365	804.83	804.83	\N	\N	\N	\N	\N	\N	\N
211	Aima	MOKKADEM	11860AV BRUNET	MTL	438-4082018	KICK BOXING	0008-08-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
212	Mohamed sam	MOKRI	8010AVE JOLIOT CURIE	MTL	514-8819231	TAE KWON DO	0001-05-22	365	800	800	\N	\N	\N	\N	\N	\N	\N
213	Neill	MOKRI	8010AVE JOLIOT CURIE	MTL	514-8819231	TAE KWON DO	0001-05-22	365	800	800	\N	\N	\N	\N	\N	\N	\N
214	Sebasti	MORIN	6901RUE MARIE-VICTORIN	MTL	438-9858523	KICK BOXING	0009-03-22	90	320	100	\N	\N	\N	\N	\N	\N	\N
215	Gabrie	MORIN-LAPORTE	7474MAURICE DEPLESSISE	MTL	514-7010348	KICK BOXING	0025-11-19	365	862	100	\N	\N	\N	\N	\N	\N	\N
216	Brahi	MOUATIF	RUE BLEAU 1003	LAV	514-2599634	KICK BOXING	0008-02-17	365	750	200	\N	\N	\N	\N	\N	\N	\N
217	Yasmin	MOUATIF	RUE BLEAU 1003	LAV	450-9368185	KICK BOXING	0005-01-19	365	750	700	\N	\N	\N	\N	\N	\N	\N
218	Yacin	MOUSSAOUI	7020 RUE JARRY EST	MTL	438-5402686	KICK BOXING	0023-05-23	90	320	320	\N	\N	\N	\N	\N	\N	\N
219	Raya	MOUZOUNI	11515 RUE LANGELIER AP4	MTL	438-8210188	KICK BOXING	0009-01-24	365	950	400	\N	\N	\N	\N	\N	\N	\N
220	Achera	NAIT DAOUD	8841AVEUE GEORGES	ENJ	514-7796114	TAE KWON DO	0009-10-21	90	320	200	\N	\N	\N	\N	\N	\N	\N
221	Abdelmaji	NEGGAZI	11529 AV LAURIER	MTL	514-6192017	KICK BOXING	0023-12-23	90	340	340	\N	\N	\N	\N	\N	\N	\N
222	Jahne	NEPTUNE	12245 56É AVENU	RDP	514-8355439	TAE KWON DO	0030-10-24	365	850	700	\N	\N	\N	\N	\N	\N	\N
223	Tyra	NEPTUNE	12245 56É AVENU	RDP	514-8355439	TAE KWON DO	0030-10-24	365	850	700	\N	\N	\N	\N	\N	\N	\N
224	Kenn	NGUYEN	12577GERTRUDE GENDREAU	MTL	514-4738438	TAE KWON DO	0009-11-17	365	750	300	\N	\N	\N	\N	\N	\N	\N
225	Vincen	NGUYEN	11725 ALLARD	MTL	514-8368850	TAE KWON DO	0028-08-23	365	900	900	\N	\N	\N	\N	\N	\N	\N
226	L	NICK	12445 4É AVE	MTL	514-2338809	TAE KWON DO	0015-10-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
227	Novee	NOROZI ALLAM	24É AVE 12607	MTL	514-5789102	KICK BOXING	0028-02-22	365	850	300	\N	\N	\N	\N	\N	\N	\N
228	Oha	OCAMP	7500ANDRE-AMPERE	MTL	438-7776527	KICK BOXING	0006-11-23	90	340	340	\N	\N	\N	\N	\N	\N	\N
229	Alejandr	OCVIL-GALVEZ	7800 Maurice Duplessis	MTL	438-8873282	TAE KWON DO	0002-01-24	365	900	900	\N	\N	\N	\N	\N	\N	\N
230	Tatian	OSMAN	12396 ELIE BEOUREGARD	MTL	514-2674723	KICK BOXING	0014-08-23	90	320	320	\N	\N	\N	\N	\N	\N	\N
231		OTHMAN ALAOUI			514-	KICK BOXING	0012-07-23	180	0	0	\N	\N	\N	\N	\N	\N	\N
232	Massi	OUHEB	6090 REMOIR	MTL	438-7652929	KICK BOXING	0015-04-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
233	Anis elya	OULD ALI	5978RUEPORTEL	LAV	514-9630211	KICK BOXING	0012-01-23	90	320	0	\N	\N	\N	\N	\N	\N	\N
234	Cerin	OUSSADA	10991L,ACHEVEQUE	MTL	514-5887467	KICK BOXING	0015-01-20	365	750	200	\N	\N	\N	\N	\N	\N	\N
235	Emm	PELUSO	12164 REU ANNA-PAQUIN	MTL	514-8810345	KICK BOXING	0011-03-24	90	320	320	\N	\N	\N	\N	\N	\N	\N
236	Ann-sara	PERDRIEL	12077 6È AVENUE	MTL	3189315 /  4472966	BOXE	0019-09-23	90	325	325	\N	\N	\N	\N	\N	\N	\N
237	Angell	PHAN	12480 54ÉAVENU	MTL	438-7251982	TAE KWON DO	0016-02-20	365	750	0	\N	\N	\N	\N	\N	\N	\N
238	Rick	PHAN	12480 54ÉAVENU	MTL	438-7251982	TAE KWON DO	0016-02-20	365	750	500	\N	\N	\N	\N	\N	\N	\N
239	Jayle	PHANORD	7525 BV PERRAS	MTL	438-8841542	TAE KWON DO	0008-10-18	365	750	200	\N	\N	\N	\N	\N	\N	\N
240	Marle	PHANORD	6533ROI RENEÉ	ANJ	514-7915839	TAE KWON DO	0020-08-19	365	750	450	\N	\N	\N	\N	\N	\N	\N
241	Cipeiiet	PHILIPPE	9676GALERIES D,ANJOU	ANJOU	514-5707980	KICK BOXING	0020-01-20	365	750	750	\N	\N	\N	\N	\N	\N	\N
242	Djahmee	PHILOSCA	1250 PHILIPPE-LEBON	MTL	514-297-3759	TAE KWON DO	0015-07-21	90	320	100	\N	\N	\N	\N	\N	\N	\N
243	Damian	PICCOLI	9247LAUIS LUMMIER	MTL	438-4920873	TAE KWON DO	0003-02-20	365	750	750	\N	\N	\N	\N	\N	\N	\N
244	Giordan	PICCOLI	9247LAUIS LUMMIER	MTL	438-4920873	TAE KWON DO	0003-02-20	365	750	750	\N	\N	\N	\N	\N	\N	\N
245	St-for	PIERRE	9151BOUL DES GALERIES  806	ANJOU	514-5507560	TAE KI BO DO	0005-01-23	90	350	350	\N	\N	\N	\N	\N	\N	\N
246	Cristia	PINTO	12050ARMAND BOMBADIER	MTL	514-7558804	JUJITSU	0004-10-23	365	950	950	\N	\N	\N	\N	\N	\N	\N
247	Fabric	POULIN	6472 AV PERDDERO	MTL	418-5585106	KICK BOXING	0009-10-23	90	320	320	\N	\N	\N	\N	\N	\N	\N
248	Ilia	RACHID	4606BDGIN EST	MTL	514-5134298	KICK BOXING	0027-01-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
249	Michae	RAFFAELE	8080PHILIPPE DE CARION	MTL	6431832 /  7181832	TAE KWON DO	0025-11-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
250	Kaei aiss	RAMY	10178 KARL LEVEQUE	MTL	438-464-2108	KICK BOXING	0011-09-23	365	900	300	\N	\N	\N	\N	\N	\N	\N
251	Fira	REKDAL	12550 EDMONT	MTL	514-4517756	TAE KWON DO	0009-09-24	365	800	500	\N	\N	\N	\N	\N	\N	\N
252	Oma	REKDAL	12550 EDMONT	MTL	514-4517756	TAE KWON DO	0009-09-24	365	800	500	\N	\N	\N	\N	\N	\N	\N
253	Abde rm	SAAD-M	11792 AV NICOLAS	MTL	514-2550319	KICK BOXING	0024-11-23	90	340	340	\N	\N	\N	\N	\N	\N	\N
254	Wassi	SAAD-M	11792 AV NICOLAS	MTL	514-2550319	KICK BOXING	0024-11-23	90	340	340	\N	\N	\N	\N	\N	\N	\N
255	Marw	SAHAL	6390 LEGER   N9	MTL	438-4023350	TAE KWON DO	0012-12-18	365	750	150	\N	\N	\N	\N	\N	\N	\N
256	Mohamed amin	SAHAL	6390 LEGER   N9	MTL	438-4023350	TAE KWON DO	0012-12-18	365	750	150	\N	\N	\N	\N	\N	\N	\N
257	Amin	SAHIL	7705 RUE SHERBROOKE. EST	MTL	514-889-1986	TAE KI BO DO	0009-04-19	365	750	0	\N	\N	\N	\N	\N	\N	\N
258	Ismae	SAHLI	7705 RUE SHERBROOKE.EST	MTL	514-889-1986	TAE KWON DO	0009-04-19	365	750	500	\N	\N	\N	\N	\N	\N	\N
259	Ahme	SAIDI	5083 RUE DECASTILLE	MTL	514-6912512	TAE KWON DO	0025-08-21	365	750	750	\N	\N	\N	\N	\N	\N	\N
260	Wassi	SALHI	11720FERNAND GAUTHIER	MTL	514-5185005	KICK BOXING	0004-05-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
261	Wassi	SALHI	11720FERNAND GAUTHIER	MTL	514-5185005	KICK BOXING	0004-05-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
262	Wassi	SALHI	11720FERNAND GAUTHIER	MTL	514-5185005	KICK BOXING	0004-05-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
263	Eberso	SALVANT	12305 54E   AVENU	MTL	9833697 /  6489313	TAE KWON DO	0010-03-23	365	850	0	\N	\N	\N	\N	\N	\N	\N
264	Eberss	SALVANT	12305 54E   AVENU	MTL	9833697 /  6489313	TAE KWON DO	0005-09-18	365	760	760	\N	\N	\N	\N	\N	\N	\N
265	Lucely	SALVANT	12305 54E   AVENU	MTL	9833697 /  6489313	TAE KWON DO	0024-01-18	365	750	500	\N	\N	\N	\N	\N	\N	\N
266	Marc	SANELLI	5621 AUBIN	MTL	3238034 /  8954100	KICK BOXING	0015-06-10	365	0	0	\N	\N	\N	\N	\N	\N	\N
267	Alessi	SANTONE	12255 42É AVENU	MTL	514-4975694	TAE KWON DO	0017-05-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
268	Vaness	SARACINO	6619BOUL LEVESQUE	LAV	514-7121065	KICK BOXING	0021-10-21	365	0	0	\N	\N	\N	\N	\N	\N	\N
269	Mickae	SERGERIE	12732 26E AVENU	MTL	8130205 /  8272981	TAE KWON DO	0009-06-19	365	750	400	\N	\N	\N	\N	\N	\N	\N
270	Oma	SHARIF			514-	KICK BOXING	0001-09-21	90	320	200	\N	\N	\N	\N	\N	\N	\N
271	Douny	SHEIKH	12740 27É AVENU	MTL	514-2983099	TAE KWON DO	0025-05-21	365	750	0	\N	\N	\N	\N	\N	\N	\N
272	Zachari	SHEIKH	12740 27É AVENU	MTL	514-2983099	TAE KWON DO	0012-01-18	365	750	0	\N	\N	\N	\N	\N	\N	\N
273	Hassa	SHEIKH	12250 41È AVE	MTL	514-7812451	KICK BOXING	0002-05-22	365	850	220	\N	\N	\N	\N	\N	\N	\N
274	Samue	SILVA	12090BOUL-RDP	MTL	514-8028776	KICK BOXING	0002-06-23	365	850	400	\N	\N	\N	\N	\N	\N	\N
275	Adolf	SIMEONE	10705 DRAPEAU	MTL	514-8986556	TAE KI BO DO	0014-02-24	90	400	400	\N	\N	\N	\N	\N	\N	\N
276	Emili	SIMEONE	10705 DRAPEAU	MTL	514-8986556	TAE KI BO DO	0014-02-24	90	400	400	\N	\N	\N	\N	\N	\N	\N
277	Soni	SIMEONE	10705 DRAPEAU	MTL	514-8986556	TAE KI BO DO	0020-03-24	90	400	400	\N	\N	\N	\N	\N	\N	\N
278	Davi	SOGORE	12379AVENU CLEMENT-ADER	MTL	514-5619044	KICK BOXING	0012-10-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
279	Mate	SPERANO	8658 TIBIDEAU	MTL	514-9735865	KICK BOXING	0001-05-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
280	Hussei	TAHA	10-6950 AVENUDE CHAMPCHERRIER	ANJOU	514-7463090	KICK BOXING	0010-01-20	365	750	370	\N	\N	\N	\N	\N	\N	\N
281	Anger	TAHANI	3869 MONSELET	MTL	438-8601212	TAE KWON DO	0022-11-23	365	950	300	\N	\N	\N	\N	\N	\N	\N
282	Rani	TAHIRI	12530AV PAUL-DUFAULT	MTL	514-7223810	TAE KWON DO	0020-09-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
283	Yacin	TAHIRI	12530AV PAUL-DUFAULT	MTL	514-7223810	TAE KWON DO	0020-09-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
284	An	TAHIRI	12530AV PAUL-DUFAULT	MTL	514-7223810	TAE KWON DO	0020-09-22	365	700	700	\N	\N	\N	\N	\N	\N	\N
285	Jose	TALBOT			514-	KICK BOXING	0001-05-23	90	325	325	\N	\N	\N	\N	\N	\N	\N
286	Achraf rost	TOUIL	11517 AV ALLARD	MTL	438-3892750	KICK BOXING	0011-12-22	365	850	0	\N	\N	\N	\N	\N	\N	\N
287	Cioban	TUDOR	9212BOUL-GOUIN	MTL	514-6055872	KICK BOXING	0002-03-22	90	330	330	\N	\N	\N	\N	\N	\N	\N
288	Angel mirell	URBAN	3-6100 MAURICE-DUPLESSIS	MN	438-9983455	TAE KWON DO	0023-11-23	30	100	100	\N	\N	\N	\N	\N	\N	\N
289	Rebecc	URBAN	3-6100 MAURICE-DUPLESSIS	MN	438-9983455	TAE KWON DO	0023-11-23	30	100	100	\N	\N	\N	\N	\N	\N	\N
290	Isabell	VACCARO	72 BOUL-ST-ELZEAR	LAV	450-4904705	KICK BOXING	0001-02-23	365	700	700	\N	\N	\N	\N	\N	\N	\N
291	Juleeana jo	VALERY	9159 BOUL. PERRA	MTL	514-649-0640	TAE KWON DO	0002-11-21	365	800	800	\N	\N	\N	\N	\N	\N	\N
292	Lyonnel evry	VALERY	9156 BOUL .PERRA	MTL	514-649-0640	TAE KWON DO	0002-11-21	365	800	800	\N	\N	\N	\N	\N	\N	\N
293	Salvator	VARODARO	12529 VOLTAIRE	MTL	5449409 /  8042375	TAE KWON DO	0030-06-22	365	850	0	\N	\N	\N	\N	\N	\N	\N
294	Jeremia	VERNELUS	12034 BOUL LOCORDAIRE	MTL	3779960 /  2678908	TAE KWON DO	0006-10-22	90	320	320	\N	\N	\N	\N	\N	\N	\N
295	Bebecc	VERNELUS	12034 BOUL LOCORDAIRE	MTL	3779960 /  2678908	TAE KWON DO	0006-10-22	90	320	320	\N	\N	\N	\N	\N	\N	\N
296	L	WINSTON	12445 4É AVE	MTL	514-2338809	TAE KWON DO	0015-10-22	365	850	850	\N	\N	\N	\N	\N	\N	\N
297	Mehd	YAHYOUI	7762 BOU PERRAS	MTL	438-9284890	TAE KWON DO	0030-08-24	365	950	450	\N	\N	\N	\N	\N	\N	\N
298	Ank	YELYAN	7051 AVE BARDWIN	MTL	514-5135429	TAE KWON DO	0002-11-21	365	800	800	\N	\N	\N	\N	\N	\N	\N
299	Mess	YANI	12395 RUE	MTL	438-9896251	KICK BOXING	0001-09-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
300	Rayan	YOUBI	5844 RUEDES TULIPES	MTL	438-9942305	TAE KWON DO	0002-05-19	365	750	300	\N	\N	\N	\N	\N	\N	\N
301	Bile	ZEMZEMI	UUUUUUUT	TU	514-7777777	TAE KWON DO	0023-07-03	3654	0	0	\N	\N	\N	\N	\N	\N	\N
302	Mohamed  azi	ZEMZEMI	UUUUUUUT	TU	514-7777777	TAE KWON DO	0023-07-03	365	0	0	\N	\N	\N	\N	\N	\N	\N
303	Mon	ZEMZEMI	7238 Maurice Duplessis	MTL	514-7777777	TAE KWON DO	0020-09-24	365	620	620	\N	\N	\N	\N	\N	\N	\N
304	Bad	ZINDINE	190 MURRAY	MTL	514-9242272	KICK BOXING	0004-06-21	365	790	475	\N	\N	\N	\N	\N	\N	\N
305	Ada	ZORDANE	6351 BOUL MAURICE-DUPLESSI	MTL	514-5018058	TAE KWON DO	0028-09-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
306	Lily	ZORDANE	6351 BOUL MAURICE-DUPLESSI	MTL	514-5018058	TAE KWON DO	0028-09-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
307	Neyli arezk	ZOUAOUI	12310 62É AVENU	MTL	438-9225585	TAE KWON DO	0017-09-19	365	750	360	\N	\N	\N	\N	\N	\N	\N
308	Jonatha	BUFFONO	7540MAURICE-DUPLESSIS	MTL	514-2490304	TAE KWON DO	0030-11-23	90	340	340	\N	\N	\N	\N	\N	\N	\N
309	Yacin	EL MAATAOUI		MTL	514-45162	TAE KWON DO	0029-11-24	365	950	400	\N	\N	\N	\N	\N	\N	\N
310	Edouar	GAGNÉ	11865 RUE MAUDE-ABBOTT	MTL	514-2666127	KICK BOXING	0001-12-23	90	340	340	\N	\N	\N	\N	\N	\N	\N
311	Cristian	MATUES	2203LEPAILLEUVE	MTL	514-8040186	TAE KWON DO	0004-03-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
312	Natali	MATUES	2203LEPAILLEUVE	MTL	514-8040186	TAE KWON DO	0004-03-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
313	Alperen ane	BOLAT	6463 RUEDE NORMANDIE	MTL	514-6387953	TAE KWON DO	0013-12-23	365	950	315	\N	\N	\N	\N	\N	\N	\N
314	Kuzey-ce	BARDAKCI	6463 RUEDE NORMANDIE	MTL	514-6387953	TAE KWON DO	0013-12-23	365	950	315	\N	\N	\N	\N	\N	\N	\N
315	Mohamed-haithe	BENHOCINE	7375RUEMOURICE COURTOI	MTL	438-3547493	TAE KWON DO	0012-03-24	365	900	900	\N	\N	\N	\N	\N	\N	\N
316	Game	RUDY	12072 AV COPERNIC	MTL	514-7951209	KICK BOXING	0019-12-23	90	340	340	\N	\N	\N	\N	\N	\N	\N
317	Pap	TRAN	11529 RUE ALLARD	MTL	514-8273378	KICK BOXING	0001-01-23	365	850	850	\N	\N	\N	\N	\N	\N	\N
318	Ani	OUHADJ	4455 RUE DOMIENS	MTN	438-6998383	KICK BOXING	0017-01-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
319	Vincenz	VACCARO	11793 AV NICOLAS-APPERT	MTL	514-4750784	TAE KWON DO	0015-01-24	365	850	850	\N	\N	\N	\N	\N	\N	\N
320	Alessandr	VACCARO	11793 AV NICOLAS-APPERT	MTL	514-4750784	TAE KWON DO	0015-01-24	365	850	850	\N	\N	\N	\N	\N	\N	\N
321	Sofhi	JARROUCHE	12753 AV PIERRE-BAILLGER	MTL	514-2961679	TAE KWON DO	0014-01-24	365	950	350	\N	\N	\N	\N	\N	\N	\N
322	Alexande	JARROUCHE	12753 AV PIERRE-BAILLGER	MTL	514-2961679	TAE KWON DO	0014-01-24	365	950	350	\N	\N	\N	\N	\N	\N	\N
323	Safy	DIBS	8893RUE MARITAIN	MTL	438-5093236	TAEKIBODO	0020-01-24	90	320	0	\N	\N	\N	\N	\N	\N	\N
324	Cssaendr	GRENIER	527  111ÉAV	ST-JÉ	438-8801043	KICK BOXING	0015-06-24	365	900	160	\N	\N	\N	\N	\N	\N	\N
325	Kari	BACHAND	11482 AV OLIER	MTL	438-4676170	TAE KWON DO	0011-01-24	365	500	500	\N	\N	\N	\N	\N	\N	\N
326	Ada	MEEIDJI	7380 RUE DES ORMEAUT	MTL	438-4027301	KICK BOXING	0004-02-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
327	Giann	QUATTROCIOCCHI	8885 RUE D'ARRAS	MTL	514-2204399	KICK BOXING	0013-01-25	90	370	370	\N	\N	\N	\N	\N	\N	\N
328	Rober	GRENIER	527  111ÉAV	ST-JÉ	514-222986	KICK BOXING	0005-02-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
329	Adrian	LO BIANCO	8656 RUE LUIS-HAINAULL	MTL	6621686 /  M8869896	TAE KWON DO	0001-01-25	365	0	500	\N	\N	\N	\N	\N	\N	\N
330	Germina	DON GERSON	9085 RUE SHERBROOK-EST	MTL	438-2330799	KICK BOXING	0014-03-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
331	Errouiss	MICHAEL	2215 RUE MONTE	LAV	514-834-6300	KICK BOXING	0016-02-24	90	345	345	\N	\N	\N	\N	\N	\N	\N
332	Yassin	MEHDI	15555 RUE SHERBROOK-EST	MTL	514-709-1086	KICK BOXING	0024-02-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
333	Alexi	LARA SOHO	2-11980LUNGELIER	MTL	514-4495433	TAE KWON DO	0005-03-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
334	Fernand	LARA SOHO	2-11980LUNGELIER	MTL	514-4495433	TAE KWON DO	0003-06-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
335	Ada	BOURAGBI	12019 BOUL SAINTE-COLETTE	MTL	514-8063801	KICK BOXING	0006-03-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
336	Ayou	SABIR	12330 BOUL RODOLPHE FORGET	MTL	438-9243930	KICK BOXING	0005-04-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
337	Gristia	PELUSO	12164 REU ANNA-PAQUIN	MTL	514-8810345	KICK BOXING	0011-03-24	90	320	320	\N	\N	\N	\N	\N	\N	\N
338	Albert	DI BUONO	4516 SOLIDANOS	MTL	514-2457332	KICK BOXING	0025-03-24	120	500	250	\N	\N	\N	\N	\N	\N	\N
339	Jenna	PISCOPO	9280RUE GABRIELLE ROY	MTL	514-8382802	TAEKIBODO	0011-04-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
340	Samanth	PISCOPO	9280RUE GABRIELLE ROY	MTL	514-8382802	TAEKIBODO	0011-04-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
341	Antoni	GUEUARA	1802 RENE DESCARTES	MTL	514-5576569	TAE KWON DO	0018-04-24	365	950	200	\N	\N	\N	\N	\N	\N	\N
342	Moua	YAHIAOUI	11571 BOU ROLLANT	MN	514-4623453	KICK BOXING	0024-04-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
343	Djen	RAMDANE-BEY	11700 AVE DAMASE-POTVIN	MTL	514-2964782	TAE KI BO DO	0029-04-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
344	Ange-wilfri	KANGA	11478 AVE ALLARD	MTL	438-4653801	TAE KWON DO	0013-04-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
345	Aksi	CHERIFI	5592 RUE MARCEL MONETTE	MTL	438-5219527	KICK BOXING	0006-03-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
346	Fatim	KHALIL	11872 MARCEL SAVARY	MTL	 /  5465504	TAE KWON DO	0009-05-24	365	950	400	\N	\N	\N	\N	\N	\N	\N
347	Tal	KHALIL	11872 MARCEL-SAVARY	MTL	 /  5465504	TAE KWON DO	0009-05-24	365	950	400	\N	\N	\N	\N	\N	\N	\N
348	Mahmou	EL-ASSAAD	1972 BOUL DES LAERENTIDES	LAV	514-7126179	KICK BOXING	0010-04-24	365	950	500	\N	\N	\N	\N	\N	\N	\N
349	Al	JOUDAD	10649 AV DE LONDON	MTL	514-9123768	KICK BOXING	0010-04-24	365	950	500	\N	\N	\N	\N	\N	\N	\N
350	Nicola	LEMIEUX	9191AVTOURELLES	ANJ	438-4931306	KICK BOXING	0002-08-24	90	360	360	\N	\N	\N	\N	\N	\N	\N
351	Tah	ALHYAN	8165 RUE DNENT	MTL	514-5707735	KICK BOXING	0010-04-24	365	950	400	\N	\N	\N	\N	\N	\N	\N
352	Fernand	FVATAUCANGELI	12642 24 AVENUE	MTL	514-8817207	KICK BOXING	0023-08-24	90	420	420	\N	\N	\N	\N	\N	\N	\N
353	Isabell	LO DICO	12070 42É AVE	MTL	438-7645024	KICK BOXING	0014-06-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
354	Mala	KHOUDARI	11782 AVENUE MATTE	MTL	438-6993862	TAEKIBODO	0011-09-24	90	420	420	\N	\N	\N	\N	\N	\N	\N
355	Ahme	BELAID	7457 RUE DOLLIER	MTL	514-4415924	KICK BOXING	0019-06-24	90	340	0	\N	\N	\N	\N	\N	\N	\N
356	Ouway	TOUMI	20 AVENIE QUWTAL	LAV	514-9432407	KICK BOXING	0024-06-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
357	Yousse	EL HABIBI	8235PIERRE MAGUET	MTL	514-8158987	KICK BOXING	0025-06-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
358	Huji	HOPE	12473RUEVOLTAIRE	MTL	514-7744105	KICK BOXING	0025-06-24	90	340	340	\N	\N	\N	\N	\N	\N	\N
359	Sebastie	PEROMBELON	11635 5È AVENUE	MTL	514-4398152	KICK BOXING	0024-08-24	90	370	130	\N	\N	\N	\N	\N	\N	\N
360	Dyla	ALLEN	12534 PIER BLANCHETE	MTL	514-7430955	KICK BOXING	0006-10-24	90	350	350	\N	\N	\N	\N	\N	\N	\N
361	Chiar	TUTINO	40 JEAN PIERRE FERLAND	BOIS BRIAND	514-7818846	TAE KWON DO	0019-08-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
362	Dor	TUTINO	40 JEAN PIERRE FERLAND	BOIS BRIAND	514-7818846	TAE KWON DO	0019-08-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
363	Elizabet	TRIASSI	40 JEAN PIERRE FERLAND	BOIS BRIAND	514-7818846	TAE KWON DO	0019-08-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
364	Ram	BELKHOUJA	12291 LEPOL-HOULE	MTL	6550109 /  5733232	TAE KWON DO	0024-11-24	365	0	350	\N	\N	\N	\N	\N	\N	\N
365	Mohamed kali	LENGLIZ	12260 64É AVENU	MTL	514-5763126	TAE KWON DO	0012-09-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
366	Ty	AL,HAJJM	VENU CHRARL GEE	MTL	514-6411104	TAE KWON DO	0012-09-24	90	325	325	\N	\N	\N	\N	\N	\N	\N
367	Le	AL,HAJJM	VENU CHRARL GEE	MTL	514-6411104	TAE KWON DO	0012-09-24	90	325	325	\N	\N	\N	\N	\N	\N	\N
368	Nazim raya	HAFINNE	8543 AVENUE DANIEL DOUY	MTL	514-5865384	TAE BOXING	0011-09-24	90	350	350	\N	\N	\N	\N	\N	\N	\N
369	Alessi	DISCIULLO	10289 RUE DES EGANTIERS	RDP	514-8146195	KICK BOXING	0016-09-24	90	350	350	\N	\N	\N	\N	\N	\N	\N
370	Alexande	VISUONE	91 33É BOIS-DES-FILON	MTL	514-4674299	TAE KWON DO	0017-09-24	365	950	0	\N	\N	\N	\N	\N	\N	\N
371	Alou	ADDIME	602 RUE MON PETIT	LONG	514-4310568	KICK BOXING	0018-09-24	90	350	350	\N	\N	\N	\N	\N	\N	\N
372	Henr	DAYAIN	6710 RUE TERDIF APP 8	MTL	438-6994511	KICK BOXING	0018-09-24	90	350	350	\N	\N	\N	\N	\N	\N	\N
373	Saa	LYASSAMI	8053 RUE PHILLIPE-DE-CARION	MTL	438-4029970	KICK BOXING	0020-09-24	365	950	475	\N	\N	\N	\N	\N	\N	\N
374	Pasqual	DOS SANTOS	12210 JEN-YVES BIGIAS	MTL	514-8380444	KICK BOXING	0008-08-24	365	0	0	\N	\N	\N	\N	\N	\N	\N
375	Samue	CELESTIN	9322 GABRIELLE-ROY	MTL	438-9921050	TAE KWON DO	0026-09-24	365	0	0	\N	\N	\N	\N	\N	\N	\N
376	Waae	KEKAGHEL	12385 AVENUE-MATTE	MTL	 /  462-7020	KICK BOXING	0030-03-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
377	Mezhou	ABDALLAH	5657 JOSEPH DUFORESN	MTL-N	 /  578-6572	KICK BOXING	0002-10-24	90	370	370	\N	\N	\N	\N	\N	\N	\N
378	Imen	AMAKOUCHE			 /  836-3964	TAE KWON DO	0002-10-24	90	525	525	\N	\N	\N	\N	\N	\N	\N
379	Fadi	GOUDA			 /  823-8985	TAE KWON DO	0002-10-24	30	240	240	\N	\N	\N	\N	\N	\N	\N
380	Oma	BELKHOUJA	12291 AV LEOPOLD HOULÉ	MTL	514-5733232	TAE KWON DO	0003-10-24	365	0	350	\N	\N	\N	\N	\N	\N	\N
381	Foua	ATMANI	10151 RUE KARL LÉVEQUE	MTL	438-9909351	TAE KWON DO	0003-10-24	90	330	330	\N	\N	\N	\N	\N	\N	\N
382	Youse	ATMANI	10151 RUE KARL LÉVEQUE	MTL	438-9909351	TAE KWON DO	0003-10-24	90	330	330	\N	\N	\N	\N	\N	\N	\N
383	Anas ahme	ATMANI	10151 RUE KARL LÉVEQUE	MTL	438-9909351	TAE KWON DO	0003-10-24	90	330	330	\N	\N	\N	\N	\N	\N	\N
384	Dalto	DANY			514-4488006	TAE KWON DO	0013-11-24	30	120	120	\N	\N	\N	\N	\N	\N	\N
385	Datilm	VRONSKY	7550 JACQUES ROUSSENU		514-7727206	TAE KWON DO	0015-10-24	365	0	546.13	\N	\N	\N	\N	\N	\N	\N
386	Emil	LAURIER	8545 ERNEST OUINNET	MTL	514-6258377	TAE KI BO DO	0024-02-25	90	365	365	\N	\N	\N	\N	\N	\N	\N
387	Jerr	TEGUIMDJE ZEBA	6369ANTHUR CHEVRIER	MTL	819-5986436	KICK BOXING	0016-10-24	365	950	321	\N	\N	\N	\N	\N	\N	\N
388	Rache	TARHOUI	7415BAUDELAIRE	ST-JÉ	514-9296025	TAE KWON DO	0013-01-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
389	Amin maile	OUAJDI	263 RUE DE PIGALLE	REP	514-2669558	TAEKIBODO	0023-10-24	90	420	420	\N	\N	\N	\N	\N	\N	\N
390	Sar	MOR	8771 AVENUE LOUIS-LUMIERE	MTL	514-9689428	TAEKIBODO	0029-01-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
391	Mohamed-amen	ABALLOUL	9224 RUE DUSGUENAY	MTL	514-9981604	TAE KWON DO	0029-10-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
392	Ahmed-tare	ABALLOUL	9224 RUE DUSGUENAY	MTL	514-9981604	TAE KWON DO	0029-10-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
393	Marya	ABALLOUL	9224 RUE DUSGUENAY	MTL	514-9981604	TAE KWON DO	0029-10-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
394	Ayoub-nabi	ABALLOUL	9224 RUE DUSGUENAY	MTL	514-9981604	TAE KWON DO	0029-10-24	365	475	450	\N	\N	\N	\N	\N	\N	\N
395	Ade	BENCHEIKH	2447RUEYOLD	MTL	514-2492518	KICK BOXING	0005-11-24	90	350	350	\N	\N	\N	\N	\N	\N	\N
396	Ay	MARZAQ	11894AV LARCHEVÉQUE	MTL	438-9883989	TAE KI BO DO	0014-11-24	365	950	950	\N	\N	\N	\N	\N	\N	\N
397	Nadi	ZID	7415BAUDELAIRE	MTL	514-9296025	KICK BOXING	0014-11-24	90	350	350	\N	\N	\N	\N	\N	\N	\N
398	Yasmin	TARHOUNI	7415BAUDELAIRE	MTL	514-9296025	KICK BOXING	0014-11-24	90	350	350	\N	\N	\N	\N	\N	\N	\N
399	Sirin	TARHOUNI	7415BAUDELAIRE	MTL	514-9296025	KICK BOXING	0014-11-24	90	350	350	\N	\N	\N	\N	\N	\N	\N
400	Sohai	BOUCEKKINE	12112 27ÉAVENU	MTL	514-4730849	KICK BOXING	0018-11-24	90	350	300	\N	\N	\N	\N	\N	\N	\N
401	Sale	NAZIM	12186EMMANELBRIFFA	MTL	514-3188485	TAE KWON DO	0001-01-25	365	950	950	\N	\N	\N	\N	\N	\N	\N
402	Ada	OUALI	7225 MICHELET   N29	MTL	514-7138081	TAE KWON DO	0014-01-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
403	Kayde	PETIT-TRERE	7585BOULPERRES	MTL	438-2237379	KICK BOXING	0024-01-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
404	Ayman	BENJELLOUN	11497 BOUL  LANGELIER N 1	MTL	514-4732991	TAE KWON DO	0031-01-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
405	Mala	BENJELLOUN	11497 BOUL  LANGELIER N 1	MTL	514-4732991	TAE KWON DO	0031-01-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
406	Yehi	HALAWA	12132AV FERNARD-GOUTHIER	MTL	514-5180273	KICK BOXING	0026-02-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
407	Mirk	VENDITTI	12391 VOLTAIRE	MTL	514-9933469	TAE KWON DO	0020-02-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
408	Alessandr	BOZZO	12245 RUE DES IRIS	MTL	438-4908000	KICK BOXING	0026-02-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
409	Christophe	BOZZO	12245 RUE DES IRIS	MTL	438-4908000	KICK BOXING	0026-02-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
410	Pineaul	VERONIQUE		MTL	514-4388359376	TAE KWON DO	0029-03-25	30	120	120	\N	\N	\N	\N	\N	\N	\N
411	Ildris	REMY	8855 BOUL PERRAS	MTL	438-8880420	TAE KWON DO	0026-02-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
412	Benjami	FILARISMA	11610 AVE ALLARD	MTL	438-4018703	TAE KWON DO	0018-03-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
413	Ney	ZOIAOUI	12556 CROISSANT ALICE-GUY	MTL	438-9248888	TAE KWON DO	0001-04-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
414	Matte	PICCOLI	12391  94 AVENU	MTL	514-9136314	KICK BOXING	0025-03-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
415	Giulian	PICCOLI	12391  94 AVENU	MTL	514-9136314	KICK BOXING	0025-03-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
416	Abderrahi	BOUDOUMA	8814 DIARRAS	MTL	514-8161835	KICK BOXING	0002-04-25	90	350	350	\N	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 4946 (class 0 OID 20041)
-- Dependencies: 245
-- Data for Name: article; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.article (id, name, description, price) FROM stdin;
2	Gants boxe ATF	Gants de boxe ATF, cuir rouge, fermeture par velcro, 14oz	120
3	Protège dent	Protège dents thermo formable	25
4	Jambières ATF	Jambières de protection ATF, mousse et polyuréthane, couleur noire, tibia et pied	60
5	Bandes boxe	Bandes de protection pour les mains	25
\.


--
-- TOC entry 4920 (class 0 OID 19896)
-- Dependencies: 219
-- Data for Name: batch_job_execution; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch_job_execution (job_execution_id, version, job_instance_id, create_time, start_time, end_time, status, exit_code, exit_message, last_updated) FROM stdin;
1	2	1	2025-05-05 16:27:38.945417	2025-05-05 16:27:38.995282	2025-05-05 16:27:42.145861	COMPLETED	COMPLETED		2025-05-05 16:27:42.146858
\.


--
-- TOC entry 4921 (class 0 OID 19901)
-- Dependencies: 220
-- Data for Name: batch_job_execution_context; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch_job_execution_context (job_execution_id, short_context, serialized_context) FROM stdin;
1	rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAANYmF0Y2gudmVyc2lvbnQABTUuMC4weA==	\N
\.


--
-- TOC entry 4922 (class 0 OID 19906)
-- Dependencies: 221
-- Data for Name: batch_job_execution_params; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch_job_execution_params (job_execution_id, parameter_name, parameter_type, parameter_value, identifying) FROM stdin;
1	run.id	java.lang.Long	1	Y
\.


--
-- TOC entry 4924 (class 0 OID 19912)
-- Dependencies: 223
-- Data for Name: batch_job_instance; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch_job_instance (job_instance_id, version, job_name, job_key) FROM stdin;
1	0	importUserJob	947cce338b790a4bb6cf8425e98bcf94
\.


--
-- TOC entry 4926 (class 0 OID 19916)
-- Dependencies: 225
-- Data for Name: batch_step_execution; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, create_time, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) FROM stdin;
1	44	step1	1	2025-05-05 16:27:39.016228	2025-05-05 16:27:39.025216	2025-05-05 16:27:40.789487	COMPLETED	42	417	1	416	0	0	0	0	COMPLETED		2025-05-05 16:27:40.790486
2	44	step2	1	2025-05-05 16:27:40.799461	2025-05-05 16:27:40.80345	2025-05-05 16:27:41.384894	COMPLETED	42	416	0	416	0	0	0	0	COMPLETED		2025-05-05 16:27:41.387888
3	44	step3	1	2025-05-05 16:27:41.399856	2025-05-05 16:27:41.409828	2025-05-05 16:27:41.883561	COMPLETED	42	416	0	416	0	0	0	0	COMPLETED		2025-05-05 16:27:41.883561
4	44	step4	1	2025-05-05 16:27:41.894532	2025-05-05 16:27:41.898523	2025-05-05 16:27:42.137896	COMPLETED	42	416	0	416	0	0	0	0	COMPLETED		2025-05-05 16:27:42.13888
\.


--
-- TOC entry 4927 (class 0 OID 19921)
-- Dependencies: 226
-- Data for Name: batch_step_execution_context; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch_step_execution_context (step_execution_id, short_context, serialized_context) FROM stdin;
1	rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAEdAARYmF0Y2gudGFza2xldFR5cGV0AD1vcmcuc3ByaW5nZnJhbWV3b3JrLmJhdGNoLmNvcmUuc3RlcC5pdGVtLkNodW5rT3JpZW50ZWRUYXNrbGV0dAAZZGF0YUl0ZW1SZWFkZXIucmVhZC5jb3VudHNyABFqYXZhLmxhbmcuSW50ZWdlchLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAAAAGidAANYmF0Y2gudmVyc2lvbnQABTUuMC4wdAAOYmF0Y2guc3RlcFR5cGV0ADdvcmcuc3ByaW5nZnJhbWV3b3JrLmJhdGNoLmNvcmUuc3RlcC50YXNrbGV0LlRhc2tsZXRTdGVweA==	\N
2	rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAEdAAYYWxsZGF0YXJlYWRlci5yZWFkLmNvdW50c3IAEWphdmEubGFuZy5JbnRlZ2VyEuKgpPeBhzgCAAFJAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAaF0ABFiYXRjaC50YXNrbGV0VHlwZXQAPW9yZy5zcHJpbmdmcmFtZXdvcmsuYmF0Y2guY29yZS5zdGVwLml0ZW0uQ2h1bmtPcmllbnRlZFRhc2tsZXR0AA1iYXRjaC52ZXJzaW9udAAFNS4wLjB0AA5iYXRjaC5zdGVwVHlwZXQAN29yZy5zcHJpbmdmcmFtZXdvcmsuYmF0Y2guY29yZS5zdGVwLnRhc2tsZXQuVGFza2xldFN0ZXB4	\N
3	rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAEdAAYYWxsZGF0YXJlYWRlci5yZWFkLmNvdW50c3IAEWphdmEubGFuZy5JbnRlZ2VyEuKgpPeBhzgCAAFJAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAaF0ABFiYXRjaC50YXNrbGV0VHlwZXQAPW9yZy5zcHJpbmdmcmFtZXdvcmsuYmF0Y2guY29yZS5zdGVwLml0ZW0uQ2h1bmtPcmllbnRlZFRhc2tsZXR0AA1iYXRjaC52ZXJzaW9udAAFNS4wLjB0AA5iYXRjaC5zdGVwVHlwZXQAN29yZy5zcHJpbmdmcmFtZXdvcmsuYmF0Y2guY29yZS5zdGVwLnRhc2tsZXQuVGFza2xldFN0ZXB4	\N
4	rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAEdAAYYWxsZGF0YXJlYWRlci5yZWFkLmNvdW50c3IAEWphdmEubGFuZy5JbnRlZ2VyEuKgpPeBhzgCAAFJAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAaF0ABFiYXRjaC50YXNrbGV0VHlwZXQAPW9yZy5zcHJpbmdmcmFtZXdvcmsuYmF0Y2guY29yZS5zdGVwLml0ZW0uQ2h1bmtPcmllbnRlZFRhc2tsZXR0AA1iYXRjaC52ZXJzaW9udAAFNS4wLjB0AA5iYXRjaC5zdGVwVHlwZXQAN29yZy5zcHJpbmdmcmFtZXdvcmsuYmF0Y2guY29yZS5zdGVwLnRhc2tsZXQuVGFza2xldFN0ZXB4	\N
\.


--
-- TOC entry 4929 (class 0 OID 19927)
-- Dependencies: 228
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (id, firstname, lastname, address, city, phonenumber, picture, barcodevalue, email, title, level, height, weight, active, birthdate) FROM stdin;
1	Med amin	GARRAOUI	8262RUE LONARDO	SLN	514-4414609	\N	202309220001	\N	\N	\N	\N	\N	t	\N
2	Rassi	ABDELBARI	8107 RUEDE NICE	STL	7254260 /  6382935	\N	201810020002	\N	\N	\N	\N	\N	t	\N
3	Sadi	ABDELBARI	8107 RUEDE NICE	STL	7254260 /  6382935	\N	201810020003	\N	\N	\N	\N	\N	t	\N
4	Aram	ABDELRAHMANNE	12554 64ÉAVENUE	MTL	514-3239892	\N	202402010004	\N	\N	\N	\N	\N	t	\N
5	Hssain	ABDESSAMAD	11515 BL LANGELIER	MN	514-2602912	\N	202309200005	\N	\N	\N	\N	\N	t	\N
6	Angel	ACETO	855 CREMARIEST	MTL	514-205-9134	\N	200508010006	\N	\N	\N	\N	\N	t	\N
7	Kaei aiss	ADAM	10178 KARL LEVEQUE	MTL	438-464-2108	\N	202309110007	\N	\N	\N	\N	\N	t	\N
8	Ada	ADDIYA	12180 16E,AVENUE	MTL	514-9629008	\N	202111260008	\N	\N	\N	\N	\N	t	\N
9	Loua	AHMIME	12050 AV PIERRE BLANCHET	MTL	438-8336250	\N	202311090009	\N	\N	\N	\N	\N	t	\N
10	Ani	AIT SI MOHAMED	12530 FERDINAND BASTIEN	MTL	438-396-9219	\N	202312010010	\N	\N	\N	\N	\N	t	\N
11	Youce	AIT-HAMMOUDI	2096351 BLV Joseph Renaud	MTL	438-509-9110	\N	202309070011	\N	\N	\N	\N	\N	t	\N
12	Mani razin	AIT-HAMMOUDI	2096351 BLV Joseph Renaud	MTL	438-509-9110	\N	202309070012	\N	\N	\N	\N	\N	t	\N
13	Yousse	AJROUDI	10953 AVENUDROPEAU	MN	514-5571211	\N	201909100013	\N	\N	\N	\N	\N	t	\N
14	Mera	AMAZICH	6955 26É AVENUE	MTL	514-2366046	\N	202211150014	\N	\N	\N	\N	\N	t	\N
15	Amyass	AMGHAR	7351 B. MORICE-DUPLISSIS	MTL	438-8703632	\N	202002200015	\N	\N	\N	\N	\N	t	\N
16	Debora	APOLLON ROY	12,315 J.A. Rouleau	MTL	8810334 /  917-4987	\N	200909010016	\N	\N	\N	\N	\N	t	\N
17	Kati	ARAB	7380 AV DE L,ALSACE	ANJOU	3519523 /  7972040	\N	201710010017	\N	\N	\N	\N	\N	t	\N
18	Rachid	ARRID	8440-25/AVENU-N5	M-N	514-5935668	\N	201604160018	\N	\N	\N	\N	\N	t	\N
19	Ri	ARRID	8440-25/AVENU-N5	M-N	514-5935668	\N	201604160019	\N	\N	\N	\N	\N	t	\N
20	Steven	AUGUSTE	12758 PLACE LOUIS CHARTIER	MTL	514-2951041	\N	202311010020	\N	\N	\N	\N	\N	t	\N
21	Anso	AZIMYT	12155RUE PHILIPPE LEBON	MTL	2946536 /  5017016	\N	201702080021	\N	\N	\N	\N	\N	t	\N
22	Kari	BACHAND	11482 AV OLIER	MTL	438-9676170	\N	202401110022	\N	\N	\N	\N	\N	t	\N
23	Nadha el iman	BAHEDDI	7960 AVE TREMOLIERES	ANJOU	438-9209118	\N	201910250023	\N	\N	\N	\N	\N	t	\N
24	Isla	BAHOUCHE	11427 RUE ALLARD	MTL	438-2278922	\N	202112070024	\N	\N	\N	\N	\N	t	\N
25	Ilia	BAKHLUCH		MTL	3542166 /  6520700	\N	202106120025	\N	\N	\N	\N	\N	t	\N
26	Nassi	BARAKAT	64580 18ÉAVENU	MTL	514-5816019	\N	202007030026	\N	\N	\N	\N	\N	t	\N
27	Bria	BARDI	8385BOUL GOUIN EST	MTL	514-4944068	\N	202401010027	\N	\N	\N	\N	\N	t	\N
28	Ana	BEGOUG	9792  3E RUE	MTL	438-5226190	\N	202305010028	\N	\N	\N	\N	\N	t	\N
29	Youce	BELDJOUDI	9184 RUE MASSE	STL	514-8849159	\N	202308080029	\N	\N	\N	\N	\N	t	\N
30	Djame	BELIAOVAR	6393 RUE BELLEAU	MTL	514-9619615	\N	202107050030	\N	\N	\N	\N	\N	t	\N
31	Ahre	BELKASSEM	1164 BOULEVARD LACORDAIRE	MTL	 /  5538190	\N	202309120031	\N	\N	\N	\N	\N	t	\N
32	Baha eddin	BEN MASOUD	7489 RUE MONPETIT	STL	514-7915621	\N	202311200032	\N	\N	\N	\N	\N	t	\N
33	Ahme	BEN SALAH	9015 RUE BOISSONNEAULT	MTL	514-	\N	201803150033	\N	\N	\N	\N	\N	t	\N
34	Mohamed amin	BEN SALAH	9015 RUE BOISSONNEAULT	MTL	514-	\N	201803150034	\N	\N	\N	\N	\N	t	\N
35	Amin	BENAMARA	12174 RUE CATELLI	MTL	438-8379538	\N	202404240035	\N	\N	\N	\N	\N	t	\N
36	Yasmin	BENHAOUA	8792RUE DARRAS	st leo	7255713 /  5466022	\N	201504300036	\N	\N	\N	\N	\N	t	\N
37	Mohame	BENISSE	4220 RUE D'HIRELLE	MTL	438-9201314	\N	201810110037	\N	\N	\N	\N	\N	t	\N
38	Rafae	BENTO	12341 4É AVENUE	MTL	514-6487502	\N	202302080038	\N	\N	\N	\N	\N	t	\N
39	Kamili	BENYAMINA	6363 BLV ROBER	SL	438-8739437	\N	202211220039	\N	\N	\N	\N	\N	t	\N
40	Zineb khadij	BENYAMINA	6363 BLV ROBER	SL	438-8739437	\N	202211220040	\N	\N	\N	\N	\N	t	\N
41	Aksi	BERKANE	6240 RENOIRE A 15	MTL	438-3085488	\N	202501230041	\N	\N	\N	\N	\N	t	\N
42	Missa	BERROUCHE	12131 61E AVENUE	MTL	438-9934213	\N	202309250042	\N	\N	\N	\N	\N	t	\N
43	Fah	BETTACH	12005 6E AVENU	MTL	514-5130402	\N	202210160043	\N	\N	\N	\N	\N	t	\N
44	Kevi	BOSSE	8581RUE JEROME-LALEMANT	MTL	438-3896662	\N	202210120044	\N	\N	\N	\N	\N	t	\N
45	Yassin	BOUALLA	12535RUEALBER MORIN	MTL	438-4093911	\N	202401160045	\N	\N	\N	\N	\N	t	\N
46	Ade	BOUAMEUR	12258ELIE BEUREGARDI	MTL	438-4091295	\N	202408020046	\N	\N	\N	\N	\N	t	\N
47	Ayou	BOUDHINA	11677 BOUL GOUMSIN	MTL	514-4970272	\N	202406130047	\N	\N	\N	\N	\N	t	\N
48	Ada	BOUJRAD	660 PLACE DISRALI	LAV	438-4948293	\N	201609300048	\N	\N	\N	\N	\N	t	\N
49	Foua	BOUJRAD	660 PLACE DISRALI	LAV	438-4948293	\N	201609300049	\N	\N	\N	\N	\N	t	\N
50	Mohamed rid	BOUJRAD	660 PLACE DISRALI	LAV	438-4948293	\N	201609300050	\N	\N	\N	\N	\N	t	\N
51	Ylia	BOUSLAH	11956 RUE MATTE N8	MTL	438-9901345	\N	201904190051	\N	\N	\N	\N	\N	t	\N
52	Ismai	BOUTERFA	8466LEVRAVEL	STL	514-3269221	\N	202410300052	\N	\N	\N	\N	\N	t	\N
53	Mala	BOUTOUILI	5800ARTHURE-CHEVIER	MTL	2983282 /  3262268	\N	202302040053	\N	\N	\N	\N	\N	t	\N
54	Ismai	BOUZAIR	8363 BOUL CHTEAU NEUF	MTL	438-5242142	\N	202109150054	\N	\N	\N	\N	\N	t	\N
55	Zy	BRIZARD	141CAR DU RUISSELET	TERR	514-7039989	\N	202002030055	\N	\N	\N	\N	\N	t	\N
56	Méliss	BRUNELLE	150 RUE DES SORBIERS	MTL	514-4240241	\N	201911010056	\N	\N	\N	\N	\N	t	\N
57	Michae	CADEMARTORI	3840 Villeray	MTL	514-9964179	\N	201401070057	\N	\N	\N	\N	\N	t	\N
58	Anthon	CALABRETTA	8375 DANIEL DONY	MTL	514-9270133	\N	202211010058	\N	\N	\N	\N	\N	t	\N
59	Ahme	CANLI	12300 AV HURTEAU	MTL	438-4020304	\N	202310250059	\N	\N	\N	\N	\N	t	\N
60	Ismai	CANLI	12300 AV HURTEAU	MTL	438-4020304	\N	202310250060	\N	\N	\N	\N	\N	t	\N
121	Nawfe	FARAJ	8991 GIOVANNI-CABOTO	SL	514-9297663	\N	201810010121	\N	\N	\N	\N	\N	t	\N
61	Antoni	CAPPELLO	3882boul-chomedy	lAV	9445717 /  6619346	\N	202208050061	\N	\N	\N	\N	\N	t	\N
62	Madiso	CARRERA	12535 57AVE	MTL	514-473-3584	\N	202109050062	\N	\N	\N	\N	\N	t	\N
63	Anthon	CARRERA	12077 70 E AVENUE	MTL	514-7727706	\N	202304110063	\N	\N	\N	\N	\N	t	\N
64	Emm	CARRERA	12077 70 E AVENUE	MTL	514-7727706	\N	202304110064	\N	\N	\N	\N	\N	t	\N
65	Simo	CHAKUVINGN	12148 AV ALLARD	MN	514-6071872	\N	202306090065	\N	\N	\N	\N	\N	t	\N
66	Hug	CHARPENTIER	6901RUE MARIE-VICTORIN	MTL	336-85291536	\N	202203090066	\N	\N	\N	\N	\N	t	\N
67	Raia	CHBICHER	9610 PERRAS	MTL	514-6259052	\N	202210130067	\N	\N	\N	\N	\N	t	\N
68	Sam	CHEKFIAI	4403 RUE DELASE	MTL	438-9236885	\N	202209160068	\N	\N	\N	\N	\N	t	\N
69	Abdl-kari	CHEMLAL	11761 BOUL LANGELIER	MTL	438-9932157	\N	202110090069	\N	\N	\N	\N	\N	t	\N
70	Aksi	CHERIFI	5592MARCEAL MONETTE	MTL	438-5215927	\N	202403050070	\N	\N	\N	\N	\N	t	\N
71	Arri	CHERRAT	12143 AV CHARLES RENARD	MTL	514-972-4949	\N	202201120071	\N	\N	\N	\N	\N	t	\N
72	Massi	CHERRAT	12143 AV CHARLES RENARD	MTL	514-972-4949	\N	202201120072	\N	\N	\N	\N	\N	t	\N
73	Arri	CHERRAT	12143 AV CHARLES RENARD	MTL	514-972-4949	\N	202201010073	\N	\N	\N	\N	\N	t	\N
74	Masi	CHERRAT	12143 AV CHARLES RENARD	MTL	514-972-4949	\N	202201010074	\N	\N	\N	\N	\N	t	\N
75	Ade	CHRIGUI	8570 25É AVENUE N3	MTL	514-3264702	\N	201904060075	\N	\N	\N	\N	\N	t	\N
76	Ram	COIRAZZA	10313RUE DES EGLLANTIERS	MTL	514-8928748	\N	202204090076	\N	\N	\N	\N	\N	t	\N
77	Letizi	COMPARSI	11835 MARCEL.SAVA	MTL	8819081 /  8084137	\N	200511060077	\N	\N	\N	\N	\N	t	\N
78	Wali	DAKTOURI	8483 HUGUET-LATOUR	MTL	438-9925920	\N	202311060078	\N	\N	\N	\N	\N	t	\N
79	Claud	DAKVIN	5528 CHARLEVOI	MTL	514-6089539	\N	202008260079	\N	\N	\N	\N	\N	t	\N
80	Giann	DE VIVO	11614 EDITH-SEREI	MTL	514-7071765	\N	202308160080	\N	\N	\N	\N	\N	t	\N
81	Fabric	DECOSSE	8970 AV DE BRETAGE	MTL	514-5865522	\N	202301120081	\N	\N	\N	\N	\N	t	\N
82	Emir hamz	DEMIRCI	12701-3LONGELIER	MN	438-8268707	\N	202308020082	\N	\N	\N	\N	\N	t	\N
83	Yigit ef	DEMIRCI	12701-3LONGELIER	MN	438-8268707	\N	202308020083	\N	\N	\N	\N	\N	t	\N
84	Samue	DERIENZO	5160 LEO OUELETTE	SLO	7213809 /  4388280158	\N	201910220084	\N	\N	\N	\N	\N	t	\N
85	Victori	DERIENZO	5160 LEO OUELETTE	SLO	7213809 /  4388280158	\N	202002100085	\N	\N	\N	\N	\N	t	\N
86	Pascal	DES SANTOS	12210JEAN-YVES BIGRAS	MTL	514-8380444	\N	202308080086	\N	\N	\N	\N	\N	t	\N
87	D	DI PALOA	7141rue Merlot	LAV	450-936-1241	\N	202108210087	\N	\N	\N	\N	\N	t	\N
88	Linn	DI PALOA	7141 rue Merlot	LAV	450-936-1241	\N	202208210088	\N	\N	\N	\N	\N	t	\N
89	Valentin	DI PISA	12592  48É AVNUE	MTL	514-4634540	\N	202311010089	\N	\N	\N	\N	\N	t	\N
90	Emma-le	DIGIOVANNI	11901 PAUL EMILE LAMARCHE	MTL	4945152 /  8084277	\N	201005050090	\N	\N	\N	\N	\N	t	\N
91	Giovann	DIGIOVANNI	11901 PAUL EMILE LAMARCHE	MTL	4945152 /  8084277	\N	201005050091	\N	\N	\N	\N	\N	t	\N
92	Michae	DITILO	3210CHAGALL	LAVAL	514-7122257	\N	202305020092	\N	\N	\N	\N	\N	t	\N
93	Junior dibri	DJAMBOU	12037 55EME AVE	MTL	514-8623894	\N	201709010093	\N	\N	\N	\N	\N	t	\N
94	Leonel yva	DJAMBOU	12037 55EME AVE	MTL	514-8623894	\N	201709010094	\N	\N	\N	\N	\N	t	\N
95	Muriell	DJAMBOU	12037 55EME AVE	MTL	514-8623894	\N	201709010095	\N	\N	\N	\N	\N	t	\N
96	Ada	DJEBLI	5222 BOUL DES GRANDS PRAIRIS	STL	3262758 /  5865509	\N	202411240096	\N	\N	\N	\N	\N	t	\N
97	Ry	DJEBLI	5222 BOUL DES GRANDS PRAIRIS	STL	3262758 /  5865509	\N	202301050097	\N	\N	\N	\N	\N	t	\N
98	Nicola	DOMIANOVICH	12065 AV COPOMIS	MTL	514-5769397	\N	202307100098	\N	\N	\N	\N	\N	t	\N
99	Fare	DRIDI	4487RUE PLINGUET	SL	438-9923690	\N	202109150099	\N	\N	\N	\N	\N	t	\N
100	Jessic	DU ROSEAU	43 rue Gilles Boisbriand	MTL	4333320 /  830-5843	\N	200412310100	\N	\N	\N	\N	\N	t	\N
101	Fede	DURSO	9267LAUIS DESSUNES	MTL	514-8244158	\N	202203180101	\N	\N	\N	\N	\N	t	\N
102	Janett	EL BADAOUI	6362 RUE ARTHUR-CHVIER	MTL	438-8781135	\N	202009180102	\N	\N	\N	\N	\N	t	\N
103	Raya	EL BADAOUI	6362 RUE ARTHUR-CHVIER	MTL	438-8781135	\N	202009180103	\N	\N	\N	\N	\N	t	\N
104	Yasmin	EL HABIBI	8235 PIERRE MOQUET	MTL	514-7708987	\N	202109160104	\N	\N	\N	\N	\N	t	\N
105	Yousse	EL HABIBI	8235 PIERRE MOQUET	MTL	514-7708987	\N	202109160105	\N	\N	\N	\N	\N	t	\N
106	Mohamed amin	EL HADJ MOUSSA	6943 RUE DETOMAINE	MTL	514-8035620	\N	201712190106	\N	\N	\N	\N	\N	t	\N
107	Nadi	EL HADJ MOUSSA	6220 RUE DE LA VILLANELLE	MTL	514-9245695	\N	202306150107	\N	\N	\N	\N	\N	t	\N
108	Kari	EL JUNDI	2458 RUE SAINT-ZOTIQUE	MTL	438-8884436	\N	202111050108	\N	\N	\N	\N	\N	t	\N
109	Salm	EL MAATAOUI	9161AV EMILE LEGAULT	MTL	514-9742824	\N	202211230109	\N	\N	\N	\N	\N	t	\N
110	Ibrahi	ELASSAAD	11830 LOUIS-SICOTTE	MTL	438-4510311	\N	202408040110	\N	\N	\N	\N	\N	t	\N
111	Moaa	ELBANNAOUI	12312 6É AVENU	MTL	514-3276902	\N	202002050111	\N	\N	\N	\N	\N	t	\N
112	Ada	ELKHARRAZ	12002BOUL LACORDAIRE	MTL	438-9783096	\N	202308200112	\N	\N	\N	\N	\N	t	\N
113	Zine	ELMELHAOUI	6945 AV CHAMPCHEVRIER	ANJOU	514-7185823	\N	202209230113	\N	\N	\N	\N	\N	t	\N
114	Ay	ELOUELLAD	6815RUE ETIENNE BOUCHARD	MTL	514-5182765	\N	202003010114	\N	\N	\N	\N	\N	t	\N
115	Ilyas	ELOUELLAD	6815RUE ETIENNE BOUCHARD	MTL	514-5182765	\N	202003010115	\N	\N	\N	\N	\N	t	\N
116	Filipp	ENEA	12062PL.PIERRE BLANCHET	MTL	8811562 /  7307000	\N	202208080116	\N	\N	\N	\N	\N	t	\N
117	Ami	ESSAFI	11514 AV BOXUET	MTL	514-5501882	\N	202211100117	\N	\N	\N	\N	\N	t	\N
118	Tra	EUGENE	11529 RUE ALLARD	MTL	514-8273378	\N	202503290118	\N	\N	\N	\N	\N	t	\N
119	Kimi	EVRAIRE-MBOYO	1891-N3 RUEBOSSVET	MTL	438-4975147	\N	202206200119	\N	\N	\N	\N	\N	t	\N
120	Male	FAJRAOUI	11559 AV BRUNET	MTL	514-3275013	\N	202208200120	\N	\N	\N	\N	\N	t	\N
122	Chad	FARAZDAG	12369 LAPIERRE 305	MN	514-2450083	\N	202010010122	\N	\N	\N	\N	\N	t	\N
123	Sam	FARAZDAG	12369 LAPIERRE 305	MN	514-2450083	\N	202010010123	\N	\N	\N	\N	\N	t	\N
124	Ayman	FARESS	11611 LACORDAIRE N3	MTL	438-9365350	\N	201810030124	\N	\N	\N	\N	\N	t	\N
125	Ala	FATTAH	4937 AMOS	MTL	438-8383994	\N	201902060125	\N	\N	\N	\N	\N	t	\N
126	Ine	FATTAH	4937 AMOS	MTL	438-8383994	\N	201902060126	\N	\N	\N	\N	\N	t	\N
127	Ismae	FATTAH	4937 AMOS	MTL	438-8383994	\N	201902060127	\N	\N	\N	\N	\N	t	\N
128	Yanni	FEKHAR	7881AV JEAN VINCENT	MTL	514-6046810	\N	202302260128	\N	\N	\N	\N	\N	t	\N
129	Pasgualin	FRAGOLA	12085 CHARKS DIONNE	MTL	514-9491952	\N	202211010129	\N	\N	\N	\N	\N	t	\N
130	Anthon	FRAGOLA	12085 CHARKS DIONNE	MTL	514-9491952	\N	202411010130	\N	\N	\N	\N	\N	t	\N
131	Nour el houd	FREJ	9072 RUE DEL,AIGLAN	st leo	514-2508942	\N	202409280131	\N	\N	\N	\N	\N	t	\N
132	Carmill	FRESCO	7380RAFPHAEL-BELLMARE	MTL	514-2421444	\N	202503310132	\N	\N	\N	\N	\N	t	\N
133	Soni	FRESCO	7380RAFPHAEL-BELLMARE	MTL	514-2421444	\N	202303090133	\N	\N	\N	\N	\N	t	\N
134	Noh	FRIKHA	12269 RUE MARCELL GOVEAUD	MTL	514-2094839	\N	202311100134	\N	\N	\N	\N	\N	t	\N
135	Souleiman	GADIRI	6641 RUEFRONCOIS BOIVIN	MTL	514-9957313	\N	202311150135	\N	\N	\N	\N	\N	t	\N
136	Ana	GADIRI	6641 RUEFRONCOIS BOIVIN	MTL	514-5617313	\N	202302180136	\N	\N	\N	\N	\N	t	\N
137	Yacin	GAOUA	1617RUEPERCEVAL	REP	438-9901866	\N	202309210137	\N	\N	\N	\N	\N	t	\N
138	Ada	GAROVALO	12290 J.A.ROULEAU	RDP	514-4944967	\N	201811150138	\N	\N	\N	\N	\N	t	\N
139	Yassin	GARRAOUI	8262 RUE LEORD	SAT	514-4414609	\N	202309220139	\N	\N	\N	\N	\N	t	\N
140	Mohame	GARRAOUI	8262 RUE LEVRARD	SAT	514-4414609	\N	202409010140	\N	\N	\N	\N	\N	t	\N
141	Vite	GERMAN	9730 BOULPERRAS	MTL	514-4314401	\N	202406140141	\N	\N	\N	\N	\N	t	\N
142	Don gerso	GERMINAL	9085 shabarouke est    n1	MTL	438-2330799	\N	202409010142	\N	\N	\N	\N	\N	t	\N
143	Al	GHAIS	8332 AV NICOLAS LEBLANC	MTL	438-5078096	\N	202406210143	\N	\N	\N	\N	\N	t	\N
144	Mari	GHARIANI	7515 MARIANA-JODIN	MTL	438-4016516	\N	201909230144	\N	\N	\N	\N	\N	t	\N
145	Sirin	GHARIANI	7515 MARIANA-JODIN	MTL	438-4016516	\N	201909230145	\N	\N	\N	\N	\N	t	\N
146	Domenic	GIUNTA			514-3286991	\N	200704010146	\N	\N	\N	\N	\N	t	\N
147	Santiag	GRISELDA	12050 5E AVENUE	MTL	514-560-0087	\N	202301010147	\N	\N	\N	\N	\N	t	\N
148	Mohamed amin	GUEMAUNI	8080RUE CHTAIGNIERS	LAV	438-4092724	\N	202302150148	\N	\N	\N	\N	\N	t	\N
149	Wissam jona	GUEMMAH                           WISSAM	12093 AV PIERRE BLANCHET	MTL	2974734 /  4389924750	\N	202409210149	\N	\N	\N	\N	\N	t	\N
150	Samir	HAGUI	12100 BLV LACOR	MN	514-6638519	\N	202301140150	\N	\N	\N	\N	\N	t	\N
151	Ahme	HAMADENI	4669VALCOURT	SL	514-6492388	\N	202211200151	\N	\N	\N	\N	\N	t	\N
152	Myria	HAMADENI	4669VALCOURT	SL	514-6492388	\N	202305100152	\N	\N	\N	\N	\N	t	\N
153	Ait ahme	HAMID ABDELHAC	11740 FERNAND GAUTHIER 6	MTL	514-882-8080	\N	202209090153	\N	\N	\N	\N	\N	t	\N
154	Dal	HAMMOUCHE	9710 3É RUE	MTL	438-4904983	\N	202310160154	\N	\N	\N	\N	\N	t	\N
155	Mohame	HANINE	11991 AVE LAPENE	MTL	514-3295144	\N	201904140155	\N	\N	\N	\N	\N	t	\N
156	Eba	HASSAN	8477DECLUY-	STL	514-4585541	\N	202309010156	\N	\N	\N	\N	\N	t	\N
157	Zhacri	HAYA	2685RUE DES FAUCONS	MTL	514-2662695	\N	202306100157	\N	\N	\N	\N	\N	t	\N
158	Maria	HIJAZI	6155BOUL LEGO N2	MTL	514-4312200	\N	202210150158	\N	\N	\N	\N	\N	t	\N
159	Zeina	HIJAZI	6155BOUL LEGO N2	MTL	514-4312200	\N	202210150159	\N	\N	\N	\N	\N	t	\N
160	Fatim	HIJAZI	6155BOUL LEGO N2	MTL	514-4312200	\N	202210150160	\N	\N	\N	\N	\N	t	\N
161	Ada	HLEL	11426 Avenue Alfred	MNR	514-827-5578	\N	202201060161	\N	\N	\N	\N	\N	t	\N
162	Neil	HLEL	11426 Avenue Alfred	MNR	514-827-5578	\N	202209250162	\N	\N	\N	\N	\N	t	\N
163	Tra	HUNG VAN	12018 BOUL ARMAND BAMBARDIER	MTL	514-8828200	\N	202208280163	\N	\N	\N	\N	\N	t	\N
164	Ann	IACONO	8235 NANETS	st leo	514-5020377	\N	202408010164	\N	\N	\N	\N	\N	t	\N
165	Antoin	IACONO	12120PIERRE-BAILLARGEAN	MTL	9777235 /  5853294	\N	202410150165	\N	\N	\N	\N	\N	t	\N
166	Luc	IAZURLO	3426AVE DESAMBASSADEUR	LAV	514-5919628	\N	202306160166	\N	\N	\N	\N	\N	t	\N
167	Carl	IOANNONE	12114PIERRE BLANCHET	MTL	6485699 /  2125344	\N	202210100167	\N	\N	\N	\N	\N	t	\N
168	Othman	JAAFOUR	11461 AVE ROME	MN	514-3272193	\N	202109180168	\N	\N	\N	\N	\N	t	\N
169	Wassi	JELIDI	7842AVE PIGEON	MTL	438-8603048	\N	202410140169	\N	\N	\N	\N	\N	t	\N
170	Ada	JULID	693 RUE BLAISE	MTL	514-7587963	\N	202203280170	\N	\N	\N	\N	\N	t	\N
171	Mohame	KAHLI	8777RUE NARBONNE	MTL	438-8366194	\N	202208090171	\N	\N	\N	\N	\N	t	\N
172	Hib	KAHLI	8777RUE NARBONNE	MTL	438-8366194	\N	202208090172	\N	\N	\N	\N	\N	t	\N
173	Ale	KAREL	102DULUTH EST	MTL	514-6214747	\N	202306020173	\N	\N	\N	\N	\N	t	\N
174	Ahmed ram	KETTOUCHE		MTL	514-7754416	\N	201904100174	\N	\N	\N	\N	\N	t	\N
175	Moua	KHAIR	7478RUE JOSEP-MORIN	MTL	438-6805810	\N	202403010175	\N	\N	\N	\N	\N	t	\N
176	Al	KHALAF	8840 MAURICE DUPLISSIS  8	MTL	4946634 /  9197766	\N	201810080176	\N	\N	\N	\N	\N	t	\N
177	Mohame	KHALAF	8840 MAURICE DUPLISSIS  8	MTL	4946634 /  9197766	\N	201810080177	\N	\N	\N	\N	\N	t	\N
178	Oma	KHLALBEL	4015SAINT-JOSEPH	MTL	438-7771015	\N	202001220178	\N	\N	\N	\N	\N	t	\N
179	Beyre	KOBBI	12636 AV CORBEIL	MTL	514-8922565	\N	202210230179	\N	\N	\N	\N	\N	t	\N
180	Bouberagi	LAETICIA	12047 ELIE-BEAUREGARD	MTL	438-9234208	\N	202205110180	\N	\N	\N	\N	\N	t	\N
181	Lin	LAHLOLI	12454 BO.ROLLAND	MTL	5854313 /  2984039	\N	202211020181	\N	\N	\N	\N	\N	t	\N
182	Lili	LAHLOLI	12454 BO.ROLLAND	MTL	4414542 /  2984039	\N	202211100182	\N	\N	\N	\N	\N	t	\N
183	Lorenz	LAMOLINARA	3430 N-301 PIE IX	LAV	514-2428992	\N	202312010183	\N	\N	\N	\N	\N	t	\N
184	Fadi skanda	LARIBI	12152 5É AVENU	MTL	514-466-9660	\N	202106140184	\N	\N	\N	\N	\N	t	\N
185	Ja	LARIBI	12152 5É AVENU	MTL	514-4674675	\N	201901140185	\N	\N	\N	\N	\N	t	\N
186	Aureli	LATELLA	8930 LAVERDIERE	st leo	514-9999666	\N	200709240186	\N	\N	\N	\N	\N	t	\N
187	Bogi	LOICLE	11830LANGELIER	MN	438-5261170	\N	202306200187	\N	\N	\N	\N	\N	t	\N
188	Jenna	MAHDI	6020 AV DES ANGEVINS	ANJOU	514-9425789	\N	202312020188	\N	\N	\N	\N	\N	t	\N
189	Akra	MAKHLOUF	1061 rue noiseux	MTL	514-2344301	\N	202402080189	\N	\N	\N	\N	\N	t	\N
190	Christia	MARINELLI	3830 MAJISTAT	LAV	514-9832100	\N	202202160190	\N	\N	\N	\N	\N	t	\N
191	Dann	MARTINS	12215 ALEXIS CORROLL	MTL	514-889599	\N	202108110191	\N	\N	\N	\N	\N	t	\N
192	Mali	MASNOUR TUIZO	12740	MTL	514-8721693	\N	202001120192	\N	\N	\N	\N	\N	t	\N
193	Fabi	MATAS	12550 58 AVENU	MTL	514-8851442	\N	202209190193	\N	\N	\N	\N	\N	t	\N
194	Arew	MAYOUF	12-12055 BOUL ARMAND BOMBA	MTL	514-8032633	\N	202311050194	\N	\N	\N	\N	\N	t	\N
195	Mohamed yacou	MAYOUF	12-12055 BOUL ARMAND BOMBA	MTL	514-8032633	\N	202311050195	\N	\N	\N	\N	\N	t	\N
196	Moamed luo	MAYOUF	12-12055 BOUL ARMAND BOMBA	MTL	514-8032633	\N	202311050196	\N	\N	\N	\N	\N	t	\N
197	Mohame	MAYOUF	12-12055 BOUL ARMAND BOMBA	MTL	514-8032633	\N	202311050197	\N	\N	\N	\N	\N	t	\N
198	Raya	MAZOUZ	6361 RUE NORMANDIE	MNR	3212515 /  5469141	\N	201810010198	\N	\N	\N	\N	\N	t	\N
199	Syrin	MAZOUZ	6361 RUE NORMANDIE	MNR	3212515 /  5469141	\N	201810010199	\N	\N	\N	\N	\N	t	\N
200	Pedr	MCKELVEY		MTL	514-4654054	\N	200704120200	\N	\N	\N	\N	\N	t	\N
201	Jess	MECCIA	6014 MAURICE-DEPESSIS	MTL	514-9092368	\N	202310130201	\N	\N	\N	\N	\N	t	\N
202	Mustaph	MERAD	12189 AV GILBERT-BARBIER	MTL	438-8711034	\N	202301100202	\N	\N	\N	\N	\N	t	\N
203	Eanz	MERAD	12189 AV GILBERT-BARBIER	MTL	9681034 /  8711034	\N	202203150203	\N	\N	\N	\N	\N	t	\N
204	Emm	MERAD	12189 AV GILBERT-BARBIER	MTL	9681034 /  8711034	\N	202203150204	\N	\N	\N	\N	\N	t	\N
205	Mohamed djame	MERZOUG	12144 RUE ALLAND	MTL	8151492 /  4620019	\N	202210050205	\N	\N	\N	\N	\N	t	\N
206	Ada	MEZARI	4515 RUE BOYA	SL	514-3460906	\N	202110190206	\N	\N	\N	\N	\N	t	\N
207	Abdalla	MEZHOUD	5651JOSEPHE DUFRIME	M NO	8859260 /  3899160	\N	201903290207	\N	\N	\N	\N	\N	t	\N
208	Yace	MEZHOUD	5651JOSEPHE DUFRIME	M NO	8859260 /  3899160	\N	202310020208	\N	\N	\N	\N	\N	t	\N
209	Lu	MINH TUAN	12435 A 4É	MTL	514-7131502	\N	202211050209	\N	\N	\N	\N	\N	t	\N
210	Jonatha	MIRARCHI	331 KINDERSLEY	MTL	7393270 /  916-1162	\N	201309250210	\N	\N	\N	\N	\N	t	\N
211	Aima	MOKKADEM	11860AV BRUNET	MTL	438-4082018	\N	202208080211	\N	\N	\N	\N	\N	t	\N
212	Mohamed sam	MOKRI	8010AVE JOLIOT CURIE	MTL	514-8819231	\N	202205010212	\N	\N	\N	\N	\N	t	\N
213	Neill	MOKRI	8010AVE JOLIOT CURIE	MTL	514-8819231	\N	202205010213	\N	\N	\N	\N	\N	t	\N
214	Sebasti	MORIN	6901RUE MARIE-VICTORIN	MTL	438-9858523	\N	202203090214	\N	\N	\N	\N	\N	t	\N
215	Gabrie	MORIN-LAPORTE	7474MAURICE DEPLESSISE	MTL	514-7010348	\N	201911250215	\N	\N	\N	\N	\N	t	\N
216	Brahi	MOUATIF	RUE BLEAU 1003	LAV	514-2599634	\N	201702080216	\N	\N	\N	\N	\N	t	\N
217	Yasmin	MOUATIF	RUE BLEAU 1003	LAV	450-9368185	\N	201901050217	\N	\N	\N	\N	\N	t	\N
218	Yacin	MOUSSAOUI	7020 RUE JARRY EST	MTL	438-5402686	\N	202305230218	\N	\N	\N	\N	\N	t	\N
219	Raya	MOUZOUNI	11515 RUE LANGELIER AP4	MTL	438-8210188	\N	202401090219	\N	\N	\N	\N	\N	t	\N
220	Achera	NAIT DAOUD	8841AVEUE GEORGES	ENJ	514-7796114	\N	202110090220	\N	\N	\N	\N	\N	t	\N
221	Abdelmaji	NEGGAZI	11529 AV LAURIER	MTL	514-6192017	\N	202312230221	\N	\N	\N	\N	\N	t	\N
222	Jahne	NEPTUNE	12245 56É AVENU	RDP	514-8355439	\N	202410300222	\N	\N	\N	\N	\N	t	\N
223	Tyra	NEPTUNE	12245 56É AVENU	RDP	514-8355439	\N	202410300223	\N	\N	\N	\N	\N	t	\N
224	Kenn	NGUYEN	12577GERTRUDE GENDREAU	MTL	514-4738438	\N	201711090224	\N	\N	\N	\N	\N	t	\N
225	Vincen	NGUYEN	11725 ALLARD	MTL	514-8368850	\N	202308280225	\N	\N	\N	\N	\N	t	\N
226	L	NICK	12445 4É AVE	MTL	514-2338809	\N	202210150226	\N	\N	\N	\N	\N	t	\N
227	Novee	NOROZI ALLAM	24É AVE 12607	MTL	514-5789102	\N	202202280227	\N	\N	\N	\N	\N	t	\N
228	Oha	OCAMP	7500ANDRE-AMPERE	MTL	438-7776527	\N	202311060228	\N	\N	\N	\N	\N	t	\N
229	Alejandr	OCVIL-GALVEZ	7800 Maurice Duplessis	MTL	438-8873282	\N	202401020229	\N	\N	\N	\N	\N	t	\N
230	Tatian	OSMAN	12396 ELIE BEOUREGARD	MTL	514-2674723	\N	202308140230	\N	\N	\N	\N	\N	t	\N
231		OTHMAN ALAOUI			514-	\N	202307120231	\N	\N	\N	\N	\N	t	\N
232	Massi	OUHEB	6090 REMOIR	MTL	438-7652929	\N	202404150232	\N	\N	\N	\N	\N	t	\N
233	Anis elya	OULD ALI	5978RUEPORTEL	LAV	514-9630211	\N	202301120233	\N	\N	\N	\N	\N	t	\N
234	Cerin	OUSSADA	10991L,ACHEVEQUE	MTL	514-5887467	\N	202001150234	\N	\N	\N	\N	\N	t	\N
235	Emm	PELUSO	12164 REU ANNA-PAQUIN	MTL	514-8810345	\N	202403110235	\N	\N	\N	\N	\N	t	\N
236	Ann-sara	PERDRIEL	12077 6È AVENUE	MTL	3189315 /  4472966	\N	202309190236	\N	\N	\N	\N	\N	t	\N
237	Angell	PHAN	12480 54ÉAVENU	MTL	438-7251982	\N	202002160237	\N	\N	\N	\N	\N	t	\N
238	Rick	PHAN	12480 54ÉAVENU	MTL	438-7251982	\N	202002160238	\N	\N	\N	\N	\N	t	\N
239	Jayle	PHANORD	7525 BV PERRAS	MTL	438-8841542	\N	201810080239	\N	\N	\N	\N	\N	t	\N
240	Marle	PHANORD	6533ROI RENEÉ	ANJ	514-7915839	\N	201908200240	\N	\N	\N	\N	\N	t	\N
241	Cipeiiet	PHILIPPE	9676GALERIES D,ANJOU	ANJOU	514-5707980	\N	202001200241	\N	\N	\N	\N	\N	t	\N
242	Djahmee	PHILOSCA	1250 PHILIPPE-LEBON	MTL	514-297-3759	\N	202107150242	\N	\N	\N	\N	\N	t	\N
243	Damian	PICCOLI	9247LAUIS LUMMIER	MTL	438-4920873	\N	202002030243	\N	\N	\N	\N	\N	t	\N
244	Giordan	PICCOLI	9247LAUIS LUMMIER	MTL	438-4920873	\N	202002030244	\N	\N	\N	\N	\N	t	\N
245	St-for	PIERRE	9151BOUL DES GALERIES  806	ANJOU	514-5507560	\N	202301050245	\N	\N	\N	\N	\N	t	\N
246	Cristia	PINTO	12050ARMAND BOMBADIER	MTL	514-7558804	\N	202310040246	\N	\N	\N	\N	\N	t	\N
247	Fabric	POULIN	6472 AV PERDDERO	MTL	418-5585106	\N	202310090247	\N	\N	\N	\N	\N	t	\N
248	Ilia	RACHID	4606BDGIN EST	MTL	514-5134298	\N	202301270248	\N	\N	\N	\N	\N	t	\N
249	Michae	RAFFAELE	8080PHILIPPE DE CARION	MTL	6431832 /  7181832	\N	202211250249	\N	\N	\N	\N	\N	t	\N
250	Kaei aiss	RAMY	10178 KARL LEVEQUE	MTL	438-464-2108	\N	202309110250	\N	\N	\N	\N	\N	t	\N
251	Fira	REKDAL	12550 EDMONT	MTL	514-4517756	\N	202409090251	\N	\N	\N	\N	\N	t	\N
252	Oma	REKDAL	12550 EDMONT	MTL	514-4517756	\N	202409090252	\N	\N	\N	\N	\N	t	\N
253	Abde rm	SAAD-M	11792 AV NICOLAS	MTL	514-2550319	\N	202311240253	\N	\N	\N	\N	\N	t	\N
254	Wassi	SAAD-M	11792 AV NICOLAS	MTL	514-2550319	\N	202311240254	\N	\N	\N	\N	\N	t	\N
255	Marw	SAHAL	6390 LEGER   N9	MTL	438-4023350	\N	201812120255	\N	\N	\N	\N	\N	t	\N
256	Mohamed amin	SAHAL	6390 LEGER   N9	MTL	438-4023350	\N	201812120256	\N	\N	\N	\N	\N	t	\N
257	Amin	SAHIL	7705 RUE SHERBROOKE. EST	MTL	514-889-1986	\N	201904090257	\N	\N	\N	\N	\N	t	\N
258	Ismae	SAHLI	7705 RUE SHERBROOKE.EST	MTL	514-889-1986	\N	201904090258	\N	\N	\N	\N	\N	t	\N
259	Ahme	SAIDI	5083 RUE DECASTILLE	MTL	514-6912512	\N	202108250259	\N	\N	\N	\N	\N	t	\N
260	Wassi	SALHI	11720FERNAND GAUTHIER	MTL	514-5185005	\N	202305040260	\N	\N	\N	\N	\N	t	\N
261	Wassi	SALHI	11720FERNAND GAUTHIER	MTL	514-5185005	\N	202305040261	\N	\N	\N	\N	\N	t	\N
262	Wassi	SALHI	11720FERNAND GAUTHIER	MTL	514-5185005	\N	202305040262	\N	\N	\N	\N	\N	t	\N
263	Eberso	SALVANT	12305 54E   AVENU	MTL	9833697 /  6489313	\N	202303100263	\N	\N	\N	\N	\N	t	\N
264	Eberss	SALVANT	12305 54E   AVENU	MTL	9833697 /  6489313	\N	201809050264	\N	\N	\N	\N	\N	t	\N
265	Lucely	SALVANT	12305 54E   AVENU	MTL	9833697 /  6489313	\N	201801240265	\N	\N	\N	\N	\N	t	\N
266	Marc	SANELLI	5621 AUBIN	MTL	3238034 /  8954100	\N	201006150266	\N	\N	\N	\N	\N	t	\N
267	Alessi	SANTONE	12255 42É AVENU	MTL	514-4975694	\N	202405170267	\N	\N	\N	\N	\N	t	\N
268	Vaness	SARACINO	6619BOUL LEVESQUE	LAV	514-7121065	\N	202110210268	\N	\N	\N	\N	\N	t	\N
269	Mickae	SERGERIE	12732 26E AVENU	MTL	8130205 /  8272981	\N	201906090269	\N	\N	\N	\N	\N	t	\N
270	Oma	SHARIF			514-	\N	202109010270	\N	\N	\N	\N	\N	t	\N
271	Douny	SHEIKH	12740 27É AVENU	MTL	514-2983099	\N	202105250271	\N	\N	\N	\N	\N	t	\N
272	Zachari	SHEIKH	12740 27É AVENU	MTL	514-2983099	\N	201801120272	\N	\N	\N	\N	\N	t	\N
273	Hassa	SHEIKH	12250 41È AVE	MTL	514-7812451	\N	202205020273	\N	\N	\N	\N	\N	t	\N
274	Samue	SILVA	12090BOUL-RDP	MTL	514-8028776	\N	202306020274	\N	\N	\N	\N	\N	t	\N
275	Adolf	SIMEONE	10705 DRAPEAU	MTL	514-8986556	\N	202402140275	\N	\N	\N	\N	\N	t	\N
276	Emili	SIMEONE	10705 DRAPEAU	MTL	514-8986556	\N	202402140276	\N	\N	\N	\N	\N	t	\N
277	Soni	SIMEONE	10705 DRAPEAU	MTL	514-8986556	\N	202403200277	\N	\N	\N	\N	\N	t	\N
278	Davi	SOGORE	12379AVENU CLEMENT-ADER	MTL	514-5619044	\N	202210120278	\N	\N	\N	\N	\N	t	\N
279	Mate	SPERANO	8658 TIBIDEAU	MTL	514-9735865	\N	202305010279	\N	\N	\N	\N	\N	t	\N
280	Hussei	TAHA	10-6950 AVENUDE CHAMPCHERRIER	ANJOU	514-7463090	\N	202001100280	\N	\N	\N	\N	\N	t	\N
281	Anger	TAHANI	3869 MONSELET	MTL	438-8601212	\N	202311220281	\N	\N	\N	\N	\N	t	\N
282	Rani	TAHIRI	12530AV PAUL-DUFAULT	MTL	514-7223810	\N	202209200282	\N	\N	\N	\N	\N	t	\N
283	Yacin	TAHIRI	12530AV PAUL-DUFAULT	MTL	514-7223810	\N	202209200283	\N	\N	\N	\N	\N	t	\N
284	An	TAHIRI	12530AV PAUL-DUFAULT	MTL	514-7223810	\N	202209200284	\N	\N	\N	\N	\N	t	\N
285	Jose	TALBOT			514-	\N	202305010285	\N	\N	\N	\N	\N	t	\N
286	Achraf rost	TOUIL	11517 AV ALLARD	MTL	438-3892750	\N	202212110286	\N	\N	\N	\N	\N	t	\N
287	Cioban	TUDOR	9212BOUL-GOUIN	MTL	514-6055872	\N	202203020287	\N	\N	\N	\N	\N	t	\N
288	Angel mirell	URBAN	3-6100 MAURICE-DUPLESSIS	MN	438-9983455	\N	202311230288	\N	\N	\N	\N	\N	t	\N
289	Rebecc	URBAN	3-6100 MAURICE-DUPLESSIS	MN	438-9983455	\N	202311230289	\N	\N	\N	\N	\N	t	\N
290	Isabell	VACCARO	72 BOUL-ST-ELZEAR	LAV	450-4904705	\N	202302010290	\N	\N	\N	\N	\N	t	\N
291	Juleeana jo	VALERY	9159 BOUL. PERRA	MTL	514-649-0640	\N	202111020291	\N	\N	\N	\N	\N	t	\N
292	Lyonnel evry	VALERY	9156 BOUL .PERRA	MTL	514-649-0640	\N	202111020292	\N	\N	\N	\N	\N	t	\N
293	Salvator	VARODARO	12529 VOLTAIRE	MTL	5449409 /  8042375	\N	202206300293	\N	\N	\N	\N	\N	t	\N
294	Jeremia	VERNELUS	12034 BOUL LOCORDAIRE	MTL	3779960 /  2678908	\N	202210060294	\N	\N	\N	\N	\N	t	\N
295	Bebecc	VERNELUS	12034 BOUL LOCORDAIRE	MTL	3779960 /  2678908	\N	202210060295	\N	\N	\N	\N	\N	t	\N
296	L	WINSTON	12445 4É AVE	MTL	514-2338809	\N	202210150296	\N	\N	\N	\N	\N	t	\N
297	Mehd	YAHYOUI	7762 BOU PERRAS	MTL	438-9284890	\N	202408300297	\N	\N	\N	\N	\N	t	\N
298	Ank	YELYAN	7051 AVE BARDWIN	MTL	514-5135429	\N	202111020298	\N	\N	\N	\N	\N	t	\N
299	Mess	YANI	12395 RUE	MTL	438-9896251	\N	202309010299	\N	\N	\N	\N	\N	t	\N
300	Rayan	YOUBI	5844 RUEDES TULIPES	MTL	438-9942305	\N	201905020300	\N	\N	\N	\N	\N	t	\N
301	Bile	ZEMZEMI	UUUUUUUT	TU	514-7777777	\N	200307230301	\N	\N	\N	\N	\N	t	\N
302	Mohamed  azi	ZEMZEMI	UUUUUUUT	TU	514-7777777	\N	200307230302	\N	\N	\N	\N	\N	t	\N
303	Mon	ZEMZEMI	7238 Maurice Duplessis	MTL	514-7777777	\N	202409200303	\N	\N	\N	\N	\N	t	\N
304	Bad	ZINDINE	190 MURRAY	MTL	514-9242272	\N	202106040304	\N	\N	\N	\N	\N	t	\N
305	Ada	ZORDANE	6351 BOUL MAURICE-DUPLESSI	MTL	514-5018058	\N	202309280305	\N	\N	\N	\N	\N	t	\N
306	Lily	ZORDANE	6351 BOUL MAURICE-DUPLESSI	MTL	514-5018058	\N	202309280306	\N	\N	\N	\N	\N	t	\N
307	Neyli arezk	ZOUAOUI	12310 62É AVENU	MTL	438-9225585	\N	201909170307	\N	\N	\N	\N	\N	t	\N
308	Jonatha	BUFFONO	7540MAURICE-DUPLESSIS	MTL	514-2490304	\N	202311300308	\N	\N	\N	\N	\N	t	\N
309	Yacin	EL MAATAOUI		MTL	514-45162	\N	202411290309	\N	\N	\N	\N	\N	t	\N
310	Edouar	GAGNÉ	11865 RUE MAUDE-ABBOTT	MTL	514-2666127	\N	202312010310	\N	\N	\N	\N	\N	t	\N
311	Cristian	MATUES	2203LEPAILLEUVE	MTL	514-8040186	\N	202403040311	\N	\N	\N	\N	\N	t	\N
312	Natali	MATUES	2203LEPAILLEUVE	MTL	514-8040186	\N	202403040312	\N	\N	\N	\N	\N	t	\N
313	Alperen ane	BOLAT	6463 RUEDE NORMANDIE	MTL	514-6387953	\N	202312130313	\N	\N	\N	\N	\N	t	\N
314	Kuzey-ce	BARDAKCI	6463 RUEDE NORMANDIE	MTL	514-6387953	\N	202312130314	\N	\N	\N	\N	\N	t	\N
315	Mohamed-haithe	BENHOCINE	7375RUEMOURICE COURTOI	MTL	438-3547493	\N	202403120315	\N	\N	\N	\N	\N	t	\N
316	Game	RUDY	12072 AV COPERNIC	MTL	514-7951209	\N	202312190316	\N	\N	\N	\N	\N	t	\N
317	Pap	TRAN	11529 RUE ALLARD	MTL	514-8273378	\N	202301010317	\N	\N	\N	\N	\N	t	\N
318	Ani	OUHADJ	4455 RUE DOMIENS	MTN	438-6998383	\N	202401170318	\N	\N	\N	\N	\N	t	\N
319	Vincenz	VACCARO	11793 AV NICOLAS-APPERT	MTL	514-4750784	\N	202401150319	\N	\N	\N	\N	\N	t	\N
320	Alessandr	VACCARO	11793 AV NICOLAS-APPERT	MTL	514-4750784	\N	202401150320	\N	\N	\N	\N	\N	t	\N
321	Sofhi	JARROUCHE	12753 AV PIERRE-BAILLGER	MTL	514-2961679	\N	202401140321	\N	\N	\N	\N	\N	t	\N
322	Alexande	JARROUCHE	12753 AV PIERRE-BAILLGER	MTL	514-2961679	\N	202401140322	\N	\N	\N	\N	\N	t	\N
323	Safy	DIBS	8893RUE MARITAIN	MTL	438-5093236	\N	202401200323	\N	\N	\N	\N	\N	t	\N
324	Cssaendr	GRENIER	527  111ÉAV	ST-JÉ	438-8801043	\N	202406150324	\N	\N	\N	\N	\N	t	\N
325	Kari	BACHAND	11482 AV OLIER	MTL	438-4676170	\N	202401110325	\N	\N	\N	\N	\N	t	\N
326	Ada	MEEIDJI	7380 RUE DES ORMEAUT	MTL	438-4027301	\N	202402040326	\N	\N	\N	\N	\N	t	\N
327	Giann	QUATTROCIOCCHI	8885 RUE D'ARRAS	MTL	514-2204399	\N	202501130327	\N	\N	\N	\N	\N	t	\N
328	Rober	GRENIER	527  111ÉAV	ST-JÉ	514-222986	\N	202402050328	\N	\N	\N	\N	\N	t	\N
329	Adrian	LO BIANCO	8656 RUE LUIS-HAINAULL	MTL	6621686 /  M8869896	\N	202501010329	\N	\N	\N	\N	\N	t	\N
330	Germina	DON GERSON	9085 RUE SHERBROOK-EST	MTL	438-2330799	\N	202403140330	\N	\N	\N	\N	\N	t	\N
331	Errouiss	MICHAEL	2215 RUE MONTE	LAV	514-834-6300	\N	202402160331	\N	\N	\N	\N	\N	t	\N
332	Yassin	MEHDI	15555 RUE SHERBROOK-EST	MTL	514-709-1086	\N	202402240332	\N	\N	\N	\N	\N	t	\N
333	Alexi	LARA SOHO	2-11980LUNGELIER	MTL	514-4495433	\N	202403050333	\N	\N	\N	\N	\N	t	\N
334	Fernand	LARA SOHO	2-11980LUNGELIER	MTL	514-4495433	\N	202406030334	\N	\N	\N	\N	\N	t	\N
335	Ada	BOURAGBI	12019 BOUL SAINTE-COLETTE	MTL	514-8063801	\N	202403060335	\N	\N	\N	\N	\N	t	\N
336	Ayou	SABIR	12330 BOUL RODOLPHE FORGET	MTL	438-9243930	\N	202404050336	\N	\N	\N	\N	\N	t	\N
337	Gristia	PELUSO	12164 REU ANNA-PAQUIN	MTL	514-8810345	\N	202403110337	\N	\N	\N	\N	\N	t	\N
338	Albert	DI BUONO	4516 SOLIDANOS	MTL	514-2457332	\N	202403250338	\N	\N	\N	\N	\N	t	\N
339	Jenna	PISCOPO	9280RUE GABRIELLE ROY	MTL	514-8382802	\N	202404110339	\N	\N	\N	\N	\N	t	\N
340	Samanth	PISCOPO	9280RUE GABRIELLE ROY	MTL	514-8382802	\N	202404110340	\N	\N	\N	\N	\N	t	\N
341	Antoni	GUEUARA	1802 RENE DESCARTES	MTL	514-5576569	\N	202404180341	\N	\N	\N	\N	\N	t	\N
342	Moua	YAHIAOUI	11571 BOU ROLLANT	MN	514-4623453	\N	202404240342	\N	\N	\N	\N	\N	t	\N
343	Djen	RAMDANE-BEY	11700 AVE DAMASE-POTVIN	MTL	514-2964782	\N	202404290343	\N	\N	\N	\N	\N	t	\N
344	Ange-wilfri	KANGA	11478 AVE ALLARD	MTL	438-4653801	\N	202404130344	\N	\N	\N	\N	\N	t	\N
345	Aksi	CHERIFI	5592 RUE MARCEL MONETTE	MTL	438-5219527	\N	202403060345	\N	\N	\N	\N	\N	t	\N
346	Fatim	KHALIL	11872 MARCEL SAVARY	MTL	 /  5465504	\N	202405090346	\N	\N	\N	\N	\N	t	\N
347	Tal	KHALIL	11872 MARCEL-SAVARY	MTL	 /  5465504	\N	202405090347	\N	\N	\N	\N	\N	t	\N
348	Mahmou	EL-ASSAAD	1972 BOUL DES LAERENTIDES	LAV	514-7126179	\N	202404100348	\N	\N	\N	\N	\N	t	\N
349	Al	JOUDAD	10649 AV DE LONDON	MTL	514-9123768	\N	202404100349	\N	\N	\N	\N	\N	t	\N
350	Nicola	LEMIEUX	9191AVTOURELLES	ANJ	438-4931306	\N	202408020350	\N	\N	\N	\N	\N	t	\N
351	Tah	ALHYAN	8165 RUE DNENT	MTL	514-5707735	\N	202404100351	\N	\N	\N	\N	\N	t	\N
352	Fernand	FVATAUCANGELI	12642 24 AVENUE	MTL	514-8817207	\N	202408230352	\N	\N	\N	\N	\N	t	\N
353	Isabell	LO DICO	12070 42É AVE	MTL	438-7645024	\N	202406140353	\N	\N	\N	\N	\N	t	\N
354	Mala	KHOUDARI	11782 AVENUE MATTE	MTL	438-6993862	\N	202409110354	\N	\N	\N	\N	\N	t	\N
355	Ahme	BELAID	7457 RUE DOLLIER	MTL	514-4415924	\N	202406190355	\N	\N	\N	\N	\N	t	\N
356	Ouway	TOUMI	20 AVENIE QUWTAL	LAV	514-9432407	\N	202406240356	\N	\N	\N	\N	\N	t	\N
357	Yousse	EL HABIBI	8235PIERRE MAGUET	MTL	514-8158987	\N	202406250357	\N	\N	\N	\N	\N	t	\N
358	Huji	HOPE	12473RUEVOLTAIRE	MTL	514-7744105	\N	202406250358	\N	\N	\N	\N	\N	t	\N
359	Sebastie	PEROMBELON	11635 5È AVENUE	MTL	514-4398152	\N	202408240359	\N	\N	\N	\N	\N	t	\N
360	Dyla	ALLEN	12534 PIER BLANCHETE	MTL	514-7430955	\N	202410060360	\N	\N	\N	\N	\N	t	\N
361	Chiar	TUTINO	40 JEAN PIERRE FERLAND	BOIS BRIAND	514-7818846	\N	202408190361	\N	\N	\N	\N	\N	t	\N
362	Dor	TUTINO	40 JEAN PIERRE FERLAND	BOIS BRIAND	514-7818846	\N	202408190362	\N	\N	\N	\N	\N	t	\N
363	Elizabet	TRIASSI	40 JEAN PIERRE FERLAND	BOIS BRIAND	514-7818846	\N	202408190363	\N	\N	\N	\N	\N	t	\N
364	Ram	BELKHOUJA	12291 LEPOL-HOULE	MTL	6550109 /  5733232	\N	202411240364	\N	\N	\N	\N	\N	t	\N
365	Mohamed kali	LENGLIZ	12260 64É AVENU	MTL	514-5763126	\N	202409120365	\N	\N	\N	\N	\N	t	\N
366	Ty	AL,HAJJM	VENU CHRARL GEE	MTL	514-6411104	\N	202409120366	\N	\N	\N	\N	\N	t	\N
367	Le	AL,HAJJM	VENU CHRARL GEE	MTL	514-6411104	\N	202409120367	\N	\N	\N	\N	\N	t	\N
368	Nazim raya	HAFINNE	8543 AVENUE DANIEL DOUY	MTL	514-5865384	\N	202409110368	\N	\N	\N	\N	\N	t	\N
369	Alessi	DISCIULLO	10289 RUE DES EGANTIERS	RDP	514-8146195	\N	202409160369	\N	\N	\N	\N	\N	t	\N
370	Alexande	VISUONE	91 33É BOIS-DES-FILON	MTL	514-4674299	\N	202409170370	\N	\N	\N	\N	\N	t	\N
371	Alou	ADDIME	602 RUE MON PETIT	LONG	514-4310568	\N	202409180371	\N	\N	\N	\N	\N	t	\N
372	Henr	DAYAIN	6710 RUE TERDIF APP 8	MTL	438-6994511	\N	202409180372	\N	\N	\N	\N	\N	t	\N
373	Saa	LYASSAMI	8053 RUE PHILLIPE-DE-CARION	MTL	438-4029970	\N	202409200373	\N	\N	\N	\N	\N	t	\N
374	Pasqual	DOS SANTOS	12210 JEN-YVES BIGIAS	MTL	514-8380444	\N	202408080374	\N	\N	\N	\N	\N	t	\N
375	Samue	CELESTIN	9322 GABRIELLE-ROY	MTL	438-9921050	\N	202409260375	\N	\N	\N	\N	\N	t	\N
376	Waae	KEKAGHEL	12385 AVENUE-MATTE	MTL	 /  462-7020	\N	202503300376	\N	\N	\N	\N	\N	t	\N
377	Mezhou	ABDALLAH	5657 JOSEPH DUFORESN	MTL-N	 /  578-6572	\N	202410020377	\N	\N	\N	\N	\N	t	\N
378	Imen	AMAKOUCHE			 /  836-3964	\N	202410020378	\N	\N	\N	\N	\N	t	\N
379	Fadi	GOUDA			 /  823-8985	\N	202410020379	\N	\N	\N	\N	\N	t	\N
380	Oma	BELKHOUJA	12291 AV LEOPOLD HOULÉ	MTL	514-5733232	\N	202410030380	\N	\N	\N	\N	\N	t	\N
381	Foua	ATMANI	10151 RUE KARL LÉVEQUE	MTL	438-9909351	\N	202410030381	\N	\N	\N	\N	\N	t	\N
382	Youse	ATMANI	10151 RUE KARL LÉVEQUE	MTL	438-9909351	\N	202410030382	\N	\N	\N	\N	\N	t	\N
383	Anas ahme	ATMANI	10151 RUE KARL LÉVEQUE	MTL	438-9909351	\N	202410030383	\N	\N	\N	\N	\N	t	\N
384	Dalto	DANY			514-4488006	\N	202411130384	\N	\N	\N	\N	\N	t	\N
385	Datilm	VRONSKY	7550 JACQUES ROUSSENU		514-7727206	\N	202410150385	\N	\N	\N	\N	\N	t	\N
386	Emil	LAURIER	8545 ERNEST OUINNET	MTL	514-6258377	\N	202502240386	\N	\N	\N	\N	\N	t	\N
387	Jerr	TEGUIMDJE ZEBA	6369ANTHUR CHEVRIER	MTL	819-5986436	\N	202410160387	\N	\N	\N	\N	\N	t	\N
388	Rache	TARHOUI	7415BAUDELAIRE	ST-JÉ	514-9296025	\N	202501130388	\N	\N	\N	\N	\N	t	\N
389	Amin maile	OUAJDI	263 RUE DE PIGALLE	REP	514-2669558	\N	202410230389	\N	\N	\N	\N	\N	t	\N
390	Sar	MOR	8771 AVENUE LOUIS-LUMIERE	MTL	514-9689428	\N	202501290390	\N	\N	\N	\N	\N	t	\N
391	Mohamed-amen	ABALLOUL	9224 RUE DUSGUENAY	MTL	514-9981604	\N	202410290391	\N	\N	\N	\N	\N	t	\N
392	Ahmed-tare	ABALLOUL	9224 RUE DUSGUENAY	MTL	514-9981604	\N	202410290392	\N	\N	\N	\N	\N	t	\N
393	Marya	ABALLOUL	9224 RUE DUSGUENAY	MTL	514-9981604	\N	202410290393	\N	\N	\N	\N	\N	t	\N
394	Ayoub-nabi	ABALLOUL	9224 RUE DUSGUENAY	MTL	514-9981604	\N	202410290394	\N	\N	\N	\N	\N	t	\N
395	Ade	BENCHEIKH	2447RUEYOLD	MTL	514-2492518	\N	202411050395	\N	\N	\N	\N	\N	t	\N
396	Ay	MARZAQ	11894AV LARCHEVÉQUE	MTL	438-9883989	\N	202411140396	\N	\N	\N	\N	\N	t	\N
397	Nadi	ZID	7415BAUDELAIRE	MTL	514-9296025	\N	202411140397	\N	\N	\N	\N	\N	t	\N
398	Yasmin	TARHOUNI	7415BAUDELAIRE	MTL	514-9296025	\N	202411140398	\N	\N	\N	\N	\N	t	\N
399	Sirin	TARHOUNI	7415BAUDELAIRE	MTL	514-9296025	\N	202411140399	\N	\N	\N	\N	\N	t	\N
400	Sohai	BOUCEKKINE	12112 27ÉAVENU	MTL	514-4730849	\N	202411180400	\N	\N	\N	\N	\N	t	\N
401	Sale	NAZIM	12186EMMANELBRIFFA	MTL	514-3188485	\N	202501010401	\N	\N	\N	\N	\N	t	\N
402	Ada	OUALI	7225 MICHELET   N29	MTL	514-7138081	\N	202501140402	\N	\N	\N	\N	\N	t	\N
403	Kayde	PETIT-TRERE	7585BOULPERRES	MTL	438-2237379	\N	202501240403	\N	\N	\N	\N	\N	t	\N
404	Ayman	BENJELLOUN	11497 BOUL  LANGELIER N 1	MTL	514-4732991	\N	202501310404	\N	\N	\N	\N	\N	t	\N
405	Mala	BENJELLOUN	11497 BOUL  LANGELIER N 1	MTL	514-4732991	\N	202501310405	\N	\N	\N	\N	\N	t	\N
406	Yehi	HALAWA	12132AV FERNARD-GOUTHIER	MTL	514-5180273	\N	202502260406	\N	\N	\N	\N	\N	t	\N
407	Mirk	VENDITTI	12391 VOLTAIRE	MTL	514-9933469	\N	202502200407	\N	\N	\N	\N	\N	t	\N
408	Alessandr	BOZZO	12245 RUE DES IRIS	MTL	438-4908000	\N	202502260408	\N	\N	\N	\N	\N	t	\N
409	Christophe	BOZZO	12245 RUE DES IRIS	MTL	438-4908000	\N	202502260409	\N	\N	\N	\N	\N	t	\N
410	Pineaul	VERONIQUE		MTL	514-4388359376	\N	202503290410	\N	\N	\N	\N	\N	t	\N
411	Ildris	REMY	8855 BOUL PERRAS	MTL	438-8880420	\N	202502260411	\N	\N	\N	\N	\N	t	\N
412	Benjami	FILARISMA	11610 AVE ALLARD	MTL	438-4018703	\N	202503180412	\N	\N	\N	\N	\N	t	\N
413	Ney	ZOIAOUI	12556 CROISSANT ALICE-GUY	MTL	438-9248888	\N	202504010413	\N	\N	\N	\N	\N	t	\N
414	Matte	PICCOLI	12391  94 AVENU	MTL	514-9136314	\N	202503250414	\N	\N	\N	\N	\N	t	\N
415	Giulian	PICCOLI	12391  94 AVENU	MTL	514-9136314	\N	202503250415	\N	\N	\N	\N	\N	t	\N
416	Abderrahi	BOUDOUMA	8814 DIARRAS	MTL	514-8161835	\N	202504020416	\N	\N	\N	\N	\N	t	\N
\.


--
-- TOC entry 4931 (class 0 OID 19933)
-- Dependencies: 230
-- Data for Name: event; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.event (id, event_name, event_type, event_date, end_date, amount) FROM stdin;
1	Taekwondo - Examen passage de ceinture	Passage de grade	2025-05-10	\N	40
\.


--
-- TOC entry 4932 (class 0 OID 19939)
-- Dependencies: 231
-- Data for Name: event_attendee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.event_attendee (id, event_id, customer_id, payed, amount) FROM stdin;
3	1	247	t	40
4	1	386	t	40
5	1	302	t	40
\.


--
-- TOC entry 4935 (class 0 OID 19944)
-- Dependencies: 234
-- Data for Name: member; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.member (id, firstname, lastname, email, password, active) FROM stdin;
1	Fabrice	POULIN	fabrice.poulin7@gmail.com	b6584ec93b75d9ad3d9566240b254bfd1add304914d58ff136053ea1b54abace	t
\.


--
-- TOC entry 4937 (class 0 OID 19951)
-- Dependencies: 236
-- Data for Name: payment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payment (id, subscription_id, amount, payment_date) FROM stdin;
1	1	850	2023-09-22
2	2	750	2018-10-02
3	3	750	2018-10-02
4	4	500	2024-02-01
5	5	850	2023-09-20
6	6	244	2005-08-01
7	7	300	2023-09-11
8	8	320	2021-11-26
9	9	500	2023-11-09
10	10	500	2023-12-01
11	11	450	2023-09-07
12	12	0	2023-09-07
13	13	400	2019-09-10
14	14	610	2022-11-15
15	15	200	2020-02-20
16	16	500	2009-09-01
17	17	440	2017-10-01
18	18	850	2016-04-16
19	19	750	2016-04-16
20	20	300	2023-11-01
21	21	800	2017-02-08
22	22	340	2024-01-11
23	23	250	2019-10-25
24	24	300	2021-12-07
25	25	800	2021-06-12
26	26	300	2020-07-03
27	27	500	2024-01-01
28	28	850	2023-05-01
29	29	340	2023-08-08
30	30	0	2021-07-05
31	31	900	2023-09-12
32	32	340	2023-11-20
33	33	250	2018-03-15
34	34	250	2018-03-15
35	35	120	2024-04-24
36	36	0	2015-04-30
37	37	300	2018-10-11
38	38	0	2023-02-08
39	39	850	2022-11-22
40	40	850	2022-11-22
41	41	370	2025-01-23
42	42	850	2023-09-25
43	43	800	2022-10-16
44	44	850	2022-10-12
45	45	950	2024-01-16
46	46	450	2024-08-02
47	47	950	2024-06-13
48	48	0	2016-09-30
49	49	200	2016-09-30
50	50	0	2016-09-30
51	51	330	2019-04-19
52	52	950	2024-10-30
53	53	320	2023-02-04
54	54	600	2021-09-15
55	55	400	2020-02-03
56	56	200	2019-11-01
57	57	500	2014-01-07
58	58	0	2022-11-01
59	59	340	2023-10-25
60	60	340	2023-10-25
61	121	200	2018-10-01
62	61	230	2022-08-05
63	62	800	2021-09-05
64	63	400	2023-04-11
65	64	400	2023-04-11
66	65	400	2023-06-09
67	66	320	2022-03-09
68	67	850	2022-10-13
69	68	320	2022-09-16
70	69	160	2021-10-09
71	70	340	2024-03-05
72	71	425	2022-01-12
73	72	375	2022-01-12
74	73	450	2022-01-01
75	74	0	2022-01-01
76	75	750	2019-04-06
77	76	850	2022-04-09
78	77	160	2005-11-06
79	78	950	2023-11-06
80	79	155	2020-08-26
81	80	900	2023-08-16
82	81	850	2023-01-12
83	82	400	2023-08-02
84	83	400	2023-08-02
85	84	350	2019-10-22
86	85	0	2020-02-10
87	86	895	2023-08-08
88	87	800	2021-08-21
89	88	850	2022-08-21
90	89	475	2023-11-01
91	90	550	2010-05-05
92	91	550	2010-05-05
93	92	0	2023-05-02
94	93	500	2017-09-01
95	94	0	2017-09-01
96	95	500	2017-09-01
97	96	350	2024-11-24
98	97	800	2023-01-05
99	98	200	2023-07-10
100	99	800	2021-09-15
101	100	800	2004-12-31
102	101	320	2022-03-18
103	102	250	2020-09-18
104	103	250	2020-09-18
105	104	320	2021-09-16
106	105	320	2021-09-16
107	106	400	2017-12-19
108	107	850	2023-06-15
109	108	850	2021-11-05
110	109	200	2022-11-23
111	110	350	2024-08-04
112	111	200	2020-02-05
113	112	320	2023-08-20
114	113	820	2022-09-23
115	114	150	2020-03-01
116	115	150	2020-03-01
117	116	750	2022-08-08
118	117	900	2022-11-10
119	118	0	2025-03-29
120	119	850	2022-06-20
121	120	850	2022-08-20
122	122	300	2020-10-01
123	123	300	2020-10-01
124	124	250	2018-10-03
125	125	500	2019-02-06
126	126	500	2019-02-06
127	127	480	2019-02-06
128	128	850	2023-02-26
129	129	850	2022-11-01
130	130	950	2024-11-01
131	131	950	2024-09-28
132	132	120	2025-03-31
133	133	0	2023-03-09
134	134	850	2023-11-10
135	135	400	2023-11-15
136	136	350	2023-02-18
137	137	500	2023-09-21
138	138	600	2018-11-15
139	139	850	2023-09-22
140	140	850	2024-09-01
141	141	340	2024-06-14
142	142	950	2024-09-01
143	143	450	2024-06-21
144	144	100	2019-09-23
145	145	100	2019-09-23
146	146	100	2007-04-01
147	147	850	2023-01-01
148	148	890	2023-02-15
149	149	850	2024-09-21
150	150	300	2023-01-14
151	151	850	2022-11-20
152	152	850	2023-05-10
153	153	500	2022-09-09
154	154	340	2023-10-16
155	155	300	2019-04-14
156	156	850	2023-09-01
157	157	950	2023-06-10
158	158	850	2022-10-15
159	159	850	2022-10-15
160	160	850	2022-10-15
161	161	850	2022-01-06
162	162	0	2022-09-25
163	163	850	2022-08-28
164	164	925	2024-08-01
165	165	350	2024-10-15
166	166	850	2023-06-16
167	167	850	2022-10-10
168	168	400	2021-09-18
169	169	950	2024-10-14
170	170	850	2022-03-28
171	171	850	2022-08-09
172	172	850	2022-08-09
173	173	120	2023-06-02
174	174	300	2019-04-10
175	175	320	2024-03-01
176	176	500	2018-10-08
177	177	250	2018-10-08
178	178	170	2020-01-22
179	179	900	2022-10-23
180	180	850	2022-05-11
181	181	800	2022-11-02
182	182	500	2022-11-10
183	183	950	2023-12-01
184	184	750	2021-06-14
185	185	350	2019-01-14
186	186	450	2007-09-24
187	187	520	2023-06-20
188	188	850	2023-12-02
189	189	0	2024-02-08
190	190	325	2022-02-16
191	191	900	2021-08-11
192	192	105	2020-01-12
193	193	850	2022-09-19
194	194	800	2023-11-05
195	195	700	2023-11-05
196	196	0	2023-11-05
197	197	0	2023-11-05
198	198	500	2018-10-01
199	199	500	2018-10-01
200	200	160	2007-04-12
201	201	300	2023-10-13
202	202	400	2023-01-10
203	203	750	2022-03-15
204	204	750	2022-03-15
205	205	300	2022-10-05
206	206	800	2021-10-19
207	207	750	2019-03-29
208	208	0	2023-10-02
209	209	450	2022-11-05
210	210	804.83	2013-09-25
211	211	850	2022-08-08
212	212	800	2022-05-01
213	213	800	2022-05-01
214	214	100	2022-03-09
215	215	100	2019-11-25
216	216	200	2017-02-08
217	217	700	2019-01-05
218	218	320	2023-05-23
219	219	400	2024-01-09
220	220	200	2021-10-09
221	221	340	2023-12-23
222	222	700	2024-10-30
223	223	700	2024-10-30
224	224	300	2017-11-09
225	225	900	2023-08-28
226	226	850	2022-10-15
227	227	300	2022-02-28
228	228	340	2023-11-06
229	229	900	2024-01-02
230	230	320	2023-08-14
231	231	0	2023-07-12
232	232	340	2024-04-15
233	233	0	2023-01-12
234	234	200	2020-01-15
235	235	320	2024-03-11
236	236	325	2023-09-19
237	237	0	2020-02-16
238	238	500	2020-02-16
239	239	200	2018-10-08
240	240	450	2019-08-20
241	241	750	2020-01-20
242	242	100	2021-07-15
243	243	750	2020-02-03
244	244	750	2020-02-03
245	245	350	2023-01-05
246	246	950	2023-10-04
247	247	320	2023-10-09
248	248	850	2023-01-27
249	249	850	2022-11-25
250	250	300	2023-09-11
251	251	500	2024-09-09
252	252	500	2024-09-09
253	253	340	2023-11-24
254	254	340	2023-11-24
255	255	150	2018-12-12
256	256	150	2018-12-12
257	257	0	2019-04-09
258	258	500	2019-04-09
259	259	750	2021-08-25
260	260	850	2023-05-04
261	261	850	2023-05-04
262	262	850	2023-05-04
263	263	0	2023-03-10
264	264	760	2018-09-05
265	265	500	2018-01-24
266	266	0	2010-06-15
267	267	950	2024-05-17
268	268	0	2021-10-21
269	269	400	2019-06-09
270	270	200	2021-09-01
271	271	0	2021-05-25
272	272	0	2018-01-12
273	273	220	2022-05-02
274	274	400	2023-06-02
275	275	400	2024-02-14
276	276	400	2024-02-14
277	277	400	2024-03-20
278	278	850	2022-10-12
279	279	850	2023-05-01
280	280	370	2020-01-10
281	281	300	2023-11-22
282	282	850	2022-09-20
283	283	850	2022-09-20
284	284	700	2022-09-20
285	285	325	2023-05-01
286	286	0	2022-12-11
287	287	330	2022-03-02
288	288	100	2023-11-23
289	289	100	2023-11-23
290	290	700	2023-02-01
291	291	800	2021-11-02
292	292	800	2021-11-02
293	293	0	2022-06-30
294	294	320	2022-10-06
295	295	320	2022-10-06
296	296	850	2022-10-15
297	297	450	2024-08-30
298	298	800	2021-11-02
299	299	850	2023-09-01
300	300	300	2019-05-02
301	301	0	2003-07-23
302	302	0	2003-07-23
303	303	620	2024-09-20
304	304	475	2021-06-04
305	305	850	2023-09-28
306	306	850	2023-09-28
307	307	360	2019-09-17
308	308	340	2023-11-30
309	309	400	2024-11-29
310	310	340	2023-12-01
311	311	340	2024-03-04
312	312	340	2024-03-04
313	313	315	2023-12-13
314	314	315	2023-12-13
315	315	900	2024-03-12
316	316	340	2023-12-19
317	317	850	2023-01-01
318	318	950	2024-01-17
319	319	850	2024-01-15
320	320	850	2024-01-15
321	321	350	2024-01-14
322	322	350	2024-01-14
323	323	0	2024-01-20
324	324	160	2024-06-15
325	325	500	2024-01-11
326	326	340	2024-02-04
327	327	370	2025-01-13
328	328	340	2024-02-05
329	329	500	2025-01-01
330	330	340	2024-03-14
331	331	345	2024-02-16
332	332	340	2024-02-24
333	333	950	2024-03-05
334	334	950	2024-06-03
335	335	340	2024-03-06
336	336	340	2024-04-05
337	337	320	2024-03-11
338	338	250	2024-03-25
339	339	340	2024-04-11
340	340	340	2024-04-11
341	341	200	2024-04-18
342	342	340	2024-04-24
343	343	340	2024-04-29
344	344	340	2024-04-13
345	345	340	2024-03-06
346	346	400	2024-05-09
347	347	400	2024-05-09
348	348	500	2024-04-10
349	349	500	2024-04-10
350	350	360	2024-08-02
351	351	400	2024-04-10
352	352	420	2024-08-23
353	353	340	2024-06-14
354	354	420	2024-09-11
355	355	0	2024-06-19
356	356	340	2024-06-24
357	357	340	2024-06-25
358	358	340	2024-06-25
359	359	130	2024-08-24
360	360	350	2024-10-06
361	361	950	2024-08-19
362	362	950	2024-08-19
363	363	950	2024-08-19
364	364	350	2024-11-24
365	365	950	2024-09-12
366	366	325	2024-09-12
367	367	325	2024-09-12
368	368	350	2024-09-11
369	369	350	2024-09-16
370	370	0	2024-09-17
371	371	350	2024-09-18
372	372	350	2024-09-18
373	373	475	2024-09-20
374	374	0	2024-08-08
375	375	0	2024-09-26
376	376	350	2025-03-30
377	377	370	2024-10-02
378	378	525	2024-10-02
379	379	240	2024-10-02
380	380	350	2024-10-03
381	381	330	2024-10-03
382	382	330	2024-10-03
383	383	330	2024-10-03
384	384	120	2024-11-13
385	385	546.13	2024-10-15
386	386	365	2025-02-24
387	387	321	2024-10-16
388	388	350	2025-01-13
389	389	420	2024-10-23
390	390	350	2025-01-29
391	391	950	2024-10-29
392	392	950	2024-10-29
393	393	950	2024-10-29
394	394	450	2024-10-29
395	395	350	2024-11-05
396	396	950	2024-11-14
397	397	350	2024-11-14
398	398	350	2024-11-14
399	399	350	2024-11-14
400	400	300	2024-11-18
401	401	950	2025-01-01
402	402	350	2025-01-14
403	403	350	2025-01-24
404	404	350	2025-01-31
405	405	350	2025-01-31
406	406	350	2025-02-26
407	407	350	2025-02-20
408	408	350	2025-02-26
409	409	350	2025-02-26
410	410	120	2025-03-29
411	411	350	2025-02-26
412	412	350	2025-03-18
413	413	350	2025-04-01
414	414	350	2025-03-25
415	415	350	2025-03-25
416	416	350	2025-04-02
\.


--
-- TOC entry 4948 (class 0 OID 20050)
-- Dependencies: 247
-- Data for Name: purchase; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.purchase (id, customer_id, article_id, number, discount, purchase_date) FROM stdin;
1	416	2	3	0	2025-05-15 11:40:10.114526
2	247	2	1	0	2025-05-15 00:00:00
3	28	4	1	0	2025-05-15 00:00:00
4	14	2	1	0	2025-05-15 00:00:00
\.


--
-- TOC entry 4939 (class 0 OID 19955)
-- Dependencies: 238
-- Data for Name: subscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subscription (id, customer_id, duration, taekwondo, kickboxing, taekibodo, amount, subscription_date) FROM stdin;
1	1	365	t	f	f	850	2023-09-22
2	2	365	t	f	f	750	2018-10-02
3	3	365	t	f	f	750	2018-10-02
4	4	365	f	t	f	950	2024-02-01
5	5	365	f	t	f	850	2023-09-20
6	6	365	f	t	f	488	2005-08-01
7	7	365	f	t	f	900	2023-09-11
8	8	90	t	f	f	320	2021-11-26
9	9	365	t	f	f	900	2023-11-09
10	10	365	t	f	f	950	2023-12-01
11	11	365	t	f	f	900	2023-09-07
12	12	365	t	f	f	900	2023-09-07
13	13	365	t	f	f	750	2019-09-10
14	14	365	f	t	f	850	2022-11-15
15	15	365	t	f	f	750	2020-02-20
16	16	365	t	f	f	500	2009-09-01
17	17	365	t	f	f	750	2017-10-01
18	18	365	t	f	f	850	2016-04-16
19	19	365	t	f	f	750	2016-04-16
20	20	365	f	t	f	950	2023-11-01
21	21	365	f	t	f	800	2017-02-08
22	22	90	f	t	f	340	2024-01-11
23	23	365	f	t	f	750	2019-10-25
24	24	90	t	f	f	325	2021-12-07
25	25	365	f	t	f	800	2021-06-12
26	26	365	f	t	f	0	2020-07-03
27	27	365	f	t	f	850	2024-01-01
28	28	365	f	t	f	850	2023-05-01
29	29	365	f	t	f	950	2023-08-08
30	30	365	f	f	t	0	2021-07-05
31	31	365	f	t	f	900	2023-09-12
32	32	90	f	t	f	340	2023-11-20
33	33	365	t	f	f	750	2018-03-15
34	34	365	t	f	f	750	2018-03-15
35	35	30	f	t	f	120	2024-04-24
36	36	365	t	f	f	650	2015-04-30
37	37	365	t	f	f	750	2018-10-11
38	38	365	f	f	t	900	2023-02-08
39	39	365	f	f	t	850	2022-11-22
40	40	365	f	f	t	850	2022-11-22
41	41	90	f	t	f	370	2025-01-23
42	42	365	t	f	f	850	2023-09-25
43	43	365	t	f	f	850	2022-10-16
44	44	365	f	t	f	850	2022-10-12
45	45	365	f	t	f	950	2024-01-16
46	46	365	f	t	f	850	2024-08-02
47	47	365	f	t	f	950	2024-06-13
48	48	365	t	f	f	650	2016-09-30
49	49	365	t	f	f	650	2016-09-30
50	50	365	t	f	f	650	2016-09-30
51	51	365	f	t	f	750	2019-04-19
52	52	365	f	t	f	950	2024-10-30
53	53	90	f	t	f	320	2023-02-04
54	54	365	f	f	f	0	2021-09-15
55	55	365	t	f	f	750	2020-02-03
56	56	365	f	t	f	750	2019-11-01
57	57	365	f	t	f	500	2014-01-07
58	58	90	f	t	f	320	2022-11-01
59	59	90	f	t	f	340	2023-10-25
60	60	90	f	t	f	340	2023-10-25
61	121	365	f	f	t	750	2018-10-01
62	61	90	f	t	f	350	2022-08-05
63	62	365	f	t	f	800	2021-09-05
64	63	365	f	t	f	850	2023-04-11
65	64	365	f	t	f	850	2023-04-11
66	65	365	t	f	f	850	2023-06-09
67	66	90	f	t	f	320	2022-03-09
68	67	365	t	f	f	850	2022-10-13
69	68	90	f	t	f	320	2022-09-16
70	69	90	f	t	f	320	2021-10-09
71	70	90	f	t	f	340	2024-03-05
72	71	365	t	f	f	850	2022-01-12
73	72	365	t	f	f	850	2022-01-12
74	73	365	t	f	f	850	2022-01-01
75	74	365	t	f	f	850	2022-01-01
76	75	365	t	f	f	750	2019-04-06
77	76	365	t	f	f	850	2022-04-09
78	77	90	f	t	f	160	2005-11-06
79	78	365	f	t	f	950	2023-11-06
80	79	365	f	t	f	750	2020-08-26
81	80	365	t	f	f	900	2023-08-16
82	81	365	f	t	f	850	2023-01-12
83	82	365	t	f	f	850	2023-08-02
84	83	365	f	t	f	850	2023-08-02
85	84	365	t	f	f	750	2019-10-22
86	85	365	t	f	f	500	2020-02-10
87	86	365	f	t	f	900	2023-08-08
88	87	365	t	f	f	800	2021-08-21
89	88	365	t	f	f	850	2022-08-21
90	89	365	t	f	f	950	2023-11-01
91	90	365	t	f	f	550	2010-05-05
92	91	365	t	f	f	550	2010-05-05
93	92	365	f	f	f	900	2023-05-02
94	93	365	t	f	f	650	2017-09-01
95	94	365	t	f	f	650	2017-09-01
96	95	365	t	f	f	650	2017-09-01
97	96	365	t	f	f	950	2024-11-24
98	97	365	t	f	f	850	2023-01-05
99	98	365	f	t	f	900	2023-07-10
100	99	365	t	f	f	800	2021-09-15
101	100	365	t	f	f	800	2004-12-31
102	101	90	f	t	f	320	2022-03-18
103	102	365	t	f	f	750	2020-09-18
104	103	365	t	f	f	750	2020-09-18
105	104	365	t	f	f	800	2021-09-16
106	105	365	t	f	f	800	2021-09-16
107	106	365	f	t	f	750	2017-12-19
108	107	365	f	t	f	850	2023-06-15
109	108	365	f	t	f	850	2021-11-05
110	109	90	f	t	f	320	2022-11-23
111	110	90	f	t	f	350	2024-08-04
112	111	365	f	f	t	850	2020-02-05
113	112	90	f	t	f	320	2023-08-20
114	113	365	t	f	f	820	2022-09-23
115	114	365	t	f	f	750	2020-03-01
116	115	365	t	f	f	750	2020-03-01
117	116	365	t	f	f	850	2022-08-08
118	117	365	f	t	f	900	2022-11-10
119	118	365	t	f	f	0	2025-03-29
120	119	365	t	f	f	850	2022-06-20
121	120	365	f	t	f	850	2022-08-20
122	122	365	t	f	f	750	2020-10-01
123	123	365	t	f	f	750	2020-10-01
124	124	365	f	t	f	750	2018-10-03
125	125	365	t	f	f	750	2019-02-06
126	126	365	t	f	f	750	2019-02-06
127	127	365	t	f	f	750	2019-02-06
128	128	365	f	t	f	850	2023-02-26
129	129	365	f	t	f	850	2022-11-01
130	130	365	f	t	f	950	2024-11-01
131	131	365	t	f	f	950	2024-09-28
132	132	30	f	t	f	120	2025-03-31
133	133	90	t	f	f	0	2023-03-09
134	134	365	f	t	f	850	2023-11-10
135	135	365	f	t	f	900	2023-11-15
136	136	90	f	t	f	350	2023-02-18
137	137	365	t	f	f	850	2023-09-21
138	138	365	t	f	f	0	2018-11-15
139	139	365	t	f	f	850	2023-09-22
140	140	365	t	f	f	850	2024-09-01
141	141	90	f	t	f	340	2024-06-14
142	142	365	f	t	f	950	2024-09-01
143	143	365	t	f	f	950	2024-06-21
144	144	365	t	f	f	660	2019-09-23
145	145	365	t	f	f	660	2019-09-23
146	146	365	t	f	f	620	2007-04-01
147	147	365	f	t	f	850	2023-01-01
148	148	365	f	t	f	890	2023-02-15
149	149	365	t	f	f	850	2024-09-21
150	150	365	f	t	f	900	2023-01-14
151	151	365	t	f	f	850	2022-11-20
152	152	365	t	f	f	850	2023-05-10
153	153	365	f	t	f	850	2022-09-09
154	154	90	f	t	f	340	2023-10-16
155	155	365	t	f	f	750	2019-04-14
156	156	365	t	f	f	850	2023-09-01
157	157	365	f	t	f	900	2023-06-10
158	158	365	t	f	f	850	2022-10-15
159	159	365	t	f	f	850	2022-10-15
160	160	365	t	f	f	850	2022-10-15
161	161	365	t	f	f	850	2022-01-06
162	162	365	t	f	f	850	2022-09-25
163	163	365	f	t	f	850	2022-08-28
164	164	365	f	t	f	925	2024-08-01
165	165	90	t	f	f	350	2024-10-15
166	166	365	t	f	f	850	2023-06-16
167	167	365	f	t	f	850	2022-10-10
168	168	365	f	t	f	800	2021-09-18
169	169	365	t	f	f	950	2024-10-14
170	170	365	f	t	f	850	2022-03-28
171	171	365	t	f	f	850	2022-08-09
172	172	365	t	f	f	850	2022-08-09
173	173	30	f	f	f	120	2023-06-02
174	174	365	f	t	f	750	2019-04-10
175	175	90	f	t	f	320	2024-03-01
176	176	365	f	t	f	750	2018-10-08
177	177	365	f	t	f	750	2018-10-08
178	178	365	f	t	f	750	2020-01-22
179	179	365	f	t	f	900	2022-10-23
180	180	365	f	t	f	850	2022-05-11
181	181	365	t	f	f	850	2022-11-02
182	182	365	t	f	f	800	2022-11-10
183	183	365	t	f	f	950	2023-12-01
184	184	365	f	f	f	750	2021-06-14
185	185	365	f	t	f	750	2019-01-14
186	186	365	f	t	f	450	2007-09-24
187	187	365	f	t	f	850	2023-06-20
188	188	365	t	f	f	850	2023-12-02
189	189	365	t	f	f	900	2024-02-08
190	190	90	t	f	f	325	2022-02-16
191	191	365	f	f	f	950	2021-08-11
192	192	365	f	t	f	750	2020-01-12
193	193	365	f	t	f	850	2022-09-19
194	194	365	t	f	f	850	2023-11-05
195	195	365	t	f	f	850	2023-11-05
196	196	365	t	f	f	800	2023-11-05
197	197	365	t	f	f	400	2023-11-05
198	198	365	t	f	f	850	2018-10-01
199	199	365	t	f	f	850	2018-10-01
200	200	90	f	t	f	160	2007-04-12
201	201	365	t	f	f	900	2023-10-13
202	202	365	t	f	f	850	2023-01-10
203	203	365	t	f	f	750	2022-03-15
204	204	365	t	f	f	750	2022-03-15
205	205	365	t	f	f	850	2022-10-05
206	206	365	t	f	f	800	2021-10-19
207	207	365	t	f	f	750	2019-03-29
208	208	365	t	f	f	0	2023-10-02
209	209	365	t	f	f	850	2022-11-05
210	210	365	t	f	f	804.83	2013-09-25
211	211	365	f	t	f	850	2022-08-08
212	212	365	t	f	f	800	2022-05-01
213	213	365	t	f	f	800	2022-05-01
214	214	90	f	t	f	320	2022-03-09
215	215	365	f	t	f	862	2019-11-25
216	216	365	f	t	f	750	2017-02-08
217	217	365	f	t	f	750	2019-01-05
218	218	90	f	t	f	320	2023-05-23
219	219	365	f	t	f	950	2024-01-09
220	220	90	t	f	f	320	2021-10-09
221	221	90	f	t	f	340	2023-12-23
222	222	365	t	f	f	850	2024-10-30
223	223	365	t	f	f	850	2024-10-30
224	224	365	t	f	f	750	2017-11-09
225	225	365	t	f	f	900	2023-08-28
226	226	365	t	f	f	850	2022-10-15
227	227	365	f	t	f	850	2022-02-28
228	228	90	f	t	f	340	2023-11-06
229	229	365	t	f	f	900	2024-01-02
230	230	90	f	t	f	320	2023-08-14
231	231	180	f	t	f	0	2023-07-12
232	232	90	f	t	f	340	2024-04-15
233	233	90	f	t	f	320	2023-01-12
234	234	365	f	t	f	750	2020-01-15
235	235	90	f	t	f	320	2024-03-11
236	236	90	f	f	f	325	2023-09-19
237	237	365	t	f	f	750	2020-02-16
238	238	365	t	f	f	750	2020-02-16
239	239	365	t	f	f	750	2018-10-08
240	240	365	t	f	f	750	2019-08-20
241	241	365	f	t	f	750	2020-01-20
242	242	90	t	f	f	320	2021-07-15
243	243	365	t	f	f	750	2020-02-03
244	244	365	t	f	f	750	2020-02-03
245	245	90	f	f	t	350	2023-01-05
246	246	365	f	f	f	950	2023-10-04
247	247	90	f	t	f	320	2023-10-09
248	248	365	f	t	f	850	2023-01-27
249	249	365	t	f	f	850	2022-11-25
250	250	365	f	t	f	900	2023-09-11
251	251	365	t	f	f	800	2024-09-09
252	252	365	t	f	f	800	2024-09-09
253	253	90	f	t	f	340	2023-11-24
254	254	90	f	t	f	340	2023-11-24
255	255	365	t	f	f	750	2018-12-12
256	256	365	t	f	f	750	2018-12-12
257	257	365	f	f	t	750	2019-04-09
258	258	365	t	f	f	750	2019-04-09
259	259	365	t	f	f	750	2021-08-25
260	260	365	f	t	f	850	2023-05-04
261	261	365	f	t	f	850	2023-05-04
262	262	365	f	t	f	850	2023-05-04
263	263	365	t	f	f	850	2023-03-10
264	264	365	t	f	f	760	2018-09-05
265	265	365	t	f	f	750	2018-01-24
266	266	365	f	t	f	0	2010-06-15
267	267	365	t	f	f	950	2024-05-17
268	268	365	f	t	f	0	2021-10-21
269	269	365	t	f	f	750	2019-06-09
270	270	90	f	t	f	320	2021-09-01
271	271	365	t	f	f	750	2021-05-25
272	272	365	t	f	f	750	2018-01-12
273	273	365	f	t	f	850	2022-05-02
274	274	365	f	t	f	850	2023-06-02
275	275	90	f	f	t	400	2024-02-14
276	276	90	f	f	t	400	2024-02-14
277	277	90	f	f	t	400	2024-03-20
278	278	365	f	t	f	850	2022-10-12
279	279	365	f	t	f	850	2023-05-01
280	280	365	f	t	f	750	2020-01-10
281	281	365	t	f	f	950	2023-11-22
282	282	365	t	f	f	850	2022-09-20
283	283	365	t	f	f	850	2022-09-20
284	284	365	t	f	f	700	2022-09-20
285	285	90	f	t	f	325	2023-05-01
286	286	365	f	t	f	850	2022-12-11
287	287	90	f	t	f	330	2022-03-02
288	288	30	t	f	f	100	2023-11-23
289	289	30	t	f	f	100	2023-11-23
290	290	365	f	t	f	700	2023-02-01
291	291	365	t	f	f	800	2021-11-02
292	292	365	t	f	f	800	2021-11-02
293	293	365	t	f	f	850	2022-06-30
294	294	90	t	f	f	320	2022-10-06
295	295	90	t	f	f	320	2022-10-06
296	296	365	t	f	f	850	2022-10-15
297	297	365	t	f	f	950	2024-08-30
298	298	365	t	f	f	800	2021-11-02
299	299	365	f	t	f	850	2023-09-01
300	300	365	t	f	f	750	2019-05-02
301	301	3654	t	f	f	0	2003-07-23
302	302	365	t	f	f	0	2003-07-23
303	303	365	t	f	f	620	2024-09-20
304	304	365	f	t	f	790	2021-06-04
305	305	365	t	f	f	850	2023-09-28
306	306	365	t	f	f	850	2023-09-28
307	307	365	t	f	f	750	2019-09-17
308	308	90	t	f	f	340	2023-11-30
309	309	365	t	f	f	950	2024-11-29
310	310	90	f	t	f	340	2023-12-01
311	311	90	t	f	f	340	2024-03-04
312	312	90	t	f	f	340	2024-03-04
313	313	365	t	f	f	950	2023-12-13
314	314	365	t	f	f	950	2023-12-13
315	315	365	t	f	f	900	2024-03-12
316	316	90	f	t	f	340	2023-12-19
317	317	365	f	t	f	850	2023-01-01
318	318	365	f	t	f	950	2024-01-17
319	319	365	t	f	f	850	2024-01-15
320	320	365	t	f	f	850	2024-01-15
321	321	365	t	f	f	950	2024-01-14
322	322	365	t	f	f	950	2024-01-14
323	323	90	f	f	t	320	2024-01-20
324	324	365	f	t	f	900	2024-06-15
325	325	365	t	f	f	500	2024-01-11
326	326	90	f	t	f	340	2024-02-04
327	327	90	f	t	f	370	2025-01-13
328	328	90	f	t	f	340	2024-02-05
329	329	365	t	f	f	0	2025-01-01
330	330	90	f	t	f	340	2024-03-14
331	331	90	f	t	f	345	2024-02-16
332	332	90	f	t	f	340	2024-02-24
333	333	365	t	f	f	950	2024-03-05
334	334	365	t	f	f	950	2024-06-03
335	335	90	f	t	f	340	2024-03-06
336	336	90	f	t	f	340	2024-04-05
337	337	90	f	t	f	320	2024-03-11
338	338	120	f	t	f	500	2024-03-25
339	339	90	f	f	t	340	2024-04-11
340	340	90	f	f	t	340	2024-04-11
341	341	365	t	f	f	950	2024-04-18
342	342	90	f	t	f	340	2024-04-24
343	343	90	f	f	t	340	2024-04-29
344	344	90	t	f	f	340	2024-04-13
345	345	90	f	t	f	340	2024-03-06
346	346	365	t	f	f	950	2024-05-09
347	347	365	t	f	f	950	2024-05-09
348	348	365	f	t	f	950	2024-04-10
349	349	365	f	t	f	950	2024-04-10
350	350	90	f	t	f	360	2024-08-02
351	351	365	f	t	f	950	2024-04-10
352	352	90	f	t	f	420	2024-08-23
353	353	90	f	t	f	340	2024-06-14
354	354	90	f	f	t	420	2024-09-11
355	355	90	f	t	f	340	2024-06-19
356	356	90	f	t	f	340	2024-06-24
357	357	90	f	t	f	340	2024-06-25
358	358	90	f	t	f	340	2024-06-25
359	359	90	f	t	f	370	2024-08-24
360	360	90	f	t	f	350	2024-10-06
361	361	365	t	f	f	950	2024-08-19
362	362	365	t	f	f	950	2024-08-19
363	363	365	t	f	f	950	2024-08-19
364	364	365	t	f	f	0	2024-11-24
365	365	365	t	f	f	950	2024-09-12
366	366	90	t	f	f	325	2024-09-12
367	367	90	t	f	f	325	2024-09-12
368	368	90	f	f	f	350	2024-09-11
369	369	90	f	t	f	350	2024-09-16
370	370	365	t	f	f	950	2024-09-17
371	371	90	f	t	f	350	2024-09-18
372	372	90	f	t	f	350	2024-09-18
373	373	365	f	t	f	950	2024-09-20
374	374	365	f	t	f	0	2024-08-08
375	375	365	t	f	f	0	2024-09-26
376	376	90	f	t	f	350	2025-03-30
377	377	90	f	t	f	370	2024-10-02
378	378	90	t	f	f	525	2024-10-02
379	379	30	t	f	f	240	2024-10-02
380	380	365	t	f	f	0	2024-10-03
381	381	90	t	f	f	330	2024-10-03
382	382	90	t	f	f	330	2024-10-03
383	383	90	t	f	f	330	2024-10-03
384	384	30	t	f	f	120	2024-11-13
385	385	365	t	f	f	0	2024-10-15
386	386	90	f	f	t	365	2025-02-24
387	387	365	f	t	f	950	2024-10-16
388	388	90	t	f	f	350	2025-01-13
389	389	90	f	f	t	420	2024-10-23
390	390	90	f	f	t	350	2025-01-29
391	391	365	t	f	f	950	2024-10-29
392	392	365	t	f	f	950	2024-10-29
393	393	365	t	f	f	950	2024-10-29
394	394	365	t	f	f	475	2024-10-29
395	395	90	f	t	f	350	2024-11-05
396	396	365	f	f	t	950	2024-11-14
397	397	90	f	t	f	350	2024-11-14
398	398	90	f	t	f	350	2024-11-14
399	399	90	f	t	f	350	2024-11-14
400	400	90	f	t	f	350	2024-11-18
401	401	365	t	f	f	950	2025-01-01
402	402	90	t	f	f	350	2025-01-14
403	403	90	f	t	f	350	2025-01-24
404	404	90	t	f	f	350	2025-01-31
405	405	90	t	f	f	350	2025-01-31
406	406	90	f	t	f	350	2025-02-26
407	407	90	t	f	f	350	2025-02-20
408	408	90	f	t	f	350	2025-02-26
409	409	90	f	t	f	350	2025-02-26
410	410	30	t	f	f	120	2025-03-29
411	411	90	t	f	f	350	2025-02-26
412	412	90	t	f	f	350	2025-03-18
413	413	90	t	f	f	350	2025-04-01
414	414	90	f	t	f	350	2025-03-25
415	415	90	f	t	f	350	2025-03-25
416	416	90	f	t	f	350	2025-04-02
\.


--
-- TOC entry 4944 (class 0 OID 20032)
-- Dependencies: 243
-- Data for Name: weight_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.weight_category (id, name, gender, sport, federation, above_limit, below_limit) FROM stdin;
1	FEATHERWEIGHT	Homme	Kickboxing	ISKA	104.1	108
2	LIGHTWEIGHT	Homme	Kickboxing	ISKA	108.1	112
3	SUPERLIGHTWEIGHT	Homme	Kickboxing	ISKA	112.1	116
4	LIGHT WELTERWEIGHT	Homme	Kickboxing	ISKA	116.1	120
5	WELTERWEIGHT	Homme	Kickboxing	ISKA	120.1	125
6	SUPERWELTERWEIGHT	Homme	Kickboxing	ISKA	125.1	130
7	LIGHT MIDDLEWEIGHT	Homme	Kickboxing	ISKA	130.1	136
8	MIDDLEWEIGHT	Homme	Kickboxing	ISKA	136.1	142
9	SUPERMIDDLEWEIGHT	Homme	Kickboxing	ISKA	142.1	149
10	LIGHT HEAVYWEIGHT	Homme	Kickboxing	ISKA	149.1	156
11	LIGHT CRUISERWEIGHT	Homme	Kickboxing	ISKA	156.1	164
12	CRUISERWEIGHT	Homme	Kickboxing	ISKA	164.1	172
13	SUPERCRUISERWEIGHT	Homme	Kickboxing	ISKA	172.1	182
14	HEAVYWEIGHT	Homme	Kickboxing	ISKA	182.1	195
15	SUPERHEAVYWEIGHT	Homme	Kickboxing	ISKA	195.1	1000
\.


--
-- TOC entry 4965 (class 0 OID 0)
-- Dependencies: 240
-- Name: accesslog_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.accesslog_id_seq', 1, true);


--
-- TOC entry 4966 (class 0 OID 0)
-- Dependencies: 218
-- Name: alldata_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.alldata_id_seq', 416, true);


--
-- TOC entry 4967 (class 0 OID 0)
-- Dependencies: 244
-- Name: article_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.article_id_seq', 5, true);


--
-- TOC entry 4968 (class 0 OID 0)
-- Dependencies: 222
-- Name: batch_job_execution_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.batch_job_execution_seq', 1, true);


--
-- TOC entry 4969 (class 0 OID 0)
-- Dependencies: 224
-- Name: batch_job_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.batch_job_seq', 1, true);


--
-- TOC entry 4970 (class 0 OID 0)
-- Dependencies: 227
-- Name: batch_step_execution_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.batch_step_execution_seq', 4, true);


--
-- TOC entry 4971 (class 0 OID 0)
-- Dependencies: 229
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_id_seq', 1, false);


--
-- TOC entry 4972 (class 0 OID 0)
-- Dependencies: 232
-- Name: event_attendee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.event_attendee_id_seq', 5, true);


--
-- TOC entry 4973 (class 0 OID 0)
-- Dependencies: 233
-- Name: event_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.event_id_seq', 1, true);


--
-- TOC entry 4974 (class 0 OID 0)
-- Dependencies: 235
-- Name: member_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.member_id_seq', 1, true);


--
-- TOC entry 4975 (class 0 OID 0)
-- Dependencies: 237
-- Name: payment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.payment_id_seq', 416, true);


--
-- TOC entry 4976 (class 0 OID 0)
-- Dependencies: 246
-- Name: purchase_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.purchase_id_seq', 4, true);


--
-- TOC entry 4977 (class 0 OID 0)
-- Dependencies: 239
-- Name: subscription_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subscription_id_seq', 416, true);


--
-- TOC entry 4978 (class 0 OID 0)
-- Dependencies: 242
-- Name: weight_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.weight_category_id_seq', 15, true);


--
-- TOC entry 4761 (class 2606 OID 20030)
-- Name: accesslog accesslog_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accesslog
    ADD CONSTRAINT accesslog_pkey PRIMARY KEY (id);


--
-- TOC entry 4735 (class 2606 OID 19967)
-- Name: alldata alldata_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alldata
    ADD CONSTRAINT alldata_pkey PRIMARY KEY (id);


--
-- TOC entry 4765 (class 2606 OID 20048)
-- Name: article article_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_pkey PRIMARY KEY (id);


--
-- TOC entry 4739 (class 2606 OID 19969)
-- Name: batch_job_execution_context batch_job_execution_context_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution_context
    ADD CONSTRAINT batch_job_execution_context_pkey PRIMARY KEY (job_execution_id);


--
-- TOC entry 4737 (class 2606 OID 19971)
-- Name: batch_job_execution batch_job_execution_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution
    ADD CONSTRAINT batch_job_execution_pkey PRIMARY KEY (job_execution_id);


--
-- TOC entry 4741 (class 2606 OID 19973)
-- Name: batch_job_instance batch_job_instance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_instance
    ADD CONSTRAINT batch_job_instance_pkey PRIMARY KEY (job_instance_id);


--
-- TOC entry 4747 (class 2606 OID 19975)
-- Name: batch_step_execution_context batch_step_execution_context_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_step_execution_context
    ADD CONSTRAINT batch_step_execution_context_pkey PRIMARY KEY (step_execution_id);


--
-- TOC entry 4745 (class 2606 OID 19977)
-- Name: batch_step_execution batch_step_execution_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_step_execution
    ADD CONSTRAINT batch_step_execution_pkey PRIMARY KEY (step_execution_id);


--
-- TOC entry 4749 (class 2606 OID 19979)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- TOC entry 4753 (class 2606 OID 19981)
-- Name: event_attendee event_attendee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event_attendee
    ADD CONSTRAINT event_attendee_pkey PRIMARY KEY (id);


--
-- TOC entry 4751 (class 2606 OID 19983)
-- Name: event event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_pkey PRIMARY KEY (id);


--
-- TOC entry 4743 (class 2606 OID 19985)
-- Name: batch_job_instance job_inst_un; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_instance
    ADD CONSTRAINT job_inst_un UNIQUE (job_name, job_key);


--
-- TOC entry 4755 (class 2606 OID 19987)
-- Name: member member_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_pkey PRIMARY KEY (id);


--
-- TOC entry 4757 (class 2606 OID 19989)
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);


--
-- TOC entry 4767 (class 2606 OID 20056)
-- Name: purchase purchase_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT purchase_pkey PRIMARY KEY (id);


--
-- TOC entry 4759 (class 2606 OID 19991)
-- Name: subscription subscription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscription_pkey PRIMARY KEY (id);


--
-- TOC entry 4763 (class 2606 OID 20039)
-- Name: weight_category weight_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.weight_category
    ADD CONSTRAINT weight_category_pkey PRIMARY KEY (id);


--
-- TOC entry 4769 (class 2606 OID 19992)
-- Name: batch_job_execution_context job_exec_ctx_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution_context
    ADD CONSTRAINT job_exec_ctx_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- TOC entry 4770 (class 2606 OID 19997)
-- Name: batch_job_execution_params job_exec_params_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution_params
    ADD CONSTRAINT job_exec_params_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- TOC entry 4771 (class 2606 OID 20002)
-- Name: batch_step_execution job_exec_step_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_step_execution
    ADD CONSTRAINT job_exec_step_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- TOC entry 4768 (class 2606 OID 20007)
-- Name: batch_job_execution job_inst_exec_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution
    ADD CONSTRAINT job_inst_exec_fk FOREIGN KEY (job_instance_id) REFERENCES public.batch_job_instance(job_instance_id);


--
-- TOC entry 4772 (class 2606 OID 20012)
-- Name: batch_step_execution_context step_exec_ctx_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_step_execution_context
    ADD CONSTRAINT step_exec_ctx_fk FOREIGN KEY (step_execution_id) REFERENCES public.batch_step_execution(step_execution_id);


-- Completed on 2025-05-15 21:07:21

--
-- PostgreSQL database dump complete
--

