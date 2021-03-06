def sum_list1(list):
    if len(list)==0:
        return 0
    else:
        return list[0] + sum_list1(list[1:]) 
        
def sum_list2(list,length = 0,sum=0):
    
    if length==0:
        return sum
        
    return sum_list2(list,length-1,sum+list[length-1])
    
def f_variation(arr, arr_len, s=0):
    if len(arr) > 0:
        variation = f_variation(arr[1:], arr_len, s + arr[0])
        return variation[0], variation[1] + (arr[0] - variation[0])**2 / (arr_len - 1)
    return s/arr_len, 0


if __name__ == "__main__":
    
    test1 = [1,2,3,4]
    test2 = []
    
    # zwykla
    print(sum_list1(test1))
    print(sum_list1(test2))
    
    print('------------')

    # ogonowa
    print(sum_list2(test1,len(test1)))
    print(sum_list2(test2))