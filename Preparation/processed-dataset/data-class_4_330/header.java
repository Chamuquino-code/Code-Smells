void method0() { 
private static final Logger logger = Logger.getLogger(Player.class.getName());
// A magic constant to denote that a players gold is not tracked. 
public static final int GOLD_NOT_ACCOUNTED = Integer.MIN_VALUE;
public static final int SCORE_SETTLEMENT_DESTROYED = -40;
public static final String ASSIGN_SETTLEMENT_NAME = "";
/**
     * The XML tag name for the set of founding fathers.
     */
private static final String FOUNDING_FATHER_TAG = "foundingFathers";
/**
     * The XML tag name for the set of offered founding fathers.
     */
private static final String OFFERED_FATHER_TAG = "offeredFathers";
/**
     * The XML tag name for the stance array.
     */
private static final String STANCE_TAG = "stance";
/**
     * The XML tag name for the tension array.
     */
private static final String TENSION_TAG = "tension";
/**
     * The name of the unknown enemy.
     */
public static final String UNKNOWN_ENEMY = "unknown enemy";
/**
     * Only used by AI - stores the tension levels, 0-1000 with 1000 maximum
     * hostility.
     */
protected java.util.Map<Player, Tension> tension = new HashMap<Player, Tension>();
// TODO: move this to AIPlayer 
/**
     * Stores the stance towards the other players. One of: WAR, CEASE_FIRE,
     * PEACE and ALLIANCE.
     */
protected java.util.Map<String, Stance> stance = new HashMap<String, Stance>();
/**
     * The name of this player. This defaults to the user name in case of a
     * human player and the rulerName of the NationType in case of an AI player.
     */
protected String name;
/** The name of this player as an independent nation. */
protected String independentNationName;
/** The NationType of this player. */
protected NationType nationType;
/** The nation ID of this player, e.g. "model.nation.dutch". */
protected String nationID;
/** The name this player uses for the New World. */
protected String newLandName = null;
/** Is this player an admin? */
protected boolean admin;
/** The current score of this player. */
protected int score;
/** The amount of gold this player owns. */
protected int gold;
/**
     * The number of immigration points. Immigration points are an
     * abstract game concept. They are generated by but are not
     * identical to crosses.
     */
protected int immigration;
/**
     * The number of liberty points. Liberty points are an
     * abstract game concept. They are generated by but are not
     * identical to bells.
     */
protected int liberty;
/**
     * The number of liberty bells produced towards the intervention
     * force.
     */
protected int interventionBells;
/** The market for Europe. */
protected Market market;
/** The European port/location for this player. */
protected Europe europe;
/** The monarch for this player. */
protected Monarch monarch;
protected boolean ready;
/** True if this is an AI player. */
protected boolean ai;
/** True if player has been attacked by privateers. */
protected boolean attackedByPrivateers = false;
/** SoL from last turn. */
protected int oldSoL;
/** Is this player dead? */
protected boolean dead = false;
/** The founding fathers in this Player's congress. */
protected final Set<FoundingFather> allFathers = new HashSet<FoundingFather>();
/** Current founding father being recruited. */
protected FoundingFather currentFather;
/** The offered founding fathers. */
protected final List<FoundingFather> offeredFathers = new ArrayList<FoundingFather>();
/** The current tax rate for this player. */
protected int tax = 0;
protected PlayerType playerType;
protected int immigrationRequired = 12;
protected Location entryLocation;
/** The Units this player owns. */
protected final java.util.Map<String, Unit> units = new HashMap<String, Unit>();
/** The Settlements this player owns. */
protected final List<Settlement> settlements = new ArrayList<Settlement>();
/** Trade routes of this player. */
protected final List<TradeRoute> tradeRoutes = new ArrayList<TradeRoute>();
/** Model messages for this player. */
protected final List<ModelMessage> modelMessages = new ArrayList<ModelMessage>();
/** The history events occuring with this player. */
protected final List<HistoryEvent> history = new ArrayList<HistoryEvent>();
/** The last-sale data. */
protected HashMap<String, LastSale> lastSales = null;
/** Indices of largest used region name by type. */
protected final HashMap<String, Integer> nameIndex = new HashMap<String, Integer>();
// Temporary variables: 
// Tiles the player can see. 
// No access to canSeeTiles without taking canSeeLock. 
private boolean[][] canSeeTiles = null;
private final Object canSeeLock = new Object();
/**
     * Whether the player is bankrupt, i.e. unable to pay for the
     * maintenance of all buildings.
     */
private boolean bankrupt;
// Contains the abilities and modifiers of this type. 
protected final FeatureContainer featureContainer = new FeatureContainer();
// Maximum food consumption of unit types available to this player. 
private int maximumFoodConsumption = -1;
private final UnitIterator nextActiveUnitIterator = new UnitIterator(this, new ActivePredicate(this));
private final UnitIterator nextGoingToUnitIterator = new UnitIterator(this, new GoingToPredicate(this));
/**
     * The HighSeas is a Location that enables Units to travel between
     * the New World and one or several European Ports.
     */
protected HighSeas highSeas;
/**
     * A cache of settlement names, a capital for natives, and a fallback
     * settlement name prefix.
     * Does not need to be serialized.
     */
protected List<String> settlementNames = null;
protected String capitalName = null;
/**
     * A cache of ship names, including a fallback ship name prefix.
     * Does not need to be serialized.
     */
protected List<String> shipNames = null;
protected String shipFallback = null;
public static final Comparator<Player> playerComparator = new Comparator<Player>() {

    public int compare(Player player1, Player player2) {
        int counter1 = 0;
        int counter2 = 0;
        if (player1.isAdmin()) {
            counter1 += 8;
        }
        if (!player1.isAI()) {
            counter1 += 4;
        }
        if (player1.isEuropean()) {
            counter1 += 2;
        }
        if (player2.isAdmin()) {
            counter2 += 8;
        }
        if (!player2.isAI()) {
            counter2 += 4;
        }
        if (player2.isEuropean()) {
            counter2 += 2;
        }
        return counter2 - counter1;
    }
};
}
