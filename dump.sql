DROP TABLE IF EXISTS manufacturer;

CREATE TABLE manufacturer (
  id SERIAL PRIMARY KEY,
  name VARCHAR(40),
  created_at DATE DEFAULT NOW()
);

INSERT INTO manufacturer (id, name) VALUES
  (1, 'Baker Hughes'),
  (2, 'GE oil&gas'),
  (3, 'Schlumberger'),
  (4, 'BORETS'),
  (5, 'BORETS-Weatherford'),
  (6, 'NOVOMET'),
  (7, 'SSP');

DROP TABLE IF EXISTS brand;
CREATE TABLE brand (
  id SERIAL PRIMARY KEY,
  manufacturer_id INT NOT NULL REFERENCES manufacturer(id),
  name VARCHAR(40),
  created_at DATE DEFAULT NOW()
);

CREATE INDEX brand_manufacturerID ON brand(manufacturer_id);

INSERT INTO brand(id, manufacturer_id, name) VALUES
  (1, 1, 'CENTRILIFT'),
  (2, 1, 'ODI'),
  (3, 2, 'ESP'),
  (4, 3, 'REDA'),
  (5, 3, 'TPS-Line'),
  (6, 3, 'EZLine'),
  (7, 4, 'BORETS'),
  (8, 5, 'Weatherford'),
  (9, 6, 'NOVOMET'),
  (10, 7, 'SSP');

DROP TABLE IF EXISTS pump_type;
CREATE TABLE pump_type (
  id                    SERIAL PRIMARY KEY,
  lng_pump_id           VARCHAR(30),
  list_id               INTEGER UNIQUE,
  manufacturer_id       INT NOT NULL REFERENCES manufacturer (id),
  brand_id              INT NOT NULL REFERENCES brand (id),
  int_series             INTEGER DEFAULT NULL,
  stg_type              VARCHAR(30),
  rus_id                VARCHAR(30),
  std_gen               VARCHAR(30),
  dte_crv_date          DATE,
  mnf_ctlg_pump         BOOLEAN,
  dat_filled            BOOLEAN,
  bin_prototype         BOOLEAN,
  bin_obsolete          BOOLEAN,
  bin_oil_well          BOOLEAN,
  bin_wtr_well          BOOLEAN,
  bin_float             BOOLEAN,
  bin_comp              BOOLEAN,
  bin_radial            BOOLEAN,
  imp_type              VARCHAR(10),
  bin_cw_rot            BOOLEAN,
  sng_pump_od_in        NUMERIC(10, 3),
  sng_min_csg_in        NUMERIC(10, 3),
  int_min_stages        INTEGER,
  int_max_stages        INTEGER,
  sng_stage_len_ft       NUMERIC(10, 3),
  int_std_burst_psi     NUMERIC(10, 3),
  int_hs_burst_psi      NUMERIC(10, 3),
  int_uhs_burst_psi     NUMERIC(10, 3),
  sng_shaft_d_in        NUMERIC(10, 3),
  sng_shaft_area_in2    NUMERIC(10, 4),
  mod_shaft_d_in        NUMERIC(10, 3),
  mod_shaft_area_in2    NUMERIC(10, 3),
  int_ref_rpm           INTEGER,
  int_ref_freq          INTEGER,
  min_f                 INTEGER,
  max_f                 INTEGER,
  max_eff               NUMERIC(6, 3),
  sng_max_pwr_hp_plot  NUMERIC(10, 3),
  sng_max_pwr_hp_ror    NUMERIC(10, 3),
  int_std_shaft_pwr_hp  NUMERIC(10, 3),
  int_hs_shaft_pwr_hp   NUMERIC(10, 3),
  int_uhs_shaft_pwr_hp  NUMERIC(10, 3),
  floating              BOOLEAN,
  compression           BOOLEAN,
  ar_compression        BOOLEAN,
  packet_compression    BOOLEAN,
  stn_stage             VARCHAR(50),
  wide_rang_st          VARCHAR(50),
  ror_min_wr            NUMERIC(10, 3),
  ror_min_bpd           NUMERIC(10, 3),
  bep_bpd               NUMERIC(10, 3),
  bep_calc_bpd          NUMERIC(10, 3),
  ror_max_bpd           NUMERIC(10, 3),
  ing_max_wr            NUMERIC(10, 3),
  lng_plot_limit_bpd    NUMERIC(10, 3),
  lng_rate_axis_max_bpd NUMERIC(10, 3),
  int_hd_axis_min_ft    NUMERIC(10, 3),
  int_hd_axis_max_ft    NUMERIC(10, 3),
  sng_hd_scale_ft       NUMERIC(10, 3),
  sng_pwr_scale_hp      NUMERIC(10, 3),
  sng_eff_scale_per     NUMERIC(5, 2),
  byt_hd_cnt            INTEGER,
  byt_pwr_cnt           INTEGER,
  hd_dev_cof            INTEGER,
  pw_dev_cof            INTEGER,
  dbl_hd_c0             DOUBLE PRECISION,
  dbl_hd_c1             DOUBLE PRECISION,
  dbl_hd_c2             DOUBLE PRECISION,
  dbl_hd_c3             DOUBLE PRECISION,
  dbl_hd_c4             DOUBLE PRECISION,
  dbl_hd_c5             DOUBLE PRECISION,
  dbl_hd_c6             DOUBLE PRECISION,
  dbl_hd_c7             DOUBLE PRECISION,
  dbl_hd_c8             DOUBLE PRECISION,
  dbl_hd_c9             DOUBLE PRECISION,
  dbl_pwr_c0            DOUBLE PRECISION,
  dbl_pwr_c1            DOUBLE PRECISION,
  dbl_pwr_c2            DOUBLE PRECISION,
  dbl_pwr_c3            DOUBLE PRECISION,
  dbl_pwr_c4            DOUBLE PRECISION,
  dbl_pwr_c5            DOUBLE PRECISION,
  dbl_pwr_c6            DOUBLE PRECISION,
  dbl_pwr_c7            DOUBLE PRECISION,
  dbl_pwr_c8            DOUBLE PRECISION,
  dbl_pwr_c9            DOUBLE PRECISION,
  dte_date              DATE
);

