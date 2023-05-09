package com.example.transcom_android.data.model;

import java.util.ArrayList;

public class Servicios {
    public ArrayList<Servicio> servicios;

    public Servicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }
}
