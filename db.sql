--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.6
-- Dumped by pg_dump version 10.1

-- Started on 2018-03-01 00:32:14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2230 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 185 (class 1259 OID 16394)
-- Name: photos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE photos (
    photo_id bigint NOT NULL,
    url character varying(250) NOT NULL,
    user_id bigint NOT NULL,
    spot_id bigint NOT NULL,
    upload_date date NOT NULL
);


ALTER TABLE photos OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16397)
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
-- TOC entry 2231 (class 0 OID 0)
-- Dependencies: 186
-- Name: photos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE photos_id_seq OWNED BY photos.photo_id;


--
-- TOC entry 187 (class 1259 OID 16399)
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
-- TOC entry 2232 (class 0 OID 0)
-- Dependencies: 187
-- Name: photos_spot_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE photos_spot_id_seq OWNED BY photos.spot_id;


--
-- TOC entry 188 (class 1259 OID 16401)
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
-- TOC entry 2233 (class 0 OID 0)
-- Dependencies: 188
-- Name: photos_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE photos_user_id_seq OWNED BY photos.user_id;


--
-- TOC entry 189 (class 1259 OID 16403)
-- Name: quests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE quests (
    quest_id bigint NOT NULL,
    name character varying(250) NOT NULL,
    description character varying(250) NOT NULL,
    upload_date date NOT NULL,
    reward integer NOT NULL
);


ALTER TABLE quests OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16409)
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
-- TOC entry 2234 (class 0 OID 0)
-- Dependencies: 190
-- Name: quests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE quests_id_seq OWNED BY quests.quest_id;


--
-- TOC entry 191 (class 1259 OID 16415)
-- Name: spots; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE spots (
    spot_id bigint NOT NULL,
    location character varying(250) NOT NULL,
    name character varying(250) NOT NULL,
    upload_date date NOT NULL
);


ALTER TABLE spots OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 16421)
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
-- TOC entry 2235 (class 0 OID 0)
-- Dependencies: 192
-- Name: spots_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE spots_id_seq OWNED BY spots.spot_id;


--
-- TOC entry 202 (class 1259 OID 16516)
-- Name: spots_in_quests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE spots_in_quests (
    spots_in_quests_id integer NOT NULL,
    spot_id integer NOT NULL,
    quest_id integer NOT NULL,
    photo_id integer NOT NULL
);


ALTER TABLE spots_in_quests OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16536)
-- Name: spots_in_quests_photo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE spots_in_quests_photo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spots_in_quests_photo_id_seq OWNER TO postgres;

--
-- TOC entry 2236 (class 0 OID 0)
-- Dependencies: 205
-- Name: spots_in_quests_photo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE spots_in_quests_photo_id_seq OWNED BY spots_in_quests.photo_id;


--
-- TOC entry 204 (class 1259 OID 16529)
-- Name: spots_in_quests_quest_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE spots_in_quests_quest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spots_in_quests_quest_id_seq OWNER TO postgres;

--
-- TOC entry 2237 (class 0 OID 0)
-- Dependencies: 204
-- Name: spots_in_quests_quest_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE spots_in_quests_quest_id_seq OWNED BY spots_in_quests.quest_id;


--
-- TOC entry 203 (class 1259 OID 16522)
-- Name: spots_in_quests_spot_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE spots_in_quests_spot_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spots_in_quests_spot_id_seq OWNER TO postgres;

--
-- TOC entry 2238 (class 0 OID 0)
-- Dependencies: 203
-- Name: spots_in_quests_spot_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE spots_in_quests_spot_id_seq OWNED BY spots_in_quests.spot_id;


--
-- TOC entry 201 (class 1259 OID 16514)
-- Name: spots_in_quests_spots_in_quests_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE spots_in_quests_spots_in_quests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spots_in_quests_spots_in_quests_id_seq OWNER TO postgres;

--
-- TOC entry 2239 (class 0 OID 0)
-- Dependencies: 201
-- Name: spots_in_quests_spots_in_quests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE spots_in_quests_spots_in_quests_id_seq OWNED BY spots_in_quests.spots_in_quests_id;


--
-- TOC entry 193 (class 1259 OID 16425)
-- Name: user_groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_groups (
    group_id bigint NOT NULL,
    name character varying(250) NOT NULL
);


ALTER TABLE user_groups OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 16428)
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
-- TOC entry 2240 (class 0 OID 0)
-- Dependencies: 194
-- Name: user_groups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_groups_id_seq OWNED BY user_groups.group_id;


--
-- TOC entry 195 (class 1259 OID 16430)
-- Name: user_progress; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_progress (
    user_id bigint NOT NULL,
    quest_id bigint NOT NULL,
    date_complete date,
    taking_date date,
    user_progress_id integer NOT NULL
);


ALTER TABLE user_progress OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16433)
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
-- TOC entry 2241 (class 0 OID 0)
-- Dependencies: 196
-- Name: user_progress_quest_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_progress_quest_id_seq OWNED BY user_progress.quest_id;


