// Exp 10 - UDP Server (Receiver)
import java.io.*;
import java.net.*;
public class MServer {
  public static void main(String[] args) throws IOException {
    int sport = 0;
    InetAddress caddr = null;
    int cport = 0, currentA = 0, currentP = 0;
    boolean foundA, foundP;
    String sentence;
    int[] cports = new int[100];
    InetAddress[] caddrs = new InetAddress[100];
    String[] cname = new String[100];
    DatagramSocket sock = null;
    BufferedReader userIn = null;

    for(int i=0; i < cports.length; i++){
       cports[i]=0;
       cname[i]="";
    }
    // get server port number as the first argument.
    sport = Integer.parseInt(args[0]);

    sock = new DatagramSocket(sport);
    userIn = new BufferedReader(new InputStreamReader(System.in));
    byte[] sendbuf = new byte[1024];
    byte[] recvbuf = new byte[1024];

    System.out.println("Welcome to 100-Client Server Chat Service! \n");
    while (true) {
      found = false;
      System.out.println("Waiting for Client input: ");
      // create the UDP packet to receive data crom client
      DatagramPacket recvpkt = new DatagramPacket(recvbuf,recvbuf.length);
        
      sock.receive(recvpkt);
      // convert the received data into string text 
      String recvdata = new String(recvpkt.getData());

      // extract the client IP Address and port number
      caddr = recvpkt.getAddress();
      cport = recvpkt.getPort();
      //Search whether port already added
      for(int i=0; i< cports.length ;i++){
         if(cport == cports[i]){
            foundP = true;
            current = i;
            break;
         }
      }
      //Add the port if not added
      if(found == false){
         for(int i=0; i< cports.length ;i++){
            if(cports[i] == 0){
               cports[i] = cport;
               current = i;
               cname[i] = "Client "+ (i+1);
               break;
            }
         }
      }
      
      System.out.println(caddr.toString() + ":" + cport);
      System.out.println(cname[current] + " : " + recvdata);

      // Auto Reply to client
      System.out.println("Me : ");
      sentence = userIn.readLine();
      sendbuf = sentence.getBytes();
      // create the UDP packet to be sent and send it on socket
      DatagramPacket sendpkt = new DatagramPacket(sendbuf,sendbuf.length,caddr,cport);
      sock.send(sendpkt);
      // initialize the receive and send buffer
      for (int i=0; i< sendbuf.length; i++) {
      	sendbuf[i] = 0;
      }
      for (int i=0; i< recvbuf.length; i++) {
      	recvbuf[i] = 0;
      }
    }
  }
}

