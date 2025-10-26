package main;

public abstract class Mascota {
    private TipoEspecieEnum especie; 
    private String color;
    private String sennas; 
    private String microchip;

    public Mascota(TipoEspecieEnum especie, String color, String sennas, String microchip) {
        this.especie = especie;
        this.color = color;
        this.sennas = sennas;
        this.microchip = microchip;
    }

    public TipoEspecieEnum getEspecie() {
        return especie;
    }

    public void setEspecie(TipoEspecieEnum especie) {
        this.especie = especie;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSennas() {
        return sennas;
    }

    public void setSennas(String sennas) {
        this.sennas = sennas;
    }

    public String getMicrochip() {
        return microchip;
    }

    public void setMicrochip(String microchip) {
        this.microchip = microchip;
    }
    
    abstract String resumenMascota();
}
