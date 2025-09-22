package main;

public class Main {

  public static void main(String[] args) {
    // Refugio Huellas Felices
    // Gestor de Reporte de Mascotas
    // Obtener automáticamente la fecha del sistema para cada reporte

    PetController petController = new PetController();

    Pet pet = petController.createPet("Archie");
    // Una vez se soliciten datos al cliente, se llame el método de sugerir coincidencias
    // Inmediatamente recorrer dichos datos y además de lo que quieras hacer sugerir coincidencias al usuario por si acaso
    // Ejemplo, registras una máscota, inmediatamente sugiera coincidencias
    // suggests.forEach(p -> System.out.println("Posible coincidencia " + p.getName());
  }

}
