package spacetrader.game_model.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import spacetrader.shared.Util;

/**
 * Nodes in a Graph can be connected with DirectedEdges or UndirectedEdges, or they
 * can be disconnected from the rest of the graph. Two DirectedEdges between two nodes
 * is not the same as a single UndirectedEdge between them. Two nodes should not
 * be connected with both a DirectedEdge and an UndirectedEdge.
 *
 * @author Michael Lane <mlane@gatech.edu>
 * @param <N>
 */
public class Graph<N extends Node> implements Serializable {

    private Map<NodePair, Edge<N>> edges;
    private Map<N, List<N>> neighborsByNode;
    private Map<N, List<Edge<N>>> edgesByNode;
//    private List<Node> expanded;

    
    public Graph(
    List<? extends DirectedEdge<N>> directedEdges, 
    List<? extends UndirectedEdge<N>> undirectedEdges) {

//        prettyPrint("undirectedEdges", undirectedEdges, 1);
        
        edgesByNode = new HashMap();
        neighborsByNode = new HashMap();
        edges = new HashMap();
        if (directedEdges != null) {
            for (DirectedEdge<N> edge : directedEdges) {
                if (edge != null) {
                    asymmetricalAdd(edge.getFromNode(), edge.getToNode(), edge);   
                }
            }
        }
        if (undirectedEdges != null) {
            for (UndirectedEdge<N> edge : undirectedEdges) {
                if (edge != null) {
                    asymmetricalAdd(edge.getNode1(), edge.getNode2(), edge);
                    asymmetricalAdd(edge.getNode2(), edge.getNode1(), edge);                    
                }
            }
        }
    }
    
//    public Graph(List<? extends DirectedEdge<N>> directedEdges, List<? extends UndirectedEdge> undirectedEdges, 
//    List<Node> isolatedNodes) {
//        
//        this(directedEdges, undirectedEdges);
//        if (isolatedNodes != null) {
//            List<Edge> empty = new ArrayList();
//            for (Node node : isolatedNodes) {
//                if (node != null) {
//                    edgesByNode.put(node, empty);              
//                }
//            }
//        }
//
//    }

    private void asymmetricalAdd(N fromNode, N toNode, Edge<N> edge) {
        
//        System.out.println("Attempting to add " + nodePair.toString() + " in asymmetricalAdd");
        NodePair nodePair = new NodePair(fromNode, toNode);
        
        // this line is why you shouldn't have two nodes that are connected by both
        // a DirectedEdge and an UndirectedEdge.
        if (!edges.containsKey(nodePair)) {
            edges.put(nodePair, edge);
        } else {
//            System.out.println("edge not added because already added");
        }
        
        List<N> neighbors = neighborsByNode.get(fromNode);
        List<Edge<N>> theseEdges = edgesByNode.get(fromNode);
        if (neighbors != null && !neighbors.contains(toNode)) {
//            System.out.println(toNode.toString() + " added as neighbor of " + fromNode.toString());
            neighbors.add(toNode);
            theseEdges.add(edge);

        } else if (neighbors == null) {
//            System.out.println(toNode.toString() + " added as neighbor of " + fromNode.toString());
            List<N> newNeighbors = new ArrayList();
            newNeighbors.add(toNode);
            neighborsByNode.put(fromNode, newNeighbors);
            List<Edge<N>> newEdges = new ArrayList();
            newEdges.add(edge);
            edgesByNode.put(fromNode, newEdges);
        }
    }

    public List<N> getNeighbors(N node) {
        List<N> neighbors = neighborsByNode.get(node);
        return (neighbors != null) ? neighbors : new ArrayList();
    }

    public List<N> getNodes() {
        return new ArrayList(neighborsByNode.keySet());
    }

    /**
     * Returns a DirectedEdge if fromNode connects to toNode but not vice-versa;
     * and returns a UndirectedEdge if both connect to each other.
     *
     * @param fromNode non-null
     * @param toNode   non-null
     * @return null if either parameter is null, or if no Edge exists from
     *         fromNode
     *         to toNode
     */
    public Edge getEdge(N fromNode, N toNode) {

        if (fromNode == null || toNode == null) {
            return null;
        }

        NodePair nodePair = new NodePair(fromNode, toNode);
        return edges.get(nodePair);
    }

