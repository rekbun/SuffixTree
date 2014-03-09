package test.java.com.rakesh.suffixtree;

import junit.framework.TestCase;
import main.java.com.rakesh.suffixtree.GeneralizedSuffixTree;

public class GeneralizedSuffixTreeTest extends TestCase {
	public void testPut() throws Exception {
		GeneralizedSuffixTree generalizedSuffixTree = new GeneralizedSuffixTree();
		String[] array=new String[]{
			"abca",
			"sdfabcsdf"
		};
		generalizedSuffixTree.put(array[0], 0);
		generalizedSuffixTree.put(array[1], 1);
		assertTrue(generalizedSuffixTree.search(array[0])!=null);
		assertTrue(generalizedSuffixTree.search(array[1])!=null);
	}

	public void testSearch() throws Exception {
		GeneralizedSuffixTree generalizedSuffixTree = new GeneralizedSuffixTree();
		String[] array=new String[]{
				"abca",
				"sdfabcsdf"
		};
		generalizedSuffixTree.put(array[0], 0);
		generalizedSuffixTree.put(array[1], 1);

	}
}
