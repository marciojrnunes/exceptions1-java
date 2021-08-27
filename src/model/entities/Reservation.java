package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import models.exceptions.DomainException;

public class Reservation {
	//atributos
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	//construtores
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException{
		if(!checkOut.after(checkIn)) { //Se data de checkou não é anterior a data de checkin
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	//Getters e Setters
	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	//Métodos
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public String updateDates(Date checkIn, Date checkOut) throws DomainException {
		
		Date now = new Date();	//Data Atual
		if(checkIn.before(now) || checkOut.before(now)) {  //1verifica se as datas não são anteriores a agora
			throw new DomainException("Reservation  dates for update must be future");
		} 
		if(!checkOut.after(checkIn)) { //Se data de checkou não é anterior a data de checkin
			throw new DomainException("Check-out date must be after check-in date");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null;   //Operação não deu erro
		
	}

	@Override
	public String toString() {
		
		return "Room "
				+ roomNumber
				+", check-in: "
				+ sdf.format(checkIn)
				+", check-out: "
				+sdf.format(checkOut)
				+", "
				+ duration()
				+ " nights";
	}
	
	

}
