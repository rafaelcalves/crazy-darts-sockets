package com.uni.redes.server;

import java.io.*;
import java.net.*;

class Servidor {
	public static void main(String argv[]) throws Exception {
		
		String fraseCliente;
		String fraseMaiusculas;
		ServerGameManager game = null;

		ServerSocket socketRecepcao = new ServerSocket(6789);

		while(true) {
			Socket socketConexao = socketRecepcao.accept();
			BufferedReader doCliente = new BufferedReader(new InputStreamReader(new ObjectInputStream(socketConexao.getInputStream())));
			ObjectOutputStream paraCliente = new ObjectOutputStream(socketConexao.getOutputStream());
			fraseCliente= doCliente.readLine();
			if(fraseCliente.equals("newClient")){
				if(game == null){
					game = new ServerGameManager();
				}
				ServerPlayer serverPlayer = game.newPlayer();
				paraCliente.writeObject(serverPlayer);
			}
			fraseMaiusculas= fraseCliente.toUpperCase() + '\n';
			paraCliente.writeBytes(fraseMaiusculas);
		}
	}
}

