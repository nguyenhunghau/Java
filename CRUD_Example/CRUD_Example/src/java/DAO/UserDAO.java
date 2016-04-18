package DAO;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import DTO.UserDTO;
import com.mysql.jdbc.PreparedStatement;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class UserDAO {
    
    private static ConnectDataDAO connect = new ConnectDataDAO();
    
    public UserDTO getUser(String strUsername, String strPassword) throws SQLException{
        
        //Get connection with mysql server
        Connection con = connect.getConnection();
        PreparedStatement prepareState;
        UserDTO user = null;
        String sql = "select * from User where UserName = ? and Passwords = ?";
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1,strUsername);
            prepareState.setString(2,strPassword);

            ResultSet rs = prepareState.executeQuery();
            
            while(rs.next()) {
               user = new UserDTO();
               user.setId(rs.getInt("ID"));
               user.setTypeId(rs.getInt("TypeID"));
               user.setStrUsername(rs.getString("Username"));
               user.setStrPassword(rs.getString("Passwords"));
            }
            
            return user;
            
        } catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        } finally {
            con.close();
        }
    }
    
    /**
     * Encrypt password of user
     */
    public String encryptMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
