package com.example.login;

public class Clientes {

    private String nom,apellido,direccion,telefono,opcion,tipo;


    public Clientes(String nom, String apellido, String direccion, String telefono, String opcion, String tipo) {
        this.nom = nom;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.opcion = opcion;
        this.tipo = tipo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
