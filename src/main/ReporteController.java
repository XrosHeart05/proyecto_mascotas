package main;

import java.util.ArrayList;
import java.util.List;

public class ReporteController {

  public final List<Reporte> reportes; // Listado que solo admite objetos Reporte // Sirve para agregar nuevos reportes
  public Reporte reporte; // Se usa para interactuar con un objeto reporte (SINGULAR)

  // Constructor
  public ReporteController(List<Reporte> reportes) {
    this.reportes = reportes;
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
    return reportes.stream().filter(r -> r.getId().equals(id)).toList();
    // Filter aplica como su nombre indica filtros a dichos objetos
    // Ósea, es como un colador de datos
    // Que filtros, en este caso solo deja pasar los datos que coinciden con el ID recibido como argumento
    // La nomenclatura usada es la siguiente, "r" pasa a ser la referencia de un único objeto de reporte
    // Es como un loop forEach o for de reportes[i]
    // Se hace uso del getId() de los getters de la clase Report
    // equals funciona para Strings al igual que == para int
    // Devuelve la primera coincidencia, si hubieran más coincidencias no importa porque ya hizo un return

  }

  public List<Reporte> obtenerReportePorZona(String zona) {
    return reportes.stream().filter(r -> r.getZona().equals(zona)).toList();
  }

  public List<Reporte> obtenerReportePorEspecie(String especie) {
    TipoEspecieEnum _especie;
    if ("DOG".equals(especie)) {
      _especie = TipoEspecieEnum.DOG;
    } else {
      _especie = TipoEspecieEnum.CAT;
    }
    return reportes.stream().filter(r -> r.getEspecie().equals(_especie)).toList();
  }

  // Realizar búsqueda de reporte por los demás atributos
  public List<Reporte> buscarReportes(int opcion, String consulta) {
    // Transforma el query a caracteres en minuscula
    String minusConsulta = consulta.toLowerCase();
    List<Reporte> resultado;

    switch (opcion) {
      // ID Reportante
      case 1:
        obtenerReportesPorId(consulta);
        resultado = reportes.stream()
          // Convierte el nombre del objeto (reporte) a minúscula y lo iguala al query
          // Se usa el método "contains" para que sea similar a la cláusula LIKE %% de SQL
          .filter(r -> r.getReportanteId().toLowerCase().contains(minusConsulta))
          // Demás filtros que se deseen usar
          .filter(r -> r.getNombreCompleto().toLowerCase().contains(minusConsulta))
          // Se debe convertir todos los filtros de un stream a nuevamente un listado
          // Porque se determinó que se devolvería un List<Report>
          .toList();
        break;

      // Especie
      // TODO: Aplicar filtro de especie
      case 2:
        resultado = reportes.stream()
          // Convierte el nombre del objeto (reporte) a minúscula y lo iguala al query
          // Se usa el método "contains" para que sea similar a la cláusula LIKE %% de SQL
          .filter(r -> r.getReportanteId().toLowerCase().contains(minusConsulta))
          // Demás filtros que se deseen usar
          .filter(r -> r.getNombreCompleto().toLowerCase().contains(minusConsulta))
          // Se debe convertir todos los filtros de un stream a nuevamente un listado
          // Porque se determinó que se devolvería un List<Report>
          .toList();
        break;

      // Zona
      // TODO: Aplicar filtro de zona
      case 3:
        resultado = reportes.stream()
          // Convierte el nombre del objeto (reporte) a minúscula y lo iguala al query
          // Se usa el método "contains" para que sea similar a la cláusula LIKE %% de SQL
          .filter(r -> r.getReportanteId().toLowerCase().contains(minusConsulta))
          // Demás filtros que se deseen usar
          .filter(r -> r.getNombreCompleto().toLowerCase().contains(minusConsulta))
          // Se debe convertir todos los filtros de un stream a nuevamente un listado
          // Porque se determinó que se devolvería un List<Report>
          .toList();
        break;
      default:
        return new ArrayList<>();
    }

    return resultado;
  }

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

  public boolean eliminarReporte(String id) {
    // Validar removeIf
    return reportes.removeIf(r -> r.getId().equals(id));
  }
}
