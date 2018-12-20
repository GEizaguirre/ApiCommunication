/************************************************/
/* Rutina del cliente del servidor "charlatán"
/* Práctica de comunicación de aplicaciones.
/* Redes de Datos - Grado en Informática - URV - Curso 2018-2019
/* Autores: Íñigo Arriazu, Bernat Boscá, German Eizaguirre
/************************************************/
import java.io.*;
import java.net.*;

//En este ejemplo, Server y Client en la misma máquina.
class TCPClient {


 public static void main(String argv[]) throws Exception {


  String sentence;
  String modifiedSentence;
  String new_ip="";
  Integer new_port;

  try {
   BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

   //Obtener parámetros de conexión.
   System.out.println(" Servicio de conexión con el servidor charlatan. Introduzca los parámetros de conexión: ");
   System.out.print("\tDirección IP del servidor > ");
   new_ip=inFromUser.readLine();
   System.out.print("\tPuerto de acceso > ");
   new_port=Integer.parseInt(inFromUser.readLine());
   //Por teclado.

   Socket clientSocket = new Socket(new_ip, new_port);
   //1234: puerto
   //localhost deberá ser una IP -> DESTINO
   //Espera si ha podido crear la conexión o no

   DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

   BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
   //Abstracción Java: da igual que BufferedReader venga de teclado o de la red de datos.

   System.out.println("*** Conectado con servidor charlatán ***");


   sentence = inFromUser.readLine();

   try {
    clientSocket.setSoTimeout(12000);
    while (!sentence.equals("buenas noches")) {
     outToServer.writeBytes(sentence + '\n');
     //Pongo '\n' porque el servidor es buffered.
     modifiedSentence = inFromServer.readLine();
     //Espero a que servidor me envíe.
     if (!modifiedSentence.equals("")) System.out.println(">" + modifiedSentence);
     sentence = inFromUser.readLine();
    }


   outToServer.writeBytes(sentence + '\n');
   //Pongo '\n' porque el servidor es buffered.

   }
   catch(SocketTimeoutException e){
    System.out.println("Conexión expirada.");
   }

   clientSocket.close();
  } catch (java.net.ConnectException e) {
       System.out.println("ERROR: Conexión fallida. El cliente no se pudo conectar.");
  }
 }

}