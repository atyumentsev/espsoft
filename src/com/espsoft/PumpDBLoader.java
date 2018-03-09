package com.espsoft;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class PumpDBLoader {
//    private static final String FILE_NAME = "C:/Users/Artem/Downloads/ESP_database_06.02.18_newForm_all.xlsx";
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

    private static String insertRow(Row row) throws SQLException {
        String query = "INSERT INTO pump_type" +
                "(lng_pump_id, list_id, manufacturer_id, brand_id, int_series, stg_type, rus_id, std_gen, dte_crv_date, " +
                "mnf_ctlg_pump, dat_filled, bin_prototype, bin_obsolete, bin_oil_well, bin_wtr_well, bin_float, bin_comp, " +
                "bin_radial, imp_type, bin_cw_rot, sng_pump_od_in, sng_min_csg_in, int_min_stages, int_max_stages, " +
                "sng_stageLen_ft, int_std_burst_psi, int_hs_burst_psi, int_uhs_burst_psi, sng_shaft_d_in, sng_shaft_area_in2," +
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
        try {
            stmt.setString(1, getStringCellValue(row, 0));// PumpID
            stmt.setObject(2, getIntCellValue(row, 1));//list_id
            stmt.setObject(3, manufacturersHash.get(getStringCellValue(row, 2)));//manufacturer_id
            stmt.setObject(4, brandsHash.get(getStringCellValue(row, 3)));//brand_id
            stmt.setObject(5, getNumericCellValue(row, 4).intValue());//int_series
            stmt.setString(6, getStringCellValue(row, 5));// stg_type
            stmt.setString(7, getStringCellValue(row, 6));// rus_id
            stmt.setString(8, getStringCellValue(row, 7));// std_gen

            stmt.setDate(9, getDateCellValue(row, 8));// dte_crv_date

            stmt.setObject(10, getBooleanCellValue(row, 9));  // mnf_ctlg_pump
            stmt.setObject(11, getBooleanCellValue(row, 10)); // dat_filled
            stmt.setObject(12, getBooleanCellValue(row, 11)); // bin_prototype
            stmt.setObject(13, getBooleanCellValue(row, 12)); // bin_obsolete
            stmt.setObject(14, getBooleanCellValue(row, 13)); // bin_oil_well
            stmt.setObject(15, getBooleanCellValue(row, 14)); // bin_wtr_well
            stmt.setObject(16, getBooleanCellValue(row, 15)); // bin_float
            stmt.setObject(17, getBooleanCellValue(row, 16)); // bin_comp
            stmt.setObject(18, getBooleanCellValue(row, 17)); // bin_radial

            stmt.setString(19, getStringCellValue(row, 18));// imp_type
            stmt.setObject(20, getBooleanCellValue(row, 19)); // bin_cw_rot
            stmt.setObject(21, getNumericCellValue(row, 20));// sng_pump_od_in
            stmt.setObject(22, getNumericCellValue(row, 21));// sng_min_csg_in
            stmt.setObject(23, getIntCellValue(row, 22)); // int_min_stages
            stmt.setObject(24, getIntCellValue(row, 23)); // int_max_stages

            stmt.setObject(25, getNumericCellValue(row, 24));// sng_stageLen_ft
            stmt.setObject(26, getNumericCellValue(row, 25));// int_std_burst_psi
            stmt.setObject(27, getNumericCellValue(row, 26));// int_hs_burst_psi
            stmt.setObject(28, getNumericCellValue(row, 27));// int_uhs_burst_psi
            stmt.setObject(29, getNumericCellValue(row, 28));// sng_shaft_d_in
            stmt.setObject(30, getNumericCellValue(row, 29));// sng_shaft_area_in2
            stmt.setObject(31, getNumericCellValue(row, 30));// mod_shaft_d_in
            stmt.setObject(32, getNumericCellValue(row, 31));// mod_shaft_area_in2

            stmt.setObject(33, getIntCellValue(row, 32)); // int_ref_rpm
            stmt.setObject(34, getIntCellValue(row, 33)); // int_ref_freq
            stmt.setObject(35, getIntCellValue(row, 34)); // min_f
            stmt.setObject(36, getIntCellValue(row, 35)); // max_f

            stmt.setObject(37, getNumericCellValue(row, 36)); // max_eff
            stmt.setObject(38, getNumericCellValue(row, 37)); //  sng_max_pwr_hp_plot
            stmt.setObject(39, getNumericCellValue(row, 38)); //  sng_max_pwr_hp_ror
            stmt.setObject(40, getNumericCellValue(row, 39)); //  int_std_shaft_pwr_hp
            stmt.setObject(41, getNumericCellValue(row, 40)); //  int_hs_shaft_pwr_hp
            stmt.setObject(42, getNumericCellValue(row, 41)); //  int_uhs_shaft_pwr_hp

            stmt.setObject(43, getBooleanCellValue(row, 42)); // floating
            stmt.setObject(44, getBooleanCellValue(row, 43)); // compression
            stmt.setObject(45, getBooleanCellValue(row, 44)); // ar_compression
            stmt.setObject(46, getBooleanCellValue(row, 45)); // packet_compression

            stmt.setString(47, getStringCellValue(row, 46));// stn_stage
            stmt.setString(48, getStringCellValue(row, 47));// wide_rang_st

            stmt.setObject(49, getNumericCellValue(row, 48)); //  ror_min_wr
            stmt.setObject(50, getNumericCellValue(row, 49)); //  ror_min_bpd
            stmt.setObject(51, getNumericCellValue(row, 50)); //  bep_bpd
            stmt.setObject(52, getNumericCellValue(row, 51)); //  bep_calc_bpd
            stmt.setObject(53, getNumericCellValue(row, 52)); //  ror_max_bpd
            stmt.setObject(54, getNumericCellValue(row, 53)); //  ing_max_wr
            stmt.setObject(55, getNumericCellValue(row, 54)); //  lng_plot_limit_bpd
            stmt.setObject(56, getNumericCellValue(row, 55)); //  lng_rate_axis_max_bpd
            stmt.setObject(57, getNumericCellValue(row, 56)); //  int_hd_axis_min_ft
            stmt.setObject(58, getNumericCellValue(row, 57)); //  int_hd_axis_max_ft
            stmt.setObject(59, getNumericCellValue(row, 58)); //  sng_hd_scale_ft
            stmt.setObject(60, getNumericCellValue(row, 59)); //  sng_pwr_scale_hp
            stmt.setObject(61, getNumericCellValue(row, 60)); //  sng_eff_scale_per
            
            stmt.setObject(62, getIntCellValue(row, 61)); // byt_hd_cnt
            stmt.setObject(63, getIntCellValue(row, 62)); // byt_pwr_cnt
            stmt.setObject(64, getIntCellValue(row, 63)); // hd_dev_cof
            stmt.setObject(65, getIntCellValue(row, 64)); // pw_dev_cof

            stmt.setObject(66, getNumericCellValue(row, 65)); // dbl_hd_c0
            stmt.setObject(67, getNumericCellValue(row, 66)); // dbl_hd_c1
            stmt.setObject(68, getNumericCellValue(row, 67)); // dbl_hd_c2
            stmt.setObject(69, getNumericCellValue(row, 68)); // dbl_hd_c3
            stmt.setObject(70, getNumericCellValue(row, 69)); // dbl_hd_c4
            stmt.setObject(71, getNumericCellValue(row, 70)); // dbl_hd_c5
            stmt.setObject(72, getNumericCellValue(row, 71)); // dbl_hd_c6
            stmt.setObject(73, getNumericCellValue(row, 72)); // dbl_hd_c7
            stmt.setObject(74, getNumericCellValue(row, 73)); // dbl_hd_c8
            stmt.setObject(75, getNumericCellValue(row, 74)); // dbl_hd_c9

            stmt.setObject(76, getNumericCellValue(row, 75)); // dbl_pwr_c0
            stmt.setObject(77, getNumericCellValue(row, 76)); // dbl_pwr_c1
            stmt.setObject(78, getNumericCellValue(row, 77)); // dbl_pwr_c2
            stmt.setObject(79, getNumericCellValue(row, 78)); // dbl_pwr_c3
            stmt.setObject(80, getNumericCellValue(row, 79)); // dbl_pwr_c4
            stmt.setObject(81, getNumericCellValue(row, 80)); // dbl_pwr_c5
            stmt.setObject(82, getNumericCellValue(row, 81)); // dbl_pwr_c6
            stmt.setObject(83, getNumericCellValue(row, 82)); // dbl_pwr_c7
            stmt.setObject(84, getNumericCellValue(row, 83)); // dbl_pwr_c8
            stmt.setObject(85, getNumericCellValue(row, 84)); // dbl_pwr_c9

            stmt.setDate(86, getDateCellValue(row, 85));// dte_date

            stmt.executeUpdate();
        } catch (SQLException e) {
            return stmt.toString() + e.getMessage();
        }
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
