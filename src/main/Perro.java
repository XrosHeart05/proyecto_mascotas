package main;

public class Perro extends Mascota {
    
    private String raza; 
    private String talla;
    private boolean collar; 

    public Perro(String raza, String talla, boolean collar, TipoEspecieEnum especie, String color, String sennas, String microchip) {
        super(especie, color, sennas, microchip);
        this.raza = raza;
        this.talla = talla;
        this.collar = collar;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public boolean isCollar() {
        return collar;
    }

    public void setCollar(boolean collar) {
        this.collar = collar;
    }
       

    @Override
    String resumenMascota() {
        String tieneCollar;
        if (collar) {
            tieneCollar = "sí";
        } else {
            tieneCollar = "no";
        }
        String micro;
        if (super.getMicrochip().isEmpty()){
            micro= "no tiene microchip"; 
        } else{
            micro= "tiene el microchip: " + super.getMicrochip();
        }
        return "Es un perro de raza '" + raza + 
                "', de color '" + super.getColor() + 
                "' de tamaño:" + talla +  
                ", " + tieneCollar + " tiene collar" +
                ", tiene las siguientes señas: " + super.getSennas() +
                " y " + micro ;
    }
    
}
