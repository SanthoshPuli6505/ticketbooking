package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Ticketcontroller {

    @Autowired
    private Ticketservice ticketservice;

    /* ================= HOME ================= */
    @GetMapping("/ticket")
    public String showTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "ticket-form";
    }

    /* ================= USER ACCESS ================= */

    // USER can BOOK ticket
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public String createTicket(@ModelAttribute Ticket ticketobj) {
        ticketservice.createticket(ticketobj);
        return "redirect:/ticket";
    }

    // USER can UPDATE ticket
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/update")
    public String updateTicket(@ModelAttribute Ticket ticketobj) {
        ticketservice.updateTicket(ticketobj.getTicketid(), ticketobj.getEmail());
        return "redirect:/ticket";
    }

    /* ================= ADMIN ACCESS ================= */

    // ADMIN can DELETE ticket
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete")
    public String deleteTicket(@RequestParam("ticketid") Integer id) {
        ticketservice.deleteTicketById(id);
        return "redirect:/ticket";
    }

    // ADMIN can VIEW ticket by ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/viewbyid")
    public String getTicketById(@RequestParam("ticketid") Integer id, Model model) {

        Ticket ticket = ticketservice.getTicketById(id);

        model.addAttribute("ticketById", ticket);
        model.addAttribute("ticket", new Ticket());

        return "ticket-form";
    }

    // ADMIN can VIEW ALL tickets
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/viewall")
    public String getAllTickets(Model model) {

        model.addAttribute("tickets", ticketservice.getAllTickets());
        model.addAttribute("ticket", new Ticket());

        return "ticket-form";
    }
}
