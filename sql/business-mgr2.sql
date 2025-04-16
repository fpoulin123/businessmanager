--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-04-16 15:46:49

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

--
-- TOC entry 240 (class 1255 OID 16768)
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
-- TOC entry 232 (class 1259 OID 16739)
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
-- TOC entry 228 (class 1259 OID 16735)
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
-- TOC entry 4912 (class 0 OID 0)
-- Dependencies: 228
-- Name: alldata_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.alldata_id_seq OWNED BY public.alldata.id;


--
-- TOC entry 220 (class 1259 OID 16654)
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
-- TOC entry 224 (class 1259 OID 16700)
-- Name: batch_job_execution_context; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batch_job_execution_context (
    job_execution_id bigint NOT NULL,
    short_context character varying(2500) NOT NULL,
    serialized_context text
);


ALTER TABLE public.batch_job_execution_context OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16666)
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
-- TOC entry 226 (class 1259 OID 16713)
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
-- TOC entry 219 (class 1259 OID 16647)
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
-- TOC entry 227 (class 1259 OID 16714)
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
-- TOC entry 222 (class 1259 OID 16676)
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
-- TOC entry 223 (class 1259 OID 16688)
-- Name: batch_step_execution_context; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batch_step_execution_context (
    step_execution_id bigint NOT NULL,
    short_context character varying(2500) NOT NULL,
    serialized_context text
);


ALTER TABLE public.batch_step_execution_context OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16712)
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
-- TOC entry 233 (class 1259 OID 16747)
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
    email character varying(100)
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16736)
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
-- TOC entry 4913 (class 0 OID 0)
-- Dependencies: 229
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- TOC entry 239 (class 1259 OID 16777)
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
-- TOC entry 237 (class 1259 OID 16770)
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
-- TOC entry 236 (class 1259 OID 16769)
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
-- TOC entry 4914 (class 0 OID 0)
-- Dependencies: 236
-- Name: event_attendee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.event_attendee_id_seq OWNED BY public.event_attendee.id;


--
-- TOC entry 238 (class 1259 OID 16776)
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
-- TOC entry 4915 (class 0 OID 0)
-- Dependencies: 238
-- Name: event_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.event_id_seq OWNED BY public.event.id;


--
-- TOC entry 218 (class 1259 OID 16633)
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
-- TOC entry 217 (class 1259 OID 16615)
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
-- TOC entry 4916 (class 0 OID 0)
-- Dependencies: 217
-- Name: member_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.member_id_seq OWNED BY public.member.id;


--
-- TOC entry 235 (class 1259 OID 16761)
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
-- TOC entry 231 (class 1259 OID 16738)
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
-- TOC entry 4917 (class 0 OID 0)
-- Dependencies: 231
-- Name: payment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.payment_id_seq OWNED BY public.payment.id;


--
-- TOC entry 234 (class 1259 OID 16755)
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
-- TOC entry 230 (class 1259 OID 16737)
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
-- TOC entry 4918 (class 0 OID 0)
-- Dependencies: 230
-- Name: subscription_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.subscription_id_seq OWNED BY public.subscription.id;


--
-- TOC entry 4701 (class 2604 OID 16742)
-- Name: alldata id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alldata ALTER COLUMN id SET DEFAULT nextval('public.alldata_id_seq'::regclass);


--
-- TOC entry 4702 (class 2604 OID 16750)
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- TOC entry 4706 (class 2604 OID 16780)
-- Name: event id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event ALTER COLUMN id SET DEFAULT nextval('public.event_id_seq'::regclass);


--
-- TOC entry 4705 (class 2604 OID 16773)
-- Name: event_attendee id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event_attendee ALTER COLUMN id SET DEFAULT nextval('public.event_attendee_id_seq'::regclass);


--
-- TOC entry 4699 (class 2604 OID 16636)
-- Name: member id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member ALTER COLUMN id SET DEFAULT nextval('public.member_id_seq'::regclass);


--
-- TOC entry 4704 (class 2604 OID 16764)
-- Name: payment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment ALTER COLUMN id SET DEFAULT nextval('public.payment_id_seq'::regclass);


--
-- TOC entry 4703 (class 2604 OID 16758)
-- Name: subscription id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription ALTER COLUMN id SET DEFAULT nextval('public.subscription_id_seq'::regclass);


--
-- TOC entry 4899 (class 0 OID 16739)
-- Dependencies: 232
-- Data for Name: alldata; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alldata (id, firstname, lastname, address, city, phonenumber, course, inscriptiondate, duration, amount, account1, solde1, account2, solde2, account3, solde3, solditems, email) FROM stdin;
\.


