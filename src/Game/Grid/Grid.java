package Game.Grid;

import Game.DataDef.SymCoordinate;
import Game.DataDef.Winning;
import Game.Reel.Reel;
import Game.ReelSets.ReelSetMain;
import Game.ReelSets.ReelSets;
import Game.Round.Round;
import Game.Symbols.Symbol;

import java.util.Map;

public class Grid {

    public ReelSets reelSets;
    //public ReelSetMain reelSets;
    public Symbol[][] window;   // Grid window (5x3)
    public Round round;

    public Grid(ReelSetMain reelSets) {
        this.reelSets = reelSets;
        this.window = new Symbol[5][3];
    }
//public Grid(ReelSetMain reelSets) {
//    this.reelSets = reelSets;
//    this.window = new Symbol[5][3];
//}

    public void Spin(boolean baseGame, int[] stops, SymCoordinate[] wsSym) {}

    public void selectReelSet(int mode, boolean baseSpin, int fsLevel) {

        reelSets.select(mode, baseSpin, fsLevel);
    }

    public void selectSpecialReelSet(Symbol specialSymbol) {

        reelSets.selectSpecialReelSet(specialSymbol);
    }

    public String getReelSetName() {

        return reelSets.getSelected().getName();
    }

    public void setSymbol(int i, int j, Symbol sym) {

        window[i][j] = sym;
    }

    public Symbol getSymbol(int i, int j) {

        return window[i][j];
    }

    public long getWinnings(Winning winning, long refBetBase) {
        return 0L;
    }

//    public Pair<long,Boolean> getWinsFromSpSym(
//            Symbol sym,
//            SpecialSymWins specialSymWins,
//            long refBetBase,
//            long winsSoFar) {
//        return null;
//    }

    public void setRoundRef(Round round) {
        this.round = round;
        reelSets.setRoundRef(round);
    }

    public void snapshot(Symbol[][] grid) {}

    public void clear() {}

    public void printGrid(String message) {}

    public void printGrid(String message, Symbol[][] win) {}

    private void printWinSymMap(Map<Symbol, winSym> symMap) {}

    private void printHighlightWinningSym(Winning winning) {}

    private void evaluateWinOptions(Reel symLine){}

    public void selectSpecialReelset(Symbol gWinSym) {}
}