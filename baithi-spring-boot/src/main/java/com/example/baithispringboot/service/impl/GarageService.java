package com.example.baithispringboot.service.impl;

import com.example.baithispringboot.model.Garage;
import com.example.baithispringboot.repository.GarageRepository;
import com.example.baithispringboot.service.IGarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarageService implements IGarageService {
    @Autowired
    GarageRepository garageRepository;
    @Override
    public List<Garage> findAllGarage() {
        return garageRepository.findAllGarage();
    }
}
