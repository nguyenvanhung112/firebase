package com.example.baithispringboot.service;

import com.example.baithispringboot.model.Garage;

import java.util.List;

public interface IGarageService {
    List<Garage> findAllGarage();
}