    /**
     * This is a graph-search based implementation; i.e. it maintains a "visited"
     * list.
     * 
     * @param start non-null
     * @param end   non-null
     * @return A path from start to end. The first node in the list is the first
     * node to visit from start; the last node is end. null if start and end are
     * disconnected in the graph.
     */
    public List<N> bfs(N start, N end) {
        
        if (start == null || end == null) {
            return null;
        }

        // edge case
        if (start.equals(end)) {
            return new ArrayList();
        }
        List<N> visited = new LinkedList();
        visited.add(start);
//        System.out.println("Neighbors of start: ");
//        for (Node neighbor : this.getNeighbors(start)) {
//            System.out.println("\t" + ((StarSystem) neighbor).getName());
//        }
        Map<N, List<N>> pathByNode = new HashMap();
        // init pathByNode
        for (N neighbor : this.getNeighbors(start)) {
            List<N> path = new ArrayList();
            path.add(neighbor);
            pathByNode.put(neighbor, path);
        }

        List<N> frontier = new LinkedList(this.getNeighbors(start));
        while (!frontier.isEmpty()) {
//            System.out.println("Frontier:");
            // int i = 0;
//            for (Node node : frontier) {
//                System.out.println("\tIndex " + i++ + ": " + ((StarSystem) node).getName());
//            }
            N node = frontier.remove(0);
//            System.out.println("Popped StarSystem: " + ((StarSystem) node).getName());
            if (!visited.contains(node)) {
//                System.out.println("Neighbors of popped StarSystem: ");
                // goal test
                if (node.equals(end)) {
//                    System.out.println("RETURNED\n\n");
                    return pathByNode.get(node);
                }
                visited.add(node);
                List<N> neighbors = this.getNeighbors(node);
//                for (Node aNode : neighbors) {
//                    System.out.println("\t" + ((StarSystem) aNode).getName());
//                }
                // update frontier
                frontier.addAll(neighbors);

                // update neighbor paths
                List<N> path = pathByNode.get(node);
                for (N neighbor : neighbors) {
                    if (pathByNode.get(neighbor) == null) {
                        List<N> newPath = new ArrayList(path);
                        newPath.add(neighbor);
                        pathByNode.put(neighbor, newPath);
                    }
                }
            }
        }

        // iff start and end are disconnected
//        System.out.println("RETURNED\n\n");
        return null;
    }

    /**
     * This graph search algorithm should be used on graphs that are a priori known
     * to be trees. Assuming that the search space is finite, this is optimal no 
     * matter the behavior of the heuristic. However, if the heuristic is particularly
     * stupid, this may run longer than comparable graph search algorithms, like BFS.
     * 
     * If the graph is a tree, then it runs in less time than aStarGraphSearch because
     * it doesn't need to keep a list of visited nodes.
     * 
     * @param start non-null
     * @param end   non-null
     * @param heur  non-null
     * @return A path from start to end. The first node in the list is the first
     * node to visit from start; the last node is end. null if start and end are
     * disconnected in the graph.
     */
    public List<Edge<N>> aStarTreeSearch(N start, N end, Heurstic<N> heur) {

        if (start == null || end == null || heur == null) return null;
        
        if (start.equals(end)) {
            return new LinkedList();
        }
        
        // linked list because we're only removing the first element and adding
        // via insertion sort. with an arraylist implementation, the object
        // would have to be copied over and over again
        List<Action> frontier = new LinkedList();
        Map<Action, List<Edge<N>>> pathByAction = new HashMap();
        Map<N, Double> hByNode = new HashMap(); // not actually used
        Map<Action, Double> costByAction = new HashMap();

        initAStarData(start, end, heur, frontier, hByNode, pathByAction, costByAction);

        // Something to keep in mind: in this implementation, all nodes are only peeked at a 
        // single time. This means that pathByNode and costByNode will be 
        // put to, but their values will never be updated.

        while (!frontier.isEmpty()) {
            Action action = frontier.remove(0);
            N node = action.state;
            // goal test
            if (node.equals(end)) {
                return pathByAction.get(action);
            }
            List<Edge<N>> path = pathByAction.get(action);
            // add neighbors to frontier
            List<N> neighbors = this.getNeighbors(node);
            double cost = costByAction.get(action);
            for (N neighbor : neighbors) {
                Action next = new Action(neighbor, this.getEdge(node, neighbor));
            	// the only thing we need to check for optimality is that
            	// neighbor hasn't already been added to the frontier
            	if (pathByAction.get(next) == null) {
	                // create path
	                List<Edge<N>> newPath = new LinkedList(path);
	                newPath.add(next.edge);
	                double w = this.getEdge(node, neighbor).getWeight();
	                double h = heur.calculate(neighbor, end);
	                costByAction.put(next, cost + w);
	                pathByAction.put(next, newPath);
	                // insert neighbor into frontier
	                insertIntoFrontier(next, frontier, costByAction, hByNode, heur, end);        		
            	}
            }
        }
        return null;
    }

