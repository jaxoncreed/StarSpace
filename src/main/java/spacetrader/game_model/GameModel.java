package spacetrader.game_model;

import java.io.Serializable;

/**
 *
 * @author Jackson Morgan
 */
public class GameModel implements Serializable {
    private static GameModel gameModel;
    public static GameModel get() throws GameModelNotSetException{
        if(gameModel==null)
            throw new GameModelNotSetException();
        return gameModel;
    }
    public static GameModel set(Player player,Galaxy gal){
        gameModel=new GameModel(player,gal);
        return gameModel;
    }

    
    private Player player;
    private Galaxy galaxy;
    public GameModel() {};
    
    public GameModel(Player aPlayer, Galaxy aGalaxy) {
        this.player = aPlayer;
        this.galaxy = aGalaxy;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the galaxy
     */
    public Galaxy getGalaxy() {
        return galaxy;
    }

    /**
     * @param galaxy the galaxy to set
     */
    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }
    
    @Override
    public String toString() {
        return player.getName() + "; w:" + galaxy.getWidth() + "; h:" + galaxy.getHeight();
    }

    public static class GameModelNotSetException extends Exception{

        public GameModelNotSetException() {
            super("Game Model not set up");
        }
    }
}
