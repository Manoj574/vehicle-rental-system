package com.practice.lld.project_design_practice.repository;

import com.practice.lld.project_design_practice.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStoreRepository extends JpaRepository<Store, Long> {

    List<Store> findByCity(String location);
}
