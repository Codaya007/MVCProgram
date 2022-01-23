package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Herramienta;
import vista.frmHerramientas;

public class ctrlHerramientas implements ActionListener {

    frmHerramientas frmHerramientas = new frmHerramientas();
    List<Herramienta> herramientas = new ArrayList<>();
    DefaultTableModel tabla = (DefaultTableModel) frmHerramientas.tablaHerramientas.getModel();
    ctrlPrincipal ctrlPrincipal;
    
    public void Herramienta(List<Herramienta> herramientas, ctrlPrincipal anterior) {
        frmHerramientas.setLocationRelativeTo(null);
        frmHerramientas.setVisible(true);
        frmHerramientas.setTitle("Registrar herramientas");

        frmHerramientas.btnRegistrar.addActionListener(this);
        frmHerramientas.btnLimpiar.addActionListener(this);
        frmHerramientas.btnRegresar.addActionListener(this);
        
        this.herramientas = herramientas;
        mostrarHerramientas();
        this.ctrlPrincipal = anterior;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (frmHerramientas.btnRegistrar == e.getSource()) {
            String errors = "";
            double costo = 0.0;
            
            //PRIMERO VALIDAMOS TODOS LOS CAMPOS
            //VALIDANDO HERRAMIENTA (NOMBRE)
            if (frmHerramientas.txtHerramienta.getText().trim().length() == 0) {
                errors += "\n\t*El campo 'Herramienta' está vacío";
            }

            //VALIDANDO DESCRIPCION
            if (frmHerramientas.txtDescripcion.getText().trim().length() == 0) {
                errors += "\n\t*El campo 'Descripción' está vacío";
            } else if (frmHerramientas.txtDescripcion.getText().trim().length() < 20) {
                errors += "\n\t*El campo 'Descripción' debe tener al menos 20 caracteres";
            }
            
            //VALIDANDO FUNCION
            if (frmHerramientas.txtFuncion.getText().trim().length() == 0) {
                errors += "\n\t*El campo 'Función' está vacío";
            } else if (frmHerramientas.txtFuncion.getText().trim().length() < 5) {
                errors += "\n\t*El campo 'Función' debe tener al menos 5 caracteres";
            }
            
            //VALIDANDO COSTO
            if (frmHerramientas.txtCosto.getText().trim().length() == 0) {
                errors += "\n\t*El campo 'Costo' está vacío";
            }else {
                try {
                    costo = Double.parseDouble(frmHerramientas.txtCosto.getText());
                    //System.out.println(costo);
                } catch (Exception err) {
                    errors += "\n\t*El campo 'Costo' solo puede contener números y punto";
                }
            }

            if (errors.length() == 0) {
                Herramienta nuevaHerramienta = new Herramienta();
                nuevaHerramienta.setHerramienta(frmHerramientas.txtHerramienta.getText());
                nuevaHerramienta.setDescripcion(frmHerramientas.txtDescripcion.getText());
                nuevaHerramienta.setFuncion(frmHerramientas.txtFuncion.getText());
                nuevaHerramienta.setCosto(costo);
                //añadimos la nueva herramienta al arraylist
                herramientas.add(nuevaHerramienta);
                //Luego de crear el trabajo, limpiamos los campos
                limpiarCampos();
                //y actualizamos la tabla
                mostrarHerramientas();
            } else {
                JOptionPane.showMessageDialog(frmHerramientas, "Se han encontrado los siguientes errores:" + errors);
            }
        } else if (frmHerramientas.btnLimpiar == e.getSource()) {
            limpiarCampos();
        } else if (frmHerramientas.btnRegresar == e.getSource()) {
            frmHerramientas.setVisible(false);
            //ctrlPrincipal ctrlPrincipal = new ctrlPrincipal();
            ctrlPrincipal.reanudar();
            ctrlPrincipal.setHerramientas(herramientas);
        }
    }

    public void limpiarCampos() {
        frmHerramientas.txtHerramienta.setText("");
        frmHerramientas.txtFuncion.setText("");
        frmHerramientas.txtDescripcion.setText("");
        frmHerramientas.txtCosto.setText("");
    }
    
    public void mostrarHerramientas(){
        String matriz[][] = new String[herramientas.size()][4];

        for (int i = 0; i < herramientas.size(); i++) {
            matriz[i][0] = herramientas.get(i).getHerramienta();
            matriz[i][1] = herramientas.get(i).getFuncion();
            matriz[i][2] = herramientas.get(i).getDescripcion();
            matriz[i][3] = Double.toString(herramientas.get(i).getCosto()); //String.valueOf(herramientas.get(i).getCosto());
        }
        
        frmHerramientas.tablaHerramientas.setModel(new javax.swing.table.DefaultTableModel(
            matriz,
            new String [] {
                "Herramienta", "Función", "Descripción", "Costo"
            }
        ));
    }
}
