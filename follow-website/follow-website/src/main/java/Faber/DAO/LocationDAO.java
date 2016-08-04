package Faber.DAO;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Faber.Connection.MySQLConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class LocationDAO {
    
    //<editor-fold defaultstate="collapsed" desc="GET LIST LOCATION">
    public List<String> getListLocation() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> listLocation = new ArrayList<String>();

        try {
            String sql = "SELECT CanonicalName FROM Adwords_Location WHERE StatusLocation = 'Active'";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listLocation.add(rs.getString("CanonicalName"));
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
        return listLocation;
    }
    //</editor-fold>
}
