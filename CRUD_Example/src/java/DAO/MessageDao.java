/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author root
 */
public class MessageDao {
    
    public static String getMessage(String id){
        switch(id){
            case "1":
                return "Update data successful";
            case "2":
                return "Can not update data";
            case "3":
                return "Insert data successful";
            case "4":
                return "Can not insert data";
            case "5":
                return "Update score successful";
            case "6":
                return "Can not update score";
            case "7":
                return "Add class successful";
            case "8":
                return "Can not add class";
            case "9":
                return "Update class successful";
            case "10":
                return "Can not update class";
            default:
                break;
        }
        return "";
    }
}
