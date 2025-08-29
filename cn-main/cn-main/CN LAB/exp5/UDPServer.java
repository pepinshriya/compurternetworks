import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(5000);
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            System.out.println("UDP Server started on port 5000, waiting for client...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                ds.receive(receivePacket);
                String clientMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("CLIENT: " + clientMsg);

                if (clientMsg.equalsIgnoreCase("stop")) {
                    System.out.println("Server stopping...");
                    break;
                }

                String reply = "Echo: " + clientMsg;
                sendData = reply.getBytes();

                InetAddress clientAddr = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddr, clientPort);
                ds.send(sendPacket);
            }
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
