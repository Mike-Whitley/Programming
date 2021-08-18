from search import * #graph is in search
import math
from heapq import *


class RoutingGraph(Graph):
    def __init__(self, map_str):
        self.map_str = map_str.split("|")
        self.goal_nodes = []
        self.map = [list(line.strip()) for line in map_str.strip().split('\n')]
        self.start_nodes = self.starting_nodes()
        self.obstacles = ['+','-',"|", "X"]
        self.searched = "."
        self.part_of_solution = "*"
        #self.movable_agent, self.goal_nodes, self.drawn_map = self.text_to_graph(map_str)

    def estimated_cost_to_goal(self, node):

        shortest = math.inf
        for goal in self.goal_nodes:
            goal_row, goal_col = goal
            move_row, move_column, _ = node
            element = abs(goal_row - move_row) + abs(goal_col - move_column)
            if element < shortest:
                shortest = element
        return shortest * 5



    def is_goal(self, node):
        """Returns true if the given node is a goal state, false otherwise."""
        row = node[0]
        col = node[1]
        return self.map[row][col] == "G"


    def starting_nodes(self):
        """Returns a sequence of starting nodes. Often there is only one
        starting node but even then the function returns a sequence
        with one element. It can be implemented as an iterator if
        needed.
        """
        starting_nodes = []
        for row, line in enumerate(self.map):
            for column, element in enumerate(line):
                if element == "G":
                    self.goal_nodes.append((row, column))
                if element.isnumeric():
                    starting_nodes.append((row, column, int(element)))
                elif element == 'S':
                    starting_nodes.append((row, column, math.inf))
        return starting_nodes


    def outgoing_arcs(self, tail_node):
        """Given a node it returns a sequence of arcs (Arc objects)
        which correspond to the actions that can be taken in that
        state (node)."""

        check_list = [('N', -1, 0),('E', 0, 1),('S', 1, 0),('W', 0, -1)]
        arcs = []
        for row, col, fuel in [tail_node]:
            for direction, move_row, move_col in check_list:
                new_row = row + move_row
                new_col = col + move_col

                if fuel > 0:
                    if self.map[new_row][new_col] not in self.obstacles:
                        if self.map[row][col] == ' ':
                            self.map[row][col] = '.'

                        arcs.append(Arc(tail_node, (new_row, new_col, fuel-1),direction, 5))

            if self.map[row][col] == "F" and fuel < 9:
                arcs.append(Arc(tail_node, (row, col, int(9)), "Fuel up", 15))

        return arcs

class AStarFrontier(Frontier):
    def __init__(self, map_graph):
        self.map_graph = map_graph
        #graph has function of estimated cost ot goal  estimated_cost_to_goal
        """The constructor takes no argument. It initialises the
               container to an empty stack."""
        self.container = []
        self.prior_nodes = set()
        self.count = 0
        #pruning

    def add(self, path):

        #when you add a node you add it to a list if its already found in this list you dont add it
        #when alreadybeen visited dont add it and when you try return no return just do nothing 1 if statemnet
        if path[-1].head not in self.prior_nodes:
            total_cost = self.map_graph.estimated_cost_to_goal(path[-1].head) #this is the heuristic value #node xy and fuel
            for arc in path:
                total_cost += arc.cost #changed this to heuristic value
            heappush(self.container, (total_cost, self.count, path))
            self.count += 1

    def __next__(self):
        """Selects, removes, and returns a path on the frontier if there is
        any.Recall that a path is a sequence (tuple) of Arc
        objects. Override this method to achieve a desired search
        strategy. If there nothing to return this should raise a
        StopIteration exception.
        """

        #add it to a list once you pop it off so its not added again
        while len(self.container) > 0:
            element = heappop(self.container)[2]
            if element[-1].head not in self.prior_nodes:
                self.prior_nodes.add(element[-1].head)
                return element
        raise StopIteration

    def __iter__(self):
        """The object returns itself because it is implementing a __next__
        method and does not need any additional state for iteration."""
        return self



def print_map(map_graph, frontier, solution):
    final_map = ""

    #go through solution and add heads to graph
    if solution != None:
        for arc in solution:
            row, col, _ = arc.head
            if map_graph.map[row][col] != "S" and map_graph.map[row][col] != "G":
                map_graph.map[row][col] = "*"

    for i in map_graph.map:
        print("".join(i))




map_str = """\
+----------------+
|                |
|                |
|                |
|                |
|                |
|                |
|        S       |
|                |
|                |
|     G          |
|                |
|                |
|                |
+----------------+
"""

map_graph = RoutingGraph(map_str)
frontier = AStarFrontier(map_graph)
solution = next(generic_search(map_graph, frontier), None)
print_map(map_graph, frontier, solution)