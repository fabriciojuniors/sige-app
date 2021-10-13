package com.sige.application.resources;

import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController()
@RequestMapping("/rel")
public class Relatorio {

    public JasperPrint export() throws JRException, FileNotFoundException, SQLException {
        File file = ResourceUtils.getFile("D:\\Google Drive\\Esucri\\APS\\SIGE\\sige-app\\src\\main\\resources\\TESTE.jrxml");
        JasperReport s = JasperCompileManager.compileReport(file.getAbsolutePath());
        Connection c = null;
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sige-app", "postgres", "123");
        JasperPrint print = JasperFillManager.fillReport(s, null,  new JREmptyDataSource());
        return print;
    }

    public void exportReport(OutputStream stream) throws JRException, FileNotFoundException, SQLException {
        JasperPrint print = export();
        JasperExportManager.exportReportToPdfStream(print, stream);
    }

    @GetMapping
    public void rel(HttpServletResponse response) throws IOException, JRException, SQLException {
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"teste.pdf\""));
        OutputStream outputStream = response.getOutputStream();
        exportReport(outputStream);
    }

}
