package modelo;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class Reporte {
    private final String ticket;
    private final String guardarTicket;
    public Reporte(){
        this.ticket="reporte/esqueletoTicket.jasper";
        this.guardarTicket="reporte/ticket2.pdf";
    }
    public void lanzarReporte(Map parametros){
        try {
          JasperPrint jp =JasperFillManager.fillReport(this.ticket, parametros,new JREmptyDataSource());
          JasperExportManager.exportReportToPdfFile(jp,this.guardarTicket);
          //JasperViewer.viewReport(jp, false);
        }
        catch (JRException error) {
            System.out.print("Error");
        }
        if (Desktop.isDesktopSupported()) {
            try{
                File myFile = new File(this.guardarTicket);
                Desktop.getDesktop().open(myFile);
            }
            catch(IOException error){
                System.out.print("Archivo no soportado");
            }
          }
        else{
            System.out.print("Archivo no soportado");
        }
    }
}
