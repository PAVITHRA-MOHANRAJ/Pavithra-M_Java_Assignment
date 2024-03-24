package com.dao;

import java.sql.SQLException;
import java.time.LocalDate;

import com.exception.InvalidPaymentRecordException;
import com.exception.StudentNotFoundException;
import com.model.Student;

public interface PaymentDao {

	int getStudentId(int paymentId) throws SQLException, InvalidPaymentRecordException;

	Student getStudent(int studentId) throws StudentNotFoundException, SQLException;

	double getPaymentAmount(int paymentId) throws SQLException, InvalidPaymentRecordException;

	LocalDate getPaymentDate(int paymentId) throws InvalidPaymentRecordException, SQLException;

	

}
