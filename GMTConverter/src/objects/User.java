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
    private int id_usuario;
    private String nombre_usuario;
    private String clave_usuario;
    private int pin_usuario;
    
    
    //private String enteredPass;
    
    private Connection con;
    private Statement sentencia;
    private String query;
    private String DB_PATH,DB_USER,DB_PASS;
    private int connectionIntents;
    
    public User(){
        this.connectionIntents = 0;
        this.DB_PATH = "jdbc:mysql://mysql6.gear.host/sicfinal";
        this.DB_USER="sicfinal";
        this.DB_PASS="Gd5I-LJ-3Z2K";
        
    }
    
    public User(String username) {
        this.connectionIntents = 0;
        
        this.DB_PATH = "jdbc:mysql://mysql6.gear.host/sicfinal";
        this.DB_USER="sicfinal";
        this.DB_PASS="Gd5I-LJ-3Z2K";
        this.nombre_usuario=username;
        this.clave_usuario=null;
        /*TODO: Get data in a new thread and run a loading bar until it's done.
        Thread dwFDB=new Thread(new DownloadFromDB(username));
        dwFDB.start();
        */
        
        
        //getDataFromDB(username,password);
        getDataFromDB();

//catch(com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e){
            
        //}
    }
    
    //private void getDataFromDB(String usr,String pass){
    private void getDataFromDB(){
        try {
            query = "SELECT * FROM usuario WHERE nombre_usuario='" + nombre_usuario + "'";//+" AND clave_usuario='"+clave_usuario+"'"

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASS);
            sentencia = con.createStatement();
            ResultSet resultado = sentencia.executeQuery(query);

            while (resultado.next()) {
                this.id_usuario=resultado.getInt("id_usuario");
                this.clave_usuario = resultado.getString("clave_usuario");
                this.pin_usuario = resultado.getInt("pin_usuario");

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
    
    
    
}
