package sample.WordTranslate;

import sample.WordTranslate.Word.*;

import java.sql.*;

public class DBHandler {

    final String HieuURL = "/home/kurikute/workspace/programming/java/DictionaryFX/data/dictionary.db";
    final String MinhURL = "C:/Users/MSI/Documents/GitHub/assignment-1-dictionary-kawaiii/data/dictionary.db";
    final String TestUrl = "D:/minhhh/python/dictionary2.db";

    public DBHandler() {
        this.conn  = this.getConnect(
                "jdbc:sqlite:" + this.TestUrl
        );
    }

    private String ins = "INSERT INTO minhpro99(idx, english, vietnamese, pronunciation, available) VALUES(?, ?, ?, ?, ?)";
    private String sel = "SELECT * FROM minhpro99 WHERE english = ?";

    private Connection conn;

    public Integer getHighestId() {
        Integer res = -1;
        try {
            String cmd = "SELECT idx FROM minhpro99 WHERE idx = (SELECT max(idx) FROM minhpro99)";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(cmd);
            while (rs.next()) {
                res = rs.getInt("idx");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public Connection getConnect(String url) {
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            String sql = "SELECT english, available FROM minhpro99";
            ResultSet rs = st.executeQuery(sql);
            int cur = 0;
            while (rs.next()) {
                String y = new String(rs.getString("english"));
                Integer avail = rs.getInt("available");
                if (avail == 0) continue;
                myHintManager.addMoreWord(y);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public aWord getWord(String englishWord) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(ins);
        Statement st = conn.createStatement();
        String sql = "SELECT idx, english, vietnamese, pronunciation FROM minhpro99 WHERE english = "+englishWord;


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

    public String getStringWord(String englishWord) throws SQLException {

        final String[] metaCharacters = {"\"", "'", "{", "}", "[", "]", "(", ")"};
        for (String sp: metaCharacters) {
            englishWord = englishWord.replace(sp, "\\" + sp);
        }
        for(Integer i=0;i<englishWord.length();i++) {
            Character j = englishWord.charAt(i);
            if (j.equals('\"')) return "Word has not been updated yet!!!";
        }
        Statement st = conn.createStatement();
        String sql = "SELECT vietnamese, pronunciation FROM minhpro99 WHERE english = \"" + englishWord + "\"";
        ResultSet rs = st.executeQuery(sql);
        String res = "";
        while (rs.next()) {
            String z = new String(rs.getString("vietnamese"));
            String t = new String(rs.getString("pronunciation"));
            res += t + "\n" + z;
            return res;
        }
        return "Word has not been updated yet!!!";
    }


    public String insertWord(HintManager myHintManager, String english, String vietnamese, String pronun) {
        String cmd = "SELECT idx, available FROM minhpro99 WHERE English = '"+english+"'";
        String cmdUpdate = "UPDATE minhpro99 SET vietnamese = ? , "
                + "pronunciation = ?, "
                + "available = ? "
                + "WHERE idx = ?";
        try {
            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(cmdUpdate);
            ResultSet rs = st.executeQuery(cmd);
            while (rs.next()) {
                Integer avail = rs.getInt("available");

                if (avail > 0) {
                    return "Word has existed!";
                }
                ps.setString(1, vietnamese);
                ps.setString(2, pronun);
                ps.setInt(3, 1);
                ps.setInt(4, rs.getInt("idx"));

                ps.executeUpdate();
                myHintManager.addMoreWord(english);
                return "Word has been added successfully!";
            }


            ps = conn.prepareStatement(ins);
            ps.setString(3, vietnamese);
            ps.setString(4, pronun);
            ps.setInt(5, 1);
            ps.setInt(1, this.getHighestId()+1);
            ps.setString(2, english);
            myHintManager.addMoreWord(english);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Word has been added successfully!";

    }

    public String deleteWord(HintManager myHintManager, String english) throws SQLException{
        String cmd = "SELECT idx, available FROM minhpro99 WHERE English = '"+english+"'";
        String cmdUpdate = "UPDATE minhpro99 SET available = ?, vietnamese = ?, pronunciation = ?"
                + "WHERE idx = ?";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Integer avail = rs.getInt("available");
                if (avail == 0) return "Word not found!";
                PreparedStatement ps = conn.prepareStatement(cmdUpdate);
                ps.setInt(1,0);
                ps.setInt(4,rs.getInt("idx"));
                ps.setString(2,"Word has been deleted!");
                ps.setString(3,"");
                ps.executeUpdate();
                myHintManager.deleteWord(english);
                return "Deleted successfully!";
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Word not found!";
    }

    public String updateWord(String english, String vietnamese, String pronun) throws  SQLException{
        String cmd = "SELECT idx, available FROM minhpro99 WHERE English = '"+english+"'";
        String cmdUpdate = "UPDATE minhpro99 SET vietnamese = ? , "
                + "pronunciation = ? "
                + "WHERE idx = ?";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Integer avail = rs.getInt("available");
                if (avail == 0) return "Word not found!";
                PreparedStatement ps = conn.prepareStatement(cmdUpdate);
                if (!vietnamese.equals("")) ps.setString(1, vietnamese);
                if (!pronun.equals("")) ps.setString(2, pronun);
                ps.setInt(3, rs.getInt("idx"));
                ps.executeUpdate();
                return "Updated success!";
            }

            //return "Invalid data!";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Word not found!";
    }

}
