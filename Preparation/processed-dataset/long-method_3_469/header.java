void method0() { 
/**
	 * The file to read from.
	 */
private File file;
public static final String[] _tokenNames = { "<0>", "EOF", "<2>", "NULL_TREE_LOOKAHEAD", "FILE", "VARIABLE_DEFS", "MODIFIERS", "ARRAY_DECLARATOR", "TYPE", "EXTENDS_CLAUSE", "OBJBLOCK", "IMPLEMENTS_CLAUSE", "CTOR_DEF", "METHOD_DEF", "INSTANCE_INIT", "VARIABLE_DEF", "ARRAY_INIT", "PARAMETERS", "PARAMETER_DEF", "SLIST", "TYPE_STAT", "EXPRESSION_STAT", "LABELED_STAT", "EMPTY_STAT", "CASE_GROUP", "FOR_INIT", "FOR_CONDITION", "FOR_ITERATOR", "ELIST", "CONCAT_ASSIGN", "CONCATENATION", "UNARY_MINUS", "UNARY_PLUS", "TYPECAST", "INDEX_OP", "METHOD_CALL", "CONSTRUCTOR_CALL", "POST_INC", "POST_DEC", "PAREN_EXPR", "\"package\"", "SEMI", "\"import\"", "IDENT", "DOT", "STAR", "\"public\"", "\"private\"", "\"protected\"", "\"static\"", "\"final\"", "\"synchronized\"", "\"volatile\"", "\"transient\"", "\"native\"", "\"abstract\"", "\"strictfp\"", "\"class\"", "\"extends\"", "\"implements\"", "COMMA", "\"interface\"", "LCURLY", "RCURLY", "LPAREN", "RPAREN", "\"throws\"", "LBRACK", "RBRACK", "\"void\"", "\"boolean\"", "\"byte\"", "\"char\"", "\"short\"", "\"int\"", "\"float\"", "\"long\"", "\"double\"", "ASSIGN", "COLON", "\"if\"", "\"else\"", "\"for\"", "\"while\"", "\"do\"", "\"break\"", "\"continue\"", "\"return\"", "\"switch\"", "\"throw\"", "\"assert\"", "\"case\"", "\"default\"", "\"try\"", "\"finally\"", "\"catch\"", "PLUS_ASSIGN", "MINUS_ASSIGN", "STAR_ASSIGN", "DIV_ASSIGN", "MOD_ASSIGN", "SR_ASSIGN", "BSR_ASSIGN", "SL_ASSIGN", "BAND_ASSIGN", "BXOR_ASSIGN", "BOR_ASSIGN", "QUESTION", "LOR", "LAND", "BOR", "BXOR", "BAND", "NOT_EQUAL", "EQUAL", "LT", "GT", "LE", "GE", "\"instanceof\"", "SL", "SR", "BSR", "PLUS", "MINUS", "DIV", "MOD", "INC", "DEC", "BNOT", "LNOT", "\"this\"", "\"super\"", "\"true\"", "\"false\"", "\"null\"", "\"new\"", "NUM_INT", "CHAR_LITERAL", "STRING_LITERAL", "NUM_FLOAT", "\"const\"", "\"goto\"", "WS", "SL_COMMENT", "ML_COMMENT", "ESC", "HEX_DIGIT", "EXPONENT", "FLOAT_SUFFIX" };
public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());
public static final BitSet _tokenSet_20 = new BitSet(mk_tokenSet_20());
public static final BitSet _tokenSet_21 = new BitSet(mk_tokenSet_21());
public static final BitSet _tokenSet_22 = new BitSet(mk_tokenSet_22());
public static final BitSet _tokenSet_23 = new BitSet(mk_tokenSet_23());
public static final BitSet _tokenSet_24 = new BitSet(mk_tokenSet_24());
public static final BitSet _tokenSet_25 = new BitSet(mk_tokenSet_25());
public static final BitSet _tokenSet_26 = new BitSet(mk_tokenSet_26());
public static final BitSet _tokenSet_27 = new BitSet(mk_tokenSet_27());
public static final BitSet _tokenSet_28 = new BitSet(mk_tokenSet_28());
public static final BitSet _tokenSet_29 = new BitSet(mk_tokenSet_29());
public static final BitSet _tokenSet_30 = new BitSet(mk_tokenSet_30());
public static final BitSet _tokenSet_31 = new BitSet(mk_tokenSet_31());
public static final BitSet _tokenSet_32 = new BitSet(mk_tokenSet_32());
public static final BitSet _tokenSet_33 = new BitSet(mk_tokenSet_33());
public static final BitSet _tokenSet_34 = new BitSet(mk_tokenSet_34());
public static final BitSet _tokenSet_35 = new BitSet(mk_tokenSet_35());
public static final BitSet _tokenSet_36 = new BitSet(mk_tokenSet_36());
public static final BitSet _tokenSet_37 = new BitSet(mk_tokenSet_37());
public static final BitSet _tokenSet_38 = new BitSet(mk_tokenSet_38());
public static final BitSet _tokenSet_39 = new BitSet(mk_tokenSet_39());
public static final BitSet _tokenSet_40 = new BitSet(mk_tokenSet_40());
public static final BitSet _tokenSet_41 = new BitSet(mk_tokenSet_41());
public static final BitSet _tokenSet_42 = new BitSet(mk_tokenSet_42());
public static final BitSet _tokenSet_43 = new BitSet(mk_tokenSet_43());
public static final BitSet _tokenSet_44 = new BitSet(mk_tokenSet_44());
public static final BitSet _tokenSet_45 = new BitSet(mk_tokenSet_45());
public static final BitSet _tokenSet_46 = new BitSet(mk_tokenSet_46());
public static final BitSet _tokenSet_47 = new BitSet(mk_tokenSet_47());
public static final BitSet _tokenSet_48 = new BitSet(mk_tokenSet_48());
public static final BitSet _tokenSet_49 = new BitSet(mk_tokenSet_49());
public static final BitSet _tokenSet_50 = new BitSet(mk_tokenSet_50());
public static final BitSet _tokenSet_51 = new BitSet(mk_tokenSet_51());
public static final BitSet _tokenSet_52 = new BitSet(mk_tokenSet_52());
public static final BitSet _tokenSet_53 = new BitSet(mk_tokenSet_53());
public static final BitSet _tokenSet_54 = new BitSet(mk_tokenSet_54());
public static final BitSet _tokenSet_55 = new BitSet(mk_tokenSet_55());
public static final BitSet _tokenSet_56 = new BitSet(mk_tokenSet_56());
public static final BitSet _tokenSet_57 = new BitSet(mk_tokenSet_57());
public static final BitSet _tokenSet_58 = new BitSet(mk_tokenSet_58());
public static final BitSet _tokenSet_59 = new BitSet(mk_tokenSet_59());
public static final BitSet _tokenSet_60 = new BitSet(mk_tokenSet_60());
public static final BitSet _tokenSet_61 = new BitSet(mk_tokenSet_61());
public static final BitSet _tokenSet_62 = new BitSet(mk_tokenSet_62());
public static final BitSet _tokenSet_63 = new BitSet(mk_tokenSet_63());
}
