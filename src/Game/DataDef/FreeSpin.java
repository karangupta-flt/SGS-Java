package Game.DataDef;

import Game.Symbols.Symbol;
import java.util.ArrayList;
import java.util.List;



 public class FreeSpin extends Spin{
    public String spinType = "FreeSpin";
    public int index = 0;
  // public List<Symbol> SpecialSymbols = new ArrayList<>();
    public List<Symbol> SpecialSymbols ;
    public String guaranteedWinSym;
    public long baseWinAmount = 0;
    public long refBaseWinAmount = 0;
    public long ssWinAmount = 0;
    public long refSsWinAmount = 0;
    public List<SpecialSymWins> ssWinnings = new ArrayList<>();


     public FreeSpin(
             List<Symbol> SpecialSymbols,
             String guaranteedWinSym,
             long baseWinAmount,
             long refBaseWinAmount,
             long ssWinAmount,
             long refSsWinAmount,
             int index,
             String spinType,
             List<SpecialSymWins> ssWinnings) {
         this.SpecialSymbols = SpecialSymbols;
         this.guaranteedWinSym = guaranteedWinSym;
         this.baseWinAmount = baseWinAmount;
         this.refBaseWinAmount = refBaseWinAmount;
         this.ssWinAmount = ssWinAmount;
         this.refSsWinAmount =refSsWinAmount;
         this.index = index;
         this.spinType = spinType;
         this.ssWinnings = ssWinnings;
         
         
     }
     //default constructor.
     public FreeSpin() {

     }
     @Override
     public String toString() {

         return "FreeSpin{" +
                 "spinType='" + spinType + '\'' +
                 ", winAmount=" + winAmount +
                 ", refWinAmount=" + refWinAmount +
                 ", reelSet='" + reelSet + '\'' +
                 ", guaranteedWinSym=" + guaranteedWinSym +
                 ", SpecialSymbols=" + SpecialSymbols +
                 ", baseWinAmount=" + baseWinAmount +
                 ", refBaseWinAmount=" + refSsWinAmount +
                 ", ssWinAMount=" + ssWinAmount +
                 ", refSsWinAmount=" + refSsWinAmount +
                 ", index=" + index +
                 '}';
     }
 }
