package Game.DataDef;

import Game.Symbols.Symbol;
import java.util.ArrayList;
import java.util.List;

public class LineWinData {
    public Symbol symbol;
    public int totalCount;
    public int WSCount;

    public List<SymCoordinate> symCoordinateVec = new ArrayList<>();

//    public LineWinData(Symbol symbol, int totalCount, int WSCount) {
//        this.symbol = symbol;
//        this.totalCount = totalCount;
//        this.WSCount = WSCount;
//    }

    public LineWinData() {

    }
}

