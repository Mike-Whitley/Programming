from search import *
from _collections import deque

class BFSFrontier(Frontier):
    """Implements a frontier container appropriate for depth-first
    search."""

    def __init__(self):
        """The constructor takes no argument. It initialises the
        container to an empty stack."""
        self.container = deque([]) #deque() with a [] inside creates an empty queue that we can popleft from

    def add(self, path):
        self.container.append(path)
        # raise NotImplementedError  # FIX THIS #takes path,  add to frontier append to container #

    def __iter__(self):
        """The object returns itself because it is implementing a __next__
        method and does not need any additional state for iteration."""
        return self

    def __next__(self): #this is called multiple times so we return it not yeild it
        if len(self.container) > 0:
             return self.container.popleft()
        #bfs collections.dequeue
        else:
            raise StopIteration  # don't change this one


def main():
    # Example 1
    flights = ExplicitGraph(nodes=['Christchurch', 'Auckland',
                                   'Wellington', 'Gold Coast'],
                            edge_list=[('Christchurch', 'Gold Coast'),
                                       ('Christchurch', 'Auckland'),
                                       ('Christchurch', 'Wellington'),
                                       ('Wellington', 'Gold Coast'),
                                       ('Wellington', 'Auckland'),
                                       ('Auckland', 'Gold Coast')],
                            starting_nodes=['Christchurch'],
                            goal_nodes={'Gold Coast'})

    my_itinerary = next(generic_search(flights, BFSFrontier()), None)
    print_actions(my_itinerary)

if __name__ == "__main__":
    main()