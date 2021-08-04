import java.io.IOException;
import java.net.ServerSocket;

public class App {
   public static void main(String[] args) throws IOException {
     WebServer webServer= new WebServer(8080);
     webServer.start();
      System.out.println("accepted");
   }


}
