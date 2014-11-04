package spacetrader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import spacetrader.game_model.GameModel;

/**
 *
 * @author Jackson Morgan
 */
public class GameSaver {
    GameModel gameModel;
    
    public GameSaver(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    
    public void saveGame() {
        try {
            FileOutputStream fileOut =
            new FileOutputStream("./saves/game.starspace");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.gameModel);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in ./saves/game.starspace");
        } catch (IOException i) {
            i.printStackTrace();
        }
        System.out.println("Game Saved");
    }
    
    public GameModel loadGame() throws IOException, ClassNotFoundException {
        GameModel loadedGame;
        FileInputStream fileIn = new FileInputStream("./saves/game.starspace");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        loadedGame = (GameModel) in.readObject();
        in.close();
        fileIn.close();
        System.out.println("Game Loaded");
        return loadedGame;
    }
}
