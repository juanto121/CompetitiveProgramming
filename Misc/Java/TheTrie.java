import java.util.HashMap;
import java.util.Map;

public class TheTrie {
	public static void main(String[] args) {
		Trie t = new Trie();
	}
}

class Trie {
	TrieNode root = new TrieNode();
	
	class TrieNode{
		boolean isEndOfWord;
		Map<String,TrieNode> children = new HashMap<>();
		TrieNode(){
			isEndOfWord = false;
		}
	}
	
	void insert(String word){
		int len = word.length();
		TrieNode current = root;
		for(int i = 0; i < len; i++){
			String index = word.charAt(i) + "";
			TrieNode nextNode = current.children.get(index);
			if(nextNode == null){
				nextNode = new TrieNode();
				current.children.put(index, nextNode);
			}
			current = nextNode;
		}
		current.isEndOfWord = true;
	}
	
	boolean search(String word){
		int len = word.length();
		TrieNode current = root;
		for(int i = 0; i < len; i++){
			String index = word.charAt(i) + "";
			TrieNode nextNode = current.children.get(index);
			if(nextNode == null){
				return false;
			}
			current = nextNode;
		}
		return current.isEndOfWord;
	}
}
