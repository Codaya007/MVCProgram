package modelo;

public class Persona {
    
    private String nombres;
    private String apellidos;
    private int identificacion;
    private int edad;

    public Persona(){
    }
    
    public Persona(String nombres, String apellidos, int identificacion, int edad){
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.edad = edad;
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public String getNombresCompletos() {
        return this.nombres + " " + this.apellidos;
    }
    
}
