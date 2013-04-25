/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ligadefutbol;

import java.util.ArrayList;

/**
 *
 * @author DAW
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Conexion.conectar("localhost","root","");
        GestionJugador gestionJugador = new GestionJugador();
        ArrayList<Jugador> pruebaJugador = new ArrayList();
        pruebaJugador = gestionJugador.list();
        
        for(int i=0; i<gestionJugador.list().size(); i++){
        System.out.println(pruebaJugador.get(i).getId_jugador()+","+pruebaJugador.get(i).getNombreApellidos()+","+pruebaJugador.get(i).getNombreCamiseta()+","+pruebaJugador.get(i).getNumeroCamisteta()+","+pruebaJugador.get(i).getEdad()+","+pruebaJugador.get(i).getEquipo()+","+pruebaJugador.get(i).getPosicion()+","+pruebaJugador.get(i).getFotoJugador());
        }
    }
}
