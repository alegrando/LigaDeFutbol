/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ligadefutbol;

/**
 *
 * @author DAW
 */
public class Posicion {
    private int id_posicion;
    private String posicion;
    
    public Posicion(){
        
    }
    
    public Posicion(int id_posicion, String posicion){
        this.id_posicion = id_posicion;
        this.posicion = posicion;
    }

    public int getId_posicion() {
        return id_posicion;
    }

    public void setId_posicion(int id_posicion) {
        this.id_posicion = id_posicion;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    
    public String toString(){
        return posicion;
    }
    
}
