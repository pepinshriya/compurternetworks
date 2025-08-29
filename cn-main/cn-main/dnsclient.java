import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class dnsclient {

    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter domain name: ");
        String domain = scanner.nextLine();

        byte[] sendData = domain.getBytes();
        byte[] receiveData = new byte[1024];

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("IP Address: " + response);

        clientSocket.close();
        scanner.close();
    }
}
    
