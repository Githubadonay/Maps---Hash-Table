public class Entry {
    
    private String k;  // key
    private String v;  // value

    public Entry(String key, String value) {
      k = key;
      v = value;
    }

    // Accessors
    public String getKey() { return k; }
    public String getValue() { return v; }

    // Mutators
    protected void setKey(String key) { k = key; }
    protected void setValue(String value) { v = value; }
  
  
}