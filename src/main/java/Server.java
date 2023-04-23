import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) {
        int port = 8059;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); 
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.println("New connection accepted");
                    out.println("Whats you name?");
                    final String name = bufferedReader.readLine();
                    out.println("Hello " + name);
                    out.println("Are you child (yes/no) ?");
                    String answer = bufferedReader.readLine();
                    if (answer.equals("yes")) {
                        kidZone(name,out,bufferedReader);
                    } else {
                        adultZone(name,out,bufferedReader);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void kidZone(String name,PrintWriter pw,BufferedReader bf) throws IOException {
        pw.println(String.format("Welcome to the kids area, %s! Let's play!", name));
        pw.println(String.format("%s, how old are you?", name));
        bf.readLine();
        pw.println("What is your favourite game?");
        pw.println("Good choice " + bf.readLine());
        pw.println("Good,See you soon,bye");

    }

    public static void adultZone(String name,PrintWriter pw,BufferedReader bf) throws IOException {
        pw.println(
                String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
        pw.println("How old are you?");
        bf.readLine();
        pw.println("Do you drink alcohol (yes/no)?");
        if (bf.readLine().equals("yes")) {
            pw.println("хорош");
        } else {
            pw.println("Хорошо что ты не пьешь");
        }
        pw.println("Meet me another time,bye");

    }
}
