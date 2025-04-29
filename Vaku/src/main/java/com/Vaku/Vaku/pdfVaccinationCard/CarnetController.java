package com.Vaku.Vaku.pdfVaccinationCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class CarnetController {

    @Autowired
    private CarnetAssemblerService carnetAssemblerService;

    @PostMapping("/generar")
    public ResponseEntity<byte[]> generarCarnet(
            @RequestBody CarnetDataRequest request
    ) {
        byte[] pdf = carnetAssemblerService.generarCarnetPDF(
                request.getInfo(), request.getAplicaciones(), request.getVacunas()
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=carnet-vacunacion.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
