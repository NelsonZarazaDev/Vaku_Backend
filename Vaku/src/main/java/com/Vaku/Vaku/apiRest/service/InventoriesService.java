package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.InventoriesEntity;
import com.Vaku.Vaku.apiRest.model.response.InventoriesResponse;
import com.Vaku.Vaku.apiRest.repository.InventoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class InventoriesService {

    @Autowired
    private InventoriesRepository inventoriesRepository;

    @Autowired
    private InventoriesEmployeesService inventoriesEmployeesService;

    @Transactional
    public InventoriesEntity updateInventarie(InventoriesEntity inventoriesRequest, String token, Long emplId) {
        Date date = new Date();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Optional<InventoriesEntity> inventoriBd = inventoriesRepository.findByinveToken(token);

        inventoriesEmployeesService.createInventarie(emplId, inventoriBd.get().getInveId());

        inventoriBd.get().setInveLaboratory(inventoriesRequest.getInveLaboratory());
        inventoriBd.get().setInveLot(inventoriesRequest.getInveLot());
        inventoriBd.get().setInveQuantity(inventoriesRequest.getInveQuantity());
        inventoriBd.get().setInveDate(LocalDate.parse(dateFormat.format(date)));
        return inventoriesRepository.save(inventoriBd.get());
    }

    public Set<InventoriesResponse> getInventoriByToken(String token) {
        Optional<InventoriesEntity> inventori = inventoriesRepository.findByinveToken(token);
        return inventoriesRepository.getInventoriByToken(inventori.get().getInveId());
    }

    public Set<InventoriesResponse> getInventoriAll() {
        return inventoriesRepository.getInventoriAll();
    }
}
