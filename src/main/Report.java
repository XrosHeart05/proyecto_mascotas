package main;

public class Report {

  int id;
  String reporterId;
  String fullName;
  ReportTypeEnum reportType;
  String date;
  String zone;
  SpeciesTypeEnum species;
  String color;
  String particularSigns;
  String contactNumber;
  String microchip;

  // Constructor con todos los atributos
  public Report(int id, String reporterId, String fullName, ReportTypeEnum reportType, String date, String zone, SpeciesTypeEnum species, String color, String particularSigns, String contactNumber, String microchip) {
    this.id = id;
    this.reporterId = reporterId;
    this.fullName = fullName;
    this.reportType = reportType;
    this.date = date;
    this.zone = zone;
    this.species = species;
    this.color = color;
    this.particularSigns = particularSigns;
    this.contactNumber = contactNumber;
    this.microchip = microchip;
  }

  // Constructor sin microchip
  public Report(int id, String reporterId, String fullName, ReportTypeEnum reportType, String date, String zone, SpeciesTypeEnum species, String color, String particularSigns, String contactNumber) {
    this.id = id;
    this.reporterId = reporterId;
    this.fullName = fullName;
    this.reportType = reportType;
    this.date = date;
    this.zone = zone;
    this.species = species;
    this.color = color;
    this.particularSigns = particularSigns;
    this.contactNumber = contactNumber;
  }
  
  // TODO: 
  // En los SET mandar a llamar el InputController
  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public ReportTypeEnum getReportType() {
    return reportType;
  }

  public void setReportType(ReportTypeEnum reportType) {
    this.reportType = reportType;
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

  public SpeciesTypeEnum getSpecies() {
    return species;
  }

  public void setSpecies(SpeciesTypeEnum species) {
    this.species = species;
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
