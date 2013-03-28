package com.balusoft.paytracking.model;


public class Payment {

	
	/**
	 * 	 
	 * @param subject
	 * @param amount
	 * @param paymentDate
	 */
	public Payment(String subject, int amount, String paymentDate) {
		super();		
		this.subject = subject;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}
	
	/**
	 * 
	 */
	public Payment() {
		super();
	}


	private String subject,paymentDate;
	private int amount;

	/**
	 * 
	 * @return
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * 
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * 
	 * @return
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * 
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * 
	 * @return
	 */
	public String getPaymentDate() {
		return paymentDate;
	}
	/**
	 * 
	 * @param paymentDate
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "Payment [subject=" + subject + ", paymentDate=" + paymentDate
				+ ", amount=" + amount + "]";
	}
	
	
}
