package web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Router implements HttpHandler {

    // Classe scritta dall'agente AI nella deroga del 2026-09-14.
    // Per ogni percorso conserviamo le azioni associate ai metodi HTTP.
    private final Map<String, Map<String, HttpHandler>> routes = new LinkedHashMap<>();

    public void addRoute(String method, String path, HttpHandler action) {
        Map<String, HttpHandler> methods = routes.computeIfAbsent(path, key -> new LinkedHashMap<>());
        if (methods.putIfAbsent(method, action) != null) {
            throw new IllegalArgumentException("Rotta già registrata: " + method + " " + path);
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            Map<String, HttpHandler> methods = routes.get(exchange.getRequestURI().getPath());
            if (methods == null) {
                exchange.sendResponseHeaders(404, -1);
                return;
            }

            HttpHandler action = methods.get(exchange.getRequestMethod());
            if (action == null) {
                exchange.getResponseHeaders().set("Allow", String.join(", ", methods.keySet()));
                exchange.sendResponseHeaders(405, -1);
                return;
            }

            // Qui viene eseguito il metodo del controller associato alla rotta.
            action.handle(exchange);
        } finally {
            exchange.close();
        }
    }
}
