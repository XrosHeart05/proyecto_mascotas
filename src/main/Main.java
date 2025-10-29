package main;

import java.util.List;
import javax.swing.SwingUtilities;

public class Main {
  
  public static void main(String[] args) {
    ReporteController reporteController = new ReporteController();
    // Refugio Huellas Felices
    // Gestor de Reporte de Mascotas
    // Obtener automáticamente la fecha del sistema para cada reporte
    
    SwingUtilities.invokeLater(() -> {
      InterfazGrafica ventanaPrincipal = new InterfazGrafica();
      ventanaPrincipal.setVisible(true);
    });
    
    int opcion;
    do {
      System.out.println(Menu.menuPrincipal());
      opcion = InputController.inputIntRango(1, 7);
      
      switch (opcion) {
        case 1:
          Reporte reporte = Menu.nuevoReporte(reporteController);
          if (reporte == null) {
            break;
          }
          reporteController.crearReporte(reporte); //Se agrega a la lista
          break;
        case 2:
          List<Reporte> resultado = Menu.buscarReporte(reporteController);
          if (resultado != null) {
            reporteController.imprimirReportes(resultado);
          }
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
            if ("PDR".equals(i.getTipo())) {
              System.out.println("PDR - Pérdida");
            } else {
              System.out.println("ENC - Encontrada");
            }
            System.out.println(" ID Reportante: " + i.getReportanteId());
            System.out.println(" Nombre: " + i.getNombreCompleto());
            System.out.println(" Especie: " + i.getMascota().getEspecie());
            System.out.println(" Color: " + i.getMascota().getColor());
            System.out.println(" Zona: " + i.getZona());
            System.out.println(" Fecha: " + i.getFecha() + "\n");
            
            if ("ENC".equals(i.getTipo())) {
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
