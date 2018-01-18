import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThreadCode extends Thread
{
        private ServerSocket m_serverSocket;
        private Socket m_socket;

        public ServerThreadCode(int port)
        {
                try
                {
                        m_serverSocket = new ServerSocket(port);
                }
                catch (IOException e)
                {
                        System.out.println(e.getMessage());
                }
        }

        @Override
                public void run()
                {
                        try
                        {
                                while(true){
                                        System.out.println("Wait for connect......");
                                        m_socket = m_serverSocket.accept();
                                        System.out.println("Connect success!");



                                        PrintStream writer;
                                        BufferedReader reader;

                                        writer = new PrintStream(m_socket.getOutputStream());


                                        reader = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));

                                        String input=reader.readLine();
                            String returnMessage=new String();
                                        float bmi=Float.parseFloat(input.split(" ")[0]);
                                        if(bmi<18.5)
                                                returnMessage+="You are too thin!";
                                        else if(bmi>=18.5&&bmi<24)
                                                returnMessage+="Nice, your are healthy!";
                                        else
                                                returnMessage+="Your are over weight!";

                                        System.out.println("Client: " + input);
                                        System.out.println("Output: " + returnMessage);
                                        writer.println(returnMessage);
                                        writer.flush();
                                        m_socket.close();

                                }
                        }
                        catch (IOException e)
                        {
                                System.out.println(e.getMessage());
                                try{
                                        m_serverSocket.close();
                                }
                                catch(Exception e2){
                                        System.out.println(e2.getMessage());
                                }
                        }
                }
}
