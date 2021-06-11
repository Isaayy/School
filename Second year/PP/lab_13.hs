data Tree a = EmptyNode 
              | Leaf a 
              | Node a (Tree a) (Tree a)

showTree :: Show a => Tree a -> Int -> String
showTree tree lvl =
     let space = replicate (3*lvl) ' ' in
     case tree of
          (Leaf n) -> space ++ (show n)
          (Node n tl tr) -> showTree tl (lvl+1) ++ "\n"
               ++ space ++ (show n) ++ "\n"
               ++ showTree tr (lvl+1) 

treeSize:: Tree b -> Int
treeSize EmptyNode = 0
treeSize (Leaf n) = 1
treeSize (Node _ tl tr) = 1 + max (treeSize tl) (treeSize tr)
 
leafs:: Tree b -> Int
leafs EmptyNode = 0
leafs (Leaf n) = 1
leafs (Node _ tl tr) = (leafs tl) + (leafs tr)


treeTOlist EmptyNode = []
treeTOlist (Leaf n) = [n] 
treeTOlist (Node n tl tr) = treeTOlist tl ++ [n] ++ treeTOlist tr
 
listTOtree [] = EmptyNode
listTOtree [n] = Leaf n
listTOtree xs = Node (xs !! half) (listTOtree $ take half xs)
    (listTOtree $ drop (half + 1) xs)
    where
        len = length xs
        half = len `div` 2
          
mkTree  =  Node 5 (Node 5 (Leaf 3) (Leaf 5)) (Leaf 2)

printTree = putStr(showTree mkTree 0)
printTreeSize = print(treeSize mkTree)
printLeafs = print(leafs mkTree)