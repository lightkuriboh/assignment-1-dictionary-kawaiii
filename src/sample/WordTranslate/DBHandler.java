package sample.WordTranslate;
import sample.WordTranslate.Trie.Trie;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler {
    private String ins = "INSERT INTO minhpro99(idx, English, Vietnamese, pronunciation) VALUES(?, ?, ?, ?)";

    private Connection conn = this.getConnect("jdbc:sqlite:D:/minhhh/python/dictionary1.db");


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

    public void release() throws SQLException {
        conn.close();
    }

    public void init(Trie myTrie) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(ins);
        Statement st = conn.createStatement();
        String sql = "SELECT english FROM minhpro99";
        ResultSet rs = st.executeQuery(sql);
        int cur=0;
        conn.setAutoCommit(false);
        while (rs.next()) {
            //int x = rs.getInt("idx");
            String y = new String(rs.getString("english"));
            //String z = new String(rs.getString("vietnamese"));
            //String t = new String(rs.getString("pronunciation"));
            cur++;
            myTrie.insertWord(cur,y);
        }
        System.out.println(cur);
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

    public void insertWord()

}
