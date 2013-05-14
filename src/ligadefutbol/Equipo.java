/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ligadefutbol;

/**
 *
 * @author DAW
 */
public class Equipo {
    private int id_equipo;
    private String equipo;
    
    public Equipo(){
        
    }
    
    public Equipo(int id_equipo, String equipo){
        this.id_equipo = id_equipo;
        this.equipo = equipo;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
    
    public String toString(){
        return equipo;
    }
}
