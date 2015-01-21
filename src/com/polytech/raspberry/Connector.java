package com.polytech.raspberry;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Connector {

	private Long id;
	private Date date;
	private boolean pushed;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isPushed() {
		return pushed;
	}

	public void setPushed(boolean pushed) {
		this.pushed = pushed;
	}
	
	public static void main(String[] args) {
		DataBase db = new DataBase();
		Connector c = new Connector();
		c.setDate(new Date());
		c.setPushed(true);
		db.save(c);
	}
}
