package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Asociacion;
import modelo.Herramienta;
import modelo.Persona;
import modelo.Trabajo;
import vista.frmAsociarHerramientas;

//PDF
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//PARA GENERAR EL ID DEL PDF
import java.text.SimpleDateFormat;
import java.util.Date;

public class ctrlAsociarHerramientas implements ActionListener {

    frmAsociarHerramientas frmAsociarHerramientas = new frmAsociarHerramientas();
    List<Herramienta> herramientas = new ArrayList<>();
    List<Trabajo> trabajos = new ArrayList<>();
    List<Persona> personas = new ArrayList<>();
    List<Asociacion> asociaciones = new ArrayList<>();
    ctrlPrincipal ctrlPrincipal;

    public void Asociar(List<Persona> personas, List<Trabajo> trabajos, List<Herramienta> herramientas, List<Asociacion> asociaciones, ctrlPrincipal anterior) {
        frmAsociarHerramientas.setLocationRelativeTo(null);
        frmAsociarHerramientas.setVisible(true);
        frmAsociarHerramientas.setTitle("Asociar herramientas");

        frmAsociarHerramientas.btnRegistrar.addActionListener(this);
        frmAsociarHerramientas.btnLimpiar.addActionListener(this);
        frmAsociarHerramientas.btnImprimir.addActionListener(this);
        frmAsociarHerramientas.btnRegresar.addActionListener(this);

        this.asociaciones = asociaciones;
        this.personas = personas;
        this.herramientas = herramientas;
        this.trabajos = trabajos;
        this.ctrlPrincipal = anterior;

        mostrarItemsPersonas();
        mostrarItemsHerramientas();
        mostrarItemsTrabajos();
        mostrarAsociaciones();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (frmAsociarHerramientas.btnRegistrar == e.getSource()) {
            String errors = "";
            Persona personaSeleccionada = new Persona();
            Herramienta herramientaSeleccionada = new Herramienta();
            Trabajo trabajoSeleccionado = new Trabajo();

            //PRIMERO VALIDAMOS LOS CAMPOS
            //VALIDANDO PERSONA
            if (frmAsociarHerramientas.cbxPersona.getSelectedIndex() == 0) {
                errors += "\n\t*La Persona seleccionada no es válida";
            } else if (frmAsociarHerramientas.cbxPersona.getSelectedItem() == "") {
                errors += "\n\t*La Persona seleccionada no es válida";
            } else {
                personaSeleccionada = personas.get(frmAsociarHerramientas.cbxPersona.getSelectedIndex() - 1);
            }

            //VALIDANDO HERRAMIENTA
            if (frmAsociarHerramientas.cbxHerramienta.getSelectedIndex() == 0) {
                errors += "\n\t*La Herramienta seleccionada no es válida";
            } else if (frmAsociarHerramientas.cbxHerramienta.getSelectedItem() == "") {
                errors += "\n\t*La Herramienta seleccionada no es válida";
            } else {
                herramientaSeleccionada = herramientas.get(frmAsociarHerramientas.cbxHerramienta.getSelectedIndex() - 1);
            }

            //VALIDANDO TRABAJO
            if (frmAsociarHerramientas.cbxTrabajo.getSelectedIndex() == 0) {
                errors += "\n\t*El trabajo seleccionado no es válido";
            } else if (frmAsociarHerramientas.cbxTrabajo.getSelectedItem() == "") {
                errors += "\n\t*El trabajo seleccionado no es válido";
            } else {
                trabajoSeleccionado = trabajos.get(frmAsociarHerramientas.cbxTrabajo.getSelectedIndex() - 1);
            }

            //SI NO HAY ERRORES, CREAMOS LA ASOCIACIÓN
            if (errors.length() == 0) {
                Asociacion nuevaAsociacion = new Asociacion(trabajoSeleccionado, herramientaSeleccionada, personaSeleccionada);
                asociaciones.add(nuevaAsociacion);
                //Luego de crear la persona, limpiamos los campos
                limpiarCampos();
                mostrarAsociaciones();
            } else {
                JOptionPane.showMessageDialog(frmAsociarHerramientas, "Se han encontrado los siguientes errores:" + errors);
            }

        } else if (frmAsociarHerramientas.btnLimpiar == e.getSource()) {
            limpiarCampos();
        } else if (frmAsociarHerramientas.btnRegresar == e.getSource()) {
            frmAsociarHerramientas.setVisible(false);
            //ctrlPrincipal ctrlPrincipal = new ctrlPrincipal();
            ctrlPrincipal.setAsociaciones(asociaciones);
            ctrlPrincipal.reanudar();
        } else if (frmAsociarHerramientas.btnImprimir == e.getSource()) {
            if (asociaciones.size() > 0) {
                generarPdf();
            } else {
                JOptionPane.showMessageDialog(frmAsociarHerramientas, "Aún no ha realizado ningún asociación");
            }
        }

    }

