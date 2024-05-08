package buscaminas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel{
    
    private Unidad[] todo;
    private int[] minas;
    
    public Panel(int m){
        setLayout(null);
        setBounds(0,0,Grafico.anchoframe,Grafico.altoframe);
        
        todo = new Unidad[500];
        for(int i=0;i<todo.length;i++)
            todo[i] = new Unidad();
        
        minas = NumerosAleatorios.generarAleatorios(m, 0, 500);
        asignarMinas();
        contarVecinos();
        
        agregarComponentes();
        setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawRect(15, 55, 600, 750);
        
        int posx = 15, posy = 55;
        for(int i=0;i<25;i++){
            for(int j=0;j<20;j++){
                g.drawRect(posx,posy,30,30);
                posx += 30;
            }
            posx = 15;
            posy += 30;
        }
    }
    
    public void asignarMinas(){
        ImageIcon icon = new ImageIcon(getClass().getResource("mina.png"));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        for(int k=0;k<minas.length;k++){
            todo[minas[k]].ponerMina();
            todo[minas[k]].getEtiqueta().setIcon(icono);
        }
    }
    
    public void agregarComponentes(){
        
        int ind = 0,posx = 15, posy = 55;
        for(int i=0;i<25;i++){
            for(int j=0;j<20;j++){
                todo[ind].getBoton().setLocation(posx,posy);
                Acciones part = new Acciones(todo[ind], this, ind);
                todo[ind].getBoton().addMouseListener(part);
                todo[ind].getBoton().addComponentListener(part);
                todo[ind].getEtiqueta().setLocation(posx,posy);
                todo[ind].ponerVecinos();
                this.add(todo[ind].getBoton());
                this.add(todo[ind].getEtiqueta());
                posx += 30;
                ind++;
            }
            posx = 15;
            posy += 30;
        }
    }
    
    public void verBotones(boolean b){
        if(!b)
            for(int s=0;s<todo.length;s++)
                todo[s].getBoton().setVisible(b);
    }
    
    public void contarVecinos(){
        int ind = 0;
        for(int i=0;i<25;i++){
            for(int j=0;j<20;j++){
                if(i == 0){
                    if(j == 0){ // Esquina Superior Izquierda
                        verE(ind);
                        verSE(ind);
                        verS(ind);
                    }else if(j == 19){ // Esquina Superior Derecha
                        verO(ind);
                        verSO(ind);
                        verS(ind);
                    }else{ // Arista Superior
                        verE(ind);
                        verSE(ind);
                        verS(ind);
                        verSO(ind);
                        verO(ind);
                    }
                }else if(i == 24){
                    if(j == 0){ // Esquina Inferior Izquierda
                        verN(ind);
                        verNE(ind);
                        verE(ind);
                    }else if(j == 19){ // Esquina Inferior Derecha
                        verN(ind);
                        verNO(ind);
                        verO(ind);
                    }else{ // Arista Inferior
                        verN(ind);
                        verNE(ind);
                        verE(ind);
                        verO(ind);
                        verNO(ind);
                    }
                }else if(j == 0){ // Arista Izquierda
                    verN(ind);
                    verNE(ind);
                    verE(ind);
                    verSE(ind);
                    verS(ind);
                }else if(j == 19){ // Arista Derecha
                    verN(ind);
                    verS(ind);
                    verSO(ind);
                    verO(ind);
                    verNO(ind);
                }else{ // Centro
                    verN(ind);
                    verNE(ind);
                    verE(ind);
                    verSE(ind);
                    verS(ind);
                    verSO(ind);
                    verO(ind);
                    verNO(ind);
                }
                ++ind;
            }
        }
    }
    // Métodos Comprobadores de Minas
    private void verN(int ind){
        if(todo[ind - 20].tieneMina())
            todo[ind].sumarVecino();
    }    
    private void verNE(int ind){
        if(todo[ind - 19].tieneMina())
            todo[ind].sumarVecino();
    }    
    private void verE(int ind){
        if(todo[ind + 1].tieneMina())
            todo[ind].sumarVecino();
    }    
    private void verSE(int ind){
        if(todo[ind + 21].tieneMina())
            todo[ind].sumarVecino();
    }    
    private void verS(int ind){
        if(todo[ind + 20].tieneMina())
            todo[ind].sumarVecino();
    }    
    private void verSO(int ind){
        if(todo[ind + 19].tieneMina())
            todo[ind].sumarVecino();
    }    
    private void verO(int ind){
        if(todo[ind - 1].tieneMina())
            todo[ind].sumarVecino();
    }    
    private void verNO(int ind){
        if(todo[ind - 21].tieneMina())
            todo[ind].sumarVecino();
    }
    
    public void despejarArea(int i){
        if(i == 0){ // Esquina Superior Izquierda
            desE(i);
            desSE(i);
            desS(i);
        }else if(i == 19){ // Esquina Superior Derecha
            desS(i);
            desSO(i);
            desO(i);
        }else if(i == 480){ // Esquina Inferior Izquierda
            desN(i);
            desNE(i);
            desE(i);
        }else if(i == 499){ // Esquina Inferior Derecha
            desN(i);
            desO(i);
            desNO(i);
        }else if(i > 0 && i < 19){ // Arista Superior
            desE(i);
            desSE(i);
            desS(i);
            desSO(i);
            desO(i);
        }else if(i > 480 && i < 500){ // Arista Inferior
            desN(i);
            desNE(i);
            desE(i);
            desO(i);
            desNO(i);
        }else if(i % 20 == 0){ // Arista Izquierda
            desN(i);
            desNE(i);
            desE(i);
            desSE(i);
            desS(i);
        }else if((i + 1) % 20 == 0){ // Arista Derecha
            desN(i);
            desS(i);
            desSO(i);
            desO(i);
            desNO(i);
        }else{ // Centro
            desN(i);
            desNE(i);
            desE(i);
            desSE(i);
            desS(i);
            desSO(i);
            desO(i);
            desNO(i);
        }
    }
    // Métodos Despejadores de Alrededores
    private void desN(int i){
        todo[i - 20].getBoton().setVisible(false);
    }
    private void desNE(int i){
        todo[i - 19].getBoton().setVisible(false);
    }
    private void desE(int i){
        todo[i + 1].getBoton().setVisible(false);
    }
    private void desSE(int i){
        todo[i + 21].getBoton().setVisible(false);
    }
    private void desS(int i){
        todo[i + 20].getBoton().setVisible(false);
    }
    private void desSO(int i){
        todo[i + 19].getBoton().setVisible(false);
    }
    private void desO(int i){
        todo[i - 1].getBoton().setVisible(false);
    }
    private void desNO(int i){
        todo[i - 21].getBoton().setVisible(false);
    }
}