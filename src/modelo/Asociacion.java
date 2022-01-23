/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Power Computers
 */
public class Asociacion {

    Trabajo trabajo;
    Herramienta herramienta;
    Persona persona;

    public Asociacion() {
    }

    public Asociacion(Trabajo trabajo, Herramienta herramienta, Persona persona) {
        this.trabajo = trabajo;
        this.persona = persona;
        this.herramienta = herramienta;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }

    public Herramienta getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(Herramienta herramienta) {
        this.herramienta = herramienta;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
