package Game.GamePlay;
import Game.DataDef.*;
import Game.Grid.Grid;

public class Main {

    public static void main(String[] args) {
        GameMain game = new GameMain();
        System.out.println("Game Main Successfully Executed\n\n");
        int featureMode = 0;
        int betAmount = 10;
        long currencyMultiplier = 1;
        boolean buyFeature = true;
        long refBetAmount = 0;
        //String Action ;


        System.out.println("Variables successfully initialized\n\n");

        PlayOptions opts = new PlayOptions(featureMode, betAmount, currencyMultiplier, buyFeature, refBetAmount);
        System.out.println("PlayOptions successfully ran\n\n");

        PlayResponse res = game.Play(opts); //loops me try karna hai
        boolean hasWin = false;

        while (!hasWin) {

            res = game.Play(opts);

            if (res.winAmount > 0) {

                hasWin = true;

                System.out.println("you won !!");
               // System.out.println("Win Amount = " + res.winAmount);
            }
        }
        System.out.println("Game Played called Successfully\n\n");

        res.printResponse();
        System.out.println("Game Response Printed Successfully\n\n");




    }

}


