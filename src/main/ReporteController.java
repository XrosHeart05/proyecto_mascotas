package main;

import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReporteController {

  public final List<Reporte> reportes; // Listado que solo admite objetos Reporte // Sirve para agregar nuevos reportes
  public Reporte reporte; // Se usa para interactuar con un objeto reporte (SINGULAR)

  // Constructor
  public ReporteController() {
    this.reportes = new ArrayList<>();
  }

  public void crearReporte(Reporte _reporte) {
    // Ajustar valores a insertar
    // Se debe validar si se inserta o no el microchip
    reportes.add(_reporte);
  }

  // Este método obtiene TODOS los reportes, pueda que este método no sea necesario del todo
  // De ser el caso que se necesite dejarlo
  // En caso de que sea necesario pero devuelva demasiados resultados se debe buscar la forma de 
  // limitar la cantidad de registros a devolver (Tipo LIMIT en SQL), pero no creo que esto sea necesario
  // Ya que habría que limitar y paginar y es un poco más complejo
  public List<Reporte> obtenerReportes() {
    // Investigar como funciona ArrayList
    return reportes;
  }

  // Regresa el reporte por identificador
  // Devuelve un Optional, Optional puede contener un objeto, en este caso Report o estar vacío si dicha máscota no se encontró
  // No devuelve null
  public List<Reporte> obtenerReportesPorId(String id) {
    // Stream es un método propio de los ArrayList o List
    // Genera un Stream (flujo) de objetos
    return reportes.stream().filter(i -> i.getId().equals(id)).toList();
    // Filter aplica como su nombre indica filtros a dichos objetos
    // Ósea, es como un colador de datos
    // Que filtros, en este caso solo deja pasar los datos que coinciden con el ID recibido como argumento
    // La nomenclatura usada es la siguiente, "r" pasa a ser la referencia de un único objeto de reporte
    // Es como un loop forEach o for de reportes[i]
    // Se hace uso del getId() de los getters de la clase Report
    // equals funciona para Strings al igual que == para int
    // Devuelve la primera coincidencia, si hubieran más coincidencias no importa porque ya hizo un return

  }

  public List<Reporte> obtenerReportePorEspecie(String especie) {
    TipoEspecieEnum _especie;
    if ("DOG".equals(especie)) {
      _especie = TipoEspecieEnum.DOG;
    } else {
      _especie = TipoEspecieEnum.CAT;
    }
    return reportes.stream().filter(i -> i.getEspecie().equals(_especie)).toList();
  }

  public List<Reporte> obtenerReportePorZona(String zona) {
    return reportes.stream().filter(i -> i.getZona().equals(zona)).toList();
  }

  // Realizar búsqueda de reporte por los demás atributos
  public List<Reporte> buscarReportes(int opcion) {
    // Transforma el query a caracteres en minuscula
    List<Reporte> resultado;
    String consulta;

    switch (opcion) {
      // ID Reportante
      case 1:
        System.out.println("\nIngrese el ID del reportante: ");
        consulta = InputController.inputString().toLowerCase();
        resultado = obtenerReportesPorId(consulta);
        break;

      // Especie
      // TODO: Aplicar filtro de especie
      case 2:
        System.out.println("\nIngrese la especie (DOG/CAT): ");
        consulta = InputController.inputString().toLowerCase();
        resultado = obtenerReportePorEspecie(consulta);
        break;

      // Zona
      // TODO: Aplicar filtro de zona
      case 3:
        System.out.println("\nIngrese la zona: ");
        consulta = InputController.inputString().toLowerCase();
        resultado = obtenerReportePorZona(consulta);
        break;
      default:
        return new ArrayList<>();
    }

    return resultado;
  }

  /**
   * Imprime la lista de reportes ingresados
   * @param reportes
   */
  public void imprimirReportes(List<Reporte> reportes) {
    System.out.println("Resultados encontrados:");
    int nombreMaximo = 0, zonaMaximo = 0;
    for (Reporte r : reportes) {
      if (r.getNombreCompleto().length() > nombreMaximo) {
        nombreMaximo = r.getNombreCompleto().length();
      }
      //Caracteres maximos para zona
      if (r.getZona().length() > zonaMaximo) {
        zonaMaximo = r.getZona().length();
      }
    }
    System.out.print("ID Reportante |");
    System.out.print(" Nombre" + " ".repeat(nombreMaximo - 5) + "|");
    System.out.print(" Fecha      |");
    System.out.print(" Zona" + " ".repeat(zonaMaximo - 3) + "|");
    System.out.println(" Tipo ");
    System.out.print("--------------+");
    System.out.print("-" + "-".repeat(nombreMaximo) + "-");
    System.out.print("+------------+");
    System.out.print("-" + "-".repeat(zonaMaximo) + "-");
    System.out.println("+------");

    for (Reporte r : reportes) {
      System.out.print(r.getId() + "   | ");
      System.out.print(r.getNombreCompleto() + " ".repeat(nombreMaximo - r.getNombreCompleto().length()) + " | ");
      System.out.print(r.getFecha() + " | ");
      System.out.print(r.getZona() + " ".repeat(zonaMaximo - r.getZona().length()) + " | ");
      System.out.println(r.getEspecie() + " ");
    }
  }

  /**
   * Suguiere coincidencias del parámetro ingresado
   *
   * @param _reporte
   * @return lista de reportes
   */
  public List<Reporte> sugerirCoincidencias(Reporte _reporte) {
    String reportante = _reporte.getNombreCompleto().toLowerCase();
    String color = _reporte.getColor().toLowerCase();
    TipoReporteEnum tipoReporte = _reporte.getTipoReporte();

    return reportes.stream()
      .filter(r -> r.getTipoReporte() == _reporte.getTipoReporte())
      // Otras coincidencias
      .filter(r -> r.getNombreCompleto().toLowerCase().contains(reportante))
      .filter(r -> r.getColor().toLowerCase().contains(color))
      // Demás atributos a filtrar si se desea
      .toList();

  }

  public Reporte actualizarReporte(Reporte _reporte) {
    Reporte aux = obtenerReportesPorId(_reporte.getId()).getFirst();
    Reporte coincidencia = aux;
    coincidencia.setTipoReporte(_reporte.getTipoReporte());
    // Ajustar demás atributos

    return coincidencia;
  }

  public void reporteAgrupado() {
    int contadorGatos = 0, contadorPerros = 0, contadorPDR = 0, contadorENC = 0;
    for (Reporte i : this.reportes) {
      if (i.getEspecie() == TipoEspecieEnum.CAT) {
        contadorGatos += 1;
      } else {
        contadorPerros += 1;
      }
      if (i.getTipoReporte() == TipoReporteEnum.ENC) {
        contadorENC += 1;
      } else {
        contadorPDR += 1;
      }
    }

    System.out.println("-".repeat(45));
    System.out.println(Menu.centrarTexto("REPORTE AGRUPADO DE MASCOTAS", 45));
    System.out.println("-".repeat(45));
    System.out.println("Conteo por tipo:");
    System.out.println("PDR: " + contadorPDR);
    System.out.println("ENC: " + contadorENC + "\n");
    System.out.println("Conteo por especie:");
    System.out.println("CAT: " + contadorGatos);
    System.out.println("DOG: " + contadorPerros + "\n");
  }

  public boolean eliminarReporte(String id) {
    // Validar removeIf
    return reportes.removeIf(r -> r.getId().equals(id));
  }
  
  public List<Reporte> reportesCoincidencias(){
      List<Reporte> resultado = new ArrayList<>();
      int contador = 0; 
      for (int i = 0; i < reportes.size(); i++){
          Reporte reporte1 = reportes.get(i);
          if (reporte1.getTipoReporte() == TipoReporteEnum.PDR){
              for (int j = i+1; j < reportes.size(); i++){ 
              Reporte reporte2 = reportes.get(j);
              
              //Aqui se empieza a hacer las comparaciones para coincidencias
              if (reporte1.microchip.equals(reporte2.microchip)){
                  resultado.add(reportes.get(i));
                  resultado.add(reportes.get(j));
                  contador +=1; 
                  System.out.println("-------------------------------------------------------------------------");
                  break;
              }
              if (reporte1.especie.equals(reporte2.especie) && reporte1.color.equals(reporte2.color) && reporte1.zona.equals(reporte2.zona)){
                  String fecha1 = reporte1.fecha;
                  String fecha2 = reporte2.fecha;
                  boolean rangoFechas = ReporteController.diferenciaDias(fecha1, fecha2);
                  if (rangoFechas = true){
                  resultado.add(reportes.get(i));
                  resultado.add(reportes.get(j));
                  contador +=1; 
                  System.out.println("-------------------------------------------------------------------------");
                  break;
                  }
              }
          }
      }
  }
    return resultado;  
  }
  /**
   * Función que indica cuales fechas cumplen con 7 días de diferencia
   * Para esta función se requirio información de la siguiente pagina web:
   * Referencia Bibliográfica
   * Labex.io. Recuperado el 28 de septiembre de 2025
   * https://labex.io/es/tutorials/java-how-to-use-chronounit-for-date-operations-in-java-414155
   * @param fechaSTR
   * @return Lista con reportes con 7 días de diferencias de fecha digitada por el usuario
   */
  public List<Reporte> Diferencia7Dias(String fechaSTR) {
     List<Reporte> resultado = new ArrayList<>();
     DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
     LocalDate fecha = LocalDate.parse(fechaSTR, formato); //La fecha ingresada se pasa a formato fecha
     
     for (Reporte i : this.reportes ){
         String fecha2 = i.getFecha();
         LocalDate fechaAComparar = LocalDate.parse(fecha2, formato);
         //Se usa long ya que el método ChronotUnit devuelve long
         long dias= ChronoUnit.DAYS.between(fecha, fechaAComparar);
         //Para cuando la fechas son menores a la de comparación, devuelve negativos
         int rango = (int)Math.abs(dias); 
         if( rango <= 7) {
             resultado.add(i);
      }
     }
    return resultado; 
  }
  public static boolean diferenciaDias(String fecha1, String fecha2){
      List<Reporte> resultado = new ArrayList<>();
      DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate fecha1Comparar = LocalDate.parse(fecha1, formato); //La fecha ingresada se pasa a formato fecha
      LocalDate fecha2Comparar = LocalDate.parse(fecha2, formato); //La fecha ingresada se pasa a formato fecha
      //Se usa long ya que el método ChronotUnit devuelve long
      long dias= ChronoUnit.DAYS.between(fecha1Comparar, fecha2Comparar);
      //Para cuando la fechas son menores a la de comparación, devuelve negativos, por eso abs
         int rango = (int)Math.abs(dias); 
         if( rango <= 7) {
             return true;
         } else{
             return false; 
         }
  }
    
}
