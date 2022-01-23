package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Trabajo;
import vista.frmTrabajos;

public class ctrlTrabajos implements ActionListener {

    frmTrabajos frmTrabajos = new frmTrabajos();
    List<Trabajo> trabajos = new ArrayList<>();
    DefaultTableModel tabla = (DefaultTableModel) frmTrabajos.tablaTrabajos.getModel();
    ctrlPrincipal ctrlPrincipal;

    public void Trabajo( List<Trabajo> trabajos, ctrlPrincipal anterior) {
        frmTrabajos.setLocationRelativeTo(null);
        frmTrabajos.setVisible(true);
        frmTrabajos.setTitle("Registrar trabajos");

        frmTrabajos.btnRegistrar.addActionListener(this);
        frmTrabajos.btnLimpiar.addActionListener(this);
        frmTrabajos.btnRegresar.addActionListener(this);
        
        this.trabajos = trabajos;
        mostrarTrabajos();
        
        this.ctrlPrincipal = anterior;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (frmTrabajos.btnRegistrar == e.getSource()) {
            String errors = "";
            //PRIMERO VALIDAMOS TODOS LOS CAMPOS
            //VALIDANDO TRABAJO
            if (frmTrabajos.txtTrabajo.getText().trim().length() == 0) {
                errors += "\n\t*El campo 'Trabajo' está vacío";
            } else if (frmTrabajos.txtTrabajo.getText().trim().length() < 5) {
                errors += "\n\t*El campo 'Trabajo' debe tener al menos 5 caracteres";
            }

            //VALIDANDO DESCRIPCIón
            if (frmTrabajos.txtDescripcion.getText().trim().length() == 0) {
                errors += "\n\t*El campo 'Descripción' está vacío";
            } else if (frmTrabajos.txtDescripcion.getText().trim().length() < 20) {
                errors += "\n\t*El campo 'Descripción' debe tener al menos 20 caracteres";
            }

            if (errors.length() == 0) {
                Trabajo nuevoTrabajo = new Trabajo();
                nuevoTrabajo.setTrabajo(frmTrabajos.txtTrabajo.getText());
                nuevoTrabajo.setDescripcion(frmTrabajos.txtDescripcion.getText());
                trabajos.add(nuevoTrabajo);
                //System.out.println(trabajos.toString());
                //Luego de crear el trabajo, limpiamos los campos
                limpiarCampos();
                //y actualizamos la tabla
                mostrarTrabajos();
            } else {
                JOptionPane.showMessageDialog(frmTrabajos, "Se han encontrado los siguientes errores:" + errors);
            }

        } else if (frmTrabajos.btnLimpiar == e.getSource()) {
            limpiarCampos();
        } else if (frmTrabajos.btnRegresar == e.getSource()) {
            frmTrabajos.setVisible(false);
            //ctrlPrincipal ctrlPrincipal = new ctrlPrincipal();
            ctrlPrincipal.reanudar();
            ctrlPrincipal.setTrabajos(trabajos);
        }
    }

    public void mostrarTrabajos() {

        String matriz[][] = new String[trabajos.size()][2];

        for (int i = 0; i < trabajos.size(); i++) {
            matriz[i][0] = trabajos.get(i).getTrabajo();
            matriz[i][1] = trabajos.get(i).getDescripcion();
        }

        frmTrabajos.tablaTrabajos.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                new String[]{
                    "Trabajo", "Descripción"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
    }

    public void limpiarCampos() {
        frmTrabajos.txtTrabajo.setText("");
        frmTrabajos.txtDescripcion.setText("");
    }
}
