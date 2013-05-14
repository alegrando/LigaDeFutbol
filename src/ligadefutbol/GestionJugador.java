/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ligadefutbol;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DAW
 */
public class GestionJugador {

    private int id_jugador;
    private String nombreApellidos;
    private String nombreCamiseta;
    private int numeroCamiseta;
    private int edad;
    private String equipo;
    private String posicion;
    private Blob fotoJugador;
    int autoincrementoID;

    public ArrayList<Jugador> list() {
        ArrayList<Jugador> jugadores = new ArrayList();
        try {

            String sql = "SELECT * FROM Jugadores";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                id_jugador = rs.getInt("id_jugador");
                nombreApellidos = rs.getString("nombreApellidos");
                nombreCamiseta = rs.getString("nombreCamiseta");
                numeroCamiseta = rs.getInt("numeroCamiseta");
                edad = rs.getInt("edad");
                equipo = rs.getString("equipo");
                posicion = rs.getString("posicion");
                fotoJugador = rs.getBlob("fotoJugador");
                Jugador jugador = new Jugador(id_jugador, nombreApellidos, nombreCamiseta, numeroCamiseta, edad, equipo, posicion, fotoJugador);
                jugadores.add(jugador);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
        }
        return jugadores;
    }

    int Insert(Jugador jugador) {

        id_jugador = jugador.getId_jugador();
        nombreApellidos = jugador.getNombreApellidos();
        nombreCamiseta = jugador.getNombreCamiseta();
        numeroCamiseta = jugador.getNumeroCamisteta();
        edad = jugador.getEdad();
        equipo = jugador.getEquipo();
        posicion = jugador.getPosicion();
        fotoJugador = jugador.getFotoJugador();

        String sql = "INSERT INTO jugadores (nombreApellidos,nombreCamiseta,numeroCamiseta,edad,equipo,posicion,fotoJugador) VALUES "
                + "('" + nombreApellidos + "','" + nombreCamiseta + "','" + numeroCamiseta + "','" + edad + "','" + equipo + "','" + posicion +"','" + fotoJugador + "')";

        try {
            Statement sentenciaSQL = Conexion.conexion.createStatement();
            sentenciaSQL.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = sentenciaSQL.getGeneratedKeys();

            if (rs.next()) {
                autoincrementoID = rs.getInt(1);
            } else {
                System.out.print("Error al sacar el Id");
            }

        } catch (Exception e) {
            System.out.print("Error en la sentencia sql: ");
            System.out.print(sql);
            e.printStackTrace();

        }
        return autoincrementoID;

    }
    public boolean delete(Jugador jugador) {

        try {

            Statement stmt = Conexion.conexion.createStatement();
            String sql = "Delete from jugadores where id_jugador =" + jugador.getId_jugador();
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *
     * @param alumno
     * @return
     */
    public boolean update(Jugador jugador) {
        String sql="";
        try {

            Statement stmt = Conexion.conexion.createStatement();
            sql = "Update jugadores set nombreApellidos = '" + jugador.getNombreApellidos() + 
                    "' , nombreCamiseta = '" + jugador.getNombreCamiseta()+ 
                    "' , numeroCamiseta = " + jugador.getNumeroCamisteta()+ 
                    " , edad = " + jugador.getEdad()+ 
                    " , equipo = '" + jugador.getEquipo()+ 
                    "' , posicion = '" + jugador.getPosicion()+ 
                    "' , fotoJugador = " + jugador.getFotoJugador()+ 
                    " where id_jugador = " + jugador.getId_jugador();
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Error al actualizar la base de datos");
            ex.printStackTrace();
            System.out.print(sql);
            return false;
        }
        return true;
    }
    public Jugador get(int id_jugador) {
        Jugador jugador = null;
        try {

            String sql = "SELECT * FROM jugadores where id_jugador =" + id_jugador;
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                id_jugador = rs.getInt("id_jugador");
                nombreApellidos = rs.getString("nombreApellidos");
                nombreCamiseta = rs.getString("nombreCamiseta");
                numeroCamiseta = rs.getInt("numeroCamiseta");
                edad = rs.getInt("edad");
                equipo = rs.getString("equipo");
                posicion = rs.getString("posicion");
                fotoJugador = rs.getBlob("fotoJugador");
                jugador = new Jugador(id_jugador, nombreApellidos, nombreCamiseta, numeroCamiseta, edad, equipo, posicion, fotoJugador);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
        }
        return jugador;
    }
    
    public ArrayList<Equipo> findEquipos() {
        ArrayList<Equipo> equipos = new ArrayList();
        try {
            String sql = "Select * from equipos group by equipo";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Equipo equipoS = new Equipo(rs.getInt("id_equipo"), rs.getString("equipo"));
                //String equipoS = rs.getString("equipo");
                equipos.add(equipoS);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
        }
        return equipos;
    }
    
    public ArrayList<Posicion> findPosicion() {
        ArrayList<Posicion> posiciones = new ArrayList();
        try {
            String sql = "Select * from posiciones group by posicion";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Posicion posicionS = new Posicion(rs.getInt("id_posicion"), rs.getString("posicion"));
                //String posicionS = rs.getString("posicion");
                posiciones.add(posicionS);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
        }
        return posiciones;
    }
}
