package Faber.Business;

import Faber.DAO.UserDAO;
import Faber.DTO.UserDTO;
import com.google.gson.Gson;
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

}
