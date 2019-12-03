
import HW6.FrequencyTable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test2
{
    public static void dfs(int i, boolean[][] graph, boolean[] visited) {
    if(!visited[i]){        
        visited[i] = true; // Mark node as "visited"
        System.out.print(i+1 + " ");

        for (int j = 0; j < graph[i].length; j++) {
            if (graph[i][j] && !visited[j]) {   
                dfs(j, graph, visited); // Visit node
            }
        }
    }   
}

public static void main(String[] args) {
    boolean graph[][]=new boolean[4][4];
    
    graph[0][1]=true;
    graph[0][2]=true;
    graph[1][2]=true;
    graph[2][3]=true;
    
    boolean [] visited = new boolean[4];
    int count = 0;
    for(int i = 0; i < graph.length; i++) {
        if(!visited[i]) {
            System.out.println(i);
            dfs(i,graph,visited);
        }
    }
    System.out.println("Total number of Components: " + count);
}
}
