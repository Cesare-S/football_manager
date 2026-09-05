public class Main {

    public static void main() {
    
        Player player = new Player("Kevin", "De Bruyne", 32, 89, "T");
        System.out.println(player.nuovaVersioneGiocatore());
        player.increaseAge();
        player.increaseOverall(0, 0);

        System.out.println(player.nuovaVersioneGiocatore());
    }
   

}