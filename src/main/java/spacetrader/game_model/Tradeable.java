package spacetrader.game_model;

/**
 * Implement this on tradeable items
 */
public interface Tradeable {

	/**
	 * The base price for an item does not change as the game progresses. This price will be multiplied by a number of pricing factors; these factors may change as the game progresses. 
	 */
	public double getBasePrice();
}