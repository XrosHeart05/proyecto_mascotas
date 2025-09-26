package main;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    ReportController reportController = new ReportController(new ArrayList<>());
    // Refugio Huellas Felices
    // Gestor de Reporte de Mascotas
    // Obtener automáticamente la fecha del sistema para cada reporte
    reportController.createReport(new Report("1", "1", "ABC", TipoReporteEnum.PRD, "", "", TipoEspecieEnum.DOG, "", "", "", ""));
    reportController.createReport(new Report("1", "1", "DEF", TipoReporteEnum.PRD, "", "", TipoEspecieEnum.CAT, "", "", "", ""));
    reportController.createReport(new Report("1", "1", "Gomita", TipoReporteEnum.PRD, "", "", TipoEspecieEnum.CAT, "", "", "", ""));
    List<Report> reports__ = reportController.getReportBySpecies("CAT");
    for(Report r: reports__) {
      System.out.println(r.getFullName());
    }

    int option;
    do {
      System.out.println(Menu.mainMenu());
      option = InputController.inputIntRange(1, 7);

      switch (option) {
        case 1:
          Report report = Menu.newReport();
          reportController.createReport(report);
          break;
        case 2:
          break;
        case 3:
          break;
        default:
          break;
      }
    } while (option != 7); // Posible forma de llamar método para sugerir coincidencias
// suggests.forEach(p -> System.out.println("Posible coincidencia " + p.getName());
  }

}
