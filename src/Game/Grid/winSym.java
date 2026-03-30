package Game.Grid;
import java.util.ArrayList;
import java.util.List;
import Game.DataDef.Coordinate;

public class winSym {
    public int count;
    public List<Coordinate> coordinates;
    public void WinSym() {
        this.count = 0;
        this.coordinates = new ArrayList<>();
    }
}

