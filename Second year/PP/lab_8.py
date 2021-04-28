import math

class Figura:
    def __init__(self,name):
         self.__name = name
         
    def get_name(self):
        return self.__name
        
class Kolo(Figura):
    def __init__(self,name,r):
        super( ).__init__(name)
        self.promien = r
        
    def obwod(self):
        return 2*math.pi*self.promien
    
class Prostokat(Figura):
    def __init__(self,name,a,b):
        super( ).__init__(name)
        self.a = a
        self.b = b
        
    def obwod(self):
        return 2*self.a + 2*self.b
        
class Kwadrat(Prostokat):
    def __init__(self,name,a):
        super( ).__init__(name,a,a)
    
def main():
    
    figury = []
    
    figury.append( Kolo('Kolo',4) )
    figury.append( Prostokat('Prostokat',5,10) )
    figury.append( Kwadrat('Kwadrat',5) )
  
    for figura in figury:
        print( figura.get_name(), figura.obwod(), sep =' ' )


if __name__ == "__main__":
    main()