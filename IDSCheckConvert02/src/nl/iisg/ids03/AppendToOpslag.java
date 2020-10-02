package nl.iisg.ids03;

/**
 * Created by IntelliJ IDEA.
 * User: cro
 * Date: 10-11-11
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public class AppendToOpslag {

    public static final String SELECT_ALL_FROM_PDS_CIVILSTATUS = "SELECT a FROM PDS_CivilStatus a";
    public static final String SELECT_ALL_FROM_PDS_OCCUPATIONALTITLE = "SELECT a FROM PDS_OccupationalTitle a";
    public static final String SELECT_ALL_FROM_PDS_PARENTSANDCHILDREN = "SELECT a FROM PDS_ParentsAndChildren a";
    public static final String SELECT_ALL_FROM_PDS_PLACEOFDESTINATION = "SELECT a FROM PDS_PlaceOfDestination a";
    public static final String SELECT_ALL_FROM_PDS_PLACEOFORIGIN = "SELECT a FROM PDS_PlaceOfOrigin a";
    public static final String SELECT_ALL_FROM_PDS_RELATIONTOHEAD = "SELECT a FROM PDS_RelationToHead a";
    public static final String SELECT_ALL_FROM_PDS_ALLTOALL = "SELECT a FROM PDS_AllToAll a";
    public static final String SELECT_ALL_FROM_PDS_RELIGION = "SELECT a FROM PDS_Religion a";

    public static final String SELECT_ALL_FROM_B2_ST = "SELECT a FROM PersonStandardized a";
    public static final String SELECT_ALL_FROM_B3_ST = "SELECT a FROM PersonDynamicStandardized a";
    public static final String SELECT_ALL_FROM_B4_ST = "SELECT a FROM RegistrationStandardized a";
    public static final String SELECT_ALL_FROM_B6_ST = "SELECT a FROM RegistrationAddressStandardized a";

    public static final String SELECT_ALL_FROM_B2 = "SELECT a FROM Person a";
    public static final String SELECT_ALL_FROM_B3 = "SELECT a FROM PersonDynamic a";
    public static final String SELECT_ALL_FROM_B4 = "SELECT a FROM Registration a";
    public static final String SELECT_ALL_FROM_B6 = "SELECT a FROM RegistrationAddress a";

    public static final String SELECT_BY_IDNRS_FROM_B2_ST = "SELECT a FROM PersonStandardized a WHERE a.keyToRP IN ";
    
    public static final String SELECT_BY_IDNR_FROM_B2_ST = "SELECT a FROM PersonStandardized a WHERE a.keyToRP = '";
    public static final String SELECT_BY_IDNR_FROM_B3_ST = "SELECT a FROM PersonDynamicStandardized a WHERE a.keyToRP = '";
    public static final String SELECT_BY_IDNR_FROM_B4_ST = "SELECT a FROM RegistrationStandardized a WHERE a.keyToRP = '";
    public static final String SELECT_BY_IDNR_FROM_B6_ST = "SELECT a FROM RegistrationAddressStandardized a WHERE a.keyToRP = '";

    public static final String SELECT_BY_IDNRS_FROM_B2 = "SELECT a FROM Person a WHERE a.keyToRP IN ";
    public static final String SELECT_BY_IDNRS_FROM_B3 = "SELECT a FROM PersonDynamic a WHERE a.keyToRP IN ";
    public static final String SELECT_BY_IDNRS_FROM_B4 = "SELECT a FROM Registration a WHERE a.keyToRP IN ";
    public static final String SELECT_BY_IDNRS_FROM_B6 = "SELECT a FROM RegistrationAddress a WHERE a.keyToRP IN ";
    
    public static final String SELECT_BY_IDNR_FROM_B2 = "SELECT a FROM Person a WHERE a.keyToRP = '";
    public static final String SELECT_BY_IDNR_FROM_B3 = "SELECT a FROM PersonDynamic a WHERE a.keyToRP = '";
    public static final String SELECT_BY_IDNR_FROM_B4 = "SELECT a FROM Registration a WHERE a.keyToRP = '";
    public static final String SELECT_BY_IDNR_FROM_B6 = "SELECT a FROM RegistrationAddress a WHERE a.keyToRP = '";

    public static final String SELECT_IDNRS_FROM_B2 = "SELECT a.keyToRP FROM Person a";
    public static final String SELECT_WORKITEMS_FROM_B4 = "SELECT a.orderNumberI FROM Registration a GROUP BY a.orderNumberI ORDER BY a.orderNumberI";
}
