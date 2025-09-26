package main;

import java.util.ArrayList;
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

  public static Reporte nuevoReporte() {
    System.out.println(Menu.nuevoReporteCabecera());
    Reporte reporte = new Reporte();
    System.out.print("\nIngrese ID del reporte (ejemplo: REP-0001): ");
    // TODO:  identificador único del reporte, ingresado por el usuario. 
    // Formato obligatorio prefijo REP- cuatro dígitos, comenzado por 1 
    // (ejemplo: REP - 0001, REP - 0002). El sistema debe validar que el ID no exista; 
    // en caso de duplicado, solicitar uno nuevo
    reporte.setId(InputController.inputString());
    System.out.println("\nIngrese ID del reportante (1-1111-1111): ");
    // TODO: cédula costarricense en el formato 1-1111-1111.
    // En el input Controller debes crear un inputString nuevo que cada ciertos 
    // caracteres solicite un guión, además de una longitud definida
    reporte.setReportanteId(InputController.inputString());
    System.out.println("\nIngrese nombre completo: ");
    // TODO: mínimo 7 caracteres; se permiten espacios.
    reporte.setNombreCompleto(InputController.inputString());
    System.out.println("\nTipo de reporte (PDR/ENC) [PDR por defecto]: ");
    // TODO: PDR o ENC (3 letras). PDR = Perdida (valor asignado por defecto al registrar una
    // nueva mascota). ENC = Encontrada (se selecciona cuando el usuario desea actualizar un
    // hallazgo).
    // Ajustar a que solo solicite el enum de tipo de reporte
    // reporte.setReportType(InputController.inputString());

    System.out.println("\nIngresa la fecha (dd/mm/yyyy): ");
    // TODO: 
    // Fecha del reporte: se obtiene automáticamente del sistema (No obstante, el usuario podrá ingresar una
    // fecha manual en el formato dd/mm/yyyy. Si el campo se deja en blanco, el sistema asignará la fecha
    // automáticamente).
    // Crear nuevo inputString para longitud determinada y además que cada ciertos caracteres solicite slashes /
    // input similar al de la cedula
    reporte.setFecha(InputController.inputString());

    System.out.println("\nIngrese zona: ");
    // TODO: Ajustar input para que solo solicite máximo; sino dejar eso, creo que está bien
    reporte.setZona(InputController.inputStringRango(0, 30));

    System.out.println("\nIngrese especie (DOG/CAT): ");
    // TODO: Crear nuevo inputString para que solicite el enum
    // reporte.setSpecies(InputController.inputString());

    System.out.println("\nIngrese color principal: ");
    reporte.setColor(InputController.inputString());

    System.out.println("\nIngrese señas particulares (mínimo 10 caracteres): ");
    // TODO: Crear nuevo inputString para que solo solicite el mínimo
    reporte.setSenasParticulares(InputController.inputStringRango(10, 100));

    System.out.println("\nIngrese teléfono de contacto (####-####): ");
    // TODO: Crear input con el formato ####-####.
    reporte.setContacto(InputController.inputString());

    System.out.println("\nIngrese microchip (opcional, deje en blanco si no tiene): ");
    // TODO: Crear input que admita vacíos
    reporte.setMicrochip(InputController.inputString());

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

  public static List<Reporte> buscarReporte() {
    ReporteController reporteController = new ReporteController(new ArrayList<>());
    int opcion;
    String consulta = "";
    System.out.println(Menu.buscarReporteCabecera());
    opcion = InputController.inputIntRango(1, 3);
    
    List<Reporte> reportes = reporteController.buscarReportes(opcion, consulta);

    return reportes;
  }
}
