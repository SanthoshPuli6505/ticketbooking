package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Ticketservice {
	@Autowired
	private Ticketdao ticketdao;
	public Ticket createticket(Ticket ticketobj) {
		return ticketdao.save(ticketobj);
	}
	public Iterable<Ticket>getAllTickets(){
		return ticketdao.findAll();
	}
	public Ticket getTicketById(Integer id) {
        return ticketdao.findById(id).orElse(new Ticket());
    }
	public Ticket updateTicket(Integer id,String newemail) {
		Ticket dbticket=getTicketById(id);
		dbticket.setEmail(newemail);
		return ticketdao.save(dbticket);
	}
	public Ticket deleteTicketById(Integer id) {
        ticketdao.deleteById(id);
		return null;
    }
}
