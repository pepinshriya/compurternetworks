import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
public class dnsserver {

    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData;

        HashMap<String, String> dnsTable = new HashMap<>();
        dnsTable.put("example.com", "93.184.216.34");
        dnsTable.put("google.com", "142.250.182.206");
        dnsTable.put("openai.com", "104.18.12.123");

        System.out.println("DNS Server is running...");

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String domain = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Received domain: " + domain);

            String ip = dnsTable.getOrDefault(domain, "Domain not found");
            sendData = ip.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(
                    sendData, sendData.length,
                    receivePacket.getAddress(), receivePacket.getPort()
            );

            serverSocket.send(sendPacket);
        }
    }

}
