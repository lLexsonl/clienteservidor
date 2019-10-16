package servidorcentral.main;

import servidorcentral.servicio.Server;
/**
 *
 * @author Libardo, Yerson
 */
public class RunMain {
    public static void main(String[] args) {
        Server server = new Server();
        server.iniciar();
    }
}
