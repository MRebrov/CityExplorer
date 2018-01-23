--
-- PostgreSQL database dump
--

-- Dumped from database version 10.1
-- Dumped by pg_dump version 10.1

-- Started on 2018-01-17 20:52:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2891 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 201 (class 1259 OID 16482)
-- Name: photos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE photos (
    id bigint NOT NULL,
    url character varying(250) NOT NULL,
    user_id bigint NOT NULL,
    spot_id bigint NOT NULL,
    upload_date date NOT NULL
);


ALTER TABLE photos OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16476)
-- Name: photos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE photos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE photos_id_seq OWNER TO postgres;

--
-- TOC entry 2892 (class 0 OID 0)
-- Dependencies: 198
-- Name: photos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE photos_id_seq OWNED BY photos.id;


--
-- TOC entry 200 (class 1259 OID 16480)
-- Name: photos_spot_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE photos_spot_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE photos_spot_id_seq OWNER TO postgres;

--
-- TOC entry 2893 (class 0 OID 0)
-- Dependencies: 200
-- Name: photos_spot_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE photos_spot_id_seq OWNED BY photos.spot_id;


--
-- TOC entry 199 (class 1259 OID 16478)
-- Name: photos_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE photos_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE photos_user_id_seq OWNER TO postgres;

--
-- TOC entry 2894 (class 0 OID 0)
-- Dependencies: 199
-- Name: photos_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE photos_user_id_seq OWNED BY photos.user_id;


--
-- TOC entry 210 (class 1259 OID 16547)
-- Name: quests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE quests (
    id bigint NOT NULL,
    name character varying(250) NOT NULL,
    description character varying(250) NOT NULL,
    spot_id bigint NOT NULL,
    original_photo_id bigint NOT NULL,
    upload_date date NOT NULL
);


ALTER TABLE quests OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16541)
-- Name: quests_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE quests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE quests_id_seq OWNER TO postgres;

--
-- TOC entry 2895 (class 0 OID 0)
-- Dependencies: 207
-- Name: quests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE quests_id_seq OWNED BY quests.id;


--
-- TOC entry 209 (class 1259 OID 16545)
-- Name: quests_original_photo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE quests_original_photo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE quests_original_photo_id_seq OWNER TO postgres;

--
-- TOC entry 2896 (class 0 OID 0)
-- Dependencies: 209
-- Name: quests_original_photo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE quests_original_photo_id_seq OWNED BY quests.original_photo_id;


--
-- TOC entry 208 (class 1259 OID 16543)
-- Name: quests_spot_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE quests_spot_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE quests_spot_id_seq OWNER TO postgres;

--
-- TOC entry 2897 (class 0 OID 0)
-- Dependencies: 208
-- Name: quests_spot_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE quests_spot_id_seq OWNED BY quests.spot_id;


--
-- TOC entry 206 (class 1259 OID 16526)
-- Name: spots; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE spots (
    id bigint NOT NULL,
    location character varying(250) NOT NULL,
    name character varying(250) NOT NULL,
    original_photo_id bigint NOT NULL,
    upload_date date NOT NULL
);


ALTER TABLE spots OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16522)
-- Name: spots_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE spots_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spots_id_seq OWNER TO postgres;

--
-- TOC entry 2898 (class 0 OID 0)
-- Dependencies: 204
-- Name: spots_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE spots_id_seq OWNED BY spots.id;


--
-- TOC entry 205 (class 1259 OID 16524)
-- Name: spots_original_photo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE spots_original_photo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spots_original_photo_id_seq OWNER TO postgres;

--
-- TOC entry 2899 (class 0 OID 0)
-- Dependencies: 205
-- Name: spots_original_photo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE spots_original_photo_id_seq OWNED BY spots.original_photo_id;


--
-- TOC entry 203 (class 1259 OID 16511)
-- Name: user_groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_groups (
    id bigint NOT NULL,
    name character varying(250) NOT NULL
);


ALTER TABLE user_groups OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16509)
-- Name: user_groups_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_groups_id_seq OWNER TO postgres;

--
-- TOC entry 2900 (class 0 OID 0)
-- Dependencies: 202
-- Name: user_groups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_groups_id_seq OWNED BY user_groups.id;


