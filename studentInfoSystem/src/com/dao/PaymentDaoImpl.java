package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.exception.InvalidPaymentRecordException;
import com.exception.StudentNotFoundException;
import com.model.Student;

import util.DBUtil;

public class PaymentDaoImpl implements PaymentDao{

	@Override
	public int getStudentId(int paymentId) throws SQLException, InvalidPaymentRecordException {
		Connection conn=DBUtil.getDBConn();
		String sql="select students_id from payments where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, paymentId);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		if(rst.next()) { 
			int studentId = rst.getInt("students_id");
			return studentId; 
		}
		DBUtil.dbClose();
		throw new InvalidPaymentRecordException("Payment Record not found");
	}

	@Override
	public Student getStudent(int studentId) throws StudentNotFoundException, SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="select * from students where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, studentId);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		if(rst.next()) { 
			int id = rst.getInt("id");
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			java.sql.Date sqldob = rst.getDate("date_of_birth");
			LocalDate dateOfBirth = sqldob.toLocalDate();
			String email = rst.getString("email");
			String phoneNumber = rst.getString("phone_number");
			
			
			Student student = new Student(id, firstName, lastName, dateOfBirth, email, phoneNumber);
			return student; 
		}
		DBUtil.dbClose();
		throw new StudentNotFoundException("Student not found");
	}

	@Override
	public double getPaymentAmount(int paymentId) throws SQLException, InvalidPaymentRecordException {
		Connection conn=DBUtil.getDBConn();
		String sql="select amount from payments where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, paymentId);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		if(rst.next()) { 
			double amount = rst.getDouble("amount");
			return amount;
		}
		DBUtil.dbClose();
		throw new InvalidPaymentRecordException("Payment Record not found");
	}

	@Override
	public LocalDate getPaymentDate(int paymentId) throws InvalidPaymentRecordException, SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="select payment_date from payments where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, paymentId);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		if(rst.next()) { 
			java.sql.Date date = rst.getDate("payment_date");
			LocalDate paymentDate = date.toLocalDate();
			
			return paymentDate; 
		}
		DBUtil.dbClose();
		throw new InvalidPaymentRecordException("Payment Record not found");
	}

	

}
