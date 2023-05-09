package com.example.transcom_android.data.model;

public class Servicio {
    public int id;
    public String empresaNombre;
    public String titulo;
    public String descripcion;

    public Servicio(int id, String empresa, String titulo, String descripcion) {
        id = id;
        empresa = empresa;
        titulo = titulo;
        descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresaNombre;
    }

    public void setEmpresa(String empresa) {
        this.empresaNombre = empresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
