package main;

public class Main {

  public static void main(String[] args) {
    // Refugio Huellas Felices
    // Gestor de Reporte de Mascotas
    // Obtener automáticamente la fecha del sistema para cada reporte

    PetController petController = new PetController();

    Pet pet = petController.createPet("Archie");
  }

}
