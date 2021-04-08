def avg_var(arr):
    avg = sum(arr) / len(arr) 
    var = sum([(x - avg) ** 2 for x in arr]) / len(arr)
    return avg,var
        
        
num=input()
arr=[]
while int(num)!=0:
   arr.append(int(num))
   num = input()
   
avg, var = avg_var(arr)
print('Srednia wynosi: ',avg) 
print('Wariancja wynosi: ',var)

avg2, var2 = avg_var([3,3,3,3])
print('Srednia wynosi: ',avg2) 
print('Wariancja wynosi: ',var2)

avg3, var3 = avg_var([5,6,7,8,9])
print('Srednia wynosi: ',avg3) 
print('Wariancja wynosi: ',var3) 


##################################################

import math

def quadratic_function(x):
    return x**2


def derivative(f,x,h=0.0001):
    return (f(x+h)-f(x))/h
    
print(derivative(math.sin,1))
print(derivative(math.sin,0))
print(derivative(quadratic_function,1,0.00001))