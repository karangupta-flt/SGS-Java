package Game.GamePlay;
import Game.DataDef.*;
import Game.Grid.GridMain;
import Game.ReelSets.ReelSetMain;
import Game.Round.RoundMain;

import java.util.Scanner;

import static Game.DataDef.FreeSpinStatus.INIT;


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

        //RoundMain roundMain = new RoundMain();

        PlayResponse res = game.Play(opts); //loops me try karna hai
        RoundMain roundMain = game.getRoundMain();

//        ReelSetMain reelSetMain = new ReelSetMain();
//        GridMain grid = new GridMain(reelSetMain);
//        RoundMain roundMain = new RoundMain(res, grid);
//        boolean hasWin = false;
//        while (!hasWin) {
//            res = game.Play(opts);
//
//            if (res.winAmount > 0) {
//
//                hasWin = true;
//
//                System.out.println("you won !!");
//                // System.out.println("Win Amount = " + res.winAmount);
//            }
//        }
        boolean freeSpinTriggered = false;

        while (!freeSpinTriggered) {

            res = game.Play(opts);

            int wsCount = res.baseSpin.wsSym.size();

            System.out.println("WS Count = " + wsCount);

            if (wsCount >= 3) {

                freeSpinTriggered = true;
                FsStatus fs = new FsStatus();

                fs.level = 1;

                fs.freeSpinStatus = INIT;

                res.fsStatus.add(fs);

                roundMain.playResponse = res;


                System.out.println("FREE SPIN TRIGGERED!");
            }
        }

        Scanner sc = new Scanner(System.in);
        while (!res.ended) {

            System.out.print("Enter g for gamble or c for collect : ");

            String gambleStr = sc.nextLine();
            String collectStr = sc.nextLine();

            // trim() == 'g'
            boolean gamble = gambleStr.trim().equals("g");
            boolean Collect = collectStr.trim().equals("c");
            System.out.println("Gamble = " + gamble);
            System.out.println("collected = " + Collect);



            // next() call from RoundMain
           // boolean succeed = false;
            boolean succeed = false;
           roundMain.next(gamble,succeed);
           res = roundMain.playResponse;

            // Print free spins
//            if (res.freeSpins != null &&
//                    !res.freeSpins.isEmpty()) {
//
//                System.out.println("\n===== FREE SPINS =====");
//
               for (FreeSpin fs : res.freeSpins) {

                    System.out.println("SpinType : " + fs.spinType);

                    System.out.println("WinAmount : " + fs.winAmount);

                    System.out.println("GridWindow : "
                            + java.util.Arrays.deepToString(fs.window));

                    System.out.println("----------------------");
               }
            }

            // Print total win
            System.out.println("Total Win : " + res.winAmount);

            System.out.println("================================");


//        System.out.println("GAME ENDED");
//
//        sc.close();
//
//
//        System.out.println("Game Played called Successfully\n\n");

        res.printResponse();
        System.out.println("Game Response Printed Successfully\n\n");


    }
}



