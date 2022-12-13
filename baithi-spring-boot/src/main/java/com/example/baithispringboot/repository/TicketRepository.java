package com.example.baithispringboot.repository;

import com.example.baithispringboot.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    @Query(value = "SELECT * FROM  `ticket` where delete_status = 1",nativeQuery = true)
    List<Ticket> find();


    @Query(value = "SELECT * FROM `ticket` where delete_status = 1 and id = :id",nativeQuery = true)
    Ticket findTicketById(@Param("id")Integer id);

    @Transactional
    @Modifying
    @Query(value = "update `ticket` set delete_status = 0 where id = :id",nativeQuery = true)
    void remove(@Param("id")Integer id);



    @Query(value = "SELECT * FROM `ticket` where start_point like %:startPoint% and end_point like %:endPoint% and date(start_day) BETWEEN date(:firstDay) and date(:secondDay) and delete_status = 1" ,nativeQuery = true)
    List<Ticket> search(
            @Param("startPoint")String startPoint,
            @Param("endPoint")String endPoint,
            @Param("firstDay")String firstDay,
            @Param("secondDay")String secondDay);
}
