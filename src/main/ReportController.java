package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReportController {

  public final List<Report> reports; // Es un listado que admite solo tipo Report (PLURAL) // Sirve para agregar nuevos reportes
  public Report report; // Es un reporte tipo Report (SINGULAR)

  // Constructor
  public ReportController(List<Report> reports) {
    this.reports = reports;
  }

  public void createReport(Report _report) {
    // Ajustar valores a insertar
    // Se debe validar si se inserta o no el microchip
    reports.add(_report);
  }

  // Este método obtiene TODOS los reportes, pueda que este método no sea necesario del todo
  // De ser el caso que se necesite dejarlo
  // En caso de que sea necesario pero devuelva demasiados resultados se debe buscar la forma de 
  // limitar la cantidad de registros a devolver (Tipo LIMIT en SQL), pero no creo que esto sea necesario
  // Ya que habría que limitar y paginar y es un poco más complejo
  public List<Report> getAllReports() {
    // Investigar como funciona ArrayList
    return reports;
  }

  // Regresa el reporte por identificador
  // Devuelve un Optional, Optional puede contener un objeto, en este caso Report o estar vacío si dicha máscota no se encontró
  // No devuelve null
  public List<Report> getReportById(String id) {
    // Stream es un método propio de los ArrayList o List
    // Genera un Stream (flujo) de objetos
    return reports.stream().filter(r -> r.getId().equals(id)).toList();
    // Filter aplica como su nombre indica filtros a dichos objetos
    // Ósea, es como un colador de datos
    // Que filtros, en este caso solo deja pasar los datos que coinciden con el ID recibido como argumento
    // La nomenclatura usada es la siguiente, "r" pasa a ser la referencia de un único objeto de reporte
    // Es como un loop forEach o for de reports[i]
    // Se hace uso del getId() de los getters de la clase Report
    // equals funciona para Strings al igual que == para int
    // Devuelve la primera coincidencia, si hubieran más coincidencias no importa porque ya hizo un return

  }

  public List<Report> getReportByZone(String zone) {
    return reports.stream().filter(r -> r.getZone().equals(zone)).toList();
  }

  public List<Report> getReportBySpecies(String especie) {
    TipoEspecieEnum _especie;
    if ("DOG".equals(especie)) {
      _especie = TipoEspecieEnum.DOG;
    } else {
      _especie = TipoEspecieEnum.CAT;
    }
    return reports.stream().filter(r -> r.getEspecie().equals(_especie)).toList();
  }

  // Realizar búsqueda de reporte por los demás atributos
  public List<Report> searchReports(int option, String consulta) {
    // Transforma el query a caracteres en minuscula
    String lowerQuery = consulta.toLowerCase();
    List<Report> toReturn;

    switch (option) {
      // ID Reportante
      case 1:
        getReportById(consulta);
        toReturn = reports.stream()
          // Convierte el nombre del objeto (reporte) a minúscula y lo iguala al query
          // Se usa el método "contains" para que sea similar a la cláusula LIKE %% de SQL
          .filter(r -> r.getReporterId().toLowerCase().contains(lowerQuery))
          // Demás filtros que se deseen usar
          .filter(r -> r.getFullName().toLowerCase().contains(lowerQuery))
          // Se debe convertir todos los filtros de un stream a nuevamente un listado
          // Porque se determinó que se devolvería un List<Report>
          .toList();
        break;

      // Especie
      // TODO: Aplicar filtro de especie
      case 2:
        toReturn = reports.stream()
          // Convierte el nombre del objeto (reporte) a minúscula y lo iguala al query
          // Se usa el método "contains" para que sea similar a la cláusula LIKE %% de SQL
          .filter(r -> r.getReporterId().toLowerCase().contains(lowerQuery))
          // Demás filtros que se deseen usar
          .filter(r -> r.getFullName().toLowerCase().contains(lowerQuery))
          // Se debe convertir todos los filtros de un stream a nuevamente un listado
          // Porque se determinó que se devolvería un List<Report>
          .toList();
        break;

      // Zona
      // TODO: Aplicar filtro de zona
      case 3:
        toReturn = reports.stream()
          // Convierte el nombre del objeto (reporte) a minúscula y lo iguala al query
          // Se usa el método "contains" para que sea similar a la cláusula LIKE %% de SQL
          .filter(r -> r.getReporterId().toLowerCase().contains(lowerQuery))
          // Demás filtros que se deseen usar
          .filter(r -> r.getFullName().toLowerCase().contains(lowerQuery))
          // Se debe convertir todos los filtros de un stream a nuevamente un listado
          // Porque se determinó que se devolvería un List<Report>
          .toList();
        break;
      default:
        return new ArrayList<>();
    }

    return toReturn;
  }

  public List<Report> suggestMatches(Report _report) {
    String reporterName = _report.getFullName().toLowerCase();
    String color = _report.getColor().toLowerCase();
    TipoReporteEnum tipoReporte = _report.getTipoReporte();

    return reports.stream()
      .filter(r -> r.getTipoReporte() == _report.getTipoReporte())
      // Otras coincidencias
      .filter(r -> r.getFullName().toLowerCase().contains(reporterName))
      .filter(r -> r.getColor().toLowerCase().contains(color))
      // Demás atributos a filtrar si se desea
      .toList();

  }

  public Report updateReport(Report _report) {
    Report auxReport = getReportById(_report.getId()).getFirst();
    Report matchedReport = auxReport;
    matchedReport.setTipoReporte(_report.getTipoReporte());
    // Ajustar demás atributos

    return matchedReport;
  }

  public boolean deleteReport(String id) {
    // Validar removeIf
    return reports.removeIf(r -> r.getId().equals(id));
  }
}
