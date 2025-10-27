package main;

public class ReportePerdida extends Reporte {

  private String tipo;

  public ReportePerdida() {
  }
  
  public ReportePerdida(String id, String reportanteId, String nombreCompleto, String fecha, String zona, String contacto, Mascota mascota) {
    super(id, reportanteId, nombreCompleto, fecha, zona, contacto, mascota);
    this.tipo = "PDR";
  }

  @Override
  public String getTipo() {
    return tipo;
  }

}
