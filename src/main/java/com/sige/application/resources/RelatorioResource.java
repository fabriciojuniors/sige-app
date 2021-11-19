package com.sige.application.resources;

import com.sige.application.enums.Relatorios;
import com.sige.application.exception.RelatorioNaoEncontradoException;
import com.sige.application.model.Ingresso;
import com.sige.application.repository.IngressoRepository;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.websocket.server.PathParam;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController()
@RequestMapping("/relatorio")
public class RelatorioResource {

    @Autowired
    DataSource dataSource;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    IngressoRepository ingressoRepository;

    public JasperPrint export(Relatorios relatorio, Map<String, Object> parametros) throws JRException, FileNotFoundException, SQLException {
        InputStream jasperStream = this.getClass().getResourceAsStream(relatorio.getArquivo());
        JasperReport s = JasperCompileManager.compileReport(jasperStream);
        JasperPrint print = JasperFillManager.fillReport(s, parametros,  dataSource.getConnection());
        return print;
    }

    public void exportReport(OutputStream stream, Relatorios relatorio, Map<String, Object> parametros) throws JRException, FileNotFoundException, SQLException {
        JasperPrint print = export(relatorio, parametros);
        JasperExportManager.exportReportToPdfStream(print, stream);
    }

    @GetMapping(value = "/{relatorio}")
    public void rel(HttpServletResponse response, @PathVariable(name = "relatorio") String r, @PathParam("evento") Long evento, @PathParam("ingresso") Long ingresso) throws Exception {
        Relatorios relatorio = null;
        try{
            relatorio = Relatorios.valueOf(r.toUpperCase());
        }catch (Exception ex){
            throw new RelatorioNaoEncontradoException(r);
        }
        /*
            PARA BAIXAR O PDF DIRETAMENTE
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"Locais.pdf\""));
        */

        //PARA ABRIR O PDF NO NAVEGADOR
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename="+relatorio.getPdf());

        OutputStream outputStream = response.getOutputStream();
        if(relatorio.equals(Relatorios.EVENTOS)){
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("id_evento", evento);
            exportReport(outputStream, relatorio, parametros);
        }else if(relatorio.equals(Relatorios.CERTIFICADO)){
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("id_ingresso", ingresso);
            exportReport(outputStream, relatorio, parametros);
        }else{
            exportReport(outputStream, relatorio, null);
        }

    }

    @PostMapping(value = "/certificado")
    public void certificado(HttpServletResponse response,  @RequestBody List<Integer> ingressos) throws Exception {
        ingressos.forEach(ingresso -> {
            Map<String, Object> parametro = new HashMap<>();
            parametro.put("id_ingresso", ingresso);

            Ingresso i = ingressoRepository.findById(ingresso.longValue()).get();

            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            try {
                helper.setTo( i.getUsuario().getEmail() );
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                helper.setSubject( "Certificado de particiação" );
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                String rel = "https://sige-app.herokuapp.com/relatorio/certificado?ingresso="+ingresso;
                helper.setText("<h3>Olá, "+i.getNome()+"</h3> <p>Seu certificado de participação  no evento "+i.getEvento().getNome()+" já está disponível! <a href=\""+rel+"\">Clique aqui para acessá-lo</a></p>", true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            mailSender.send(mail);
        });

    }

}
