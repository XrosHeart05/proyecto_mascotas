package main;

public class Menu {
  // Clase que busca imprimir menus

  public static String centerText(String text, int large) {
    int spaces = (large - text.length()) / 2;
    return " ".repeat(spaces) + text;
  }

  public static String mainMenu() {
    return mainMenuHeader() + mainMenuBody();
  }
  
  public static String mainMenuHeader() {
    String text = "";
    int large = 45;
    String line1 = "SISTEMA - REFUGIO \"HUELLAS FELICES\"";
    String line2 = "GESTOR DE REPORTES DE MASCOTAS";
    text += "=".repeat(large) + "\n";
    text += centerText(line1, large) + "\n";
    text += centerText(line2, large) + "\n";
    text += "=".repeat(large) + "\n";

    return text;
  }
  
  public static String mainMenuBody() {
    String text = "";
    int large = 45;
    text += "1. Registrar mascota desaparecida\n";
    text += "2. Consultar por ID / Especie / Zona\n";
    text += "3. Reporte general\n";
    text += "4. Reporte agrupado\n";
    text += "5. Ver coincidencias\n";
    text += "6. Actualizar reporte\n";
    text += "7. Salir\n";
    text += "-".repeat(large) + "\n";
    text += "Ingrese una opción:\n";
    
    return text;
  }
}
