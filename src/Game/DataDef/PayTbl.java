package Game.DataDef;

import Game.Symbols.Symbol;

public class PayTbl {
    Symbol symbol;
    int pay;
    int n;

    public PayTbl(Symbol symbol, int pay, int n){
        this.symbol = symbol;
        this.pay = pay;
        this.n = n;
    }
}
