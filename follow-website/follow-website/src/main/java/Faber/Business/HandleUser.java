package Faber.Business;

import Faber.DAO.UserDAO;
import Faber.DTO.UserDTO;
import com.google.gson.Gson;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyen Hung Hau
 */
public class HandleUser {

    UserDAO userDao = new UserDAO();

    public boolean checkLogin(String json, HttpSession session) {

        try {
            Gson gson = new Gson();
            UserDTO user = gson.fromJson(json, UserDTO.class);
            user.setPassword(encryptMD5(user.getPassword()));
            String result = userDao.checkLogin(user);
            if (!result.equals("false")) {
                session.setAttribute("account", result);
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(HandleUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private String encryptMD5(String input) {
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
