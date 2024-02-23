package Swing;

import Logic.FileManager;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CDMX extends JFrame{
    private final File Ruta;
    private String Code;
    private String Root;
    
    public CDMX(){
        Ruta = new File("src");
        Root = Ruta.getAbsolutePath() + "\\";
        setFrame();
    }
    
    private void setFrame(){
        setMainPanel();
        setTextArea();
        pack();
        
        setIconImage(new ImageIcon(getClass().getResource("CDMX.jpg")).getImage());
        setDefaultCloseOperation(3);
        setTitle("CIUDAD DE MEXICO");
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private void setMainPanel(){
        MainPanel = new JPanel();
        
        MainPanel.setPreferredSize(new java.awt.Dimension(500,250));
        MainPanel.setLayout(null);
        add(MainPanel);
    }
    
    private void setTextArea(){
        JTextArea Scripting = new JTextArea();
        
        Scripting.setFont(new java.awt.Font("Product Sans", 0, 14));
        Scripting.setBounds(0, 0, 500, 250);
        Scripting.setBackground(Color.BLACK);
        Scripting.setForeground(Color.WHITE);
        Scripting.setCaretColor(Color.WHITE);
        Scripting.setText(Ruta.getAbsolutePath() + "\\");
        Scripting.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                Code += e.getKeyChar();
                if (e.getKeyChar() == '\n'){
                    //Revisa si el codigo es para crear folders
                    if (Code.contains(Root) && Code.contains("MKdir<") && Code.contains(">\n")) {
                        if (FileManager.CrearDirectorio(Code)){
                            Scripting.setText(Scripting.getText() + "\n> ¡Se ha creado la carpeta existosamente!\n\n");
                        } else Scripting.setText(Scripting.getText() + "\n> ¡No se ha podido crear la carpeta!\n\n");
                    //Revisa si es para crear Archivos
                    } else if (Code.contains(Root) && Code.contains("Mfile<") && Code.contains(">\n")){
                        if (FileManager.CrearArchivo(Code)){
                            Scripting.setText(Scripting.getText() + "\n> ¡Se ha creado el archivo existosamente!\n\n");
                        } else Scripting.setText(Scripting.getText() + "\n> ¡No se ha podido crear el archivo!\n\n");
                    } else if (Code.contains(Root) && Code.contains("Rm<") && Code.contains(">\n")){
                        if (FileManager.BorrarFile(Code)){
                            Scripting.setText(Scripting.getText() + "\n> ¡Se ha borrado el objeto seleccionado!\n\n");
                        } else Scripting.setText(Scripting.getText() + "\n> ¡No se ha podido borrar el objeto deseado!\n\n");
                    } else if (Code.contains(Root) && Code.contains("Cd<") && Code.contains(">\n")){
                        Root = Ruta.getAbsolutePath() + FileManager.addtoRoot(Code) + "\\";
                    } else if (Code.contains(Root) && Code.contains("Date\n")){
                        Scripting.setText(Scripting.getText() + "\nLa fecha actual es:" + FileManager.fecha() + "\n");
                    } else if (Code.contains(Root) && Code.contains("Time\n")){
                        Scripting.setText(Scripting.getText() + "\nLa hora actual es:" + FileManager.hora() + "\n");
                    }
                    Scripting.setText(Scripting.getText() + Root);
                    Code = Root;
                }
            }
        });
        Scripting.setSelectionStart(Root.length());
        JScrollPane Skroll = new JScrollPane(Scripting);
        
        Skroll.setBounds(Scripting.getBounds());
        MainPanel.add(Skroll);
    }
    
    public static void main(String[] args) {
        new CDMX().setVisible(true);
    }
    
    //-- SWING ELEMENTS --
    private JPanel MainPanel;
    //-- SWING ELEMENTS --
}
