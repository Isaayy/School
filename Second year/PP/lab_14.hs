main = do
     putStrLn "Insert height of the tree"
     height <- readLn :: IO Int
     let char = 
          if height `mod` 2 == 0
          then "*"
          else "#"
     printTree height char 1
     if height == 7
          then return()
          else main

printTree 0 char i = return ()
printTree n char i = do
     printSpace (n-1)
     printChar i char
     putChar '\n'
     printTree (n-1) char (i+2)
     
printChar 0 char = return ()
printChar n char = do
     putStr char
     printChar (n-1) char

printSpace 0 = return ()
printSpace n = do
     putStr " "
     printSpace (n-1)
