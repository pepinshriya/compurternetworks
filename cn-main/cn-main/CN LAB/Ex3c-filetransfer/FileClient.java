import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5000);
            System.out.println("Connected to server.");

            InputStream is = s.getInputStream();
            FileOutputStream fos = new FileOutputStream("received.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();

            System.out.println("File received successfully.");

            bos.close();
            fos.close();
            is.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
