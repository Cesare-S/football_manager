import java.util.List;
import java.util.ArrayList;

public class Team {

    private String name;
    private List<Player> players;

    public Team(String nameTeam) {
        this.name = nameTeam;
        this.players = new ArrayList<>();
    
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void insertPlayerOnTeam(Player teamPlayer) {
        this.players.add(teamPlayer);
    }
}