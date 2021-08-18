def depth(expression):
    # add one
    # base case is that it is length one
    # if not length one return count

    if isinstance(expression, str):
        return 0

    if isinstance(expression, int):
        return 0

    if isinstance(expression, list):
        return 1 + max(depth(expression[1]),depth(expression[2]))



expression = 12
print(depth(expression))