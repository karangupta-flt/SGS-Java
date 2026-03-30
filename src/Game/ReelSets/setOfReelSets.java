package Game.ReelSets;
import java.util.List;
import java.util.Random;
import Game.Constant.GameConstant;

public class setOfReelSets {

    public final Set[] sets;

    public setOfReelSets(Set[] sets) {

        this.sets = sets;
    }

    // Equivalent of: const Set* getRandomSet() const
    public Set getRandomSet() {
        if (sets == null || sets.length == 0) {
            return null; // or throw exception
        }
        int index = (int)(sets.length * Math.random());
            return sets[index];
    }
    public int[] stops = new int[GameConstant.REEL_COUNT];

}
