class Fraction:
    
    # Keep track of Fraction class instances
    instances = []
    
    # Class constructor
    def __init__(self,*args):
        if (len(args) == 2 ):
            self.numerator = args[0]
            self.denominator = args[1]
        else:
            self.numerator = str(args[0]-int(args[0]))[2:]
            self.denominator = 10**len(self.numerator)
        Fraction.instances.append(self)
    
    # Display object
    def __str__(self):
        return str(self.numerator)+"/"+str(self.denominator)
        
    # Common denominator private method
    def __common(self,obj):
        return self.denominator * obj.denominator
        
    # Add method
    def __add__(self,obj):
        n = self.numerator*obj.denominator + self.denominator*obj.numerator
        d = self.__common(obj)
        return Fraction(n,d)
        
    # Subtract
    def __sub__(self,obj):
        n= self.numerator*obj.denominator - self.denominator*obj.numerator
        d = self.__common(obj)
        return Fraction(n,d)
        
    # Multiply method
    def __mul__(self,obj):
        n = self.numerator * obj.numerator
        d = self.denominator * obj.denominator
        return Fraction(n,d)
        
    # Divide method
    def __div__(self,obj):
        n = self.numerator * obj.denominator
        d = self.denominator * obj.numerator
        return Fraction(n,d)
    

def main():
    f1 = Fraction(3,4)
    f2 = Fraction(3,4)
    f3 = f1*f2
    f4 = f1-f2
    print(f3)
    print(f4)
    f5 = Fraction(0.25)
    print(f5)

if __name__ == "__main__":
    main()