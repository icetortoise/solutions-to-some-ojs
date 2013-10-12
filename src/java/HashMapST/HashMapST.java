package HashMapST;

import java.util.Random;

//a hashmap impl using a universal hashing method and open address(double hashing) techniques

public class HashMapST<K, V>{
    // pick a prime number so that offset part of double hashing will be relatively prime to the size.
    private int size = 1031;
    // a large prime for the use of universal hashing
    private long p = 15485863;
    private Object[] keys;
    private Object[] data;
    private long a, b = 0;

    @SuppressWarnings({"unchecked"})
    public HashMapST(){
	a = 1 + (long)(Math.random()*(p-1));
	b = (long)(Math.random()*p);
	data = new Object[size];
	keys = new Object[size];
    }

    private int hashValue(K key){
	int hc = key.hashCode();
	// System.out.println("hashcode " + hc);
	// System.out.println("a " + a);
	// System.out.println("b " +b);
	// System.out.println("p " +p);
	// System.out.println("hashval " + (hc * a + b)%p);
	long hashval = (hc * a + b)%p;
	if(hashval < 0)
	    hashval += p;
	return (int)(hashval)%size;
    }

    private int offsetHash(K key){
	int offhash = key.hashCode() % size;
	return offhash < 0 ? offhash + size : offhash;
    }

    private int availableSlot(K key){
	int h = hashValue(key);
	int offset = offsetHash(key);
	int i = 0;
	for(; i < size; i++){
	    int idx = (h+offset*i) % size;
	    if(keys[idx] == null || keys[idx].equals(key)){
		System.out.println("probing: " + i);
		return idx;
	    }
	}
	System.out.println("probing: " + i);
	return -1;//table full
    }

    private int matchSlot(K key){
	int h = hashValue(key);
	int offset = offsetHash(key);
	int i = 0;
	for(; i < size; i++){
	    int idx = (h+offset*i) % size;
	    if(keys[idx] == null){
		System.out.println("probing: " + i);
		return -1;
	    }else if(key.equals(keys[idx])){
		System.out.println("probing: " + i);
		return idx;
	    }
	}
	System.out.println("probing: " + i);
	return -1;
    }
    
    @SuppressWarnings({"unchecked"})
    public V get(K key){
	assert key != null;
	int h = matchSlot(key);
	System.out.println("getting from " + h);	
	if(h < 0){
	    throw new RuntimeException("Table full");
	}else{
	    return (V)data[h];
	}
    }

    @SuppressWarnings({"unchecked"})
    public boolean put(K key, V val){
	assert val!=null;
	assert key!=null;
	int h = availableSlot(key);
	System.out.println("putting to " + h);
	if(h < 0){
	    return false;
	}else{
	    keys[h] = key;
	    data[h] = val;
	    return true;
	}
    }
    
    private static int c = 0;
    private static HashMapST table = new HashMapST<String, Integer>();
    public static void test(){
	Random rng = new Random();
	for(int i = 0; i <= 1031; i++){
	    String k = generateString(rng, "abcdefghijklmnopqrstuvwxyz0123456789", 20);
	    table.put(k, ++c);
	    System.out.println(table.get(k));
	}

    }

    public static String generateString(Random rng, String characters, int length)
    {
	char[] text = new char[length];
	for (int i = 0; i < length; i++)
	    {
		text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	return new String(text);
    }
}
