package com.uni.redes.client;

import java.io.*;
import java.net.*;

public class Cliente {

	public static void main(String argv[]) throws Exception {

		String frase="";
		String fraseModificada;

		BufferedReader doUsuario = new BufferedReader(new InputStreamReader(System.in));
		Socket socketCliente = new Socket("127.0.0.1", 6789);
		DataOutputStream paraServidor = new DataOutputStream(socketCliente.getOutputStream());
		//BufferedReader doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
		ObjectInputStream doServidorO = new ObjectInputStream(new BufferedInputStream(socketCliente.getInputStream()));
		while(!frase.equals("ESC")) {
			System.out.print("Digite uma frase: ");
			frase = doUsuario.readLine();
			paraServidor.writeBytes(frase + '\n');
			fraseModificada = doServidorO.readUTF();
			//fraseModificada = doServidor.readLine();
			System.out.println("Do Servidor: " + fraseModificada);
		}
		socketCliente.close();

	}
}

