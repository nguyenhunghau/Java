package DAO;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import static DAO.ConnectDataDAO.getConnection;
import DTO.SubjectDTO;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class SubjectDAO {
    
    //<editor-fold defaultstate="collapsed" desc="Get list subject">
    public List<SubjectDTO> getListSubject() throws SQLException{
        
        List<SubjectDTO> listSubject = new ArrayList<>();
        String strSql = "SELECT * FROM Subject";
        Connection con = getConnection();
        PreparedStatement prepareState = null;
        ResultSet resultSet = null;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            resultSet = prepareState.executeQuery();
            
            while(resultSet.next()) {
                
               SubjectDTO subject = new SubjectDTO();
               
               subject.setId(resultSet.getInt("Id"));
               subject.setStrName(resultSet.getString("NameSubject"));
               listSubject.add(subject);
            }
            
        } catch(Exception ex){
            System.err.println("Error Connect ");
            
        } finally {
            if (con != null) {
                con.close();
            }

            if (prepareState != null) {
                prepareState.close();
            }

            if (resultSet != null) {
                prepareState.close();
            }
            return listSubject;
        }
    }
//</editor-fold>
    
}
