package Game.DataDef;

import Game.Symbols.Symbol;

public class SymCoordinate {
    public final int x;
    public final int y;
    public final Symbol symbol;

    public SymCoordinate(int x, int y, Symbol symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    public String toString(){
        return("symCoordinate : " +this.x + ", " + this.y +"," + this.symbol);
    }
}
