package Game.Round;
import java.util.ArrayList;

import java.util.List;


import Game.Bet.BetMode;
import Game.DataDef.*;

import Game.Grid.Pair;
import Game.Grid.GridMain;

import Game.ReelSets.ReelSetMain;
import Game.Symbols.Symbol;


import static Game.Constant.GameConstant.MAX_SCATTER_COUNT;
import static Game.Constant.GameConstant.MAX_WIN_CAP;
import static Game.DataDef.FreeSpinStatus.*;
import static Game.Symbols.Symbol.SYM_ARR;


 public class RoundMain extends Round {
     public List<Symbol> specialSymbols;
     public List<Symbol>symRemaining;
     public Symbol sym;
     public SpecialSymWins specialSymWins;
     //public List<Symbol> symRemain = new ArrayList<>();



     public RoundMain(PlayResponse playResponse, GridMain grid) {
        super(playResponse, grid);
        this.playResponse = playResponse;
        this.grid = grid;

    }

     public RoundMain() {
         super();
     }

//     public RoundMain() {
//         super();
//
//             ReelSetMain reelSetMain = new ReelSetMain();
//
//             this.grid = new GridMain(reelSetMain);
//
//             this.playResponse = new PlayResponse();
//
//     }


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
        //PlayResponse playResponse = new PlayResponse();
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
        collect(playResponse);
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


    public static final int NUM_FREE_SPINS = 8;

    @Override
    public void runFreeSpins(int fsLevel, long refWinsSoFar) {

        int numSpins = NUM_FREE_SPINS;
        long totalWins = playResponse.baseSpin.refWinAmount;
        long runningWinAmount = refWinsSoFar;

        FsStatus fsStatus = playResponse.fsStatus.get(playResponse.fsStatus.size() - 1);



        grid.selectReelSet(mode, false, fsLevel);
        String ReelSets = grid.getReelSetName();


        symRemaining = new ArrayList<>();

        specialSymbols = new ArrayList<>();

        makeSymVector(symRemaining);
        selectSpecialSym(fsLevel, symRemaining, specialSymbols);

        long winsFromFS = 0;
        int wsFromPrevFreespin = 0;

        for (int i = 0; i < numSpins; i++) {                            //this loop executing free spin.
            System.out.println("free Spin: " + i);
            FreeSpin freeSpin = new FreeSpin();
            freeSpin.SpecialSymbols = new ArrayList<>();
            freeSpin.reelSet = ReelSets;
            playResponse.freeSpins.add(freeSpin);



            /*
             * Adding new special symbol(s) to current FS based on WS yield from previous one
             * PAR Sheet v2.2: tab: free spins, col: 87:
             *     Landing 3 or More Scatter symbols (WS) during free spins, gives an additional 8 spins
             *     and activates additional special symbol(s).
             */


            if (wsFromPrevFreespin >= 3) {
                selectSpecialSym(numWsFsLevel(wsFromPrevFreespin), symRemaining, specialSymbols);
            }
            wsFromPrevFreespin = 0;

            /* Guaranteed win ReelSet based on special Symbol */

            if (i == numSpins - 1 && winsFromFS == 0) {
                if (specialSymbols.size() <= 0)
                    throw new IllegalStateException("no special symbols but we are in freeSpins");

                    int index = (int) (Math.random() * (specialSymbols.size()));
                    Symbol gWinSym = specialSymbols.get(index);
                    freeSpin.guaranteedWinSym = gWinSym.getCode();

                    grid.selectSpecialReelSet(gWinSym);

                    ReelSets = grid.getReelSetName();

            }

            freeSpin.reelSet = ReelSets;

            // ToDo : in the spin ->grid ->winnings, trim the winnings array on max-win- triggered
            Pair<Long, Integer> result = Spin(false, freeSpin, runningWinAmount);
            Long refWinAmount = result.getFirst();
            Integer newWS = result.getSecond();

            freeSpin.index = i;
            freeSpin.refBaseWinAmount = refWinAmount;
            freeSpin.refWinAmount = refWinAmount;
            runningWinAmount += refWinAmount;
            freeSpin.refWinsSoFar = runningWinAmount;

            // check for max- win trigger here that might be caused by base-win- amount.
            if (freeSpin.maxWinTriggered) {
                playResponse.freeSpins.add(freeSpin);
                break;
            }

            if (newWS >= 3) {
                System.out.printf("[freeSpin] newWS =  %d\n", newWS);
                numSpins += NUM_FREE_SPINS;
                wsFromPrevFreespin = newWS;
            }

            freeSpin.SpecialSymbols = specialSymbols;  // add one symbol in a list.
            freeSpin.refSsWinAmount = getWinningsFromSpecialSymbols(freeSpin, runningWinAmount);
            freeSpin.refWinAmount += freeSpin.refSsWinAmount;
            runningWinAmount += freeSpin.ssWinAmount;
            freeSpin.refWinsSoFar = runningWinAmount;
            totalWins += freeSpin.refWinAmount;
            winsFromFS += freeSpin.refWinAmount;

           // playResponse.freeSpins.add(freeSpin);

            if (freeSpin.maxWinTriggered){
                break;
            }

            fsStatus.freeSpinStatus  = COLLECTED;
            playResponse.ended = true;
            playResponse.subGameTriggered = false;
            playResponse.maxWinTriggered = playResponse.freeSpins.get(playResponse.freeSpins.size() - 1).maxWinTriggered;




        }
    }




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
        s.wsSym.clear();
        grid.Spin(baseGame, s.stops, s.wsSym);
        //System.out.println("AFTER GRID SPIN = " + s.wsSym);
        //System.out.println("s.wsSym = " + s.wsSym);

        playResponse.baseSpin.wsSym = new ArrayList<>(s.wsSym);
        //System.out.println("baseSpin.wsSym = " + playResponse.baseSpin.wsSym);
        //playResponse.wsSym = new ArrayList<>(s.wsSym);





        long maxWinAmount = playResponse.refBetBase * MAX_WIN_CAP;
        long refWinAmount = grid.getWinnings(s.winnings, playResponse.refBetBase);

        boolean maxWinTriggered = (refWinsSoFar + refWinAmount) >= maxWinAmount;

        refWinAmount = maxWinTriggered ? maxWinAmount - refWinsSoFar : refWinAmount;

        s.maxWinTriggered = maxWinTriggered;
        s.refWinsSoFar = refWinsSoFar + refWinAmount;
       // playResponse.refWinSoFar = s.refWinsSoFar;
        //System.out.println("Ref Wins So Far = " + s.refWinsSoFar); //debug refWinSoFar

        grid.snapshot(s.window);

        int numWS = s.wsSym.size();

          System.out.println("WS count: " + numWS);
//        System.out.println("WS list: " + s.wsSym);

//        for (SymCoordinate sym : s.wsSym){
//            if(sym != null) numWS++;
//
//        }
//        s.printSpin(); //change

        return new Pair<>(refWinAmount, numWS);
    }

    @Override
    protected long getWinningsFromSpecialSymbols (FreeSpin freeSpin, long refWinSoFar) {
        long refWinAmount = 0;
        long runningWinAmount = refWinSoFar;



        for (Symbol sym : freeSpin.SpecialSymbols) {
            specialSymWins = new SpecialSymWins();
            specialSymWins.Symbol = sym;
            specialSymWins.winnings = new ArrayList<>();
            specialSymWins.refWinAmount = 0;
            specialSymWins.maxWinTriggered = false;


            Pair<Long, Boolean> Result = grid.getWinsFromSpSym(sym, specialSymWins, playResponse.refBetBase, runningWinAmount);
            Long win = Result.getFirst();
            Boolean maxWinTriggered = Result.getSecond();

            if (win != 0) {
                refWinAmount += win;
                runningWinAmount += win;
                freeSpin.refWinsSoFar = runningWinAmount;
                freeSpin.maxWinTriggered = maxWinTriggered;
                specialSymWins.maxWinTriggered = maxWinTriggered;

                freeSpin.ssWinnings.add(specialSymWins);

                if (maxWinTriggered) {
                    break;
                }

            }
        }
       
        return refWinAmount;
    }

    // see PAR sheet V2.2 tab : gamble, col: e

    public static final float[] LADDER_WEIGHTS = {
            2.0f / 3,
            0.75f,
            0.785f,
            0.825f,
            0.85f,
            0.875f,
            0.9f,
            0.925f
    };

    public Pair<Boolean, Float> tryGamble(int prevLevel) {
        float successProb = LADDER_WEIGHTS[prevLevel - 1]; // or array[index]
        float prob = (float) Math.random();

        return new Pair<>(prob < successProb, prob);
    }

     protected void selectSpecialSym(int numToSelect, List<Symbol>symRemaining, List<Symbol>specialSym) {
         if (numToSelect < 0) {
             throw new IllegalArgumentException("cannot select special symbol since numToSelect < 0");
         }

         if (symRemaining == null) {
             return;
         }

         for (int i = 0; i < numToSelect; i++) {
             int rand = (int) (Math.random() * symRemaining.size());

             Symbol special = symRemaining.get(rand);
             symRemaining.remove(rand);   // remove so it doesn't repeat
             specialSym.add(special);

//             System.out.printf(
//                     "[freeSpin] adding special symbol = %s (numToSelect = %d)%n",
//                     SymbolChar[special.ordinal()], // adjust if your mapping is different
//                     numToSelect
//             );

             if (symRemaining.isEmpty()) {
                 break;
             }
         }
     }
     @Override
     protected void makeSymVector(List<Symbol> symbols) {
             for (int i = Symbol.H1.ordinal(); i < Symbol.INVALID.ordinal(); i++) {

                 symbols.add(SYM_ARR.get(i));
             }
//         if(symbols == null){
//             symbols = new ArrayList<>();
//         }
//
//
//         for (Symbol sym : Symbol.values()) {
//
//                 if (sym != Symbol.INVALID) {
//
//                     symbols.add(sym);
//                 }
//             }

    }

    @Override
    protected void clear(){
        grid.clear();
    }







 }