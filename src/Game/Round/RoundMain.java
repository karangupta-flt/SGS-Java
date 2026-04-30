package Game.Round;

import Game.Bet.BetMode;
import Game.DataDef.*;

import Game.Grid.Pair;
import Game.Grid.GridMain;
import Game.ReelSets.ReelSetMain;


import static Game.Constant.GameConstant.MAX_SCATTER_COUNT;
import static Game.Constant.GameConstant.MAX_WIN_CAP;
import static Game.DataDef.FreeSpinStatus.*;


public class RoundMain extends Round {
    public RoundMain(PlayResponse playResponse, GridMain grid) {
        super(playResponse, grid);
//        this.playResponse = playResponse;
//        this.grid = grid;

    }

    @Override
    public void Play() {

        //clear();

        mode = playResponse.featureMode;
        playResponse.ended = false;

        if ( mode >= BetMode.MODE_FEATURE_BUY_1.getValue()) {

            playResponse.fsStatus.add(new FsStatus(mode - BetMode.MODE_FEATURE_BUY_1.getValue() + 1, INIT));

            playResponse.subGameTriggered = true;
            playResponse.ended = false;
            playResponse.winAmount = 0;
            playResponse.refWinAmount = 0;
            playResponse.action = "choice";

            return;
        }
        // see PAR sheet v2.2 tab: free spins, cell: (A:9) //purpose of this code to throw error if ws is more than 5.
        int numWS = runBaseSpin(0);
        //playResponse.baseSpin.printSpin(); // change is here

        if (numWS > MAX_SCATTER_COUNT){
            throw new IllegalStateException("numWS exceeding allowed limit");
        }
    }


    /*
     * PAR sheet v2.2 tab: gamble, row: 15
     * When at level 1-8, the player has a choice between collecting or gambling to get to the next higher level.
     */

    public static final int MAX_FS_LVL = 9;
    @Override
    public void next(boolean gamble, boolean succeed) {
        if(playResponse.fsStatus.size() <=0)
            throw new IllegalStateException("next called but fsStatus array is zero sized");

        FsStatus prevFsStatus = playResponse.fsStatus.get(playResponse.fsStatus.size() - 1);
        FsStatus FreeSpinsStatus = new FsStatus();

        if(prevFsStatus.level < MAX_FS_LVL && gamble){
            Pair<Boolean, Float> result = tryGamble(prevFsStatus.level);
            boolean succeeded = result.getFirst();
            float prob = result.getSecond();
            System.out.println("[next] gamble has " + (succeeded ? "succeeded" : "failed"));

            prevFsStatus.draw = result.getSecond();


            if(succeeded){
                prevFsStatus.freeSpinStatus = SUCCEEDED;
                FsStatus.level = prevFsStatus.level +1;
                FsStatus.freeSpinStatus = INIT;
                playResponse.fsStatus.add(FreeSpinsStatus);

                if(FsStatus.level > MAX_FS_LVL){
                    System.out.println("[next] collecting since \"fs level\"("+ FsStatus.level + ") >= MAX_FS_LVL(" + MAX_FS_LVL + ")");
                    //return collect(playResponse);
                }
            }
            // Else gamble failed
            prevFsStatus.freeSpinStatus = FAILED;
            playResponse.ended = true;
            playResponse.subGameTriggered = false;

        }
        // Else collect
        //return collect(playResponse);
    }
    public int numWsFsLevel(int numWS){

        return numWS -2;
    }
    @Override
    protected void collect(PlayResponse playResponse){
        if (playResponse.fsStatus.size() <=0)
            throw new IllegalStateException("next called but fsStatus array is zero sized");
        FsStatus prevFsStatus = playResponse.fsStatus.get(playResponse.fsStatus.size() - 1);

        runFreeSpins(prevFsStatus.level, playResponse.baseSpin.refWinsSoFar);

    }

