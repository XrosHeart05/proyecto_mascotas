package main;

import java.util.List;

public class Menu {
  // Clase que busca imprimir menus

  public static String centrarTexto(String texto, int largo) {
    int espacios = (largo - texto.length()) / 2;
    return " ".repeat(espacios) + texto;
  }

  public static String menuPrincipal() {
    return menuPrincipalCabecera() + menuPrincipalCuerpo();
  }

  public static String menuPrincipalCabecera() {
    String texto = "";
    int largo = 45;
    String linea1 = "SISTEMA - REFUGIO \"HUELLAS FELICES\"";
    String linea2 = "GESTOR DE REPORTES DE MASCOTAS";
    texto += "=".repeat(largo) + "\n";
    texto += centrarTexto(linea1, largo) + "\n";
    texto += centrarTexto(linea2, largo) + "\n";
    texto += "=".repeat(largo) + "\n";

    return texto;
  }

  public static String menuPrincipalCuerpo() {
    String texto = "";
    int largo = 45;
    texto += "1. Registrar mascota desaparecida\n";
    texto += "2. Consultar por ID / Especie / Zona\n";
    texto += "3. Reporte general\n";
    texto += "4. Reporte agrupado\n";
    texto += "5. Ver coincidencias\n";
    texto += "6. Actualizar reporte\n";
    texto += "7. Salir\n";
    texto += "-".repeat(largo) + "\n";
    texto += "Ingrese una opción:\n";

    return texto;
  }

  public static String nuevoReporteCabecera() {
    String texto = "";
    int largo = 45;
    texto += "-".repeat(largo) + "\n";
    texto += centrarTexto("REGISTRAR NUEVO REPORTE", largo) + "\n";
    texto += "=".repeat(largo) + "\n";

    return texto;
  }

  public static ReportePerdida nuevoReporte(ReporteController reporteController) {
    System.out.println(Menu.nuevoReporteCabecera());
    ReportePerdida reporte = new ReportePerdida();
    Mascota mascota = null;

    System.out.print("\nIngrese ID del reporte (ejemplo: REP-0001): ");
    String usuarioReporteId = InputController.inputReporteId();
    boolean idUsable = reporteController.existeId(usuarioReporteId);
    if (idUsable) {
      reporte.setId(usuarioReporteId);
    } else {
      return null;
    }
    
    //Pedir el id del reportante
    System.out.println("\nIngrese ID del reportante (1-1111-1111): ");
    
    reporte.setReportanteId(InputController.inputID());

    //--------------------------------------------------------------------------
    
    //Pedir nombre completo
    System.out.println("\nIngrese nombre completo: ");
    reporte.setNombreCompleto(InputController.inputStringMinimo(7));

    //--------------------------------------------------------------------------
    
    //Pedir tipo de reporte(PDR por defecto)
    System.out.println("\nTipo de reporte (PDR/ENC) [PDR por defecto]: ");
    

    //--------------------------------------------------------------------------
    
    //Pedir fecha
    System.out.println("\nIngresa la fecha (dd/mm/yyyy): ");
    reporte.setFecha(InputController.inputFecha());
    //--------------------------------------------------------------------------
    
    //ingresar zona
    System.out.println("\nIngrese zona: ");
    reporte.setZona(InputController.inputStringMaximo(30));
    //---------------------------------------------------------------------------
   
    //Pedir tipo de especie
    System.out.println("\nIngrese especie (DOG/CAT): ");
    String especieEscogida = InputController.inputEspecie();
    if ("CAT".equals(especieEscogida)) {
      // Pedir datos de gato
      System.out.println("\nIngrese la raza del gato: ");
      String raza = InputController.inputString();
      
      mascota = new Gato(raza, "", false, "CAT", "Naranja", "Ninguna", "ABC");
      System.out.println("\n");
    } else if ("DOG".equals(especieEscogida)) {
      // Pedir datos de perro
    }
    // Settear mascota
    reporte.setMascota(mascota);
    // Ya la mascota no sería nula
    reporte.getMascota().setEspecie(InputController.inputEspecie());
    //--------------------------------------------------------------------------
    
    //Ingresar Color de mascota
    System.out.println("\nIngrese color principal: ");
    reporte.getMascota().setColor(InputController.inputString());
    //--------------------------------------------------------------------------
    
    //Ingresar senas particulares
    System.out.println("\nIngrese señas particulares (mínimo 10 caracteres): ");
    reporte.getMascota().setSennas(InputController.inputStringRango(10, 100));
    //--------------------------------------------------------------------------

    //Pedir número de teléfono

    reporte.setContacto(InputController.inputTelefono());
    //--------------------------------------------------------------------------

    System.out.println("\nIngrese microchip (opcional, deje en blanco si no tiene): ");
    reporte.getMascota().setMicrochip(InputController.inputVacio());

    return reporte;
  }