    /**
     * This graph search algorithm should be used on graphs that are a priori not known
     * to be trees. Assuming that the search space is finite, this is optimal no 
     * matter the behavior of the heuristic. However, if the heuristic is particularly
     * stupid, this may run longer than comparable graph search algorithms, like BFS.
     * 
     * @param start non-null
     * @param end non-null
     * @param heur non-null
     * @return A path from start to end. The first node in the list is the first
     * node to visit from start; the last node is end. null if start and end are
     * disconnected in the graph.
     */
    public List<Edge<N>> aStarGraphSearch(N start, N end, Heurstic heur) {
        
//        for (Node node : this.getNodes()) {
//            prettyPrint("Neighbors of " + node.toString(), this.getNeighbors(node), 1);
//        }
        
        if (start == null || end == null || heur == null) return null;
        
        if (start.equals(end)) {
            return new LinkedList();
        }
        
        List<Action> frontier = new LinkedList();
//        System.out.println("Expanded " + ((StarSystem) start).getName());
        // the path with least total cost from start to node
        Map<Action, List<Edge<N>>> pathByAction = new HashMap();
        Map<N, Double> hByNode = new HashMap();
        // the cost along the path will least total cost from start to node 
        Map<Action, Double> costByAction = new HashMap();
        List<N> visited = new LinkedList();
        visited.add(start);
        
        initAStarData(start, end, heur, frontier, hByNode, pathByAction, costByAction);
        
//        prettyPrintMapSubset("Frontier after initialization/sorting:", frontier, sumMaps(hByNode, costByNode));
        
        while(!frontier.isEmpty()) {
            
//            prettyPrint("Visited:", visited, 1);
//            prettyPrint("Frontier:", frontier, 1);
            
//            prettyPrintMapSubset("Frontier:", frontier, sumMaps(hByNode, costByNode));
            Action action = frontier.remove(0);
            if (!visited.contains(action.state)) {
                
                N node = action.state;
                
//                System.out.println(node.toString() + " => " + (heur.calculate(node, end) + costByAction.get(action)));
                // goal test
                if (node.equals(end)) {
                    return new ArrayList(pathByAction.get(action));
                }
                visited.add(node);
                double cost = costByAction.get(action);
                
//                prettyPrint("neighbors", this.getNeighbors(node), 1);

                // peek at neighbors
//                System.out.println("neighbors: " + this.getNeighbors(node));
                for (N neighbor : this.getNeighbors(node)) {
                	if (!visited.contains(neighbor)) {
                		Double newH = null;
                        boolean toRecalc = heur.toRecalc();
                		if (toRecalc || (newH = hByNode.get(neighbor)) == null) {
                            newH = heur.calculate(neighbor, end);
                            if (!toRecalc)  {
                                hByNode.put(neighbor, newH);
                            }
                        }
                        
                        Edge<N> edge = this.getEdge(node, neighbor);
                        Action next = new Action(neighbor, edge);
            			double newCost = cost + edge.getWeight();
            			double newF = newCost + newH;
		                List<Edge<N>> newPath = new ArrayList(pathByAction.get(action));
		                newPath.add(edge);
        				pathByAction.put(next, newPath);
        				costByAction.put(next, newCost);
//                        prettyPrint("hByNode", hByNode, 1);
//                        prettyPrint("costByNode", costByNode, 1);
        				insertIntoFrontier(next, frontier, costByAction, hByNode, heur, end);
                	}
                }
            }
                
        }
        return null;
        
    }	

