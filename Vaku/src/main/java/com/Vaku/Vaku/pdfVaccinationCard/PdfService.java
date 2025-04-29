package com.Vaku.Vaku.pdfVaccinationCard;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    @Autowired
    private TemplateEngine templateEngine;

    public byte[] generarCarnet(PacienteDTO paciente, List<VacunaDTO> vacunas) {
        Context context = new Context();
        context.setVariable("paciente", paciente);
        context.setVariable("vacunas", vacunas);

        String html = templateEngine.process("carnet-vacunacion", context);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, null);
            builder.toStream(outputStream);
            builder.run();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}