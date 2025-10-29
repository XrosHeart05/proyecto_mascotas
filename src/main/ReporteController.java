package main;

import java.io.*;
import java.util.*;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReporteController {

  // Listado que solo admite objetos Reporte
  // Sirve para agregar nuevos reportes
  public final List<Reporte> reportes;
  private final String archivoPerdidas = "reporte_perdidas.txt";
  private final String archivoEncontradas = "reporte_encontradas.txt";

  // Constructor
  public ReporteController() {
    this.reportes = new ArrayList<>();
    sincronizarArchivos();
  }

  /**
   * Invoca los métodos de carga de archivos
   */
  private void sincronizarArchivos() {
    reportes.clear();
    cargarArchivo(archivoPerdidas, true);
    cargarArchivo(archivoEncontradas, false);
  }

  /**
   * Apertura archivos txt, los lee línea por línea y separa cada una de sus
   * "columnas" por medio del caracter "|" Transforma los archivos txt en
   * objetos de tipo Reporte para su posterior adición al listado de reportes
   *
   * @param archivo Nombre del archivo
   * @param esPerdida Si se trata del archivo de perdidos o no
   */
  private void cargarArchivo(String archivo, boolean esPerdida) {
    // Instancia de objetos para lectura de archivos
    File file = new File(archivo);
    Reporte reporte;
    BufferedReader bufferedReader = null;

    // Valida si existe el archivo o no
    if (!file.exists()) {
      try {
        if (file.createNewFile()) {
          System.out.println("Archivo creado: " + archivo);
        } else {
          System.out.println("No se pudo crear el archivo: " + archivo);
        }
      } catch (IOException e) {
        System.out.println("Error al crear el archivo " + archivo + ". Error: " + e.getMessage());
      }
    }

    // Archivo vacío, nada que cargar
    if (file.length() == 0) {
      return;
    }

    try {
      // Lectura de archivo
      bufferedReader = new BufferedReader(new FileReader(file));
      String linea;

      // Línea por línea
      while ((linea = bufferedReader.readLine()) != null) {
        // Corte de cada línea por el caracter "|"
        String[] datos = linea.split("\\|");
        // Determinar que especie es
        String especie = datos[6];
        boolean estaEsterelizado = false, tieneCollar = false;

        // Rellenado de datos de la mascota
        Mascota mascota;
        if ("CAT".equals(especie)) {
          if ("si".equals(datos[12])) {
            estaEsterelizado = true;
          }
          mascota = new Gato(datos[10], datos[11], estaEsterelizado, especie, datos[7], datos[8], datos[9]);
        } else {
          if ("si".equals(datos[12])) {
            tieneCollar = true;
          }
          mascota = new Perro(datos[10], datos[11], tieneCollar, especie, datos[7], datos[8], datos[9]);
        }

        // Rellenado de los datos de cada reporte
        if (esPerdida) {
          reporte = new ReportePerdida(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], mascota);
        } else {
          reporte = new ReporteEncontrada(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], mascota);
        }

        this.reportes.add(reporte);
      }

    } catch (IOException e) {
      System.out.println("Error al leer el archivo " + archivo + ". Error: " + e.getMessage());
    } finally {
      try {
        if (bufferedReader != null) {
          // Cerrar lectura de archivo
          bufferedReader.close();
        }
      } catch (IOException e) {
        System.out.println("Error al cerrar el lector del archivo " + archivo + ". Error: " + e.getMessage());
      }
    }
  }

  /**
   * Agrega los reportes al listado de reportes e invoca el método de
   * almacenamiento de datos al archivo de txt correspondiente
   *
   * @param _reporte
   */
  public void crearReporte(Reporte _reporte) {
    reportes.add(_reporte);
    almacenarReporte(_reporte);
  }

  /**
   * Desfragmenta un reporte en string para almacenarse en el txt
   * correspondiente
   *
   * @param reporte Reporte a almacenar
   */
  private void almacenarReporte(Reporte reporte) {
    FileWriter fileWriter = null;
    BufferedWriter bufferedWriter = null;
    PrintWriter printWriter = null;

    String nombreArchivo;
    // Validación de tipo de reporte
    if ("PDR".equals(reporte.getTipo())) {
      nombreArchivo = archivoPerdidas;
    } else {
      nombreArchivo = archivoEncontradas;
    }

    // Escritura en archivo
    try {
      // Inicialización de objetos para escritura de archivo
      // true para modo append
      fileWriter = new FileWriter(nombreArchivo, true);
      bufferedWriter = new BufferedWriter(fileWriter);
      printWriter = new PrintWriter(bufferedWriter);

      Mascota mascota = reporte.getMascota();
      // Escritura en archivo
      printWriter.println(String.join("|",
        reporte.getId(),
        reporte.getReportanteId(),
        reporte.getNombreCompleto(),
        reporte.getFecha(),
        reporte.getZona(),
        reporte.getContacto(),
        mascota.getEspecie(),
        mascota.getColor(),
        mascota.getSennas(),
        mascota.getMicrochip(),
        mascota.toPrint()
      ));

    } catch (IOException e) {
      System.out.println("Error al guardar el reporte en el archivo " + nombreArchivo + ". Error: " + e.getMessage());
    } finally {
      try {
        if (printWriter != null) {
          printWriter.close();
        }
        if (bufferedWriter != null) {
          bufferedWriter.close();
        }
        if (fileWriter != null) {
          fileWriter.close();
        }

      } catch (IOException e) {
        System.out.println("Error al cerrar el archivo " + nombreArchivo + ". Error: " + e.getMessage());
      }
    }

  }

  /**
   * Regresa el reporte por identificador Puede devolver un objeto o estar
   * vacío. No devuelve null
   *
   * @param id ID del reporte a buscar
   * @return Listado de reportes
   */
  public List<Reporte> obtenerReportesPorIdReportante(String id) {
    // Stream es un método propio de los ArrayList o List
    // Genera un Stream (flujo) de objetos
    // Filter aplica como su nombre indica filtros a dichos objetos(colador de datos)
    List<Reporte> reportesADevolver = reportes.stream().filter(i -> i.getReportanteId().trim().equals(id.trim())).toList();
    return reportesADevolver;
  }

  /**
   * Regresa el reporte por identificador Puede devolver un objeto o estar
   * vacío. No devuelve null
   *
   * @param id ID del reporte a buscar
   * @return Listado de reportes
   */
  public List<Reporte> obtenerReportesPorId(String id) {
    // Stream es un método propio de los ArrayList o List
    // Genera un Stream (flujo) de objetos
    // Filter aplica como su nombre indica filtros a dichos objetos(colador de datos)
    List<Reporte> reportesADevolver = reportes.stream().filter(i -> i.getId().trim().equals(id.trim())).toList();
    return reportesADevolver;
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
    return reportes.stream().filter(i -> i.getZona().toLowerCase().equals(zona)).toList();
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
        resultado = obtenerReportesPorIdReportante(consulta);
        break;

      // Especie
      // TODO: Aplicar filtro de especie
      case 2:
        System.out.println("\nIngrese la especie (DOG/CAT): ");
        consulta = InputController.inputString();
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
   * Devuelve una lista de reportes de acuerdo a lo que digita el usuario 1)
   * Reportes de acuerdo al ID 2) Reportes de acuerdo a la especie 3) Reportes
   * de acuerdo a la zona
   *
   * @param opcion
   * @param valor
   * @return resultado
   */
  public List<Reporte> buscarReportes2(int opcion, String valor) {
    List<Reporte> resultado = null;
    String consulta;

    switch (opcion) {
      // ID Reportante
      case 1:
        consulta = InputController.inputReporteIdInterfaz(valor);
        if (consulta != null) {
          resultado = obtenerReportesPorId(valor);
        } else {
          resultado = null;
        }
        break;

      // Especie
      case 2:
        List<String> permitidos = Arrays.asList("CAT", "DOG");
        consulta = InputController.inputRestrictInterfaz(permitidos, valor);
        if (consulta != null) {
          resultado = obtenerReportePorEspecie(consulta);
        } else {
          resultado = null;
        }
        break;
//
//      // Zona
//      // TODO: Aplicar filtro de zona
//      case 3:
//        System.out.println("\nIngrese la zona: ");
//        consulta = InputController.inputString().toLowerCase();
//        resultado = obtenerReportePorZona(consulta);
//        break;
//      default:
//        return new ArrayList<>();
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

    return reportes.stream()
      .filter(r -> r.getTipo().equals(_reporte.getTipo()))
      // Otras coincidencias
      .filter(r -> r.getNombreCompleto().toLowerCase().contains(reportante))
      .filter(r -> r.getMascota().getColor().toLowerCase().contains(color))
      // Demás atributos a filtrar si se desea
      .toList();

  }

  /**
   * Actualiza un reporte completo o solo ciertos valores de este
   *
   * @param reporte Reporte a actualizar
   * @param accion Indica si actualizar un reporte por completo o solo un dato
   * especifico
   */
  public void actualizarReporte(Reporte reporte, int accion) {
    Mascota mascota = null;
    List<String> especiesPermitidas = Arrays.asList("DOG", "CAT");
    List<String> pelajesPermitidos = Arrays.asList("CORTO", "LARGO");
    List<String> tallasPermitidas = Arrays.asList("PEQ", "MED", "GRA");

    if (reporte == null) {
      System.out.println("No se encontró el reporte");
      return;
    }

    switch (accion) {
      // Todos los datos:
      case 0:
        // Todos los datos
        System.out.println("Actualizando todos los datos");

        // ID del reportante
        System.out.println("\nDigite el ID del reportante:");
        reporte.setReportanteId(InputController.inputID());

        // Nombre completo
        System.out.println("\nDigite el nombre completo del reportante:");
        reporte.setNombreCompleto(InputController.inputStringMinimo(7));

        // Fecha
        System.out.println("\nDigite la fecha:");
        reporte.setFecha(InputController.inputFecha());

        // Zona
        System.out.println("\nDigite la zona:");
        reporte.setZona(InputController.inputStringMaximo(30));

        // Número de teléfono
        System.out.println("\nIngrese el número de teléfono: ");
        reporte.setContacto(InputController.inputTelefono());

        // Tipo de especie
        System.out.println("\nIngrese el tipo de especie:");
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
        String raza,
         tipoPelaje,
         talla;
        boolean estaEsterelizado,
         tieneCollar;
        if ("CAT".equals(especieEscogida)) {
          // Raza del gato
          System.out.println("\nIngrese la raza del " + especieEscogida + ": ");
          raza = InputController.inputString();

          // Tipo de pelaje del gato
          System.out.println("\nIngrese el tipo de pelaje del " + especieEscogida + ": ");
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
          talla = InputController.inputRestrict(tallasPermitidas);

          // Si el perro tiene collar o no
          System.out.println("\nIngrese si el " + especieEscogida + " tiene collar o no: ");
          tieneCollar = InputController.inputSiNo();

          mascota = new Perro(raza, talla, tieneCollar, especieEscogida, color, sennas, microchip);
        }

        reporte.setMascota(mascota);
        break;

      // Nombre completo
      case 1:
        System.out.println("\nDigite el nuevo nombre completo");
        String nombre = InputController.inputString();
        reporte.setNombreCompleto(nombre);
        break;

      // Tipo de reporte
      case 2:
        System.out.println("\nEl tipo de reporte actual es:" + reporte.getTipo());
        List<String> tiposPermitidos = Arrays.asList("PDR", "ENC");
        String tipoRep = InputController.inputRestrict(tiposPermitidos);

        if (tipoRep.equals(reporte.getTipo())) {
          System.out.println("Ya el reporte es del tipo " + reporte.getTipo());
          break;
        }

        String tipoOriginal = reporte.getTipo();

        if ("PDR".equals(tipoRep)) {
          reporte = new ReportePerdida(
            reporte.getId(),
            reporte.getReportanteId(),
            reporte.getNombreCompleto(),
            reporte.getFecha(),
            reporte.getZona(),
            reporte.getContacto(),
            reporte.getMascota()
          );
        } else if ("ENC".equals(tipoRep)) {
          reporte = new ReporteEncontrada(
            reporte.getId(),
            reporte.getReportanteId(),
            reporte.getNombreCompleto(),
            reporte.getFecha(),
            reporte.getZona(),
            reporte.getContacto(),
            reporte.getMascota()
          );
        }

        int indiceReporte = indicePorId(reporte.getId());
        if (indiceReporte != -1) {
          reportes.set(indiceReporte, reporte);
        }

        String archivoOriginal;
        if (tipoOriginal.equals("PDR")) {
          archivoOriginal = archivoPerdidas;
        } else {
          archivoOriginal = archivoEncontradas;
        }

        eliminarReporteDeArchivo(archivoOriginal, reporte.getId());

        almacenarReporte(reporte);

        System.out.println("Tipo de reporte actualizado correctamente a " + tipoRep);

        break;

      // Zona
      case 3:
        String zona = InputController.inputStringMaximo(30);
        reporte.setZona(zona);
        break;

      // Especie
      case 4:
        // Tipo de especie
        System.out.println("\nIngrese el tipo de especie: ");
        String especieAct = InputController.inputRestrict(especiesPermitidas);

        if (especieAct.equals(reporte.getMascota().getEspecie())) {
          System.out.println("Ya la mascota es de ese tipo de especie");
          break;
        }

        // Datos generales de mascota
        System.out.println("\nIngrese los datos restantes de la mascota");
        // Color
        System.out.println("\nIngrese el color del " + especieAct + ": ");
        String colorAct = InputController.inputString();

        // Señas
        System.out.println("\nIngrese las señas del " + especieAct + ": ");
        String sennasAct = InputController.inputStringRango(10, 100);

        // Microchip
        System.out.println("\nIngrese microchip (opcional, deje en blanco si no tiene): ");
        String microchipAct = InputController.inputVacio();

        // Gato
        String tipoPelajeAct,
         tallaAct;
        boolean estaEsterelizadoAct,
         tieneCollarAct;
        if ("CAT".equals(especieAct)) {
          // Raza del gato
          System.out.println("\nIngrese la raza del " + especieAct + ": ");
          String razaGatoAct = InputController.inputString();

          // Tipo de pelaje del gato
          System.out.println("\nIngrese el tipo de pelaje del " + especieAct + ": ");
          tipoPelajeAct = InputController.inputRestrict(pelajesPermitidos);

          // Si el gato está esterelizado o no
          System.out.println("\nIngrese si el " + especieAct + " se encuentra esterelizado o no: ");
          estaEsterelizadoAct = InputController.inputSiNo();

          mascota = new Gato(razaGatoAct, tipoPelajeAct, estaEsterelizadoAct, especieAct, colorAct, sennasAct, microchipAct);
        } // PERRO
        else if ("DOG".equals(especieAct)) {
          // Raza del perro
          System.out.println("\nIngrese la raza del " + especieAct + ": ");
          String razaPerroAct = InputController.inputString();

          // Talla del perro
          System.out.println("\nIngrese la talla del " + especieAct + ": ");
          tallaAct = InputController.inputRestrict(tallasPermitidas);

          // Si el perro tiene collar o no
          System.out.println("\nIngrese si el " + especieAct + " tiene collar o no: ");
          tieneCollarAct = InputController.inputSiNo();

          mascota = new Perro(razaPerroAct, tallaAct, tieneCollarAct, especieAct, colorAct, sennasAct, microchipAct);
        }

        reporte.setMascota(mascota);
        System.out.println("Mascota actualizada");
        break;

      // Color principal
      case 5:
        String soloColorAct = InputController.inputString();
        reporte.getMascota().setColor(soloColorAct);
        break;

      // Señas
      case 6:
        String soloSennasAct = InputController.inputString();
        reporte.getMascota().setSennas(soloSennasAct);
        break;

      // Contacto
      case 7:
        String soloContactoAct = InputController.inputTelefono();
        reporte.setContacto(soloContactoAct);
        break;

      // Micro
      case 8:
        String soloMicroAct = InputController.inputVacio();
        reporte.getMascota().setMicrochip(soloMicroAct);
        break;
      default:
        System.out.println("Error en actualización");
        break;
    }

    int indice = indicePorId(reporte.getId());
    if (indice != -1) {
      reportes.set(indice, reporte);
    }

    actualizarArchivoTexto(reporte);
    System.out.println("Reporte actualizado correctamente");
  }

  private void eliminarReporteDeArchivo(String archivo, String id) {
    File file = new File(archivo);
    if (!file.exists()) {
      System.out.println("El archivo no existe");
      return;
    }

    List<String> lineas = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      String linea;
      while ((linea = bufferedReader.readLine()) != null) {
        if (!linea.startsWith(id + "|")) {
          lineas.add(linea);
        }
      }
    } catch (IOException e) {
      System.out.println("Error al leer archivo. Error: " + e.getMessage());
    }

    try (PrintWriter printWriter = new PrintWriter(new FileWriter(file, false))) {
      for (String linea : lineas) {
        printWriter.println(linea);
      }
    } catch (IOException e) {
      System.out.println("Error al reescribir el archivo. Error: " + e.getMessage());
    }
  }

  /**
   * Actualiza el archivo de texto
   *
   * @param reporte Reporte a actualizar
   */
  private void actualizarArchivoTexto(Reporte reporte) {
    String archivoActualizar;
    // Validación de tipo de reporte
    if ("PDR".equals(reporte.getTipo())) {
      archivoActualizar = archivoPerdidas;
    } else {
      archivoActualizar = archivoEncontradas;
    }

    File file = new File(archivoActualizar);
    List<String> lineasActualizadas = new ArrayList<>();

    BufferedReader bufferedReader = null;
    PrintWriter printWriter = null;

    try {
      bufferedReader = new BufferedReader(new FileReader(file));
      String linea;

      while ((linea = bufferedReader.readLine()) != null) {
        String[] datos = linea.split("\\|");

        if (datos.length < 11) {
          System.out.println("Faltan datos en tu archivo de texto, verificalo");
          continue;
        }

        if (datos[0].equals(reporte.getId())) {
          Mascota mascotaActualizada = reporte.getMascota();
          lineasActualizadas.add(String.join("|",
            reporte.getId(),
            reporte.getReportanteId(),
            reporte.getNombreCompleto(),
            reporte.getFecha(),
            reporte.getZona(),
            reporte.getContacto(),
            mascotaActualizada.getEspecie(),
            mascotaActualizada.getColor(),
            mascotaActualizada.getSennas(),
            mascotaActualizada.getMicrochip(),
            mascotaActualizada.toPrint()
          ));
        } else {
          lineasActualizadas.add(linea);
        }
      }

      // Reescritura
      printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
      for (String l : lineasActualizadas) {
        printWriter.println(l);
      }

    } catch (IOException e) {
      System.out.println("Error actualizando el reporte. Error: " + e.getMessage());
    } finally {
      try {
        if (bufferedReader != null) {
          bufferedReader.close();
        }
        if (printWriter != null) {
          printWriter.close();
        }
      } catch (IOException e) {
        System.out.println("Error cerrando los archivos. Error: " + e.getMessage());
      }
    }
  }

  /**
   * Valida si el id del reporte ya existe o no
   *
   * @param id Id del reporte a comparar
   * @return boolean indicando si existe o no; true no existe, false, existe
   */
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

  /**
   * Encontrar el indice del reporte
   *
   * @param id Id a buscar
   * @return Indice
   */
  private int indicePorId(String id) {
    for (int i = 0; i < reportes.size(); i++) {
      if (reportes.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Imprime conteos por tipo de reportes y por tipo de especies
   */
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

  /**
   * Genera un listado de coincidencias de reportes
   *
   * @return Listado de reportes
   */
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
