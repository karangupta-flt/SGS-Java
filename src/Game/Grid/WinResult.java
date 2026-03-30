//package Game.Grid;
//import java.util.*;
//
//import Game.Constant.GameConstant;
//import Game.DataDef.SpecialSymWins;
//import Game.PayTable.PayTable;
//import Game.PayTable.PayTableCtrl;
//import Game.Symbols.Symbol;
//
//public class WinResult {
//    public long winAmount;
//    public boolean isWin;
//
//    public WinResult(long winAmount, boolean isWin){
//        this.winAmount =  winAmount;
//        this.isWin = isWin;
//    }
//    public WinResult getWinsFromSpSym(Symbol sym, SpecialSymWins SpecialSymWins, long refBetBAse, long refWinsSoFar){
//
//        int[][] lines = PayTableCtrl.lines(); // depends on your implementation
//        PayTblItem paytableItem = PayTable.get(sym);
//
//        // 🔁 Deep copy of grid (IMPORTANT)
//        Symbol[][] newGrid = new Symbol[window.length][window[0].length];
//        for (int i = 0; i < window.length; i++) {
//            for (int j = 0; j < window[i].length; j++) {
//                newGrid[i][j] = window[i][j];
//            }
//        }
//
//        int symCount = 0;
//
//        // Expand symbol
//        for (int i = 0; i < GameConstant.REEL_COUNT; i++) {
//            boolean hasSym = false;
//
//            for (int j = 0; j < GameConstant.GRID_HEIGHT; j++) {
//
//                if (window[i][j] != sym)
//                    continue;
//
//                hasSym = true;
//
//                // expand full reel
//                for (int k = 0; k < GameConstant.GRID_HEIGHT; k++) {
//                    newGrid[i][k] = sym;
//                }
//
//                break;
//            }
//
//            if (hasSym) {
//                symCount++;
//            }
//        }
//    }
//}
