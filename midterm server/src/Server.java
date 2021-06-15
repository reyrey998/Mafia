/**
 * @author Reyhaneh jarchizadeh
 * 9531310
 * Server and ClientHandler and Main class
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;

/**
 * in class vasl shodane chand client ra modiriat mikonad.
 */
class ClientHandler implements Runnable{
private Socket connectionServer;
private int clientNumber;
private Player player;

    /**
     *
     * @param connectionServer server ma
     * @param clientNumber tedade baziconan
     */
    public ClientHandler(Socket connectionServer,int clientNumber){
    this.clientNumber=clientNumber;
    this.connectionServer=connectionServer;
}

    /**
     * method run ra ke dar runnable bud override kardim
     */
    @Override
    public void run() {
    try {
        DataInputStream in = new DataInputStream(new BufferedInputStream(connectionServer.getInputStream()));
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(connectionServer.getOutputStream()));
        String str = "";
        String temp = "";
    while(in.available() > 0){
            temp = in.readLine();
        str += temp + " ";
        System.out.println("Text :"+str);
        connectionServer.getOutputStream().write(this.toBytes(str+"\n"));}
    connectionServer.close();
    }
    /**
     * exception ra modiriat mikonad ke dar soorate disconnect shodane client ijad mishavad
     */
    catch (IOException e) {
        e.printStackTrace();
    }

    }

    /**
     * voroudi char ra be byte tabdil mikonad
     * @param matn vorodi client
     * @return noe khorouji method
     */
    public byte [] toBytes(String matn){
        char [] chars = matn.toCharArray();
        byte [] bytes = new byte[chars.length] ;
        for (int i = 0; i < bytes.length ; i++) {
            bytes[i] = (byte) chars[i] ;
        }
        return bytes ;
    }
}

/**
 * class asli server ma ke modiriate naghsh ha va bazi be ebarati naghsh hara taghsim mikonad
 *
 */
public class Server {
    public static void main(String [] args){
        Scanner scanner=new Scanner(System.in);
        int clientNumbers=scanner.nextInt();
        ExecutorService pool= Executors.newCachedThreadPool();

        try (ServerSocket wlcServer= new ServerSocket(4040)) {
            System.out.println("Server started! it is hearing ...");
            int counter = 0;
            /**
             * doone doone clint ha vasl mishavand
             */
            while (counter < clientNumbers) {
                Socket connectionServer = wlcServer.accept();
                counter++;
                System.out.println("Client" + counter + "is connected.");
                pool.execute(new ClientHandler(connectionServer, counter));
            }
            /**
             * yek sevome jamiat ra be soorate random mafia mikonad va aval naghshhaye asli mafia ra tozi mikonad va be baghie mafiaye sade nesbat midahad.
             */
            for (int k=0; k<(clientNumbers/3); k++){
                Random rand=new Random();
                int x=rand.nextInt((clientNumbers/3));
                if(x==0)
                    System.out.println("Godfather");
                else if (x==1)
                    System.out.println("doctorlecter");
                    if(1<x && x>(clientNumbers/3))
                        System.out.println("MAFIAYE SADE");
                    }
/**
 * baghie jamiat ra random shahr mikinad va pas az taghsime naghsh haye asli be baghie shahrvande sade mikdahad.
 */
            for (int k= (clientNumbers/3); k<clientNumbers; k++){
                Random rand=new Random();
                int x=rand.nextInt(clientNumbers)+(clientNumbers/3);
                if (x==(clientNumbers/3))
                    System.out.println("doctor");
                if (x==(clientNumbers/3)+1)
                    System.out.println("karagah");
                if (x==(clientNumbers/3)+2)
                    System.out.println("herfeii");
                if (x==(clientNumbers/3)+3)
                    System.out.println("jansakht");
                if (x==(clientNumbers/3)+4)
                System.out.println("shahrdar");
                if (x==(clientNumbers/3)+5)
                    System.out.println("ravanshenas");
                if (x>((clientNumbers/3)+5)&& x<clientNumbers)
                    System.out.println("SHAHRVANDE SADE");
                }
            /**
             * exception ra migirad
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
/**
 * server ha baste mishavand
 */
        pool.shutdown();
            System.out.println("Server is closed.");

System.out.println("Done.");

    }}

