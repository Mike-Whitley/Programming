import itertools


def n_queens_cost(state):
    collisions = 0
    for i in (list(itertools.combinations(state, 2))):
        # create all combos

        if (abs(i[0] - i[1]) == abs(state.index(i[0]) - state.index(i[1]))):
            # checks to see if there is a diagonal collision
            collisions += 1
    return collisions