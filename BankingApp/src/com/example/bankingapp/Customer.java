/*
 * Customer of bank app:
 * 	- Register for an account
 * 	- 	Apply for joint account
 * 
 * 	- Log in to the account
 * 
 * 	- Once you have an account:
 * 		- withdraw, deposit, transfer between accounts
 */
package com.example.bankingapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

	private String firstName;
	private String lastName;
	private String userName;
	private String passWord;

	private Double balance;

	// Constructor using fields
	public Customer(String firstName, String lastName, String userName, String passWord) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.passWord = passWord;
		this.setBalance(0.0);

	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	// To String Format
	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + "]";
	}

	// Getters and Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public static Boolean logIn(ArrayList<Customer> customers, String userName, String passWord) {
		// Checking the array for the username and password
		for (Customer c : customers) {
			if (c.getUserName().equals(userName) && c.getPassWord().equals(passWord)) {
				return true;
			}
		}
		return false;
	}

	public static Customer getUser(ArrayList<Customer> customers, String userName) {
		for (Customer c : customers) {
			if (c.getUserName().equals(userName)) {
				return c;
			}
		}
		return null;
	}

	// Checking if the list have the User
	public static boolean hasUser(ArrayList<Customer> customers, String toUser) {
		for (Customer c : customers) {
			if (c.getUserName().equals(toUser)) {
				return true;
			}
		}
		return false;
	}

	// Printing the Customer information for Employee
	public static void printInfo(ArrayList<Customer> customers, String customerName) {
		for (Customer c : customers) {
			if (c.getUserName().equals(customerName)) {
				System.out.println("Name: " + c.getFirstName() + " " + c.getLastName());
				System.out.println("UserName: " + c.getUserName() + "\nPassword: " + c.getPassWord());
				System.out.println("Account Balance: " + c.getBalance());
			}
		}
	}

	// Deposit Money to Account
	public static void deposit(ArrayList<Customer> customers, String userName) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Amount you like to Deposit:");
		// Check for wrong inputs
		if (scanner.hasNextDouble()) {
			Double deposit = scanner.nextDouble();
			if (deposit >= 0) {
				Customer.getUser(customers, userName)
						.setBalance(Customer.getUser(customers, userName).getBalance() + deposit);
				System.out.println("Added $" + deposit + " to the Account");
			} else {
				System.out.println("Negative Amount!");
			}
		} else {
			System.out.println("Deposit Must Be Numbers!");
		}
	}

	// Withdrawing Money from account
	public static void withdraw(ArrayList<Customer> customers, String userName) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Amount you like to Withdraw:");
		// Check for wrong inputs
		if (scanner.hasNextDouble()) {
			// Checking if they are trying to withdraw more than Customer have in their acc
			Double withdraw = scanner.nextDouble();
			if (withdraw >= 0) {
				if (Customer.getUser(customers, userName).getBalance() >= withdraw) {
					Customer.getUser(customers, userName)
							.setBalance(Customer.getUser(customers, userName).getBalance() - withdraw);
					System.out.println("Whithdrew $" + withdraw + " from the Account!");
				} else {
					System.out.println("You Dont Have Enough Money!");
				}
			} else {
				System.out.println("Negative Amount");
			}
		} else {
			System.out.println("Withdraw Must Be Numbers!");
		}
	}

	// Transfer Customer fund to another Customer by username
	public static void transferFunds(ArrayList<Customer> customers, String userName) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Username of the acc you want to Transfer Money to");
		String toUser = scanner.nextLine();
		// If the account exists
		if (Customer.hasUser(customers, toUser)) {
			System.out.println("Enter the Amount to Send:");
			// Checking input
			if (scanner.hasNextDouble()) {
				Double toSend = scanner.nextDouble();
				// Updating amount from both user
				if (Customer.getUser(customers, userName).getBalance() >= toSend) {
					Customer.getUser(customers, userName)
							.setBalance(Customer.getUser(customers, userName).getBalance() - toSend);

					Customer.getUser(customers, toUser)
							.setBalance(Customer.getUser(customers, toUser).getBalance() + toSend);
					System.out.println("Send $" + toSend + " to " + toUser);
				} else {
					System.out.println("You Dont Have Enough Money!");
				}
			} else {
				System.out.println("Not A Valid Amount!");
			}
		} else {
			System.out.println("Could not Find any user with that Username\n");
		}
	}

}
