public static void reInit() {
    unicodeWarningGiven = false;
    generatedStates = 0;
    idCnt = 0;
    lohiByteCnt = 0;
    dummyStateIndex = -1;
    done = false;
    mark = null;
    stateDone = null;
    allStates = new ArrayList();
    indexedAllStates = new ArrayList();
    nonAsciiTableForMethod = new ArrayList();
    equivStatesTable = new Hashtable();
    allNextStates = new Hashtable();
    lohiByteTab = new Hashtable();
    stateNameForComposite = new Hashtable();
    compositeStateTable = new Hashtable();
    stateBlockTable = new Hashtable();
    stateSetsToFix = new Hashtable();
    allBitVectors = new ArrayList();
    tmpIndices = new int[512];
    allBits = "{\n   0xffffffffffffffffL, " + "0xffffffffffffffffL, " + "0xffffffffffffffffL, " + "0xffffffffffffffffL\n};";
    tableToDump = new Hashtable();
    orderedStateSet = new ArrayList();
    lastIndex = 0;
    //boilerPlateDumped = false; 
    jjCheckNAddStatesUnaryNeeded = false;
    jjCheckNAddStatesDualNeeded = false;
    kinds = null;
    statesForState = null;
}