--
-- TOC entry 4887 (class 0 OID 16654)
-- Dependencies: 220
-- Data for Name: batch_job_execution; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch_job_execution (job_execution_id, version, job_instance_id, create_time, start_time, end_time, status, exit_code, exit_message, last_updated) FROM stdin;
1	2	1	2025-03-10 14:41:28.919151	2025-03-10 14:41:28.962035	2025-03-10 14:41:31.432426	COMPLETED	COMPLETED		2025-03-10 14:41:31.432426
\.


--
-- TOC entry 4891 (class 0 OID 16700)
-- Dependencies: 224
-- Data for Name: batch_job_execution_context; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch_job_execution_context (job_execution_id, short_context, serialized_context) FROM stdin;
1	rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAANYmF0Y2gudmVyc2lvbnQABTUuMC4weA==	\N
\.


--
-- TOC entry 4888 (class 0 OID 16666)
-- Dependencies: 221
-- Data for Name: batch_job_execution_params; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch_job_execution_params (job_execution_id, parameter_name, parameter_type, parameter_value, identifying) FROM stdin;
1	run.id	java.lang.Long	1	Y
\.


--
-- TOC entry 4886 (class 0 OID 16647)
-- Dependencies: 219
-- Data for Name: batch_job_instance; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch_job_instance (job_instance_id, version, job_name, job_key) FROM stdin;
1	0	importUserJob	947cce338b790a4bb6cf8425e98bcf94
\.


--
-- TOC entry 4889 (class 0 OID 16676)
-- Dependencies: 222
-- Data for Name: batch_step_execution; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, create_time, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) FROM stdin;
1	69	step1	1	2025-03-10 14:41:28.98298	2025-03-10 14:41:28.989961	2025-03-10 14:41:30.642542	COMPLETED	67	660	150	510	0	0	0	0	COMPLETED		2025-03-10 14:41:30.643538
2	54	step2	1	2025-03-10 14:41:30.65351	2025-03-10 14:41:30.658497	2025-03-10 14:41:31.066407	COMPLETED	52	510	0	510	0	0	0	0	COMPLETED		2025-03-10 14:41:31.067403
3	54	step3	1	2025-03-10 14:41:31.077379	2025-03-10 14:41:31.082364	2025-03-10 14:41:31.427441	COMPLETED	52	510	0	510	0	0	0	0	COMPLETED		2025-03-10 14:41:31.428438
\.


--
-- TOC entry 4890 (class 0 OID 16688)
-- Dependencies: 223
-- Data for Name: batch_step_execution_context; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch_step_execution_context (step_execution_id, short_context, serialized_context) FROM stdin;
1	rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAEdAARYmF0Y2gudGFza2xldFR5cGV0AD1vcmcuc3ByaW5nZnJhbWV3b3JrLmJhdGNoLmNvcmUuc3RlcC5pdGVtLkNodW5rT3JpZW50ZWRUYXNrbGV0dAAZZGF0YUl0ZW1SZWFkZXIucmVhZC5jb3VudHNyABFqYXZhLmxhbmcuSW50ZWdlchLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAAAAKVdAANYmF0Y2gudmVyc2lvbnQABTUuMC4wdAAOYmF0Y2guc3RlcFR5cGV0ADdvcmcuc3ByaW5nZnJhbWV3b3JrLmJhdGNoLmNvcmUuc3RlcC50YXNrbGV0LlRhc2tsZXRTdGVweA==	\N
2	rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAEdAAYYWxsZGF0YXJlYWRlci5yZWFkLmNvdW50c3IAEWphdmEubGFuZy5JbnRlZ2VyEuKgpPeBhzgCAAFJAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAf90ABFiYXRjaC50YXNrbGV0VHlwZXQAPW9yZy5zcHJpbmdmcmFtZXdvcmsuYmF0Y2guY29yZS5zdGVwLml0ZW0uQ2h1bmtPcmllbnRlZFRhc2tsZXR0AA1iYXRjaC52ZXJzaW9udAAFNS4wLjB0AA5iYXRjaC5zdGVwVHlwZXQAN29yZy5zcHJpbmdmcmFtZXdvcmsuYmF0Y2guY29yZS5zdGVwLnRhc2tsZXQuVGFza2xldFN0ZXB4	\N
3	rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAEdAAYYWxsZGF0YXJlYWRlci5yZWFkLmNvdW50c3IAEWphdmEubGFuZy5JbnRlZ2VyEuKgpPeBhzgCAAFJAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAf90ABFiYXRjaC50YXNrbGV0VHlwZXQAPW9yZy5zcHJpbmdmcmFtZXdvcmsuYmF0Y2guY29yZS5zdGVwLml0ZW0uQ2h1bmtPcmllbnRlZFRhc2tsZXR0AA1iYXRjaC52ZXJzaW9udAAFNS4wLjB0AA5iYXRjaC5zdGVwVHlwZXQAN29yZy5zcHJpbmdmcmFtZXdvcmsuYmF0Y2guY29yZS5zdGVwLnRhc2tsZXQuVGFza2xldFN0ZXB4	\N
\.


