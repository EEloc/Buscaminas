package buscaminas;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Acciones extends Adaptadora {
        
    private final Unidad uevento;
    private final Panel panel;
    private final int ind;
    private final ImageIcon icon = new ImageIcon(getClass().getResource("band.png"));
    private final ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

    public Acciones(Unidad u, Panel p, int i){
        uevento = u;
        panel = p;
        ind = i;
    }
    
    @Override
    public void componentHidden(ComponentEvent e){
        if(uevento.tieneMina())
            panel.verBotones(false);
        else if(!uevento.tieneVecinos()){
            panel.despejarArea(ind);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int b = e.getButton();
        if(uevento.getBoton().getIcon() != icono){
            if(b == 3)
                uevento.getBoton().setIcon(icono);
            else if(b == 1){
                uevento.getBoton().setVisible(false);
            }
        }
        else if(b == 3)
            uevento.getBoton().setIcon(null);
            
    }
}