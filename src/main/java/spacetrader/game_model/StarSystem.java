package spacetrader.game_model;

import java.io.Serializable;
import java.util.List;

import java.util.ArrayList;
import spacetrader.game_model.graph.Node;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import spacetrader.game_model.positioncontainer.Bounds;
import spacetrader.game_model.positioncontainer.PositionContainer;

public class StarSystem implements Node {

    private String name;
    private Position pos;
    private StarType starType;
    private List<Planet> planets;
    private double starMass;
    private Map<Node, JumpPoint> jumpPoints;
    private Faction faction;
    private List<Ship> ships;

    public Position getPosition() {
        return pos;
    }

    public StarSystem(String name, Position pos, StarType starType) {
        this.name = name;
        this.pos = pos;
        this.planets = new ArrayList<Planet>();
        this.jumpPoints = new HashMap<Node, JumpPoint>();
        this.starType = starType;
        this.faction = Faction.NoFaction;
        this.ships = new ArrayList<Ship>();
    }

    public StarSystem(
            String name,
            Position pos,
            StarType starType,
            double starMass,
            Faction faction) {

        this(name, pos, starType);
        setStarMass(starMass);
        this.faction = faction;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public void removeShip(Ship ship) {
        ships.remove(ship);
    }

    public List<Ship> getShips() {
        return ships;
    }

    public double getX() {
        return pos.x;
    }

    public double getY() {
        return pos.y;
    }

    public void addPlanet(Planet planet) {
        planets.add(planet);
    }

    public void addJumpPoint(Position pos, StarSystem targetSys, Position targetPos) {
        jumpPoints.put(targetSys, new JumpPoint(pos, targetSys, targetPos));
        targetSys.asymmetricalAddJumpPoint(this, new JumpPoint(targetPos, this, pos));
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    private void asymmetricalAddJumpPoint(StarSystem from, JumpPoint jumpPoint) {
        jumpPoints.put(from, jumpPoint);
    }

    public ArrayList<StarSystem> getNeighbors() {
        ArrayList<StarSystem> out = new ArrayList();
        jumpPoints.forEach((k, v) -> {
            out.add(v.getTargetSystem());
        });
        return out;
    }

    public JumpPoint getJumpPoint(StarSystem s) {
        return jumpPoints.get(s);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.pos);
        return hash;
    }

    public void setFaction(Faction f) {

        if (f == null) {
            throw new IllegalArgumentException("faction must be non-null");
        }
        faction = f;
    }

    public final void setStarMass(double starMass) {

        if (starMass <= 0) {
            throw new IllegalArgumentException("mass must be positive");
        }
        this.starMass = starMass;
    }
    
    public List<Planet> getNearbyPlanets(Position p,int radius){
        return null;
    }
    public List<Planet> getNearbyPlanets(PositionContainer container){
        ArrayList<Planet> temp = new ArrayList<Planet>();
        for(Planet p : planets){
            if(container.contains(p.getPos()))
                temp.add(p);
        }
        return temp;
    }

    public double getStarMass() {
        return starMass;
    }

    public Faction getFaction() {
        return faction;
    }

    public StarType getStarType() {
        return starType;
    }
    public Bounds getBounds(){
        Position minx=new Position(0,0),miny=new Position(0,0),maxx=new Position(0,0),maxy =new Position(0,0);
        for(Planet p:planets){
            if(p.getPos().x<minx.x){
                minx=p.getPos();
            }
            if(p.getPos().x>maxx.x){
                maxx=p.getPos();
            }
            if(p.getPos().y<miny.y){
                miny=p.getPos();
            }
            if(p.getPos().y>maxy.y){
                maxy=p.getPos();
            }
        }
        return new Bounds(minx,miny,maxx,maxy);
    }
}
