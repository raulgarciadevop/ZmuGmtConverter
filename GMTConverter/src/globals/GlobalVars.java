/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globals;

/**
 *
 * @author Administrator
 */
public class GlobalVars {
    private final String path;
    private final String user;
    private final String pass;
    
    public GlobalVars(){
        this.path="jdbc:mysql://den1.mysql4.gear.host/ura$$";
        this.user="nein";
        this.pass="nein";
    }

    public String getPath() {
        return path;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
    
    
    
    
    
}
