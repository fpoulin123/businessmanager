--
-- PostgreSQL database dump
--

-- Dumped from database version 16.8 (Ubuntu 16.8-0ubuntu0.24.04.1)
-- Dumped by pg_dump version 17.4

-- Started on 2025-05-15 21:20:03

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
-- TOC entry 215 (class 1259 OID 90113)
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
-- TOC entry 216 (class 1259 OID 90118)
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
-- TOC entry 3527 (class 0 OID 0)
-- Dependencies: 216
-- Name: accesslog_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.accesslog_id_seq OWNED BY public.accesslog.id;


--
-- TOC entry 217 (class 1259 OID 90119)
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
-- TOC entry 218 (class 1259 OID 90124)
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
-- TOC entry 3528 (class 0 OID 0)
-- Dependencies: 218
-- Name: alldata_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.alldata_id_seq OWNED BY public.alldata.id;


--
-- TOC entry 219 (class 1259 OID 90125)
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
-- TOC entry 220 (class 1259 OID 90130)
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
-- TOC entry 3529 (class 0 OID 0)
-- Dependencies: 220
-- Name: article_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.article_id_seq OWNED BY public.article.id;


--
-- TOC entry 221 (class 1259 OID 90131)
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
-- TOC entry 222 (class 1259 OID 90136)
-- Name: batch_job_execution_context; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batch_job_execution_context (
    job_execution_id bigint NOT NULL,
    short_context character varying(2500) NOT NULL,
    serialized_context text
);


ALTER TABLE public.batch_job_execution_context OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 90141)
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
-- TOC entry 224 (class 1259 OID 90146)
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
-- TOC entry 225 (class 1259 OID 90147)
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
-- TOC entry 226 (class 1259 OID 90150)
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
-- TOC entry 227 (class 1259 OID 90151)
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
-- TOC entry 228 (class 1259 OID 90156)
-- Name: batch_step_execution_context; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batch_step_execution_context (
    step_execution_id bigint NOT NULL,
    short_context character varying(2500) NOT NULL,
    serialized_context text
);


ALTER TABLE public.batch_step_execution_context OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 90161)
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
-- TOC entry 230 (class 1259 OID 90162)
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
-- TOC entry 231 (class 1259 OID 90168)
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
-- TOC entry 3530 (class 0 OID 0)
-- Dependencies: 231
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- TOC entry 232 (class 1259 OID 90169)
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
-- TOC entry 233 (class 1259 OID 90175)
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
-- TOC entry 234 (class 1259 OID 90178)
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
-- TOC entry 3531 (class 0 OID 0)
-- Dependencies: 234
-- Name: event_attendee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.event_attendee_id_seq OWNED BY public.event_attendee.id;


--
-- TOC entry 235 (class 1259 OID 90179)
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
-- TOC entry 3532 (class 0 OID 0)
-- Dependencies: 235
-- Name: event_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.event_id_seq OWNED BY public.event.id;


--
-- TOC entry 236 (class 1259 OID 90180)
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
-- TOC entry 237 (class 1259 OID 90186)
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
-- TOC entry 3533 (class 0 OID 0)
-- Dependencies: 237
-- Name: member_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.member_id_seq OWNED BY public.member.id;


--
-- TOC entry 238 (class 1259 OID 90187)
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
-- TOC entry 239 (class 1259 OID 90190)
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
-- TOC entry 3534 (class 0 OID 0)
-- Dependencies: 239
-- Name: payment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.payment_id_seq OWNED BY public.payment.id;


--
-- TOC entry 240 (class 1259 OID 90191)
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
-- TOC entry 241 (class 1259 OID 90196)
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
-- TOC entry 3535 (class 0 OID 0)
-- Dependencies: 241
-- Name: purchase_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.purchase_id_seq OWNED BY public.purchase.id;


--
-- TOC entry 242 (class 1259 OID 90197)
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
-- TOC entry 243 (class 1259 OID 90200)
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
-- TOC entry 3536 (class 0 OID 0)
-- Dependencies: 243
-- Name: subscription_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.subscription_id_seq OWNED BY public.subscription.id;


--
-- TOC entry 244 (class 1259 OID 90201)
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
-- TOC entry 245 (class 1259 OID 90206)
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
-- TOC entry 3537 (class 0 OID 0)
-- Dependencies: 245
-- Name: weight_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.weight_category_id_seq OWNED BY public.weight_category.id;


--
-- TOC entry 3324 (class 2604 OID 90207)
-- Name: accesslog id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accesslog ALTER COLUMN id SET DEFAULT nextval('public.accesslog_id_seq'::regclass);


--
-- TOC entry 3325 (class 2604 OID 90208)
-- Name: alldata id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alldata ALTER COLUMN id SET DEFAULT nextval('public.alldata_id_seq'::regclass);


--
-- TOC entry 3326 (class 2604 OID 90209)
-- Name: article id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article ALTER COLUMN id SET DEFAULT nextval('public.article_id_seq'::regclass);


--
-- TOC entry 3327 (class 2604 OID 90210)
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- TOC entry 3329 (class 2604 OID 90211)
-- Name: event id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event ALTER COLUMN id SET DEFAULT nextval('public.event_id_seq'::regclass);


