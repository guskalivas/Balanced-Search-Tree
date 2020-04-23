
class BSTNode<K,V> {
    
    K key;
    V value;
    BSTNode<K,V> left;
    BSTNode<K,V> right;
    int balanceFactor;
    int height;
    

    /**
     * @param key
     * @param value
     * @param leftChild
     * @param rightChild
     */
    BSTNode(K key, V value, BSTNode<K,V>  leftChild, BSTNode<K,V> rightChild) {
        this.key = key;
        this.value = value;
        this.left = leftChild;
        this.right = rightChild;
        this.height = 0;
        this.balanceFactor = 0;
    }
    
    BSTNode(K key, V value) { 
    	this(key,value,null,null);
    	}
    private K get_key(){
    	return this.key;
    	
    }
    
}
