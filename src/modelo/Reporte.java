package modelo;

import java.util.ArrayList;
import java.util.Map;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrinterName;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;

public class Reporte {
    private final String ticket;
    
    public Reporte(){
        
        this.ticket="reporte/esqueletoTicket.jasper";
    }
    public void lanzarReporte(Map parametros, ArrayList<String> configs){
        JasperPrint jp=null;
        String nombreImpresora=configs.get(0);
        try {
          jp =JasperFillManager.fillReport(this.ticket, parametros,new JREmptyDataSource());
        }
        catch (JRException error) {
            System.out.print("Error al crear el reporte");
        }
        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        printRequestAttributeSet.add(MediaSizeName.ISO_B8);
        printRequestAttributeSet.add(new Copies(2));
        printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
        PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
        printServiceAttributeSet.add(new PrinterName(nombreImpresora, null));
        JRPrintServiceExporter exporter = new JRPrintServiceExporter();
        SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
        configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
        configuration.setPrintServiceAttributeSet(printServiceAttributeSet);
        configuration.setDisplayPageDialog(false);
        configuration.setDisplayPrintDialog(false);
        exporter.setExporterInput(new SimpleExporterInput(jp));
        exporter.setConfiguration(configuration);
        try{
            exporter.exportReport();
        }catch(JRException error){
            System.out.println("Error no se puede imprimir");
        }
    }
}
