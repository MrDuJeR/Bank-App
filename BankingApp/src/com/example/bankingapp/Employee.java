/*
 * Employee of the banking app
 * 	- View all customer information (Account info, Balance, Personal info)
 * 	- Approve/Deny Open applications for account
 *
 * 	#### Could be the parent class for bank admin #####
 */
package com.example.bankingapp;

import java.util.ArrayList;

public class Employee {

	private String eFirstName;
	private String eLastName;
	private String eUserName;
	private String ePassWord;

	public String geteFirstName() {
		return eFirstName;
	}

	public void seteFirstName(String eFirstName) {
		this.eFirstName = eFirstName;
	}

	public String geteLastName() {
		return eLastName;
	}

	public void seteLastName(String eLastName) {
		this.eLastName = eLastName;
	}

	public String geteUserName() {
		return eUserName;
	}

	public void seteUserName(String eUserName) {
		this.eUserName = eUserName;
	}

	public String getePassWord() {
		return ePassWord;
	}

	public void setePassWord(String ePassWord) {
		this.ePassWord = ePassWord;
	}

	@Override
	public String toString() {
		return "Employee [eFirstName=" + eFirstName + ", eLastName=" + eLastName + ", eUserName=" + eUserName + "]";
	}

	public Employee(String eFirstName, String eLastName, String eUserName, String ePassWord) {
		super();
		this.eFirstName = eFirstName;
		this.eLastName = eLastName;
		this.eUserName = eUserName;
		this.ePassWord = ePassWord;
	}

	public static boolean logIn(ArrayList<Employee> employees, String eUserName, String ePassword) {
		// Checking the array for the username and password
		for (Employee e : employees) {
			if (e.geteUserName().equals(eUserName) && e.getePassWord().equals(ePassword)) {
				return true;
			}
		}
		return false;
	}

}
