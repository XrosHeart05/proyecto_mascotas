package main;

import java.util.Scanner;

public class InputController {
  // static hace que no pueda cambiar
  // Hace que pertenezca a la clase de manera global y no a una referencia del objeto especificamente

  private static Scanner scanner = new Scanner(System.in);

  // Al llamar los métodos se haría de la siguiente manera
  // InputController.readInt("Ingrese un número: ");
  // Y no así
  // InputController input = new InputController(); // Esto es una instancia del objeto común
  // input.readInt("Ingrese un número: ");
  public static String inputString() {
    while (true) {
      String input = scanner.nextLine().trim();
      if (input.isEmpty()) {
        System.out.println("Ingrese una cadena de caracteres valida");
        continue;
      }
      return input;
    }
  }

  public static String inputStringRange(int min, int max) {
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
      String input = scanner.nextLine().trim();
      try {
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Ingrese un entero valido");
      }
    }
  }

  public static int inputIntRange(int min, int max) {
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

  public static boolean inputYesNo() {
    while (true) {

      String input = scanner.nextLine().trim().toLowerCase();
      if ("s".equals(input)) {
        return true;
      }
      if ("n".equals(input)) {
        return false;
      }

      System.out.println("Ingrese un valor valido");
    }
  }

}
