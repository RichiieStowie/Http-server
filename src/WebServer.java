import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

//created to handle multiple requests
public class WebServer extends Thread{
    //Ready to receive messages
    ServerSocket server;
    public WebServer(int port) throws IOException {
        this.server = new ServerSocket(port);
    }

    @Override
    public void run() {
       try {
           Executor executor = Executors.newFixedThreadPool(10);
           while (true){
               executor.execute(new ClientThread(server.accept()));
           }

       }catch(IOException e){
           e.printStackTrace();
       }
    }

    //Read the request and listen to the message

    //Decide how we would like to respond

    //change the route
}
