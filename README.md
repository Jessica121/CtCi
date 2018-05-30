# CtCi practice

·錯誤：
- class 中 property 用 public or private
- if else沒有把所有情況考慮完全，比如 h1 = h2 這種
- recursive 左和右都寫成左……………… （4.8）
- Wrapper class中 要比較這整個class還是那裡面的node or value

·方法分類：

1. String and Array

2. LinkedList

3. Stack and Queue

4. Tree and Graph 
	~Null node， input check，下面要用到左右parent的check自己null；recursive 保护
				insert in tree: left可能有值用recursive去insert，除非没有（left == null）才可以覆盖！（left = new ...)
	~Recursive: bulk 中 寫下所有可能出現的情況。
				自己是要check的node
				自己是leaf
				自己是要求的
				比較root 和 recursive的結果children，在同一bulk中出現

