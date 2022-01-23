package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import modelo.Asociacion;
import modelo.Herramienta;
import modelo.Persona;
import modelo.Trabajo;
import vista.frmPrincipal;

public class ctrlPrincipal implements ActionListener {

    frmPrincipal frmPrincipal = new frmPrincipal();
    List<Herramienta> herramientas = new ArrayList<>();
    List<Trabajo> trabajos = new ArrayList<>();
    List<Persona> personas = new ArrayList<>();
    List<Asociacion> asociaciones = new ArrayList<>();

    public void setAsociaciones(List<Asociacion> asociaciones) {
        this.asociaciones = asociaciones;
    }

    public void setHerramientas(List<Herramienta> herramientas) {
        this.herramientas = herramientas;
    }

    public void setTrabajos(List<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public void Inicio() {
        frmPrincipal.setVisible(true);
        frmPrincipal.setLocationRelativeTo(null);

        //--------------Botones---------------
        frmPrincipal.btnRegistrarPersonas.addActionListener(this);
        frmPrincipal.btnRegistrarHerramientas.addActionListener(this);
        frmPrincipal.btnRegistrarTrabajos.addActionListener(this);
        frmPrincipal.btnAsociarHerramientas.addActionListener(this);
        frmPrincipal.btnLimpiarTodo.addActionListener(this);
        frmPrincipal.btnSalir.addActionListener(this);

        //le quemo algunos valores para probar
        Persona personaDefault = new Persona("Juan Ricardo", "Jimenez Sánches", 1900127177, 25);
        personas.add(personaDefault);
        Trabajo trabajoDefault = new Trabajo("Contador", "Diseña, gestiona y ejecuta las estrategias económicas y financieras de una empresa. Interpreta la información contable para el planeamiento, el control y la toma de decisiones.");
        trabajos.add(trabajoDefault);
        Herramienta herramientaDefault = new Herramienta("Calculadora pantalla gráfica", "Calcula", "Calculadora para la resolución de problemas matemáticos complejos", 100.45);
        herramientas.add(herramientaDefault);
    }
    
    public void reanudar(){
        frmPrincipal.setVisible(true);
        frmPrincipal.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pruebaDatos();

        if (frmPrincipal.btnRegistrarPersonas == e.getSource()) {
            frmPrincipal.setVisible(false);
            ctrlPersonas ctrlPersonas = new ctrlPersonas();
            ctrlPersonas.Persona(personas, this);
        }
        if (frmPrincipal.btnRegistrarHerramientas == e.getSource()) {
            frmPrincipal.setVisible(false);
            ctrlHerramientas ctrlHerramientas = new ctrlHerramientas();
            ctrlHerramientas.Herramienta(herramientas, this);
        }

        if (frmPrincipal.btnRegistrarTrabajos == e.getSource()) {
            frmPrincipal.setVisible(false);
            ctrlTrabajos ctrlTrabajos = new ctrlTrabajos();
            ctrlTrabajos.Trabajo(trabajos, this);
        }

        if (frmPrincipal.btnAsociarHerramientas == e.getSource()) {
            frmPrincipal.setVisible(false);
            ctrlAsociarHerramientas ctrlAsociarHerramientas = new ctrlAsociarHerramientas();
            ctrlAsociarHerramientas.Asociar(personas, trabajos, herramientas, asociaciones, this);
        }

        if (frmPrincipal.btnLimpiarTodo == e.getSource()) {
            /////////////////////////////////////////
            personas.clear();
            herramientas.clear();
            trabajos.clear();
            asociaciones.clear();
        }

        if (frmPrincipal.btnSalir == e.getSource()) {
            frmPrincipal.dispose();
            System.exit(0);
        }
    }

    public void pruebaDatos() {
        System.out.println("====================================");
        System.out.println(herramientas.toString());
        System.out.println(personas.toString());
        System.out.println(trabajos.toString());
        System.out.println(asociaciones.toString());
    }
}
