/************************************************/
/* Rutina de gestión de peticiones por el servidor "charlatán".
/* Práctica de comunicación de aplicaciones.
/* Redes de Datos - Grado en Informática - URV - Curso 2018-2019
/* Autores: Íñigo Arriazu, Bernat Boscá, German Eizaguirre
/************************************************/

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Random;

import ED.AI;
import ED.Tema;

public class AttendPetition extends Thread{
	Socket connectionSocket;
	private static int client_code=1;

	/* Constructor: recibe el socket como parámetro.
	 */
	public AttendPetition(Socket s){
		this.connectionSocket=s;
	} //Pasa por parámetro el socket y lo guarda.

	/* Rutina principal de gestión de la petición. Inicia el hilo de ejecución.
	 */
	public void run(){
		// Indica de qué cliente proviene la petición
		System.out.println("	* Petición recibida: "+ connectionSocket.toString() +"\n"
			+ "	* Código del cliente: " + client_code);
		System.out.println("\tcodigo\t> mensaje");

		int this_client_code=client_code;
		client_code++;
		// Método que ejecuta start.
		String clientSentence="";
		String flagTema = "D";
		String sentenceToClient = "\n";
		BufferedReader inFromClient;

		// Instancia una nueva AI
		AI inteligencia=new AI();
		inteligencia.inicializar_sistema();
		Tema tema_actual=null;
		int opcion = 0, mismoTema = 0, indice_frase = 0;
		int endFlag = 0, endTema = 0;
		
		try {

				//Establece un stream de entrada a través del socket (configurado en el hilo principal del servidor)
				inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				//Establce un stream de salida a través del socket.
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			try{
				clientSentence = inFromClient.readLine();
				System.out.println("\t(" + this_client_code + ")\t\t> " + clientSentence);
				clientSentence = inteligencia.normalizar_string(clientSentence);
			}
			catch (SocketTimeoutException e){

					System.out.println("\t("+this_client_code+")\t\t> " + "CONEXIÓN EXPIRADA");
					join();

			}

			try {

				//Comprueba frase de incio de servicio.
				while ((!clientSentence.equals("buenos dias")) && (!clientSentence.equals("buenas tardes"))) {
					outToClient.writeBytes(sentenceToClient);
					clientSentence = inFromClient.readLine();
					System.out.println("\t(" + this_client_code + ")\t\t> " + clientSentence);
				}

				// Responde a frase de inicio.
				if (clientSentence.equals("buenos dias")) sentenceToClient = "buenas tardes\n";
				else sentenceToClient = "buenos dias\n";

				outToClient.writeBytes(inteligencia.getNombre() + ": " + sentenceToClient);
				clientSentence = inFromClient.readLine();
				System.out.println("\t(" + this_client_code + ")\t\t> " + clientSentence);
				clientSentence = inteligencia.normalizar_string(clientSentence);
				//Comprueba frase de fin de servicio.
				while (!clientSentence.equals("buenas noches")) {


					// Ofrece tema.
					sentenceToClient = flagTema + "e que te gustaria hablar? " +
							"Matematicas, Biologia, Lengua, Historia, " +
							"Filosofia, Deporte, Actualidad, Invenciones...\n";
					outToClient.writeBytes(inteligencia.getNombre() + ": " + sentenceToClient);
					clientSentence = inFromClient.readLine();
					System.out.println("\t(" + this_client_code + ")\t\t> " + clientSentence);
					clientSentence = inteligencia.normalizar_string(clientSentence);
					// Comprueba frase de fin de servicio.
					if (!clientSentence.equals("buenas noches")) {
						opcion = inteligencia.contiene_tema(clientSentence);

						while ((endTema != 1) && (endFlag != 1)) {
							if (opcion != -1) {


								if (mismoTema == 0) {
									tema_actual = inteligencia.getTema(opcion);
									inteligencia.limpiar_registro(); //limpiar registro de resultados anteriores.
								}

								indice_frase = inteligencia.filtrar(inteligencia.normalizar_string(clientSentence));

								sentenceToClient = tema_actual.getFrase(indice_frase) + '\n';
							} else {
								// Si el tema elegido no está disponible responde una frase aleatoria.
								Random rand = new Random();
								int indice = rand.nextInt(7) + 1;
								tema_actual = inteligencia.getTema(indice);
								indice = rand.nextInt(4) + 1;
								sentenceToClient = tema_actual.getFrase(indice) + '\n';
							}

							outToClient.writeBytes(inteligencia.getNombre() + ": " + sentenceToClient);

							clientSentence = inFromClient.readLine();
							clientSentence = inteligencia.normalizar_string(clientSentence);
							if (clientSentence.equals("buenas noches")) endFlag = 1;

							System.out.println("\t(" + this_client_code + ")\t\t> " + clientSentence);

							// Ha repetido suficientes frases de un mismo tema.
							mismoTema++;
							if ((mismoTema > 4) && (endFlag != 1)) {
								sentenceToClient = "Quieres cambiar de tema? (Si no quieres di solo: No)\n";
								outToClient.writeBytes(inteligencia.getNombre() + ": " + sentenceToClient);
								clientSentence = inFromClient.readLine();
								clientSentence = inteligencia.normalizar_string(clientSentence);
								if (clientSentence.equals("buenas noches")) endFlag = 1;
								else {
									if (!clientSentence.equals("no")) endTema = 1;
									else mismoTema = 2;        //Para que no sea tan cansino siempre el mismo tema.
								}
							}
						}
						mismoTema = 0;
						endFlag = 0;
						endTema = 0;
						flagTema = "Cambiemos de tema. D";
					}
				}

				System.out.println("\t(" + this_client_code + ")\t\t> " + "DESCONEXION");
				//Cierra el socket de etse cliente.
				this.connectionSocket.close();
				join();
			}
			catch (SocketTimeoutException w){
				System.out.println("\t("+this_client_code+")\t\t> " + "CONEXIÓN EXPIRADA");
				join();
			}

		} catch (Exception e) {
			//Catch para problemas de conexión.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
