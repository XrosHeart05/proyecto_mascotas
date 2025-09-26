package main;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    ReporteController reporteController = new ReporteController(new ArrayList<>());
    // Refugio Huellas Felices
    // Gestor de Reporte de Mascotas
    // Obtener automáticamente la fecha del sistema para cada reporte
    reporteController.crearReporte(new Reporte("1", "1", "ABC", TipoReporteEnum.PRD, "", "", TipoEspecieEnum.DOG, "", "", "", ""));
    reporteController.crearReporte(new Reporte("1", "1", "DEF", TipoReporteEnum.PRD, "", "", TipoEspecieEnum.CAT, "", "", "", ""));
    reporteController.crearReporte(new Reporte("1", "1", "Gomita", TipoReporteEnum.PRD, "", "", TipoEspecieEnum.CAT, "", "", "", ""));
    List<Reporte> reportes = reporteController.obtenerReportePorEspecie("CAT");
    for(Reporte r: reportes) {
      System.out.println(r.getNombreCompleto());
    }

    int opcion;
    do {
      System.out.println(Menu.menuPrincipal());
      opcion = InputController.inputIntRango(1, 7);

      switch (opcion) {
        case 1:
          Reporte report = Menu.nuevoReporte();
          reporteController.crearReporte(report); //Se agrega a la lista
          break;
        case 2:
            Menu.buscarReporte();
          break;
        case 3:
          break;
        default:
          break;
      }
    } while (opcion != 7); // Posible forma de llamar método para sugerir coincidencias
// suggests.forEach(p -> System.out.println("Posible coincidencia " + p.getName());
  }

}
