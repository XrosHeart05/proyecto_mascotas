package main;

public class ReporteEncontrada extends Reporte{
  private String tipo;

  public ReporteEncontrada() {
  }
  
  public ReporteEncontrada(String id, String reportanteId, String nombreCompleto, String fecha, String zona, String contacto, Mascota mascota) {
    super(id, reportanteId, nombreCompleto, fecha, zona, contacto, mascota);
    this.tipo = "ENC";
  }

  @Override
  public String getTipo() {
    return tipo;
  }
  
}
