
package agenciaviajes.acceso;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yerson
 */
public class ServivioServidorCentral implements IServidorCentral{
    
    private Socket socket = null;
    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private final String IP_SERVIDOR = "localhost";
    private final int PUERTO = 9999;
    
    @Override
    public String obtenerClienteservidorCentral (String id) {
        String respuesta = null;
        try{
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(id);
            cerrarFlujos();
            desconectar();
        } catch(IOException e) {
            Logger.getLogger(ServivioServidorCentral.class.getName()).log(Level.SEVERE, null, e);
        }
        return respuesta;
    }
    
    private String leerFlujoEntradaSalida (String id) throws IOException{
        String respuesta = "";
        entradaDecorada = new Scanner(socket.getInputStream());
        salidaDecorada = new PrintStream(socket.getOutputStream());
        salidaDecorada.flush();
        // Usando el protocolo de comunicación
        salidaDecorada.println("consultarCiudadano" + id);
        if(entradaDecorada.hasNextLine()){
            respuesta = entradaDecorada.nextLine();
        }
        return respuesta;
    }
    
    private void cerrarFlujos() {
        salidaDecorada.close();
        entradaDecorada.close();
    }
    
    private void desconectar() {
        try{
            socket.close();
        }catch (IOException e) {
            Logger.getLogger(ServivioServidorCentral.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void conectar (String address, int port) throws IOException {
        socket = new Socket(address, port);
        System.out.println("Conectado");
    }
    
}
