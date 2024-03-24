package com.model;

import java.time.LocalDate;

public class Payment {
	private int id;
	private double amount;
	private LocalDate paymentDate;
	private int studentId;
	
	// constructor without arguments
	public Payment() {	}
	
	// constructor with all fields
	public Payment(int id, double amount, LocalDate paymentDate, int studentId) {
		super();
		this.id = id;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.studentId = studentId;
	}

	// constructor without id
	public Payment(double amount, LocalDate paymentDate, int studentId) {
		super();
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.studentId = studentId;
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	// to string method
	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", paymentDate=" + paymentDate + ", studentId=" + studentId
				+ "]";
	}
	
	
}
