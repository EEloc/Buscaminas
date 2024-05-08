package buscaminas;

import javax.swing.*;
import java.awt.Color;

public class Unidad {
    
    private JButton botones;
    private JLabel etiquetas;
    private int vecinos;
    private boolean tieneMina;
    private final Color[] nums = {new Color(0,0,109), new Color(0,109,10), 
        new Color(20,205,219), new Color(130,40,100), 
        new Color(250,130,0), new Color(250,0,110),
        new Color(23,250,170), new Color(230,220,75)};

    public Unidad() {
        botones = new JButton();
        botones.setSize(30,30);
        
        etiquetas = new JLabel();
        etiquetas.setSize(30,30);
        
        tieneMina = false;
        
        vecinos = 0;
    }
    
    public JButton getBoton() {
        return botones;
    }

    public JLabel getEtiqueta() {
        return etiquetas;
    }
    
    public boolean tieneMina(){
        return tieneMina;
    }
    
    public boolean tieneVecinos(){
        if(vecinos == 0)
            return false;
        else
            return true;
    }
    
    public void sumarVecino(){
        vecinos++;
    }
    
    public void ponerVecinos(){
        if(tieneVecinos() && !tieneMina){
            etiquetas.setForeground(nums[vecinos - 1]);
            etiquetas.setHorizontalAlignment(0);
            etiquetas.setText(vecinos+"");
        }
    }
    public void ponerMina(){
        tieneMina = true;
    }
    
}