--
-- TOC entry 213 (class 1259 OID 16572)
-- Name: user_progress; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_progress (
    user_id bigint NOT NULL,
    quest_id bigint NOT NULL,
    quest_status character varying(250) NOT NULL,
    date_complete date
);


ALTER TABLE user_progress OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16570)
-- Name: user_progress_quest_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_progress_quest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_progress_quest_id_seq OWNER TO postgres;

--
-- TOC entry 2901 (class 0 OID 0)
-- Dependencies: 212
-- Name: user_progress_quest_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_progress_quest_id_seq OWNED BY user_progress.quest_id;


--
-- TOC entry 211 (class 1259 OID 16568)
-- Name: user_progress_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_progress_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_progress_user_id_seq OWNER TO postgres;

--
-- TOC entry 2902 (class 0 OID 0)
-- Dependencies: 211
-- Name: user_progress_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_progress_user_id_seq OWNED BY user_progress.user_id;


--
-- TOC entry 197 (class 1259 OID 16467)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    id bigint NOT NULL,
    group_id bigint NOT NULL,
    first_name character varying(250) NOT NULL,
    last_name character varying(250) NOT NULL,
    birthday date NOT NULL,
    email character varying(250) NOT NULL,
    registration_date date NOT NULL,
    password character varying(250) NOT NULL,
    ex_id bigint
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16465)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO postgres;

--
-- TOC entry 2903 (class 0 OID 0)
-- Dependencies: 196
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 2716 (class 2604 OID 16485)
-- Name: photos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photos ALTER COLUMN id SET DEFAULT nextval('photos_id_seq'::regclass);


--
-- TOC entry 2717 (class 2604 OID 16486)
-- Name: photos user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photos ALTER COLUMN user_id SET DEFAULT nextval('photos_user_id_seq'::regclass);


--
-- TOC entry 2718 (class 2604 OID 16487)
-- Name: photos spot_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photos ALTER COLUMN spot_id SET DEFAULT nextval('photos_spot_id_seq'::regclass);


--
-- TOC entry 2722 (class 2604 OID 16550)
-- Name: quests id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quests ALTER COLUMN id SET DEFAULT nextval('quests_id_seq'::regclass);


--
-- TOC entry 2723 (class 2604 OID 16551)
-- Name: quests spot_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quests ALTER COLUMN spot_id SET DEFAULT nextval('quests_spot_id_seq'::regclass);


--
-- TOC entry 2724 (class 2604 OID 16552)
-- Name: quests original_photo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quests ALTER COLUMN original_photo_id SET DEFAULT nextval('quests_original_photo_id_seq'::regclass);


--
-- TOC entry 2720 (class 2604 OID 16529)
-- Name: spots id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY spots ALTER COLUMN id SET DEFAULT nextval('spots_id_seq'::regclass);


--
-- TOC entry 2721 (class 2604 OID 16530)
-- Name: spots original_photo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY spots ALTER COLUMN original_photo_id SET DEFAULT nextval('spots_original_photo_id_seq'::regclass);


--
-- TOC entry 2719 (class 2604 OID 16514)
-- Name: user_groups id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_groups ALTER COLUMN id SET DEFAULT nextval('user_groups_id_seq'::regclass);


--
-- TOC entry 2725 (class 2604 OID 16575)
-- Name: user_progress user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_progress ALTER COLUMN user_id SET DEFAULT nextval('user_progress_user_id_seq'::regclass);


--
-- TOC entry 2726 (class 2604 OID 16576)
-- Name: user_progress quest_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_progress ALTER COLUMN quest_id SET DEFAULT nextval('user_progress_quest_id_seq'::regclass);


--
-- TOC entry 2715 (class 2604 OID 16470)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- TOC entry 2872 (class 0 OID 16482)
-- Dependencies: 201
-- Data for Name: photos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY photos (id, url, user_id, spot_id, upload_date) FROM stdin;
\.


--
-- TOC entry 2881 (class 0 OID 16547)
-- Dependencies: 210
-- Data for Name: quests; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY quests (id, name, description, spot_id, original_photo_id, upload_date) FROM stdin;
\.


--
-- TOC entry 2877 (class 0 OID 16526)
-- Dependencies: 206
-- Data for Name: spots; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY spots (id, location, name, original_photo_id, upload_date) FROM stdin;
\.


