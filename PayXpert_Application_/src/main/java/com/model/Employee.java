package com.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Employee {
	
	private int EmployeeID;
	Date DateOfBirth, JoiningDate, TerminationDate;
	private String FirstName, LastName, Gender, Email, PhoneNumber, Address, Position;
	
	public Employee(int employeeID, String firstName, String lastName, Date dateOfBirth, String gender, String email,
			String phoneNumber, String address, String position, Date joiningDate, Date terminationDate) {

		EmployeeID = employeeID;
		FirstName = firstName;
		LastName = lastName;
		DateOfBirth = dateOfBirth;
		Gender = gender;
		Email = email;
		PhoneNumber = phoneNumber;
		Address = address;
		Position = position;
		JoiningDate = joiningDate;
		TerminationDate = terminationDate;
	}
	public Employee(int id, String name, double salary) {
		super();
	}
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public Date getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public Date getJoiningDate() {
		return JoiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		JoiningDate = joiningDate;
	}
	public Date getTerminationDate() {
		return TerminationDate;
	}
	public void setTerminationDate(Date terminationDate) {
		TerminationDate = terminationDate;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public int CalculateAge(String dobirth) {
		LocalDate dob = LocalDate.parse(dobirth);
		LocalDate currentDate = LocalDate.now();
		int age = (Period.between(dob, currentDate)).getYears();
		return age;
	}
	@Override
	public String toString() {
		return "{EmployeeID=" + EmployeeID + ", DateOfBirth=" + DateOfBirth + ", JoiningDate=" + JoiningDate
				+ ", TerminationDate=" + TerminationDate + ", FirstName=" + FirstName + ", LastName=" + LastName
				+ ", Gender=" + Gender + ", Email=" + Email + ", PhoneNumber=" + PhoneNumber + ", Address=" + Address
				+ ", Position=" + Position + "}";
	}
	

}