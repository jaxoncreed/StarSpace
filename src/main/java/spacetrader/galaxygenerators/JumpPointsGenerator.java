package spacetrader.galaxygenerators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import spacetrader.game_model.system.Galaxy;
import spacetrader.game_model.system.StarSystem;
import spacetrader.game_model.system.StarType;
import java.util.List;
import java.util.Map;
import spacetrader.game_model.GameModel;
import spacetrader.game_model.system.JumpPoint;
import spacetrader.game_model.system.Planet;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.Ship;
import spacetrader.game_model.graph.Graph;
import spacetrader.game_model.positioncontainer.Bounds;
import spacetrader.shared.Util;

/**
 * @author Michael Lane <mlane@gatech.edu>
 */
public class JumpPointsGenerator {

    /**
     * The average number of jump points per star system
     */
    private double numJumpsMean;
    /**
     * the deviation to the number of jump points per star system
     */
    private double numJumpsSD;
    /**
     * true iff black holes should always be connected; otherwise, black holes
     * are treated the same as any other StarType.
     */
    public boolean connectBlackHoles;

    private List<JumpPoint> jumpPoints;
    private GameModel gameModel;

    public JumpPointsGenerator() {
        jumpPoints = new ArrayList();
        gameModel=GameModel.get();
    }

//    private double calculateAttraction(StarSystem system1, StarSystem system2) {
//
//        double dist = system1.getPosition().distTo(system2.getPosition());
//        double starMass1 = system1.getStarMass();
//        double starMass2 = system2.getStarMass();
//
//        return constant * (starMass1 + starMass2)
//                / //----------------------------------
//                Math.pow(dist, 1.5);
//    }

//    public Galaxy generate() {
//
//        Galaxy galaxy = gameModel.getGalaxy();
//
//        // #todo terribly inefficient
//        List<StarSystem> systems = galaxy.getSystems();
//        int numSystems = systems.size();
//        boolean[] atleastOneConnection = new boolean[numSystems];
//        for (int i = 0; i < numSystems; i++) {
//            StarSystem system1 = systems.get(i);
//            for (int j = i + 1; j < numSystems; j++) {
//                StarSystem system2 = systems.get(j);
//                double attraction = calculateAttraction(system1, system2);
//                if (attraction >= threshold
//                        || (connectBlackHoles
//                        && system1.getStarType() == StarType.BLACK_HOLE)
//                        && system2.getStarType() == StarType.BLACK_HOLE) {
//
//                    atleastOneConnection[i] = true;
//                    Position pos1 = makePosition(system1);
//                    Position pos2 = makePosition(system2);
//                    jumpPoints.addAll(system1.addJumpPoint(system2, pos1, pos2));
//                }
//            }
//        }
//
//        for (int i = 0; i < numSystems; i++) {
//            if (!atleastOneConnection[i]) {
//                StarSystem system1 = systems.get(i);
//                StarSystem system2 = systems.get((int) Util.sampleFromUniformReal(0, systems.size()));
//                Position pos1 = makePosition(system1);
//                Position pos2 = makePosition(system2);
//                jumpPoints.addAll(system1.addJumpPoint(system2, pos1, pos2));
//            }
//        }
//
//        galaxy.replaceSystems(systems);
//        return galaxy;
//    }
    
    public void generate() {
        
        List<JumpPoint> jumpPoints1 = new LinkedList();
        Galaxy gax = gameModel.getGalaxy();
        List<StarSystem> systems = gax.getSystems();
        for (StarSystem system1 : systems) {
            double sample = Util.sampleFromNormal(numJumpsMean, numJumpsSD);
            int numJumps = (sample >= 1) ? (int) sample : 1;
            Map<StarSystem, Double> dists = gax.getDistancesTo(system1);
            List<StarSystem> sortedSystems = new ArrayList(systems);
            Util.sortUsingValuesInMap(sortedSystems, dists);
            Iterator<StarSystem> iter = sortedSystems.iterator();
            // throw out the first item in the sorted list, which will be the item
            // "system"
            if (iter.hasNext()) iter.next();
            int i = 0;
            while (iter.hasNext() && i++ < numJumps) {
                StarSystem system2 = iter.next();
                Position fromPos = this.makePosition(system1);
                Position toPos = this.makePosition(system2);
                if (fromPos != null && toPos != null) {
                    jumpPoints1.addAll(system1.addJumpPoint(system2, fromPos, toPos));
                } 
            }
        }
        
        Graph<StarSystem> pregraph = new Graph(jumpPoints1, null);
        List<StarSystem> disconn = pregraph.getDisconnections();
        List<JumpPoint> jumpPoints2 = new LinkedList();
        Iterator<StarSystem> iter = disconn.iterator();
        StarSystem prev = null;
        if (iter.hasNext()) prev = iter.next();
        for (StarSystem current : disconn) {
            Position fromPos = this.makePosition(prev);
            Position toPos = this.makePosition(current);
            jumpPoints2.addAll(prev.addJumpPoint(current, fromPos, toPos));
            prev = current;
        }
        Graph<StarSystem> augGraph = new Graph(jumpPoints2, null) ;
        pregraph.union(augGraph);
        
        jumpPoints.addAll(jumpPoints1);
        jumpPoints.addAll(jumpPoints2);
        
        gax.setJumpPoints(pregraph);
    }

    private Position makePosition(StarSystem system) {
        Bounds bounds = system.getBounds();
        boolean tryAgain = true;
        double shipInteraction = gameModel.getPlayer().getShip().getInteractionRange();
        int attempts = 0;
        while (tryAgain && attempts++ < 50) {
            double x1 = Util.sampleFromUniformReal(bounds.getMinX(), bounds.getMaxX());
            double y1 = Util.sampleFromUniformReal(bounds.getMinY(), bounds.getMaxY());
            Position pos = new Position(x1, y1);
            boolean broken = false;
            for (Planet p : system.getPlanets()) {
                double dist = pos.distTo(p.getPosition());
                if (dist < p.getInteractionRange()/2 + shipInteraction + JumpPoint.INTERACTION_RANGE/2) {
                    broken = true;
                    break;
                }
            }
            if (!broken) {
                for (JumpPoint jp : system.getJumpPoints()) {
                    double dist = pos.distTo(jp.getFromPosition());
                    if (dist < JumpPoint.INTERACTION_RANGE + shipInteraction) {
                        broken = true;
                        break;
                    }
                }                
            }
                
            if (!broken) {
                return pos;
            }
        }
        return null;
    }
    
    /**
     * @param numJumpsSD positive
     */
    public void setNumJumpsSD(Double numJumpsSD) {
        this.numJumpsSD = numJumpsSD;
    }
    
    /**
     * @param numJumpsMean >= 1
     */
    public void setNumJumpsMean(Double numJumpsMean) {
        this.numJumpsMean = numJumpsMean;
    }

    public List<JumpPoint> getJumpPoints() {
        return jumpPoints;
    }

    public final void setConnectBlackHoles(Boolean connect) {
        this.connectBlackHoles = true;
    }
}
