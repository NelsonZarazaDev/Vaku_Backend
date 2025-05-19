package com.Vaku.Vaku.pdfVaccinationCard;

import com.Vaku.Vaku.apiRest.model.response.VaccinesResponse;
import com.Vaku.Vaku.apiRest.repository.VaccinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CarnetAssemblerService {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private VaccinesRepository vaccinesRepository;  // Repositorio para obtener las vacunas

    public byte[] generarCarnetPDF(ParentChildInfoDTO info, List<AplicacionVacunaDTO> aplicaciones) {

        // Crear un DTO para el paciente con la información básica
        PacienteDTO paciente = new PacienteDTO();
        paciente.setNombre(info.getChildNames() + " " + info.getChildLastNames());
        paciente.setDocumento(info.getChildDocument());
        paciente.setFechaNacimiento(info.getChildBirthDate());
        paciente.setNombrePadre(info.getParentNames() + " " + info.getParentLastNames());
        paciente.setDireccion(info.getChildAddress() != null ? info.getChildAddress() : info.getParentAddress());
        paciente.setTelefono(info.getParentPhone());
        paciente.setCorreo(info.getParentEmail());

        // Llamamos al repositorio para obtener todas las vacunas con su información de inventario
        List<VaccinesResponse> vacunasFromDb = vaccinesRepository.findAllVaccines();  // Aquí cambiamos a la consulta personalizada
        List<VacunaDTO> vacunas = new ArrayList<>();

        // Para evitar duplicados, usamos un Set para almacenar los inventarios asignados
        Set<String> inventariosAsignados = new HashSet<>();

        // Iteramos sobre las vacunas obtenidas del repositorio
        // Iteramos sobre las vacunas obtenidas del repositorio
        for (VaccinesResponse vacunaResponse : vacunasFromDb) {
            VacunaDTO vacuna = new VacunaDTO();

            vacuna.setNombreVacuna(vacunaResponse.getVaccName());
            vacuna.setEdad(vacunaResponse.getVaccAgeDose());
            vacuna.setDosis(vacunaResponse.getVaccDosage());

            // Usamos directamente las fechas desde el resultado SQL
            if (vacunaResponse.getVaapDateApplication() != null) {
                vacuna.setFechaAplicacion(vacunaResponse.getVaapDateApplication().toString());
            } else {
                vacuna.setFechaAplicacion("-");
            }

            if (vacunaResponse.getVaapNextAppointmentDate() != null) {
                vacuna.setFechaProximaCita(vacunaResponse.getVaapNextAppointmentDate().toString());
            } else {
                vacuna.setFechaProximaCita("-");
            }

            vacuna.setLaboratorio(vacunaResponse.getInveLaboratory());
            vacuna.setNumeroLote(vacunaResponse.getInveLot());

            vacunas.add(vacuna);
        }

        // Finalmente, generamos el PDF con la información del paciente y las vacunas
        return pdfService.generarCarnet(paciente, vacunas);
    }

    // Buscar si una vacuna fue aplicada
    public AplicacionVacunaDTO buscarAplicacion(String nombreVacuna, List<AplicacionVacunaDTO> aplicaciones) {
        return aplicaciones.stream()
                .filter(aplicacion -> aplicacion.getVaccName() != null && aplicacion.getVaccName().equals(nombreVacuna))
                .findFirst()
                .orElse(null);
    }
}