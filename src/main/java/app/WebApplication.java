package app;

import com.sun.net.httpserver.HttpExchange;
import controller.LoginController;
import controller.RegistrationController;
import controller.TeamController;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import persistence.ManagerRepository;
import persistence.TeamRepository;
import service.RegistrationService;
import service.TeamService;
import web.Router;
import web.TemplateRenderer;
import web.WebServer;

public class WebApplication {

    // Classe scritta dall'agente AI nella deroga del 2026-09-14.
    // Questo è il punto in cui colleghiamo gli oggetti dell'interfaccia web.
    public static WebServer createServer() {
        ManagerRepository managerRepository = new ManagerRepository();
        RegistrationService registrationService = new RegistrationService(managerRepository);
        TeamRepository teamRepository = new TeamRepository();
        TeamService teamService = new TeamService(teamRepository);
        TemplateRenderer templateRenderer = new TemplateRenderer();

        RegistrationController registrationController =
                new RegistrationController(registrationService, templateRenderer);
        LoginController loginController = new LoginController(templateRenderer);

        TeamController teamController = new TeamController(teamService, templateRenderer);

        Router router = new Router();
        // :: passa il metodo da eseguire in seguito, quando arriva la richiesta.
        router.addRoute("GET", "/", registrationController::showRegistrationForm);
        router.addRoute("POST", "/register", registrationController::registerManager);
        router.addRoute("GET", "/login", loginController::showLoginForm);

        // Manteniamo disponibili la destinazione del redirect e il CSS esistenti.
        router.addRoute("GET", "/choose-club", teamController::showClubSelection);
        router.addRoute("POST", "/club-selection-form", teamController::associateClub);
        
        router.addRoute("GET", "/dashboard", teamController::showDashboard);
        router.addRoute("GET", "/static/css/style.css", WebApplication::serveStylesheet);

        router.addRoute("GET", "/static/css/dashboard.css", WebApplication::serveStylesheet);

        return new WebServer(router);
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
