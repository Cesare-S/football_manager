package web;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {

    // Classe riscritta dall'agente AI nella deroga del 2026-09-14.
    private final HttpHandler router;

    public WebServer(HttpHandler router) {
        this.router = router;
    }

    public void startServer(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", router);
        server.start();
        System.out.println("Server avviato correttamente sulla porta " + port);
    }
}
