package com.alkemy.paypocket.controllers.config.migrated;

public abstract class Ramdomizer {
    /**
     * Una clase Generica para traer cosas aleatorias
     */


     public static String nombre(){
        String[] nombresArgentinos = {
            "Lautaro", "Sofía", "Mateo", "Valentina", "Benjamín", "Emma", "Mía", "Lucas", "Isabella", "Bautista",
            "Catalina", "Thiago", "Martina", "Joaquín", "Martina", "Jerónimo", "Emilia", "Tomás", "Valentina", "Agustín",
            "Sofía", "Santiago", "Camila", "Nicolás", "Jazmín", "Juan", "Lucía", "Facundo", "Victoria", "Ignacio",
            "Antonella", "Sebastián", "Micaela", "Gonzalo", "Delfina", "Ezequiel", "Agustina", "Alejandro", "Renata", "Iván",
            "Abril", "Francisco", "Bianca", "Leonardo", "Celeste", "Maximiliano", "Romina", "Manuel", "Melina", "Gabriel"
        };
        return nombresArgentinos[(int)(Math.random()*50)];
     }

     
     public static String apellido(){
        String[] apellidosArgentinos = {
            "González", "Rodríguez", "López", "Fernández", "García", "Martínez", "Pérez", "Álvarez", "Romero", "Sánchez",
            "Gómez", "Díaz", "Torres", "Vargas", "Ramos", "Molina", "Silva", "Ortega", "Herrera", "Medina",
            "Schmidt", "Acosta", "Rojas", "Mendoza", "Ruiz", "Ramírez", "Morales", "Suárez", "Aguirre", "Moreno",
            "Jiménez", "Giménez", "Peralta", "Castro", "Ríos", "Navarro", "Ferreira", "Ojeda", "Luna", "Ponce",
            "Vega", "Cardozo", "Maidana", "Cabrera", "Vera", "Sosa", "Coronel", "Miranda", "Mansilla", "Benítez"
        };
        return apellidosArgentinos[(int)(Math.random()*50)];
        
     }


     public static String email(String nombre , String apellido){
        return nombre+"."+apellido+"@desarrollo-alkemy.com";
     }


     public static String password(){
        return "123456"; //Checate esa seguridad 
     }









    }
