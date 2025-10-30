package main;

import java.util.Scanner;
//para validar fecha
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import javax.swing.JOptionPane;

public class InputController {
  // static hace que no pueda cambiar
  // Hace que pertenezca a la clase de manera global y no a una referencia del objeto especificamente

  private static final Scanner inputScanner = new Scanner(System.in);

  // Función para inputs
  public static String inputVacio() {
    return InputController.inputScanner.nextLine().trim();
  }

  //Función para inputs
  public static String inputString() {
    while (true) {
      String input = InputController.inputScanner.nextLine().trim();
      if (input.isEmpty()) {
        System.out.println("Ingrese una cadena de caracteres valida");
        System.out.println("Vuelva a digitar: ");
        continue;
      }
      return input;
    }
  }

  //Función para inputs
  public static String inputStringInterfaz(String texto, String campo) {
    texto = texto.trim();
    if (texto.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Debe digitar algún texto para " + campo);
      return null;
    }

    return texto;
  }

  //Función para menú
  //Función que válida que caracteres esté dentro del rango
  public static String inputStringRango(int min, int max) {
    while (true) {
      String input = inputString();
      if (input.length() < min || input.length() > max) {
        System.out.println("Cadena de caracteres fuera de rango");
        System.out.println("Rango aceptado " + min + " - " + max + " caracteres");
        System.out.println("Vuelva a digitar: ");
        continue;
      }
      return input;
    }
  }

  // Función que válida que caracteres esté dentro del rango
  public static String inputStringRangoInterfaz(String texto, int min, int max, String campo) {
    if (texto.length() < min || texto.length() > max) {
      JOptionPane.showMessageDialog(null, "Cadena de caracteres fuera de rango, rango aceptado " + min + " - " + max + "caracteres para el campo " + campo);
      return null;
    }

    return texto;
  }

  //Valida que el usuario digite un entero válido
  public static String inputReporteId() {
    while (true) {
      String input = inputString();
      // Uso de expresiones regulares
      if (!input.matches("^REP-\\d{4,}$")) {
        System.out.println("ID de reporte inválido, debe iniciar con 'REP-' y mínimo 4 digitos numerales");
        System.out.println("Vuelva a digitarlo: ");
        continue;
      }

      return input;
    }
  }

  //Valida que el usuario digite un entero válido
  public static String inputReporteIdInterfaz(String texto) {
    // Uso de expresiones regulares
    if (!texto.matches("^REP-\\d{4,}$")) {
      JOptionPane.showMessageDialog(null, "Formato inválido en ID de reporte");
      return null;
    }
    
    

    return texto;
  }

  public static int inputInt() {
    while (true) {
      String input = InputController.inputScanner.nextLine().trim();
      try {
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Ingrese un entero valido");
      }
    }
  }

  //Función que valida que el usuario digite un número dentro del rango
  public static int inputIntRango(int min, int max) {
    while (true) {
      int input = inputInt();
      if (input < min || input > max) {
        System.out.println("Entero fuera de rango");
        System.out.println("Rango aceptado " + min + " - " + max + "");
        System.out.println("Intente nuevamente");
        continue;
      }

      return input;
    }
  }

  // Función que valida confirmación
  public static boolean inputSiNo() {
    while (true) {
      System.out.println("Digite 's' para sí y 'n' para no: ");
      String input = InputController.inputScanner.nextLine().trim().toLowerCase();
      if ("s".equals(input)) {
        return true;
      }
      if ("n".equals(input)) {
        return false;
      }

      System.out.println("Ingrese un valor valido");
    }
  }

  // Función que valida confirmación
  public static Boolean inputSiNoInterfaz(String texto, String campo) {
    if (texto.trim().isEmpty()) {
      JOptionPane.showMessageDialog(null, "No se permiten valores vacíos para " + campo);
      return null;
    }
    
    if ("s".equals(texto)) {
      return true;
    }
    if ("n".equals(texto)) {
      return false;
    }
    
    JOptionPane.showMessageDialog(null, "Valor inválido " + texto + " para " + campo);
    return null;
  }

  //Función para usuario digite nombre
  //Valida que el usuario digite el nombre en el rango máximo
  public static String inputStringMinimo(int min) {
    while (true) {
      String input = inputString();
      if (input.length() < min) {
        System.out.println("Cadena de caracteres fuera de rango");
        System.out.println("Rango aceptado " + min + " caracteres");
        continue;
      }
      return input;
    }
  }

  // Valida que el valor cumpla con el mínimo de caracteres
  public static String inputStringMinimoInterfaz(int min, String texto, String campo) {
    if (texto.length() < min) {
      JOptionPane.showMessageDialog(null, "Cadena de caracteres fuera de rango. Rango aceptado " + min + " caracteres. Campo: " + campo);
      return null;
    }

    return texto;
  }

  //Función usada en zona
  //Valida que el usuario dijite la cantidad máxima de caracteres para zona
  public static String inputStringMaximo(int max) {
    while (true) {
      String input = inputString();
      if (input.length() > max) {
        System.out.println("Cadena de caracteres fuera de rango");
        System.out.println("Rango aceptado " + max + " caracteres");
        System.out.println("Ingrese nuevamente: ");
        continue;
      }
      return input;
    }
  }

  // Valida que el usuario dijite la cantidad máxima de caracteres
  public static String inputStringMaximoInterfaz(int max, String texto, String campo) {
    if (texto.length() > max) {
      JOptionPane.showMessageDialog(null, "Cadena de caracteres fuera de rango. Rango aceptado " + max + " caracteres. Campo: " + campo);
      return null;
    }

    return texto;
  }

