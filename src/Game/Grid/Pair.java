package Game.Grid;

import Game.DataDef.BaseSpin;

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


//    public K getKey() {
//        return Key;
//    }
//
//    public V getValue() {
//        return null;
//    }
}


