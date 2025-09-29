package main;

public class Util {

  public static String centrarTexto(String texto, int largo) {
    int espacios = (largo - texto.length()) / 2;
    return " ".repeat(espacios) + texto;
  }
}
