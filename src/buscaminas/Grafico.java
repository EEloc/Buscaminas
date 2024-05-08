package buscaminas;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Grafico extends JFrame implements ActionListener{
    
    public static int anchoframe = 630, altoframe = 920;
    private Panel vista;
    private int cantminas = 80;
    
    public Grafico(){
        setLayout(null);
        setBounds(300,100,anchoframe,altoframe);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Buscaminas");
        setResizable(false);
        
        agregarMenu();
        vista = new Panel(cantminas);
        add(vista);
        
        
        setVisible(true);
    }
    
    private void agregarMenu(){
        
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(1, 3, 19, 19));
        menu.setBounds(15, 15, anchoframe-30, 35);
        
        JButton[] bmenu = new JButton[4];
        String[] nombres = {"Reiniciar", "80 minas", "120 minas", "160 minas"};
        for(byte h=0;h<4;h++){
            bmenu[h] = new JButton(nombres[h]);
            bmenu[h].addActionListener(this);
            menu.add(bmenu[h]);
        }
        menu.setVisible(true);
        this.add(menu);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Reiniciar")){
            vista.setEnabled(false);
            vista.setVisible(false);
            vista = new Panel(cantminas);
            add(vista);
        }else if(e.getActionCommand().equals("80 minas")){
            vista.setEnabled(false);
            vista.setVisible(false);
            vista = new Panel(80);
            add(vista);
            cantminas = 80;
        }else if(e.getActionCommand().equals("120 minas")){
            vista.setEnabled(false);
            vista.setVisible(false);
            vista = new Panel(120);
            add(vista);
            cantminas = 120;
        }else if(e.getActionCommand().equals("160 minas")){
            vista.setEnabled(false);
            vista.setVisible(false);
            vista = new Panel(160);
            add(vista);
            cantminas = 160;
        }
    }
}
