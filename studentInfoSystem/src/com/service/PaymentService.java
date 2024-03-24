package com.service;

import java.sql.SQLException;
import java.time.LocalDate;

import com.dao.PaymentDao;
import com.dao.PaymentDaoImpl;
import com.exception.InvalidPaymentRecordException;
import com.exception.StudentNotFoundException;
import com.model.Student;

public class PaymentService {
	PaymentDao paymentDao = new PaymentDaoImpl();

	public int getStudentId(int paymentId) throws SQLException, InvalidPaymentRecordException {
		return paymentDao.getStudentId(paymentId);
	}

	public Student getStudent(int studentId) throws StudentNotFoundException, SQLException {
		return paymentDao.getStudent(studentId);
	}

	public double getPaymentAmount(int paymentId) throws SQLException, InvalidPaymentRecordException {
		return paymentDao.getPaymentAmount(paymentId);
	}

	public LocalDate getPaymentDate(int paymentId) throws InvalidPaymentRecordException, SQLException {
		return paymentDao.getPaymentDate(paymentId);
	}

	

}
