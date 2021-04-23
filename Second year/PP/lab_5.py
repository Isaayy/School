def my_range(*args):
    if len(args) < 1 or len(args) > 3:
        raise ValueError

    a = args[0]
    b = 0 if len(args) < 2 else args[1]
    k = 0 if len(args) < 3 else args[2]

    if a > b and k > 0:
        return "Wrong input"

    result = []

    if b == 0:
        i = 0.0
        while i < a:
            result.append(i)
            i += 1
        return result

    if k > 0:
        while a < b:
            result.append(a)
            a += k
    else:
        while a > b:
            result.append(a)
            a += k

    if len(result) == 0:
        return [a, b - 0.1]

    return result


if __name__ == '__main__':
    print(my_range(1.1, 2.2, 0.5))
    print(my_range(1.1, 2.1, 0.5))
    print(my_range(1.1, 2.2))
    print(my_range(2.2))