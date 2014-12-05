package spacetrader.game_model.gameLogic;

import spacetrader.game_model.Faction;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class FactionMultiplier {
    
    public static double getMultiplier(Faction fact1, Faction fact2) {
        switch (fact1) {
            case Space_Alliance:
                switch (fact1) {
                    case Space_Alliance:      return 1.0;
                    case Star_Empire:     return 5.0;
                    case The_Test:     return 1.5;
                    case Aliens:      return 2.0;
                    case No_Faction:return 2.5;
                }
            case Star_Empire:
                switch (fact1) {
                    case Space_Alliance:      return 1.0;
                    case Star_Empire:     return 2.5;
                    case The_Test:     return 23.2;
                    case Aliens:      return 7.8;
                    case No_Faction:return 9.0;
                }
            case The_Test:
                switch (fact1) {
                    case Space_Alliance:      return 64.2;
                    case Star_Empire:     return 45.2;
                    case The_Test:     return .01;
                    case Aliens:      return 4.3;
                    case No_Faction:return 2.4;
                }
            case Aliens:
                switch (fact1) {
                    case Space_Alliance:      return 2.3;
                    case Star_Empire:     return 4.5;
                    case The_Test:     return 8.99;
                    case Aliens:      return 221;
                    case No_Faction:return 43.2;
                }
            case No_Faction:
                switch (fact1) {
                    case Space_Alliance:      return 23.2;
                    case Star_Empire:     return 67.8;
                    case The_Test:     return 3.2;
                    case Aliens:      return 4.5;
                    case No_Faction:return 8.9;
                }
        }
        
        return 1.0; // needed to make compiler happy
    }
}
