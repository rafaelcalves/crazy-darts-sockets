package com.uni.redes;

import java.io.*;
import java.net.*;

class Servidor {
	private static String moves(String move){

		return Integer.toString((int)(Math.random() * ( 10 - 1 )) + 1);
	}

	public static void main(String argv[]) throws Exception {

		String code;
		String[] moveP1 = new String[3];
		String[] moveP2 = new String[3];
		String points1, points2;
		int pointP1 =  0, pointP2 = 0;

		//Constructor
		ServerSocket socketServer = new ServerSocket(6789);

		//"main"
		while(true) {

			System.out.print("\nNew game\nWaiting players\n");

			//Creating a connection with client 1
			Socket socketClient1 = socketServer.accept();
			System.out.print("Client1 connected\n");

			//Client 1 communication settings
			BufferedReader readClient1 = new BufferedReader(new InputStreamReader(socketClient1.getInputStream()));
			DataOutputStream sendClient1 = new DataOutputStream(socketClient1.getOutputStream());

			//Warning client 1
			sendClient1.writeBytes("1\n");
			System.out.print("Client 1 has warned\n");

			//Creating a connection with client 2
			Socket socketClient2 = socketServer.accept();
			System.out.print("Client2 connected\n");

			//Client 2 communication settings
			BufferedReader readClient2 = new BufferedReader(new InputStreamReader(socketClient2.getInputStream()));
			DataOutputStream sendClient2 = new DataOutputStream(socketClient2.getOutputStream());

			//Warning client 2
			sendClient2.writeBytes("2\n");
			System.out.print("Client 2 has warned\n");

			int cod = 3;

			for(int i = 0; i < 3; i++){

				//Client 1  move
				sendClient1.writeBytes(Integer.toString(cod) + "\n");
				System.out.print("Client 1 has warned to play your move\n");
				cod++;

				//Read move 1 client 1
				moveP1[0] = readClient1.readLine();
				System.out.println("Move of player 1: " + moveP1[0]);

				//Client 1 points of first move
				points1 = moves(moveP1[0]);
				pointP1 += (Integer.parseInt(points1));
				sendClient1.writeBytes(points1 + '\n');
				System.out.println("Client 1 has warned about your points: " + points1);

				//Client 2 move
				sendClient2.writeBytes(Integer.toString(cod) + "\n");
				System.out.print("Client 2 has warned to play your move\n");
				cod++;

				//Read move 1 client 2
				moveP2[0] = readClient2.readLine();
				System.out.println("Move of player 2: " + moveP2[0]);

				//Client 2 points of first move
				points2 = moves(moveP2[0]);
				pointP2 += (Integer.parseInt(points2));
				sendClient2.writeBytes(points2 + '\n');
				System.out.println("Client 2 has warned about your points: " + points2);

				//Client 1 e 2 receive the points of opponent
				sendClient1.writeBytes(points2 + '\n');
				System.out.println("Client 1 has warned about points of opponent: " + points2);

				sendClient2.writeBytes(points1 + '\n');
				System.out.println("Client 2 has warned about points of opponent: " + points1);

				points1 = null;
				points2 = null;
			}


			//Client 1 e 2 results
			if(pointP1 == pointP2){
				sendClient1.writeBytes("10\n");
				sendClient2.writeBytes("10\n");
				System.out.print("Client 1 e 2 have warned that they drawn\n");
			} else if (pointP1 > pointP2) {
				sendClient1.writeBytes("11\n");
				sendClient2.writeBytes("9\n");
				System.out.print("Client 1 e 2 have warned that P1 won\n");
			} else {
				sendClient1.writeBytes("9\n");
				sendClient2.writeBytes("11\n");
				System.out.print("Client 1 e 2 have warned that P2 won\n");
			}

			//Client 1 e 2 receive your points
			sendClient1.writeBytes(Integer.toString(pointP1) + '\n');
			sendClient2.writeBytes(Integer.toString(pointP2) + '\n');
			System.out.println("Client 1 e 2 have warned about their total points");

			//Client 1 e 2 receive the points of opponent
			sendClient1.writeBytes(Integer.toString(pointP2) + '\n');
			sendClient2.writeBytes(Integer.toString(pointP1) + '\n');
			System.out.println("Client 2 has warned about total points of opponent");


			//Closing connections
			socketClient1.close();
			socketClient2.close();
		}
	}
}

