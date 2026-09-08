package model;

public class Player {
    private String name;
    private String surname;
    private int age;
    private int overall;
    private String role;

    public Player(String playerName, String playerSurname, int playerAge, int playerOverall, String playerRole) {

        if (playerName == null || playerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto.");
        }

        if (playerSurname == null || playerSurname.trim().isEmpty()) {
            throw new IllegalArgumentException("Il cognome non può essere vuoto.");
        }

        if (playerAge < 16 || playerAge > 40) {
            throw new IllegalArgumentException("Giocatore fuori dal range di età.");
        }

        if (playerOverall > 100 || playerOverall < 0) {
            throw new IllegalArgumentException("Overall giocatore non ammesso.");
        }

        if (playerRole == null || playerRole.trim().isEmpty()) {
            throw new IllegalArgumentException("Il ruolo non può essere vuoto.");
        }

        this.name = playerName;
        this.surname = playerSurname;
        this.age = playerAge;
        this.overall = playerOverall;
        this.role = playerRole;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int getOverall() {
        return overall;
    }

    public String getRole() {
        return role;
    }
    

    public void increaseAge() {
        age++;
    }

    public int increaseOverall(int durataAllenamento, int intensitaAllenamento) {

        int newOverall;

        newOverall = overall + ((durataAllenamento + intensitaAllenamento) / 100);

        overall = newOverall;
        return overall;
    }

    public String nuovaVersioneGiocatore(){
        return "il giocatore"+name+" "+surname+" di età: "+ age+" ora ha una valutazione di "+overall ;
    }

}