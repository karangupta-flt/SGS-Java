package Game.DataDef;

import Game.Constant.GameConstant;
import Game.Symbols.Symbol;

import java.util.ArrayList;
import java.util.List;

import static Game.Constant.GameConstant.GRID_HEIGHT;
import static Game.Constant.GameConstant.REEL_COUNT;

 public class Spin {

        public String reelSet;
        public long winAmount = 0;
        public long refWinAmount = 0;
        public long winsSoFar = 0;
        public long refWinsSoFar = 0;
        public Symbol[][] GridWindow = new Symbol [GRID_HEIGHT][REEL_COUNT];
        public List<Winning> winnings = new ArrayList<>();

        public int[] stops = new int[GameConstant.REEL_COUNT];
        public SymCoordinate[] wsSym;
        public boolean maxWinTriggered = false;

        public Spin(String reelSet,
                    long winAmount,
                    long refWinAmount,
                    long winsSoFar,
                    long refWinsSoFar,
                    Symbol [][] GridWindow,
                    List winnings,
                    int[] stops,
                    SymCoordinate [] wsSym,
                    boolean maxWinTriggered) {
                this.reelSet = reelSet;
                this.winAmount = winAmount;
                this.refWinAmount = refWinAmount;
                this.winsSoFar = winsSoFar;
                this.refWinsSoFar = refWinsSoFar;
                this.GridWindow = GridWindow;
                this.winnings = winnings;
                this.stops = stops;
                this.wsSym = wsSym;
                this.maxWinTriggered = maxWinTriggered;
        }


        public Spin(){
        }
}