    private void insertIntoFrontier(Action next, List<Action> frontier,
    Map<Action, Double> costByAction, Map<N, Double> hByNode, Heurstic<N> heur, N end) {
        
//        System.out.println("Adding neighbor " + neighbor.toString() + 
//            " to frontier.");
//        prettyPrintMapSubset("Frontier:", frontier, sumMaps(hByNode, costByNode));
//        prettyPrint("hByNode: ", hByNode);
//        prettyPrint("costByNode:", costByNode);
        
        ListIterator<Action> iter = frontier.listIterator();
        N neighbor = next.state;
        
        double h;
        if (heur.toRecalc()) {
            h = heur.calculate(neighbor, end);
        } else h = hByNode.get(neighbor);
        double f = costByAction.get(next) + h;
        boolean proceed = true;
//        for (int i = 0; i < frontier.size(); i++) {
//            Node n1 = frontier.get(i);
//            if (n1 == null) System.out.println("null");
//            else System.out.println(n1);
//        }
        while (iter.hasNext() && proceed) {
            Action action = iter.next();
//            System.out.println("iterated");
            if (action.state != null) {
                if (heur.toRecalc()) {
                    h = heur.calculate(action.state, end);
                } else h = hByNode.get(action.state);
//                System.out.println("attempting to access costByNode.get(" + n.toString() + ")");
//                System.out.println("null: " + costByNode.get(n) == null);
//                System.out.println("node " + n.toString() + " has f-value " +
//                    (costByNode.get(n) + h));
                if (f < costByAction.get(action) + h) {
                    iter.previous(); // move cursor back
                    iter.add(next);
                    proceed = false;
                }
            } else {
//                System.out.println("but found a null?");
            }
        }
        // if we reached the end of the list without adding
        if (proceed) {
            // cursor will be at the end
            
            iter.add(next);
        }
        
//        System.out.println("added " + neighbor.toString() + " to frontier at position "
//            + (iter.nextIndex() - 1));
    }
    
    public List<Edge<N>> getEdges() {
        return new ArrayList(edges.values());
    }
    
    public List<Edge<N>> getEdges(N fromNode) {
        List<Edge<N>> edges = edgesByNode.get(fromNode);
        return (edges != null) ? edges : new ArrayList();
    }
    
    public List<N> getConnectedNodes(N node) {
        
        List<N> visited = new LinkedList();
        List<N> frontier = new LinkedList();
        
        frontier.addAll(this.getNeighbors(node));
        
        while (!frontier.isEmpty()) {
            N n = frontier.remove(0);
            if (!visited.contains(n)) {
                visited.add(n);
                List<N> neighbors = this.getNeighbors(n);
                for (N neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        frontier.add(neighbor);
                    }
                }                
            }      
        }
        return visited;
    }
    
    public List<N> getDisconnections() {
        List<N> nodes = this.getNodes();
        Iterator<N> nodesIter = nodes.iterator();
        int numNodes = nodes.size();
        List<N> totalConnected = new LinkedList();
        List<N> disconn = new LinkedList();
        while (totalConnected.size() < numNodes) {
            N n = nodesIter.next();
            while (totalConnected.contains(n)) {
                n = nodesIter.next();
            }
            totalConnected.addAll(this.getConnectedNodes(n));
            disconn.add(n);
        }
        
        return disconn;
    }
    
