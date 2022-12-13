package com.example.baithispringboot.repository;

import com.example.baithispringboot.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageRepository extends JpaRepository<Garage,Integer> {

    @Query(value = "SELECT * FROM  `garage` where delete_status = 1",nativeQuery = true)
    List<Garage> findAllGarage();
}
