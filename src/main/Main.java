package main;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    ReporteController reporteController = new ReporteController();
    // Refugio Huellas Felices
    // Gestor de Reporte de Mascotas
    // Obtener automáticamente la fecha del sistema para cada reporte

    int opcion;
    do {
      System.out.println(Menu.menuPrincipal());
      opcion = InputController.inputIntRango(1, 7);
      
      switch (opcion) {
        case 1:
          Reporte report = Menu.nuevoReporte(reporteController);
          reporteController.crearReporte(report); //Se agrega a la lista
          break;
        case 2:
          List<Reporte> resultado = Menu.buscarReporte(reporteController);
          reporteController.imprimirReportes(resultado);
          break;
        case 3:
          reporteController.imprimirReportes(reporteController.reportes);
          break;
        case 4:
          reporteController.reporteAgrupado();
          break;

        case 5:
          List<Reporte> coincidencias = reporteController.reportesCoincidencias();
          int contador = 0;
          for (Reporte i : coincidencias) {
            contador++;
          }
          String texto = "";
          int largo = 45;
          texto += "-".repeat(largo) + "\n";
          texto += Util.centrarTexto("REPORTE DE COINCIDENCIAS ENCONTRADAS", largo) + "\n";
          texto += "=".repeat(largo) + "\n";
          System.out.println(texto);

          for (Reporte i : coincidencias) {
            if (i.getTipoReporte() == TipoReporteEnum.PDR) {
              System.out.println("PDR - Pérdida");
            } else {
              System.out.println("ENC - Encontrada");
            }
            System.out.println(" ID Reportante: " + i.getReportanteId());
            System.out.println(" Nombre: " + i.getNombreCompleto());
            System.out.println(" Especie: " + i.getEspecie());
            System.out.println(" Color: " + i.getColor());
            System.out.println(" Zona: " + i.getZona());
            System.out.println(" Fecha: " + i.getFecha() + "\n");

            if (i.getTipoReporte() == TipoReporteEnum.ENC) {
              texto = "-".repeat(largo);
              System.out.println(texto);
            }
          }
          if (contador >= 2) {
            System.out.println("Coincidencias encontradas: " + contador / 2);
          } else {
            System.out.println("Coincidencias encontradas: " + contador);
          }
          break;

        case 6:
          Reporte reporteA = Menu.actualizarReporte(reporteController);
          if (reporteA == null) {
            System.out.println("No existe el reporte por ID ingresado, intente nuevamente");
            break;
          }
          break;
        default:
          break;
      }
    } while (opcion != 7);
  }

}
