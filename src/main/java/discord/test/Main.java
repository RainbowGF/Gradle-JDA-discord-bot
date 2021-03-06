package discord.test;

import discord.test.models.OSValidator;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		OSValidator os = new OSValidator();
		Scanner scanner;
		String currentDirectory = System.getProperty("user.dir");
		File file;
		
		if (os.getUserOS() == 2 || os.getUserOS() == 3) {
			System.out.println("Starting on linux/mac");
			file = new File(currentDirectory + "/token.txt");
		}
		else {
			if (os.getUserOS() != 1)
				System.err.println("Can not figure out what OS you are using. Bot do not support Solaris. \nProgram will try to start, assuming that your OS is Windows.");
			System.out.println("Starting on Windows");
			file = new File(currentDirectory + "\\token.txt");
		}
		try {
			scanner = new Scanner(new FileInputStream(file));
			String[] strings = scanner.nextLine().strip().split("[\\s=]+");
			new Bot(strings[strings.length-1]);
		} catch (FileNotFoundException | NoSuchElementException e) {
			System.err.println("\nError: File token.txt not found in: "+file.getAbsolutePath() +"\nCreate token.txt if its not created, and write your bot token there");
			System.exit(0);
		}
		catch (LoginException e) {
			System.err.println("\nError: Your bot token isn't working. Make sure that token in token.txt is right");
			System.exit(0);
		}
	}
	
}
