package modelo;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Reporte {
    private final String ticket;
    private final String guardarTicket;
    public Reporte(){
        this.ticket="C:\\Users\\RetailAdmin\\Documents\\NetBeansProjects\\SistemaVentasComedor\\src\\reporte\\newReport.jasper";
        this.guardarTicket="C:\\Users\\RetailAdmin\\Documents\\NetBeansProjects\\SistemaVentasComedor\\src\\reporte\\ticket.pdf";
    }
    public void lanzarReporte(String fecha){
      try {
      Map parameters = new HashMap();
      parameters.put("fecha", fecha);
      //JasperReport report = JasperCompileManager.compileReport(this.ticket);
      JasperPrint print = (JasperPrint) JasperFillManager.fillReport(this.ticket, parameters);
      // Exporta el informe a PDF
      JasperExportManager.exportReportToPdfFile(print,this.guardarTicket);
      JasperViewer.viewReport(print, false);
    }
    catch (JRException e) {
        System.out.print("d");
    }
    }
}
