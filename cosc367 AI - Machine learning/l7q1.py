import copy

def swapPositions(list, pos1, pos2):
    list[pos1], list[pos2] = list[pos2], list[pos1]
    return list


def n_queens_neighbours(state):
    answer = []
    lst_state = list(state)
    for i in range(len(state)):
        for j in range(i, len(state)-1):
            new_list = copy.copy(lst_state)
            result = swapPositions(new_list, i, j+1)
            answer.append(tuple(result))
    return sorted(answer)


#takes a list


print(n_queens_neighbours((1, 2, 3, 4, 5, 6, 7, 8)))