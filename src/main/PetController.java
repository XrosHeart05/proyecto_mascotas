package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetController {

  private final List<Pet> pets;
  private Pet pet;

  public PetController() {
    this.pets = new ArrayList<>();
  }

  public Pet createPet(String name) {
    // Ajustar valores a insertar
    // Se deben de ajustar los parámetros a insertar
    // Ejemplo, si además del nombre lleva, fecha de nacimiento, todos esos datos debería de insertarse y no solo el nombre
    pet = new Pet(0, name, true);
    pets.add(pet);
    return pet;
  }

  // Este método obtiene TODAS las máscotas, pueda que este método no sea necesario del todo
  // De ser el caso que se necesite dejarlo
  // En caso de que sea necesario pero devuelva demasiados resultados se debe buscar la forma de 
  // limitar la cantidad de registros a devolver (Tipo LIMIT en SQL), pero no creo que esto sea necesario
  // Ya que habría que limitar y paginar y es un poco más complejo
  public List<Pet> getAllPets() {
    // Investigar como funciona ArrayList
    return new ArrayList<>(pets);
  }

  // Regresa la máscota por identificador
  // Devuelve un Optional, Optional puede contener un objeto, en este caso Pet o estar vacío si dicha máscota no se encontró
  // No devuelve null
  public Optional<Pet> getPetById(int id) {
    // Stream es un método propio de los ArrayList o List
    // Genera un Stream (flujo) de objetos
    return pets.stream()
      // Filter aplica como su nombre indica filtros a dichos objetos
      // Ósea, es como un colador de datos
      // Que filtros, en este caso solo deja pasar los datos que coinciden con el ID recibido como argumento
      // La nomenclatura usada es la siguiente, "p" pasa a ser la referencia de un único objeto de máscota
      // Es como un lop forEach o for de pets[i]
      // Se hace uso del getId() de los getters de la clase Pet
      .filter(p -> p.getId() == id)
      // Devuelve la primera coincidencia, si hubieran más coincidencias no importa porque ya hizo un return
      .findFirst();
  }

  // Realizar búsqueda de máscota por los demás atributos
  // Se puede colocar o quitar el parámetro status, esto como alguna consulta 
  // en consola al usuario, indicando 0 si está perdido o 1 si está hallado por ejemplo
  public List<Pet> searchPets(String query, boolean status) {
    // Valida que el query no esté vacío
    if (query == null || query.isEmpty()) {
      System.out.println("Ingresa los datos a buscar");
      // Si el query está vacío devuelve un arreglo vacío para su futura validación
      return new ArrayList<>();
    }

    // Transforma el query a caracteres en minuscula
    String lowerQuery = query.toLowerCase();
    return pets.stream()
      // Convierte el nombre del objeto (máscota) a minúscula y lo iguala al query
      // Se usa el método "contains" para que sea similar a la cláusula LIKE %% de SQL
      .filter(p -> p.getName().toLowerCase().contains(lowerQuery))
      // Validar si usar o no este filtro
      .filter(p -> p.isStatus() == status)
      // Se debe convertir todos los filtros de un stream a nuevamente un listado
      // Porque se determinó que se devolvería un List<Pet>
      .toList();
  }
  
  public List<Pet> suggestMatches(Pet _pet){
    String petName = _pet.getName().toLowerCase();
    boolean opposite = !_pet.isStatus();
    
    return pets.stream()
      // Encontrados vs Perdidos
      .filter(p -> p.isStatus() == opposite)
      // Otras coincidencias
      .filter(p -> p.getName().toLowerCase().equals(petName) || petName.contains(p.getName().toLowerCase()))
      .toList();
      
  }

  public Pet updatePet(int id, String name, boolean status) {
    Optional<Pet> auxPet = getPetById(id);
    if (auxPet.isEmpty()) {
      return null;
    }
    Pet newPet = auxPet.get();
    pet.setName(name);
    pet.setStatus(status);

    return newPet;
  }

  public boolean deletePet(int id) {
    // Validar removeIf
    return pets.removeIf(p -> p.getId() == id);
  }

  /**
   * Obtiene los datos de la máscota respecto su ID
   *
   * @param petId Identificador de la máscota
   * @return String con los datos de la máscota
   */
  public Pet getData(int petId) {
    // Obtener datos de la máscota respecto al id de la misma - Puede hacerse uso del método getPet(petId)
    // Recorrer los datos
    // Poblar la variable data
    // Ejemplo: La máscota es un "gato-perro" llamado(a) "pet.nombre" y está "perdido-hallado";

    return pet;
  }
}
