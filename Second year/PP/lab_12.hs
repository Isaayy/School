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