import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;





/**
 * @author cris
 *
 */

public class main {
        public static List<Usuario>Usuarios;
        public static List<Paciente>pacientes;
        public static List<Medico>medicos;
        public static List<Cita>citas;
    private static String json;
 
    public static void main (String[] args){
  
    	
    	Usuarios_Cargados();
       boolean Usuario_existe;
       boolean exit;
       String nombre_Usuario="";
       Integer id_Usuario=0;
       Integer password=0;
       
      
       Scanner matriculas= new Scanner(System.in);  
        

            
            System.out.println("*----------------------------------------------------------*");
            System.out.println("----------- Bienvenido a tu creador de citas --------------");
            System.out.println("*----------------------------------------------------------*\n");
            System.out.print("Ingresa el nombre del Usuario: ");
            nombre_Usuario = matriculas.nextLine();
            System.out.print("Ingresa el ID del Usuario: ");
            id_Usuario = matriculas.nextInt();
            System.out.print("Ingresa la contraseña: ");
            password=matriculas.nextInt();
            Usuario_existe= validar_datos(nombre_Usuario,id_Usuario,password);
            
                if(Usuario_existe){
 
                   System.out.println("               Bienvenido "+nombre_Usuario +"       ");
 

                    menu();                                        
                }else{
                    System.out.println("El Usuario no existe, no puedes acceder al sistema");
                }
               

         } 
   public static  void menu(){
       int seleccion=0;
       do{
     Scanner scn= new Scanner(System.in);
     System.out.println("OPCIONES");
     System.out.println("Ingresa 1 para Crear una cita");
     System.out.println("Ingresa 2 para Dar de alta a un paciente");
     System.out.println("Ingresa 3 para Dar de alta a un médico");
     System.out.println("Ingresa 0 para Salir del sistema");
seleccion=scn.nextInt();
        switch(seleccion){
            case 1:
                crear_Cita();
                break;
            case 2:
                crear_Paciente();
                break;
            case 3:
                crear_Medico();
                break;
            case 0:
                System.out.println("Salida de manera exitosa, vuelva pronto");
                System.exit(0);
                break;
            default: System.out.println("LA OPCIÓN QUE ELEGISTE NO EXISTE");
            
        }
       }
     while(seleccion>0|| seleccion<5);     
   }

// Carga de Usuarios
   public static void Usuarios_Cargados(){     
           if(Usuarios==null){
               Usuarios= new ArrayList<>();
           }      
           Usuarios.add(new Usuario("Cristian",22, 2323));
           Usuarios.add(new Usuario("Juan Carlos",21,2323));
           Usuarios.add(new Usuario("Laila",25,2323));
           Usuarios.add(new Usuario("Oscar",14,2323));
           Usuarios.add(new Usuario("Mel",13,2323));
           
            System.out.println("Los Usuarios se han cargado correctamente: " );
            System.out.println(+ Usuarios.size() +" Usuarios cargados en el sistema");
            
                   }
       