--
-- TOC entry 197 (class 1259 OID 16435)
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
-- TOC entry 2242 (class 0 OID 0)
-- Dependencies: 197
-- Name: user_progress_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_progress_user_id_seq OWNED BY user_progress.user_id;


--
-- TOC entry 200 (class 1259 OID 16505)
-- Name: user_progress_user_progress_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_progress_user_progress_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_progress_user_progress_id_seq OWNER TO postgres;

--
-- TOC entry 2243 (class 0 OID 0)
-- Dependencies: 200
-- Name: user_progress_user_progress_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_progress_user_progress_id_seq OWNED BY user_progress.user_progress_id;


--
-- TOC entry 207 (class 1259 OID 16560)
-- Name: user_spot_progress; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_spot_progress (
    id integer NOT NULL,
    spots_in_quests_id integer NOT NULL,
    user_progress_id integer NOT NULL,
    spot_status character varying(250) NOT NULL,
    date_complete date
);


ALTER TABLE user_spot_progress OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16558)
-- Name: user_spot_progress_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_spot_progress_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_spot_progress_id_seq OWNER TO postgres;

--
-- TOC entry 2244 (class 0 OID 0)
-- Dependencies: 206
-- Name: user_spot_progress_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_spot_progress_id_seq OWNED BY user_spot_progress.id;


--
-- TOC entry 208 (class 1259 OID 16566)
-- Name: user_spot_progress_spots_in_quests_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_spot_progress_spots_in_quests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_spot_progress_spots_in_quests_id_seq OWNER TO postgres;

--
-- TOC entry 2245 (class 0 OID 0)
-- Dependencies: 208
-- Name: user_spot_progress_spots_in_quests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_spot_progress_spots_in_quests_id_seq OWNED BY user_spot_progress.spots_in_quests_id;


--
-- TOC entry 209 (class 1259 OID 16573)
-- Name: user_spot_progress_user_progress_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_spot_progress_user_progress_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_spot_progress_user_progress_id_seq OWNER TO postgres;

--
-- TOC entry 2246 (class 0 OID 0)
-- Dependencies: 209
-- Name: user_spot_progress_user_progress_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_spot_progress_user_progress_id_seq OWNED BY user_spot_progress.user_progress_id;


--
-- TOC entry 198 (class 1259 OID 16437)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    user_id bigint NOT NULL,
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
-- TOC entry 199 (class 1259 OID 16443)
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
-- TOC entry 2247 (class 0 OID 0)
-- Dependencies: 199
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.user_id;


--
-- TOC entry 2064 (class 2604 OID 16445)
-- Name: photos photo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photos ALTER COLUMN photo_id SET DEFAULT nextval('photos_id_seq'::regclass);


--
-- TOC entry 2065 (class 2604 OID 16446)
-- Name: photos user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photos ALTER COLUMN user_id SET DEFAULT nextval('photos_user_id_seq'::regclass);


--
-- TOC entry 2066 (class 2604 OID 16447)
-- Name: photos spot_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photos ALTER COLUMN spot_id SET DEFAULT nextval('photos_spot_id_seq'::regclass);


--
-- TOC entry 2067 (class 2604 OID 16448)
-- Name: quests quest_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quests ALTER COLUMN quest_id SET DEFAULT nextval('quests_id_seq'::regclass);


--
-- TOC entry 2068 (class 2604 OID 16451)
-- Name: spots spot_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY spots ALTER COLUMN spot_id SET DEFAULT nextval('spots_id_seq'::regclass);


--
-- TOC entry 2074 (class 2604 OID 16519)
-- Name: spots_in_quests spots_in_quests_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY spots_in_quests ALTER COLUMN spots_in_quests_id SET DEFAULT nextval('spots_in_quests_spots_in_quests_id_seq'::regclass);


--
-- TOC entry 2075 (class 2604 OID 16524)
-- Name: spots_in_quests spot_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY spots_in_quests ALTER COLUMN spot_id SET DEFAULT nextval('spots_in_quests_spot_id_seq'::regclass);


--
-- TOC entry 2076 (class 2604 OID 16531)
-- Name: spots_in_quests quest_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY spots_in_quests ALTER COLUMN quest_id SET DEFAULT nextval('spots_in_quests_quest_id_seq'::regclass);


--
-- TOC entry 2077 (class 2604 OID 16538)
-- Name: spots_in_quests photo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY spots_in_quests ALTER COLUMN photo_id SET DEFAULT nextval('spots_in_quests_photo_id_seq'::regclass);


--
-- TOC entry 2069 (class 2604 OID 16453)
-- Name: user_groups group_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_groups ALTER COLUMN group_id SET DEFAULT nextval('user_groups_id_seq'::regclass);


--
-- TOC entry 2070 (class 2604 OID 16454)
-- Name: user_progress user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_progress ALTER COLUMN user_id SET DEFAULT nextval('user_progress_user_id_seq'::regclass);


