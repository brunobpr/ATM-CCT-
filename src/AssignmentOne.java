
//BRUNO RIBEIRO
//2017138
//First Assignment: ATM JAVA PROJECT
//Dr. Kyle

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileWriter;

public class AssignmentOne {
	// Calling global variables
	int menuOption;
	String textFileName;
	String secondTextFileName;
	String[] userInformation = new String[5];
	String[] recipientInformation = new String[5];
	String cFirstName = "";
	String cSurName;
	String cPassword;
	String cBalance;
	String cRecipientFirstName = "";
	String cRecipientSurName;
	String cRecipientPassword;
	String cRecipientBalance;

	// Default constructor Assignment Number One
	public AssignmentOne() {
		userLogin();
	}

	// Login display, the user will be requires to enter ID and PIN
	// After collecting what is needed, the authentication method will be called
	public void userLogin() {
		welcomeText();
		System.out.println("ID number: ");
		String userIDNumber = userInput();
		System.out.println("4 Digit Pin: ");
		String userPassword = userInput();
		userAuthentication(userIDNumber, userPassword);
	}

	// This method will check the userID and userPassword
	// This is the method that will retrieve the text file
	// If the password is correct, the user will be sent to the menu
	public void userAuthentication(String userIDNumber, String userPassword) {
		textFileName = "C:\\Users\\Bruno\\eclipse-workspace\\AssignmentOne\\src\\" + userIDNumber + ".txt";
		try {
			BufferedReader br = new BufferedReader(new FileReader(textFileName));
			int i = 0;
			String line = br.readLine();
			while ((line != null)) {
				line = br.readLine();
				userInformation[i] = line;
				i++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			if (userPassword.equals(userInformation[2])) {
				cFirstName = userInformation[0];
				cSurName = userInformation[1];
				cPassword = userInformation[2];
				cBalance = userInformation[3];
				menu();
			} else {
				System.out.println("Information Incorrect" + "\n" + "Restarting process...");
				// cleaning variables and restarting login
				cFirstName = "";
				userIDNumber = "";
				userPassword = "";
				for (int i = 0; i < 10; i++) {
					userInformation[i] = "";
				}
				userLogin();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// This method will display the main menu
	// It has 6 options
	public void menu() {

		welcomeText();
		System.out.println("1.|Check Current Balance|");
		System.out.println("2.|      Withdraw       |");
		System.out.println("3.|  Change PIN Number  |");
		System.out.println("4.|  Check Stock Price  |");
		System.out.println("5.| Tranfer between Acc |");
		System.out.println("6.|     Bank Summary    |");
		System.out.println("7.|        Logout       |");
		System.out.println("Please, choose one of the above!");
		// System.out.println("6.|Bank Summary|");
		// Uncomment bellow to enable the Transfer option
		// System.out.println("7.|Transfer between accounts|
		menuOption = Integer.valueOf(userInput());
		switch (menuOption) {
		case 1:
			balanceMenu();
			break;
		case 2:
			withdrawMenu();
			break;
		case 3:
			changePinMenu();
			break;
		case 4:
			stockMenu();
			break;
		case 5:
			transferMenu();
			break;
		case 6:
			bankSummary();
			break;
		case 7:
			logout();
			break;
		default:
			System.out.println("Sorry, Command Invalid.");
			menu();
		}
		userLogin();
	}

	// 1.|Check Current Balance|
	// If the user chooses the option 1 this method will be called
	// It allows the user to check their current balance
	public void balanceMenu() {
		System.out.println("Your current balance is: " + userInformation[3] + " euro");
		navigationMenu();
	}

	// 2. |Withdraw|
	// If the user chooses the option 2 this method will be called
	// It allows the user to withdraw money giving them options of quantity as its
	// on the ATMs
	public void withdrawMenu() {
		System.out.println("Withdraw" + "\n" + "Please select the withdraw amount:");
		System.out.println("  |     $ 10       |");
		System.out.println("  |     $ 20       |");
		System.out.println("  |     $ 40       |");
		System.out.println("  |     $ 50       |");
		System.out.println("  |     $ 60       |");
		System.out.println("  |     $ 100      |");
		System.out.println("1.|    Cancel      |");
		menuOption = Integer.valueOf(userInput());
		Integer money = Integer.valueOf(userInformation[3]);
		switch (menuOption) {
		case 10:
			userInformation[3] = String.valueOf(money - 10);
			break;
		case 20:
			userInformation[3] = String.valueOf(money - 20);
			break;
		case 40:
			userInformation[3] = String.valueOf(money - 40);
			break;
		case 50:
			userInformation[3] = String.valueOf(money - 50);
			break;
		case 60:
			userInformation[3] = String.valueOf(money - 60);
			break;
		case 100:
			userInformation[3] = String.valueOf(money - 100);
			break;
		case 1:
			logout();
			break;
		default:
			System.out.println("Invalid format");
			withdrawMenu();
			break;
		}
		System.out.println("Are you sure you want to withdraw $" + money + " ?");
		yesNo();
		System.out.println("Your new balance is: " + userInformation[3] + "\n" + "\n" + "\n");
		cBalance = userInformation[3];
		FileWriter();
		navigationMenu();
	}

	// 3. |Change Pin Number|
	// If the user chooses the option 3 this method will be called
	// It allows the user to change their PIN number
	public void changePinMenu() {
		String newPinFirst;
		String newPinSecond;
		System.out.println("Please, enter your new 4-Digits PIN");
		newPinFirst = userInput();
		while (newPinFirst.length() > 4) {
			System.out.println("Sorry, only 4 digits are accepted");
			newPinFirst = userInput();
		}
		System.out.println("Please, confirm your new 4-Digit PIN");
		newPinSecond = userInput();
		System.out.println("Change PIN?");
		yesNo();
		if (newPinFirst.equals(newPinSecond)) {
			cPassword = newPinFirst;
			FileWriter();
			System.out.println("Your PIN has been uploaded");
		} else {
			System.out.println("Sorry, Pin does not match!" + "\n" + "Restarting process...");
			changePinMenu();
		}
		navigationMenu();
	}

	// 4. |Stock Prices|
	// If the user chooses the option 4 this method will be called
	// It will show the stock prices
	public void stockMenu() {
		try {
			BufferedReader br = new BufferedReader(
					new FileReader("C:\\Users\\Bruno\\eclipse-workspace\\AssignmentOne\\src\\stockPrice.txt"));
			String line = br.readLine();
			while ((line != null)) {
				System.out.println(line);
				line = br.readLine();
			}
		} catch (Exception e) {
		}
		navigationMenu();
	}

	// 5. |Transfer Between Accounts|
	// It allows the user to make transfer between users of the bank
	public void transferMenu() {
		Integer money;
		Integer recipientID;
		System.out.println("Hello, " + cFirstName + "." + "\n" + "You currently have $" + userInformation[3]);
		System.out.println("How much do you want to send?");
		money = Integer.valueOf(userInput());
		System.out.println("Transfer to user: ");
		recipientID = Integer.valueOf(userInput());
		secondTextFileName = "C:\\Users\\Bruno\\eclipse-workspace\\AssignmentOne\\src\\" + recipientID + ".txt";
		File textFile = new File(secondTextFileName);
		if (textFile.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(secondTextFileName));
				int i = 0;
				String line = br.readLine();
				while ((line != null)) {
					line = br.readLine();
					recipientInformation[i] = line;
					i++;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			cRecipientFirstName = recipientInformation[0];
			cRecipientSurName = recipientInformation[1];
			cRecipientPassword = recipientInformation[2];
			cRecipientBalance = recipientInformation[3];
			System.out.println(
					"Do you want to transfer $" + money + " to " + cRecipientFirstName + " " + cRecipientSurName + "?");
			yesNo();
			Integer newBalance = Integer.valueOf(userInformation[3]) - money;
			cBalance = String.valueOf(newBalance);
			newBalance = Integer.valueOf(recipientInformation[3]) + money;
			cRecipientBalance = String.valueOf(newBalance);
			FileWriter();
			System.out.println(
					cRecipientFirstName + "has received $" + money + "." + "\n" + "You currently have $" + cBalance);
			cRecipientFirstName = "";
			cRecipientSurName = "";
			cRecipientPassword = "";
			cRecipientBalance = "";
		} else {
			System.out.println("User ID not found" + "\n" + "Restarting process..." + "\n");
			cRecipientFirstName = "";
			cRecipientSurName = "";
			cRecipientPassword = "";
			cRecipientBalance = "";
			transferMenu();
		}

		navigationMenu();
	}

	public void yesNo() {
		System.out.println("|Yes|   |No| ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String currentInput = "";
		try {
			currentInput = br.readLine();
			while (currentInput.isEmpty() || currentInput.matches("[0-9]")) {
				System.out.println("Please, re-enter information: ");
				currentInput = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		if (currentInput.equals("No") || currentInput.equals("N") || currentInput.equals("n")
				|| currentInput.equals("no")) {
			navigationMenu();
		}
	}

	// 6.|Bank Summary|
	// It will show how much money the bank currently stores (total of all users'
	// balance)
	// and divide the balance in 4 categories
	public void bankSummary() {
		Double bankTotal = 0.00;
		int registeredUsers = 0;
		int smallGroup = 0;
		int mediumGroup = 0;
		int largeGroup = 0;
		int extraLargeGroup = 0;
		String bankInformation[] = new String[100];
		for (int nAcc = 0; nAcc <= 999; nAcc++) {
			textFileName = "C:\\Users\\Bruno\\eclipse-workspace\\AssignmentOne\\src\\" + nAcc + ".txt";
			File textFile = new File(textFileName);
			// Check if the file existed and can be read
			// Otherwise the program would try read files that don't exist
			if (textFile.canRead()) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(textFileName));
					int i = 0;
					String line = br.readLine();
					while ((line != null)) {
						line = br.readLine();
						bankInformation[i] = line;
						i++;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				bankTotal = bankTotal + Double.valueOf(bankInformation[3]);
				registeredUsers++;
				// If else to divide in 4 different categories
				if (Double.valueOf(bankInformation[3]) < 100) {
					smallGroup++;
				} else if (Double.valueOf(bankInformation[3]) > 100 && Double.valueOf(bankInformation[3]) <= 200) {
					mediumGroup++;
				} else if (Double.valueOf(bankInformation[3]) > 200 && Double.valueOf(bankInformation[3]) <= 300) {
					largeGroup++;
				} else if (Double.valueOf(bankInformation[3]) > 300) {
					extraLargeGroup++;
				}
			}
		}
		System.out.println("Bruno Ribeiro & CCT Bank currently has $" + bankTotal + " stored.");
		System.out.println("| " + registeredUsers + " | Registered accounts |");
		System.out.println("| " + smallGroup + " | with less than $100 |");
		System.out.println("| " + mediumGroup + " |between $101 and $200|");
		System.out.println("| " + largeGroup + " |between $201 and $300|");
		System.out.println("| " + extraLargeGroup + " | with more than $300 |" + "\n");
		navigationMenu();
	}

	// 7.|Log-out| 2.|Log-out|"
	// If the user chooses the option 7 on the main menu or 2 on other screens it
	// will be called
	// It cleans variables related to the users
	public void logout() {
		System.out.println("Goodbye, " + cFirstName + "!");
		cFirstName = "";
		textFileName = "";
		secondTextFileName = "";
		cFirstName = "";
		cSurName = "";
		cPassword = "";
		cBalance = "";
		cRecipientFirstName = "";
		cRecipientSurName = "";
		cRecipientPassword = "";
		cRecipientBalance = "";
		userLogin();
	}

	// userInput method will return the string currentInput
	// with validation of empty and whether the input is String or not
	public String userInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String currentInput = "";
		try {
			currentInput = br.readLine();
			// Validation, it will loop while the user don't enter any information and show
			// a message
			while (currentInput.isEmpty() || currentInput.matches("[a-z]")) {
				System.out.println("Please, re-enter information: ");
				currentInput = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		for (int clear = 0; clear < 100; clear++) {
			System.out.println("\n");
		}
		return currentInput;
	}

	// It displays an welcome message to the user, it has two options of welcome
	// messages
	public void welcomeText() {
		if (cFirstName == "") {
			System.out.println("Welcome to Bruno Ribeiro & CCT Bank!");
		} else {
			System.out.println("Welcome, " + cFirstName + "!");
		}
	}

	// This method will re-write the file using new information
	public void FileWriter() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(textFileName));
			writer.write("\n" + cFirstName + "\n" + cSurName + "\n" + cPassword + "\n" + cBalance);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(secondTextFileName));
			writer.write("\n" + cRecipientFirstName + "\n" + cRecipientSurName + "\n" + cRecipientPassword + "\n"
					+ cRecipientBalance);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// This method will be called from every menu
	// It shows to options |Return| and |Logout|
	public void navigationMenu() {
		System.out.println("1. |Return to Menu|");
		System.out.println("2. |    Log-out   |");
		menuOption = Integer.valueOf(userInput());
		while (menuOption > 2) {
			System.out.println("Option not found!" + "\n" + "Please, choose one of the above!");
			menuOption = Integer.valueOf(userInput());
		}
		if (menuOption == 1) {
			menu();
		} else if (menuOption == 2) {
			logout();
		}
	}

	public static void main(String[] args) {
		new AssignmentOne();
	}

}
