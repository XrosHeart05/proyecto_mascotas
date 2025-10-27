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

  public boolean existeId(String id) {
    boolean esValido = true;
    for (Reporte i : this.reportes) {
      if (id.equals(i.getId())) {
        System.out.println("El ID del reporte ya existe, intente con otro ID");
        esValido = false;
      }
    }
    return esValido;
  }

  public void crearReporte(Reporte _reporte) {
    reportes.add(_reporte);
  }

  // Regresa el reporte por identificador
  // Puede devolver un objeto o estar vacío.
  // No devuelve null
  public List<Reporte> obtenerReportesPorId(String id) {
    // Stream es un método propio de los ArrayList o List
    // Genera un Stream (flujo) de objetos
    // Filter aplica como su nombre indica filtros a dichos objetos(colador de datos)
    return reportes.stream().filter(i -> i.getId().equals(id)).toList();
  }

  /**
   * Devuelve una lista de reportes que tengan la misma especie
   *
   * @param especie
   * @return reportes
   */
  public List<Reporte> obtenerReportePorEspecie(String especie) {
    String _especie;
    if ("DOG".equals(especie)) {
      _especie = "DOG";
    } else {
      _especie = "CAT";
    }

    return reportes
      .stream()
      .filter(i -> i.getMascota().getEspecie().equals(_especie))
      .toList();
  }

  /**
   * Función que devuelve una lista de reportes que tengan la misma zona
   *
   * @param zona
   * @return reportes
   */
  public List<Reporte> obtenerReportePorZona(String zona) {
    return reportes.stream().filter(i -> i.getZona().equals(zona)).toList();
  }

  /**
   * Devuelve una lista de reportes de acuerdo a lo que digita el usuario 1)
   * Reportes de acuerdo al ID 2) Reportes de acuerdo a la especie 3) Reportes
   * de acuerdo a la zona
   *
   * @param opcion
   * @return resultado
   */
  public List<Reporte> buscarReportes(int opcion) {
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
   *
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
      System.out.println(r.getMascota().getEspecie() + " ");
    }
  }

  /**
   * Imprime un reporte
   *
   * @param reporte
   */
  public void imprimirReporte(Reporte reporte) {
    System.out.println("ID Reporte: " + reporte.getId());
    System.out.println("ID Reportante: " + reporte.getReportanteId());
    System.out.println("Nombre: " + reporte.getNombreCompleto());
    if ("PDR".equals(reporte.getTipo())) {
      System.out.println("Tipo: PDR (Perdida)");
    } else {
      System.out.println("Tipo: ENC (Encontrada)");
    }
    System.out.println("Fecha: " + reporte.getFecha());
    System.out.println("Zona: " + reporte.getZona());
    System.out.println("Especie: " + reporte.getMascota().getEspecie());
    System.out.println("Color: " + reporte.getMascota().getColor());
    System.out.println("Señas: " + reporte.getMascota().getSennas());
    System.out.println("Teléfono: " + reporte.getContacto());
    System.out.println("Microchip: " + reporte.getMascota().getMicrochip());
  }

  /**
   * Suguiere coincidencias del parámetro ingresado
   *
   * @param _reporte
   * @return lista de reportes
   */
  public List<Reporte> sugerirCoincidencias(Reporte _reporte) {
    String reportante = _reporte.getNombreCompleto().toLowerCase();
    String color = _reporte.getMascota().getColor().toLowerCase();
    String tipoReporte = _reporte.getTipo();

    return reportes.stream()
      .filter(r -> r.getTipo() == _reporte.getTipo())
      // Otras coincidencias
      .filter(r -> r.getNombreCompleto().toLowerCase().contains(reportante))
      .filter(r -> r.getMascota().getColor().toLowerCase().contains(color))
      // Demás atributos a filtrar si se desea
      .toList();

  }

  public void actualizarReporte(Reporte _reporte, int accion) {
    switch (accion) {
      // Todos los datos:
      case 0:
        // Todos los datos
        break;

      // Nombre completo
      case 1:
        String nombre = InputController.inputString();
        _reporte.setNombreCompleto(nombre);
        break;

      // Tipo de reporte
      case 2:
        System.out.println("El tipo de reporte actual es:" + _reporte.getTipo());
        String tipoRep = InputController.inputReporte();
        if (!"PDR".equals(tipoRep) || !"ENC".equals(tipoRep)) {
          System.out.println("Solo se admiten 'PDR' o 'ENC'");
          break;
        }
        if ("PDR".equals(tipoRep)) {
          _reporte = new ReportePerdida(
            _reporte.getId(),
            _reporte.getReportanteId(),
            _reporte.getNombreCompleto(),
            _reporte.getFecha(),
            _reporte.getZona(),
            _reporte.getContacto(),
            _reporte.getMascota()
          );
        } else if ("ENC".equals(tipoRep)) {
          _reporte = new ReporteEncontrada(
            _reporte.getId(),
            _reporte.getReportanteId(),
            _reporte.getNombreCompleto(),
            _reporte.getFecha(),
            _reporte.getZona(),
            _reporte.getContacto(),
            _reporte.getMascota()
          );
        }
        break;

      // Zona
      case 3:
        String zona = InputController.inputString();
        _reporte.setZona(zona);
        break;

      // Especie
      case 4:
        String tipoEsp = InputController.inputEspecie();
        _reporte.getMascota().setEspecie(tipoEsp);
        break;

      // Color principal
      case 5:
        String color = InputController.inputString();
        _reporte.getMascota().setColor(color);
        break;

      // Señas
      case 6:
        String senas = InputController.inputString();
        _reporte.getMascota().setSennas(senas);
        break;

      // Contacto
      case 7:
        String contacto = InputController.inputTelefono();
        _reporte.setContacto(contacto);
        break;

      // Micro
      case 8:
        String micro = InputController.inputVacio();
        _reporte.getMascota().setMicrochip(micro);
        break;
      default:
        System.out.println("Error en actualización");
        break;
    }
  }

  public void reporteAgrupado() {
    int contadorGatos = 0, contadorPerros = 0, contadorPDR = 0, contadorENC = 0;
    for (Reporte i : this.reportes) {
      if ("CAT".equals(i.getMascota().getEspecie())) {
        contadorGatos += 1;
      } else {
        contadorPerros += 1;
      }

      if (i.getTipo().equals("ENC")) {
        contadorENC += 1;
      } else if (i.getTipo().equals("PDR")) {
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

  public List<Reporte> reportesCoincidencias() {
    List<Reporte> resultado = new ArrayList<>();
    int contador = 0;
    for (int i = 0; i < this.reportes.size(); i++) {
      Reporte reporte1 = this.reportes.get(i);
      // Si está PDR
      if (reporte1.getTipo() == "PDR") {
        for (int j = i + 1; j < this.reportes.size(); j++) {
          Reporte reporte2 = this.reportes.get(j);

          //Aqui se empieza a hacer las comparaciones para coincidencias
          if (!reporte1.getMascota().getMicrochip().isEmpty()
            && !reporte2.getMascota().getMicrochip().isEmpty()
            && reporte1.getMascota().getMicrochip().equals(reporte2.getMascota().getMicrochip())) {
            resultado.add(this.reportes.get(i));
            resultado.add(this.reportes.get(j));
            contador += 1;
            break;
          }

          if (reporte1.getMascota().getEspecie().equals(reporte2.getMascota().getEspecie())
            && reporte1.getMascota().getColor().equals(reporte2.getMascota().getColor())
            && reporte1.zona.equals(reporte2.zona)) {
            String fecha1 = reporte1.fecha;
            String fecha2 = reporte2.fecha;
            boolean rangoFechas = diferenciaDias(fecha1, fecha2);
            if (rangoFechas) {
              resultado.add(this.reportes.get(i));
              resultado.add(this.reportes.get(j));
              contador += 1;
              break;
            }
          }
        }
      }
    }
    return resultado;
  }

  /**
   * Función que indica cuales fechas cumplen con 7 días de diferencia Para esta
   * función se requirio información de la siguiente pagina web: Referencia
   * Bibliográfica Labex.io. Recuperado el 28 de septiembre de 2025
   * https://labex.io/es/tutorials/java-how-to-use-chronounit-for-date-operations-in-java-414155
   *
   * @param fecha1 String con la fecha a comparar
   * @param fecha2 String con la fecha a comparar
   * @return booleano que indica si están dentro del rango de 7 días o no
   */
  public static boolean diferenciaDias(String fecha1, String fecha2) {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate fecha1Comparar = LocalDate.parse(fecha1, formato); //La fecha ingresada se pasa a formato fecha
    LocalDate fecha2Comparar = LocalDate.parse(fecha2, formato); //La fecha ingresada se pasa a formato fecha
    //Se usa long ya que el método ChronotUnit devuelve long
    long dias = ChronoUnit.DAYS.between(fecha1Comparar, fecha2Comparar);
    //Para cuando la fechas son menores a la de comparación, devuelve negativos, por eso abs
    int rango = (int) Math.abs(dias);
    if (rango <= 7) {
      return true;
    }
    return false;
  }

}
