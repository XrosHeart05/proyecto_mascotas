package main;

public class Main {

  public static void main(String[] args) {
    // Refugio Huellas Felices
    // Gestor de Reporte de Mascotas
    // Obtener automáticamente la fecha del sistema para cada reporte

    int option = 0;
    do {
      System.out.println(Menu.mainMenu());
      option = InputController.inputIntRange(1, 7);
    } while (option != 7);
    // Posible forma de llamar método para sugerir coincidencias
    // suggests.forEach(p -> System.out.println("Posible coincidencia " + p.getName());
  }

}
