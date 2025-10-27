package main;

import java.util.Scanner;
//para validar fecha
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

public class InputController {
  // static hace que no pueda cambiar
  // Hace que pertenezca a la clase de manera global y no a una referencia del objeto especificamente

  private static final Scanner inputScanner = new Scanner(System.in);

  //Función para inputs
  public static String inputVacio() {
    return InputController.inputScanner.nextLine().trim();
  }

  //Función para inputs
  public static String inputString() {
    while (true) {
      String input = InputController.inputScanner.nextLine().trim();
      if (input.isEmpty()) {
        System.out.println("Ingrese una cadena de caracteres valida");
        continue;
      }
      return input;
    }
  }

  //Función para menú
  //Función que válida que caracteres esté dentro del rango
  public static String inputStringRango(int min, int max) {
    while (true) {
      String input = inputString();
      if (input.length() < min || input.length() > max) {
        System.out.println("Cadena de caracteres fuera de rango");
        System.out.println("Rango aceptado " + min + " - " + max + " caracteres");
        continue;
      }
      return input;
    }
  }

  //Valida que el usuario digite un entero válido
  public static String inputReporteId() {
    while (true) {
      String input = inputString();
      // Uso de expresiones regulares
      if (!input.matches("^REP-\\d{4,}$")) {
        System.out.println("ID de reporte inválido, debe iniciar con 'REP-' y mínimo 4 digitos numerales");
        continue;
      }

      return input;
    }
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

  //Función usada en zona
  //Valida que el usuario dijite la cantidad máxima de caracteres para zona
  public static String inputStringMaximo(int max) {
    while (true) {
      String input = inputString();
      if (input.length() > max) {
        System.out.println("Cadena de caracteres fuera de rango");
        System.out.println("Rango aceptado " + max + " caracteres");
        continue;
      }
      return input;
    }
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

  public static String inputID() {

    while (true) {
      String id = inputString();
      // Uso de expresiones regulares
      if (!id.matches("^\\d-\\d{4}-\\d{4}$")) {

        System.out.println("ID del usuario inválido, el formato debe ser 1-1111-1111");
        continue;
      }
      return id;
    }
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
    }
  }

  public static String inputTelefono() {
    System.out.println("Ingrese el número de telefono");
    System.out.println("El formato permitido es: ####-####");
    while (true) {
      String numero = inputString();
      if (!numero.matches("^\\d{4}-\\d{4}$")) {
        System.out.println("Formato del número es incorrecto, el formato correcto es: ####-####");
        continue;
      } else {
        return numero;
      }
    }
  }

  public static String inputReporte() {
    System.out.println("Ingrese ENC para encontrado o PDR para perdido");
    while (true) {
      String reporte = inputString();
      if (!reporte.equals("ENC") && !reporte.equals("PDR")) {
        System.out.println("Formato del reporte incorrecto");
        System.out.println("Vuelva a ingresar el tipo de reporte");
        continue;
      } else {
        return reporte;
      }
    }
  }

}