--
-- TOC entry 2071 (class 2604 OID 16455)
-- Name: user_progress quest_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_progress ALTER COLUMN quest_id SET DEFAULT nextval('user_progress_quest_id_seq'::regclass);


--
-- TOC entry 2072 (class 2604 OID 16507)
-- Name: user_progress user_progress_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_progress ALTER COLUMN user_progress_id SET DEFAULT nextval('user_progress_user_progress_id_seq'::regclass);


--
-- TOC entry 2078 (class 2604 OID 16563)
-- Name: user_spot_progress id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_spot_progress ALTER COLUMN id SET DEFAULT nextval('user_spot_progress_id_seq'::regclass);


--
-- TOC entry 2079 (class 2604 OID 16568)
-- Name: user_spot_progress spots_in_quests_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_spot_progress ALTER COLUMN spots_in_quests_id SET DEFAULT nextval('user_spot_progress_spots_in_quests_id_seq'::regclass);


--
-- TOC entry 2080 (class 2604 OID 16575)
-- Name: user_spot_progress user_progress_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_spot_progress ALTER COLUMN user_progress_id SET DEFAULT nextval('user_spot_progress_user_progress_id_seq'::regclass);


--
-- TOC entry 2073 (class 2604 OID 16456)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN user_id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- TOC entry 2096 (class 2606 OID 16565)
-- Name: user_spot_progress id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_spot_progress
    ADD CONSTRAINT id PRIMARY KEY (id);


--
-- TOC entry 2082 (class 2606 OID 16458)
-- Name: photos photos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photos
    ADD CONSTRAINT photos_pkey PRIMARY KEY (photo_id);


--
-- TOC entry 2084 (class 2606 OID 16460)
-- Name: quests quests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quests
    ADD CONSTRAINT quests_pkey PRIMARY KEY (quest_id);


--
-- TOC entry 2094 (class 2606 OID 16521)
-- Name: spots_in_quests spots_in_quests_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY spots_in_quests
    ADD CONSTRAINT spots_in_quests_id PRIMARY KEY (spots_in_quests_id);


--
-- TOC entry 2086 (class 2606 OID 16462)
-- Name: spots spots_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY spots
    ADD CONSTRAINT spots_pkey PRIMARY KEY (spot_id);


--
-- TOC entry 2088 (class 2606 OID 16464)
-- Name: user_groups user_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_groups
    ADD CONSTRAINT user_groups_pkey PRIMARY KEY (group_id);


--
-- TOC entry 2090 (class 2606 OID 16513)
-- Name: user_progress user_progress_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_progress
    ADD CONSTRAINT user_progress_id PRIMARY KEY (user_progress_id);


--
-- TOC entry 2092 (class 2606 OID 16468)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2101 (class 2606 OID 16469)
-- Name: users group_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT group_id FOREIGN KEY (group_id) REFERENCES user_groups(group_id);


--
-- TOC entry 2104 (class 2606 OID 16553)
-- Name: spots_in_quests photo_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY spots_in_quests
    ADD CONSTRAINT photo_id FOREIGN KEY (photo_id) REFERENCES photos(photo_id);


--
-- TOC entry 2099 (class 2606 OID 16479)
-- Name: user_progress quest_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_progress
    ADD CONSTRAINT quest_id FOREIGN KEY (quest_id) REFERENCES quests(quest_id);


--
-- TOC entry 2103 (class 2606 OID 16548)
-- Name: spots_in_quests quest_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY spots_in_quests
    ADD CONSTRAINT quest_id FOREIGN KEY (quest_id) REFERENCES quests(quest_id);


--
-- TOC entry 2097 (class 2606 OID 16484)
-- Name: photos spot_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photos
    ADD CONSTRAINT spot_id FOREIGN KEY (spot_id) REFERENCES spots(spot_id);


--
-- TOC entry 2102 (class 2606 OID 16543)
-- Name: spots_in_quests spot_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY spots_in_quests
    ADD CONSTRAINT spot_id FOREIGN KEY (spot_id) REFERENCES spots(spot_id);


--
-- TOC entry 2105 (class 2606 OID 16580)
-- Name: user_spot_progress spots_in_quests_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_spot_progress
    ADD CONSTRAINT spots_in_quests_id FOREIGN KEY (spots_in_quests_id) REFERENCES spots_in_quests(spots_in_quests_id);


--
-- TOC entry 2098 (class 2606 OID 16494)
-- Name: photos user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photos
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2100 (class 2606 OID 16499)
-- Name: user_progress user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_progress
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2106 (class 2606 OID 16585)
-- Name: user_spot_progress user_progress_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_spot_progress
    ADD CONSTRAINT user_progress_id FOREIGN KEY (user_progress_id) REFERENCES user_progress(user_progress_id);


-- Completed on 2018-03-01 00:32:14

--
-- PostgreSQL database dump complete
--

