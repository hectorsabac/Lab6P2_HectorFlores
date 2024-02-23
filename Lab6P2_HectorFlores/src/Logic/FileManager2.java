package Logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class FileManager2 {
    private static File Archivos;
    
    public static boolean CrearDirectorio(String Code) {
        String[] NombreDeCarpeta = Code.split("[<>]");
        String[] Direccion = Code.split("[\\\\]");
        String DirURL = "";
        System.out.println(Direccion.length);
        for (int Carpeta = 0; Carpeta < Direccion.length - 1; Carpeta++){
            DirURL += Direccion[Carpeta] + "\\";
        }
        DirURL += NombreDeCarpeta[1];
        System.out.println("DirURL: "+DirURL);
        Archivos = new File(DirURL);
        return Archivos.mkdirs();
    }
    
    public static boolean FileExists(String Code){
        String[] NombreDeCarpeta = Code.split("[<>]");
        String[] Direccion = Code.split("[\\\\]");
        String DirURL = "";
        for (int Carpeta = 0; Carpeta < Direccion.length - 1; Carpeta++){
            DirURL += Direccion[Carpeta] + "\\";
        }
        DirURL += NombreDeCarpeta[1];
        Archivos = new File(DirURL);
        return Archivos.exists();
    }
    
    public static boolean CrearArchivo(String Code) {
        try {
            String[] NombreDeCarpeta = Code.split("[<>]");
            String[] Direccion = Code.split("[\\\\]");
            String DirURL = "";
            for (int Carpeta = 0; Carpeta < Direccion.length - 1; Carpeta++){
                DirURL += Direccion[Carpeta] + "\\";
            }
            DirURL += NombreDeCarpeta[1];
            Archivos = new File(DirURL);
            return Archivos.createNewFile();
        } catch (Exception Ex){
            return false;
        }
    }
    
    public static String addtoRoot(String Code){
        String[] AddedtoRoot = Code.split("[<>]");
        String[] OGRoot = Code.split("[\\\\]");
        String neoRoot = "";
        for (int Carpeta = 0; Carpeta < OGRoot.length - 1; Carpeta++){
                neoRoot += OGRoot[Carpeta] + "\\";
        }
        File nuovaRuta = new File(neoRoot + AddedtoRoot[1]);
        if (nuovaRuta.exists()){
            neoRoot += AddedtoRoot[1] + "\\";
        }
        
        return neoRoot;
    }
    
    public static String getFileInfo(String Code){
        String[] AddedtoRoot = Code.split("[<>]");
        String[] OGRoot = Code.split("[\\\\]");
        String neoRoot = "";
        for (int Carpeta = 0; Carpeta < OGRoot.length - 1; Carpeta++){
                neoRoot += OGRoot[Carpeta] + "\\";
        }
        try{
            neoRoot += AddedtoRoot[1];
            File ReadFile = new File(neoRoot);
            Scanner FileReader = new Scanner(ReadFile);
            String text = "\n\t";
            while (FileReader.hasNextLine()){
                text += FileReader.nextLine();
            }
            text += "\n";
            return text;
        } catch (Exception Ex){
            return "\n> No se ha podido leer dicho archivo\n\n";
        }
    }
    
    public static boolean BorrarFile(String Code){
        String[] NombreDeCarpeta = Code.split("[<>]");
        String[] Direccion = Code.split("[\\\\]");
        String DirURL = "";
        for (int Carpeta = 0; Carpeta < Direccion.length - 1; Carpeta++){
                DirURL += Direccion[Carpeta] + "\\";
        }
        DirURL += NombreDeCarpeta[1];
        Archivos = new File(DirURL);
        return BorrarFolder(Archivos);
    }
    
    private static boolean BorrarFolder(File F){
        if (F.isDirectory()){
            for (File listFiles : F.listFiles()){
                BorrarFolder(listFiles);
            }
        }
        return F.delete();
    }

    public static String fecha(){
        SimpleDateFormat simple = new SimpleDateFormat("EEE MM/dd/yy", Locale.US);
        Date fecha = new Date();
        return simple.format(fecha) + "\n";
    }
    
    public static String hora(){
        SimpleDateFormat simple = new SimpleDateFormat("HH:mm:ss:SSS");
        Date fecha = new Date();
        return simple.format(fecha) + "\n";
    }
    
    public static String dir(String ruta){
        String accum = "";
        File archivo = new File (ruta);
        
        if (archivo.isDirectory()){
            accum += "Directorio de: " + archivo.getAbsolutePath() + "\n";
            
            for (File child : archivo.listFiles()){
                if (!child.isHidden()){
                    Date ultima = new Date (child.lastModified());
                    accum += ultima + "\t";
                    if (child.isDirectory()){
                        accum += "<DIR>\t" + child.getName() + "\t";
                    } else {
                        accum += "     \t" + child.getName() + "\t";
                    }
                    accum += "Size: " + child.length() + "\n";
                }
            }
        }
        return accum += "\n";
    }
    
    public static void Write(String Code, String mensaje){
        String[] NombreDeArchivo = Code.split("[<>]");
        String[] Direccion = Code.split("[\\\\]");
        String FileURL = "";
        for (int File = 0; File < Direccion.length - 1; File++){
            FileURL += Direccion[File] + "\\";
        }
        FileURL += NombreDeArchivo[1];
        
        
        File target = new File(FileURL);
        
        System.out.println(target.isFile());
        
        if (target.isFile()){

            try {
                FileWriter writer = new FileWriter(target);
                
                writer.write(mensaje);
                writer.close();
            } catch (IOException e) {
                System.out.println("Ocurrió un error al escribir en el archivo.");
                e.printStackTrace();
            }
        }
    }
    
    public static String Returnfrom(String Code){
        String[] OGRoot = Code.split("[\\\\]");
        String neoRoot = "";
        for (int Carpeta = 0; Carpeta < OGRoot.length - 2; Carpeta++){
                neoRoot += OGRoot[Carpeta] + "\\";
        }
        return neoRoot;
    }
    
}
