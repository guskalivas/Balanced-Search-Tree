//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Author: Gus kalivas

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

@SuppressWarnings("rawtypes")
public class BALSTTest {

	BALST<String, String> balst1;
	BALST<Integer, String> balst2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		balst1 = createInstance();
		balst2 = createInstance2();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		balst1 = null;
		balst2 = null;
	}

	protected BALST<String, String> createInstance() {
		return new BALST<String, String>();
	}

	protected BALST<Integer, String> createInstance2() {
		return new BALST<Integer, String>();
	}

	/**
	 * Insert three values in sorted order and then check the root, left, and right
	 * keys to see if rebalancing occurred.
	 */
	@Test
	void testBALST_001_insert_sorted_order_simple() {
		try {
			balst2.insert(10, "10"); // tries to insert node with key 10 and value 10
			if (!balst2.getKeyAtRoot().equals(10)) // if root does not equal 10 test failed
				fail("avl insert at root does not work"); // fail message

			balst2.insert(20, "20"); // insert node 20 key and 20 value
			if (!balst2.getKeyOfRightChildOf(10).equals(20)) // if right child of 10 is not 20 failed
				fail("avl insert to right child of root does not work");

			balst2.insert(30, "30"); // insert node with key and value 30
			Integer k = balst2.getKeyAtRoot(); // k = key at root
			if (!k.equals(20)) // if k does not equal 20
				fail("avl rotate does not work"); // fail

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
			Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
			Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

			balst2.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Insert three values in reverse sorted order and then check the root, left,
	 * and right keys to see if rebalancing occurred in the other direction.
	 */
	@Test
	void testBALST_002_insert_reversed_sorted_order_simple() {
		try {
			// tries to insert large to small
			balst2.insert(30, "hello");
			balst2.insert(20, "hello");
			balst2.insert(10, "hello");

			try {
				// tests if root is equal to 20, left child equals 10 and right childis 30
				Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
				Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
				Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));
			} catch (KeyNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IllegalNullKeyException | DuplicateKeyException e) {
			e.printStackTrace();
			fail("Error in test002 insert reverd sorted order"); // prints fail message
		}
	}

	/**
	 * Insert three values so that a right-left rotation is needed to fix the
	 * balance.
	 * 
	 * Example: 10-30-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing occurred in
	 * the other direction.
	 */
	@Test
	void testBALST_003_insert_smallest_largest_middle_order_simple() {
		try {
			// tries to insert small, largest then middle and test rebalance
			balst2.insert(10, "hello");
			balst2.insert(30, "hello");
			balst2.insert(20, "hello");

			try {
				// if root does not equal 20, left does not equal 10 and right does not equal 30
				// test fail
				Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
				Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
				Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));
			} catch (KeyNotFoundException e) {
				fail("KeyNotFoundException caught: error");
				e.printStackTrace();
			}
		} catch (IllegalNullKeyException | DuplicateKeyException e) {
			e.printStackTrace();
			fail("Error in test003 insert small largest middle order"); // print fail message
		}

	}

	/**
	 * Insert three values so that a left-right rotation is needed to fix the
	 * balance.
	 * 
	 * 
	 * Then check the root, left, and right keys to see if rebalancing occurred in
	 * the other direction.
	 */
	@Test
	void testBALST_004_insert_largest_smallest_middle_order_simple() {
		try {
			// tries to insert largest smallest
			balst2.insert(30, "hello");
			balst2.insert(10, "hello");
			balst2.insert(20, "hello");

			try {
				// test is root equals 20, left of 20 equals 10 and right of 20 equals 30
				Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
				Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
				Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));
			} catch (KeyNotFoundException e) {
				fail("Unexpected exception AVL 004: " + e.getMessage()); // prints unexpected exception
				e.printStackTrace();
			}
		} catch (IllegalNullKeyException | DuplicateKeyException e) {
			e.printStackTrace();
			fail("Error in test004 insert largest smallest then middle"); // prints error if execption caught
		}
	}

	@Test
	/**
	 * height of tree test method inserts 5 nodes that should rotate to a height of
	 * 3 after rebalancing
	 */
	void testBALST_005_height_of_tree() {
		try {
			// inserts 5 nodes
			balst2.insert(30, "hey");
			balst2.insert(10, "helo");
			balst2.insert(20, "hello");
			balst2.insert(40, "yoyoyoyo");
			balst2.insert(50, "value");
		} catch (IllegalNullKeyException e) {
			fail("Unexpected exception AVL 005: " + e.getMessage()); // prints fail for exception caught
			e.printStackTrace();
		} catch (DuplicateKeyException e) {
			fail("Unexpected exception AVL 005: " + e.getMessage()); // prints fail for execption caught
			e.printStackTrace();
		}
		if (balst2.getHeight() != 3) { // if height does not equal 3
			fail("Error in test 005 height of tree is not correct"); // print error message
		}
	}

	@Test
	/**
	 * test 06 checks the working capacity of the contains method
	 */
	void testBALST_006_contains() {
		try {
			// inserts three nodes into tree
			balst2.insert(10, "hello");
			balst2.insert(30, "hello");
			balst2.insert(20, "hello");
		} catch (IllegalNullKeyException e) {
			fail("Unexpected exception AVL 006: " + e.getMessage()); // prints error if caught fail
			e.printStackTrace();
		} catch (DuplicateKeyException e) {
			fail("Unexpected exception AVL 005: " + e.getMessage()); // prints error if caught fail
			e.printStackTrace();
		}

		try {
			// if balst2 contains returns not true
			if (!balst2.contains(30)) {
				fail("Error in test 006 contains method did not return correct result"); // print error message
			}
		} catch (IllegalNullKeyException e) {
			fail("Unexpected exception AVL 006: " + e.getMessage()); // print error if exception caught
			e.printStackTrace();
		}
	}

	@Test
	/**
	 * test 07 checks the get method working capacity
	 */
	void testBALST_007_get() {
		try {
			// inserts four nodes
			balst2.insert(10, "value");
			balst2.insert(40, "hello");
			balst2.insert(20, "gus");
			balst2.insert(70, "library");
		} catch (IllegalNullKeyException e) { // if catches exception print error
			fail("Unexpected exception AVL 007: " + e.getMessage());

			e.printStackTrace();
		} catch (DuplicateKeyException e) { // if catches exception prints error
			fail("Unexpected exception AVL 007: " + e.getMessage());
			e.printStackTrace();
		}
		try {
			// if balst get method for key 70 does not return "library" test fails
			if (!balst2.get(70).equals("library")) {
				fail("Error in test 007 get method did not return correct value");
			}
		} catch (IllegalNullKeyException e) {
			fail("Unexpected exception AVL 007: " + e.getMessage()); // if exception caught failed
			e.printStackTrace();
		} catch (KeyNotFoundException e) {
			fail("Unexpected exception AVL 007: " + e.getMessage()); // if exception caught print fail message
			e.printStackTrace();
		}
	}

	@Test
	/**
	 * test remove method - inserts three nodes and removes two
	 */
	void testBALST_008_remove_method() {
		try {
			// inserts three nodes
			balst2.insert(30, "value");
			balst2.insert(10, "value");
			balst2.insert(40, "value");

			try {
				// if remove does not return true test failed
				if (!balst2.remove(30)) {
					fail("Error in test 008 numkeys in remove not correct");
				}
				// if remove does not reutn true test failed
				if (!balst2.remove(40)) {
					fail("Error in test 008 numkeys in remove not correct");
				}
			} catch (KeyNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IllegalNullKeyException e) {
			fail("Unexpected exception AVL 008: " + e.getMessage()); // if exception caught print fail
			e.printStackTrace();
		} catch (DuplicateKeyException e) {
			fail("Unexpected exception AVL 008: " + e.getMessage()); // if execption caught print fails
			e.printStackTrace();
		}

	}
}
