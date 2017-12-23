/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static javax.swing.JOptionPane.showMessageDialog;
//import operations.DownloadFromDB;
/**
 *
 * @author Administrator
 */
public class User {
    //User login data
    private int id_usuario;
    private String nombre_usuario;
    private String clave_usuario;
    private int pin_usuario;
    
    //User GM Data
    private String gmName;
    private int gmt;
    private String location;
    private int timeFrom;
    private int timeTo;
    private int userRank;
    
    //Connection variables
    private Connection con;
    private Statement sentencia;
    private String query;
    private String DB_PATH,DB_USER,DB_PASS;
    private int connectionIntents;
    
    
    
    private void connectionSettings(){
        //Connection settings
        this.connectionIntents = 0;
        this.DB_PATH = "jdbc:mysql://sql10.freemysqlhosting.net/sql10212066";
        this.DB_USER="sql10212066";
        this.DB_PASS="Y1yE5Klb5w";
    }
    
    public User(){
        connectionSettings();
        
        //User GM Data
        this.gmt=0; 
        this.gmName=null; 
        this.location=null; 
        this.timeFrom=0; 
        this.timeTo=0;
        this.userRank=0;
    }
    
    public User(String username) {
        connectionSettings();
        //Get user
        this.nombre_usuario=username;
        this.clave_usuario=null;
        
        //User GM Data
        this.gmt=0; 
        this.gmName=null; 
        this.location=null; 
        this.timeFrom=0; 
        this.timeTo=0;
        this.userRank=0;
        getDataFromDB();
        
    }
    
    //private void getDataFromDB(String usr,String pass){
    private void getDataFromDB(){
        try {
            query = "SELECT * FROM users WHERE user_name='" + nombre_usuario + "'";//+" AND clave_usuario='"+clave_usuario+"'"

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASS);
            sentencia = con.createStatement();
            ResultSet resultado = sentencia.executeQuery(query);

            while (resultado.next()) {
                this.id_usuario=resultado.getInt("id_user");
                this.clave_usuario = resultado.getString("user_password");
                this.pin_usuario = resultado.getInt("user_pin");
                
                //User GM Data
                this.gmt=resultado.getInt("gm_gmt");
                this.gmName=resultado.getString("gm_name"); 
                this.location=resultado.getString("gm_location");
                this.timeFrom=resultado.getInt("gm_time_from");
                this.timeTo=resultado.getInt("gm_time_to");
                this.userRank=resultado.getInt("gm_rank");

            }
            //showMessageDialog(null, "= "+nombre_usuario+" "+clave_usuario+""+id_usuario+" "+pin_usuario+"\n\n"+DB_PATH+"\n"+DB_USER+"\n"+DB_PASS);
            /*
            while(resultado.next()){
            choice1.add(resultado.getString("nombre_usuario"));
            }
             */

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch(CommunicationsException e){
            this.DB_PATH = "jdbc:mysql://localhost/sic_final";
            this.DB_USER="root";
            this.DB_PASS="";
            connectionIntents++;
            if(connectionIntents>=1 && connectionIntents<=3)
                //getDataFromDB(nombre_usuario,clave_usuario);
                getDataFromDB();
            else{
                showMessageDialog(null, "Error: System is no available right now.\n\n Error code: 01LSVE ");
                System.exit(0);
            }
            
                
            
        }catch(SQLException e){
            e.printStackTrace();
            showMessageDialog(null, "Error SQL.");
        }
    }
    
    
    
    //public boolean authActualUP(String user, String pass){
    public boolean authActualUP(String pass){
        //return user.equals(this.nombre_usuario) && pass.equals(this.clave_usuario);
        return pass.equals(this.clave_usuario);
    }
    
    public boolean authActualPin(String pin){
        return Integer.parseInt(pin)==this.pin_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getClave_usuario() {
        return clave_usuario;
    }

    public void setClave_usuario(String clave_usuario) {
        this.clave_usuario = clave_usuario;
    }

    public int getPin_usuario() {
        return pin_usuario;
    }

    public void setPin_usuario(int pin_usuario) {
        this.pin_usuario = pin_usuario;
    }
    
    
    
    //public void updateGMT(int gmt,String location, int from, int to){
        
    //}
    
    public void updateUser(int gmt,String location, int from, int to){
        
        try {
            query="UPDATE users SET gm_gmt ="+gmt+", gm_location = '"+location+"', gm_time_from = "+from+", gm_time_to = "+to+" WHERE users.id_user = "+this.id_usuario;
            Class.forName("com.mysql.jdbc.Driver");
            sentencia.executeUpdate(query);
            getDataFromDB();
            showMessageDialog(null, "Updated successfully.");
            

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(CommunicationsException e){
            showMessageDialog(null, "Error: System is no available right now.\n\n Error code: 01LSVE ");
            
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getGmName() {
        return gmName;
    }

    public void setGmName(String gmName) {
        this.gmName = gmName;
    }

    public int getGmt() {
        return gmt;
    }

    public void setGmt(int gmt) {
        this.gmt = gmt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(int timeFrom) {
        this.timeFrom = timeFrom;
    }

    public int getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(int timeTo) {
        this.timeTo = timeTo;
    }

    public int getUserRank() {
        return userRank;
    }

    public void setUserRank(int userRank) {
        this.userRank = userRank;
    }
    
    
    
}
