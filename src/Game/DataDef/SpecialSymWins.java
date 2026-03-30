package Game.DataDef;
import Game.Symbols.Symbol;
import Game.Constant.GameConstant;
import java.util.*;

public class SpecialSymWins {
    public Symbol Symbol;
    public ArrayList<Winning> Winnings = new ArrayList<>();
    public Symbol[][] Symbols = new Symbol[GameConstant.REEL_COUNT][GameConstant.GRID_HEIGHT];
    public long winAmount = 0;
    public long refWinAmount = 0;
    public long winSoFar = 0;
    public long refWinSoFar = 0;
    public boolean maxWinTriggered = false;
    public long winsSoFar;
    public long refWinsSoFar;

    public SpecialSymWins(){

    }

}
