import operator

import random


def evaluate(expression, bindings):

    if isinstance(expression, int):
        return expression

    if isinstance(expression, str):
        return bindings[expression]

    if isinstance(expression, list):
        return bindings[expression[0]](evaluate(expression[1], bindings), evaluate(expression[2], bindings))



bindings = dict(x = 5, y = 10, blah = 15, add = operator.add)
expression = ['add', ['add', 22, 'y'], 'x']
print(evaluate(expression, bindings))