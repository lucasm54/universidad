/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import java.sql.SQLException;

/**
 *
 * @author USER
 */
    

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MateriaData {
    private Connection con;
    private Conexion conexion;

    public MateriaData(Conexion conexion) {
        try {
            this.conexion=conexion;
            con = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    //Método para guardar una materia
    public void guardarMateria(Materia materia){
        try {
            String sql = "INSERT INTO materia (nombre) VALUES ( ? );";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString (1, materia.getNombre());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                materia.setId(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar el alumno");
            }
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar una materia " + ex.getMessage());
        }
    }
    
 public List<Materia> obtenerMaterias(){
        List<Materia> materias = new ArrayList<Materia>();
            

        try {
            String sql = "SELECT * FROM materia;";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Materia materia;
            while(resultSet.next()){
                materia = new Materia();
                materia.setId(resultSet.getInt("idMateria"));
                materia.setNombre(resultSet.getString("nombre"));
               

                materias.add(materia);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener las materias: " + ex.getMessage());
        }
        
        
        return materias;
    }
    
    public Materia buscarMateria(int id){
    
        Materia materia=null;
    try {
            
            String sql = "SELECT * FROM materia WHERE idMateria =?;";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                materia = new Materia();
                materia.setId(resultSet.getInt("idMateria"));
                materia.setNombre(resultSet.getString("nombre"));
               

                
            }      
            statement.close();
            
            
            
            
    
        } catch (SQLException ex) {
            System.out.println("Error al recuperar una materia: " + ex.getMessage());
        }
        
        return materia;
    }
    public void borrarMateria (int id){
    
        
    try {
            
            String sql = "DELETE FROM materia WHERE idMateria =?;";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar una materia: " + ex.getMessage());
        }
        
    
    
    }
    public void actualizarMateria(Materia materia){
    try {
            
            String sql = "UPDATE materia SET nombre = ? WHERE idMateria = ?;";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, materia.getNombre());
            statement.setInt(2, materia.getId());
            statement.executeUpdate();
            
          
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar una materia: " + ex.getMessage());
        }
     
        
    
    }
    }
    

