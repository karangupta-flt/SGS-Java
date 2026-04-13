package Game.Grid;

import Game.DataDef.SpecialSymWins;
import Game.DataDef.SymCoordinate;
import Game.DataDef.Winning;
import Game.ReelSets.ReelSetMain;
import Game.Round.Round;
import Game.Symbols.Symbol;

import java.util.List;

 public class Grid {

    //public ReelSets reelSets;
    protected ReelSetMain reelSets;
    protected Symbol[][] GridWindow;   // Grid window (5x3)
    protected Round round;

    public Grid(ReelSetMain reelSets) {
        this.reelSets = reelSets;
        this.GridWindow = new Symbol[5][3];
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

        GridWindow[i][j] = sym;
    }

    public Symbol getSymbol(int i, int j) {

        return GridWindow[i][j];
    }

    public long getWinnings(Winning winning, long refBetBase) {
        return 0L;
    }


    public Pair<Long, Boolean> getWinsFromSpSym(Symbol sym, SpecialSymWins specialSymWins, long refBetBase, long refWinsSoFar){
        return new Pair<>(0L, false);
    }

    public void setRoundRef(Round round) {
        this.round = round;
        reelSets.setRoundRef(round);
    }

    public void snapshot(Symbol[][] grid) {}

    private Pair<Symbol,Integer> evaluateWinOptions(List<Symbol> symLine){
        Symbol symbol = null;
        int count = 0;
        return new Pair<>(symbol, count);
    }

 }