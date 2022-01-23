package modelo;

public class Herramienta {

    private String herramienta;
    private String funcion;
    private String descripcion;
    private double costo;

    public Herramienta(String herramienta, String funcion, String descripcion, double costo) {
        this.herramienta = herramienta;
        this.funcion = funcion;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public Herramienta() {

    }

    public String getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(String herramienta) {
        this.herramienta = herramienta;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

}
