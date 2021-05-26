package com.emergentes.modelo;

public class Rol {
   
    private int id;
    private String descripcion;

    public Rol() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Rol{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }
        
}
