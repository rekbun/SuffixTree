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
				"sdfabcsdf",
				"sssssfd"
		};
		for(int i=0;i<array.length;i++) {
			generalizedSuffixTree.put(array[i], i);
		}

		for(int i=0;i<array.length;i++) {
			assertEquals(true,generalizedSuffixTree.search(array[i]).contains(i));
		}
	}

	public void testSearchForLimit() throws Exception{

		GeneralizedSuffixTree generalizedSuffixTree = new GeneralizedSuffixTree();
		String[] array=new String[]{
				"abca",
				"sdfabcsdf",
				"sssssfd"
		};
		for(int i=0;i<array.length;i++) {
			generalizedSuffixTree.put(array[i], i);
		}

		assertEquals(1,generalizedSuffixTree.search("s",1).size());
	}
}
