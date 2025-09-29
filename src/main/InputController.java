package main;

import java.util.Scanner;
//para validar fecha
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


public class InputController {
  // static hace que no pueda cambiar
  // Hace que pertenezca a la clase de manera global y no a una referencia del objeto especificamente

  private static final Scanner inputScanner = new Scanner(System.in);

  // Al llamar los métodos se haría de la siguiente manera
  // InputController.readInt("Ingrese un número: ");
  // Y no así
  // InputController input = new InputController(); // Esto es una instancia del objeto común
  // input.readInt("Ingrese un número: ");
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

  public static int inputIntRango(int min, int max) {
    while (true) {
      int input = inputInt();
      if (input < min || input > max) {
        System.out.println("Entero fuera de rango");
        System.out.println("Rango aceptado " + min + " - " + max + "");
        continue;
      }

      return input;
    }
  }

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

  public static String inputFecha(){
    while (true) {
      String input = inputString();
      System.out.println("Ingrese la fecha en el formato: dd/mm/yyyy");
      if (input.length() != 10) {
        System.out.println("La fecha no cumple con el formato establecido");
        continue;
      }
      return input;
    }
  }
  
  /**
   * La función valida que la fecha que digite el usuario es correcta en formato y que sea real
   * @return true: si la fecha es correcta, false: la fecha es incorrecta
   */
  public static boolean validarFecha(){
     //Definir el formato de fecha
     DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
             .withResolverStyle(ResolverStyle.STRICT);
     try {
         String fecha = inputString();
         LocalDate.parse(fecha,formato);
         return true;
     } catch (DateTimeParseException e){
         System.out.println(" Error: el formato de la fecha es invalido o la fecha es incorrecta");
         return false;
     }
  }
}