--
-- TOC entry 4900 (class 0 OID 16747)
-- Dependencies: 233
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (id, firstname, lastname, address, city, phonenumber, picture, barcodevalue, email) FROM stdin;
2	Jacques	 Lefevre	 23 boulevard Saint-Laurent	 Montréal	 0145567890	\N	\N	 jacques.lefevre@email.com
3	Sophie	 Durand	 10 rue Saint-Jean	 Québec	 0123456789	\N	\N	 sophie.durand@email.com
4	Alain	 Bernard	 42 rue de l'Université	 Trois-Rivières	 0178392910	\N	\N	 alain.bernard@email.com
5	Claire	 Robert	 56 rue de la Paix	 Laval	 0321564789	\N	\N	 claire.robert@email.com
6	Thomas	 Petit	 78 rue de Verdun	 Montréal	 0612345678	\N	\N	 thomas.petit@email.com
7	Lucie	 Roux	 34 rue de l'Église	 Gatineau	 0739281940	\N	\N	 lucie.roux@email.com
8	Michel	 Lefevre	 67 rue des Érables	 Longueuil	 0987654321	\N	\N	 michel.lefevre@email.com
9	Emma	 Marchand	 89 avenue Cartier	 Sherbrooke	 0156321478	\N	\N	 emma.marchand@email.com
10	Marc	 Lefevre	 123 rue des Laurentides	 Laval	 0147362850	\N	\N	 marc.lefevre@email.com
11	Isabelle	 Laurent	 33 rue du Parc	 Montréal	 0624567834	\N	\N	 isabelle.laurent@email.com
12	Nicolas	 Dupuis	 45 rue de la Rivière	 Lévis	 0687439821	\N	\N	 nicolas.dupuis@email.com
13	Alice	 Lemoine	 56 rue de l'Industrie	 Montréal	 0156472910	\N	\N	 alice.lemoine@email.com
14	Pierre	 Boucher	 77 rue des Tilleuls	 Saint-Jean-sur-Richelieu	 0981264785	\N	\N	 pierre.boucher@email.com
15	Céline	 Barbier	 32 rue du Vieux-Port	 Québec	 0778123456	\N	\N	 celine.barbier@email.com
16	Didier	 Robert	 23 avenue de la Gare	 Trois-Rivières	 0156345987	\N	\N	 didier.robert@email.com
17	Sophie	 Rousseau	 55 rue des Bois	 Laval	 0624738294	\N	\N	 sophie.rousseau@email.com
18	François	 Martin	 102 rue des Lilas	 Gatineau	 0148529304	\N	\N	 francois.martin@email.com
19	Hélène	 Lefevre	 67 rue du Vieux-Montréal	 Montréal	 0623761987	\N	\N	 helene.lefevre@email.com
20	Xavier	 Durand	 89 rue de l'Acier	 Shawinigan	 0712564390	\N	\N	 xavier.durand@email.com
21	Camille	 Lefevre	 11 rue des Fleurs	 Trois-Rivières	 0692318475	\N	\N	 camille.lefevre@email.com
22	Laurent	 Lambert	 23 rue du Parc	 Laval	 0783921846	\N	\N	 laurent.lambert@email.com
23	Julie	 Lefevre	 54 rue de la Rivière	 Montréal	 0778391023	\N	\N	 julie.lefevre@email.com
24	Thomas	 Robert	 34 rue des Pins	 Québec	 0657382917	\N	\N	 thomas.robert@email.com
25	Pierre	 Lefevre	 15 rue du Parc	 Gatineau	 0603128479	\N	\N	 pierre.lefevre@email.com
26	Claire	 Dupuis	 101 rue du Lac	 Saint-Hyacinthe	 0168523947	\N	\N	 claire.dupuis@email.com
27	Lucien	 Bernard	 47 avenue du Mont-Royal	 Montréal	 0987412365	\N	\N	 lucien.bernard@email.com
28	Éric	 Marchand	 32 rue de la Ville	 Trois-Rivières	 0773892651	\N	\N	 eric.marchand@email.com
29	Isabelle	 Lefevre	 57 rue des Mimosas	 Laval	 0683748291	\N	\N	 isabelle.lefevre@email.com
30	Claire	 Lefevre	 22 rue de Saint-Jean	 Québec	 0661529370	\N	\N	 claire.lefevre@email.com
31	Thierry	 Robert	 39 rue de la Montagne	 Gatineau	 0159234738	\N	\N	 thierry.robert@email.com
32	Anne	 Lefevre	 102 rue des Pins	 Laval	 0783921045	\N	\N	 anne.lefevre@email.com
33	Michel	 Durand	 14 rue des Érables	 Longueuil	 0705674812	\N	\N	 michel.durand@email.com
34	Valérie	 Lefevre	 121 rue de l'Université	 Québec	 0612089347	\N	\N	 valerie.lefevre@email.com
35	Olivier	 Dupont	 13 rue de la Plage	 Rimouski	 0783019835	\N	\N	 olivier.dupont@email.com
36	Gérard	 Rousseau	 55 avenue du Parc	 Montréal	 0619238476	\N	\N	 gerard.rousseau@email.com
1	Marie	 Martin	 15 avenue des Pins	 Montréal	 0607080910	\N	202503180001	marie.martin@email.com
39	Fabrice	POULIN	6472 Avenue Pérodeau	Montréal	4185585106	./img/customerPictures/9b6d8c50-2ad1-41af-93e7-586257ec1df4.jpg	202503310039	test@test.com
\.


