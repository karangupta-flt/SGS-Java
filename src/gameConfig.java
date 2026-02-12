import java.util.*;
public class gameConfig {

    public final int creditPerBet;
    public final int maxWinCap;

    public final List<BetConfig> betConfig;
    public final List<PayTbl> payTable;

    public final Reelset reelsets;

    public final String gameCore;
    public final String gameStudio;
    public final String gameVersion;
    public final String gameName;
    public final String variant;
    public final String parSheetVersion;


    public final int[][] paylines;

    public gameConfig(
            int creditPerBet,
            int maxWinCap,
            List<BetConfig> betConfig,
            List<PayTbl> payTable,
            Reelset reelsets,
            String gameCore,
            String gameStudio,
            String gameVersion,
            String gameName,
            String variant,
            String parSheetVersion,
            int [][] paylines
    )
    {

            this.creditPerBet = creditPerBet;
            this.maxWinCap = maxWinCap;
            this.betConfig = betConfig;
            this.payTable = payTable;
            this.reelsets = reelsets;
            this.gameCore = gameCore;
            this.gameStudio = gameStudio;
            this.gameVersion = gameVersion;
            this.gameName = gameName;
            this.variant = variant;
            this.parSheetVersion = parSheetVersion;
            this.paylines = paylines;
    }
}
