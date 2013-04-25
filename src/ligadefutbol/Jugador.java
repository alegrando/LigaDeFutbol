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
    private String equipo;
    private String posicion;
    private Blob fotoJugador;

    public Jugador() {
    }

    public Jugador(int id_jugador, String nombreApellidos, String nombreCamiseta, int numeroCamisteta, int edad, String equipo, String posicion, Blob fotoJugador) {
        this.id_jugador = id_jugador;
        this.nombreApellidos = nombreApellidos;
        this.nombreCamiseta = nombreCamiseta;
        this.numeroCamisteta = numeroCamisteta;
        this.edad = edad;
        this.equipo = equipo;
        this.posicion = posicion;
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

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
