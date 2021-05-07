import operator
from functools import reduce


def avg(arr):
    return reduce(lambda a, b: a+b, arr)/len(arr)


def max_in_list(arr):
    return reduce(lambda a, b: a if a > b else b, arr)


def join_lists(*arrays):
    return reduce(operator.add, arrays)


def manhattan_distance(a, b):
    return reduce(lambda v1, v2: abs(v1[0] - v2[0]) + abs(v1[1] - v2[1]), zip(a, b))


if __name__ == '__main__':

    test_case = [1, 2, 3, 4, 5]
    test_case2 = [6, 7, 8]
    test_case3 = [9, 10]

    print("Average in list : ", avg(test_case))
    print("Max value in list: ", max_in_list(test_case))
    print("Join lists: ", join_lists(test_case, test_case2, test_case3))
    print("Manhattan distance: ", manhattan_distance([1, 2], [2, 3]))