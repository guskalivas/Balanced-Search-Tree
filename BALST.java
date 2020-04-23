//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P2 BALST.javaT
// Author: Gus kalivas
// Email: wkalivas@wisc.edu


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Class to implement a BalanceSearchTree. AVL
 * Tree
 * 
 * @author guska - Gus Kalivas
 * 
 * 
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {
	private BSTNode<K, V> root; // root of tree
	private int numKeys; // number of keys in the tree

	/**
	 * BSTNode<K,V> Class is an inner class of BALST. It creates the nodes within
	 * the AVL tree
	 * 
	 * @author guska
	 *
	 * @param <K> is the generic type of key
	 * @param <V> is the generic type of value
	 */
	private class BSTNode<K, V> {
		K key; // Unique identifier of node
		V value; // value within the node
		BSTNode<K, V> left; // left node of this node
		BSTNode<K, V> right; // right node of this node
		int balanceFactor; // balance factor of this node
		int height; // height of this node

		/**
		 * BSTNode private method constructor initializes key, value, left child, right
		 * child of this node
		 * 
		 * @param key
		 * @param value
		 * @param leftChild
		 * @param rightChild
		 */
		private BSTNode(K key, V value, BSTNode<K, V> leftChild, BSTNode<K, V> rightChild) {
			this.key = key; // initializes key
			this.value = value; // initializes value
			this.left = leftChild; // initializes left child
			this.right = rightChild; // initializes right child
			this.height = 0; // initializes height
			this.balanceFactor = 0; // initializes balance factor
		}

		/**
		 * BSTNode constructor for param of key and value initializes key and value and
		 * sets left and right child as null
		 * 
		 * @param key   - unique identifier of this node
		 * @param value - value of this node
		 */
		private BSTNode(K key, V value) {
			this(key, value, null, null); // initalizes key value and sets left and right null
		}

		/*
		 * get_key method returns this nodes key
		 * 
		 * @return - key
		 */
		private K get_key() {
			return this.key;

		}

		/**
		 * get_left method returns this nodes left child
		 * 
		 * @return - left child
		 */
		private BSTNode<K, V> get_left() {
			return left;
		}

		/**
		 * get_right method returns this nodes right child
		 * 
		 * @return - right child
		 */
		private BSTNode<K, V> get_right() {
			return right;
		}

		/**
		 * get_value method returns this nodes value
		 * 
		 * @return - value
		 */
		private V get_value() {
			return this.value;
		}

	}

	/*
	 * BALST constructor creates the balance search tree
	 */
	public BALST() {
		root = null; // sets root equal to null
		numKeys = 0; // sets numKEys to 0
	}

	@Override
	/**
	 * Returns the key that is in the root node of this BST. If root is null,
	 * returns null.
	 * 
	 * @return key found at root node, or null
	 */
	public K getKeyAtRoot() {
		if (root == null) { // if root is null return null
			return null;
		}
		return root.get_key(); // return the key at root
	}

	@Override
	/**
	 * Tries to find a node with a key that matches the specified key. If a matching
	 * node is found, it returns the returns the key that is in the left child. If
	 * the left child of the found node is null, returns null.
	 * 
	 * @param key A key to search for
	 * @return The key that is in the left child of the found key
	 * 
	 * @throws IllegalNullKeyException if key argument is null
	 * @throws KeyNotFoundException    if key is not found in this BST
	 */
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) { // if key is null throw IllegalNullKeyException
			throw new IllegalNullKeyException();
		}
		if (!contains(key)) { // if BST does not contain key throw keyNotFoundException
			throw new KeyNotFoundException();
		}
		// else returns the helper method of get key of the left child
		return gethelp(key).get_left().get_key();
	}

	@Override
	/*
	 * 
	 * Tries to find a node with a key that matches the specified key. If a matching
	 * node is found, it returns the returns the key that is in the right child. If
	 * the right child of the found node is null, returns null.
	 * 
	 * @param- key A key to search for
	 * 
	 * @return The key that is in the right child of the found key
	 * 
	 * @throws IllegalNullKeyException if key is null
	 * 
	 * @throws KeyNotFoundException if key is not found in this BST
	 */
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) { // if key is null throw illegaNullKeyException
			throw new IllegalNullKeyException();
		}
		if (!contains(key)) { // if BST does not contain key throw keyNotFoundException
			throw new KeyNotFoundException();
		}
		// else returns the helper method of get key of the right child
		return gethelp(key).get_right().get_key();
	}

	/**
	 * getHelp method is a helper method that gets the Node of of a specific value
	 * 
	 * @param key - key to search for
	 * @return - BSTNode<K,V> of key
	 * @throws IllegalNullKeyException if key is null
	 * @throws KeyNotFoundException    if key is not found in this BST
	 */
	private BSTNode<K, V> gethelp(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) { // if key is null throw IllegaNullKeyException
			throw new IllegalNullKeyException();
		}
		if (!contains(key)) { // if this BST does not contain key throw KeyNotFoundException
			throw new KeyNotFoundException();
		}
		return gethelper(root, key); // return the helper method of get
	}

	/**
	 * helper method to get that returns a node given a key
	 * 
	 * @param node - node to search throw BST
	 * @param key  - of node to return
	 * @return - BSTNode<K,V>
	 * @throws IllegalNullKeyException if key is null
	 * @throws KeyNotFoundException    if key is not found in this BST
	 */
	private BSTNode<K, V> gethelper(BSTNode<K, V> node, K key) throws IllegalNullKeyException, KeyNotFoundException {
		// if nodes key is equal to the key searching for returns the node
		if (node.get_key().equals(key)) {
			return node;
		}
		// recursively calls search method on the left and returns left node
		if (key.compareTo(node.get_key()) < 0) {
			return gethelper(node.get_left(), key);
			// recursively calls search method on the right and returns right node
		} else {
			return gethelper(node.get_right(), key);
		}
	}

	@Override
	/**
	 * Returns the height of this BST. H is defined as the number of levels in the
	 * tree.
	 * 
	 * @return the number of levels that contain keys in this BINARY SEARCH TREE
	 */
	public int getHeight() {
		if (root == null) { // if root is null return 0
			return 0;
		}
		// if roots left and roots right is null return 1
		if (root.get_left() == null && root.get_right() == null) {
			return 1;
		}
		// else return 1 + the max of roots left and right to find the largest branch of
		// BST
		return 1 + Math.max(height(root.get_left()), height(root.get_right()));
	}

	/**
	 * Returns the height of this BST given a node
	 * 
	 * @param node - Node of height looking for
	 * @return - integer of height
	 */
	private int height(BSTNode<K, V> node) {
		if (node == null) { // if node is null return 0
			return 0;
		}
		// if nodes left and right are null return 1
		if (node.get_left() == null && node.get_right() == null) {
			return 1;
		}
		// else return 1 + the max of the node sleft and right to find the largest
		// branch
		return 1 + Math.max(height(node.left), height(node.right));
	}

	@Override
	/**
	 * Returns the keys of the data structure in sorted order. In the case of binary
	 * search trees, the visit order is: L V R
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in-order
	 */
	public List<K> getInOrderTraversal() {
		List<K> list = new LinkedList<K>(); // creates a list to return
		if (root.get_left() != null) { // if left is not null
			// calls the helper method with the list and left side to recurse through tree
			list = inOrderTraversal(list, root.get_left());
		}
		// adds the middle key
		list.add(root.get_key());
		// if right is not null
		if (root.get_right() != null) {
			// calls the helper method with the list and right side to recurse through tree
			list = inOrderTraversal(list, root.get_right());
		}
		return list; // returns the list
	}

	/**
	 * Helper method for InOrderTraversal
	 * 
	 * @param list - list to create
	 * @param node - node of current place in tree
	 * @return - returns the created list
	 */
	private List<K> inOrderTraversal(List<K> list, BSTNode<K, V> node) {
		if (node.get_left() != null) { // if left child is not null
			// keep recursing through tree and set equal to list
			list = inOrderTraversal(list, node.get_left());
		}
		// add middle key
		list.add(node.get_key());
		// if right node is not null
		if (node.get_right() != null) {
			// add the right node to the list and keep recursing
			list = inOrderTraversal(list, node.get_right());
		}
		return list;
	}

	@Override
	/**
	 * Returns the keys of the data structure in pre-order traversal order. In the
	 * case of binary search trees, the order is: V L R
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in pre-order
	 */
	public List<K> getPreOrderTraversal() {
		List<K> list = new LinkedList<K>(); // creates a list to add keys to
		list.add(root.get_key()); // add the middle key
		if (root.get_left() != null) { // if roots left child is not null
			// add the key into list and call helper
			list = getPreOrderTraversal(list, root.get_left());
		}
		// if right child is not null add right and call helper
		if (root.get_right() != null) {
			list = getPreOrderTraversal(list, root.get_right());
		}
		return list; // return the list
	}

	/**
	 * preOrderTraversal helper method to recurse through tree
	 * 
	 * @param list - list to create
	 * @param node - current node in tree
	 * @return - list created
	 */
	private List<K> getPreOrderTraversal(List<K> list, BSTNode<K, V> node) {
		list.add(node.get_key()); // add the node
		if (node.get_left() != null) { // if left child is not null
			// call method again with left child
			list = getPreOrderTraversal(list, node.get_left());
		}
		// if right child is not null call method again with right child
		if (node.get_right() != null) {
			list = getPreOrderTraversal(list, node.get_right());
		}
		return list; // return the created list

	}

	@Override
	/**
	 * Returns the keys of the data structure in post-order traversal order. In the
	 * case of binary search trees, the order is: L R V
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in post-order
	 */
	public List<K> getPostOrderTraversal() {
		List<K> list = new LinkedList<K>(); // creates the list to return
		if (root.get_left() != null) { // if left child is not null
			// call the helper method with the list and left child
			list = getPostOrderTraversal(list, root.get_left());
		}
		// if right child is not null call helper
		if (root.get_right() != null) {
			list = getPostOrderTraversal(list, root.get_right());
		}
		// adds the node
		list.add(root.get_key());
		return list; // returns the list
	}

	/**
	 * helper method for postOrderTraversal
	 * 
	 * @param list - list creates
	 * @param node - current node
	 * @return - List
	 */
	private List<K> getPostOrderTraversal(List<K> list, BSTNode<K, V> node) {
		// if left node is not null call helper method again
		if (node.get_left() != null) {
			list = getPostOrderTraversal(list, node.get_left());
		}
		// if right node is not null call helper method
		if (node.get_right() != null) {
			list = getPostOrderTraversal(list, node.get_right());
		}
		// add nodes key into list
		list.add(node.get_key());
		return list; // return list
	}

	@Override
	/**
	 * Returns the keys of the data structure in post-order traversal order. In the
	 * case of binary search trees, the order is: L R V
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in post-order
	 */
	public List<K> getLevelOrderTraversal() {
		return getLevelOrderTraversal(root); // returns helper method with root
	}

	/**
	 * Helper method for level order traversal
	 * 
	 * @param node - current node in traversal
	 * @return - list created
	 */
	private List<K> getLevelOrderTraversal(BSTNode<K, V> node) {
		ArrayList<K> list = new ArrayList<K>(); // creates an arrayList to store keys
		if (root == null) { // if root is null return list
			return list;
		}
		// Second arrayList of nodes
		ArrayList<BSTNode<K, V>> nodes = new ArrayList<BSTNode<K, V>>();
		nodes.add(root); // add root into nodes list
		while (!nodes.isEmpty()) { // while the nodes list is not empty
			int size = nodes.size(); // keeps track of number of nodes in list
			ArrayList<K> temp = new ArrayList<>(size); // temp array list of keys with size of nodes
			// loop through number of times of size of nodes
			for (int i = 0; i < size; i++) {
				// temp node created to that removes from nodes and adds to temp
				BSTNode<K, V> tempNode = nodes.remove(0);
				temp.add(tempNode.get_key());
				// if temp nodes left child is not null
				if (tempNode.get_left() != null) {
					nodes.add(tempNode.get_left()); // add temp nodes left into nodes
				}
				// if temps right node is not null
				if (tempNode.get_right() != null) {
					nodes.add(tempNode.get_right()); // add node from temp
				}
			}
			// loop through temp list size
			for (int i = 0; i < temp.size(); i++) {
				list.add(temp.get(i)); // add each node from temp into list to return
			}
		}
		return list; // returns created list
	}

	@Override
	/**
	 * Insert method adds a new node into the tree and calls the rotate methods to
	 * rotate nodes and keep a balance AVL tree. If key is null, throw
	 * IllegalNullKeyException. If key is already in data structure, throw
	 * DuplicateKeyException(); Do not increase the num of keys in the structure, if
	 * key,value pair is not added.
	 * 
	 * @param key   - unique identifier of node to insert
	 * @param value - value of node to add
	 * @throws IllegalNullKeyException if key is null
	 * @throws KeyNotFoundException    if key is not found in this BST
	 * 
	 */
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		// if key is null throw IllegalNullKeyExcpeiton
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		// Otherwise insert key/value pair with helper method
		root = insert(key, value, root);
	}

	/**
	 * Private helper for insert
	 * 
	 * @param key          - identifier of node to insert
	 * @param value        - value of node to insert
	 * @param BSTNode<K,V> - current node
	 * @throws KeyNotFoundException if key is not found in this BST
	 */
	private BSTNode<K, V> insert(K key, V value, BSTNode<K, V> node) throws DuplicateKeyException {
		// if current node is null add new BSTNode and increase numKeys
		if (node == null) {
			numKeys++;
			return new BSTNode<K, V>(key, value);
		}

		// if key is less than nodes key go left
		if (key.compareTo(node.key) < 0) {
			node.left = insert(key, value, node.left);

			// if key is greater than nodes key go right
		} else if (key.compareTo(node.key) > 0) {
			node.right = insert(key, value, node.right);

			// else keys are equal and throw Duplicate key exception
		} else {
			throw new DuplicateKeyException();
		}
		// Update height and balance factor of node
		node.height = height(node);
		node.balanceFactor = height(node.left) - height(node.right);

		// if nodes balance factor is greater than or equal to 2 left is unbalanced
		if (node.balanceFactor >= 2) {
			// key was inserted left of child, rightRotate
			if (node.left.balanceFactor > 0) {
				node = rightRotate(node);
				// New key was inserted down right subtree of child, leftRotate on
				// child, then rightRotate
			} else if (node.left.balanceFactor < 0) {
				node.left = leftRotate(node.left);
				node = rightRotate(node);
			}
		}
		// if nodes balance factor is less then or equal to -2 right is unbalanced
		if (node.balanceFactor <= -2) {
			// key was inserted right of child, leftRotate
			if (node.right.balanceFactor < 0) {
				node = leftRotate(node);
				// key was inserted left of child, rightRotate on
				// child, then leftRotate
			} else if (node.right.balanceFactor > 0) {
				node.right = rightRotate(node.right);
				node = leftRotate(node);
			}
		}
		return node;
	}

	/**
	 * leftRotate method rotates the nodes if unbalanced
	 * 
	 * @param current - BSTNode
	 * @return - BSTNode rotated
	 */
	private BSTNode<K, V> leftRotate(BSTNode<K, V> current) {
		BSTNode<K, V> temp = current.right; // current.left is parent
		current.right = temp.left; // sets current right to currents left
		temp.left = current; // temps left becomes current
		return temp; // returns rotated node
	}

	/**
	 * rightRotate method rotates the node if unbalanced
	 * 
	 * @param current - BSTNode to rotate
	 * @return BSTNode rotated
	 */
	private BSTNode<K, V> rightRotate(BSTNode<K, V> current) {
		BSTNode<K, V> temp = current.left; // currentNode.right is parent
		current.left = temp.right; // sets current right to current left
		temp.right = current; // temps right becomes current
		return temp; // returns rotated node
	}

	@Override
	/**
	 * Remove method - finds a node by its key and removes it from tree If key is
	 * found, remove the key,value pair from the data structure and decrease num
	 * keys. If key is not found, do not decrease the number of keys in the data
	 * structure. If key is null, throw IllegalNullKeyException If key is not found,
	 * throw KeyNotFoundException().
	 * 
	 * @param key - key of node to remove
	 * @return True or false - if key is found or not
	 * 
	 * @throws IllegalNullKeyException if key is null
	 * @throws KeyNotFoundException    if key is not found in this BST
	 */
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) { // if key is null throw IllegalNullKeyExepction
			throw new IllegalNullKeyException();
		}
		if (root == null) { // if root is null return false
			return false;
		}
		if (!contains(key)) { // if BST does not contain key throw KeyNotFoundExecption
			throw new KeyNotFoundException();
		}
		root = removeHelper(root, key); // recursive call to helper with root and key
		numKeys--; // decrement keys
		return true; // return true
	}

	/**
	 * Remove helper is the private helper method for remove. It takes BSTNode<K,V>
	 * and key and throw illegalNullKEyExecption KeyNotFoundExecption
	 * 
	 * @param node - BSTNode<K,V> of current node
	 * @param key  - unique identifier of node to remove
	 * @return BSTNode<K,V> to remove
	 * @throws IllegalNullKeyException - if key is null
	 * @throws KeyNotFoundException    - if key not in BST throws not found
	 *                                 exception
	 * 
	 */
	private BSTNode<K, V> removeHelper(BSTNode<K, V> node, K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (node == null) { // if node is null return null
			return null;
		}
		// if key is less then node's key go right
		if (key.compareTo(node.key) < 0) {
			node.right = removeHelper(node.right, key); // set nodes left equal to remove helper of nodes right and key
		} else {
			// if left and right are null node is a leaf set root to null
			if (node.left == null && node.right == null) {
				node = null;
			} else if (node.left == null) { // if left is null set null equal to nodes right
				node = node.right;
			} else if (node.right == null) { // if nodes right is null set node equal to nodes left
				node = node.left;
			} else { // find the max of left for in order predessor
				BSTNode<K, V> max = findMax(node.left);
				node = max; // node equal to max
				node.left = removeHelper(node.left, node.key); // left is equal to helper method of left and nodes key
			}
		}
		return node; // return the node to remove
	}

	/**
	 * findMAx method is the helper method for remove to find the in order
	 * predecessor
	 * 
	 * @param node - node to find max of
	 * @return - in order predecessor
	 */
	private BSTNode<K, V> findMax(BSTNode<K, V> node) {
		BSTNode<K, V> max = node; // temp node of max
		if (node.right != null) { // go right till null
			max = findMax(node.right); // max equals the nodes right
		}
		return max;

	}

	@Override
	/**
	 * Searches through the tree to find the node associated with the key and
	 * returns the value of the node
	 *
	 * @param key - key to find value of
	 * @return value - value associated with the key
	 * 
	 * @throws IllegalNullKeyException - if key is null
	 * @throws KeyNotFoundException    - if key is not in tree
	 * 
	 */
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		return get(root, key); // calls helper method
	}

	private V get(BSTNode<K, V> node, K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) { // if key is null throw illegalNullKeyException
			throw new IllegalNullKeyException();
		}
		if (!contains(key)) { // if tree does not contain key throw KeyNotFoundException
			throw new KeyNotFoundException();
		}
		// returns value if the key is found in the tree
		if (node.get_key().equals(key)) {
			return node.get_value();
		}
		// recursively calls search method on the left
		if (key.compareTo(node.get_key()) < 0) {
			return get(node.get_left(), key);
			// recursively calls search method on the right
		} else {
			return get(node.get_right(), key);
		}
	}

	/*
	 * @Override Contains method searches the tree to see if it contains a key or
	 * not
	 * 
	 * @param key - key to search for
	 * 
	 * @return true or false - if tree contains key or not
	 * 
	 * @throws - IllegalNullKeyException if key is null
	 */
	public boolean contains(K key) throws IllegalNullKeyException {
		return containshelp(root, key); // returns helper method
	}

	private boolean containshelp(BSTNode<K, V> node, K key) throws IllegalNullKeyException {
		if (key == null) { // if key is null throw IllegalNullKeyExcpetion
			throw new IllegalNullKeyException();
		}
		if (node == null) { // if node is null return false
			return false;
		}
		// returns true if the key is found in the tree
		if (node.get_key().equals(key)) {
			return true;
		}
		// recursively calls search method on the left
		if (key.compareTo(node.get_key()) < 0) {
			return containshelp(node.get_left(), key);
			// recursively calls search method on the right
		} else {
			return containshelp(node.get_right(), key);
		}
	}

	@Override
	/**
	 * Returns the number of key,value pairs in the data structure
	 */
	public int numKeys() {
		return numKeys;
	}

	@Override
	/**
	 * print method prints an image of the tree
	 */
	public void print() {
		print(root, 0);
	}

	/**
	 * Helper method for print
	 * 
	 * @param node - current node
	 * @return - String of the tree
	 */
	private void print(BSTNode<K, V> node, int space) {
		if (node == null) { // if node is null exit print helper
			return;
		}
		System.out.print("\n" + "\t");
		print(node.right, space++); // call helper method going right first and incrementing space
		if (space != 0) { // if space is not 0
			for (int i = 0; i < space - 1; i++) { // loop through number of spaces -1
				System.out.print("\t");

			}
			System.out.println("|------" + node.key);

		} else
			System.out.println(node.key);
		print(node.left, space++);

	}

}
