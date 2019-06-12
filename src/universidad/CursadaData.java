/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

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


public class CursadaData {
    private Connection con;
    private Conexion conexion;

    public CursadaData(Conexion conexion) {
        try {
            this.conexion=conexion;
            con = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    //Método para guardar una cursada
    public void guardarCursada(Cursada cursada){
        try {
            String sql = "INSERT INTO cursada (idAlumno, idMateria, nota) VALUES ( ? , ? , ? );";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cursada.getAlumno().getId());
            ps.setInt(2, cursada.getMateria().getId());
            ps.setDouble(3, cursada.getNota());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                cursada.setId(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id");
            }
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar Cursada " + ex.getMessage());
        }
    }
    
//Obtener todas las cursadas

    public List<Cursada> obtenerCursadas(){
        List<Cursada> cursadas = new ArrayList<Cursada>();
        AlumnoData ad = new AlumnoData(conexion);
        MateriaData md = new MateriaData(conexion);
        try {
            String sql = "SELECT * FROM cursada;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            Cursada cursada;
            while(resultSet.next()){
                cursada = new Cursada();
                cursada.setId(resultSet.getInt("idCursada"));
                
		     //Recupero el alumno que cursa
                Alumno a=ad.buscarAlumno(resultSet.getInt("idAlumno"));
                cursada.setAlumno(a);
                
	 	     //Recupero la materia que cursa
                Materia m=md.buscarMateria(resultSet.getInt("idMateria"));
                cursada.setMateria(m);

                cursada.setNota(resultSet.getInt("nota"));
               
                cursadas.add(cursada);
            }      
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al obtener las cursadas:  " + ex.getMessage());
        }
        return cursadas;
    }

//Borrar una cursada de una materia para un alumno en particular.
    
public void borrarCursadaDeUnaMateriaDeunAlumno(int id_alumno,int    id_materia){
        try {
            String sql = "DELETE FROM cursada WHERE idAlumno =? and  idMateria =?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ps.setInt(2, id_materia);
           
            ps.executeUpdate();
            
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar una cursada " + ex.getMessage());
        }
    }


//Actualizar una nota para un alumno en particular.

    public void actualizarNotaCursada(int id_alumno,int id_materia, int nota){
        try {
            String sql = "UPDATE cursada SET nota = ? WHERE idAlumno =? and idMateria =?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,nota);
            ps.setInt(2, id_alumno);
            ps.setInt(3, id_materia);
 
            ps.executeUpdate();

            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la nota a un alumno: " + ex.getMessage());
        }
    }

      public List<Materia> obtenerMateriasCursadas(int id){
      List<Materia> materias = new ArrayList<Materia>();
  
try {
String sql = "SELECT cursada.idMateria, nombre FROM cursada, materia WHERE cursada.idMateria = materia.idMateria\n" +
"and cursada.idAlumno = ?;";
PreparedStatement statement = con.prepareStatement(sql);
statement.setInt(1, id);
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
System.out.println("Error al obtener las materias cursadas: " + ex.getMessage());
}


return materias;

}

public List<Materia> obtenerMateriasNOCursadas(int id){
List<Materia> materias = new ArrayList<Materia>();

try {
String sql = "Select * from materia where idMateria not in "
+ "(select idMateria from cursada where idAlumno =?);";
PreparedStatement statement = con.prepareStatement(sql);
statement.setInt(1, id);
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
System.out.println("Error al obtener materias no cursadas: " + ex.getMessage());
}


return materias;

}
public List<Cursada> obtenerCursadasXAlumno(int id){
List<Cursada> cursadas = new ArrayList<Cursada>();

try {
String sql = "SELECT * FROM cursada WHERE idAlumno = ?;";
PreparedStatement statement = con.prepareStatement(sql);
statement.setInt(1,id);
ResultSet resultSet = statement.executeQuery();
Cursada cursada;
while(resultSet.next()){
cursada = new Cursada();
cursada.setId(resultSet.getInt("idCursada"));

Alumno a=buscarAlumno(resultSet.getInt("idAlumno"));
cursada.setAlumno(a);

Materia m=buscarMateria(resultSet.getInt("idMateria"));
cursada.setMateria(m);
cursada.setNota(resultSet.getInt("nota"));

cursadas.add(cursada);
} 
statement.close();
} catch (SQLException ex) {
System.out.println("Error al obtener cursadas por alumno: " + ex.getMessage());
}


return cursadas;
}
public Alumno buscarAlumno(int id){

AlumnoData ad=new AlumnoData(conexion);

return ad.buscarAlumno(id);

}

public Materia buscarMateria(int id){

MateriaData md=new MateriaData(conexion);
return md.buscarMateria(id);

}
}
