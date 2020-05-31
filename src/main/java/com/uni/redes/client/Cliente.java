package com.uni.redes.client;

import com.uni.redes.server.ServerPlayer;

import java.io.*;
import java.net.*;

public class Cliente {

	public static void main(String argv[]) throws Exception {
		ServerPlayer player = null;

		String frase;
		String fraseModificada;

		BufferedReader doUsuario = new BufferedReader(new InputStreamReader(System.in));
		Socket socketCliente = new Socket("127.0.0.1", 6789);
		DataOutputStream paraServidor = new DataOutputStream(new ObjectOutputStream(socketCliente.getOutputStream()));
		ObjectInputStream doServidor = new ObjectInputStream(socketCliente.getInputStream());

		paraServidor.writeBytes("newClient");
		player = (ServerPlayer) doServidor.readObject();

		System.out.print("Digite uma frase: ");
		frase = doUsuario.readLine();
		paraServidor.writeBytes(frase + '\n');
		fraseModificada = doServidor.readLine();
		System.out.println("Do com.uni.redes.server.Servidor: " + fraseModificada);
		socketCliente.close();

	}
}

