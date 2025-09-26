package main;

public class Report {

  String id;
  String reporterId;
  String fullName;
  // Valor por defecto
  TipoReporteEnum tipoReporte = TipoReporteEnum.PRD;
  String date;
  String zone;
  TipoEspecieEnum especie;
  String color;
  String particularSigns;
  String contactNumber;
  String microchip;

  public Report() {
  }

  // Constructor con todos los atributos
  public Report(String id, String reporterId, String fullName, TipoReporteEnum tipoReporte, String date, String zone, TipoEspecieEnum especie, String color, String particularSigns, String contactNumber, String microchip) {
    this.id = id;
    this.reporterId = reporterId;
    this.fullName = fullName;
    this.tipoReporte = tipoReporte;
    this.date = date;
    this.zone = zone;
    this.especie = especie;
    this.color = color;
    this.particularSigns = particularSigns;
    this.contactNumber = contactNumber;
    this.microchip = microchip;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getReporterId() {
    return reporterId;
  }

  public void setReporterId(String reporterId) {
    this.reporterId = reporterId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public TipoReporteEnum getTipoReporte() {
    return tipoReporte;
  }

  public void setTipoReporte(TipoReporteEnum tipoReporte) {
    this.tipoReporte = tipoReporte;
  }


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getZone() {
    return zone;
  }

  public void setZone(String zone) {
    this.zone = zone;
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

  public String getParticularSigns() {
    return particularSigns;
  }

  public void setParticularSigns(String particularSigns) {
    this.particularSigns = particularSigns;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getMicrochip() {
    return microchip;
  }

  public void setMicrochip(String microchip) {
    this.microchip = microchip;
  }

}