--
-- TOC entry 4906 (class 0 OID 16777)
-- Dependencies: 239
-- Data for Name: event; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.event (id, event_name, event_type, event_date, end_date, amount) FROM stdin;
1	Gala Kickboxing ICEMAN Gatineau	Compétition	2025-05-25	\N	0
\.


--
-- TOC entry 4904 (class 0 OID 16770)
-- Dependencies: 237
-- Data for Name: event_attendee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.event_attendee (id, event_id, customer_id, payed, amount) FROM stdin;
3	1	36	t	0
4	1	26	t	0
\.


--
-- TOC entry 4885 (class 0 OID 16633)
-- Dependencies: 218
-- Data for Name: member; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.member (id, firstname, lastname, email, password, active) FROM stdin;
1	admin	admin	admin@mybusiness.com	8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918	t
3	Jean	DUJARDINS	jean.dujardins@email.com	5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8	f
2	Fabrice	POULIN	fabrice.poulin7@gmail.com	b6584ec93b75d9ad3d9566240b254bfd1add304914d58ff136053ea1b54abace	t
\.


--
-- TOC entry 4902 (class 0 OID 16761)
-- Dependencies: 235
-- Data for Name: payment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payment (id, subscription_id, amount, payment_date) FROM stdin;
1	1	350	2025-03-17
2	2	200	2025-03-18
4	2	650	2025-03-19
5	4	350	2025-03-20
6	5	850	2025-01-01
7	6	850	2025-02-01
8	8	350	2023-06-07
9	3	350	2025-04-11
10	10	350	2025-04-11
\.


--
-- TOC entry 4901 (class 0 OID 16755)
-- Dependencies: 234
-- Data for Name: subscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subscription (id, customer_id, duration, taekwondo, kickboxing, taekibodo, amount, subscription_date) FROM stdin;
1	1	90	f	t	f	350	2025-03-17
2	6	90	t	t	f	850	2025-03-18
3	35	90	f	t	f	350	2025-03-19
4	2	90	t	t	f	350	2025-03-20
5	5	90	t	f	f	850	2025-01-01
6	4	365	f	t	f	850	2025-02-01
8	9	90	f	f	t	350	2023-06-09
9	3	90	f	t	f	350	2025-04-10
10	7	90	f	t	f	350	2025-04-11
11	8	30	f	t	f	350	2025-04-11
12	10	365	t	t	t	1200	2025-04-11
13	11	90	f	t	f	350	2025-04-11
\.


--
-- TOC entry 4919 (class 0 OID 0)
-- Dependencies: 228
-- Name: alldata_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.alldata_id_seq', 1, false);


--
-- TOC entry 4920 (class 0 OID 0)
-- Dependencies: 226
-- Name: batch_job_execution_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.batch_job_execution_seq', 1, true);


--
-- TOC entry 4921 (class 0 OID 0)
-- Dependencies: 227
-- Name: batch_job_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.batch_job_seq', 1, true);


