package spacetrader.game_model;

import spacetrader.game_model.gameLogic.Item;
import spacetrader.game_model.system.StarSystem;
import spacetrader.game_model.gameLogic.Inventory;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.system.JumpPoint;
import java.io.Serializable;
import org.jbox2d.dynamics.Body;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import spacetrader.game_model.interactable.InteractableObject;
import spacetrader.game_model.interactable.InteractionType;

/**
 * Ship model!
 */
public class ShipDef {

    public String      name;
    public double      basePrice;
    public int         firePower;
    public Inventory   cargo;
    public int         health;
    public int         maxHealth;
    public double      linearThrust;
    public double      maxLinearSpeed;
    public float       linearBrake;
    public double      angularThrust;
    public double      maxAngularSpeed;
    public float       angularBrake;
    public double      interactionRange;

    public ShipDef() {
        this.name              = "Test Ship";
        this.basePrice         = 1000;
        this.firePower         = 10;
        this.health            = 100;
        this.maxHealth         = 100;
        this.linearThrust      = 0.005;
        this.maxLinearSpeed    = 0.2;
        this.linearBrake       = 1.5f;
        this.angularThrust     = 0.001;
        this.maxAngularSpeed   = 2.0;
        this.angularBrake      = 3.0f;
        this.interactionRange  = 100.0;
    }

}

