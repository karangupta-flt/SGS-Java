package Game.Bet;

public class BetMain {
        public static void main(String[] args) {
            System.out.println("Hello World");

            // Example usage
            System.out.println("RTP: " + RTPConfig.RTP);
            System.out.println("Normal Bet name: " + BetConfig.BET_NORMAL.name);
            System.out.println("All bet sizes:");
            for (BetConfig bet : BetConfig.ALL_BETS_CONFIG) {
                System.out.println(bet.name + " size: " + bet.size);
            }
        }
}

