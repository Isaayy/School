import Control.Applicative

justProduct:: [Maybe Int] -> Maybe Int
justProduct [ ] = Just 1
justProduct (x:xs) = (*) <$> x <*> (justProduct xs)

justProduct' [ ] = Just 1
justProduct' (x:xs) = do   
  a <- x
  b <- (justProduct' xs)
  return (a * b)

lift2 f mx my = f <$> mx <*> my 

justProduct'' (x:xs) = foldl (lift2 (*)) (Just 1) (x:xs)
                                              
main = do
        let lista1 = [Just 1, Just 2, Just 3, Just 4]
        let lista2 = [Just 1, Just 2, Nothing, Just 4]
        print( justProduct lista1)
        print( justProduct' lista1)
        print( justProduct'' lista1)
        print( justProduct lista2)
        print( justProduct' lista2)
        print( justProduct'' lista2)