/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import java.util.List;

/**
 *
 * @author USER
 */
public class Universidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try{
           Conexion c=new Conexion();
           MateriaData md=new MateriaData(c);
           
           List<Materia> ltaMaterias = md.obtenerMaterias();
           for(Materia m:ltaMaterias){
                System.out.println("("+m.getId() +")Materia"+ "getNombre()");
            }
           
           
           
           CursadaData cd = new CursadaData(c);
           Cursada cursada = new Cursada();
           Alumno a;
           Materia m;
           AlumnoData ad = new AlumnoData(c);
                   
           a = ad.buscarAlumno(4);
           m = md.buscarMateria(3);
           cursada.setAlumno(a);
           cursada.setMateria(m);
           cursada.setNota(10);
           cd.guardarCursada(cursada);
       }
       catch (Exception ex) {
           System.out.println("Error :"+ex.getMessage());
       }
    }
    
}