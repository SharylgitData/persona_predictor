package dc.on.persona_predictor.constants;

public class Constant {
    /**URLs**/
    public static final String ROOT_URL = "/resume";
    public static final String SIGN_UP =  "/signUp";
    public static final String LOGIN = "/login";
    public static final String JOB_POST_REQUEST = "/sendJobRequest";
    public static final String DECIDE_POSTING = "/decidePosting/{id}/{status}";
    public static final String STORE_PERSONALITY = "/savePersonality/{dominantPersonality}/{emailId}";
    public static final String JOB_APPLICATION = "/apply";
    public static final String CHECK_IF_ALREADY_APPLIED = "/ifapplied/{jobid}/{emailId}";
    public static final String GET_APPLIED_USERS   = "/getAppliedUsers/{jobid}";
    public static final String GET_CANDIDATE_APPLICATION   = "/candidateapp/{emailId}";
    /**URLs**/



    /**Api**/
    public static final String CROSS_ORIGIN = "https://cv-predictor.vercel.app/";
    /**Api**/



    /**General**/
    public static final String ORG = "organization";
    public static final String JOB_SEEKER = "jobSeeker";
    public static final String YES = "Y";
    public static final String ASTRICK = "*";
    public static final String IMPROVEMENT_AREA ="improvement_area";
    public static final String RANK = "rank";
    public static final String BEAN_POSTGRESQL              = "PostgreSQL";
    public static final String BEAN_JDBCTEMPLATE            = "jdbcTemplate";
    public static final String ACTION            = "action";
    public static final String COMPANY_NAME            ="companyname";
    public static final String PATH_MATCH_ALL = "/**";
    public static final String APPROVED = "Approved";
    public static final String PENDING = "Pending";
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String EMPTY_STRING = "";
    public static final String NO = "N";
    public static final String NAME = "name";
    public static final String REASON = "reason";
    public static final String AREA_OF_IMPROVEMENT= "area_of_improvement";
    public static final String FILE = "file";
    public static final String PERSONALITY = "personality";
    /**General**/


    /**HTTP methods**/
    public static final String HTTP_GET     = "GET";
    public static final String HTTP_POST    = "POST";
    public static final String HTTP_PUT     = "PUT";
    public static final String HTTP_DELETE  = "DELETE";
    public static final String HTTP_OPTIONS = "OPTIONS";
    /**HTTP methods**/

    /**Parameters**/
    public static final String PARAM_ID                          = "id";
    public static final String PARAM_STATUS                      = "status";
    public static final String DOMINANT_PERSONALITY        = "dominantPersonality";
    public static final String EMAIL_ID                    = "emailId";
    public static final String JOBID                       = "jobid";
    public static final String RESUME                      = "resume";
    public static final String JOB_ID                      = "job_id";
    public static final String EMAIL_ID_UNDERSCORE         = "email_id";
    public static final String JOB_DESCRIPTION             = "job_description";
    public static final String  JOB_TITLE                   = "job_title";
    /**Parameters**/


    /**SWAGGER API**/
    public static final String  API_TITLE                   = "My API";
    public static final String  VERSION                   = "1.0";
    public static final String  DESCRIPTION                   = "Spring Boot Swagger API documentation";
    /**SWAGGER API**/

    /**Environment Property keys **/
    public static final String PROP_ENV                     = "${env}";
    public static final String PROP_DATASOURCE_DRIVER       = "${spring.datasource.driver}";
    public static final String PROP_DATASOURCE_URL          = "${spring.datasource.url}";

    /**Environment Property keys **/

    /** Database Constants**/
    public static final String SCHEMA_PERSONA_PREDICTOR = "persona_predictor";
    public static final String TABLE_USER_INFO          = "user_info";



}
