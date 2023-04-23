import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    static PrintWriter out = null;
    static BufferedReader bufferedReader = null;
    static ServerSocket serverSocket = null;
    static Socket clientSocket = null;

    public static void main(String[] args) {
        int port = 8059;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                clientSocket = serverSocket.accept();
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("New connection accepted");
                out.println("Whats you name?");
                final String name = bufferedReader.readLine();
                out.println("Hello " + name);
                out.println("Are you child (yes/no) ?");
                String answer = bufferedReader.readLine();
                if (answer.equals("yes")) {
                    kidZone(name);
                } else {
                    adultZone(name);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void kidZone(String name) throws IOException {
        out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
        out.println(String.format("%s, how old are you?", name));
        bufferedReader.readLine();
        out.println("What is your favourite game?");
        out.println("Good choice " + bufferedReader.readLine());
        out.println("Good,See you soon,bye");

    }

    public static void adultZone(String name) throws IOException {
        out.println(
                String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
        out.println("How old are you?");
        bufferedReader.readLine();
        out.println("Do you drink alcohol (yes/no)?");
        if (bufferedReader.readLine().equals("yes")) {
            out.println("хорош");
        } else {
            out.println("Хорошо что ты не пьешь");
        }
        out.println("Meet me another time,bye");

    }
}
