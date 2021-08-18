import math
from search import *

class LocationGraph(ExplicitGraph): #this is bidirectional and cost is no longer one its the euclidean distanc e
    """This is a concrete subclass of Graph where vertices and edges
     are explicitly enumerated. Objects of this type are useful for
     testing graph algorithms."""

    def __init__(self, nodes, edges, starting_nodes, goal_nodes, locations): # change
        """Initialises an explicit graph.
        Keyword arguments:
        nodes -- a set of nodes
        edge_list -- a sequence of tuples in the form (tail, head) or
                     (tail, head, cost)
        starting_nodes -- the list of starting nodes. We use a list
                          to remind you that the order can influence
                          the search behaviour.
        goal_node -- the set of goal nodes. It's better if you use a set
                     here to remind yourself that the order does not matter
                     here. This is used only by the is_goal method.
        # """
        # print("nodes", nodes)
        # #, edges, starting_nodes, goal_nodes, locations)
        # print("Edges", edges)
        # print("starting nodes", starting_nodes)
        # print("goal nodes", goal_nodes)
        # print("locations", locations)
        # print("locations A ", locations['A'])


        self.edge_list = edges
        self.locations = locations

    def outgoing_arcs(self, node):
        """Returns a sequence of Arc objects that go out from the given
        node. The action string is automatically generated.

        """
        #check if edges contain the node
        #check for bi directional #bi directional means there if there is a link from A-> B

        arcs = set()
        for edge in self.edge_list:
            if node in edge:
                if len(edge) == 2:  # if no cost is specified
                    tail, head = edge
                    x = self.locations[head]
                    y = self.locations[tail]
                    cost = math.sqrt(sum([(a - b) ** 2 for a, b in zip(x, y)])) # assume unit cost #edclidean distance #
                    # print("The cost is", cost)
                else:
                    tail, head, cost = edge
                if tail == node: # have to check that the node is not equal to
                    arcs.add(Arc(tail, head, str(tail) + '->' + str(head), cost))
                elif head == node:
                    arcs.add(Arc(head, tail, str(head) + '->' + str(tail), cost))
        arcs = sorted(arcs, key=lambda x: x.head) #need to sort for answer
        return arcs

graph = LocationGraph(nodes=set('ABC'),
                      locations={'A': (0, 0),
                                 'B': (3, 0),
                                 'C': (3, 4)},
                      edges={('A', 'B'), ('B', 'C'),
                             ('C', 'A')},
                      starting_nodes=['A'],
                      goal_nodes={'C'})

for arc in graph.outgoing_arcs('A'):
    print(arc)

for arc in graph.outgoing_arcs('B'):
    print(arc)

for arc in graph.outgoing_arcs('C'):
    print(arc)