package main;

public class Pet {
  // Atributos de la Mascota
  int id;
  String name;
  boolean status; // 0 - False (Perdido); 1 - True (Hallado)
  
  
  // Constructor
  public Pet(int id, String name, boolean status) {
    this.id = id;
    this.name = name;
    this.status = status;
  }

  // Getters y Setters

  public int getId() {
    return id;
  }

  public void setId(int id) {
    // Validaciones
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    // Validaciones
    this.name = name;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    // Validaciones
    this.status = status;
  }
  
}
