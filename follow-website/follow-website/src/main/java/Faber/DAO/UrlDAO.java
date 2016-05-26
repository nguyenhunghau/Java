package Faber.DAO;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Faber.Connection.MySQLConnect;
import Faber.DTO.UrlDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import static java.time.OffsetTime.now;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class UrlDAO {

    //<editor-fold defaultstate="collapsed" desc="GET LIST USL WITH URL AND USER">
    public List<UrlDTO> getListUrl(String url, String user) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UrlDTO> list = new ArrayList<>();

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

        } catch (SQLException | NumberFormatException ex) {
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GET LIST URL WILL RUN IN CRONJOB">
    public List<UrlDTO> getListUrl() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UrlDTO> list = new ArrayList<>();

        try {
            String sql = "SELECT IdUser, Frequent, LinkUrl, Pc, Html, max(TimeSave) as time"
                            + " FROM Url GROUP BY LinkUrl  ORDER BY TimeSave DESC ";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                java.sql.Date dateSave = rs.getDate("time");
                int frequent = rs.getInt("Frequent");
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, frequent * (-1)); 
                SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                String time = simpleFormat.format(cal.getTime());
                if (time.equals(dateSave.toString())) {
                    UrlDTO urlDto = new UrlDTO();
                    urlDto.setIdUser(rs.getInt("IdUser"));
                    urlDto.setLinkUrl(rs.getString("LinkUrl"));
                    urlDto.setPc(rs.getString("Pc"));
                    urlDto.setHtml(rs.getString("Html"));
                    list.add(urlDto);
                }
            }

        } catch (SQLException | NumberFormatException ex) {
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

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="CHECK URL IS SAVED IN DATABASE IN CURRENT DATE">
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
        } catch (SQLException | NumberFormatException ex) {
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="CHECK URL EXIST IN DATABASE OR NOT">
    /**
     * Check url exist in database or not
     *
     * @param url
     * @param user
     * @return
     */
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
        } catch (SQLException | NumberFormatException ex) {
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="ADD URL INTO DATABASE">
    /**
     * Add url into database
     *
     * @param urlDto
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="DELETE URL IN DATABASE">
    /**
     * Delete url from database
     *
     * @param url
     * @param time
     * @param user
     * @return
     */
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

        } catch (SQLException | NumberFormatException ex) {
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="UPDATE FREQUENCY">
     /**
     * Update freequency in database
     *
     * @param url
     * @param user
     * @param freequency
     * @return
     */
    public boolean updateFrequency(String url, String user, String freequency) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = (Connection) MySQLConnect.getConnection();
            String sql = "UPDATE Url SET Frequent = ? WHERE LinkUrl = ? AND IdUser = ?";
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(freequency));
            ps.setString(2, url);
            ps.setInt(3, Integer.valueOf(user));
            ps.executeUpdate();
            return true;

        } catch (SQLException | NumberFormatException ex) {
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
    //</editor-fold>
}
