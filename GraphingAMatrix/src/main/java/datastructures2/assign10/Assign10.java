/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package datastructures2.assign10;
import java.util.*;
import java.io.*;
/**
 *
 * @author mlegi
 */
public class Assign10 {
    public static GraphAMatrix readIn(String n) {
    String first = "";
    String second = "";
    GraphAMatrix graph = null;
    try {
        Scanner scan = new Scanner(new File(n));
        Scanner scan1 = new Scanner(new File(n));
        scan.useDelimiter("[(, )]+"); // will be used for the vertices
        
        int v = scan1.nextInt();
        int e = scan1.nextInt();
        
        graph = new GraphAMatrix(v, e);
        
        scan.nextLine();
        scan.nextLine();
        while (scan.hasNextLine()) {
            first = scan.next();
            second = scan.next();
            scan.nextLine();
            graph.addEdge(Integer.valueOf(first), Integer.valueOf(second));
            
        }
    } catch (FileNotFoundException e) {           
        System.out.println("File not found");
    }
        
    return graph;
}
    public static void main(String[] args) {
        //System.out.println("Hello World!");
        GraphAMatrix first = null;
        GraphAMatrix second = null;
        ArrayDeque<Integer> traversalOrder = new ArrayDeque<>();
        Scanner scan = new Scanner(System.in);
        String reader = "";
        System.out.println("Enter in a text document to read in: ");
        reader = scan.nextLine();
        first = readIn(reader);
        System.out.println(first.toString());
        traversalOrder = first.BFS();
        System.out.println("\nOrder visited " + traversalOrder.toString() + "\n");
        traversalOrder = first.DFS();
        System.out.println("\nOrder visited " + traversalOrder.toString());
        System.out.println("\nEnter another text document to read in: ");
        reader = scan.nextLine();
        second = readIn(reader);
        System.out.println(second.toString());
        traversalOrder = second.BFS();
        System.out.println("\nOrder visited " + traversalOrder.toString() + "\n");
        traversalOrder = second.DFS();
        System.out.println("\nOrder visited " + traversalOrder.toString());
    }
}
