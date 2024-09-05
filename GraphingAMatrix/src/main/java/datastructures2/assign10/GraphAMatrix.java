package datastructures2.assign10;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Stack;

public class GraphAMatrix {
	
	// Assume that the vertex names will be 0, 1, 2, 3, ...... (numVertices - 1)
	private int numVertices;
	private int numEdges;
	
	// Use an adjacency matrix (2 dimensional array) to store the graph edges
	private boolean[][] adjMatrix;
		
	// Constructor
	public GraphAMatrix(int v, int e) {
		numVertices = v;	
		numEdges = e;
		
		// Instantiate adjMatrix
                adjMatrix = new boolean [v][e];

	}
	
	public void setNumVertices(int v) {
		numVertices = v;
	}
	
	public void setNumEdges(int e) {
		numEdges = e;
	}
	
	public int getNumVertices() {
		return numVertices;
	}
	
	public int getNumEdges() {
		return numEdges;
	}
	
	// Add the edge from vertex v1 to vertex v2
	public void addEdge(int v1, int v2) {
            adjMatrix[v1][v2] = true;
            ++numEdges;

	}

	// Remove the edge from vertex v1 to vertex v2
	public void removeEdge(int v1, int v2) {
            adjMatrix[v1][v2] = false;
            --numEdges;
	}

	// Return a String containing the matrix
	// in row column format
        
        @Override
	public String toString() {
            String s = "";
            for(int i = 0; i < numVertices; i++){
                for(int j = 0; j < numVertices; j++){
                    if(adjMatrix[i][j] == true){
                        
                        s+= "1 ";
                    }else{
                        s+= "0 ";
                    }
                }
                s+="\n";
            }
            return s;
	}

	// Do the Breadth First Traversal starting at vertex 0
	// This must be done by following the pseudocode in the book (see handout)
	// You must also print every edge that is traveled over
	// during the traversal (edges in the tree)
	// Return the traversalOrder structure
	public ArrayDeque<Integer> BFS() {
		// Use the ArrayDeque structures as queues
		// Use the ArrayList to mark vertices as visited
                System.out.println("BFS: ");
		ArrayDeque<Integer> traversalOrder = new ArrayDeque<>();
		ArrayDeque<Integer> vertexQueue = new ArrayDeque<>();
		ArrayList<Boolean> marked = new ArrayList<>();
		traversalOrder.add(0);
                vertexQueue.add(0);
                marked.add(0, Boolean.TRUE);
                
                
                for(int i = 1; i < numVertices; i++){
                    marked.add(i, Boolean.FALSE);//queuing up false elements
                }
                while(!vertexQueue.isEmpty()){//checking if empty
                    int frontVertex = vertexQueue.pop();
                    
                    for(int i = 0; i < numVertices; i++){
                       int nextNeighbor = i; 
                       if(marked.get(i) == false && adjMatrix[frontVertex][i] == true){
                           marked.set(nextNeighbor, Boolean.TRUE);
                           traversalOrder.add(nextNeighbor);
                           
                           vertexQueue.add(i);
                           System.out.print("(" + frontVertex + ", " + nextNeighbor + ") ");
                            
                       }
                    }
                }  
                return traversalOrder;
	}

	// Do the Depth First Traversal starting at vertex 0
	// This must be done by following the pseudocode in the book (see handout)
	// You must also print every edge that is traveled over
	// during the traversal (edges in the tree)
	// Return the traversalOrder structure
	public ArrayDeque<Integer> DFS() {
            System.out.println("DFS:");
		ArrayDeque<Integer> traversalOrder = new ArrayDeque<>();
		Stack<Integer> vertexStack = new Stack<>();
		ArrayList<Boolean> marked = new ArrayList<>();
                traversalOrder.add(0);
                vertexStack.push(0);
                
                for(int i = 0; i < numVertices; i++){
                    marked.add(i, Boolean.FALSE);//queuing up false elements
                }
                marked.set(0,Boolean.TRUE);
                while(!vertexStack.isEmpty()){
                    int topVertex = vertexStack.peek();
                    boolean visited = false;  
                    int i = 0;
                    while(i< numVertices){
                        if(adjMatrix[topVertex][i] == true && marked.get(i)==false){
                            int nextNeighbor = i;
                            marked.set(nextNeighbor, Boolean.TRUE);
                            traversalOrder.add(nextNeighbor);
                            vertexStack.push(nextNeighbor);      
                            System.out.print("(" + topVertex + ", " + nextNeighbor + ") ");
                            break;
                        }
                        i++;
                        if(i == numVertices){
                            vertexStack.pop();
                        }
                    }         
                }
                return traversalOrder;
                }
}
