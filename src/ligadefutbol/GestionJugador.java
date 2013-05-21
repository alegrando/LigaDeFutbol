/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ligadefutbol;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DAW
 */
public class GestionJugador {

    public ArrayList<Jugador> list() {
        ArrayList<Jugador> jugadores = new ArrayList();
        try {

            String sql = "SELECT * FROM Jugadores";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id_jugador = rs.getInt("id_jugador");
                String nombreApellidos = rs.getString("nombreApellidos");
                String nombreCamiseta = rs.getString("nombreCamiseta");
                int numeroCamiseta = rs.getInt("numeroCamiseta");
                int edad = rs.getInt("edad");
                int id_equipo = rs.getInt("id_equipo");
                int id_posicion = rs.getInt("id_posicion");
                Blob fotoJugador = rs.getBlob("fotoJugador");
                Jugador jugador = new Jugador(id_jugador, nombreApellidos, nombreCamiseta, numeroCamiseta, edad, id_equipo, id_posicion, fotoJugador);
                jugadores.add(jugador);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos aaaaaa");
            ex.printStackTrace();
        }
        return jugadores;
    }

    public boolean Insert(Jugador jugador) {

        int id_jugador = jugador.getId_jugador();
        String nombreApellidos = jugador.getNombreApellidos();
        String nombreCamiseta = jugador.getNombreCamiseta();
        int numeroCamiseta = jugador.getNumeroCamisteta();
        int edad = jugador.getEdad();
        int id_equipo = jugador.getId_equipo();
        int id_posicion = jugador.getId_posicion();
        Blob fotoJugador = jugador.getFotoJugador();

        String sql = "INSERT INTO jugadores (nombreApellidos,nombreCamiseta,numeroCamiseta,edad,id_equipo,id_posicion,fotoJugador) VALUES "
                + "('" + nombreApellidos + "','" + nombreCamiseta + "','" + numeroCamiseta + "','" + edad + "','" + id_equipo + "','" + id_posicion + "', ? )";

        try {
            PreparedStatement pstmt = Conexion.conexion.prepareStatement(sql);
            pstmt.setBlob(1, fotoJugador);
            pstmt.execute();

        } catch (Exception e) {
            System.out.print("Error en la sentencia sql: ");
            System.out.print(sql);
            e.printStackTrace();
            return false;
        }
        return true;
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
        String sql = "";
        try {


            sql = "Update jugadores set nombreApellidos = '" + jugador.getNombreApellidos()
                    + "' , nombreCamiseta = '" + jugador.getNombreCamiseta()
                    + "' , numeroCamiseta = " + jugador.getNumeroCamisteta()
                    + " , edad = " + jugador.getEdad()
                    + " , id_equipo = '" + jugador.getId_equipo()
                    + "' , id_posicion = '" + jugador.getId_posicion()
                    + "' , fotoJugador = ? where id_jugador = " + jugador.getId_jugador();
            PreparedStatement pstmt = Conexion.conexion.prepareStatement(sql);
            pstmt.setBlob(1, jugador.getFotoJugador());
            pstmt.execute();

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
                int id_jugadors = rs.getInt("id_jugador");
                String nombreApellidos = rs.getString("nombreApellidos");
                String nombreCamiseta = rs.getString("nombreCamiseta");
                int numeroCamiseta = rs.getInt("numeroCamiseta");
                int edad = rs.getInt("edad");
                int id_equipo = rs.getInt("id_equipo");
                int id_posicion = rs.getInt("id_posicion");
                Blob fotoJugador = rs.getBlob("fotoJugador");
                jugador = new Jugador(id_jugadors, nombreApellidos, nombreCamiseta, numeroCamiseta, edad, id_equipo, id_posicion, fotoJugador);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
        }
        return jugador;
    }

    public Equipo getEquipo(int id_equipo) {
        Equipo equipo = null;
        try {

            String sql = "SELECT * FROM equipos where id_equipo =" + id_equipo;
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int id_equipos = rs.getInt("id_equipo");
                String equipos = rs.getString("equipo");
                equipo = new Equipo(id_equipos, equipos);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
        }
        return equipo;
    }

    public ArrayList<Equipo> listEquipos() {
        ArrayList<Equipo> equipos = new ArrayList();
        try {
            String sql = "Select * from equipos";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Equipo equipoS = new Equipo(rs.getInt("id_equipo"), rs.getString("equipo"));
                equipos.add(equipoS);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
        }
        return equipos;
    }

    public ArrayList<Posicion> listPosicion() {
        ArrayList<Posicion> posiciones = new ArrayList();
        try {
            String sql = "Select * from posiciones";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Posicion posicionS = new Posicion(rs.getInt("id_posicion"), rs.getString("posicion"));
                posiciones.add(posicionS);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
        }
        return posiciones;
    }
}
