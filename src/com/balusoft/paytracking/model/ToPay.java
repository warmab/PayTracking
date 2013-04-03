package com.balusoft.paytracking.model;

public class ToPay {

	
	
	public ToPay(String subject, String paymentDate, int amount) {
		super();
		this.subject = subject;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}
	public ToPay(){
		super();
	}
	
	private String subject,paymentDate;
	private int amount;
	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