    public static boolean   validar_datos(String nombre_Usuario,Integer id_Usuario,Integer password){
        return Usuarios.stream().anyMatch(x->x.getNombre_usuario().equals(nombre_Usuario) && x.getId_usuario().equals(id_Usuario) && x.getPassword().equals(password)); 
                   }
  
// Crear paciente      
 public static boolean crear_Paciente(){
                Scanner pacienteSC= new Scanner(System.in);
               pacientes= new ArrayList<>();
        System.out.println("            DAR DE ALTA UN PACIENTE");
        System.out.println("Por favor ingresa el nombre del paciente");
        String nombre_paciente= pacienteSC.nextLine();
        System.out.println("Por favor ingresa el ID del paciente");
        Integer id_paciente= pacienteSC.nextInt();
        pacienteSC.nextLine();
        System.out.println("Por favor ingresa el área del paciente");
        String area= pacienteSC.nextLine();
        System.out.println("Por favor ingresa el ID del médico");
        Integer id_medico= pacienteSC.nextInt();
        System.out.println("Por favor ingresa la ID de la cita del paciente");
        Integer id_cita= pacienteSC.nextInt();
        pacienteSC.nextLine();
        
        if(pacientes.contains(id_paciente)){
              System.out.println("Paciente ya registrado");
    }
        else{
             Paciente paciente= new Paciente(nombre_paciente, id_paciente,area, id_medico, id_cita);
                              guardar_Paciente(paciente);
               return pacientes.add(paciente);       
    }
        return true;
    }
 
public static void guardar_Paciente(Paciente paciente){
        String jsonPaciente;
        Gson gson= new Gson();
        String nombre_Archivo="pacientes.txt";
        jsonPaciente=gson.toJson(paciente);
        System.out.println("Información del paciente en formato jason"+ jsonPaciente); 
        try{
          BufferedWriter writer= new BufferedWriter(new FileWriter(nombre_Archivo));
          writer.write(jsonPaciente);
          FileWriter filewriter=new FileWriter(nombre_Archivo);
          PrintWriter printwriter=new PrintWriter(filewriter);
          printwriter.print(jsonPaciente);
          printwriter.close();
        }catch(Exception e){
            System.out.println("Datos no almacenado");
        }
                System.out.println("Tarea lograda con satisfaccion");
                System.out.println("Puedes visualizar la información en documento de texto -pacientes.txt- \n");

    }
    public static void cargar_paciente(){       
    Gson gson= new Gson();
        Paciente paciente= gson.fromJson(json, Paciente.class);       
    }
        
    
// Crear Medico    
public static boolean crear_Medico(){
                Scanner medicoSC= new Scanner(System.in);
               medicos= new ArrayList<>();
        System.out.println("            DAR DE ALTA UN MEDICO");
        System.out.println("Por favor ingresa el nombre del médico");
        String nombre_medico= medicoSC.nextLine();
        System.out.println("Por favor ingresa el ID del medico");
        Integer id_medico= medicoSC.nextInt();
        medicoSC.nextLine();
        System.out.println("Por favor ingresa la especialidad del medico");
        String especialidad= medicoSC.nextLine();
        
        medicoSC.nextLine();
        if(medicos.contains(id_medico)){
              System.out.println("Medico ya registrado");
    }
        else{
             Medico medico= new Medico(id_medico,nombre_medico,especialidad);
                              guardar_Medico(medico);
               return medicos.add(medico);       
    }
        return true;
    }
    
public static void guardar_Medico(Medico medico){
        String jsonMedico;
        Gson gson= new Gson();

        String nombre_Archivo="medicos.txt";
        jsonMedico=gson.toJson(medico);
        System.out.println("Medico JSON"+ jsonMedico); 

        try{
          BufferedWriter writer= new BufferedWriter(new FileWriter(nombre_Archivo));
          writer.write(jsonMedico);
          FileWriter filewriter=new FileWriter(nombre_Archivo);
          PrintWriter printwriter=new PrintWriter(filewriter);
          printwriter.print(jsonMedico);
          printwriter.close();
        }catch(Exception e){
            System.out.println("NO SE PUDIERON GUARDAR LOS DATOS");
        }
                System.out.println("MEDICO DADO DE ALTA EXITOSAMENTE");
                System.out.println("Puedes visualizar la información en documento de texto -medicos.txt- \n");
    }  
 
public static void cargar_Medico(){
    Gson gson= new Gson();
        Medico medico= gson.fromJson(json, Medico.class);
  }

 /**======================MÉTODOS DE DADA DE ALTA DE CITAS=========================*/         

 public static boolean crear_Cita(){
    

                Scanner citaSC= new Scanner(System.in);
               citas= new ArrayList<>();
        System.out.println("================CREAR CITA================");
        System.out.println("Por favor ingresa el motivo de la cita");
        String motivo_cita= citaSC.nextLine();
        System.out.println("Por favor ingresa el ID de la cita");
        Integer id_cita= citaSC.nextInt();
        citaSC.nextLine();
        System.out.println("Por favor ingresa la fecha de la cita");
        String fecha_cita= citaSC.nextLine();
        System.out.println("Por favor ingresa el ID del médico");
        Integer id_medico= citaSC.nextInt();
        System.out.println("Por favor ingresa el ID del paciente");
        Integer id_paciente= citaSC.nextInt();
        
        if(citas.contains(id_cita)){
              System.out.println("LA CITA YA EXISTE");
    }
        else{
             Cita cita= new Cita(id_cita,motivo_cita,fecha_cita,id_medico,id_paciente);
                              guardar_Cita(cita);
               return citas.add(cita);       
    }
        return true;
    }
 
public static void guardar_Cita(Cita cita){
        String jsonCita;
        Gson gson= new Gson();

        String nombre_Archivo="citas.txt";
        jsonCita=gson.toJson(cita);
        System.out.println("Paciente jason"+ jsonCita); 

        try{
          BufferedWriter writer= new BufferedWriter(new FileWriter(nombre_Archivo));
          writer.write(jsonCita);
          FileWriter filewriter=new FileWriter(nombre_Archivo);
          PrintWriter printwriter=new PrintWriter(filewriter);
          printwriter.print(jsonCita);
          printwriter.close();
        }catch(Exception e){
            System.out.println("NO SE PUDIERON GUARDAR LOS DATOS");
        }
                System.out.println("CITA DADA DE ALTA EXITOSAMENTE");
                System.out.println("Puedes visualizar la información en documento de texto -citas.txt- \n");

    }  

public static void cargar_Cita(){
    Gson gson= new Gson();
        Cita cita= gson.fromJson(json, Cita.class);
  }
}


