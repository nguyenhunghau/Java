package DTO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class User {
    private int id;
    private int typeId;
    private String strUsername;
    private String strPassword;

    /**
     * @return the Id
     */
    public int getId() {
        return id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.id = Id;
    }

    /**
     * @return the TypeId
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * @param TypeId the TypeId to set
     */
    public void setTypeId(int TypeId) {
        this.typeId = TypeId;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return getStrPassword();
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.setStrPassword(Password);
    }

    /**
     * @return the strUsername
     */
    public String getStrUsername() {
        return strUsername;
    }

    /**
     * @param strUsername the strUsername to set
     */
    public void setStrUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    /**
     * @return the strPassword
     */
    public String getStrPassword() {
        return strPassword;
    }

    /**
     * @param strPassword the strPassword to set
     */
    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }
}
