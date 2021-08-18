import random
random.seed(123456789)

def depth(expression):
    if isinstance(expression, str):
        return 0
    if isinstance(expression, int):
        return 0
    if isinstance(expression, list):
        return 1 + max(depth(expression[1]),depth(expression[2]))

def is_valid_expression(object, function_symbols, leaf_symbols):

    if isinstance(object, str) and object in leaf_symbols:
        return True
    if isinstance(object, int):
        return True
    if isinstance(object, list):
        if len(object) == 3 and object[0] in function_symbols:
            return is_valid_expression(object[1], function_symbols, leaf_symbols) and is_valid_expression(object[2], function_symbols, leaf_symbols)
    return False


def evaluate(expression, bindings):

    if isinstance(expression, int):
        return expression
    if isinstance(expression, str):
        return bindings[expression]
    if isinstance(expression, list):
        return bindings[expression[0]](evaluate(expression[1], bindings), evaluate(expression[2], bindings))



def random_expression(function_symbols, leaves, max_depth):
    if random.randint(0, 1) or max_depth == 0:
        #pick a head of tail of 1 or 0
        return random.choice(leaves) #random picked out from the list to be returned

    else:
        #return a in first slot the function symbol then 2 lists to go recursivly deeper
        return [random.choice(function_symbols), random_expression(function_symbols, leaves, max_depth - 1),
                random_expression(function_symbols, leaves, max_depth - 1)]




def generate_rest(num_seq, expression, length):
    #define the bindings eeds [+,- * and x and y] x is 2nd to last y is last num in list
    #continuation of the initial list according to the given expression
    bindings = {'+': lambda x, y: x + y, '-': lambda x, y: x - y, '*': lambda x, y: x * y, 'x': num_seq[-2],
                'y': num_seq[-1]}

    final_list = []
    start = len(num_seq)
        #we want to start at the end of the list then go on for a certian length this allows us to keep our index
    for i in range(start, start + length):
        bindings['i'] = i #this is the index position we add it as a new value to our bindings dict
        final_list.append(evaluate(expression, bindings)) #append the evaluation of the expression
        bindings['x'] = bindings['y'] #update our x and y values to our old value as now 2nd to last in list
        bindings['y'] = final_list[-1]

    return final_list



def predict_rest(sequence)
