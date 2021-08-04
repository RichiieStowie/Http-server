import java.io.*;
import java.net.Socket;
import java.nio.file.Path;

public class ClientThread implements Runnable{
    Socket clientRequest;
    String resource="";
    String fileStore="";


    public ClientThread(Socket clientRequest) throws IOException  {
        this.clientRequest = clientRequest;
    }
    StringBuffer request= new StringBuffer();

    @Override
    public void run() {
      try {
          BufferedReader br= new BufferedReader(new InputStreamReader(clientRequest.getInputStream()));
          String line="";
          line= br.readLine();
          if(line!=null){
              request.append(line + "\r\n");
              System.out.println(request);
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
        String firstLine= request.toString().split("\n")[0];
      String newLine = firstLine.split(" ")[1];


      //Getting the requested data
       if(newLine.equals("/html")){
          fileStore="/Users/decagon/Desktop/week_4/week5/Resources/index.html";
           try {
         BufferedReader fileReader= new BufferedReader(new FileReader(fileStore));

           OutputStream clientOutput= clientRequest.getOutputStream();
           clientOutput.write(("HTTP/1.1 200 OK\r\n").getBytes());
           clientOutput.write(("\r\n").getBytes());
           while(true){
               String returnData= fileReader.readLine();
               if(returnData==null){
                   break;
               }
               clientOutput.write(returnData.getBytes());
           }
               clientOutput.flush();
           clientOutput.close();

           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       else if(newLine.equals("/json")){
           fileStore= "/Users/decagon/Desktop/week_4/week5/Resources/Http.json";
           try {
               BufferedReader fileReader= new BufferedReader(new FileReader(fileStore));
               OutputStream clientOutput= clientRequest.getOutputStream();
               clientOutput.write(("HTTP/1.1 200 OK\r\n").getBytes());
               clientOutput.write(("\r\n").getBytes());
               while(true){
                   String returnData= fileReader.readLine();
                   if(returnData==null){
                       break;
                   }
                   clientOutput.write(returnData.getBytes());
               }
               clientOutput.flush();
               clientOutput.close();


           } catch (IOException e) {
               e.printStackTrace();
           }

       }
       else{
           try {
               OutputStream clientOutput= clientRequest.getOutputStream();
               clientOutput.write("HTTP/1.1 404 Not Found!\r\n".getBytes());
               clientOutput.flush();
               clientOutput.close();

           } catch (IOException e) {
               e.printStackTrace();
           }
       }

    }
}
