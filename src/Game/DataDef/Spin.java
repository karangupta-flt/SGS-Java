package Game.DataDef;
import Game.Symbols.Symbol;
import Game.Constant.GameConstant;

import java.util.ArrayList;
import java.util.List;

public class Spin {

        public String reelSet;
        public long winAmount = 0;
        public long refWinAmount = 0;
        public long winsSoFar = 0;
        public long refWinsSoFar = 0;
        public Symbol[][] symbol = new Symbol[GameConstant.REEL_COUNT][GameConstant.GRID_HEIGHT];

        public List<Winning> winnings = new ArrayList<>();
        public int[] stops = new int[GameConstant.REEL_COUNT];
        public List<SymCoordinate> WSSym = new ArrayList<>();
        public boolean maxWinTriggered = false;

        public Spin(String reelSet,
                    long winAmount,
                    long refWinAmount,
                    long winsSoFar,
                    long refWinsSoFar,
                    Symbol symbols,
                    List winnings,
                    int[] stops,
                    List WSSym,
                    boolean maxWinTriggered) {
                this.reelSet = reelSet;
                this.winAmount = winAmount;
                this.refWinAmount = refWinAmount;
                this.winsSoFar = winsSoFar;
                this.refWinsSoFar = refWinsSoFar;
                this.symbol = symbol;
                this.winnings = winnings;
                this.stops = stops;
                this.WSSym = WSSym;
                this.maxWinTriggered = maxWinTriggered;
        }
        public Spin(){}
}


