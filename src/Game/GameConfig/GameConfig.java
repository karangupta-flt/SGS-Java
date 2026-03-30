package Game.GameConfig;
import java.util.*;
import Game.PayTable.*;
import Game.Bet.BetConfig;
import Game.ReelSets.*;



public class GameConfig {
//    public static void main(String[] args) {
//        System.out.println("hello world");
//
//    }

    public int creditPerBet;
    public int maxWinCap;

    public List<BetConfig> betConfig;
    public List<PayTable> payTable;

    public  ReelSets reelSets;

    public String gameCore;
    public String gameStudio;
    public String gameVersion;
    public String gameName;
    public String variant;
    public String parSheetVersion;


    public int[][] payLines;

    public GameConfig(){

    };

    public GameConfig(int creditPerBet,
                      int maxWinCap,
                      List betConfig,
                      List payTable,
                      ReelSets reelSets,
                      String gameCore,
                      String gameStudio,
                      String gameVersion,
                      String gameName,
                      String variant,
                      String parSheetVersion,
                      int[][] payLines)
    {

        this.creditPerBet = creditPerBet;
        this.maxWinCap = maxWinCap;
        this.betConfig = betConfig;
        this.payTable = payTable;
        this.reelSets = reelSets;
        this.gameCore = gameCore;
        this.gameStudio = gameStudio;
        this.gameVersion = gameVersion;
        this.gameName = gameName;
        this.variant = variant;
        this.parSheetVersion = parSheetVersion;
        this.payLines = payLines;
    }

    public void ReelSets(ReelSets reelSets) {
    }
}

