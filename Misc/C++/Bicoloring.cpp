          
#include<iostream>
#include<queue>
#include<string.h>

#define MAX_S 200

using namespace std;

int n;
int graph[MAX_S][MAX_S];
int colors[MAX_S];

bool bicolorable() {
  memset(colors, 0, sizeof(colors));    
  colors[0] = 1;
  queue<int> q;
  q.push(0);
  while (!q.empty()) {
    int at = q.front();
    q.pop();    
    for (int i = 0; i < n; i++) {
      if (graph[at][i]) {       
        if (colors[i] && colors[i] == colors[at]) return false;
        if (!colors[i]) {
          colors[i] = -colors[at];        
          q.push(i);
        }
      }
    }
  }
  return true;
};

int main() {
  while (cin >> n && n) {
    memset(graph, 0, sizeof(graph));
    int edges;
    cin >> edges;
    while (edges--) {
      int a, b;
      cin >> a >> b;
      graph[a][b] = 1;
      graph[b][a] = 1;
    }    
    if (bicolorable()) 
      cout << "BICOLORABLE." << endl;
    else
      cout << "NOT BICOLORABLE." << endl;
  }
  return 0;
}
