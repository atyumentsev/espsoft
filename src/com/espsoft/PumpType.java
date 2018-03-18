package com.espsoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class PumpType {
    int id;
    String lng_pump_id;
    Integer list_id;
    Integer manufacturer_id;
    Integer brand_id;
    Integer int_series;
    String stg_type;
    String rus_id;
    String std_gen;
    Date dte_crv_date;
    Boolean mnf_ctlg_pump;
    Boolean dat_filled;
    Boolean bin_prototype;
    Boolean bin_obsolete;
    Boolean bin_oil_well;
    Boolean bin_wtr_well;
    Boolean bin_float;
    Boolean bin_comp;
    Boolean bin_radial;
    String imp_type;
    Boolean bin_cw_rot;
    Double sng_pump_od_in;
    Double sng_min_csg_in;
    Integer int_min_stages;
    Integer int_max_stages;
    Double sng_stage_len_ft;
    Double int_std_burst_psi;
    Double int_hs_burst_psi;
    Double int_uhs_burst_psi;
    Double sng_shaft_d_in;
    Double sng_shaft_area_in2;
    Double mod_shaft_d_in;
    Double mod_shaft_area_in2;
    Integer int_ref_rpm;
    Integer int_ref_freq;
    Integer min_f;
    Integer max_f;
    Double max_eff;
    Double sng_max_pwr_hp_plot;
    Double sng_max_pwr_hp_ror;
    Double int_std_shaft_pwr_hp;
    Double int_hs_shaft_pwr_hp;
    Double int_uhs_shaft_pwr_hp;
    Boolean floating;
    Boolean compression;
    Boolean ar_compression;
    Boolean packet_compression;
    String stn_stage;
    String wide_rang_st;
    Double ror_min_wr;
    Double ror_min_bpd;
    Double bep_bpd;
    Double bep_calc_bpd;
    Double ror_max_bpd;
    Double ing_max_wr;
    Double lng_plot_limit_bpd;
    Double lng_rate_axis_max_bpd;
    Double int_hd_axis_min_ft;
    Double int_hd_axis_max_ft;
    Double sng_hd_scale_ft;
    Double sng_pwr_scale_hp;
    Double sng_eff_scale_per;
    Integer byt_hd_cnt;
    Integer byt_pwr_cnt;
    Integer hd_dev_cof;
    Integer pw_dev_cof;
    Double dbl_hd_c0;
    Double dbl_hd_c1;
    Double dbl_hd_c2;
    Double dbl_hd_c3;
    Double dbl_hd_c4;
    Double dbl_hd_c5;
    Double dbl_hd_c6;
    Double dbl_hd_c7;
    Double dbl_hd_c8;
    Double dbl_hd_c9;
    Double dbl_pwr_c0;
    Double dbl_pwr_c1;
    Double dbl_pwr_c2;
    Double dbl_pwr_c3;
    Double dbl_pwr_c4;
    Double dbl_pwr_c5;
    Double dbl_pwr_c6;
    Double dbl_pwr_c7;
    Double dbl_pwr_c8;
    Double dbl_pwr_c9;
    Date dte_date;

    public void saveToDb() throws SQLException, ClassNotFoundException {
        Connection connection = DB.getConnection();
        String query = "INSERT INTO pump_type" +
                "(lng_pump_id, list_id, manufacturer_id, brand_id, int_series, stg_type, rus_id, std_gen, dte_crv_date, " +
                "mnf_ctlg_pump, dat_filled, bin_prototype, bin_obsolete, bin_oil_well, bin_wtr_well, bin_float, bin_comp, " +
                "bin_radial, imp_type, bin_cw_rot, sng_pump_od_in, sng_min_csg_in, int_min_stages, int_max_stages, " +
                "sng_stage_len_ft, int_std_burst_psi, int_hs_burst_psi, int_uhs_burst_psi, sng_shaft_d_in, sng_shaft_area_in2," +
                "mod_shaft_d_in, mod_shaft_area_in2, int_ref_rpm, int_ref_freq, min_f, max_f," +
                "max_eff, sng_max_pwr_hp_plot, sng_max_pwr_hp_ror, int_std_shaft_pwr_hp, int_hs_shaft_pwr_hp, int_uhs_shaft_pwr_hp, " +
                "floating, compression, ar_compression, packet_compression, " +
                "stn_stage, wide_rang_st," +
                "ror_min_wr, ror_min_bpd, bep_bpd, bep_calc_bpd, ror_max_bpd, ing_max_wr, lng_plot_limit_bpd, lng_rate_axis_max_bpd, " +
                "int_hd_axis_min_ft, int_hd_axis_max_ft, sng_hd_scale_ft, sng_pwr_scale_hp, sng_eff_scale_per," +
                "byt_hd_cnt, byt_pwr_cnt, hd_dev_cof, pw_dev_cof," +
                "dbl_hd_c0, dbl_hd_c1, dbl_hd_c2, dbl_hd_c3, dbl_hd_c4, dbl_hd_c5, dbl_hd_c6, dbl_hd_c7, dbl_hd_c8, dbl_hd_c9," +
                "dbl_pwr_c0, dbl_pwr_c1, dbl_pwr_c2, dbl_pwr_c3, dbl_pwr_c4, dbl_pwr_c5, dbl_pwr_c6, dbl_pwr_c7, dbl_pwr_c8, dbl_pwr_c9," +
                "dte_date)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, lng_pump_id);// PumpID
        stmt.setObject(2, list_id);//list_id
        stmt.setObject(3, manufacturer_id);//manufacturer_id
        stmt.setObject(4, brand_id);//brand_id
        stmt.setObject(5, int_series);//int_series
        stmt.setString(6, stg_type);// stg_type
        stmt.setString(7, rus_id);// rus_id
        stmt.setString(8, std_gen);// std_gen

        stmt.setDate(9, new java.sql.Date(dte_crv_date.getTime()));// dte_crv_date

        stmt.setObject(10, mnf_ctlg_pump);
        stmt.setObject(11, dat_filled);
        stmt.setObject(12, bin_prototype);
        stmt.setObject(13, bin_obsolete);
        stmt.setObject(14, bin_oil_well);
        stmt.setObject(15, bin_wtr_well);
        stmt.setObject(16, bin_float);
        stmt.setObject(17, bin_comp);
        stmt.setObject(18, bin_radial);

        stmt.setString(19, imp_type);
        stmt.setObject(20, bin_cw_rot);
        stmt.setObject(21, sng_pump_od_in);
        stmt.setObject(22, sng_min_csg_in);
        stmt.setObject(23, int_min_stages);
        stmt.setObject(24, int_max_stages);

        stmt.setObject(25, sng_stage_len_ft);
        stmt.setObject(26, int_std_burst_psi);
        stmt.setObject(27, int_hs_burst_psi);
        stmt.setObject(28, int_uhs_burst_psi);
        stmt.setObject(29, sng_shaft_d_in);
        stmt.setObject(30, sng_shaft_area_in2);
        stmt.setObject(31, mod_shaft_d_in);
        stmt.setObject(32, mod_shaft_area_in2);

        stmt.setObject(33, int_ref_rpm);
        stmt.setObject(34, int_ref_freq);
        stmt.setObject(35, min_f);
        stmt.setObject(36, max_f);

        stmt.setObject(37, max_eff);
        stmt.setObject(38, sng_max_pwr_hp_plot);
        stmt.setObject(39, sng_max_pwr_hp_ror);
        stmt.setObject(40, int_std_shaft_pwr_hp);
        stmt.setObject(41, int_hs_shaft_pwr_hp);
        stmt.setObject(42, int_uhs_shaft_pwr_hp);

        stmt.setObject(43, floating);
        stmt.setObject(44, compression);
        stmt.setObject(45, ar_compression);
        stmt.setObject(46, packet_compression);

        stmt.setString(47, stn_stage);
        stmt.setString(48, wide_rang_st);

        stmt.setObject(49, ror_min_wr);
        stmt.setObject(50, ror_min_bpd);
        stmt.setObject(51, bep_bpd);
        stmt.setObject(52, bep_calc_bpd);
        stmt.setObject(53, ror_max_bpd);
        stmt.setObject(54, ing_max_wr);
        stmt.setObject(55, lng_plot_limit_bpd);
        stmt.setObject(56, lng_rate_axis_max_bpd);
        stmt.setObject(57, int_hd_axis_min_ft);
        stmt.setObject(58, int_hd_axis_max_ft);
        stmt.setObject(59, sng_hd_scale_ft);
        stmt.setObject(60, sng_pwr_scale_hp);
        stmt.setObject(61, sng_eff_scale_per);

        stmt.setObject(62, byt_hd_cnt);
        stmt.setObject(63, byt_pwr_cnt);
        stmt.setObject(64, hd_dev_cof);
        stmt.setObject(65, pw_dev_cof);

        stmt.setObject(66, dbl_hd_c0);
        stmt.setObject(67, dbl_hd_c1);
        stmt.setObject(68, dbl_hd_c2);
        stmt.setObject(69, dbl_hd_c3);
        stmt.setObject(70, dbl_hd_c4);
        stmt.setObject(71, dbl_hd_c5);
        stmt.setObject(72, dbl_hd_c6);
        stmt.setObject(73, dbl_hd_c7);
        stmt.setObject(74, dbl_hd_c8);
        stmt.setObject(75, dbl_hd_c9);

        stmt.setObject(76, dbl_pwr_c0);
        stmt.setObject(77, dbl_pwr_c1);
        stmt.setObject(78, dbl_pwr_c2);
        stmt.setObject(79, dbl_pwr_c3);
        stmt.setObject(80, dbl_pwr_c4);
        stmt.setObject(81, dbl_pwr_c5);
        stmt.setObject(82, dbl_pwr_c6);
        stmt.setObject(83, dbl_pwr_c7);
        stmt.setObject(84, dbl_pwr_c8);
        stmt.setObject(85, dbl_pwr_c9);

        stmt.setDate(86, new java.sql.Date(dte_date.getTime()));

        stmt.executeUpdate();
    }
}
