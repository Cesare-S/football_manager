public class Player {
    String name;
    String surname;
    int age;
    int overall;
    String role;

    public Player(String playerName, String playerSurname, int playerAge, int playerOverall, String playerRole) {
        name = playerName;
        surname = playerSurname;
        age = playerAge;
        overall = playerOverall;
        role = playerRole;
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