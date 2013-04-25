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
                String equipo = rs.getString("equipo");
                String posicion = rs.getString("posicion");
                Blob fotoJugador = rs.getBlob("fotoJugador");
                Jugador jugador = new Jugador(id_jugador, nombreApellidos, nombreCamiseta, numeroCamiseta, edad, equipo, posicion, fotoJugador);
                jugadores.add(jugador);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
        }
        return jugadores;
    }
}
