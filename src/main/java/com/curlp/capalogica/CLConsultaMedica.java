package com.curlp.capalogica;

public class CLConsultaMedica {
    
    private int idConsultasMedicas;
    private String fechaIngreso;
    private String observaciones;
    private String recetasMedicas;
    private String numeroIdentidad;
    private int idUsuario;

    public int getIdConsultasMedicas() {
        return idConsultasMedicas;
    }

    public void setIdConsultasMedicas(int idConsultasMedicas) {
        this.idConsultasMedicas = idConsultasMedicas;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRecetasMedicas() {
        return recetasMedicas;
    }

    public void setRecetasMedicas(String recetasMedicas) {
        this.recetasMedicas = recetasMedicas;
    }

    public String getNumeroIdentidad() {
        return numeroIdentidad;
    }

    public void setNumeroIdentidad(String numeroIdentidad) {
        this.numeroIdentidad = numeroIdentidad;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
