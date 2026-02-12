import java.util.*;

public class gameConstant {


        //  RTP Variant
        final class RTPConfig {
                public static final double RTP;

                static {
                        String variant = System.getProperty("variant", "96"); // default 96 if not provided

                        if ("96".equals(variant)) {
                                RTP = 96.00;
                        } else if ("94".equals(variant)) {
                                RTP = 94.00;
                        } else if ("92".equals(variant)) {
                                RTP = 92.00;
                        } else {
                                throw new RuntimeException("unknown variant as compilation flag");
                        }

                }

        }

        // Game Metadata
        public static final String gameCore    = "bdg3479";
        public static final String gameVersion = "1.0";
        public static final String gameStudio  = "BigDaddy";
        public static final String gameName    = "legacyOfDesperado";
        public static final String parSheetVersion= "v2.2";

        // Game Structure Constants
        public static final int MAX_WIN_CAP = 5000;
        public static final int REEL_COUNT = 5;
        public static final int GRID_HEIGHT = 3;
        public static final int MAX_SCATTER_COUNT = 5;


}
