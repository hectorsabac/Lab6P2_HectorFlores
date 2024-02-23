/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import java.io.File;
import javax.swing.JOptionPane;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FileManager {
    private static File Archivos;
    
    public static boolean CrearDirectorio(String Code) {
        String[] NombreDeCarpeta = Code.split("[<>]");
        String[] Direccion = Code.split("[/]");
        String DirURL = "";
        if (Direccion.length > 4){
            for (int Carpeta = 3; Carpeta < Direccion.length; Carpeta++){
                if (!Direccion[Carpeta].contains("MKdir")){
                    DirURL += Direccion[Carpeta] + "/";
                }
            }
        }
        DirURL += NombreDeCarpeta[1];
        Archivos = new File(DirURL);
        return Archivos.mkdirs();
    }
    
    public static boolean CrearArchivo(String Code) {
        try {
            String[] NombreDeCarpeta = Code.split("[<>]");
            String[] Direccion = Code.split("[/]");
            String DirURL = "";
            if (Direccion.length > 4){
                for (int Carpeta = 3; Carpeta < Direccion.length; Carpeta++){
                    if (!Direccion[Carpeta].contains("MKdir")){
                        DirURL += Direccion[Carpeta] + "/";
                    }
                }
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
        String[] OGRoot = Code.split("[/]");
        String neoRoot = "";
        if (OGRoot.length > 4){
            for (int Carpeta = 3; Carpeta < OGRoot.length; Carpeta++){
                if (!OGRoot[Carpeta].contains("MKdir")){
                    neoRoot += OGRoot[Carpeta] + "/";
                }
            }
        }
        neoRoot += AddedtoRoot[1];
        return neoRoot;
    }
    
    public static boolean BorrarFile(String Code){
        String[] NombreDeCarpeta = Code.split("[<>]");
        String[] Direccion = Code.split("[/]");
        String DirURL = "";
        if (Direccion.length > 4){
            System.out.println("Entro al loop");
            for (int Carpeta = 3; Carpeta < Direccion.length; Carpeta++){
                if (!Direccion[Carpeta].contains("MKdir")){
                    DirURL += Direccion[Carpeta] + "/";
                }
            }
        }
        DirURL += NombreDeCarpeta[1];
        Archivos = new File(DirURL);
        return BorrarFolder(Archivos);
    }
    
    private static boolean BorrarFolder(File F){
        if (F.isDirectory()){
            for (File listFiles : F.listFiles()){
                BorrarFolder(F);
            }
        }
        return F.delete();
    }
    
    public static String fecha(){
        SimpleDateFormat simple = new SimpleDateFormat("EEE MM/dd/yy", Locale.US);
        Date fecha = new Date();
        return simple.format(fecha);
    }
    
    public static String hora(){
        SimpleDateFormat simple = new SimpleDateFormat("HH:mm:ss:SSS");
        Date fecha = new Date();
        return simple.format(fecha);
    }
    
}