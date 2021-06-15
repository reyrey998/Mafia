import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * this is the client class
 */
public class Client {
    /**
     *
     * @param matn we want to change it to byte
     * @return bytes of each char
     */
    public byte [] toBytesChanger(String matn){
        char [] chars = matn.toCharArray();
        byte [] bytes = new byte[chars.length] ;
        for (int i = 0; i < bytes.length ; i++) {
            bytes[i] = (byte) chars[i] ;
        }
        return bytes ;
    }

    /**
     * it is the text that client enter
     */
    private String payam;

    /**
     *here we try to connect to server and we continue get text from client and print all the entered text together.
     * @exception when they are disconnected
     */
    public void run(){
        try {
            Socket socket = new Socket("127.0.0.1" , 4020) ;
            while (true) {
                Scanner scanner = new Scanner(System.in);
                payam = scanner.next();
                socket.getOutputStream().write(this.toBytesChanger(payam + "\n"));
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                System.out.println("text:" + dataInputStream.readLine());
            }
            /**
             * here is the exception
             */
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }
}
