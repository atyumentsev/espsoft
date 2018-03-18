package com.espsoft;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class PumpDBLoader {
    private static Connection connection;
    private static HashMap<String, Integer> brandsHash;
    private static HashMap<String, Integer> manufacturersHash;
    private static final int DATA_STARTS_AT = 6;

    public static ArrayList<String> load(String file_name) {
        ArrayList<String> result = new ArrayList<>();
        String row_result;

        try {
            connection = DB.getConnection();

            String query = "TRUNCATE pump_type RESTART IDENTITY";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.execute();

            brandsHash = getBrandsHash();
            manufacturersHash = getManufacturersHash();

            FileInputStream excelFile = new FileInputStream(new File(file_name));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet;
            sheet = workbook.getSheet("tblPump");
            int i = 0;
            for (Row row : sheet) {
                i++;
                if (i < DATA_STARTS_AT) {
                    continue;
                }
                if (row.getCell(0) == null) {
                    continue;
                }

                row_result = insertRow(row);
                result.add(row_result);
            }
        } catch (Exception e) {
            result.add("Upload failed, exception: " + e.toString());
        }

        return result;
    }

    private static String insertRow(Row row) throws SQLException, ClassNotFoundException {
        PumpType pumpType = new PumpType();
        pumpType.lng_pump_id = getStringCellValue(row, 0);

        pumpType.list_id = getIntCellValue(row, 1);
        pumpType.manufacturer_id = manufacturersHash.get(getStringCellValue(row, 2));//manufacturer_id
        pumpType.brand_id = brandsHash.get(getStringCellValue(row, 3));//brand_id
        pumpType.int_series = getNumericCellValue(row, 4).intValue();//int_series
        pumpType.stg_type = getStringCellValue(row, 5); // stg_type
        pumpType.rus_id = getStringCellValue(row, 6); // rus_id
        pumpType.std_gen = getStringCellValue(row, 7); // std_gen

        pumpType.dte_crv_date = getDateCellValue(row, 8); // dte_crv_date

        pumpType.mnf_ctlg_pump = getBooleanCellValue(row, 9); // mnf_ctlg_pump
        pumpType.dat_filled = getBooleanCellValue(row, 10); // dat_filled
        pumpType.bin_prototype = getBooleanCellValue(row, 11); // bin_prototype
        pumpType.bin_obsolete = getBooleanCellValue(row, 12); // bin_obsolete
        pumpType.bin_oil_well = getBooleanCellValue(row, 13); // bin_oil_well
        pumpType.bin_wtr_well = getBooleanCellValue(row, 14); // bin_wtr_well
        pumpType.bin_float = getBooleanCellValue(row, 15); // bin_float
        pumpType.bin_comp = getBooleanCellValue(row, 16); // bin_comp
        pumpType.bin_radial = getBooleanCellValue(row, 17); // bin_radial

        pumpType.imp_type = getStringCellValue(row, 18); // imp_type
        pumpType.bin_cw_rot = getBooleanCellValue(row, 19); // bin_cw_rot
        pumpType.sng_pump_od_in = getNumericCellValue(row, 20); // sng_pump_od_in
        pumpType.sng_min_csg_in = getNumericCellValue(row, 21); // sng_min_csg_in
        pumpType.int_min_stages = getIntCellValue(row, 22); // int_min_stages
        pumpType.int_max_stages = getIntCellValue(row, 23); // int_max_stages

        pumpType.sng_stage_len_ft = getNumericCellValue(row, 24); // sng_stage_len_ft
        pumpType.int_std_burst_psi = getNumericCellValue(row, 25); // int_std_burst_psi
        pumpType.int_hs_burst_psi = getNumericCellValue(row, 26); // int_hs_burst_psi
        pumpType.int_uhs_burst_psi = getNumericCellValue(row, 27); // int_uhs_burst_psi
        pumpType.sng_shaft_d_in = getNumericCellValue(row, 28); // sng_shaft_d_in
        pumpType.sng_shaft_area_in2 = getNumericCellValue(row, 29); // sng_shaft_area_in2
        pumpType.mod_shaft_d_in = getNumericCellValue(row, 30); // mod_shaft_d_in
        pumpType.mod_shaft_area_in2 = getNumericCellValue(row, 31); // mod_shaft_area_in2

        pumpType.int_ref_rpm = getIntCellValue(row, 32); // int_ref_rpm
        pumpType.int_ref_freq = getIntCellValue(row, 33); // int_ref_freq
        pumpType.min_f = getIntCellValue(row, 34); // min_f
        pumpType.max_f = getIntCellValue(row, 35); // max_f

        pumpType.max_eff = getNumericCellValue(row, 36); // max_eff
        pumpType.sng_max_pwr_hp_plot = getNumericCellValue(row, 37); // sng_max_pwr_hp_plot
        pumpType.sng_max_pwr_hp_ror = getNumericCellValue(row, 38); // sng_max_pwr_hp_ror
        pumpType.int_std_shaft_pwr_hp = getNumericCellValue(row, 39); // int_std_shaft_pwr_hp
        pumpType.int_hs_shaft_pwr_hp = getNumericCellValue(row, 40); // int_hs_shaft_pwr_hp
        pumpType.int_uhs_shaft_pwr_hp = getNumericCellValue(row, 41); // int_uhs_shaft_pwr_hp

        pumpType.floating = getBooleanCellValue(row, 42); // floating
        pumpType.compression = getBooleanCellValue(row, 43); // compression
        pumpType.ar_compression = getBooleanCellValue(row, 44); // ar_compression
        pumpType.packet_compression = getBooleanCellValue(row, 45); // packet_compression

        pumpType.stn_stage = getStringCellValue(row, 46); // stn_stage
        pumpType.wide_rang_st = getStringCellValue(row, 47); // wide_rang_st

        pumpType.ror_min_wr = getNumericCellValue(row, 48); // ror_min_wr
        pumpType.ror_min_bpd = getNumericCellValue(row, 49); // ror_min_bpd
        pumpType.bep_bpd = getNumericCellValue(row, 50); // bep_bpd
        pumpType.bep_calc_bpd = getNumericCellValue(row, 51); // bep_calc_bpd
        pumpType.ror_max_bpd = getNumericCellValue(row, 52); // ror_max_bpd
        pumpType.ing_max_wr = getNumericCellValue(row, 53); // ing_max_wr
        pumpType.lng_plot_limit_bpd = getNumericCellValue(row, 54); // lng_plot_limit_bpd
        pumpType.lng_rate_axis_max_bpd = getNumericCellValue(row, 55); // lng_rate_axis_max_bpd
        pumpType.int_hd_axis_min_ft = getNumericCellValue(row, 56); // int_hd_axis_min_ft
        pumpType.int_hd_axis_max_ft = getNumericCellValue(row, 57); // int_hd_axis_max_ft
        pumpType.sng_hd_scale_ft = getNumericCellValue(row, 58); // sng_hd_scale_ft
        pumpType.sng_pwr_scale_hp = getNumericCellValue(row, 59); // sng_pwr_scale_hp
        pumpType.sng_eff_scale_per = getNumericCellValue(row, 60); // sng_eff_scale_per
        
        pumpType.byt_hd_cnt = getIntCellValue(row, 61); // byt_hd_cnt
        pumpType.byt_pwr_cnt = getIntCellValue(row, 62); // byt_pwr_cnt
        pumpType.hd_dev_cof = getIntCellValue(row, 63); // hd_dev_cof
        pumpType.pw_dev_cof = getIntCellValue(row, 64); // pw_dev_cof

        pumpType.dbl_hd_c0 = getNumericCellValue(row, 65); // dbl_hd_c0
        pumpType.dbl_hd_c1 = getNumericCellValue(row, 66); // dbl_hd_c1
        pumpType.dbl_hd_c2 = getNumericCellValue(row, 67); // dbl_hd_c2
        pumpType.dbl_hd_c3 = getNumericCellValue(row, 68); // dbl_hd_c3
        pumpType.dbl_hd_c4 = getNumericCellValue(row, 69); // dbl_hd_c4
        pumpType.dbl_hd_c5 = getNumericCellValue(row, 70); // dbl_hd_c5
        pumpType.dbl_hd_c6 = getNumericCellValue(row, 71); // dbl_hd_c6
        pumpType.dbl_hd_c7 = getNumericCellValue(row, 72); // dbl_hd_c7
        pumpType.dbl_hd_c8 = getNumericCellValue(row, 73); // dbl_hd_c8
        pumpType.dbl_hd_c9 = getNumericCellValue(row, 74); // dbl_hd_c9

        pumpType.dbl_pwr_c0 = getNumericCellValue(row, 75); // dbl_pwr_c0
        pumpType.dbl_pwr_c1 = getNumericCellValue(row, 76); // dbl_pwr_c1
        pumpType.dbl_pwr_c2 = getNumericCellValue(row, 77); // dbl_pwr_c2
        pumpType.dbl_pwr_c3 = getNumericCellValue(row, 78); // dbl_pwr_c3
        pumpType.dbl_pwr_c4 = getNumericCellValue(row, 79); // dbl_pwr_c4
        pumpType.dbl_pwr_c5 = getNumericCellValue(row, 80); // dbl_pwr_c5
        pumpType.dbl_pwr_c6 = getNumericCellValue(row, 81); // dbl_pwr_c6
        pumpType.dbl_pwr_c7 = getNumericCellValue(row, 82); // dbl_pwr_c7
        pumpType.dbl_pwr_c8 = getNumericCellValue(row, 83); // dbl_pwr_c8
        pumpType.dbl_pwr_c9 = getNumericCellValue(row, 84); // dbl_pwr_c9

        pumpType.dte_date = getDateCellValue(row, 85); // dte_date

        pumpType.saveToDb();

        return "Pump #" + getStringCellValue(row, 0) + " successfully uploaded";
    }

    private static Boolean getBooleanCellValue(Row row, int position) {
        Boolean value;
        try {
            Cell cell = row.getCell(position);
            if (cell == null || cell.getCellTypeEnum() == CellType.BLANK) {
                value = null;
            } else if (cell.getCellTypeEnum() == CellType.STRING) {
                value = null;
            } else {
                value = cell.getBooleanCellValue();
            }
        } catch (Exception e) {
            value = null;
        }
        return value;
    }

    private static String getStringCellValue(Row row, int position) {
        String value;
        try {
            Cell cell = row.getCell(position);
            if (cell == null || cell.getCellTypeEnum() == CellType.BLANK) {
                value = null;
            } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                value = (int)cell.getNumericCellValue() + "";
            } else {
                value = cell.getStringCellValue();
            }
        } catch (Exception e) {
            value = null;
        }
        return value;
    }

    private static Double getNumericCellValue(Row row, int position) {
        Double value;
        try {
            Cell cell = row.getCell(position);
            if (cell == null || cell.getCellTypeEnum() == CellType.BLANK) {
                value = null;
            } else if (cell.getCellTypeEnum() == CellType.STRING) {
                value = null;
            } else {
                value = cell.getNumericCellValue();
            }
        } catch (Exception e) {
            value = null;
        }
        return value;
    }

    private static Integer getIntCellValue(Row row, int position) {
        Double value = getNumericCellValue(row, position);
        if (value == null) {
            return null;
        }
        return value.intValue();
    }

    private static Date getDateCellValue(Row row, int position) {
        Date date;
        Cell cell = row.getCell(position);
        try {
            date = new Date(cell.getDateCellValue().getTime());
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    private static HashMap<String, Integer> getBrandsHash() throws SQLException {
        HashMap<String, Integer> result = new HashMap<>();
        Statement stmt = connection.createStatement();
        String sql;
        sql = "SELECT id, name FROM brand";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            result.put (
                rs.getString("name"),
                rs.getInt("id")
            );
        }


        return result;
    }

    private static HashMap<String, Integer> getManufacturersHash() throws SQLException {
        HashMap<String, Integer> result = new HashMap<>();
        Statement stmt = connection.createStatement();
        String sql;
        sql = "SELECT id, name FROM manufacturer";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            result.put (
                rs.getString("name"),
                rs.getInt("id")
            );
        }

        result.put(
                "BakerHughes",
                result.get("Baker Hughes")
        );
        result.put(
                "Baker Hughes\n",
                result.get("Baker Hughes")
        );
        result.put(
                "Baker Hughes\n",
                result.get("Baker Hughes")
        );

        return result;
    }
}
