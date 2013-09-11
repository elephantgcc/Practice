import java.util.*;

public class Solution {
	public String longestWord(String[] words) {
		HashSet<String> dict = new HashSet<String>();
		for (String w : words) {
			dict.add(w);
		}
		int max = -1;
		String result = null;
		for (String word : words) {
			if(isMadeUp(dict, word) && word.length() > max) {
				max = word.length();
				result = word;
			}
		}
		return result;
	}

	public boolean isMadeUp(HashSet<String> dict, String word) {
		for (int end = 0; end <= word.length() - 2; ++end) {
			String prefix = word.substring(0, end + 1);
			String suffix = word.substring(end + 1);
			if (dict.contains(prefix) && isMadeUp(dict, suffix)) {
				return true;
			}
		}
		return dict.contains(word);
	}

	public static void main(String args[]) {
		String[] words = new String[] {"test", "tester", "testertest", "testing"};
		System.out.println(new Solution().longestWord(words));
	}
}
