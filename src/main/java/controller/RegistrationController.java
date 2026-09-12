package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Map;
import service.RegistrationService;
import web.FormParser;
import web.TemplateRenderer;

public class RegistrationController implements HttpHandler {

    // Classe scritta dall'agente AI su richiesta esplicita dell'utente.
    private final RegistrationService registrationService;
    private final TemplateRenderer templateRenderer;

    public RegistrationController(RegistrationService registrationService, TemplateRenderer templateRenderer) {
        this.registrationService = registrationService;
        this.templateRenderer = templateRenderer;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();

        if (path.equals("/") && method.equals("GET")) {
            showRegistrationForm(exchange);
            return;
        }

        if (path.equals("/register") && method.equals("POST")) {
            registerManager(exchange);
            return;
        }

        exchange.sendResponseHeaders(404, -1);
    }

    private void showRegistrationForm(HttpExchange exchange) throws IOException {
        String response = templateRenderer.returnTemplate("register.html");
        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, responseBytes.length);

        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(responseBytes);
        }
    }

    private void registerManager(HttpExchange exchange) throws IOException {
        try (InputStream requestBody = exchange.getRequestBody()) {
            String formDataString = new String(requestBody.readAllBytes(), StandardCharsets.UTF_8);
            Map<String, String> formData = FormParser.mapForm(formDataString);

            String name = formData.get("name");
            String email = formData.get("email");
            String password = formData.get("password");

            try {
                registrationService.registerManager(name, email, password);
                exchange.getResponseHeaders().set("Location", "/choose-club");
                exchange.sendResponseHeaders(302, -1);
            } catch (SQLException exception) {
                System.out.println("error " + exception);
                throw new IOException("errore registrazione");
            }
        }
    }
}
