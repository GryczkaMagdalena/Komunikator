/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

public class AndroidSerwer {
    static int port;
    static int SIZE;
    static InetAddress IP;
    static DatagramPacket temp_msg;

    private static String getIPfromXML(DatagramPacket p) {
        String msg = new String(p.getData(), p.getOffset(), p.getLength());

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(msg);

            return doc.getElementsByTagName("ip").item(0).getTextContent();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String();
    }


    public static void main(String[] args) {
        port = 6000;
        SIZE = 200;

        //wyświetlanie lokalnego adresu IP
        try{
            InetAddress IP1 = InetAddress.getLocalHost();
            System.out.println(IP1.getHostAddress());
        }
        catch(UnknownHostException e) {
            System.out.print(e);
        }

        //obsługa requestów
        try {
            DatagramSocket soc = new DatagramSocket(port);
            System.out.println( "The server is ready..." ) ;

            while(true) {
                DatagramPacket p = new DatagramPacket(new byte[SIZE], SIZE); //tworzenie pakietu
                soc.receive(p); //odbierz pakiet - blokowanie
                //IP = InetAddress.getByName(getIPfromXML(p));
                //p.setAddress(IP);
                
                //p.setAddress(InetAddress.getByName(getIPfromXML(p)));
        	String msg = new String(p.getData(), p.getOffset(), p.getLength());
                System.out.println(msg) ;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