    @Override
    protected int runBaseSpin(long refWinsSoFar) {
        Spin baseSpin = playResponse.baseSpin;
        //grid.Spin(true, playResponse.baseSpin.stops);
       //grid.snapshot(playResponse.baseSpin.GridWindow);

        //playResponse.baseSpin.stops = grid.getStops();
        //grid.snapshot(playResponse.baseSpin.GridWindow);

//        if (baseSpin == null) {
//            baseSpin = new BaseSpin();
//            playResponse.baseSpin = baseSpin;
       // }
//        grid.Spin(true, baseSpin.stops);          // stops fill
//        grid.snapshot(baseSpin.window);       // symbols fill

        grid.selectReelSet(mode, true, 0);
        baseSpin.reelSet = grid.getReelSetName();

        Pair<Long, Integer> result = Spin(true, baseSpin, refWinsSoFar);
        //baseSpin.printSpin(); //change
        long refWinAmount = result.getFirst();
        int numWS = result.getSecond();
        baseSpin.refWinAmount = result.getFirst();

        playResponse.subGameTriggered = false;
        playResponse.ended = true;

        if (!baseSpin.maxWinTriggered && numWS >=3){
            FsStatus fsStatus = new FsStatus(numWsFsLevel(numWS), FreeSpinStatus.INIT);
            FsStatus.level = numWsFsLevel(numWS);
            FsStatus.freeSpinStatus =INIT;

            playResponse.fsStatus.add(fsStatus);
            playResponse.subGameTriggered = true;
            playResponse.ended = false;
        }
        playResponse.maxWinTriggered = baseSpin.maxWinTriggered;
        return numWS;
    }




   // public static final int NUM_FREE_SPINS = 8;

//    @Override
//    public void runFreeSpins(int fsLevel, long refWinsSoFar) {
//
//        int numSpins = NUM_FREE_SPINS;
//        long totalWins = playResponse.baseSpin.refWinAmount;
//        long runningWinAmount = refWinsSoFar;
//
//        FsStatus fsStatus = playResponse.fsStatus.get(playResponse.fsStatus.size() - 1);
//
//        Symbol specialSym;
//        Symbol symRemaining ;
//
//        grid.selectReelSet(mode, false, fsLevel);
//        String reelSet = grid.getReelSetName();
//
//        Symbol symRemaining = makeSymVector();
//        specialSym = selectSpecialSym(fsLevel, symRemaining);
//
//        long winsFromFS = 0;
//        int wsFromPrevFreeSpin = 0;
//
//        for (int i=0; i< numSpins; i++){                            //this loop executing free spin.
//            System.out.println("free Spin: "+i);
//            FreeSpin freeSpin;


            /*
             * Adding new special symbol(s) to current FS based on WS yield from previous one
             * PAR Sheet v2.2: tab: free spins, col: 87:
             *     Landing 3 or more scatter symbols (WS) during free spins, gives an additional 8 spins
             *     and activates additional special symbol(s).
             */

//            int WsFromPrevFreeSpin = 0;
//            if (WsFromPrevFreeSpin >=3){
//                 selectSpecialSym(numWsFsLevel(WsFromPrevFreeSpin), symRemaining, specialSym);
//             }
//            /* guranteed win ReelSet based on special Symbol*/.
//
//            if (i == numSpins -1 && winsFromFS ==0){
//                if(specialSym.size() <=0)
//                    throw new IllegalStateException ("no special symbols but we are in freeSpins");
//
//                int index = rng.getScaled(specialSym.size());
//
//                Symbol gWinSym = specialSym.get(index);
//
//                freeSpin.guaranteedWinSym = gWinSym.getCode();
//
//                grid.selectSpecialReelset(gWinSym);
//
//                reelSet = grid.getReelSetName();
//            }
//            freeSpin.reelSet = reelSet;
//
//
//        }
//
//    }


    @Override
    public Pair<Long,Integer> Spin(boolean baseGame, Spin s, long refWinsSoFar) {

        //safety check
//        if (s.stops == null){
//            s.stops = new int[GameConstant.REEL_COUNT];
//        }
//        if (s.wsSym == null){
//            s.wsSym = new SymCoordinate[50];
//        }
        //BaseSpin Spin = playResponse.baseSpin;
        grid.Spin(baseGame, s.stops, s.wsSym);

        long maxWinAmount = playResponse.refBetBase * MAX_WIN_CAP;
        long refWinAmount = grid.getWinnings(s.winnings, playResponse.refBetBase);

        boolean maxWinTriggered = (refWinsSoFar + refWinAmount) >= maxWinAmount;

        refWinAmount = maxWinTriggered ? maxWinAmount - refWinsSoFar : refWinAmount;

        s.maxWinTriggered = maxWinTriggered;
        s.refWinsSoFar = refWinsSoFar + refWinAmount;

        grid.snapshot(s.window);

        int numWS = 0;
        for (SymCoordinate sym : s.wsSym){
            if(sym != null) numWS++;

        }
       // s.printSpin(); //change

        return new Pair<>(refWinAmount, numWS);
    }

}