package Game.ReelSets;

import Game.Constant.GameConstant;

public class SetOfReelSets {

    public final Set[] sets;

    public SetOfReelSets(Set[] sets) {

        this.sets = sets;
    }

    public Set getRandomSet() {
        if (sets == null || sets.length == 0) {
            return null; // or throw exception
        }
        int index = (int)(sets.length * Math.random());
            return sets[index];
    }
    public int[] stops = new int[GameConstant.REEL_COUNT];

}
