package com.Vaku.Vaku.pdfVaccinationCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarnetAssemblerService {

    @Autowired
    private PdfService pdfService;

    public byte[] generarCarnetPDF(ParentChildInfoDTO info, List<AplicacionVacunaDTO> aplicaciones, List<VacunaInfoDTO> vacunasInfo) {

        PacienteDTO paciente = new PacienteDTO();
        paciente.setNombre(info.getChildNames() + " " + info.getChildLastNames());
        paciente.setDocumento(info.getChildDocument());
        paciente.setFechaNacimiento(info.getChildBirthDate());
        paciente.setNombrePadre(info.getParentNames() + " " + info.getParentLastNames());
        paciente.setDireccion(info.getChildAddress() != null ? info.getChildAddress() : info.getParentAddress());
        paciente.setTelefono(info.getParentPhone());
        paciente.setCorreo(info.getParentEmail());

        List<VacunaDTO> vacunas = new ArrayList<>();

        for (VacunaInfoDTO vacunaInfo : vacunasInfo) {
            VacunaDTO vacuna = new VacunaDTO();
            vacuna.setEdad(vacunaInfo.getVaccAgeDose());
            vacuna.setNombreVacuna(vacunaInfo.getVaccName());
            vacuna.setDosis(vacunaInfo.getVaccDosage());

            // Buscar si la vacuna fue aplicada
            AplicacionVacunaDTO aplicacion = buscarAplicacion(vacunaInfo.getVaccId(), aplicaciones);

            if (aplicacion != null) {
                vacuna.setFechaAplicacion(aplicacion.getVaapDateApplication());
                vacuna.setFechaProximaCita(aplicacion.getVaapNextAppointmentDate());
            } else {
                vacuna.setFechaAplicacion("-");
                vacuna.setFechaProximaCita("-");
            }

            vacunas.add(vacuna);
        }

        return pdfService.generarCarnet(paciente, vacunas);
    }

    // Buscar si una vacuna fue aplicada
    private AplicacionVacunaDTO buscarAplicacion(int idVacuna, List<AplicacionVacunaDTO> aplicaciones) {
        return aplicaciones.stream()
                .filter(a -> a.getvVaccId() == idVacuna)
                .findFirst()
                .orElse(null);
    }

}
