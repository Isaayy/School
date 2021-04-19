def numberToString(inputStr):
    
    #remove characters from inputed string
    inputStr = filter(str.isdigit, inputStr)
    
    # Collection 
    slownik = {
        "0" : "zero",
        "1" : "jeden",
        "2" : "dwa",
        "3" : "trzy",
        "4" : "cztery",
        "5" : "pięć",
        "6" : "sześć",
        "7" : "siedem",
        "8" : "osiem",
        "9" : "dziewięć"
    }
    
    # Create array of single numbers from input string and replace them using colection
    charsArray = list(inputStr)
    for i in range(len(charsArray)):
        charsArray[i] = slownik[charsArray[i]]
  
    # Join them into one string with space as seperator
    result = " "
    result = result.join(charsArray)
    
    return result
    
        
def leap_years(start,end):
    return [ x for x in range(start,end) if x % 400 == 0 or (x % 100 != 0 and x % 4 == 0)]
   

print(numberToString('14s105251sfdad2'))
print(leap_years(1900,2001))