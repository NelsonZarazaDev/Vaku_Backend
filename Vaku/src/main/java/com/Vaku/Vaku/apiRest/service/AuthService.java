package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.EmployeesEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.repository.EmployeesRepository;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import com.Vaku.Vaku.dto.AuthResponse;
import com.Vaku.Vaku.dto.LoginChildRequest;
import com.Vaku.Vaku.dto.LoginEmployeeRequest;
import com.Vaku.Vaku.security.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final PersonsRepository personsRepository;
    private final EmployeesRepository employeesRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthResponse login(LoginEmployeeRequest request) throws ChangeSetPersister.NotFoundException {
        PersonsEntity persons = personsRepository.findByPersEmail(request.getPersEmail())
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        Optional<EmployeesEntity> employees = employeesRepository.findByPersons(persons);

        if (passwordEncoder.matches(request.getPersPassword(), persons.getPersPassword())) {
            if (employees.isPresent() && employees.get().isEmplState()) {
                String token = jwtService.getToken(persons);
                return AuthResponse.builder().token(token).build();
            } else {
                throw new ChangeSetPersister.NotFoundException();
            }
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public AuthResponse loginChild(LoginChildRequest request) throws ChangeSetPersister.NotFoundException {
        PersonsEntity persons = personsRepository.findByPersDocument(request.getPersDocument())
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if (persons.getPersRole().equals("Niño")) {
            String token = jwtService.getToken(persons);
            return AuthResponse.builder().token(token).build();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }
}
