package Faber.DAO;

import Faber.Connection.MySQLConnect;
import Faber.DTO.UrlDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Hung Hau
 */
public class UrlDAO {

    /**
     * Get all url from database
     *
     * @param url
     * @return
     */
    public List<String> getListUrl(String url, String user) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<>();
        int freequency = 0;

        try {
            String sql = "SELECT * FROM Url WHERE LinkUrl LIKE ? and IdUser = ? ORDER BY TimeSave DESC";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, "%" + url);
            ps.setInt(2, Integer.valueOf(user));
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("LinkUrl"));
                freequency = rs.getInt("Frequent");
            }
            if(list.size() > 0)
                list.add(String.valueOf(freequency));
            
        } catch (Exception ex) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    /**
     * Check url exist in database or not
     *
     * @param url
     * @param dateSave
     * @param user
     * @return
     */
    public String checkUrl(String url, java.sql.Date dateSave, String user) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "false";

        try {
            String sql = "SELECT * FROM Url WHERE LinkUrl LIKE ? AND IdUser = ? ORDER BY TimeSave DESC";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, "%" + url);
            ps.setInt(2, Integer.valueOf(user));
            rs = ps.executeQuery();

            while (rs.next()) {
                java.sql.Date date = rs.getDate("TimeSave");
                String strDate = date.toString();
                String strDateSave = dateSave.toString();
                if (strDate.equals(strDateSave)) {
                    result = rs.getString("linkUrl") + "&&frequency=" + rs.getInt("Frequent");
                }
                break;
            }
        } catch (Exception ex) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return result;
    }
    
    public String checkUrl(String url, String user) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "false";

        try {
            String sql = "SELECT * FROM Url WHERE LinkUrl LIKE ? AND IdUser = ? ORDER BY TimeSave DESC";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, "%" + url);
            ps.setInt(2, Integer.valueOf(user));
            rs = ps.executeQuery();

            while (rs.next()) {
                result = rs.getString("linkUrl");
                break;
            }
        } catch (Exception ex) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return result;
    }

    /**
     * Add url into database
     *
     * @param url
     * @param timeSave
     * @return
     */
    public boolean addUrl(UrlDTO urlDto) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = MySQLConnect.getConnection();
            String sql = "INSERT INTO Url (IdUser,Frequent,LinkUrl, TimeSave, Pc,Tablet,Mobile,Html) VALUES (?,?,?,?,?,?,?,?)";
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, urlDto.getIdUser());
            ps.setInt(2, urlDto.getFrequent());
            ps.setString(3, urlDto.getLinkUrl());
            ps.setDate(4, urlDto.getTimeSave());
            ps.setString(5, urlDto.getPc());
            ps.setString(6, urlDto.getTablet());
            ps.setString(7, urlDto.getMobile());
            ps.setString(8, urlDto.getHtml());
            ps.executeUpdate();
            return true;

        } catch (Exception ex) {
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean deleteUrl(String url, java.sql.Date time, String user) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = (Connection) MySQLConnect.getConnection();
            String sql = "DELETE FROM Url WHERE LinkUrl LIKE ? AND TimeSave = ? AND IdUser = ?";
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, "%" + url);
            ps.setDate(2, time);
            ps.setInt(3, Integer.valueOf(user));
            ps.executeUpdate();
            return true;

        } catch (Exception ex) {
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    public boolean updateFrequency(String url, String user, String freequency) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = (Connection) MySQLConnect.getConnection();
            String sql = "UPDATE Url SET Frequent = ? WHERE LinkUrl LIKE ? AND IdUser = ?";
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(freequency));
            ps.setString(2, "%" + url);
            ps.setInt(3, Integer.valueOf(user));
            ps.executeUpdate();
            return true;

        } catch (Exception ex) {
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
