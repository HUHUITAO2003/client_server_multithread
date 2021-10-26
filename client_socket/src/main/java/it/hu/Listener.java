package it.hu;

import java.io.*;
import java.net.*;

public class Listener extends Thread {
    Socket miosocket;

    public Listener(Socket miosocket) {
        this.miosocket = miosocket;
    }

    public void run() {
        for (;;) {
            System.out.println(miosocket.isConnected());
            if (miosocket.isConnected()!=true || miosocket.isClosed()) {
                    System.out.println(miosocket.isConnected());
                    System.out.println("il server è stato chiuso da un altro client");
                    try {
                        miosocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
