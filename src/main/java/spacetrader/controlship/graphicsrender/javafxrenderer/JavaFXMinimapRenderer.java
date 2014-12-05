/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship.graphicsrender.javafxrenderer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import spacetrader.game_model.system.JumpPoint;
import spacetrader.game_model.system.*;
import spacetrader.game_model.player.Player;
import spacetrader.game_model.gameLogic.Position;
import javafx.scene.image.Image;
import spacetrader.game_model.GameModel;

/**
 *
 * @author Ben Hirsch <bhirsch42@gatech.edu>
 */
public class JavaFXMinimapRenderer extends JavaFXRenderer{
    private Position position;
    private StarSystem system;
    private Player player;
    public JavaFXMinimapRenderer(Position normalize) {
        this.position = normalize;
    }
    
    public void setSystem(StarSystem system) {
        this.system = system;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    @Override
    public void draw() {
        graphics.drawImage(SpriteManager.MINIMAP_BG, 0, 0);

        Position zero = new Position(100, 620);
        double mapScale = 0.07;
        // star
        graphics.setFill(Color.WHITE);
        graphics.fillOval(zero.x, zero.y, 20, 20);

        // planets
        graphics.setFill(Color.BROWN);
        for (Planet planet : system.getPlanets()) {
            Position pos = new Position(planet.getPosition());
            pos.scale(mapScale);
            pos.add(zero);
            graphics.fillOval(pos.x, pos.y, 10, 10);
        } 

        // jumppoints
        graphics.setFill(Color.PURPLE);
        for (JumpPoint jumpPoint : system.getJumpPoints()) {
            Position pos = new Position(jumpPoint.getFromPosition());
            pos.scale(mapScale);
            pos.add(zero);
            System.out.println(GameModel.get().getPlayer().getNextJumpPoint()+" "+jumpPoint);
            
            System.out.println(jumpPoint.equals(GameModel.get().getPlayer().getNextJumpPoint()));

            if(jumpPoint.equals(GameModel.get().getPlayer().getNextJumpPoint())){
                graphics.setFill(Color.WHITE);
            }else{
                graphics.setFill(Color.PURPLE);               
            }
            graphics.fillOval(pos.x, pos.y, 10, 10);
        } 

        // player
        graphics.setFill(Color.GREEN);
        Position pos = new Position(player.getPosition());
        pos.scale(mapScale);
        pos.add(zero);
        graphics.fillOval(pos.x, pos.y, 10, 10);
 
        graphics.drawImage(SpriteManager.MINIMAP_FRAME, 0, 0);
    }

    @Override
    public void init() {
    }

    @Override
    public void end() {
    }
    
}
