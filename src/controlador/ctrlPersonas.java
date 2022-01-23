package controlador;

import modelo.Persona;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import vista.frmPersonas;
import javax.swing.JOptionPane;

public class ctrlPersonas implements ActionListener {

    frmPersonas frmPersonas = new frmPersonas();
    List<Persona> personas = new ArrayList<>();
    DefaultTableModel tabla = (DefaultTableModel) frmPersonas.tablaPersonas.getModel();
    ctrlPrincipal ctrlPrincipal;

    public void Persona(List<Persona> personas, ctrlPrincipal anterior) {
        frmPersonas.setVisible(true);
        frmPersonas.setLocationRelativeTo(null);
        frmPersonas.setTitle("Registrar personas");

        frmPersonas.btnRegistrar2.addActionListener(this);
        frmPersonas.btnLimpiar2.addActionListener(this);
        frmPersonas.btnRegresar2.addActionListener(this);
        //System.out.println(personas.toString());
        
        this.personas = personas;
        mostrarPersonas();
        this.ctrlPrincipal = anterior;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (frmPersonas.btnRegistrar2 == e.getSource()) {
            String errors = "";
            int edad = 0, identificacion = 0;
            //PRIMERO VALIDAMOS TODOS LOS CAMPOS
            //VALIDANDO NOMBRES
            if (frmPersonas.txtNombres.getText().trim().length() == 0) {
                errors += "\n\t*El campo 'Nombres' está vacío";
            } else if (frmPersonas.txtNombres.getText().trim().length() < 5) {
                errors += "\n\t*El campo 'Nombres' debe tener al menos 5 caracteres";
            }

            //VALIDANDO APELLIDOS
            if (frmPersonas.txtApellidos.getText().trim().length() == 0) {
                errors += "\n\t*El campo 'Apellidos' está vacío";
            } else if (frmPersonas.txtApellidos.getText().trim().length() < 5) {
                errors += "\n\t*El campo 'Apellidos' debe tener al menos 5 caracteres";
            }

            //VALIDANDO EDAD
            if (frmPersonas.txtEdad.getText().trim().length() == 0) {
                errors += "\n\t*El campo 'Edad' está vacío";
            } else {
                try {
                    edad = Integer.parseInt(frmPersonas.txtEdad.getText());
                    if (edad > 120) {
                        errors += "\n\t*El campo 'Edad' debe tener un valor máximo de 120 años";
                    }
                } catch (Exception err) {
                    errors += "\n\t*El campo 'Edad' debe contener solo números";
                }
            }

            //VALIDANDO IDENTIFICACION
            if (frmPersonas.txtIdentificacion.getText().trim().length() == 0) {
                errors += "\n\t*El campo 'Identificación' está vacío";
            } else if (frmPersonas.txtIdentificacion.getText().trim().length() > 10) {
                errors += "\n\t*El campo 'Identificación' debe tener como máximo 10 dígitos";
            } else {
                try {
                    identificacion = Integer.parseInt(frmPersonas.txtIdentificacion.getText());
                } catch (Exception err) {
                    errors += "\n\t*El campo 'Identificación' debe contener solo números";
                }
            }
            
            //SI NO HALLAMOS ERRORES, PROCEDEMOS A CREAR A LA PERSONA
            if (errors.length() == 0) {
                Persona persona = new Persona();
                persona.setNombres(frmPersonas.txtNombres.getText());
                persona.setApellidos(frmPersonas.txtApellidos.getText());
                persona.setIdentificacion(identificacion);
                persona.setEdad(edad);
                personas.add(persona);
                //Luego de crear la persona, limpiamos los campos
                limpiarCampos();
                mostrarPersonas();
            } else {
                JOptionPane.showMessageDialog(frmPersonas, "Se han encontrado los siguientes errores:" + errors);
            }
        }

        else if (frmPersonas.btnLimpiar2 == e.getSource()) {
            limpiarCampos();
        }

        else if (frmPersonas.btnRegresar2 == e.getSource()) {
            frmPersonas.setVisible(false);
            //ctrlPrincipal ctrlPrincipal = new ctrlPrincipal();
            ctrlPrincipal.setPersonas(personas);
            ctrlPrincipal.reanudar();
        }
    }

    public void mostrarPersonas() {

        String matriz[][] = new String[personas.size()][4];

        for (int i = 0; i < personas.size(); i++) {
            matriz[i][0] = personas.get(i).getNombres();
            matriz[i][1] = personas.get(i).getApellidos();
            matriz[i][2] = String.valueOf(personas.get(i).getIdentificacion());
            matriz[i][3] = String.valueOf(personas.get(i).getEdad());
        }

        frmPersonas.tablaPersonas.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                new String[]{
                    "Nombres", "Apellidos", "Identificación", "Edad"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
    }

    public void limpiarCampos() {
        frmPersonas.txtNombres.setText("");
        frmPersonas.txtApellidos.setText("");
        frmPersonas.txtIdentificacion.setText("");
        frmPersonas.txtEdad.setText("");
    }
}
