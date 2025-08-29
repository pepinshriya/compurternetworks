import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server started, waiting for client...");
            Socket s = ss.accept();
            System.out.println("Client connected.");

            // Open file to send
            FileInputStream fis = new FileInputStream("send.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            OutputStream os = s.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();

            System.out.println("File sent successfully.");

            bis.close();
            fis.close();
            os.close();
            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
