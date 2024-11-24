package com.mygdx.game.tools;

import javax.sound.midi.Soundbank;
import java.sql.*;

public class SQLConnector {
    //Lớp trình tải trình kết nối cơ sở dữ liệu


    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12746518?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    static final String USER = "sql12746518";
    static final String PASS = "iAP5dSdx8u";
    private static Connection conn = null;

    private static Statement stmt = null;

    static {//Nó được gọi khi lớp được khởi tạo để hoàn thành kết nối và sau đó không cần phải tạo kết nối thường xuyên.
        try{
            // Đăng ký trình điều khiển JDBC
            Class.forName(JDBC_DRIVER);

            // mở liên kết
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // Thực hiện truy vấn
            stmt = conn.createStatement();
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Kết nối cơ sở dữ liệu không thành công, sử dụng bộ nhớ cục bộ!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet readData(String sql) throws SQLException {
        //Nhập câu lệnh truy vấn và trả về dữ liệu
        ResultSet rs = null;
        rs=stmt.executeQuery(sql);
        return rs;
    }

    public static void writeData(String sql) {
        //Nhập câu lệnh sql để ghi dữ liệu vào cơ sở dữ liệu

        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConn() {
        return conn;
    }

    public static Statement getStmt() {
        return stmt;
    }




}
