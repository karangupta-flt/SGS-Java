package Game.DataDef;

import Game.Symbols.Symbol;
import java.util.ArrayList;
import java.util.List;



 public class FreeSpin extends Spin{
    public final String spinType = "FreeSpin";
    public int index = 0;
//    public List<Symbol> SpecialSymbols = new ArrayList<>();
    public List<Symbol> SpecialSymbols ;
    public String guaranteedWinSym;
    public long baseWinAmount = 0;
    public long refBaseWinAmount = 0;
    public long ssWinAmount = 0;
    public long refSsWinAmount = 0;
    public long SpecialSymWinAmount = 0;
    public long refSpecialSymWinAmount = 0;
    public List<SpecialSymWins> ssWinnings = new ArrayList<>();

 }
