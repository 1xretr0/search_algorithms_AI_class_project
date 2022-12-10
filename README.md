# search_algorithms_AI_class_project
This repo contains the files of an example projecct to show some search Algorithms related with Artificial Intelligence UPAEP course.

## Concept
The program displays a simple GUI showing a map with obstacles in order to visualize how the selected search algorithm works and finds a solution. In this case, the solution is the path to exit the map, avoiding the obstacle pattern.

## Classes
- AppGUI: manages the interface, includes the graphic map of the Map class. Main flow script of the app.
- Algorithm: computes the chosen search algorithm.
- Map: paints the obstacles and graphic map where the algorithm will work. The search method path is also painted here. 
- Obstacles: defines and generates the obstacle patterns.
- PathPoints: stores all attributes and methods used to manage point coordinates values.

## Available Search Algorithms
- Breadth Search Algorithm
- Depth Search Algorithm
- Hill Climbing Algorithm
- Best First Algorithm
- A* Algorithm

## Usage/Examples

```
javac AppGUI.java
java AppGUI
```
![image](https://user-images.githubusercontent.com/65722993/206825065-2d73d246-fb25-4e06-b16a-3a9d84bd632d.png)
