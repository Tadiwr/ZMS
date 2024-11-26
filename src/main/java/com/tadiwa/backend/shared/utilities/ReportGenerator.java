package com.tadiwa.backend.shared.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;

import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


public class ReportGenerator {

    private InputStream reportStream;

    @Setter
    private List<?> items;

    @Setter
    private Map<String, Object> params;

    public ReportGenerator(String jasperFilePath) {
        this.reportStream = this.getClass().getResourceAsStream(jasperFilePath);
        
    }

    public ReportGenerator(Resource jasperFile) throws IOException {
        this.reportStream = jasperFile.getInputStream();
    }

    private JasperPrint getJasperPrint() throws JRException {
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(this.items));

        return jasperPrint;
    }
    
    public byte[] generatePdfReport() throws JRException {
        
        JasperPrint jasperPrint = getJasperPrint();
        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

        return pdfBytes;
    }

}