//    public Edge<N> getMinimalEdge(N node) {
//        List<Edge<N>> someEdges = this.getEdges(node);
//        Edge<N> minEdge = someEdges.get(0);
//        double minWeight = minEdge.getWeight();
//        for (Edge<N> edge : someEdges) {
//            double weight = edge.getWeight();
//            if (weight < minWeight) {
//                minEdge = edge;
//                minWeight = weight;
//            }
//        }
//        return minEdge;
//    }

    public void union(Graph<N> graph) { 
        
        this.edges.putAll(graph.edges);
        Util.unionListMaps(this.neighborsByNode, graph.neighborsByNode);
        Util.unionListMaps(this.edgesByNode, graph.edgesByNode);
    }
    
    private void initAStarData(
        N start, N end, Heurstic<N> heur, List<Action> frontier,
        Map<N, Double> hByNode, 
        Map<Action, List<Edge<N>>> pathByAction, Map<Action, Double> costByAction) {

//        System.out.println("end position: " + ((Positionable) end).getPosition());
        
        for (N neighbor : this.getNeighbors(start)) {
//            System.out.println(neighbor.toString() + " position: " + 
//                ((Positionable) neighbor).getPosition().toString());
            
            Edge<N> edge = this.getEdge(start, neighbor);
            Action action = new Action(neighbor, edge);
            frontier.add(action);
            double w = edge.getWeight();
            double h = heur.calculate(neighbor, end);
            costByAction.put(action, w);            
            hByNode.put(neighbor, h);
            List<Edge<N>> path = new LinkedList();
            path.add(edge);
            pathByAction.put(action, path);
//            System.out.println(neighbor.toString() + " h = " + h);
//            System.out.println(neighbor.toString() + " w = " + w);
//            System.out.println(neighbor.toString() + " f = " + (w + h));
        }

        // sort the initialized frontier
        Collections.sort(frontier, (Action a1, Action a2) -> {
            double f1 = hByNode.get(a1.state) + costByAction.get(a1);
            double f2 = hByNode.get(a2.state) + costByAction.get(a2) ;
            if (f1 > f2) return 1;
            if (f1 < f2) return -1;
            return 0;
        });
    }
    
//    public List<Node> getExpanded() {
//        return expanded;
//    }
    
    private class NodePair implements Serializable {

        N node1;
        N node2;

        public NodePair(N node1, N node2) {
            this.node1 = node1;
            this.node2 = node2;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 89 * hash + Objects.hashCode(this.node1);
            hash = 89 * hash + Objects.hashCode(this.node2);
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            NodePair nodePair = (NodePair) o;
            return node1.equals(nodePair.node1)
                && node2.equals(nodePair.node2);
        }
        
        public String toString()  {
            return "(" + node1.toString() + ", " + node2.toString() + ")";
        }
    }
    
    private class Action {
        
        final N state;
        final Edge<N> edge;
        
        Action(N state, Edge<N> edge) {
            this.state = state;
            this.edge = edge;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            Action a = (Action) o;
            return a.state.equals(this.state) && a.edge.equals(this.edge);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + Objects.hashCode(this.state);
            hash = 97 * hash + Objects.hashCode(this.edge);
            return hash;
        }
        
        
    }
    
//    /**
//     * There was no other good place to put this method, so here it is.
//     * 
//     * @param edge non-null
//     * @param node non-null
//     * @return The node in given Edge that isn't the given Node; null if the Node
//     *         isn't along the Edge.
//     */
//    public static Node getOtherNode(Edge edge, Node node) {
//        
//        if (edge == null || node == null) return null;
//        
//        if (edge instanceof DirectedEdge) {
//            DirectedEdge dirEdge = (DirectedEdge) edge;
//            if (dirEdge.getFromNode().equals(node)) {
//                return dirEdge.getToNode();
//            } else if (dirEdge.getToNode().equals(node)) {
//                return dirEdge.getFromNode();
//            } else return null;
//            
//        } else if (edge instanceof UndirectedEdge) {
//            UndirectedEdge undirEdge = (UndirectedEdge) edge;
//            if (undirEdge.getNode1().equals(node)) {
//                return undirEdge.getNode2();
//            } else if (undirEdge.getNode2().equals(node)) {
//                return undirEdge.getNode1();
//            } else return null;
//        }
//        
//        return null;
//    }


}
