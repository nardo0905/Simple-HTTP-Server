import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {

        final ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080...");
        while (true) {
            try (final Socket client = server.accept();) {
                // Reading request
                InputStreamReader isr = new InputStreamReader(client.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                String line = reader.readLine();
                while (!line.isEmpty()) {
                    System.out.println(line);
                    line = reader.readLine();
                }
                // Sending response
                Date today = new Date();
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                client.getOutputStream().write(httpResponse.getBytes("UTF-8"));
            }
        }

    }
}
