package com.example.baithispringboot.controller;

import com.example.baithispringboot.dto.TicketDTO;
import com.example.baithispringboot.model.Garage;
import com.example.baithispringboot.model.Ticket;
import com.example.baithispringboot.service.IGarageService;
import com.example.baithispringboot.service.ITicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private ITicketService ticketService;

    @Autowired
    private IGarageService garageService;


    @GetMapping("/ticket")
    public ResponseEntity<List<Ticket>> getList() {
        List<Ticket> tickets = ticketService.find();
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/garage")
    public ResponseEntity<List<Garage>> getListGarage() {
        List<Garage> garageList = garageService.findAllGarage();
        if (garageList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(garageList, HttpStatus.OK);
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Integer id) {
        Ticket ticket = ticketService.findTicketById(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable Integer id) {
        Ticket ticket = ticketService.findTicketById(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ticketService.remove(id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PutMapping("/ticket/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Integer id, @RequestBody TicketDTO ticketDTO) {
        Ticket ticket = ticketService.findTicketById(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        BeanUtils.copyProperties(ticketDTO, ticket);
        ticketService.saveTicket(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket) {
        ticket.setDeleteStatus(1);
        ticketService.saveTicket(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @GetMapping("/ticket/search")
    public ResponseEntity<List<Ticket>> search(@RequestParam String startPoint,
                                               @RequestParam String endPoint,
                                               @RequestParam String firstDay,
                                               @RequestParam String secondDay) {
        List<Ticket> tickets = ticketService.search(startPoint, endPoint, firstDay, secondDay);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PutMapping("/ticket/order/{id}")
    public ResponseEntity<Ticket> order(@PathVariable Integer id, @RequestBody Ticket ticket) {
        Ticket ticketFind = ticketService.findTicketById(id);
        if (ticketFind == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(ticket, ticketFind);
        ticketFind.setQuantity(ticketFind.getQuantity() - 1);
        this.ticketService.order(ticketFind);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }
}
