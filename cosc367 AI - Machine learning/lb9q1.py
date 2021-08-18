
#creating a newwork
from numbers import Number

network = {
    'X1': {
        'Parents': ['Y'],
        'CPT': {
            (True,): ((1+2)/(4+4)), #when y is true
            (False,): ((3+2)/(3+4)) #when y is false


        }
    },

    'X2': {
        'Parents': ['Y'],
        'CPT': {
            (True,): ((1 + 2) / (4 + 4)),  # when y is true
            # (Probability of the True Y's that X2 is true) /(Probabillity that Y is True)
            (False,): ((2 + 2) / (3 + 4))  # when y is false
            # (Probability of the false Y's that X2 is true) /(Probabillity that Y is false)

        }
    },

    'X3': {
        'Parents': ['Y'],
        'CPT': {
            (True,): ((0 + 2) / (4 + 4)),  # when y is true
            (False,): ((0 + 2) / (3 + 4))  # when y is false
        }
    },

    'Y': {
        'Parents': [],
        'CPT': {
            (): ((4+2)/(7+4)), #divison by total is normalizing with alpha
            #2 for true and 2 for false so + 4 in total
            #true is empty tuple false is 1-
            #have to add +2 to each side of the division for Laplace smoothing

        }
    },

}


# Checking the overall type-correctness of the network
# without checking anything question-specific

assert type(network) is dict
for node_name, node_info in network.items():
    assert type(node_name) is str
    assert type(node_info) is dict
    assert set(node_info.keys()) == {'Parents', 'CPT'}
    assert type(node_info['Parents']) is list
    assert all(type(s) is str for s in node_info['Parents'])
    for assignment, prob in node_info['CPT'].items():
        assert type(assignment) is tuple
        assert isinstance(prob, Number)

print("OK")


print(sorted(network.keys()))


for assignment, prob in sorted(network['Y']['CPT'].items()):
    print(assignment, "{:0.2f}".format(prob))