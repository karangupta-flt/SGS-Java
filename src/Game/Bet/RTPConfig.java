package Game.Bet;

// Class for RTP configuration
public final class RTPConfig {
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
//        switch (variant) {  // LEMBDA EXPRESSIONS
//            case "96" -> RTP = 96.0;
//            case "94" -> RTP = 94.0;
//            case "92" -> RTP = 92.0;
//            default -> throw new RuntimeException("Unknown variant as compilation flag");
//        }
//    }

    //private config.RTPConfig() {} // class only used statically
}
