package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ticket {
	private String passengername;
	private String email;
	private String sourcestation;
	private String destinationstation;
	private String phonenumber;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ticketid;
	public Ticket(String passengername, String email, String sourcestation, String destinationstation,
			String phonenumber, Integer ticketid) {
		super();
		this.passengername = passengername;
		this.email = email;
		this.sourcestation = sourcestation;
		this.destinationstation = destinationstation;
		this.phonenumber = phonenumber;
		this.ticketid = ticketid;
	}
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPassengername() {
		return passengername;
	}
	public void setPassengername(String passengername) {
		this.passengername = passengername;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSourcestation() {
		return sourcestation;
	}
	public void setSourcestation(String sourcestation) {
		this.sourcestation = sourcestation;
	}
	public String getDestinationstation() {
		return destinationstation;
	}
	public void setDestinationstation(String destinationstation) {
		this.destinationstation = destinationstation;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Integer getTicketid() {
		return ticketid;
	}
	public void setTicketid(Integer ticketid) {
		this.ticketid = ticketid;
	}
	
}
