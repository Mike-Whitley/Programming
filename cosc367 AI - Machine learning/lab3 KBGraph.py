import re
from search import *


def clauses(knowledge_base):
    """Takes the string of a knowledge base; returns an iterator for pairs
    of (head, body) for propositional definite clauses in the
    knowledge base. Atoms are returned as strings. The head is an atom
    and the body is a (possibly empty) list of atoms.

    -- Kourosh Neshatian - 31 Jul 2019

    """
    ATOM = r"[a-z][a-zA-z\d_]*"
    HEAD = rf"\s*(?P<HEAD>{ATOM})\s*"
    BODY = rf"\s*(?P<BODY>{ATOM}\s*(,\s*{ATOM}\s*)*)\s*"
    CLAUSE = rf"{HEAD}(:-{BODY})?\."
    KB = rf"^({CLAUSE})*\s*$"

    assert re.match(KB, knowledge_base)

    for mo in re.finditer(CLAUSE, knowledge_base):
        yield mo.group('HEAD'), re.findall(ATOM, mo.group('BODY') or "")


class KBGraph(Graph):
    def __init__(self, kb, query):
        self.clauses = list(clauses(kb)) #[('a', ['b', 'c']), ('b', ['d', 'e']), ('b', ['g', 'e']), ('c', ['e']), ('d', []), ('e', []), ('f', ['a', 'g'])]
        self.query = query
        self.kb = kb



    def starting_nodes(self):
        """Returns a sequence of starting nodes. Often there is only one
        starting node but even then the function returns a sequence
        with one element. It can be implemented as an iterator if
        needed.

        """
        # print([self.query])
        return [self.query] # starting node starts as the query passed in then must be query

    def is_goal(self, node): #letters that area true are goal nodes
        """Returns true if the given node is a goal state, false otherwise."""
        return len(node) == 0

    def outgoing_arcs(self, tail_node): #tail node is the left most node when loop through if find that its body is [] pop it off
        """Given a node it returns a sequence of arcs (Arc objects)
                which correspond to the actions that can be taken in that
                state (node)."""
        tail_node = list(tail_node)
        arcs_list = []
        for head, body in self.clauses:
            if len(tail_node) >= 1 and tail_node[0] == head:
                    for tail_node_single in tail_node:
                            new_head = set(tail_node) - set(tail_node_single) #everything in tail_node thats not in tail_node_single
                            new_head = new_head.union(body)
                            if Arc(set(tail_node), set(new_head), str(tail_node) + '->' + str(new_head), 1) not in arcs_list:
                                arcs_list.append(Arc(tail_node, new_head, str(tail_node) + '->' + str(new_head), 1))

        return arcs_list #return a list of arcs

class DFSFrontier(Frontier):
    """Implements a frontier container appropriate for depth-first
    search."""

    def __init__(self):
        """The constructor takes no argument. It initialises the
        container to an empty stack."""
        self.container = []

    def add(self, path):
        self.container.append(path)
        # raise NotImplementedError  # FIX THIS #takes path,  add to frontier append to container

    def __iter__(self):
        """The object returns itself because it is implementing a __next__
        method and does not need any additional state for iteration."""
        return self

    def __next__(self): #this is called multiple times so we return it not yeild it
        if len(self.container) > 0:
             return self.container.pop() # FIX THIS return something instead #if anything in the list return the next item to visit pop next item to list bfs opposite
        #if anything is in the self.container list pop the item adn return it
        else:
            raise StopIteration  # don't change this one


# def forward_deduce(a_knowledge_base_str):
#     """ Use to find the goal nodes """
#     result_list = set()
#     for i in list(clauses(a_knowledge_base_str)):
#         if i[1] == []:
#             result_list.add(i[0])
#     change = True
#     loop = 0
#     while change:
#         start_list = result_list
#         for body in list(clauses(a_knowledge_base_str)):
#             if set(set(body[1])) <= set(result_list):
#                 result_list.add(body[0])
#                 if start_list != result_list:
#                     loop = 0
#         loop += 1
#         if loop > 2:
#             change = False
#     return sorted(result_list)
#
#


kb = """
a :- b, c.
b :- d, e.
b :- g, e.
c :- e.
d.
e.
f :- a,
     g.
"""

query = {'a', 'b', 'd', 'e'}
if next(generic_search(KBGraph(kb, query), DFSFrontier()), None):
    print("The query is true.")
else:
    print("The query is not provable.")