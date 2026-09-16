package controller;

import com.sun.net.httpserver.HttpExchange;

import service.RegistrationService;
import service.TeamService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import web.TemplateRenderer;
import web.FormParser;
import java.util.Map;

public class TeamController {

    // Metodo showLoginForm e relativi import scritti dall'agente AI il 2026-09-14.
    // Campo e costruttore erano già presenti nel tentativo dell'utente.
    private final TemplateRenderer templateRenderer;
    private final TeamService teamService;

    public TeamController(TeamService teamService, TemplateRenderer templateRenderer) {
        this.teamService = teamService;
        this.templateRenderer = templateRenderer;
    }
    
    public void showClubSelection(HttpExchange exchange) throws IOException {
        String response = templateRenderer.returnTemplate("club-selection.html");
        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, responseBytes.length);

        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(responseBytes);
        }
    }

    public void associateClub(HttpExchange exchange) throws IOException {
        try (InputStream requestBody = exchange.getRequestBody()) {
            String formDataString = new String(requestBody.readAllBytes(), StandardCharsets.UTF_8);
            Map<String, String> formData = FormParser.mapForm(formDataString);

            String id = formData.get("team_id");
       
            String cookieHeader = exchange.getRequestHeaders().getFirst("Cookie");
            String idTrovato = 0;

            // 2. Se l'header esiste ed è pieno, estrai il valore
            if (cookieHeader != null && !cookieHeader.isEmpty()) {
                // I cookie arrivano come "nome=valore; altroNome=altroValore", li dividiamo
                String[] cookies = cookieHeader.split("; ");
                for (String c : cookies) {
                    if (c.startsWith("managerId=")) {
                        idTrovato = c.substring("managerId=".length());
                        break; // Trovato!
                    }
                }
            }

            try {
                teamService.associateTeamToManager(Integer.parseInt(id), Integer.parseInt(idTrovato));
                exchange.getResponseHeaders().set("Location", "/dashboard");
                exchange.sendResponseHeaders(302, -1);
            } catch (SQLException exception) {
                System.out.println("error " + exception);
                throw new IOException("errore registrazione");
            }
        }
    }

}