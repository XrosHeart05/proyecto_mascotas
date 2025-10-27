package main;

public class Reporte {

  String id;
  String reportanteId;
  String nombreCompleto;
  String fecha;
  String zona;
  String contacto;
  Mascota mascota;

  public Reporte() {
  }

  // Constructor con todos los atributos
  public Reporte(String id, String reportanteId, String nombreCompleto, String fecha, String zona, String contacto, Mascota mascota) {
    this.id = id;
    this.reportanteId = reportanteId;
    this.nombreCompleto = nombreCompleto;
    this.fecha = fecha;
    this.zona = zona;
    this.contacto = contacto;
    this.mascota = mascota;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getReportanteId() {
    return reportanteId;
  }

  public void setReportanteId(String reportanteId) {
    this.reportanteId = reportanteId;
  }

  public String getNombreCompleto() {
    return nombreCompleto;
  }

  public void setNombreCompleto(String nombreCompleto) {
    this.nombreCompleto = nombreCompleto;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public String getZona() {
    return zona;
  }

  public void setZona(String zona) {
    this.zona = zona;
  }

  public String getContacto() {
    return contacto;
  }

  public void setContacto(String contacto) {
    this.contacto = contacto;
  }

  public Mascota getMascota() {
    return mascota;
  }

  public void setMascota(Mascota mascota) {
    this.mascota = mascota;
  }

  public String getTipo() {
    return "";
  }

}
