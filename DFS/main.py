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

    def dfs_func (self,v , visited , result):
        visited[v] = True
        
        result.append(v)

        adjs = self.getAdjancies(v)

        for adj in adjs:

            if visited[adj] == False:
                self.dfs_func(adj,visited,result)

        return result

    def dfs (self , v):
        
        visited = [False for _ in range(self.vertices)]
        result = []
        return self.dfs_func(v,visited,result)


if __name__ == "__main__":

    # graph = [
    #         [0, 1, 1, 0, 0, 0, 0, 0], 
    #         [1, 0, 0, 1, 1, 0, 0, 0], 
    #         [1, 0, 0, 0, 1, 0, 0, 0], 
    #         [0, 1, 0, 0, 0, 0, 0, 0], 
    #         [0, 1, 1, 0, 0, 0, 0, 0], 
    #         [0, 0, 0, 0, 0, 0, 1, 0], 
    #         [0, 0, 0, 0, 0, 1, 0, 0], 
    #         [0, 0, 0, 0, 0, 0, 0, 0], 
    #     ]

    s = str(input()).split(' ')
    n = int(s[0])
    m = int(s[1])-1

    graph = [str(input()).split(" ") for _ in range(n)]

    g = Graph(n)

    g.addGraph(graph)

    print (len(g.dfs(m)))