COMMENT ON COLUMN pump_type.lng_pump_id IS 'ID насоса';
COMMENT ON COLUMN pump_type.list_id IS 'Порядковое расположение';
COMMENT ON COLUMN pump_type.manufacturer_id IS 'Головная компания';
COMMENT ON COLUMN pump_type.brand_id IS 'Производитель';
COMMENT ON COLUMN pump_type.int_series IS 'серия';
COMMENT ON COLUMN pump_type.stg_type IS 'Тип';
COMMENT ON COLUMN pump_type.rus_id IS 'Российское наименование';
COMMENT ON COLUMN pump_type.std_gen IS 'Модификация';
COMMENT ON COLUMN pump_type.dte_crv_date IS 'Дата внесения';
COMMENT ON COLUMN pump_type.mnf_ctlg_pump IS 'В каталоге производителя';
COMMENT ON COLUMN pump_type.dat_filled IS 'Данные заполнены в БД';
COMMENT ON COLUMN pump_type.bin_prototype IS 'Протопип';
COMMENT ON COLUMN pump_type.bin_obsolete IS 'Снят с производства';
COMMENT ON COLUMN pump_type.bin_oil_well IS 'Для нефтяных скважин';
COMMENT ON COLUMN pump_type.bin_wtr_well IS 'Для водяных скважин';
COMMENT ON COLUMN pump_type.bin_float IS 'Плавающий тип';
COMMENT ON COLUMN pump_type.bin_comp IS 'Компрессионный тип';
COMMENT ON COLUMN pump_type.bin_radial IS 'Радиальная ступень';
COMMENT ON COLUMN pump_type.imp_type IS 'Тип ступени(R-radial,M-mix,A-axual)';
COMMENT ON COLUMN pump_type.bin_cw_rot IS 'Вращение по часовой стрелке';
COMMENT ON COLUMN pump_type.sng_pump_od_in IS 'Внешний диаметр';
COMMENT ON COLUMN pump_type.sng_min_csg_in IS 'Минимальный диаметр ЭК';
COMMENT ON COLUMN pump_type.int_min_stages IS 'Минимально ступеней';
COMMENT ON COLUMN pump_type.int_max_stages IS 'Макс ступеней';
COMMENT ON COLUMN pump_type.sng_stage_len_ft IS 'Длина ступени в футах';
COMMENT ON COLUMN pump_type.int_std_burst_psi IS 'Макс. Давление в psi стандартно';
COMMENT ON COLUMN pump_type.int_hs_burst_psi IS 'Макс. Давление в psi высокопрочном исполнении';
COMMENT ON COLUMN pump_type.int_uhs_burst_psi IS 'Макс. Давление в psi ултра высокопрочном исполнении';
COMMENT ON COLUMN pump_type.sng_shaft_d_in IS 'Диаметр вала';
COMMENT ON COLUMN pump_type.sng_shaft_area_in2 IS 'Площадь вала стандартного исполнения дюймы2';
COMMENT ON COLUMN pump_type.mod_shaft_d_in IS 'Диаметр усиленного вала';
COMMENT ON COLUMN pump_type.mod_shaft_area_in2 IS 'Площадь усиленного вала';
COMMENT ON COLUMN pump_type.int_ref_rpm IS 'паспортн. Число оборотов в мин';
COMMENT ON COLUMN pump_type.int_ref_freq IS 'паспортн. Частота';
COMMENT ON COLUMN pump_type.min_f IS 'мин. Частота';
COMMENT ON COLUMN pump_type.max_f IS 'Макс Частота';
COMMENT ON COLUMN pump_type.max_eff IS 'максимальный КПД';
COMMENT ON COLUMN pump_type.sng_max_pwr_hp_plot IS 'макс мощность для графика';
COMMENT ON COLUMN pump_type.sng_max_pwr_hp_ror IS 'макс мощность в рабочем диапазоне';
COMMENT ON COLUMN pump_type.int_std_shaft_pwr_hp IS 'допустимая мощность на стандартном валу';
COMMENT ON COLUMN pump_type.int_hs_shaft_pwr_hp IS 'допустимая мощность на высокопрочном валу';
COMMENT ON COLUMN pump_type.int_uhs_shaft_pwr_hp IS 'допустимая мощность на ултра высокопрочном валу';
COMMENT ON COLUMN pump_type.floating IS 'Варианты исполненния';
COMMENT ON COLUMN pump_type.compression IS 'Варианты исполненния';
COMMENT ON COLUMN pump_type.ar_compression IS 'Варианты исполненния';
COMMENT ON COLUMN pump_type.packet_compression IS 'Варианты исполненния';
COMMENT ON COLUMN pump_type.stn_stage IS 'Ступени в стандартном диапазоне';
COMMENT ON COLUMN pump_type.wide_rang_st IS 'Модификация с расширенным рабочим диапазоном';
COMMENT ON COLUMN pump_type.ror_min_wr IS 'Левая зона расширенного диапазона';
COMMENT ON COLUMN pump_type.ror_min_bpd IS 'левая граница рабочего диапазона';
COMMENT ON COLUMN pump_type.bep_bpd IS 'оптимальный точка (паспортная)';
COMMENT ON COLUMN pump_type.bep_calc_bpd IS 'оптимальная точка (расчетная)';
COMMENT ON COLUMN pump_type.ror_max_bpd IS 'правая граница рабочего диапазона';
COMMENT ON COLUMN pump_type.ing_max_wr IS 'Правая зона расширенного диапазона';
COMMENT ON COLUMN pump_type.lng_plot_limit_bpd IS 'макс Q для построения графика';
COMMENT ON COLUMN pump_type.lng_rate_axis_max_bpd IS 'макс возможный Q';
COMMENT ON COLUMN pump_type.int_hd_axis_min_ft IS 'шкала напора 1 ступени от';
COMMENT ON COLUMN pump_type.int_hd_axis_max_ft IS 'шкала напора 1 ступени до';
COMMENT ON COLUMN pump_type.sng_hd_scale_ft IS 'коэф. масштаба для 1 ступени по напору';
COMMENT ON COLUMN pump_type.sng_pwr_scale_hp IS 'коэф. масштаба для 1 ступени по мощности';
COMMENT ON COLUMN pump_type.sng_eff_scale_per IS 'коэф. масштаба для 1 ступени по Eff';
COMMENT ON COLUMN pump_type.byt_hd_cnt IS 'колическво коэф. Напора';
COMMENT ON COLUMN pump_type.byt_pwr_cnt IS 'колич коэф мощности';
COMMENT ON COLUMN pump_type.hd_dev_cof IS 'коэф деления характеристики напора';
COMMENT ON COLUMN pump_type.pw_dev_cof IS 'коэф деления характеристики мощности';

