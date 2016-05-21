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
    public List<UrlDTO> getListUrl(String url, String user) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UrlDTO> list = new ArrayList<>();
        int freequency = 0;

        try {
            String sql = "SELECT * FROM Url WHERE LinkUrl = ? and IdUser = ? ORDER BY TimeSave DESC";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, url);
            ps.setInt(2, Integer.valueOf(user));
            rs = ps.executeQuery();

            while (rs.next()) {
                UrlDTO urlDto = new UrlDTO();
                urlDto.setIdUser(rs.getInt("IdUser"));
                urlDto.setFrequent(rs.getInt("Frequent"));
                urlDto.setLinkUrl(rs.getString("LinkUrl"));
                urlDto.setPc(rs.getString("Pc"));
                urlDto.setTimeSave(rs.getDate("TimeSave"));
                urlDto.setMobile(rs.getString("Mobile"));
                urlDto.setTablet(rs.getString("Tablet"));
                urlDto.setHtml(rs.getString("Html"));
                list.add(urlDto);
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
    public UrlDTO checkUrl(String url, java.sql.Date dateSave, String user) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UrlDTO urlDto = new UrlDTO();

        try {
            String sql = "SELECT * FROM Url WHERE LinkUrl = ? AND IdUser = ? ORDER BY TimeSave DESC";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, url);
            ps.setInt(2, Integer.valueOf(user));
            rs = ps.executeQuery();

            while (rs.next()) {
                java.sql.Date date = rs.getDate("TimeSave");
                String strDate = date.toString();
                String strDateSave = dateSave.toString();
                if (strDate.equals(strDateSave)) {
                    urlDto.setIdUser(rs.getInt("IdUser"));
                    urlDto.setFrequent(rs.getInt("Frequent"));
                    urlDto.setLinkUrl(rs.getString("LinkUrl"));
                    urlDto.setPc(rs.getString("Pc"));
                    urlDto.setTimeSave(rs.getDate("TimeSave"));
                    urlDto.setMobile(rs.getString("Mobile"));
                    urlDto.setTablet(rs.getString("Tablet"));
                    urlDto.setHtml(rs.getString("Html"));
                    break;
                }
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
        return urlDto;
    }
    
    public String checkUrl(String url, String user) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "false";

        try {
            String sql = "SELECT * FROM Url WHERE LinkUrl = ? AND IdUser = ? ORDER BY TimeSave DESC";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, url);
            ps.setInt(2, Integer.valueOf(user));
            rs = ps.executeQuery();

            while (rs.next()) {
                result = rs.getString("Html");
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
            String sql = "DELETE FROM Url WHERE LinkUrl = ? AND TimeSave = ? AND IdUser = ?";
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, url);
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
            String sql = "UPDATE Url SET Frequent = ? WHERE LinkUrl = ? AND IdUser = ?";
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(freequency));
            ps.setString(2,  url);
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
