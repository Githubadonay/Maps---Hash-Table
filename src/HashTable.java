public class HashTable {
  private static int numElement;
  private Entry DEFUNCT = new Entry(null, null); // sentinel for deleted item
  private int collisions; // counts the total number of collisions during insertion
  private ArrayList<Entry> table;

  public HashTable(int capacity) {
    table = new ArrayList<Entry>(capacity);
    for (int i = 0; i < capacity; i++) {
      table.add(i, null);
    }
  }

  // Returns true if location is either empty or the ”defunct” sentinel
  private boolean isAvailable(int j) {
    return (table.get(j) == null || table.get(j) == DEFUNCT);
  }

  // compute and return hash code given the key
  private int computeHashCode(String key) {
    int sum = 0;
    for (int i = 0; i < key.length(); i++) {
      sum += (int) key.charAt(i);
    }
    return sum;
  }

  public int getCollisions() {
    return collisions;
  }

  // compute and return the compressed hash index
  private int compressHashCode(int sum) {
    return sum % table.capacity;
  }
  // return the index for a key.
  // this is the code that wouldnt let me pass past 17
  /*
   * public int findSlot(String key) {
   * int avail = -1;
   * int h = computeHashCode(key);
   * int j = compressHashCode(h);
   * 
   * do {
   * if (isAvailable(j)) {
   * if (avail == -1)
   * avail = j;
   * if (table.get(j) == null)
   * break;
   * } else if (table.get(j).getKey().equals(key))
   * return j;
   * else {
   * j = (j + 1) % table.capacity;
   * collisions++;
   * }
   * } while (j != compressHashCode(h));
   * 
   * return -(avail + 1);
   * }
   */

  // return the index for a key.
  public int findSlot(String key, boolean countCollisions) {
    int avail = -1;
    int h = computeHashCode(key);
    int j = compressHashCode(h);
    int startJ = j;
    int probes = 0;
    do {
      if (isAvailable(j)) {
        if (avail == -1)
          avail = j;
        if (table.get(j) == null)
          break;
      } else if (table.get(j).getKey().equals(key))
        return j;
      else {
        if (countCollisions) {
          collisions++;
        }
      }
      j = (j + 1) % table.capacity;
      probes++;
      if (probes >= table.capacity)
        break;
    } while (j != startJ);
    return -(avail + 1);
  }

  // return the value associated with key K
  public String tableSearch(String K) {
    int j = findSlot(K, false);
    if (j < 0)
      return null;
    else
      return table.get(j).getValue();
  }

  // inserts the value associated with key K
  public String tableInsert(String key, String value) {
    int j = findSlot(key, true);
    if (j >= 0) {
      table.get(j).setValue(value);
    } else {
      table.set(-(j + 1), new Entry(key, value));
      numElement++;
    }
    return value;
  }

  // remove the value associated with key K
  public String tableRemove(String K) {
    int j = findSlot(K, false);
    if (j < 0)
      return null;
    else {
      String answer = table.get(j).getValue();
      table.set(j, DEFUNCT);
      numElement--;
      return answer;
    }
  }

  // print the content of the table
  // print the content of the hashtable
  public String tablePrint() {
    System.out.println();
    String key = null;
    String value = null;
    String output = "";
    for (int i = 0; i < table.capacity; i++) {
      if (table.get(i) != null) {
        key = table.get(i).getKey();
        value = table.get(i).getValue();
        if (key == null)
          key = "DEFUNCT";
        if (value == null)
          value = "DEFUNCT";
      } else {
        key = null;
        value = null;
      }
      output += "\n" + "Row " + (i + 1) + " |Key: " + key + " | " + value;
    }
    return output;
  }

}
