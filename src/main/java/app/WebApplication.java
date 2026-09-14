package app;

import com.sun.net.httpserver.HttpExchange;
import controller.LoginController;
import controller.RegistrationController;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import persistence.ManagerRepository;
import service.RegistrationService;
import web.Router;
import web.TemplateRenderer;
import web.WebServer;

public class WebApplication {

    // Classe scritta dall'agente AI nella deroga del 2026-09-14.
    // Questo è il punto in cui colleghiamo gli oggetti dell'interfaccia web.
    public static WebServer createServer() {
        ManagerRepository managerRepository = new ManagerRepository();
        RegistrationService registrationService = new RegistrationService(managerRepository);
        TemplateRenderer templateRenderer = new TemplateRenderer();

        RegistrationController registrationController =
                new RegistrationController(registrationService, templateRenderer);
        LoginController loginController = new LoginController(templateRenderer);

        Router router = new Router();
        // :: passa il metodo da eseguire in seguito, quando arriva la richiesta.
        router.addRoute("GET", "/", registrationController::showRegistrationForm);
        router.addRoute("POST", "/register", registrationController::registerManager);
        router.addRoute("GET", "/login", loginController::showLoginForm);

        // Manteniamo disponibili la destinazione del redirect e il CSS esistenti.
        router.addRoute("GET", "/choose-club", TeamController::showClubSelection);
        router.addRoute("GET", "/static/css/style.css", WebApplication::serveStylesheet);

        return new WebServer(router);
    }

    private static void showClubSelection(HttpExchange exchange, TemplateRenderer templateRenderer)
            throws IOException {
        String response = templateRenderer.returnTemplate("club-selection.html");
        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, responseBytes.length);
        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(responseBytes);
        }
    }

    private static void serveStylesheet(HttpExchange exchange) throws IOException {
        try (InputStream inputStream = WebApplication.class.getClassLoader()
                .getResourceAsStream("static/css/style.css")) {
            if (inputStream == null) {
                exchange.sendResponseHeaders(404, -1);
                return;
            }
            byte[] cssBytes = inputStream.readAllBytes();
            exchange.getResponseHeaders().set("Content-Type", "text/css; charset=UTF-8");
            exchange.sendResponseHeaders(200, cssBytes.length);
            try (OutputStream outputStream = exchange.getResponseBody()) {
                outputStream.write(cssBytes);
            }
        }
    }
}
