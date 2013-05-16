/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ligadefutbol;

import java.awt.Frame;
import java.sql.Blob;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Alejandro
 */
public class ListaJugadoresPanel extends javax.swing.JPanel {

    private DefaultTableModel modeloTabla;
    GestionJugador gestion = new GestionJugador();
    Blob foto;

    public ListaJugadoresPanel() {
        Conexion.conectar("localhost", "root", "");
        initComponents();
        if (!java.beans.Beans.isDesignTime()) {
            this.CargarDatosJTable();
        }
    }

    void CargarDatosJTable() {

        ArrayList<Jugador> listaJugadores = new ArrayList();

        modeloTabla = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        jTable2.setModel(modeloTabla);

        //Rellenar las cabeceras de las columnas
        String[] cabecera = {"Id_jugador", "Nombre Completo", "Equipo"};
        modeloTabla.setColumnIdentifiers(cabecera);

        listaJugadores = gestion.list();
        //Recorrer la lista de contactos para añadir algunos datos en el JTable
        for (Jugador jugador : listaJugadores) {
            //Se va mostrar sólo el nombre y los apellidos
            String[] datosFilaJugador = {
                String.valueOf(jugador.getId_jugador()),
                jugador.getNombreApellidos(),
                //                jugador.getNombreCamiseta(),
                //                String.valueOf(jugador.getNumeroCamisteta()),
                //                String.valueOf(jugador.getEdad()),
                String.valueOf(jugador.getId_equipo()), //                jugador.getPosicion()
            };
            modeloTabla.addRow(datosFilaJugador);
        }

        //Establecer que sólo se pueda seleccionar una fila
        jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Ocultar columna de idContacto
        TableColumn tc = jTable2.getColumn("Id_jugador");
        jTable2.removeColumn(tc);

        //Mostrar vacíos los datos de contacto
        mostrarDatosJugador();
    }

    void mostrarDatosJugador() {
        //Obtener número de fila seleccionada en el JTable
        int numFilaSelec = jTable2.getSelectedRow();
        //Comprobar que el usuario ha seleccionado alguna fila

        if (numFilaSelec != -1) {

            //Obtener el contacto correspondiente a la fila seleccionada
            Jugador jugador = gestion.get(
                    Integer.valueOf((String) modeloTabla.getValueAt(numFilaSelec, 0)));
            jLabelNombre.setText(jugador.getNombreApellidos());
            jLabelCamiseta.setText(jugador.getNombreCamiseta());
            jLabelNumero.setText(String.valueOf(jugador.getNumeroCamisteta()));
            jLabelEdad.setText(String.valueOf(jugador.getEdad()));
            jLabelEquipo.setText(String.valueOf(jugador.getId_equipo()));
            jLabelPosicion.setText(String.valueOf(jugador.getId_posicion()));

        } else {
            jLabelNombre.setText("");
            jLabelCamiseta.setText("");
            jLabelNumero.setText("");
            jLabelEdad.setText("");
            jLabelEquipo.setText("");
            jLabelPosicion.setText("");
        }
    }

    void insertar() {
        JugadorDialog dialogoJugador = new JugadorDialog(Frame.getFrames()[0], true);
        //Crear un nuevo objeto jugador
        Jugador jugador = new Jugador();
        //Asignar el jugador obtenido a la ventana de diálogo
        dialogoJugador.setJugador(jugador);
        //Mostar la ventana con los campos de edición activados
        dialogoJugador.activarCampos(true);
        dialogoJugador.setVisible(true);
        //Liberar la memoria de pantalla ocupada por la ventana de detalle
        dialogoJugador.dispose();
        //Comprobar si se ha pulsado Aceptar o Cancelar 
        if (dialogoJugador.isAceptado()) {
            //Añadir el jugador en la BD
            gestion.Insert(jugador);
            //Actualiza los datos en la tabla de la ventana
            CargarDatosJTable();
        }
    }

    void editar() {
        //Obtener número de fila seleccionada en el JTable
        int numFilaSelec = jTable2.getSelectedRow();
        //Comprobar que el usuario ha seleccionado alguna fila
        if (numFilaSelec != -1) {
            JugadorDialog dialogoJugador = new JugadorDialog(Frame.getFrames()[0], true);
            //Obtener el jugador correspondiente a la fila seleccionada
            Jugador jugador = gestion.get(
                    Integer.valueOf((String) modeloTabla.getValueAt(numFilaSelec, 0)));
            //Asignar el jugador obtenido a la ventana de diálogo
            dialogoJugador.setJugador(jugador);
            //Mostar la ventana con los detalles del jugador desactivados
            //dialogoJugador.activarCampos(false);
            dialogoJugador.setVisible(true);
            //Liberar la memoria de pantalla ocupada por la ventana de detalle
            dialogoJugador.dispose();
            //Comprobar si se ha pulsado Aceptar o Cancelar 
            if (dialogoJugador.isAceptado()) {
                //Guardar el contacto en la BD
                gestion.update(jugador);
                //Actualiza los datos en la tabla de la ventana
                CargarDatosJTable();
            }
        } else {
            //Si no se ha seleccionado un contacto de la lista hay que notificarlo
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un jugador previamente",
                    "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }

    void suprimir() {
        //Obtener número de fila seleccionada en el JTable
        int numFilaSelec = jTable2.getSelectedRow();
        //Comprobar que el usuario ha seleccionado alguna fila
        if (numFilaSelec != -1) {
            //Obtener el jugador correspondiente a la fila seleccionada
            Jugador jugador = gestion.get(
                    Integer.valueOf((String) modeloTabla.getValueAt(numFilaSelec, 0)));
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Desea suprimir el contacto?\n"
                    + jugador.getNombreApellidos(),
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            //Comprobar si se ha pulsado Aceptar o Cancelar 
            if (respuesta == JOptionPane.YES_OPTION) {
                //Suprimir el jugador de la BD
                gestion.delete(jugador);
                //Actualiza los datos en la tabla de la ventana
                CargarDatosJTable();
                //Mostrar vacíos los datos del jugador
                mostrarDatosJugador();
            }
        } else {
            //Si no se ha seleccionado un jugador de la lista hay que notificarlo
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un contacto previamente",
                    "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelCamiseta = new javax.swing.JLabel();
        jLabelNumero = new javax.swing.JLabel();
        jLabelEdad = new javax.swing.JLabel();
        jLabelEquipo = new javax.swing.JLabel();
        jLabelPosicion = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable2KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del jugador"));

        jLabel1.setText("Nombre completo:");

        jLabel2.setText("Nombre camiseta:");

        jLabel3.setText("Numero camiseta:");

        jLabel4.setText("Edad:");

        jLabel5.setText("Equipo:");

        jLabel6.setText("Posicion:");

        jLabel7.setText("Foto:");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Foto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(jLabelCamiseta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelEdad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelEquipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelPosicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabelCamiseta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jButton1.setText("Insertar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Suprimir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(44, 44, 44)
                        .addComponent(jButton2)
                        .addGap(52, 52, 52)
                        .addComponent(jButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        this.mostrarDatosJugador();
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased
        this.mostrarDatosJugador();
    }//GEN-LAST:event_jTable2KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insertar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        editar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        suprimir();
    }//GEN-LAST:event_jButton3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelCamiseta;
    private javax.swing.JLabel jLabelEdad;
    private javax.swing.JLabel jLabelEquipo;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JLabel jLabelPosicion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
