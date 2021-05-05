def f1(arr):
    return list(map(lambda num: num * len(arr), arr))


def f2(arr):
    return list(map((len(arr)).__mul__, arr))


def f3(arr):
    def multiply(a):
        return a * len(arr)
    return list(map(multiply,arr))
    
def prime_numbers(max_number):
    f = lambda m: all(list(map(lambda num: m % num, range(2, m))))
    return filter(f, range(2, max_number))


if __name__ == '__main__':
    test = [1, 2, 3]
    test2 = [1, 2, 3, 4, 5]

    print(f1(test))
    print(f1(test2))
    
    print('-------------------------')
    
    print(f2(test))
    print(f2(test2))
    
    print('-------------------------')
    
    print(f3(test))
    print(f3(test2))
    
    for i in prime_numbers(50):
        print(i)