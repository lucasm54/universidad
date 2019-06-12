/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class Alumno {
    

        private int idAlumno;
        private String nombre;
        private LocalDate fecNac;
        private boolean activo;

        
  //Constructor 
        
        
    public Alumno(int id, String nombre, LocalDate fecNac, boolean activo) {
        this.idAlumno= id;
        this.nombre = nombre;
        this.fecNac = fecNac;
        this.activo = activo;
    }

   
    public Alumno(String nombre, LocalDate fecNac, boolean activo) {
        this.nombre = nombre;
        this.fecNac = fecNac;
        this.activo = activo;
    }

    public Alumno() {
    }
       
//Metodos
     public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getId() {
        return idAlumno;
    }

    public void setId(int id) {
        this.idAlumno = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecNac() {
        return fecNac;
    }

    public void setFecNac(LocalDate fecNac) {
        this.fecNac = fecNac;
    }


    @Override
    public String toString() {
        return idAlumno + "-"+ nombre ;
        
    }
        
        
        
        
        
        
        
        
        
        
                }



