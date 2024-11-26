package com.tadiwa.backend.shared.utilities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Setter
public class ReportGenerator {

    private String jasperFilePath;
    private InputStream reportStream;
    private List<?> items;
    private Map<String, Object> params;

    public ReportGenerator(String jasperFilePath) {
        this.jasperFilePath = jasperFilePath;
        this.items = new ArrayList<>();
        this.reportStream = this.getClass().getResourceAsStream(jasperFilePath);
        
    }

    private JasperPrint getJasperPrint() throws JRException {
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        JRBeanCollectionDataSource dataCollection = new JRBeanCollectionDataSource(items);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataCollection);

        return jasperPrint;
    }
    
    public byte[] generatePdfReport() throws JRException {
        
        JasperPrint jasperPrint = getJasperPrint();
        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

        return pdfBytes;
    }

}
