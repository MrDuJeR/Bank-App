package com.example.bankingapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		// #####################################################################
		// Creating the list of Users
		ArrayList<Customer> customers = new ArrayList<>();
		// #### Populate the list from File HERE ####
		Customer c = new Customer("Jevin", "Francis", "user", "pass");
		Customer c2 = new Customer("Tom", "Ancis", "u", "p");
		customers.add(c);
		customers.add(c2);
		// Creating the list of New Account that are Not Yet Approved
		ArrayList<Customer> newCustomers = new ArrayList<>();
		// Creating the list of Employees

		ArrayList<Employee> employees = new ArrayList<>();
		// #### Populate the List from File HERE ####
		Employee e = new Employee("Jevin", "Francis", "e", "e");
		employees.add(e);

		// #####################################################################

		String choice; // Choice
		Boolean running = true;

		while (running) {
			System.out.println("---------------------------------------------------------");
			System.out.println("Welcome to JKM Bank.\n");
			System.out.println("Press the Number to select!");
			System.out.println("1. Customers\n2. Employee\n0. Exit");
			choice = scanner.nextLine();

			switch (choice) {
			// If user chose 1 for Customer
//##########################################################################################################################
			case "0":
				running = false;
				break;
			case "1":
				// Chose Customer
				System.out.println("Press:\n1. Login\n2. New Accounts");
				choice = scanner.nextLine();
				switch (choice) {
				case "0":
					running = false;
					break;
				case "1":
					// Chose Login
					System.out.println("Enter your Username:");
					String userName = scanner.nextLine();
					System.out.println("Enter your Password:");
					String passWord = scanner.nextLine();
					// Checking if the user is in the list
					if (Customer.logIn(customers, userName, passWord)) {
						Boolean logIn = true;
						// Run Until Logged out
						while (logIn) {
							// User exists log them in HERE!
							System.out.println("---------------------------------------------------------");

							System.out.println("Logged In as " + Customer.getUser(customers, userName).getFirstName());
							System.out.println(
									"Your Current Balance is: $" + Customer.getUser(customers, userName).getBalance());
							System.out.println("Would you like to:\n1. Deposit\n2. Withdraw\n3. Transfer\n4. Log Out");
							// Deposit Withdraw and Trasfer Here####
							choice = scanner.nextLine();
							switch (choice) {
							case "0":
								running = false;
								logIn = false;
								break;
							case "1":
								// Deposit Amount to Account
								Customer.deposit(customers, userName);
								scanner.nextLine();
								break;
							case "2":
								// Withdraw from account
								Customer.withdraw(customers, userName);
								scanner.nextLine();
								break;
							case "3":
								// Transfer to another account
								Customer.transferFunds(customers, userName);
								scanner.nextLine();
								break;
							case "4":
								// lOG OUT
								System.out.println("Logging Out...\n");
								scanner.nextLine();
								logIn = false;
								break;
							default:
								System.out.println("Invalid Choice!");
								scanner.nextLine();
								break;
							}
						}

					} else {
						System.out.println("Username or Password is Incorrect!");
						scanner.nextLine();
					}
					break;
				case "2":
					// Chose New Account
					System.out.println("What whould you like to do:\n1. New Account\n2. New Joint Account");
					choice = scanner.nextLine();
					switch (choice) {
					case "0":
						running = false;
						break;
					case "1":
						// New Account
						System.out.println("Creating new Account\nEnter First Name:");
						String firstName = scanner.nextLine();
						System.out.println("Enter Last Name:");
						String lastName = scanner.nextLine();
						System.out.println("Enter Username:");
						String newUserName = scanner.nextLine();
						System.out.println("Enter Password:");
						String newPassWord = scanner.nextLine();
						// Check if Username exists
						Boolean userExisits = false;
						for (Customer i : customers) {
							if (i.getUserName().equals(newUserName)) {
								System.out.println("A User With the Username Already Exisits");
								userExisits = true;
								scanner.nextLine();
								break;
							}
						}
						if(!userExisits) {
							// Create new User in Another list for admin to approve.
							Customer c1 = new Customer(firstName, lastName, newUserName, newPassWord);
							newCustomers.add(c1);
							System.out.println("Account Request Send!");
							scanner.nextLine();
							break;	
						}
						break;
					case "2":
						// ########## JOINT ACCOUNT######
						System.out.println("Joint Account Section is Not Yet Implimented");
						scanner.nextLine();
						break;
					default:
						System.out.println("Invalid Choice!");
						break;
					}
					break;
				default:
					System.out.println("Invalid Choice!");
					break;
				}
				break;

//###########################################################################################################################################
			// If user chose 2 for Employee
			case "2":
				// Chose Employee
				System.out.println("Sign In As:\n1. Employee\n2. Admin");
				choice = scanner.nextLine();

				switch (choice) {
				case "0":
					running = false;
					break;
				case "1":
					// login as employyee
					// ### SIGN IN AS AN EMPLOYEE HERE ###
					System.out.println("Enter Employee Username:");
					String eUserName = scanner.nextLine();
					System.out.println("Enter Employee Password:");
					String ePassword = scanner.nextLine();

					if (Employee.logIn(employees, eUserName, ePassword)) {
						Boolean logIn = true;
						// ###################################
						while (logIn) {
							System.out.println(
									"To See\n1. Specific User\n2. View All Users\n3. Appove/Deny Application\n4. Log Out");
							choice = scanner.nextLine();
							//Employee Options
							switch (choice) {
							// See User One at a time
							case "0":
								running = false;
								logIn = false;
								break;
							case "1":
								System.out.println("Enter The Username of the Customer");
								String customerName = scanner.nextLine();

								// Check id user exisits
								if (Customer.hasUser(customers, customerName)) {
									// printing the info to the screen
									Customer.printInfo(customers, customerName);
									scanner.nextLine();
								} else {
									// if user is not found
									System.out.println("User Not Found");
									scanner.nextLine();
								}
								break;
							case "2":
								// See All Users
								for (Customer c3 : customers) {
									Customer.printInfo(customers, c3.getUserName());
									System.out.println("");
								}
								scanner.nextLine();
								break;
							case "3":
								// Approve Deny Accounts
								for (Customer c4 : newCustomers) {
									System.out.println("Review:\nName: " + c4.getFirstName() + " " + c4.getLastName());
									System.out.println("User Name: " + c4.getUserName());
									System.out.println("Password: " + c4.getPassWord());

									System.out.println("1. Approve\n2. Deny");
									choice = scanner.nextLine();
									switch (choice) {
									case "1":
										customers.add(c4);
										System.out.println("Approved");
										scanner.nextLine();
										break;
									case "2":
										System.out.println("Denied");
										scanner.nextLine();
										break;
									default:
										break;
									}
								}
								//Clearing the Waitlist after Review
								newCustomers.removeAll(newCustomers);
								System.out.println("No More Accounts in Review.");
								scanner.nextLine();
								break;
							case "4":
								// lOG oUT
								System.out.println("Logging Out...\n");
								scanner.nextLine();
								logIn = false;
								break;
							default:
								break;
							}

						}

					} else {
						System.out.println("Username or Password is Incorrect!");
						scanner.nextLine();
					}
					break;
				// Login As An ADMIN
				case "2":
					// login as admin
					break;
				default:
					break;
				}

				// DO WHAT EMPLOYEE CAN DO HERE

				break;
//####################################################################################################################
				// If the choice was not 1 or 2
			default:
				System.out.println("Invalid Choice!");
				scanner.nextLine();
				break;
			}
		}

		// ### Save the Data to File Here After the program finsh running ###
		System.out.println("################################################################");
		scanner.close();
	}

}
