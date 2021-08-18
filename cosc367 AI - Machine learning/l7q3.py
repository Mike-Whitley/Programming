def greedy_descent(initial_state, neighbours, cost):
    states_visited = []
    #initial_state search starts here
    #neighbors takes a state and returns a list of neighbours
    #cost = number of conflicts
    #min function
    #cant go below 0
    current_cost = float('inf')
    states_visited.append(initial_state) #append astarting state
    truthcheck = True
    past_cost = float('inf')

    while current_cost != 0 and truthcheck == True:
        current_neighbor = neighbours(initial_state)

        cost1, cost2 = cost(current_neighbor[0]), cost(current_neighbor[1])

        if cost1 < cost2:
            initial_state = current_neighbor[0]
            current_cost = cost1
        else:
            initial_state = current_neighbor[1]
            current_cost = cost2

        states_visited.append(initial_state)
        print(current_cost, initial_state)
        if round(current_cost, 4) == round(past_cost, 4):
            truthcheck = False
        else:
            past_cost = current_cost



        #
        # print(cost(0.25), cost([1]))

    #stop while loop when cost is 0 if it doesnt change stuck and finish there


    #while loop

    return states_visited #returns a list of all the states it has gone through






def cost(x):
    return x**2

def neighbours(x):
    return [x - 1, x + 1]

for state in greedy_descent(-6.75, neighbours, cost):
    print(state)