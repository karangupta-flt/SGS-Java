package Game.GamePlay;
import Game.DataDef.BaseSpin;
import Game.DataDef.PlayOptions;
import Game.DataDef.PlayResponse;

public class Main {

    public static void main(String[] args) {
        GameMain game = new GameMain();
        System.out.println("Game Main Successfully Executed\n\n");
        int featureMode = 0;
        int betAmount = 10;
        long currencyMultiplier = 1;
        boolean buyFeature = false;
        long refBetAmount = 0;
        //String Action ;

        System.out.println("Variables successfully initialized\n\n");

        PlayOptions opts = new PlayOptions(featureMode, betAmount, currencyMultiplier, buyFeature, refBetAmount);
        System.out.println("PlayOptions successfully ran\n\n");

        PlayResponse res = game.Play(opts);
        System.out.println("Game Played called Successfully\n\n");


        res.printResponse();
        System.out.println("Game Response Printed Successfully\n\n");




    }

}


