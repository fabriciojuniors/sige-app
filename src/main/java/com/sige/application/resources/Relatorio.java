package com.sige.application.resources;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController()
@RequestMapping("/rel")
public class Relatorio {

    @Autowired
    DataSource dataSource;

    public JasperPrint export() throws JRException, FileNotFoundException, SQLException {
        InputStream jasperStream = this.getClass().getResourceAsStream("/Locais.jrxml");
        JasperReport s = JasperCompileManager.compileReport(jasperStream);
        JasperPrint print = JasperFillManager.fillReport(s, null,  dataSource.getConnection());
        return print;
    }

    public void exportReport(OutputStream stream) throws JRException, FileNotFoundException, SQLException {
        JasperPrint print = export();
        JasperExportManager.exportReportToPdfStream(print, stream);
    }

    @GetMapping
    public void rel(HttpServletResponse response) throws IOException, JRException, SQLException {
        /*
            PARA BAIXAR O PDF DIRETAMENTE
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"Locais.pdf\""));
        */

        //PARA ABRIR O PDF NO NAVEGADOR
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=Locais.pdf");

        OutputStream outputStream = response.getOutputStream();
        exportReport(outputStream);
    }

}
