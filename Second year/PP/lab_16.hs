import Data.List  

moreThan oN x = res
  where
    s = sort x
    res = f oN s
    f n [] = []
    f 0 (x : xs) = x : (moreThan oN $ filter (/= x) xs)
    f n (x : y : xs)
      | x == y = f (n -1) (y : xs)
      | x /= y = f oN $ filter (/= x) (y : xs)
      | otherwise = []
    f n _ = []  

--------------------------------------------------------------


insert :: Ord a => a -> [a] -> [a]
insert x xs = [i | i <- xs, i <= x] ++ x : [i | i <- xs, i > x]

sort :: Ord a => [a] -> [a]
sort = foldr insert []   

main = do
  let list = [1,2,4,5]
  let el = 3
  print(list)
  print(insert el list)

  let list2 = [2,1,3,5,4]
  print(list2)
  print(sort list2)

  --------------------------------------------------------------

  let ilosc = 1
  let lista = [1,2,3,6,3,2,3,2]
  let wynik = moreThan ilosc lista
  print(wynik)

  let ilosc2 = 2
  let wynik2 = moreThan ilosc2 lista
  print(wynik2)

  let ilosc3 = 3
  let wynik3 = moreThan ilosc3 lista
  print(wynik3)