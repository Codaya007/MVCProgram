package modelo;

public class Trabajo {
    
    private String trabajo;
    private String descripcion;
    
    public Trabajo(String trabajo, String descripcion){
        this.trabajo = trabajo;
        this.descripcion = descripcion;
    }
    
    public Trabajo(){
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    } 
    
}
