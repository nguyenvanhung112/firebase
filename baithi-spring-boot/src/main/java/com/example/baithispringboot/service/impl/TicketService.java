package com.example.baithispringboot.service.impl;


import com.example.baithispringboot.model.Ticket;
import com.example.baithispringboot.repository.TicketRepository;
import com.example.baithispringboot.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITicketService {
    @Autowired
    TicketRepository ticketRepository;


    @Override
    public List<Ticket> find() {
        return ticketRepository.find();
    }

    @Override
    public Ticket findTicketById(Integer id) {
        return ticketRepository.findTicketById(id);
    }

    @Override
    public void remove(Integer id) {
        ticketRepository.remove(id);
    }

    @Override
    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }



    @Override
    public List<Ticket> search(String startPoint, String endPoint, String firstDay, String secondDay) {
        return ticketRepository.search(startPoint, endPoint,firstDay,secondDay);
    }

    @Override
    public void order(Ticket ticketFind) {
       this.ticketRepository.save(ticketFind);
    }
}
