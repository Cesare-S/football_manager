package web;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.util.List;
import java.util.ArrayList;

import model.Club;
import model.Team;
import model.Player;

public class WebServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        
         Player midfielder = new Player("Kevin", "De Bruyne", 32, 89, "T");
        
        Team team = new Team("Napoli");

        team.insertPlayerOnTeam(midfielder);

        Player goalkeeper = new Player("Alex", "Meret", 28, 83, "Por");

        team.insertPlayerOnTeam(goalkeeper);

        Club club = new Club("Napoli Calcio", team);
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        // 3. Associa un percorso (context) a un gestore di richieste (Handler)
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                try {
                String response = "<h1>Benvenuto nella pagina del club:"+club.getName()+"!</h1>";
                
                response += "<h3>Il tema del club è: "+team.getName()+"</h3>";        


                response += "<span>I giocatori sono:";
                List<Player> players = team.getPlayers();
                for (int i = 0; i < players.size(); i++) {
                    
    
                    Player player = players.get(i);
                    // Accedi alle proprietà tramite il punto + il metodo getter
                    String nome = player.getName(); 
                    String cognome = player.getSurname();
                    int age = player.getAge();
                    int overall = player.getOverall();
                    String role = player.getRole();

                    response += "<p>"+nome+" "+cognome+". Età: "+age+". Valutazione: "+overall+". Con il ruolo di: "+role+"</p>"; 
                
                }

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

