package Game.ReelSets;

import Game.Bet.BetMode;
import Game.Constant.GameConstant;
import Game.Reel.Reel;
import Game.Round.Round;
import Game.Symbols.Symbol;
//import Game.Reel.ReelMain;




 public class ReelSetMain extends ReelSets{

//     private static final Set BG_Set1 = new Set(0, "BG_Set1", new Reel[0]);
//     private static final Set BG_Set2 = new Set(1, "BG_Set2", new Reel[0]);
//     private static final Set BG_Set3 = new Set(2, "BG_Set3", new Reel[0]);
//     private static final Set FS_Set1 = new Set(3, "FS_Set1", new Reel[0]);
//     private static final Set FS_Set2 = new Set(4, "FS_Set2", new Reel[0]);
//     private static final Set FS_Set3 = new Set(5, "FS_Set3", new Reel[0]);
//     private static final Set FS_Set4 = new Set(6, "FS_Set4", new Reel[0]);
//     private static final Set FS_Set5 = new Set(7, "FS_Set5", new Reel[0]);





     public ReelSetMain(Set[] BG_Set, Set[] FS_Set) {
        super(BG_Set, FS_Set);
    }





    //public static final Variant CURRENT_VARIANT = Variant.VARIANT_96;

    public static final double W_NORMAL_PROB =0.31706;
    public static final double W_ENHANCED_PROB= 0.52026;
    public static final double[] W_FREE_SPIN = new double[]{0.6546, 0.4951, 0.3795, 0.4876, 0.5631, 0.6732, 0.7517, 0.7859, 0.8834};


//   // static {
//        switch (CURRENT_VARIANT) {
//
//            case VARIANT_96:
//                W_NORMAL_PROB = 0.31706;
//                W_ENHANCED_PROB = 0.52026;
//                W_FREE_SPIN = new double[]{0.6546, 0.4951, 0.3795, 0.4876, 0.5631, 0.6732, 0.7517, 0.7859, 0.8834};
//                break;
//            case VARIANT_94:
//                W_NORMAL_PROB =  0.27400;
//                W_ENHANCED_PROB = 0.53785;
//                W_FREE_SPIN = new double[]{0.5302, 0.3354, 0.1925, 0.2740, 0.3261, 0.4117, 0.4627, 0.4626, 0.5147 };
//                break;
//            case VARIANT_92:
//                W_NORMAL_PROB = 0.22951;
//                W_ENHANCED_PROB = 0.55598;
//                W_FREE_SPIN = new double[]{0.4057, 0.1756, 0.0055, 0.0604, 0.0891, 0.1502, 0.1738, 0.1392, 0.1460};
//                break;
//
//            default:
//                W_NORMAL_PROB = 0;
//                W_ENHANCED_PROB = 0;
//                W_FREE_SPIN = new double[]{};
//        }
//    }


    @Override
    public void select(int Mode, boolean BaseSpin, int fsLevel) {
        double prob = Math.random();


        if (!BaseSpin) {
            // It's a free spin
            double weight = W_FREE_SPIN[fsLevel - 1];

            selected = (prob < weight) ? FS_Set[0] : FS_Set[1];

            System.out.println("[reelSet] selected reel set \"" + selected.getName() + "\"");

        }
        //else it's a base spin.
        double weight = (Mode == BetMode.MODE_NORMAL.ordinal() ? W_NORMAL_PROB : W_ENHANCED_PROB);
        if (Mode == BetMode.MODE_NORMAL.ordinal()){
            selected = (prob < weight) ? BG_Set[0] : BG_Set[1];
        }
        else {
            selected = (prob < weight) ? BG_Set[0] : BG_Set[2];
        }
        System.out.println("[reelSet] selected reel set \"" + selected.getName() + "\"");

    }

    //guaranteed win reelSet based on special symbol.
     @Override
    public void selectSpecialReelSet(Symbol specialSymbol) {
        int selectedReelSet = 0;

        switch (specialSymbol) {
            case H3:
            case L5:
            case L8:
                selectedReelSet = 3;
                break;

            case H2:
            case L6:
            case L9:
                selectedReelSet = 4;
                break;

            case WS:
                break;
            case H1:
            case H4:
            case L7:
                selectedReelSet = 5;
                break;
            default:
                throw new IllegalStateException("unsavoury special symbol while choosing special reelSet");

        }
        selected = FS_Set[selectedReelSet - 1];
        System.out.println("[reelSet] selected reel set \"" + selected.getName() + "\"");

    }

     @Override
     public void Spin(boolean baseGame, int[] stops) {
        Reel[] reels = selected.getReels();
        for (int i = 0; i < reels.length; i++) {
            Reel reel = reels[i];
            int stop = reel.Spin();
            stops[i] = stop;
           // System.out.println("stop position : "+ stops[i]);
        }


    }



     @Override
     public int[] getStops() {

         Reel[] reels = selected.getReels();
         int[] stops = new int[GameConstant.REEL_COUNT];


         for (int i = 0; i < reels.length; i++) {
             Reel reel = reels[i];
             stops[i] = reel.stopPos();
             System.out.println("reading stop = " +reel.stopPos());


         }
         return stops;
     }
 }
