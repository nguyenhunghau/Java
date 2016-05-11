package Faber.DAO;

import Faber.Connection.MySQLConnect;
import Faber.DTO.UrlDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Hung Hau
 */
public class HandleUrlDAO {

    /**
     * Get all url from database
     *
     * @param url
     * @return
     */
    public List<UrlDTO> getListUrl(String url) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UrlDTO> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM URL WHERE LinkUrl LIKE ? ORDER BY TimeSave ";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, "%" + url);
            rs = ps.executeQuery();

            while (rs.next()) {
                //Substring url get from database and remove "save/2010-01-01_00:00:01.123"
                if (rs.getString("LinkUrl").substring(24).equals(url)) {
                    UrlDTO urlDto = new UrlDTO();
                    urlDto.setLinkUrl(rs.getString("LinkUrl"));
                    urlDto.setTimeSave(rs.getTimestamp("TimeSave"));
                    list.add(urlDto);
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
        return list;
    }

    /**
     * Check url exist in database or not
     *
     * @param url
     * @return
     */
    public String checkUrl(String url) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "false";

        try {
            String sql = "SELECT * FROM URL WHERE LinkUrl LIKE ? ORDER BY TimeSave DESC";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, "%" + url);
            rs = ps.executeQuery();

            while (rs.next()) {
                result = "save/" + rs.getString("LinkUrl") + "&&type=check";
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
    public boolean addUrl(String url, Timestamp timeSave) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = MySQLConnect.getConnection();
            String sql = "INSERT INTO URL (LinkUrl, TimeSave) VALUES (?, ?)";
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, url);
            ps.setTimestamp(2, timeSave);
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
