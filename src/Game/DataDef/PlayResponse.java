package Game.DataDef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayResponse {


    public long betAmount;
    public long refBetAmount;
    public long refBetBase;
    public int featureMode;
    public boolean subGameTriggered;
    public boolean ended = false;
    public long winAmount = 0;
    public long refWinAmount = 0;
    public List<FsStatus> fsStatus = new ArrayList<>();
    public String action;
    public BaseSpin baseSpin;
    public List<FreeSpin> freeSpins = new ArrayList<>();
    public boolean maxWinTriggered = false;
    public long currencyMultiplier = -1;
    public Internal internal;
    public long winSoFar = 0;
    public long refWinSoFar= 0;

    public PlayResponse() {
      this.baseSpin =  new BaseSpin();

    }

    public void printResponse () {
        System.out.println("betAmount : "+ this.betAmount);
        System.out.println("currencyMultiplier: " + this.currencyMultiplier);
        System.out.println("refBetBase :" + this.refBetBase);
        System.out.println("refBetAmount :"+ refBetAmount);
        System.out.println("winAmount :" + this.winAmount);
        System.out.println("refWinAmount :" + this.refWinAmount);
        System.out.println("maxWinTriggered : " + this.maxWinTriggered);
        System.out.println("ended : " + this.ended);
        System.out.println("action : "+ this.action);
        System.out.println("featureMode :" + this.featureMode);
        System.out.println("subGameTriggered : " + this.subGameTriggered);
        System.out.println("winSoFar :" + this.winSoFar);
        System.out.println("refWinSoFar :"+ this.refWinSoFar);
        System.out.println("SpinType :" + this.baseSpin.spinType);
        System.out.println("GridWindow :"+ Arrays.deepToString(this.baseSpin.window));
        System.out.println("winnings :" + this.baseSpin.winnings);
        System.out.println("stops positions : "+ Arrays.toString(this.baseSpin.stops));
//        System.out.println("Size of symCoordinateVec: " + this.wsSym.size());
//        for (SymCoordinate coord : this.wsSym) {
//            System.out.println("wsSym : "+ coord);

      //  }
        //System.out.println("wsSym : "+ wsSym);




    }


}