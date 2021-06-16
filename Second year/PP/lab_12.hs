f1 list = map ((/2) . (+1)) list
f2 list = (map (\ x -> x/2) . map (\x -> x+1)) list

factorial :: ( Integral a ) => a -> a
factorial n 
 | n < 0 = -1
 | n > 0 = n * factorial ( n - 1 )
 | otherwise = 1

 
factorial_2 :: ( Integral a ) => a -> a
factorial_2 n = if n >= 0 
                then if n == 0
                     then 1
                     else n * factorial ( n - 1 )
                else -1

searchLast el list = search el list (-1) 0
  where
    search el list index i =
      if length list == 0
        then index
        else 
          if head list == el
            then search el (tail list) i (i+1)
            else search el (tail list) index (i+1)

searchFirst el (x:xs) = search el (x:xs) (-1) 0
  where
    search el list index i
      | length list == 0 = index
      | head list == el = i
      | otherwise = search el (tail list) index (i+1)


main = do

let szukanyElement = 3
let lista = [1,2,3,4,5,3,6]

let wynik = searchLast szukanyElement lista
let wynik2 = searchFirst szukanyElement lista

print(wynik)
print(wynik2)