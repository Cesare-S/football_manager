package app;

import model.Player;
import model.Team;
import model.Club;

public class Main {

    public static void main(String[] args) {
    
        Player midfielder = new Player("Kevin", "De Bruyne", 32, 89, "T");
        System.out.println(midfielder.nuovaVersioneGiocatore());
        
        Team team = new Team("Napoli");

        team.insertPlayerOnTeam(midfielder);

        Player goalkeeper = new Player("Alex", "Meret", 28, 83, "Por");
        System.out.println(goalkeeper.nuovaVersioneGiocatore());

        team.insertPlayerOnTeam(goalkeeper);


        System.out.println(team.getPlayers().toString());

        int port = 8080;
        
        // 2. Crea l'istanza del server associandola alla porta desiderata
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        // 3. Associa un percorso (context) a un gestore di richieste (Handler)
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "<h1>Benvenuto sul server HTTP Java!</h1>";
                
                // Imposta gli header di risposta: Codice 200 (OK) e lunghezza del testo
                exchange.sendResponseHeaders(200, response.getBytes().length);
                
                // Invia il corpo della risposta
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });
        
        // 4. Avvia il server
        server.start();
        System.out.println("Server avviato correttamente sulla porta " + port);

    }
   

}