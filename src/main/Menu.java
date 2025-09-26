package main;

import java.util.ArrayList;
import java.util.List;

public class Menu {
  // Clase que busca imprimir menus

  public static String centerText(String text, int large) {
    int spaces = (large - text.length()) / 2;
    return " ".repeat(spaces) + text;
  }

  public static String mainMenu() {
    return mainMenuHeader() + mainMenuBody();
  }

  public static String mainMenuHeader() {
    String text = "";
    int large = 45;
    String line1 = "SISTEMA - REFUGIO \"HUELLAS FELICES\"";
    String line2 = "GESTOR DE REPORTES DE MASCOTAS";
    text += "=".repeat(large) + "\n";
    text += centerText(line1, large) + "\n";
    text += centerText(line2, large) + "\n";
    text += "=".repeat(large) + "\n";

    return text;
  }

  public static String mainMenuBody() {
    String text = "";
    int large = 45;
    text += "1. Registrar mascota desaparecida\n";
    text += "2. Consultar por ID / Especie / Zona\n";
    text += "3. Reporte general\n";
    text += "4. Reporte agrupado\n";
    text += "5. Ver coincidencias\n";
    text += "6. Actualizar reporte\n";
    text += "7. Salir\n";
    text += "-".repeat(large) + "\n";
    text += "Ingrese una opción:\n";

    return text;
  }

  public static String newReportHeader() {
    String text = "";
    int large = 45;
    text += "-".repeat(large) + "\n";
    text += centerText("REGISTRAR NUEVO REPORTE", large) + "\n";
    text += "=".repeat(large) + "\n";

    return text;
  }

  public static Report newReport() {
    System.out.println(Menu.newReportHeader());
    Report report = new Report();
    System.out.print("\nIngrese ID del reporte (ejemplo: REP-0001): ");
    // TODO:  identificador único del reporte, ingresado por el usuario. 
    // Formato obligatorio prefijo REP- cuatro dígitos, comenzado por 1 
    // (ejemplo: REP - 0001, REP - 0002). El sistema debe validar que el ID no exista; 
    // en caso de duplicado, solicitar uno nuevo
    report.setId(InputController.inputString());
    System.out.println("\nIngrese ID del reportante (1-1111-1111): ");
    // TODO: cédula costarricense en el formato 1-1111-1111.
    // En el input Controller debes crear un inputString nuevo que cada ciertos 
    // caracteres solicite un guión, además de una longitud definida
    report.setReporterId(InputController.inputString());
    System.out.println("\nIngrese nombre completo: ");
    // TODO: mínimo 7 caracteres; se permiten espacios.
    report.setFullName(InputController.inputString());
    System.out.println("\nTipo de reporte (PDR/ENC) [PDR por defecto]: ");
    // TODO: PDR o ENC (3 letras). PDR = Perdida (valor asignado por defecto al registrar una
    // nueva mascota). ENC = Encontrada (se selecciona cuando el usuario desea actualizar un
    // hallazgo).
    // Ajustar a que solo solicite el enum de tipo de reporte
    // report.setReportType(InputController.inputString());

    System.out.println("\nIngresa la fecha (dd/mm/yyyy): ");
    // TODO: 
    // Fecha del reporte: se obtiene automáticamente del sistema (No obstante, el usuario podrá ingresar una
    // fecha manual en el formato dd/mm/yyyy. Si el campo se deja en blanco, el sistema asignará la fecha
    // automáticamente).
    // Crear nuevo inputString para longitud determinada y además que cada ciertos caracteres solicite slashes /
    // input similar al de la cedula
    report.setDate(InputController.inputString());

    System.out.println("\nIngrese zona: ");
    // TODO: Ajustar input para que solo solicite máximo; sino dejar eso, creo que está bien
    report.setZone(InputController.inputStringRange(0, 30));

    System.out.println("\nIngrese especie (DOG/CAT): ");
    // TODO: Crear nuevo inputString para que solicite el enum
    // report.setSpecies(InputController.inputString());

    System.out.println("\nIngrese color principal: ");
    report.setColor(InputController.inputString());

    System.out.println("\nIngrese señas particulares (mínimo 10 caracteres): ");
    // TODO: Crear nuevo inputString para que solo solicite el mínimo
    report.setParticularSigns(InputController.inputStringRange(10, 100));

    System.out.println("\nIngrese teléfono de contacto (####-####): ");
    // TODO: Crear input con el formato ####-####.
    report.setContactNumber(InputController.inputString());

    System.out.println("\nIngrese microchip (opcional, deje en blanco si no tiene): ");
    // TODO: Crear input que admita vacíos
    report.setMicrochip(InputController.inputString());

    return report;
  }

  public static String searchReportHeader() {
    String text = "";
    int large = 45;
    text += "-".repeat(large) + "\n";
    text += centerText("CONSULTA DE REPORTES POR CRITERIO", large) + "\n";
    text += "=".repeat(large) + "\n";
    text += "Seleccione criterio de búsqueda: \n";
    text += "1. ID del reportante\n";
    text += "2. Especie\n";
    text += "3. Zona\n";
    text += "Ingrese opción: ";

    return text;
  }

  public static List<Report> searchReport() {
    ReportController reportController = new ReportController(new ArrayList<>());
    int option;
    String query = "";
    System.out.println(Menu.searchReportHeader());
    option = InputController.inputIntRange(1, 3);
    
    List<Report> reports = reportController.searchReports(option, query);

    return reports;
  }
}
