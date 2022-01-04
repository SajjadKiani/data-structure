

class Graph:
    adjMatrix = []
    vertices = 0
    edges = 0

    def __init__(self , vertices):
        self.vertices = vertices
        self.edges = 0

        self.adjMatrix = [[False for _ in range(vertices)] for _ in range(vertices)]

    def addGraph (self,graph):
        
        for i in range(len(graph)):
            for j in range(len(graph)):
                if int(graph[i][j]) == 1:
                    self.addEdge(i,j);

    def isEdge (self,v,w):
        return self.adjMatrix[v][w]

    def addEdge (self,v , w):
        if self.isEdge(v,w) == False:
            self.adjMatrix[v][w] = True
            self.adjMatrix[w][v] = True
            self.edges += 1

    def nextAdjancy (self,v,w):
        
        for i in range(w+1,self.vertices):
            if (self.isEdge(v,i)):
                return i
        
        return -1

    def getAdjancies(self,v):

        ans = []
        i = self.nextAdjancy(v,-1)
        while i >= 0:
            ans.append(i)
            i = self.nextAdjancy(v,i)

        return ans

    def bfs (self):
        visited = [False for _ in range(self.vertices)]
        visited[0] = True

        distance = [[] for _ in range(self.vertices+1)]
        distance[0].append(0)

        i =0
        while len(distance[i]) != 0:

            for j in distance[i]:

                adjs = self.getAdjancies(j)
                
                for adj in adjs:

                    if (visited[adj] != True):
                        distance[i+1].append(adj)
                        visited[adj] = True
            
            i += 1
        return distance
    

if __name__ == "__main__":

    # graph = [
    #         [0, 0, 0, 1, 0, 0, 1, 1], 
    #         [0, 0, 1, 0, 0, 1, 0, 1], 
    #         [0, 1, 0, 0, 1, 0, 0, 0], 
    #         [1, 0, 0, 0, 0, 0, 0, 1], 
    #         [0, 0, 1, 0, 0, 0, 1, 0], 
    #         [0, 1, 0, 0, 0, 0, 1, 0], 
    #         [1, 0, 0, 0, 1, 1, 0, 0], 
    #         [1, 1, 0, 1, 0, 0, 0, 0], 
    #     ]

    n = int (input())

    graph = [str(input()).split(" ") for _ in range(n)]

    g = Graph(n)

    g.addGraph(graph)

    bfs = g.bfs()

    for i in bfs:
        if n-1 in i:
            print (bfs.index(i))
