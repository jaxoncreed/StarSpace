package spacetrader.game_model.gameLogic;

import spacetrader.game_model.Faction;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class FactionMultiplier {
    
    public static double getMultiplier(Faction fact1, Faction fact2) {
        switch (fact1) {
            case Asdf:
                switch (fact1) {
                    case Asdf:      return 1.0;
                    case Test1:     return 5.0;
                    case Test2:     return 1.5;
                    case Ghjk:      return 2.0;
                    case No_Faction:return 2.5;
                }
            case Test1:
                switch (fact1) {
                    case Asdf:      return 1.0;
                    case Test1:     return 2.5;
                    case Test2:     return 23.2;
                    case Ghjk:      return 7.8;
                    case No_Faction:return 9.0;
                }
            case Test2:
                switch (fact1) {
                    case Asdf:      return 64.2;
                    case Test1:     return 45.2;
                    case Test2:     return .01;
                    case Ghjk:      return 4.3;
                    case No_Faction:return 2.4;
                }
            case Ghjk:
                switch (fact1) {
                    case Asdf:      return 2.3;
                    case Test1:     return 4.5;
                    case Test2:     return 8.99;
                    case Ghjk:      return 221;
                    case No_Faction:return 43.2;
                }
            case No_Faction:
                switch (fact1) {
                    case Asdf:      return 23.2;
                    case Test1:     return 67.8;
                    case Test2:     return 3.2;
                    case Ghjk:      return 4.5;
                    case No_Faction:return 8.9;
                }
        }
        
        return 1.0; // needed to make compiler happy
    }
}
