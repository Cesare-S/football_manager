package web;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import service.RegistrationService;


public class WebServer {

    private final RegistrationService registrationService;

    public WebServer(RegistrationService registrationService) {

        this.registrationService = registrationService;
     
                 
    }

    public void startServer(int port) throws IOException {

       
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        // 3. Associa un percorso (context) a un gestore di richieste (Handler)
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                try {
                String response = "<h1>Benvenuto su football manager scarso!</h1>";
                
                

                exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                
                exchange.sendResponseHeaders(200, response.getBytes().length);

                
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                }catch(Exception ex){
                    throw new IOException("Errore di connesione.");
                }
                
            }
        });
        
        server.start();

        
        System.out.println("Server avviato correttamente sulla porta " + port);

    }

}

