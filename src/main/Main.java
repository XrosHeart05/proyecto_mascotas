package main;

import java.util.ArrayList;
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
          // Reporte report = Menu.nuevoReporte();
          // reporteController.crearReporte(report); //Se agrega a la lista
          reporteController.crearReporte(new Reporte("1-1234-5678", "123", "Juan Pérez López", TipoReporteEnum.PDR, "05/08/2025", "San José", TipoEspecieEnum.CAT, "", "", "", ""));
          reporteController.crearReporte(new Reporte("3-3456-7890", "124", "Luis Rodríguez", TipoReporteEnum.ENC, "07/08/2025", "Alajuela", TipoEspecieEnum.CAT, "", "", "", ""));
          reporteController.crearReporte(new Reporte("3-3456-7891", "125", "Luis Rodríguez Enrico", TipoReporteEnum.PDR, "07/08/2025", "Montes de Oca", TipoEspecieEnum.CAT, "", "", "", ""));
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
            String fechaUsuario = InputController.inputString();
            List<Reporte> prueba = reporteController.Diferencia7Dias(fechaUsuario);
            for(Reporte i : prueba){
              System.out.println("Nombre: " + i.getNombreCompleto());
            }
            break;
          
        case 6:
          //Reporte _reporte = new Reporte(String id, String reportanteId, String nombreCompleto, TipoReporteEnum tipoReporte, String fecha, String zona, TipoEspecieEnum especie, String color, String senasParticulares, String contacto, String microchip)
          //reporteController.sugerirCoincidencias(_reporte);
        default:
          break;
      }
    } while (opcion != 7); // Posible forma de llamar método para sugerir coincidencias
// suggests.forEach(p -> System.out.println("Posible coincidencia " + p.getName());
  }

}
