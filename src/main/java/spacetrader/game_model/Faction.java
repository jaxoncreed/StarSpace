
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model;
//import spacetrader.graph.Heurstic;
//import spacetrader.graph.Node;
import spacetrader.game_model.system.StarSystem;
import java.io.Serializable;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public enum Faction implements Serializable {
    Space_Alliance, Star_Empire, No_Faction, The_Test, Aliens;
    @Override
    public String toString(){
        switch(this){
            case Space_Alliance:return "Space Alliance";
            case Star_Empire:return "Star Empire";
            case No_Faction:return "No Faction";
            case The_Test:return "The Test";
            case Aliens:return "Aliens";
        }
        return "";
    }
}
