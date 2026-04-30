package Game.DataDef;

import Game.Symbols.Symbol;
import java.util.ArrayList;
import java.util.List;


public class FreeSpin extends Spin{
    public final String spinType = "FreeSpin";
    public int index = 0;
    public List<Symbol> SpecialSymbol = new ArrayList<>();
    public String guaranteedWinSym;
    public long baseWinAmount = 0;
    public long refBaseWinAmount = 0;
    public long ssWinAmount = 0;
    public long refSsWinAmount = 0;
//    public long SpecialSymWinAmount = 0;
//    public long refSpecialSymWinAmount = 0;
    public List<SpecialSymWins> SpecialSymWinnings = new ArrayList<>();






//    public FreeSpin(String reelSet, long winAmount, long refWinAmount, long winsSoFar, long refWinsSoFar, Symbol symbols, List winnings, int stops, List WSSym, boolean maxWinTriggered) {
//    }
}
