package Game.DataDef;

import Game.Symbols.Symbol;
import Game.Constant.GameConstant;

import java.util.ArrayList;
import java.util.List;

public class Winning {
    public String type = "normal";
    public long winAmount = 0;
    public long refWinAmount = 0;
    public int payLines = -1;
    public int symCount = 0;
    public int multiplier = 1;
    public String dir;
    public Symbol symbol;
    public ArrayList<SymCoordinate> Coords = new ArrayList<>();
    public ArrayList<Winning> winnings = new ArrayList<>();
    public Symbol[][] gridWindow = new Symbol[GameConstant.REEL_COUNT][GameConstant.GRID_HEIGHT];
    public ArrayList<Symbol> specialSymbols = new ArrayList<>();
    public int payLine;
    public List<Coordinate> coords;


    //    public Winning(String normal, long winAMount, long refWinAmount, int payLines, int symCount, int multiplier, String dir, Symbol symbol,) {
//        this.normal = normal;
//        this.winAmount = winAmount;
//        this.refWinAmount = refWinAmount;
//        this.payLines = payLines;
//    }
    public Winning(){
        //default constructor.
    }

    public void add(Winning win) {
    }
}