  public static String actualizarReporteCabecera() {
    String texto = "";
    int largo = 45;
    texto += "-".repeat(largo) + "\n";
    texto += centrarTexto("ACTUALIZAR ESTADO DE  REPORTE", largo) + "\n";
    texto += "=".repeat(largo) + "\n";

    return texto;
  }

  public static Reporte actualizarReporte(ReporteController reporteController) {
    System.out.println(Menu.actualizarReporteCabecera());

    System.out.print("\nIngrese ID del reporte (No editable): ");
    String reporteId = InputController.inputReporteId();

    List<Reporte> coincidenciasPorId = reporteController.obtenerReportesPorId(reporteId);
    Reporte reporte = null;
    if (!coincidenciasPorId.isEmpty()) {
      for (Reporte i : coincidenciasPorId) {
        System.out.println("Reporte encontrado: ");
        reporteController.imprimirReporte(i);
        reporte = i;
        break;
      }
    } else {
      return null;
    }

    System.out.println("Seleccione nueva acción: ");
    System.out.println("1. Editar un solo dato");
    System.out.println("2. Reingresar todos los datos\n");
    System.out.print("Ingrese opción: ");
    int accion = InputController.inputIntRango(1, 2);

    int accion2;
    if (accion == 1) {
      System.out.println("Que dato desea editar?");
      System.out.println("1. Nombre completo");
      System.out.println("2. Tipo de Reporte (PDR/ENC)");
      System.out.println("3. Zona");
      System.out.println("4. Especie (DOG/CAT)");
      System.out.println("5. Color principal");
      System.out.println("6. Señas particulares");
      System.out.println("7. Teléfono de contacto");
      System.out.println("8. Microchip\n");
      System.out.print("Ingrese una opción: ");
      accion2 = InputController.inputIntRango(1, 8);
      reporteController.actualizarReporte(reporte, accion2);
    } else {
      // reporteController.actualizarReporte(reporte, 0);
      return null;
    }

    return reporte;
  }

  public static String buscarReporteCabecera() {
    String texto = "";
    int largo = 45;
    texto += "-".repeat(largo) + "\n";
    texto += centrarTexto("CONSULTA DE REPORTES POR CRITERIO", largo) + "\n";
    texto += "=".repeat(largo) + "\n";
    texto += "Seleccione criterio de búsqueda: \n";
    texto += "1. ID del reportante\n";
    texto += "2. Especie\n";
    texto += "3. Zona\n";
    texto += "Ingrese opción: ";

    return texto;
  }

  public static List<Reporte> buscarReporte(ReporteController reporteController) {
    int opcion;
    System.out.println(Menu.buscarReporteCabecera());
    opcion = InputController.inputIntRango(1, 3);

    List<Reporte> reportes = reporteController.buscarReportes(opcion);
    if (reportes.isEmpty()) {
      System.out.println("No hay reportes que coincidan");
    }
    return reportes;
  }

  public static Reporte sugerirCoincidenciasMenu() {
    Reporte reporte = new Reporte();
    System.out.println("Ingrese la especie (DOG/CAT): ");
    String especie = InputController.inputString();
    if ("CAT".equals(especie)) {
      reporte.getMascota().setEspecie("CAT");
    } else {
      reporte.getMascota().setEspecie("DOG");
    }

    System.out.println("Ingrese el color de la especie:");
    reporte.getMascota().setColor(InputController.inputString());

    System.out.println("Ingrese la zona donde fue vista la especie:");
    reporte.setZona(InputController.inputString());

    return reporte;
  }

}
