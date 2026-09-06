public class Main {

    public static void main() {
    
        Player midfielder = new Player("Kevin", "De Bruyne", 32, 89, "T");
        System.out.println(midfielder.nuovaVersioneGiocatore());
        
        Team team = new Team("Napoli");

        team.insertPlayerOnTeam(midfielder);

        Player goalkeeper = new Player("Alex", "Meret", 28, 83, "Por");
        System.out.println(goalkeeper.nuovaVersioneGiocatore());

        team.insertPlayerOnTeam(goalkeeper);


        System.out.println(team.getPlayers());

    }
   

}