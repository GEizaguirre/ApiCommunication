/************************************************/
/* Servidor "charlatán" gestión de múltiples peticiones
/* Práctica de comunicación de aplicaciones.
/* Redes de Datos - Grado en Informática - URV - Curso 2018-2019
/* Autores: Íñigo Arriazu, Bernat Boscá, German Eizaguirre
/************************************************/
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

class TCPServerMultiThreading {
	public static void main(String argv[]) throws Exception {

		try {
			//Obtener parámetro de conexión.

			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Activando el servidor carlatán");
			System.out.print("\tIntroduzca puerto de comunicación >");
			Integer new_port=Integer.parseInt(inFromUser.readLine());
			ServerSocket welcomeSocket = new ServerSocket(new_port);
			System.out.println("*** Servidor charlatán activado ***");


			while (true) {
					Socket connectionSocket = welcomeSocket.accept();
					connectionSocket.setSoTimeout(10000);
					//Instrucción bloqueante, espera que algún cliente se le conecte.
					//Para hablar con el que se le ha conectado, utilizará el connectionSocket.
					//server bloqueado en accept. En cuanto llega petición abro thread con AttendPetition
					new AttendPetition(connectionSocket).start();
					//start  enciende en sí el thread.
			}
		}
		catch (java.net.BindException e){
			System.out.println(" ERROR: El puerto indicado ya está en uso. No se pudo activar el servidor.");
		}
	}
}