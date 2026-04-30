package Game.Grid;

import Game.Constant.GameConstant;
import Game.DataDef.*;
import Game.PayTable.PayTable;
import Game.PayTable.PayTableCtrl;
import Game.Reel.Reel;
import Game.ReelSets.ReelSetMain;
import Game.PayLines.PayLines;

import Game.Symbols.Symbol;

import java.util.Arrays;
import java.util.List;

import static Game.Bet.BetConfig.CREDITS_PER_BET;
import static Game.PayLines.PayLines.*;

public class GridMain extends Grid {
    //ReelSets reelSets;
    Symbol[] all = Symbol.values();
    Reel obj = new Reel(0, all);
    public GridMain(ReelSetMain reelSets) {
        super(reelSets);

    }


    @Override //this code is for grid window.
    public void Spin(boolean baseGame, int[] stops, List<SymCoordinate> wsSym) {
        int count = 0;

        Reel[] reels = reelSets.getSelected().getReels();
        reelSets.Spin(baseGame, stops);
//        System.out.println("after reelSets.Spin");
//        System.out.println(Arrays.toString(stops));

//        for (int i = 0; i< stops.length; i++){
//            System.out.println("Reel" + i + "stop = "+ reels[i].stopPos());
//        }

        for (int i = 0; i < stops.length; i++) {
            Reel reel = reels[i];
            for (int j = 0; j < GameConstant.GRID_HEIGHT; j++) {
                Symbol sym = reel.symbolAt(stops[i] + j);
                window[i][j] = sym;

                if (sym == Symbol.WS) {
                    wsSym.add (new SymCoordinate(i, j, sym));
                        count++;


                }
            }
        }

        System.out.println("fresh spin");
    }


//    public void fillWindow(Symbol[][]window) {
//        System.out.println(Arrays.toString(all)+ " "+ "this is the symbol");
//        int n = window.length, m = window[0].length, size = all.length;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                window[i][j] = all[obj.Spin()%size];
//            }
//        }
//        System.out.println("one dimension array is successfully print");
//    }
    @Override
    public void snapshot(Symbol [][] grid) {
        //Symbol[][] window= new Symbol[GameConstant.REEL_COUNT][GameConstant.GRID_HEIGHT];
        //fillWindow(window);
        for(int i =0; i< window.length; i++){
            for(int j = 0; j< window[i].length; j++) {
                grid[i][j]=window[i][j];
                //GridWindow[i][j] = GridWindow[i][j];
//                System.out.println(grid[i][j] + " ");
            }
            //System.out.println();
        }
    }


    @Override
    public long getWinnings(List<Winning> winnings, long refBetBase) {

        PayLines.payLines = PayTableCtrl.lines();  //all payLines
        long refWinAmount = 0;

        for (int i = 0; i < payLines.length; i++) {
            System.out.println("Evalutaing payline : "+ i+ "\n");
            PayLines.payLine = payLines[i];                 //current payLine
            for (int j = 0; j < PayLines.payLine.length; j++) {
                System.out.println("payline : "+ payLine[j]);
            }
            LineWinData winData = new LineWinData();

            PayTable.evaluateLine(window, payLine, i, winData);

            Symbol symbol = winData.symbol;
            int totalCount = winData.totalCount;
            int WSCount = winData.WSCount;

            if (totalCount < 2 && WSCount < 2)
                continue;

            int wsMulti = WSCount < 2 ? 0 : PayTableCtrl.PAY_TABLE[Symbol.WS.ordinal()][WSCount - 2];
            int symMulti = totalCount < 2 ? 0 : PayTableCtrl.PAY_TABLE[symbol.ordinal()][totalCount - 2];

            int multi = Math.max(wsMulti, symMulti);

            long currWinAmount = refBetBase * multi / CREDITS_PER_BET;

            if (currWinAmount != 0) {
                Winning win = new Winning();
                win.payLine = i ;
                win.dir = "ltr";

                if (wsMulti > symMulti) {
                    win.symbol = Symbol.WS;
                    win.symCount = WSCount;
                } else {
                    win.symbol = symbol;
                    win.symCount = totalCount;
                }
                win.multiplier = multi;
                win.refWinAmount = currWinAmount;
                win.coords = PayTable.getCoords(PayLines.payLine,wsMulti > symMulti ? WSCount : totalCount);
                refWinAmount += win.refWinAmount;


                winnings.add(win);


            }

        }

        return refWinAmount;



    }



    public Pair<Long, Boolean> getWinsFromSpSym(Symbol sym, SpecialSymWins specialSymWins, long refBetBase, long refWinsSoFar) {

        int[][] PayLines = PayTableCtrl.lines();
        int[] payTblItem = PayTableCtrl.PAY_TABLE[sym.getId()];

        Symbol[][] newGrid = new Symbol[window.length][window[0].length];
        for (int i = 0; i < window.length; i++) {
            for (int j = 0; j < window[i].length; j++) {
                    newGrid[i][j] = window[i][j];
            }
        }

        int symCount = 0;

            // Expand symbol
            for (int i = 0; i < GameConstant.REEL_COUNT; i++) {
                boolean hasSym = false;

                for (int j = 0; j < GameConstant.GRID_HEIGHT; j++) {

                    if (window[i][j] != sym)
                        continue;

                    hasSym = true;

                    // expand full reel
                    for (int k = 0; k < GameConstant.GRID_HEIGHT; k++) {
                        newGrid[i][k] = sym;
                    }

                    break;
                }

                if (hasSym) {
                    symCount++;
                }
            }

            if (symCount < 2 ||  payTblItem[symCount - 2] <=0 ){

            specialSymWins.window = newGrid;
                return new Pair<>(0L, false);
            }

            long maxWinAmount = GameConstant.MAX_WIN_CAP * refBetBase;
            long refWinAmount = 0;
            long runningWinAmount = refWinsSoFar;
            boolean maxWinTriggered = refWinsSoFar >= maxWinAmount;
            Winning win = new Winning();

            int multi  = payTblItem[symCount - 2];
            if (multi == 0 || maxWinTriggered)
                return  new Pair<>(0L, maxWinTriggered);

            for (int i = 0; i< PayLines.length; i++){
                win.type = "speacial";
                win.payLine = i;
                win.dir = "ltr";
                win.symbol = sym;
                win.symCount = symCount;
                win.multiplier = multi;

                long currWinAmount = multi * refBetBase / CREDITS_PER_BET;
                maxWinTriggered = (runningWinAmount + currWinAmount) >=maxWinAmount;
                currWinAmount = maxWinTriggered ? maxWinAmount - runningWinAmount : currWinAmount;

                win.refWinAmount = currWinAmount;
                refWinAmount += win.refWinAmount;
                runningWinAmount += currWinAmount;

                if (maxWinTriggered){

                specialSymWins.Winnings.add(win);
                    break;
                }

            }

            specialSymWins.refWinAmount = refWinAmount;
            specialSymWins.refWinSoFar = runningWinAmount;

            return new Pair<>(refWinAmount, maxWinTriggered);


    }

    boolean isSymOnWinLine(Symbol sym, int x, int y, final List <Winning> winnings) {
        for (Winning win : winnings) {
            for (Coordinate c : win.coords) {
                if ((x == c.x) && (y == c.y) && ((sym == win.symbol) || (sym == Symbol.WS))) {
                    return true;
                }
            }
        }
        return false;
    }



}
