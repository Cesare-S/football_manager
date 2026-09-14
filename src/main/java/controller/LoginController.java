package controller;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import web.TemplateRenderer;

public class LoginController {

    // Metodo showLoginForm e relativi import scritti dall'agente AI il 2026-09-14.
    // Campo e costruttore erano già presenti nel tentativo dell'utente.
    private final TemplateRenderer templateRenderer;

    public LoginController(TemplateRenderer templateRenderer) {
        this.templateRenderer = templateRenderer;
    }

    public void showLoginForm(HttpExchange exchange) throws IOException {
        String response = templateRenderer.returnTemplate("login.html");
        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, responseBytes.length);

        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(responseBytes);
        }
    }
}
