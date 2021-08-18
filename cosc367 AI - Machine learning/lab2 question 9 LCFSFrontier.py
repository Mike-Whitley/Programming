import math
from search import *
from heapq import *

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

        self._starting_nodes = starting_nodes
        self.edge_list = edges
        self.locations = locations
        self.nodes = nodes
        self._starting_nodes = starting_nodes
        self.goal_nodes = goal_nodes
        #these all need to be defined or it screwed up frontier functions



    def starting_nodes(self):
        """Returns a sequence of starting nodes."""
        return self._starting_nodes

    def is_goal(self, node):
        """Returns true if the given node is a goal node."""
        return node in self.goal_nodes

    def outgoing_arcs(self, node):
        """Returns a sequence of Arc objects that go out from the given
        node. The action string is automatically generated.

        """

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




class LCFSFrontier(Frontier):
    """This is an LCFS class for frontier classes. It outlines the
    methods that must be implemented by a concrete subclass. Concrete
    subclasses determine the search strategy.

    """

    def __init__(self):
        """The constructor takes no argument. It initialises the
        container to an empty stack."""
        self.container = [] #the list works for a heap
        #self.container = heapify(self.container)

    def add(self, path):
        """Adds a new path to the frontier. A path is a sequence (tuple) of
        Arc objects. You should override this method.
        """
        total_cost_of_path = 0
        #path looks like (Arc(tail=None, head='a', action='no action', cost=0), Arc(tail='a', head='b', action='a->b', cost=5.0), Arc(tail='b', head='a', action='b->a', cost=5.0))
        #each arc i.e  tail=None, head='a', action='no action', cost=0 we can work out the total cost by adding all the arcs together then push it on to the heap
        for arc in path:
            total_cost_of_path = total_cost_of_path + arc.cost
        #self.container is the heap, append a tuple of cost of path and the current path
        heappush(self.container, (total_cost_of_path, path))

    def __iter__(self):
        """We don't need a separate iterator object. Just return self. You
        don't need to change this method."""
        return self

    def __next__(self):
        """Selects, removes, and returns a path on the frontier if there is
        any.Recall that a path is a sequence (tuple) of Arc
        objects. Override this method to achieve a desired search
        strategy. If there nothing to return this should raise a
        StopIteration exception.
        """
        if len(self.container) > 0:
            print("self.container item", self.container)

            return heappop(self.container)[1] #returns the path that has the lowest total cost
            #as this is a heap the order is adjusted so heap structure is maintaied heappop just pops the lowest value in the heap
        else:
            raise StopIteration






pythagorean_graph = LocationGraph(
    nodes=set("abc"),
    locations={'a': (5, 6),
               'b': (10,6),
               'c': (10,18)},
    edges={tuple(s) for s in {'ab', 'ac', 'bc'}},
    starting_nodes=['a'],
    goal_nodes={'c'})

solution = next(generic_search(pythagorean_graph, LCFSFrontier()))
print_actions(solution)