package Swing;
import Logic.FileManager2;
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
    boolean Writing;
    private final File Ruta;
    private String Code = "";
    private String Root;
    String accum;
    
    public CDMX(){
        Ruta = new File("src");
        Root = Ruta.getAbsolutePath() + "\\";
        Writing = false;
        Code = Root;
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
        
        MainPanel.setPreferredSize(new java.awt.Dimension(1000,500));
        MainPanel.setLayout(null);
        add(MainPanel);
    }
    
    private void setTextArea(){
        JTextArea Scripting = new JTextArea();
        
        Scripting.setFont(new java.awt.Font("Product Sans", 0, 14));
        Scripting.setBounds(0, 0, 1000, 500);
        Scripting.setBackground(Color.BLACK);
        Scripting.setForeground(Color.WHITE);
        Scripting.setCaretColor(Color.WHITE);
        Scripting.setText(Ruta.getAbsolutePath() + "\\");
        Scripting.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                Code += e.getKeyChar();
                if (e.getKeyChar() == '\n' && !Writing){
                    System.out.println(Code);
                    //Revisa si el codigo es para crear folders
                    if (Code.contains(Root) && Code.contains("Mkdir <") && Code.contains(">\n")) {
                        if (FileManager2.CrearDirectorio(Code)){
                            Scripting.setText(Scripting.getText() + "\n> ¡Se ha creado la carpeta existosamente!\n\n");
                        } else Scripting.setText(Scripting.getText() + "\n> ¡No se ha podido crear la carpeta!\n\n");
                    //Revisa si es para crear Archivos
                    } else if (Code.contains(Root) && Code.contains("Mfile <") && Code.contains(">\n")){
                        if (FileManager2.CrearArchivo(Code)){
                            Scripting.setText(Scripting.getText() + "\n> ¡Se ha creado el archivo existosamente!\n\n");
                        } else Scripting.setText(Scripting.getText() + "\n> ¡No se ha podido crear el archivo!\n\n");
                    //Revisa si es para eliminnar archivos
                    } else if (Code.contains(Root) && Code.contains("Rm <") && Code.contains(">\n")){
                        if (FileManager2.BorrarFile(Code)){
                            Scripting.setText(Scripting.getText() + "\n> ¡Se ha borrado el objeto seleccionado!\n\n");
                        } else Scripting.setText(Scripting.getText() + "\n> ¡No se ha podido borrar el objeto deseado!\n\n");
                    //Revisa si es para cambiar de directorio
                    } else if (Code.contains(Root) && Code.contains("Cd <") && Code.contains(">\n")){
                        Root = Ruta.getAbsolutePath() + FileManager2.addtoRoot(Code) + "\\";
                    //Revisa si es para listar los elementos del directorio
                    } else if (Code.contains(Root) && Code.contains("dir\n")){
                        Scripting.setText(Scripting.getText() + FileManager2.dir(Root));
                    //Revisa si es para Mostrar la fecha actual
                    } else if (Code.contains(Root) && Code.contains("Date\n")){
                        Scripting.setText(Scripting.getText() + "La fecha actual es: " + FileManager2.fecha() + "\n");
                    //Revisa si es para ver la hora actual
                    } else if (Code.contains(Root) && Code.contains("Time\n")){
                        Scripting.setText(Scripting.getText() + "La hora actual es: " +FileManager2.hora() + "\n");
                    } else if (Code.contains(Root) && Code.contains("Escribir <") && Code.contains(">\n")){
                        if (FileManager2.FileExists(Code)){
                            Scripting.setText(Scripting.getText() + "\n> Mensaje que desea escribir (para concretar ingrese un .):\n");
                            Writing = true;
                            accum = "";
                        }
                    } else if (Code.contains(Root) && Code.contains("Leer <") && Code.contains(">\n")){
                        Scripting.setText(Scripting.getText() + FileManager2.getFileInfo(Code));
                    } else if (Code.contains(Root) && Code.contains("<...>\n")){
                        FileManager2.Returnfrom(Code);
                    }
                    
                    if (!Writing){
                        Scripting.setText(Scripting.getText() + Root);
                        Code = Root;
                    }
                } else if (e.getKeyChar() == '.' && Writing){
                    FileManager2.Write(Root, accum);
                    accum = "";
                    System.out.println("Terminado");
                    Writing = false;
                    Scripting.setText(Scripting.getText() + "\n" + Root);
                    Code = Root;
                } else if (Writing) {
                    System.out.println("Escribiendo");
                    accum += e.getKeyChar();
                    System.out.println(accum);
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
