package Game.GamePlay;

import Game.Bet.BetConfig;
import Game.Bet.BetMode;
import Game.DataDef.*;
import Game.GameConfig.GameConfig;
import Game.Grid.Grid;
import Game.Reel.Reel;
import Game.ReelSets.DataReelSets;
import Game.ReelSets.ReelSetMain;
import Game.ReelSets.ReelSets;
import Game.ReelSets.Set;
import Game.Round.RoundMain;
import Game.Grid.GridMain;

import java.util.HashMap;
import java.util.Map;

import static Game.Bet.BetConfig.*;
import static Game.Constant.GameConstant.MAX_WIN_CAP;

 public class GameMain extends GamePlay{

     Map<BetMode, Map<Integer, Boolean>> betmap = new HashMap<>();


     public void GamePlay() {
         reelSets = initReels();
         createBetMap();

     }


    @Override
    public GameConfig getConfig() {
    GameConfig config = new GameConfig();
    config.ReelSets = reelSets;
    return config;
    }

    @Override
    public void collectRands(PlayResponse response) {

    }


    @Override
    public void init() {

        return;
    }

    @Override
    public PlayResponse Play(PlayOptions options) {
            PlayResponse playResponse = new PlayResponse();

            GridMain grid = new GridMain((ReelSetMain) reelSets);

            playResponse.winAmount = 0;
            playResponse.betAmount= options.betAmount;
            playResponse.refBetBase = getRefBetBase(options);

            playResponse.refBetAmount = playResponse.refBetBase * options.currencyMultiplier;

            playResponse.currencyMultiplier = options.currencyMultiplier;
            playResponse.featureMode = options.featureMode;
            playResponse.action = "spin";

            // Create Round object
            RoundMain round = new RoundMain(playResponse, grid);

            try {
                round.play();
                collectRands(playResponse);
                playResponse.refWinAmount = calculateWins (playResponse);
            }
            catch (Exception e) {
                System.err.println("Exception caught: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }

            return playResponse;
        }

    @Override
    public PlayResponse next(NextPlay next, PlayResponse prev) {
        PlayResponse playResponse = new PlayResponse();

        GridMain grid = new GridMain((ReelSetMain) reelSets);

        RoundMain round = new RoundMain(playResponse, grid);

        try {
            round.next((Boolean) next.getGamble(), false);
            collectRands(playResponse);
            playResponse.refWinAmount = calculateWins(playResponse);
        }
        catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
            e.printStackTrace();
            throw e;   // rethrow
        }

// Apply multiplier after successful round
        applyCurrencyMultiplier(playResponse);

        return playResponse;
    }



     public void applyCurrencyMultiplier(PlayResponse r) {

        long currMp = r.currencyMultiplier;

        // play response
        r.winAmount = r.refWinAmount * currMp;

        // base-spin
        r.baseSpin.winAmount = r.baseSpin.refWinAmount * currMp;
        r.baseSpin.winsSoFar = r.baseSpin.refWinsSoFar * currMp;

        for (Winning w : r.baseSpin.winnings) {
            w.winAmount = w.refWinAmount * currMp;
        }

        // freeSpin
        for (FreeSpin f : r.freeSpins) {

            f.winAmount     = f.refWinAmount * currMp;
            f.winsSoFar     = f.refWinsSoFar * currMp;
            f.baseWinAmount = f.refBaseWinAmount * currMp;
            f.ssWinAmount   = f.refSsWinAmount * currMp;

            for (Winning w : f.winnings) {
                w.winAmount = w.refWinAmount * currMp;
            }

            for (SpecialSymWins sw : f.SpecialSymWinnings) {

                sw.winAmount = sw.refWinAmount * currMp;
                sw.winsSoFar = sw.refWinsSoFar * currMp;

                for (Winning w : sw.Winnings) {
                    w.winAmount = w.refWinAmount * currMp;
                }
            }
        }
    }

    public long calculateWins(PlayResponse playResponse) {

        long refWinAmount = 0;
        long maxWinAmount = MAX_WIN_CAP * playResponse.refBetBase;

        refWinAmount += playResponse.baseSpin.refWinAmount;

        for (Spin freeSpin : playResponse.freeSpins) {
            refWinAmount += freeSpin.refWinAmount;

        }
        if (refWinAmount >= maxWinAmount) {
            return maxWinAmount;
        } else {
            return refWinAmount;
        }
        //return (refWinAmount >= maxWinAmount) ? maxWinAmount : refWinAmount;

    }

    public long getRefBetBase(PlayOptions options){
        long betAmount =          options.betAmount;
        long currencyMultiplier = options.currencyMultiplier;
        long FeatureMode =        options.featureMode;
        boolean buyFeature =      options.buyFeature;


        BetMode mode = BetMode.values()[(int) FeatureMode];
        if (!betmap.containsKey(mode)) {
            throw new IllegalArgumentException("invalid featureMode");
        }

        if ((!buyFeature && FeatureMode != 0) ||
                (buyFeature && FeatureMode == 0)) {
            throw new IllegalArgumentException("incompatible feature mode and buy");
        }

        Map<Integer, Boolean> betSizeMap = betmap.get(mode);

        long normalizedBetAmount = betAmount / currencyMultiplier;

        if (!betSizeMap.containsKey(normalizedBetAmount)) {
            throw new IllegalArgumentException("invalid bet size");
        }

        BetConfig config = ALL_BETS_CONFIG.get((int)FeatureMode);

        return (normalizedBetAmount * CREDITS_PER_BET) / config.cost;


    }

    public ReelSets initReels() {

        Set BG_Set1 = new Set(0, "BG_ReelSet1",
                new Reel[]{
                        new Reel(0, DataReelSets.BG_Set1_REEL1),
                        new Reel(1, DataReelSets.BG_Set1_REEL2),
                        new Reel(2, DataReelSets.BG_Set1_REEL3),
                        new Reel(3, DataReelSets.BG_Set1_REEL4),
                        new Reel(4, DataReelSets.BG_Set1_REEL5)
                }
        );


        Set BG_Set2 = new Set(0, "BG_ReelSet2",
                new Reel[]{
                        new Reel(0, DataReelSets.BG_Set2_REEL1),
                        new Reel(1, DataReelSets.BG_Set2_REEL2),
                        new Reel(2, DataReelSets.BG_Set2_REEL3),
                        new Reel(3, DataReelSets.BG_Set2_REEL4),
                        new Reel(4, DataReelSets.BG_Set2_REEL5)
                }

        );

        Set BG_Set3 = new Set(0, "BG_ReelSet3",
                new Reel[]{
                        new Reel(0, DataReelSets.BG_Set3_REEL1),
                        new Reel(1, DataReelSets.BG_Set3_REEL2),
                        new Reel(2, DataReelSets.BG_Set3_REEL3),
                        new Reel(3, DataReelSets.BG_Set3_REEL4),
                        new Reel(4, DataReelSets.BG_Set3_REEL5)
                }

        );


        Set FS_Set1 = new Set(0, "FS_ReelSet1",
                new Reel[]{
                        new Reel(0, DataReelSets.FS_Set1_REEL1),
                        new Reel(1, DataReelSets.FS_Set1_REEL2),
                        new Reel(2, DataReelSets.FS_Set1_REEL3),
                        new Reel(3, DataReelSets.FS_Set1_REEL4),
                        new Reel(4, DataReelSets.FS_Set1_REEL5)
                }

        );

        Set FS_Set2 = new Set(0, "FS_ReelSet2",
                new Reel[]{
                        new Reel(0, DataReelSets.FS_Set2_REEL1),
                        new Reel(1, DataReelSets.FS_Set2_REEL2),
                        new Reel(2, DataReelSets.FS_Set2_REEL3),
                        new Reel(3, DataReelSets.FS_Set2_REEL4),
                        new Reel(4, DataReelSets.FS_Set2_REEL5)
                }

        );

        Set FS_Set3 = new Set(0, "FS_ReelSet3",
                new Reel[]{
                        new Reel(0, DataReelSets.FS_Set3_REEL1),
                        new Reel(1, DataReelSets.FS_Set3_REEL2),
                        new Reel(2, DataReelSets.FS_Set3_REEL3),
                        new Reel(3, DataReelSets.FS_Set3_REEL4),
                        new Reel(4, DataReelSets.FS_Set3_REEL5)
                }

        );

        Set FS_Set4 = new Set(0, "FS_ReelSet4",
                new Reel[]{
                        new Reel(0, DataReelSets.FS_Set4_REEL1),
                        new Reel(1, DataReelSets.FS_Set4_REEL2),
                        new Reel(2, DataReelSets.FS_Set4_REEL3),
                        new Reel(3, DataReelSets.FS_Set4_REEL4),
                        new Reel(4, DataReelSets.FS_Set4_REEL5)
                }

        );

        Set FS_Set5 = new Set(0, "FS_ReelSet5",
                new Reel[]{
                        new Reel(0, DataReelSets.FS_Set5_REEL1),
                        new Reel(1, DataReelSets.FS_Set5_REEL2),
                        new Reel(2, DataReelSets.FS_Set1_REEL3),
                        new Reel(3, DataReelSets.FS_Set1_REEL4),
                        new Reel(4, DataReelSets.FS_Set5_REEL5)
                }

        );

        return new ReelSets(
                new Set[] {BG_Set1, BG_Set2, BG_Set3},
                new Set[] {FS_Set1, FS_Set2, FS_Set3, FS_Set4, FS_Set5}
        );
    }

    @Override
    public void createBetMap() {

        // NORMAL
        Map<Integer, Boolean> betMap = new HashMap<>();
        for (int i : BET_NORMAL_ARRAY) {
            betMap.put(i, true);
        }
        betmap.put(BetMode.MODE_NORMAL, betMap);

        // ENHANCED
        betMap = new HashMap<>();
        for (int i : BET_ENHANCED_ARRAY) {
            betMap.put(i, true);
        }
        betmap.put(BetMode.MODE_ENHANCED, betMap);

        // FEATURE BUY 1
        betMap = new HashMap<>();
        for (int i : BET_FB1_ARRAY) {
            betMap.put(i, true);
        }
        betmap.put(BetMode.MODE_FEATURE_BUY_1, betMap);

        // FEATURE BUY 2
        betMap = new HashMap<>();
        for (int i : BET_FB2_ARRAY) {
            betMap.put(i, true);
        }
        betmap.put(BetMode.MODE_FEATURE_BUY_2, betMap);

        // FEATURE BUY 3
        betMap = new HashMap<>();
        for (int i : BET_FB3_ARRAY) {
            betMap.put(i, true);
        }
        betmap.put(BetMode.MODE_FEATURE_BUY_3, betMap);
    }

//     public void play() {
//     }
 }
