/**
	 * Puts a value for a key in a given map
	 * 
	 * @param map the name of the map to put in the value
	 * @param key the key to store for the value
	 * @param value the value
	 */
public void put(String map, String key, int value) {
    this.put(map, key, Integer.toString(value));
}
