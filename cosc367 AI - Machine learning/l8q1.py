import itertools

def joint_prob(network, assignment):
    product = 1
    for Letter,Truth_statement in assignment.items(): # assignment[i] returns true or false and i returns the letter e.g. A\
        parents = tuple(assignment[parent] for parent in network[Letter]["Parents"])
        if Truth_statement:
            product *= network[Letter]["CPT"][parents]
        else:
            product *= (1 - network[Letter]["CPT"][parents])

    return product


def Merge(dict1, dict2):
    res = {**dict1, **dict2}
    return res


def query(network, query_var, evidence):
    """return a pair of real numbers where the first element is the probability of query_var being
    false given the evidence and the second element is the probability of query_var being true given the evidence."""
    #returns the posterior distribution of query_var
    true_list = [0,0]
    hidden_vars = network.keys() - evidence.keys() - {query_var}
    for values in itertools.product((True, False), repeat=len(hidden_vars)): # pulls out hidden variables dont know if true of false
        hidden_assignments = {var: val for var, val in zip(hidden_vars, values)}
        Combined_assignent = Merge(hidden_assignments, evidence) #this gives us all variables related to what we want for the assignment


        combinations = [{query_var:True},{query_var:False}]
        for i in combinations: #this loop adds query variable
            Combined_assignent.update(i)
            result = joint_prob(network, Combined_assignent)
            true_list[i[query_var]] += result

    total = sum(true_list)
    for i in range(len(true_list)): #normalizes it
        true_list[i]/=total
    return tuple(true_list)




    #
    #
    #
    # print(result)


#test A is 95% truth chance that the virus is present
#test A has a 10% false positive rate that the virus is present when its not

#test B is 90%  truth chance that the virus is present
#test B has a 5% false positive rate that the virus is present when its not

network = {
    'A': {
        'Parents': [],
        'CPT': {
            (): 0.1
        }},
    'B': {
        'Parents': ['A'],
        'CPT': {
            (False,): 0.2,
            (True,): 0.3
        }},

    'C': {
        'Parents': ['A'],
        'CPT': {
            (False,): 0.4,
            (True,): 0.4
        }},
}

print(sorted(network.keys()))
