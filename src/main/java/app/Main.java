package app;

import java.io.IOException;
import web.WebServer;

public class Main {

    /*
     * Riassetto scritto dall'agente AI il 2026-09-14, con deroga limitata a questo task.
     * La creazione delle dipendenze e le rotte sono in WebApplication.
     * WebServer avvia il server; Router sceglie il metodo del controller.
     * Sono gestite registrazione e sola pagina di login, non l'autenticazione.
     * Questo intervento non autorizza future modifiche Java dell'agente.
     */
    public static void main(String[] args) throws IOException {
        WebServer webServer = WebApplication.createServer();
        webServer.startServer(8080);
    }
}
