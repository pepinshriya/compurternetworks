import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            InetAddress serverAddr = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);

            byte[] sendData;
            byte[] receiveData = new byte[1024];

            while (true) {
                System.out.print("TO SERVER: ");
                String msg = sc.nextLine();
                sendData = msg.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddr, 5000);
                ds.send(sendPacket);

                if (msg.equalsIgnoreCase("stop")) {
                    break;
                }

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                ds.receive(receivePacket);
                String reply = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("SERVER: " + reply);
            }
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
