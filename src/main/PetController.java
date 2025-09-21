package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetController {

  private List<Pet> pets;
  private Pet pet;

  public PetController() {
    this.pets = new ArrayList<>();
  }

  public Pet createPet(String name) {
    // Ajustar valores a insertar
    pet = new Pet(0, name, true);
    pets.add(pet);
    return pet;
  }
  
  public List<Pet> getAllPets(){
    // Investigar como funciona ArrayList
    return new ArrayList<>(pets);
  }
  
  // Investigar como funciona Optional
  public Optional<Pet> getPetById(int id){
     return pets.stream()
       .filter(p -> p.getId() == id)
       .findFirst();
  }
  
  public Pet updatePet(int id, String name, boolean status) {
    Optional<Pet> auxPet = getPetById(id);
    if (auxPet.isEmpty()){
      return null;
    }
    Pet newPet = auxPet.get();
    pet.setName(name);
    pet.setStatus(status);
    
    return newPet;
  }
  
  public boolean deletePet(int id){
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
