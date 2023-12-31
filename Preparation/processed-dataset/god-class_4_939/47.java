public boolean isPunishable(Object o) {
    // null is not punish-able  
    if (o == null || srv.storeList.size() == 0)
        return false;
    // get the punish-object if there is one  
    Hashtable<?, ?> st = (Hashtable<?, ?>) srv.storeList.clone();
    for (Enumeration<?> e = st.keys(); e.hasMoreElements(); ) {
        Object key = e.nextElement();
        ActionstoreObject l = (ActionstoreObject) key;
        if (l.usr.equals(o) && l.equalsActionState(IActionStates.ISPUNISHABLE)) {
            if (l.time < System.currentTimeMillis()) {
                storeList.remove(key);
                return false;
            }
            return true;
        }
        // check if the punish is still fresh  
        if (l.time < System.currentTimeMillis()) {
            storeList.remove(key);
        }
    }
    // get the punishable-object if there is one  
    return false;
}
