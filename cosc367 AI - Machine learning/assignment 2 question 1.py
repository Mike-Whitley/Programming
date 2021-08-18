def is_valid_expression(object, function_symbols, leaf_symbols):

    #check function symbol starts with f
    #check function symbols and leaf symbols are valid expressions

    #stop cases if its an int then its true and valid
    #if its a string and in list of leaf symbols
    #all three values = recursive

    #check if obect is type string return true
    if isinstance(object, str) and object in leaf_symbols:
        return True

    if isinstance(object, int):
        return True

    if isinstance(object, list):
        if len(object) == 3 and object[0] in function_symbols:
            return is_valid_expression(object[1], function_symbols, leaf_symbols) and is_valid_expression(object[2], function_symbols, leaf_symbols)

    return False




    #check if object is type int return true

    #if object



    # if object[0] in function_symbols: #check if the first index of object is
    #     if len(object[0]) == 1:
    #         if object[0] in leaf_symbols:
    #             return True
    #     else:
    #         return is_valid_expression(object[0][0], object[0][1], object[0][2])
    #     if len(object[2]) == 1:
    #         return True
    #     else:
    #         return is_valid_expression(object[2][0], object[2][1], object[2][2])
    # else:
    #     return False

    #check that object index



function_symbols = ['f', '+']
leaf_symbols = ['x', 'y']
expression = ['f', ['+', 0, -1], ['f', 1, 'x']]

print(is_valid_expression(
        expression, function_symbols, leaf_symbols))