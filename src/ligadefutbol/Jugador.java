/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ligadefutbol;

import java.sql.Blob;

/**
 *
 * @author DAW
 */
public class Jugador {

    private int id_jugador;
    private String nombreApellidos;
    private String nombreCamiseta;
    private int numeroCamisteta;
    private int edad;
    private int id_equipo;
    private int id_posicion;
    private Blob fotoJugador;
    
    public static final int TAM_NOMBRE = 30;
    public static final int TAM_CAMISETA = 20;
    public static final int TAM_NUMERO = 5;
    public static final int TAM_EDAD = 5;


    public Jugador() {
    }

    public Jugador(int id_jugador, String nombreApellidos, String nombreCamiseta, int numeroCamisteta, int edad, int equipo, int posicion, Blob fotoJugador) {
        this.id_jugador = id_jugador;
        this.nombreApellidos = nombreApellidos;
        this.nombreCamiseta = nombreCamiseta;
        this.numeroCamisteta = numeroCamisteta;
        this.edad = edad;
        this.id_equipo = equipo;
        this.id_posicion = posicion;
        this.fotoJugador = fotoJugador;
    }

    public Blob getFotoJugador() {
        return fotoJugador;
    }

    public void setFotoJugador(Blob fotoJugador) {
        this.fotoJugador = fotoJugador;
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public String getNombreCamiseta() {
        return nombreCamiseta;
    }

    public void setNombreCamiseta(String nombreCamiseta) {
        this.nombreCamiseta = nombreCamiseta;
    }
    
    public int getNumeroCamisteta() {
        return numeroCamisteta;
    }

    public void setNumeroCamisteta(int numeroCamisteta) {
        this.numeroCamisteta = numeroCamisteta;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int equipo) {
        this.id_equipo = equipo;
    }

    public int getId_posicion() {
        return id_posicion;
    }

    public void setId_posicion(int posicion) {
        this.id_posicion = posicion;
    }
}