    public void mostrarItemsPersonas() {
        String matriz[] = new String[personas.size() + 1];

        matriz[0] = "Seleccionar Persona";

        for (int i = 0; i < personas.size(); i++) {
            matriz[i + 1] = personas.get(i).getIdentificacion() + " - " + personas.get(i).getNombresCompletos();
        }

        frmAsociarHerramientas.cbxPersona.setModel(new javax.swing.DefaultComboBoxModel<>(matriz));
    }

    public void mostrarItemsHerramientas() {
        String matriz[] = new String[herramientas.size() + 1];

        matriz[0] = "Seleccionar Herramienta";

        for (int i = 0; i < herramientas.size(); i++) {
            matriz[i + 1] = herramientas.get(i).getHerramienta();
        }

        frmAsociarHerramientas.cbxHerramienta.setModel(new javax.swing.DefaultComboBoxModel<>(matriz));
    }

    public void mostrarItemsTrabajos() {
        String matriz[] = new String[trabajos.size() + 1];

        matriz[0] = "Seleccionar trabajo";

        for (int i = 0; i < trabajos.size(); i++) {
            matriz[i + 1] = trabajos.get(i).getTrabajo();
        }

        frmAsociarHerramientas.cbxTrabajo.setModel(new javax.swing.DefaultComboBoxModel<>(matriz));
    }

    public void mostrarAsociaciones() {

        String matriz[][] = new String[asociaciones.size()][3];

        for (int i = 0; i < asociaciones.size(); i++) {
            matriz[i][0] = asociaciones.get(i).getPersona().getNombresCompletos();
            matriz[i][1] = asociaciones.get(i).getHerramienta().getHerramienta();
            matriz[i][2] = asociaciones.get(i).getTrabajo().getTrabajo();
        }

        frmAsociarHerramientas.tablaAsociaciones.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                new String[]{
                    "Persona", "Herramienta", "Trabajo"
                }
        ));
    }

    public void limpiarCampos() {
        frmAsociarHerramientas.cbxPersona.setSelectedIndex(0);
        frmAsociarHerramientas.cbxHerramienta.setSelectedIndex(0);
        frmAsociarHerramientas.cbxTrabajo.setSelectedIndex(0);
    }

    private String generaId() {
        Date currentDate = new Date();

        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String dateTime = ft.format(currentDate);

        return dateTime;
    }

    public void generarPdf() {
        System.out.println("Estoy escribiendo el pdf...");
        String FILE_NAME = "files/Asociaciones" + generaId() + ".pdf";

        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));

            //open
            document.open();

            Font boldFont = new Font();
            boldFont.setStyle(Font.BOLD);
            boldFont.setSize(12);

            Paragraph title, subtitle, body;
            subtitle = new Paragraph();
            body = new Paragraph();

            for (int i = 0; i < asociaciones.size(); i++) {
                //AÑADO TÍTULO A LA ASOCIACIÓN
                title = new Paragraph("Asociación Nº " + (i + 1));
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);
                ///////////////////////////////////////////////////////
                // DATOS PERSONA
                subtitle.add("Persona:");
                subtitle.setFont(boldFont);
                document.add(subtitle);
                subtitle.clear();
                //CONTENIDO
                body.add("Identificacion: " + asociaciones.get(i).getPersona().getIdentificacion());
                body.add("\nNombres: " + asociaciones.get(i).getPersona().getNombres());
                body.add("\nApellidos: " + asociaciones.get(i).getPersona().getApellidos());
                body.add("\nEdad: " + asociaciones.get(i).getPersona().getEdad());
                document.add(body);
                body.clear();
                ///////////////////////////////////////////////////////
                //DATOS HERRAMIENTA
                subtitle.add("Herramienta:");
                subtitle.setFont(boldFont);
                document.add(subtitle);
                subtitle.clear();
                //CONTENIDO
                body.add("Nombre herramienta: " + asociaciones.get(i).getHerramienta().getHerramienta());
                body.add("\nFunción: " + asociaciones.get(i).getHerramienta().getFuncion());
                body.add("\nDescripción: " + asociaciones.get(i).getHerramienta().getDescripcion());
                body.add("\nCosto: " + asociaciones.get(i).getHerramienta().getCosto());
                document.add(body);
                body.clear();
                ///////////////////////////////////////////////////////
                //DATOS TRABAJO
                subtitle.add("Trabajo:");
                subtitle.setFont(boldFont);
                document.add(subtitle);
                subtitle.clear();
                //CONTENIDO
                body.add("Nombre trabajo: " + asociaciones.get(i).getTrabajo().getTrabajo());
                body.add("\nDescripción: " + asociaciones.get(i).getTrabajo().getDescripcion());
                document.add(body);
                body.clear();
                
                //AÑADO LINEA EN BLANCO
                document.add( Chunk.NEWLINE );
            }
            //close
            document.close();

            JOptionPane.showMessageDialog(frmAsociarHerramientas, "Se ha guardado en la carpeta Files");
            System.out.println("Terminado...");

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
