package sample.WordTranslate;

import sample.WordTranslate.Word.*;

import java.sql.*;

public class DBHandler {

    public DBHandler() {
        this.conn  = this.getConnect(
                "jdbc:sqlite:C:/Users/MSI/Documents/GitHub/assignment-1-dictionary-kawaiii/data/dictionary.db"
        );
    }

    private String ins = "INSERT INTO minhpro99(idx, English, Vietnamese, pronunciation) VALUES(?, ?, ?, ?)";

    private Connection conn;


    public Connection getConnect(String url) {
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn==null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }

    public void release() {
        try {
            this.conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void initHint(HintManager myHintManager) {
        try {
            PreparedStatement ps = this.conn.prepareStatement(ins);
            Statement st = this.conn.createStatement();
            String sql = "SELECT english FROM minhpro99";
            ResultSet rs = st.executeQuery(sql);
            int cur = 0;
            this.conn.setAutoCommit(false);
            while (rs.next()) {
                String y = new String(rs.getString("english"));
                myHintManager.addMoreWord(y);
            }
            myHintManager.initData();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public aWord getWord(Integer idx) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(ins);
        Statement st = conn.createStatement();
        String sql = "SELECT idx, english, vietnamese, pronunciation FROM minhpro99 WHERE idx = "+Integer.toString(idx);
        ResultSet rs = st.executeQuery(sql);
        aWord res = new aWord();
        while (rs.next()) {
            Integer x = rs.getInt("idx");
            String y = new String(rs.getString("english"));
            String z = new String(rs.getString("vietnamese"));
            String t = new String(rs.getString("pronunciation"));
            res.setWord(x, y, z, t);
        }
        return res;
    }

    public void insertWord() {

    }

}
