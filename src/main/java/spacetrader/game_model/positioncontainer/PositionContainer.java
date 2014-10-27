/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model.positioncontainer;

import spacetrader.game_model.Position;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public abstract class PositionContainer {
    public abstract boolean contains(Position p);
}
