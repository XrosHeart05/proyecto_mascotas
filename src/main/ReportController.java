package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReportController {
  private final List<Report> reports;
  private Report report;

  public ReportController() {
    this.reports = new ArrayList<>();
  }

  public Report createReport(Report _report) {
    // Ajustar valores a insertar
    // Se debe validar si se inserta o no el microchip
    report = _report;
    reports.add(report);
    return report;
  }

  // Este método obtiene TODOS los reportes, pueda que este método no sea necesario del todo
  // De ser el caso que se necesite dejarlo
  // En caso de que sea necesario pero devuelva demasiados resultados se debe buscar la forma de 
  // limitar la cantidad de registros a devolver (Tipo LIMIT en SQL), pero no creo que esto sea necesario
  // Ya que habría que limitar y paginar y es un poco más complejo
  public List<Report> getAllReports() {
    // Investigar como funciona ArrayList
    return new ArrayList<>(reports);
  }

  // Regresa el reporte por identificador
  // Devuelve un Optional, Optional puede contener un objeto, en este caso Report o estar vacío si dicha máscota no se encontró
  // No devuelve null
  public Optional<Report> getReportById(String id) {
    // Stream es un método propio de los ArrayList o List
    // Genera un Stream (flujo) de objetos
    return reports.stream()
      // Filter aplica como su nombre indica filtros a dichos objetos
      // Ósea, es como un colador de datos
      // Que filtros, en este caso solo deja pasar los datos que coinciden con el ID recibido como argumento
      // La nomenclatura usada es la siguiente, "r" pasa a ser la referencia de un único objeto de reporte
      // Es como un lop forEach o for de pets[i]
      // Se hace uso del getId() de los getters de la clase Report
      .filter(r -> r.getId().equals(id))
      // Devuelve la primera coincidencia, si hubieran más coincidencias no importa porque ya hizo un return
      .findFirst();
  }

  // Realizar búsqueda de reporte por los demás atributos
  public List<Report> searchReports(String query) {
    // Transforma el query a caracteres en minuscula
    String lowerQuery = query.toLowerCase();
    return reports.stream()
      // Convierte el nombre del objeto (reporte) a minúscula y lo iguala al query
      // Se usa el método "contains" para que sea similar a la cláusula LIKE %% de SQL
      .filter(r -> r.getReporterId().toLowerCase().contains(lowerQuery))
      // Demás filtros que se deseen usar
      .filter(r -> r.getFullName().toLowerCase().contains(lowerQuery))
      // Se debe convertir todos los filtros de un stream a nuevamente un listado
      // Porque se determinó que se devolvería un List<Report>
      .toList();
  }

  public List<Report> suggestMatches(Report _report) {
    String reporterName = _report.getFullName().toLowerCase();
    String color = _report.getColor().toLowerCase();
    ReportTypeEnum reportType = _report.getReportType();

    return reports.stream()
      .filter(r -> r.getReportType() == _report.getReportType())
      // Otras coincidencias
      .filter(r -> r.getFullName().toLowerCase().contains(reporterName))
      .filter(r -> r.getColor().toLowerCase().contains(color))
      // Demás atributos a filtrar si se desea
      .toList();

  }

  public Report updateReport(Report _report) {
    Optional<Report> auxReport = getReportById(_report.getId());
    if (auxReport.isEmpty()) {
      return null;
    }
    Report matchedReport = auxReport.get();
    matchedReport.setReportType(_report.getReportType());
    // Ajustar demás atributos

    return matchedReport;
  }

  public boolean deleteReport(String id) {
    // Validar removeIf
    return reports.removeIf(r -> r.getId().equals(id));
  }
}
