package Game.Grid;

public class Pair<K, V> {

    private K first;
    private V second;

    public Pair(K first, V second) {

        this.first = first;
        this.second = second;
        }

        // Getters
        public K getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }

        // Setters (optional)
        public void setFirst(K first) {
            this.first = first;
        }

        public void setSecond(V second) {
            this.second = second;
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }


//import Game.DataDef.SpecialSymWins;
//import Game.Symbols.Symbol;
//
//public class Pair<L extends Number, B> {
//
//    public Pair <Long, Boolean> getWinsFromSpSym(
//            Symbol sym,
//            SpecialSymWins specialSymWins,
//            long refBetBase,
//            long refWinsSoFar) {
//
//
//        return new Pair<>(0L, false);
//    }
//}