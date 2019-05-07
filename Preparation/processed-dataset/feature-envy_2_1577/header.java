void method0() { 
// Constants  
//////////////////////////////////  
/** The namespace for the configuration model is {@value} */
public static final String NS = "http://jena.hpl.hp.com/2003/04/schemagen#";
/** The default location of the configuration model is {@value} */
public static final String DEFAULT_CONFIG_URI = "file:schemagen.rdf";
/** The default marker string for denoting substitutions is {@value} */
public static final String DEFAULT_MARKER = "%";
/** Default template for writing out value declarations */
public static final String DEFAULT_TEMPLATE = "public static final %valclass% %valname% = m_model.%valcreator%( \"%valuri%\" );";
/** Default template for writing out individual declarations */
public static final String DEFAULT_INDIVIDUAL_TEMPLATE = "public static final %valclass% %valname% = m_model.%valcreator%( \"%valuri%\", %valtype% );";
/** Default template for writing out individual declarations for non-ontology vocabularies */
public static final String DEFAULT_RDFS_INDIVIDUAL_TEMPLATE = "public static final %valclass% %valname% = m_model.%valcreator%( \"%valuri%\" );";
/** Default template for the file header */
public static final String DEFAULT_HEADER_TEMPLATE = "/* CVS $" + "Id: $ */%nl%%package% %nl%%imports% %nl%/**%nl% * Vocabulary definitions from %sourceURI% %nl% * @author Auto-generated by schemagen on %date% %nl% */";
/** Default line length for comments before wrap */
public static final int COMMENT_LENGTH_LIMIT = 80;
/** List of Java reserved keywords, see <a href="http://java.sun.com/docs/books/tutorial/java/nutsandbolts/_keywords.html">this list</a>. */
public static final String[] JAVA_KEYWORDS = { "abstract", "continue", "for", "new", "switch", "assert", "default", "goto", "package", "synchronized", "boolean", "do", "if", "private", "this", "break", "double", "implements", "protected", "throw", "byte", "else", "import", "public", "throws", "case", "enum", "instanceof", "return", "transient", "catch", "extends", "int", "short", "try", "char", "final", "interface", "static", "void", "class", "finally", "long", "strictfp", "volatile", "const", "float", "native", "super", "while" };
// Static variables  
//////////////////////////////////  
private static List<String> KEYWORD_LIST;
// Instance variables  
//////////////////////////////////  
/** Schemagen options interface */
protected SchemagenOptions m_options;
/** The model that contains the input source */
protected OntModel m_source;
/** The output stream we write to */
protected PrintStream m_output;
/** Option definitions */
/** Stack of replacements to apply */
protected List<Replacement> m_replacements = new ArrayList<Replacement>();
/** Output file newline char - default is Unix, override with --dos */
protected String m_nl = "\n";
/** Size of indent step */
protected int m_indentStep = 4;
/** Set of names used so far */
protected Set<String> m_usedNames = new HashSet<String>();
/** Map from resources to java names */
protected Map<Resource, String> m_resourcesToNames = new HashMap<Resource, String>();
/** List of allowed namespace URI strings for admissible values */
protected List<String> m_includeURI = new ArrayList<String>();
}