--
-- TOC entry 2874 (class 0 OID 16511)
-- Dependencies: 203
-- Data for Name: user_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_groups (id, name) FROM stdin;
\.


--
-- TOC entry 2884 (class 0 OID 16572)
-- Dependencies: 213
-- Data for Name: user_progress; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_progress (user_id, quest_id, quest_status, date_complete) FROM stdin;
\.


--
-- TOC entry 2868 (class 0 OID 16467)
-- Dependencies: 197
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id, group_id, first_name, last_name, birthday, email, registration_date, password, ex_id) FROM stdin;
\.


--
-- TOC entry 2904 (class 0 OID 0)
-- Dependencies: 198
-- Name: photos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('photos_id_seq', 1, false);


--
-- TOC entry 2905 (class 0 OID 0)
-- Dependencies: 200
-- Name: photos_spot_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('photos_spot_id_seq', 1, false);


--
-- TOC entry 2906 (class 0 OID 0)
-- Dependencies: 199
-- Name: photos_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('photos_user_id_seq', 1, false);


--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 207
-- Name: quests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('quests_id_seq', 1, false);


--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 209
-- Name: quests_original_photo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('quests_original_photo_id_seq', 1, false);


--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 208
-- Name: quests_spot_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('quests_spot_id_seq', 1, false);


--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 204
-- Name: spots_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('spots_id_seq', 1, false);


--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 205
-- Name: spots_original_photo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('spots_original_photo_id_seq', 1, false);


--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 202
-- Name: user_groups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_groups_id_seq', 1, false);


--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 212
-- Name: user_progress_quest_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_progress_quest_id_seq', 1, false);


--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 211
-- Name: user_progress_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_progress_user_id_seq', 1, false);


--
-- TOC entry 2915 (class 0 OID 0)
-- Dependencies: 196
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_id_seq', 1, false);


--
-- TOC entry 2730 (class 2606 OID 16489)
-- Name: photos photos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photos
    ADD CONSTRAINT photos_pkey PRIMARY KEY (id);


--
-- TOC entry 2736 (class 2606 OID 16557)
-- Name: quests quests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quests
    ADD CONSTRAINT quests_pkey PRIMARY KEY (id);


--
-- TOC entry 2734 (class 2606 OID 16535)
-- Name: spots spots_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY spots
    ADD CONSTRAINT spots_pkey PRIMARY KEY (id);


--
-- TOC entry 2732 (class 2606 OID 16516)
-- Name: user_groups user_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_groups
    ADD CONSTRAINT user_groups_pkey PRIMARY KEY (id);


--
-- TOC entry 2738 (class 2606 OID 16578)
-- Name: user_progress user_progress_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_progress
    ADD CONSTRAINT user_progress_pkey PRIMARY KEY (user_id, quest_id);


--
-- TOC entry 2728 (class 2606 OID 16475)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2739 (class 2606 OID 16517)
-- Name: users group_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT group_id FOREIGN KEY (group_id) REFERENCES user_groups(id);


--
-- TOC entry 2743 (class 2606 OID 16563)
-- Name: quests original_photo_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quests
    ADD CONSTRAINT original_photo_id FOREIGN KEY (original_photo_id) REFERENCES photos(id);


--
-- TOC entry 2745 (class 2606 OID 16585)
-- Name: user_progress quest_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_progress
    ADD CONSTRAINT quest_id FOREIGN KEY (quest_id) REFERENCES quests(id);


--
-- TOC entry 2741 (class 2606 OID 16536)
-- Name: photos spot_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photos
    ADD CONSTRAINT spot_id FOREIGN KEY (spot_id) REFERENCES spots(id);


--
-- TOC entry 2742 (class 2606 OID 16558)
-- Name: quests spot_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quests
    ADD CONSTRAINT spot_id FOREIGN KEY (spot_id) REFERENCES spots(id);


--
-- TOC entry 2740 (class 2606 OID 16504)
-- Name: photos user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photos
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 2744 (class 2606 OID 16580)
-- Name: user_progress user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_progress
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES users(id);


-- Completed on 2018-01-17 20:52:25

--
-- PostgreSQL database dump complete
--
