package Game.ReelSets;

import Game.Round.Round;
import Game.Symbols.Symbol;

 public class ReelSets {

        public Set[] BG_Set;
        public Set[] FS_Set;
        protected Round round;
        protected Set selected;
        protected SetOfReelSets BG;
        protected SetOfReelSets FS;
        public int[] stops;

        public ReelSets (Set[] BG_Set, Set[] FS_Set) {
                this.BG_Set = BG_Set;
                this.FS_Set = FS_Set;
        }
        public ReelSets(){}

        public void select(int mode, boolean baseSpin, int fsLevel) {};

        public void selectSpecialReelSet(Symbol specialSymbol) {}

        public void Spin(boolean baseGame, int[] stops) {}

        public void setRoundRef(Round round) {
                this.round = round;

                for (Set set : BG_Set) {
                    set.setRoundRef(round);
                }

                for (Set set : FS_Set) {
                        set.setRoundRef(round);
                }
        }


        public void getStops(int[] stops) {
               this.stops = stops;
        }

        public Set getSelected() {
                return selected;
        }

        public Set[] getBGSet() {
                return BG_Set;
        }

        public Set[] getFSSet() {
                return FS_Set;
        }

        private void printState(String message) {
        }

 }
