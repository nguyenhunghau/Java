package Faber.DAO;

import Faber.Connection.MySQLConnect;
import Faber.DTO.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Hung Hau
 */
public class UserDAO {

    public String checkLogin(UserDTO user) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "false";

        try {
            String sql = "SELECT * FROM User WHERE Username = ? and Passwords = ?";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();

            while (rs.next()) {
                result = String.valueOf(rs.getInt("Id"));
                break;
            }
        } catch (Exception ex) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
            return result;
        }
    }
}
