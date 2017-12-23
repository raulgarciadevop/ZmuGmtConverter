/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proccess;

//import static javax.swing.JOptionPane.*;

import static javax.swing.JOptionPane.showMessageDialog;


/**
 *
 * @author Administrator
 */
public class GMTConverter {
    
    public GMTConverter(){
        
    }
    
    public int convert(int h,int gmt){
        
        int agmt=(gmt*=-1);
        
        return h+agmt;
    }
    
    
    /*
    public static void main(String args[]){
        GMTConverter gmtc=new GMTConverter();
        int h=0, gmt=0;
        showMessageDialog(null, "Hour:");
        h=Integer.parseInt(showInputDialog("Ingrese la hora:"));
        gmt=Integer.parseInt(showInputDialog("Ingrese su gmt:"));
        showMessageDialog(null, gmtc.convert(h,gmt));
    }
    */
    
    
}