  /**
   * La función valida que la fecha que digite el usuario es correcta en formato
   * y que sea real Para esta función se requirió información de la siguiente
   * página Referencia Bibliográfica: Alarcón, J.(08/2023) Cómo validar fechas
   * en Java. campusMVP.es.
   * https://www.campusmvp.es/recursos/post/como-validar-fechas-en-java.aspx?srsltid=AfmBOoqh9ZAplVDZJ-dG3bVxNxV3o93s2IwdBoiM1jIAVxgXQGk-kzGW
   *
   * @return String con la fecha en formato 'dd/MM/uuuu'
   */
  public static String inputFecha() {
    //Definir el formato de fecha
    DateTimeFormatter formato = DateTimeFormatter
      .ofPattern("dd/MM/uuuu")
      .withResolverStyle(ResolverStyle.STRICT);
    while (true) {
      // Admite vacíos
      String fecha = inputVacio();
      try {
        if (fecha.isEmpty()) {
          // Devuelve la fecha actual
          System.out.println("Diste Enter, se asignó la fecha actual");
          return LocalDate.now().format(formato);
        } else {
          LocalDate fechaParseada = LocalDate.parse(fecha, formato);
          return fechaParseada.format(formato);
        }
      } catch (DateTimeParseException e) {
        System.out.println("Error en el formato de la fecha es invalido o la fecha es incorrecta. Error: " + e.getMessage());
        System.out.println("Ingrese nuevamente la fecha en el formato 'dd/mm/yyyy'");
      }
    }

  }

  /**
   * La función valida que la fecha que digite el usuario es correcta en formato
   * y que sea real Para esta función se requirió información de la siguiente
   * página Referencia Bibliográfica: Alarcón, J.(08/2023) Cómo validar fechas
   * en Java. campusMVP.es.
   * https://www.campusmvp.es/recursos/post/como-validar-fechas-en-java.aspx?srsltid=AfmBOoqh9ZAplVDZJ-dG3bVxNxV3o93s2IwdBoiM1jIAVxgXQGk-kzGW
   *
   * @param texto Fecha a validar
   * @return String con la fecha en formato 'dd/MM/uuuu'
   */
  public static String inputFechaInterfaz(String texto) {
    //Definir el formato de fecha
    DateTimeFormatter formato = DateTimeFormatter
      .ofPattern("dd/MM/uuuu")
      .withResolverStyle(ResolverStyle.STRICT);

    try {
      if (texto.isEmpty()) {
        return LocalDate.now().format(formato);
      }
      LocalDate fechaParseada = LocalDate.parse(texto, formato);
      return fechaParseada.format(formato);
    } catch (DateTimeParseException e) {
      JOptionPane.showMessageDialog(null, "Error en fecha: " + e.getMessage());
      return null;
    }

  }

  public static String inputID() {

    while (true) {
      String id = inputString();
      // Uso de expresiones regulares
      if (!id.matches("^\\d-\\d{4}-\\d{4}$")) {

        System.out.println("ID del usuario inválido, el formato debe ser 1-1111-1111");
        System.out.println("Ingrese nuevamente");
        continue;
      }
      return id;
    }
  }

  public static String inputIDInterfaz(String texto) {
    texto = texto.trim();
    if (texto.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Debe digitar algún texto");
      return null;
    }

    if (!texto.matches("^\\d-\\d{4}-\\d{4}$")) {
      JOptionPane.showMessageDialog(null, "ID del reportante inválido, el formato debe ser 1-1111-1111");
      return null;
    }

    return texto;
  }

  public static String inputEspecie() {
    System.out.println("Ingrese CAT o DOG, solo se acepta en mayúsculas");
    while (true) {
      String especie = inputString();
      if (!especie.equals("DOG") && !especie.equals("CAT")) {
        System.out.println("Formato incorrecto, solo se permite CAT o DOG en mayúsculas");
        System.out.println("Vuelve a escribir el código correcto");
        continue;
      }
      return especie;
    }
  }

  public static String inputRestrict(List<String> permitidos) {
    System.out.println("Solo se permiten los siguientes valores: ");
    for (String permitido : permitidos) {
      System.out.println("- " + permitido);
    }
    while (true) {
      String valor = inputString();
      if (permitidos.contains(valor)) {
        return valor;
      }
      System.out.println("Ingrese uno de los valores permitidos");
    }
  }

  public static String inputRestrictInterfaz(List<String> permitidos, String valor) {
    valor = valor.trim();
    if (!permitidos.contains(valor)) {
      JOptionPane.showMessageDialog(null, "Valor no permitido");
      return null;
    }
    return valor;
  }

  public static String inputTelefono() {
    System.out.println("El formato permitido es: ####-####");
    while (true) {
      String numero = inputString();
      if (!numero.matches("^\\d{4}-\\d{4}$")) {
        System.out.println("Formato del número es incorrecto, el formato correcto es: ####-####");
        System.out.println("Ingrese de nuevo: ");
        continue;
      }
      return numero;
    }
  }

  public static String inputTelefonoInterfaz(String texto) {
    texto = texto.trim();
    if (texto.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Debe digitar el número de telefono");
      return null;
    }

    if (!texto.matches("^\\d{4}-\\d{4}$")) {
      JOptionPane.showMessageDialog(null, "Formato del número es incorrecto, el formato correcto es: ####-####");
      return null;
    }

    return texto;
  }

  public static String inputReporte() {
    System.out.println("Ingrese ENC para encontrado o PDR para perdido");
    while (true) {
      String reporte = inputString();
      if (!reporte.equals("ENC") && !reporte.equals("PDR")) {
        System.out.println("Formato del reporte incorrecto");
        System.out.println("Vuelva a ingresar el tipo de reporte");
        continue;
      }
      return reporte;
    }
  }

}
