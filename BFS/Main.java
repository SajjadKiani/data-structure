import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scn.nextInt();
            }
        }

        
        // int[][] graph = {
        //     {0, 0, 0, 1, 0, 0, 1, 1}, 
        //     {0, 0, 1, 0, 0, 1, 0, 1}, 
        //     {0, 1, 0, 0, 1, 0, 0, 0}, 
        //     {1, 0, 0, 0, 0, 0, 0, 1}, 
        //     {0, 0, 1, 0, 0, 0, 1, 0}, 
        //     {0, 1, 0, 0, 0, 0, 1, 0}, 
        //     {1, 0, 0, 0, 1, 1, 0, 0}, 
        //     {1, 1, 0, 1, 0, 0, 0, 0}, 
        // };

        Graph g = new Graph(n);

        // generate graph (convert 2D array to adjantcy matrix) 
        g.addGraph(graph);

        ArrayList<Integer>[] bfs = g.bfs();

        // find dictance from root and end of graph
        for (int i=0; i < bfs.length; i++) {
            if (bfs[i].contains(n-1)) {
                System.out.print(i);
                System.exit(1);
            }
        }

        // print breaths
        // for (ArrayList<Integer> arrayList : bfs) {
        //     System.out.println(arrayList);
        // }
     }
}

class Graph {
    private boolean[][] adjMatrix;
    private int vertices;
    private int edges;

    public Graph (int vertices) {
        this.vertices = vertices;
        this.edges = 0;

        adjMatrix = new boolean[vertices][vertices];
    }

    public void addGraph (int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] == 1) {
                    addEdge(i, j);
                }
            }
        }
    }

    private boolean isEdge(int v, int w) {
        return adjMatrix[v][w];
    }

    public void removeEdge(int v , int w) {
        if (isEdge(v, w)) {
            adjMatrix[v][w] = false;
            adjMatrix[w][v] = false;
            edges--;
        }
    }

    public void addEdge(int v , int w) {
        if (!isEdge(v, w)) {
            adjMatrix[v][w] = true;
            adjMatrix[w][v] = true;
            edges++;
        }
    }

    private int nextAdj (int v , int w) {
        
        for (int i = w+1; i < vertices; i++) {
            if (isEdge(v, i)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Integer> getAdjs (int v) {
        ArrayList<Integer> ans = new ArrayList<Integer>();

        for (int i = nextAdj(v, -1); i >= 0; i=nextAdj(v, i)) {
            ans.add(i);
        }

        return ans;
    }

    
    public ArrayList<Integer>[] bfs () {
        boolean[] visited = new boolean[vertices];
        visited[0] = true;

        // Array of Array lists
        // (array bellow: distace from root of graph that contain adjantcies of each node in graph)
        ArrayList<Integer>[] distance = new ArrayList[vertices+1];

        // distace root from root is 0
        distance[0] = new ArrayList<>();
        distance[0].add(0);

        int i=0;

        while (!distance[i].isEmpty()) {
            distance[i+1] = new ArrayList<>();

            for (int node : distance[i]) {

                ArrayList<Integer> adjs = getAdjs(node);
                
                // get adjancies of this node
                for (int adj : adjs) {

                    if (!visited[adj]) {
                        distance[i+1].add(adj);
                        visited[adj] = true;
                    }
                }
            }

            i++;

        }

        return distance;
    }
}
