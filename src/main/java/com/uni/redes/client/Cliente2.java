package com.uni.redes.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente2 {

	public static void main(String argv[]) throws Exception {

		String messages[] = {
				"Your player has connected\n"
				, "Waiting other player\n"
				, "Player 2 has connected\n"
				, "\nThe game has started\n\nPlayer 1 make your first move:\n"
				, "\nThe game has started\n\nPlayer 2 make your first move:\n"
				, "\nPlayer 1 make your second move:\n"
				, "\nPlayer 2 make your second move:\n"
				, "\nPlayer 1 make your third move:\n"
				, "\nPlayer 2 make your third move:\n"
				, "\nYou lose :(\n"
				, "\nDrawn\n"
				, "\nYOU WON!\n"
		};
		String code, points, option, moveX, moveY;
		boolean gameIsRunning = true;

		//Creating a connection with server
		Socket socketServer = new Socket("127.0.0.1", 6789);
		System.out.print("Server has connected\n");

		//Server communication settings
		DataOutputStream sendServer = new DataOutputStream(socketServer.getOutputStream());
		BufferedReader readServer = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));

		//User communication settings
		BufferedReader readUser = new BufferedReader(new InputStreamReader(System.in));

		//The game
		while (gameIsRunning) {

			//Menu
			System.out.print("\n-------------------------------------------------------------------------------------");
			System.out.print("\nNew game\n");

			System.out.println("\nMenu\n");
			System.out.println("Start       -> Type '1'");
			System.out.println("Instruction -> Type '2'");
			System.out.println("About       -> Type '3'");
			System.out.println("Quit        -> Type '4'");

			//Read user input
			option = readUser.readLine();

			switch (option) {
				case "1":
					//Read server message
					code = readServer.readLine();

					//Waiting other player
					if (code.equals("1")) {
						System.out.print(messages[0]);
						System.out.print(messages[1]);
					} else if (code.equals("2")) {
						System.out.print(messages[0]);
					}

					for(int i = 0; i < 3; i++){

						System.out.println("Round " + (i+1) + "!");

						//Read server message
						code = readServer.readLine();
						//First move
						if (code.equals("3")) {
							System.out.print(messages[2]);
							System.out.print(messages[3]);
						}
						else {
							System.out.print(messages[Integer.parseInt(code)]);
						}

						//Read X1
						System.out.print("X: ");
						moveX = readUser.readLine();

						//Read Y1
						System.out.print("Y: ");
						moveY = readUser.readLine();

						//Sending to server
						sendServer.writeBytes("X" + moveX + "_Y" + moveY +'\n');

						//Read server points
						points = readServer.readLine();
						System.out.println("You did: " + points + " points");

						points = readServer.readLine();
						System.out.println("Opponent did: " + points + " points");
					}

					//Read server result
					code = readServer.readLine();
					System.out.print(messages[Integer.parseInt(code)]);

					//Read server points
					points = readServer.readLine();
					System.out.println("You did: " + points + " total points");

					points = readServer.readLine();
					System.out.println("Opponent did: " + points + " total points");

					break;
				case "2":
					//Instructions
					System.out.println("\nWelcome to Crazy Darts Sockets.\n\nTwo players throw their darts by entering x and y, each player has three moves." +
							" A player 1 move and then a player 2 move. Blablabla\n");
					break;
				case "3":
					//About
					System.out.println("\nThis is a computer network work and it was developed by Fábio Krein and Rafael Côrrea. Blablabla\n");
					break;
				case "4":
					//Quit
					gameIsRunning = false;
					System.out.println("\nEnd Game");

					//Closing connections
					socketServer.close();
					break;
				default:
					System.out.println("\nIncorrect option, please enter a valid option.");
			}
		}
	}
}

