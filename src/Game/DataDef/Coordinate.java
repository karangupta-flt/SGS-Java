package Game.DataDef;
import java.util.ArrayList;
import java.util.List;

 public class Coordinate {
        public final int x;
        public final int y;
        public List<Coordinate> CoordinateVec = new ArrayList<>();

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString(){
            return ("(" + this.x + ", " + this.y + ")");
        }


 }
