from search import *


class DFSFrontier(Frontier):
    """Implements a frontier container appropriate for depth-first
    search."""

    def __init__(self):
        """The constructor takes no argument. It initialises the
        container to an empty stack."""
        self.container = []

    def add(self, path):
        self.container.append(path)
        # raise NotImplementedError  # FIX THIS #takes path,  add to frontier append to container

    def __iter__(self):
        """The object returns itself because it is implementing a __next__
        method and does not need any additional state for iteration."""
        return self

    def __next__(self): #this is called multiple times so we return it not yeild it
        if len(self.container) > 0:
             return self.container.pop() # FIX THIS return something instead #if anything in the list return the next item to visit pop next item to list bfs opposite
        #if anything is in the self.container list pop the item adn return it
        else:
            raise StopIteration  # don't change this one


def main():
    # Example 1
    graph = ExplicitGraph(nodes=set('SAG'),
                          edge_list=[('S', 'A'), ('S', 'G'), ('A', 'G')],
                          starting_nodes=['S'],
                          goal_nodes={'G'})
    solutions = generic_search(graph, DFSFrontier())
    solution = next(solutions, None)
    print_actions(solution)
    # Example 2
    graph = ExplicitGraph(nodes=set('SAG'),
                          edge_list=[('S', 'G'), ('S', 'A'), ('A', 'G')],
                          starting_nodes=['S'],
                          goal_nodes={'G'})
    solutions = generic_search(graph, DFSFrontier())
    solution = next(solutions, None)
    print_actions(solution)

    available_flights = ExplicitGraph(
        nodes=['Christchurch', 'Auckland',
               'Wellington', 'Gold Coast'],
        edge_list=[('Christchurch', 'Gold Coast'),
                   ('Christchurch', 'Auckland'),
                   ('Christchurch', 'Wellington'),
                   ('Wellington', 'Gold Coast'),
                   ('Wellington', 'Auckland'),
                   ('Auckland', 'Gold Coast')],
        starting_nodes=['Christchurch'],
        goal_nodes={'Gold Coast'})

    my_itinerary = next(generic_search(available_flights, DFSFrontier()), None)
    print_actions(my_itinerary)


if __name__ == "__main__":
    main()