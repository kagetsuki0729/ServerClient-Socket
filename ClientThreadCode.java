import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientThreadCode extends Thread
{
    private Socket m_socket;//�M���A���ݶi��s�u
    
    public ClientThreadCode(String ip, int port)
    {
        try
        {
            m_socket = new Socket(ip, port);//�إ߳s�u�C(ip�����A���ݪ�ip�Aport�����A���ݶ}�Ҫ�port)
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
            if (m_socket != null)//�s�u���\�~�~�򩹤U����
            {

                //�ѩ�Server�ݨϥ� PrintStream �M BufferedReader �ӱ����M�H�e�T���A�ҥHClient�ݤ]�n�ۦP
                PrintStream writer = new PrintStream(m_socket.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
            
                writer.println("Hello");
                writer.flush();
                System.out.println("Server:" + reader.readLine());
            
                m_socket.close();

            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}