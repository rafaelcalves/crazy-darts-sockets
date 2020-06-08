package com.uni.redes.server;

import java.io.*;
import java.net.*;

class Servidor {
	public static void main(String argv[]) throws Exception {
		
		String fraseCliente;
		String fraseMaiusculas;

		ServerSocket socketRecepcao = new ServerSocket(6789);

		while(true) {
			Socket socketConexao = socketRecepcao.accept();
			BufferedReader doCliente = new BufferedReader(new InputStreamReader(socketConexao.getInputStream()));
			DataOutputStream paraCliente = new DataOutputStream(socketConexao.getOutputStream());
			ObjectOutputStream paraClienteO = new ObjectOutputStream(socketConexao.getOutputStream());
			while(true) {
				fraseCliente = doCliente.readLine();
				fraseMaiusculas = fraseCliente.toUpperCase() + '\n';
				paraClienteO.writeUTF(fraseMaiusculas);
				//paraCliente.writeBytes(fraseMaiusculas);
			}
		}
	}
}

