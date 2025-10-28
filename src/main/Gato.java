package main;

public class Gato extends Mascota {

  private String raza;
  private String tipoPelaje;
  private boolean esterelizado;

  public Gato(String raza, String tipoPelaje, boolean esterelizado, String especie, String color, String sennas, String microchip) {
    super(especie = "CAT", color, sennas, microchip);
    this.raza = raza;
    this.tipoPelaje = tipoPelaje;
    this.esterelizado = esterelizado;
  }

  public String getRaza() {
    return raza;
  }

  public void setRaza(String raza) {
    this.raza = raza;
  }

  public String getTipoPelaje() {
    return tipoPelaje;
  }

  public void setTipoPelaje(String tipoPelaje) {
    this.tipoPelaje = tipoPelaje;
  }

  public boolean isEsterelizado() {
    return esterelizado;
  }

  public void setEsterelizado(boolean esterelizado) {
    this.esterelizado = esterelizado;
  }

  @Override
  String resumenMascota() {
    String estaEsterelizado;
    if (esterelizado) {
      estaEsterelizado = "sí";
    } else {
      estaEsterelizado = "no";
    }
    String micro;
    if (super.getMicrochip().isEmpty()) {
      micro = "no tiene microchip";
    } else {
      micro = "tiene el microchip: " + super.getMicrochip();
    }
    return "Es un gato de raza '" + raza
      + "', de color '" + super.getColor()
      + "' con tipo de pelaje:" + tipoPelaje
      + ", " + estaEsterelizado + " está esterelizado"
      + ", tiene las siguientes señas: " + super.getSennas()
      + " y " + micro;
  }

  @Override
  String toPrint() {
    String estaEsterelizado;
    if (isEsterelizado()) {
      estaEsterelizado = "si";
    } else {
      estaEsterelizado = "no";
    }

    return String.join("|",
      getRaza(),
      getTipoPelaje(),
      estaEsterelizado
    );

  }

}
