package it.hu;

import java.io.*;
import java.net.*;

public class ClientThread extends Thread{
    BufferedReader tastiera;// buffered per memorizzare la stringa ottenuta da tastiera
    Socket miosocket;
    String stringaUtente;// stringa inserita dal client
    String stringaRicevutaDalServer;// stringa ricevuta dal server
    DataOutputStream outVersoServer;// stream output
    BufferedReader inDalServer;// stream input

    public ClientThread(Socket miosocket, BufferedReader tastiera, DataOutputStream outVersoServer, BufferedReader inDalServer) {
        this.miosocket = miosocket;
        this.tastiera = tastiera;
        this.outVersoServer = outVersoServer;
        this.inDalServer = inDalServer;
    }

    public void run(){
        for (;;) {
            try {// leggo una riga
                
                System.out.println("4 ... inserisci la stringa da trasmettere al server: ");
                stringaUtente = tastiera.readLine();// input tastiera

                System.out.println("5 ... invio la stringa al server e attendo ...");
                outVersoServer.writeBytes(stringaUtente + '\n');// invio della stringa
                stringaRicevutaDalServer = inDalServer.readLine();
                System.out.println("7 ... risposta dal server " + '\n' + stringaRicevutaDalServer);// lettura stringa di risposta
                if (stringaUtente.equals("FINE") || stringaUtente.equals("STOP")) {
                    System.out.println("8 CLIENT: termina elaborazione e chiude connessione");// chiusura connessione
                    miosocket.close();
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Errore durante la comunicazione con il server!");
                System.exit(1);
            }
        }

    }
}