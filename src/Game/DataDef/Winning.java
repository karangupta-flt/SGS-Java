package Game.DataDef;

import Game.Symbols.Symbol;
import Game.Constant.GameConstant;

import java.util.ArrayList;
import java.util.List;

import static Game.Constant.GameConstant.GRID_HEIGHT;
import static Game.Constant.GameConstant.REEL_COUNT;


 public class Winning {
    public String type = "normal";
    public long winAmount = 0;
    public long refWinAmount = 0;
    public int payLine = -1;
    public int symCount = 0;
    public int multiplier = 1;
    public String dir;
    public Symbol symbol;

    public List<Coordinate> coords = new ArrayList<>();
    public ArrayList<Winning> winnings = new ArrayList<>();
    public Symbol[] GridColumn = new Symbol[GameConstant.GRID_HEIGHT];
    public  Symbol[][] window = new Symbol[REEL_COUNT][GRID_HEIGHT];
    List<Symbol> symbols = new ArrayList<>();



    public Winning(){
        //default constructor.
    }

    public String toString() {
        return "{" +
                "type='" + type + '\'' +
                ", payLine=" + payLine +
                ", symbol=" + symbol +
                ", symCount=" + symCount +
                ", multiplier=" + multiplier +
                ", winAmount=" + winAmount +
                ", refWinAmount=" + refWinAmount +
                ", dir='" + dir + '\'' +
                ", coords=" + coords +
                '}'+ '\n';

    }


 }





