/*
 * Rizma Nurviarelda (1301144229)
 * IF-38-09
 */
package consoleApp;

import javaChat.ClientConnection;

/**
 *
 * @author ASUS
 */
public class ConsoleApplication {
    private ClientConnection client;
    
    class ReadInput extends Thread {
        @Override
        public void run() {
            try {
                String inputKeyboard;
                do {
                    System.out.println(">> ");
                    inputKeyboard = client.inputString();
                    client.writeStream(inputKeyboard);
                } while (!inputKeyboard.equals("quit"));
                client.disconnect();
                } 
            catch (Exception e) {
                    System.out.println("Error");
            }
        }
    }
    
    class WriteOutput extends Thread {
        @Override
        public void run () {
            try {
                String inputan;
                while ((inputan = client.readStream()) != null) {
                    System.out.println(inputan);
                    System.out.println(">> ");
                }
            }
            catch (Exception e) {
                System.out.println("Error");
            }
        }
    }
    
    public void startChat() {
        try {
            client = new ClientConnection();
            System.out.println("Input server IP: ");
            String ip = client.inputString();
            client.connect(ip);
            ReadInput in = new ReadInput();
            WriteOutput out = new WriteOutput();
            in.start();
            out.start();
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }
}
