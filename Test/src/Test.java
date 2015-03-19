

import java.util.HashMap;





public class Test {
	
	static class Pair {
		int x;
		int y;
	}

	HashMap<Pair, Integer> relations = new HashMap<Pair, Integer>();
	
	Pair p = new Pair();
	
	p.x = personNumber1;
	p.y = personNumber2;
	
	if(!relations.containsKey(p)){			
		relations.put(p, -1);
	}
}
