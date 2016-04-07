/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Semester;
import DTO.Subject;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class SubjectDao {
    static private ConnectData connect = new ConnectData();
    
    /**
     * Get semester with Course ID
     */
    public List getListSubject(){
        
        List<Subject> listSubject = new ArrayList<Subject>();
        //Get connection with mysql server
        Connection con = connect.getConnection();
        String sql = "select * from Subject";
        
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = prepareState.executeQuery();
            
            while(rs.next()) {
                
               Subject subject = new Subject();
               
               subject.setId(rs.getInt("ID"));
               subject.setName(rs.getString("NameSubject"));
               listSubject.add(subject);
            }
            
            con.close();
            return listSubject;
            
        } catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        }
    }
}
