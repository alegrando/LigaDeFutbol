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
       Jugador jug2 = new Jugador(0, "Antonio", "Castro", 12, 27, "Real Betis", "Delantero", null );
        gestionJugador.delete(jug2);
//        pruebaJugador = gestionJugador.list();
//        
//        for(int i=0; i<gestionJugador.list().size(); i++){
//        System.out.println(pruebaJugador.get(i).getId_jugador()+","+pruebaJugador.get(i).getNombreApellidos()+","+pruebaJugador.get(i).getNombreCamiseta()+","+pruebaJugador.get(i).getNumeroCamisteta()+","+pruebaJugador.get(i).getEdad()+","+pruebaJugador.get(i).getEquipo()+","+pruebaJugador.get(i).getPosicion()+","+pruebaJugador.get(i).getFotoJugador());
//        }
//        Jugador jug = new Jugador(0, "Ruben Castro", "Castro", 12, 27, "Real Betis", "Delantero", null );
//        gestionJugador.Insert(jug);
//        gestionJugador.delete(jug);
//        System.out.print(jug.getId_jugador());
        
    }
}
