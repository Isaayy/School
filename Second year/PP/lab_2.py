while True:
    height = int(input("Enter the height of the tree: "))
    signs = 1;
    sign =  '*' if height % 2 == 0 else '#'
    i = 1;

    for i in range(0, height):
        spaces = height - i
        print(spaces * ' ',end=" ")
        print(signs * sign,end=" ")
        print()
        i += 1
        signs += 2

    if height == 7:
        break

########################################################################

str = input("Input string: ") 
result = 0;

for letter in str:
    if(letter == 'A' or letter == 'a'):
        result += 1
print("Number of 'a' and 'A' in your string is : ",result)

########################################################################

import random

number = random.randint(1, 10)
chances = 3

while chances:
  guessedNumber = int(input("Guess my number: ")) 
  if guessedNumber < number:
    print("Your number is too low")
  elif guessedNumber > number:
    print("Your number is too high")
  else:
    print("Congratulations you have guessed the number")
    break
  chances -= 1
else:
  print("You lost! No more chances left")