package com.example.baithispringboot.service;

import com.example.baithispringboot.model.Garage;
import com.example.baithispringboot.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITicketService {

    List<Ticket> find();

    Ticket findTicketById(Integer id);

    void remove(Integer id);

    void saveTicket(Ticket ticket);

    List<Ticket> search(String startPoint, String endPoint, String firstDay, String secondDay);

    void order(Ticket ticketFind);
}