--
-- TOC entry 3331 (class 2604 OID 90212)
-- Name: event_attendee id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event_attendee ALTER COLUMN id SET DEFAULT nextval('public.event_attendee_id_seq'::regclass);


--
-- TOC entry 3332 (class 2604 OID 90213)
-- Name: member id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member ALTER COLUMN id SET DEFAULT nextval('public.member_id_seq'::regclass);


--
-- TOC entry 3334 (class 2604 OID 90214)
-- Name: payment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment ALTER COLUMN id SET DEFAULT nextval('public.payment_id_seq'::regclass);


--
-- TOC entry 3335 (class 2604 OID 90215)
-- Name: purchase id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase ALTER COLUMN id SET DEFAULT nextval('public.purchase_id_seq'::regclass);


--
-- TOC entry 3338 (class 2604 OID 90216)
-- Name: subscription id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription ALTER COLUMN id SET DEFAULT nextval('public.subscription_id_seq'::regclass);


--
-- TOC entry 3339 (class 2604 OID 90217)
-- Name: weight_category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.weight_category ALTER COLUMN id SET DEFAULT nextval('public.weight_category_id_seq'::regclass);


--
-- TOC entry 3341 (class 2606 OID 90219)
-- Name: accesslog accesslog_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accesslog
    ADD CONSTRAINT accesslog_pkey PRIMARY KEY (id);


--
-- TOC entry 3343 (class 2606 OID 90221)
-- Name: alldata alldata_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alldata
    ADD CONSTRAINT alldata_pkey PRIMARY KEY (id);


--
-- TOC entry 3345 (class 2606 OID 90223)
-- Name: article article_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_pkey PRIMARY KEY (id);


--
-- TOC entry 3349 (class 2606 OID 90225)
-- Name: batch_job_execution_context batch_job_execution_context_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution_context
    ADD CONSTRAINT batch_job_execution_context_pkey PRIMARY KEY (job_execution_id);


--
-- TOC entry 3347 (class 2606 OID 90227)
-- Name: batch_job_execution batch_job_execution_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution
    ADD CONSTRAINT batch_job_execution_pkey PRIMARY KEY (job_execution_id);


--
-- TOC entry 3351 (class 2606 OID 90229)
-- Name: batch_job_instance batch_job_instance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_instance
    ADD CONSTRAINT batch_job_instance_pkey PRIMARY KEY (job_instance_id);


--
-- TOC entry 3357 (class 2606 OID 90231)
-- Name: batch_step_execution_context batch_step_execution_context_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_step_execution_context
    ADD CONSTRAINT batch_step_execution_context_pkey PRIMARY KEY (step_execution_id);


--
-- TOC entry 3355 (class 2606 OID 90233)
-- Name: batch_step_execution batch_step_execution_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_step_execution
    ADD CONSTRAINT batch_step_execution_pkey PRIMARY KEY (step_execution_id);


--
-- TOC entry 3359 (class 2606 OID 90235)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- TOC entry 3363 (class 2606 OID 90237)
-- Name: event_attendee event_attendee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event_attendee
    ADD CONSTRAINT event_attendee_pkey PRIMARY KEY (id);


--
-- TOC entry 3361 (class 2606 OID 90239)
-- Name: event event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_pkey PRIMARY KEY (id);


--
-- TOC entry 3353 (class 2606 OID 90241)
-- Name: batch_job_instance job_inst_un; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_instance
    ADD CONSTRAINT job_inst_un UNIQUE (job_name, job_key);


--
-- TOC entry 3365 (class 2606 OID 90243)
-- Name: member member_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_pkey PRIMARY KEY (id);


--
-- TOC entry 3367 (class 2606 OID 90245)
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);


--
-- TOC entry 3369 (class 2606 OID 90247)
-- Name: purchase purchase_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT purchase_pkey PRIMARY KEY (id);


--
-- TOC entry 3371 (class 2606 OID 90249)
-- Name: subscription subscription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscription_pkey PRIMARY KEY (id);


--
-- TOC entry 3373 (class 2606 OID 90251)
-- Name: weight_category weight_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.weight_category
    ADD CONSTRAINT weight_category_pkey PRIMARY KEY (id);


--
-- TOC entry 3375 (class 2606 OID 90252)
-- Name: batch_job_execution_context job_exec_ctx_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution_context
    ADD CONSTRAINT job_exec_ctx_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- TOC entry 3376 (class 2606 OID 90257)
-- Name: batch_job_execution_params job_exec_params_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution_params
    ADD CONSTRAINT job_exec_params_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- TOC entry 3377 (class 2606 OID 90262)
-- Name: batch_step_execution job_exec_step_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_step_execution
    ADD CONSTRAINT job_exec_step_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- TOC entry 3374 (class 2606 OID 90267)
-- Name: batch_job_execution job_inst_exec_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_job_execution
    ADD CONSTRAINT job_inst_exec_fk FOREIGN KEY (job_instance_id) REFERENCES public.batch_job_instance(job_instance_id);


--
-- TOC entry 3378 (class 2606 OID 90272)
-- Name: batch_step_execution_context step_exec_ctx_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_step_execution_context
    ADD CONSTRAINT step_exec_ctx_fk FOREIGN KEY (step_execution_id) REFERENCES public.batch_step_execution(step_execution_id);


-- Completed on 2025-05-15 21:20:05

--
-- PostgreSQL database dump complete
--