--
-- TOC entry 4922 (class 0 OID 0)
-- Dependencies: 225
-- Name: batch_step_execution_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.batch_step_execution_seq', 3, true);


--
-- TOC entry 4923 (class 0 OID 0)
-- Dependencies: 229
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_id_seq', 39, true);


--
-- TOC entry 4924 (class 0 OID 0)
-- Dependencies: 236
-- Name: event_attendee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.event_attendee_id_seq', 4, true);


--
-- TOC entry 4925 (class 0 OID 0)
-- Dependencies: 238
-- Name: event_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.event_id_seq', 3, true);


--
-- TOC entry 4926 (class 0 OID 0)
-- Dependencies: 217
-- Name: member_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.member_id_seq', 3, true);


--
-- TOC entry 4927 (class 0 OID 0)
-- Dependencies: 231
-- Name: payment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.payment_id_seq', 10, true);


--
-- TOC entry 4928 (class 0 OID 0)
-- Dependencies: 230
-- Name: subscription_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subscription_id_seq', 13, true);


--
-- TOC entry 4723 (class 2606 OID 16746)
-- Name: alldata alldata_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alldata
    ADD CONSTRAINT alldata_pkey PRIMARY KEY (id);


--
-- TOC entry 4721 (class 2606 OID 16706)
-- Name: batch_job_execution_context batch_job_execution_context_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution_context
    ADD CONSTRAINT batch_job_execution_context_pkey PRIMARY KEY (job_execution_id);


--
-- TOC entry 4715 (class 2606 OID 16660)
-- Name: batch_job_execution batch_job_execution_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution
    ADD CONSTRAINT batch_job_execution_pkey PRIMARY KEY (job_execution_id);


--
-- TOC entry 4711 (class 2606 OID 16651)
-- Name: batch_job_instance batch_job_instance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_instance
    ADD CONSTRAINT batch_job_instance_pkey PRIMARY KEY (job_instance_id);


--
-- TOC entry 4719 (class 2606 OID 16694)
-- Name: batch_step_execution_context batch_step_execution_context_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_step_execution_context
    ADD CONSTRAINT batch_step_execution_context_pkey PRIMARY KEY (step_execution_id);


--
-- TOC entry 4717 (class 2606 OID 16682)
-- Name: batch_step_execution batch_step_execution_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_step_execution
    ADD CONSTRAINT batch_step_execution_pkey PRIMARY KEY (step_execution_id);


--
-- TOC entry 4725 (class 2606 OID 16754)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- TOC entry 4731 (class 2606 OID 16775)
-- Name: event_attendee event_attendee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event_attendee
    ADD CONSTRAINT event_attendee_pkey PRIMARY KEY (id);


--
-- TOC entry 4733 (class 2606 OID 16785)
-- Name: event event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_pkey PRIMARY KEY (id);


--
-- TOC entry 4713 (class 2606 OID 16653)
-- Name: batch_job_instance job_inst_un; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_instance
    ADD CONSTRAINT job_inst_un UNIQUE (job_name, job_key);


--
-- TOC entry 4709 (class 2606 OID 16640)
-- Name: member member_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_pkey PRIMARY KEY (id);


--
-- TOC entry 4729 (class 2606 OID 16766)
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);


--
-- TOC entry 4727 (class 2606 OID 16760)
-- Name: subscription subscription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscription_pkey PRIMARY KEY (id);


--
-- TOC entry 4738 (class 2606 OID 16707)
-- Name: batch_job_execution_context job_exec_ctx_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution_context
    ADD CONSTRAINT job_exec_ctx_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- TOC entry 4735 (class 2606 OID 16671)
-- Name: batch_job_execution_params job_exec_params_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution_params
    ADD CONSTRAINT job_exec_params_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- TOC entry 4736 (class 2606 OID 16683)
-- Name: batch_step_execution job_exec_step_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_step_execution
    ADD CONSTRAINT job_exec_step_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- TOC entry 4734 (class 2606 OID 16661)
-- Name: batch_job_execution job_inst_exec_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution
    ADD CONSTRAINT job_inst_exec_fk FOREIGN KEY (job_instance_id) REFERENCES public.batch_job_instance(job_instance_id);


--
-- TOC entry 4737 (class 2606 OID 16695)
-- Name: batch_step_execution_context step_exec_ctx_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_step_execution_context
    ADD CONSTRAINT step_exec_ctx_fk FOREIGN KEY (step_execution_id) REFERENCES public.batch_step_execution(step_execution_id);


-- Completed on 2025-04-16 15:46:49

--
-- PostgreSQL database dump complete
--

