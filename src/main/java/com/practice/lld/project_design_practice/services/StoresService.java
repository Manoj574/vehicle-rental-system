package com.practice.lld.project_design_practice.services;

import com.practice.lld.project_design_practice.models.Store;
import com.practice.lld.project_design_practice.models.Vehicle;
import com.practice.lld.project_design_practice.repository.IStoreRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoresService {
    private final IStoreRepository storeRepository;

    public StoresService(final IStoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }


    public List<Store> getStores(final String location) {
        return storeRepository.findByCity(location);
    }

    public List<Vehicle> getVehicles(final Long storeId, final Date fromDate, final Date toDate) {
        Optional<Store> store = storeRepository.findById(storeId);

        return store.map(value -> value.getVehicles()
                .stream()
                .filter(vehicle -> vehicle.isAvailable(fromDate, toDate))
                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
