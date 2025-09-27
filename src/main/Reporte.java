package main;

public class Reporte {

  String id;
  String reportanteId;
  String nombreCompleto;
  // Valor por defecto
  TipoReporteEnum tipoReporte = TipoReporteEnum.PDR;
  String fecha;
  String zona;
  TipoEspecieEnum especie;
  String color;
  String senasParticulares;
  String contacto;
  String microchip;

  public Reporte() {
  }

  // Constructor con todos los atributos
  public Reporte(String id, String reportanteId, String nombreCompleto, TipoReporteEnum tipoReporte, String fecha, String zona, TipoEspecieEnum especie, String color, String senasParticulares, String contacto, String microchip) {
    this.id = id;
    this.reportanteId = reportanteId;
    this.nombreCompleto = nombreCompleto;
    this.tipoReporte = tipoReporte;
    this.fecha = fecha;
    this.zona = zona;
    this.especie = especie;
    this.color = color;
    this.senasParticulares = senasParticulares;
    this.contacto = contacto;
    this.microchip = microchip;
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

  public TipoReporteEnum getTipoReporte() {
    return tipoReporte;
  }

  public void setTipoReporte(TipoReporteEnum tipoReporte) {
    this.tipoReporte = tipoReporte;
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

  public TipoEspecieEnum getEspecie() {
    return especie;
  }

  public void setEspecie(TipoEspecieEnum especie) {
    this.especie = especie;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getSenasParticulares() {
    return senasParticulares;
  }

  public void setSenasParticulares(String senasParticulares) {
    this.senasParticulares = senasParticulares;
  }

  public String getContacto() {
    return contacto;
  }

  public void setContacto(String contacto) {
    this.contacto = contacto;
  }

  public String getMicrochip() {
    return microchip;
  }

  public void setMicrochip(String microchip) {
    this.microchip = microchip;
  }

}
