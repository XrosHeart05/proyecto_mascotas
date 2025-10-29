package main;

import java.util.Arrays;
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

  public static Reporte nuevoReporte(ReporteController reporteController) {
    System.out.println(Menu.nuevoReporteCabecera());
    Mascota mascota = null;

    // Tipo de reporte
    System.out.println("\nIngrese el tipo de reporte que desea:");
    List<String> tipoReportePermitidos = Arrays.asList("PDR", "ENC");
    String tipoReporteEscogido = InputController.inputRestrict(tipoReportePermitidos);

    // ID del reporte
    System.out.print("\nIngrese ID del reporte (ejemplo: REP-0001): ");
    String usuarioReporteId = InputController.inputReporteId();
    boolean idUsable = reporteController.existeId(usuarioReporteId);
    if (!idUsable) {
      return null;
    }

    // ID del reportante
    System.out.println("\nIngrese ID del reportante (1-1111-1111): ");
    String reportanteId = InputController.inputID();

    // Nombre completo
    System.out.println("\nIngrese su nombre completo: ");
    String nombreCompleto = InputController.inputStringMinimo(7);

    // Fecha
    System.out.println("\nIngresa la fecha (dd/mm/yyyy) o presione Enter para colocar la fecha actual: ");
    String fecha = InputController.inputFecha();

    // Zona
    System.out.println("\nIngrese zona: ");
    String zona = InputController.inputStringMaximo(30);

    // Tipo de especie
    System.out.println("\nIngrese el tipo de especie: ");
    List<String> especiesPermitidas = Arrays.asList("DOG", "CAT");
    String especieEscogida = InputController.inputRestrict(especiesPermitidas);

    // Datos generales de mascota
    // Color
    System.out.println("\nIngrese el color del " + especieEscogida + ": ");
    String color = InputController.inputString();

    // Señas
    System.out.println("\nIngrese las señas del " + especieEscogida + ": ");
    String sennas = InputController.inputStringRango(10, 100);

    // Microchip
    System.out.println("\nIngrese microchip (opcional, deje en blanco si no tiene): ");
    String microchip = InputController.inputVacio();

    // Gato
    String raza, tipoPelaje, talla;
    boolean estaEsterelizado, tieneCollar;
    if ("CAT".equals(especieEscogida)) {
      // Raza del gato
      System.out.println("\nIngrese la raza del " + especieEscogida + ": ");
      raza = InputController.inputString();

      // Tipo de pelaje del gato
      System.out.println("\nIngrese el tipo de pelaje del " + especieEscogida + ": ");
      List<String> pelajesPermitidos = Arrays.asList("CORTO", "LARGO");
      tipoPelaje = InputController.inputRestrict(pelajesPermitidos);

      // Si el gato está esterelizado o no
      System.out.println("\nIngrese si el " + especieEscogida + " se encuentra esterelizado o no: ");
      estaEsterelizado = InputController.inputSiNo();

      mascota = new Gato(raza, tipoPelaje, estaEsterelizado, especieEscogida, color, sennas, microchip);
    } // PERRO
    else if ("DOG".equals(especieEscogida)) {
      // Raza del perro
      System.out.println("\nIngrese la raza del " + especieEscogida + ": ");
      raza = InputController.inputString();

      // Talla del perro
      System.out.println("\nIngrese la talla del " + especieEscogida + ": ");
      List<String> tallasPermitidas = Arrays.asList("PEQ", "MED", "GRA");
      talla = InputController.inputRestrict(tallasPermitidas);

      // Si el perro tiene collar o no
      System.out.println("\nIngrese si el " + especieEscogida + " tiene collar o no: ");
      tieneCollar = InputController.inputSiNo();

      mascota = new Perro(raza, talla, tieneCollar, especieEscogida, color, sennas, microchip);
    }

    // Número de teléfono
    System.out.println("\nIngrese el número de teléfono: ");
    String contacto = InputController.inputTelefono();

    // Validaciones tipo de reporte
    if ("PDR".equals(tipoReporteEscogido)) {
      return new ReportePerdida(usuarioReporteId, reportanteId, nombreCompleto, fecha, zona, contacto, mascota);
    } else if ("ENC".equals(tipoReporteEscogido)) {
      return new ReporteEncontrada(usuarioReporteId, reportanteId, nombreCompleto, fecha, zona, contacto, mascota);
    }

    // Caso error
    return null;
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
      reporteController.actualizarReporte(reporte, 0);
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
      return null;
    }
    return reportes;
  }

  public static Reporte sugerirCoincidenciasMenu() {
    System.out.println("\nQué tipo de reporte es? \n1: Perdido\n2: Encontrado");
    int opcion = InputController.inputIntRango(1, 2);
    Reporte reporte;
    if (opcion == 1) {
      reporte = new ReportePerdida();
    } else {
      reporte = new ReporteEncontrada();
    }

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
