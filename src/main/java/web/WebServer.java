package web;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.net.InetSocketAddress;
import service.RegistrationService;
import java.util.Map;
import java.sql.SQLException;

public class WebServer {

    private final RegistrationService registrationService;
    private final TemplateRenderer templateRenderer;

    public WebServer(RegistrationService registrationService, TemplateRenderer templateRenderer) {

        this.registrationService = registrationService;
        this.templateRenderer = templateRenderer;
     
                 
    }

    public void startServer(int port) throws IOException {

       
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        // 3. Associa un percorso (context) a un gestore di richieste (Handler)
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
             
                String response = templateRenderer.returnTemplate("register.html");
                byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
                
                

                exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                
                exchange.sendResponseHeaders(200, responseBytes.length);

                
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(responseBytes);
                }
                
                
            }
        });

        server.createContext("/register", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
             
            
                if (exchange.getRequestMethod().equals("POST")){

                    try (InputStream getParsedBody = exchange.getRequestBody()) {


                        String formDataString = new String(getParsedBody.readAllBytes(), StandardCharsets.UTF_8);


                        Map<String, String> formData = FormParser.mapForm(formDataString);

                        String name = formData.get("name");
                        String email = formData.get("email");
                        String password = formData.get("password");

                        try {
                            registrationService.registerManager(name, email, password);
                            exchange.getResponseHeaders().set("Location", "/choose-club");
    
                            exchange.sendResponseHeaders(302, -1);


                        } catch (SQLException e) {
                            System.out.println("error " + e);
                            throw new IOException("errore registrazione");
                        }


                    return;
                    }
                    
                }else{
                    exchange.sendResponseHeaders(405, -1);

                    return;

                }
                
            }
        });
        

        server.createContext("/choose-club", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
             
                String response = templateRenderer.returnTemplate("club-selection.html");
                byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
                
                

                exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                
                exchange.sendResponseHeaders(200, responseBytes.length);

                
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(responseBytes);
                }
                
                
            }
        });

        server.createContext("/static/css/style.css", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("static/css/style.css")) {
                    if (inputStream == null || !exchange.getRequestURI().getPath().equals("/static/css/style.css")) {
                        exchange.sendResponseHeaders(404, -1);
                        exchange.close();
                        return;
                    }

                    byte[] cssBytes = inputStream.readAllBytes();
                    exchange.getResponseHeaders().set("Content-Type", "text/css; charset=UTF-8");
                    exchange.sendResponseHeaders(200, cssBytes.length);

                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(cssBytes);
                    }
                }
            }
        });

        server.start();

        
        System.out.println("Server avviato correttamente sulla porta " + port);

    }

}
