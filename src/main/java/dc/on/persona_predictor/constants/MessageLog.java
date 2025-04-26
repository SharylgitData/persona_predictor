package dc.on.persona_predictor.constants;

public class MessageLog {
    /**INFO LOGS**/
    public static final String USER_DATA_LOG         = "User data is {}";
    public static final String WELCOME_LOGIN_LOG     = "Welcome to login API point {}";
    public static final String REQUEST_TO_ADMIN_LOG  = "Request to send to admin is {}";
    public static final String RESULT_KEY            = "result";
    public static final String JOB_REQUEST_SUCCESS = "Your job request has been successfully sent to the Admin";
    public static final String MSG_STATUS_UPDATED_SUCCESSFULLY = "Status Updated successfully";
    public static final String MSG_STATUS_UPDATE_FAILED = "Status Update failed";
    public static final String MSG_PERSONALITY_SAVED_SUCCESSFULLY = "Personality of email‑Id‑ %s has been saved successfully";
    public static final String MSG_PERSONALITY_SAVE_FAILED = "Personality save failed for %s";
    public static final String SUCCESS_REGISTERED="User Successfully Registered";
    public static final String REGISTRATION_FAILED="Registration failed";
    public static final String MSG_RESUME_SUBMITTED = "Your Resume has been Submitted. Thank you for applying.";
    public static final String  USER_HAS_APPLIED_FIRST_TIME = "User is filing the application first time";
    public static final String  RESPONSE = "The response is {}";
    public static final String  COMPLETED_PROCESSING = "Completed processing";
   /** INFO LOGS **/


    /** ERROR LOGS **/
    public static final String ERROR_VALIDATING_USER =  "Error validating user: {}";

    public static final String ERROR_FETCHING_ORG_JOBS = "Error fetching org jobs: {}";

    public static final String ERROR_FETCHING_LISTED_JOBS = "Error fetching listed jobs: {}";

    public static final String ERROR_FETCHING_ALL_JOBS = "Error fetching all jobs: {}";
    public static final String ERROR_SOEMETHING_WENT_WRONG = "Something went wrong while saving the request";

    public static final String ERROR_SAVING_JOB_REQUEST = "Error saving job request: {} ";

    public static final String ERROR_UPDATING_JOB_STATUS = "Error updating job status: {} ";

    public static final String ERROR_SAVING_PERSONALITY = "Error saving personality: {}";

    public static final String ERROR_RETRIEVING_PERSONALITY =  "Error retrieving personality: {}";

    public static final String ERROR_SAVING_RESUME_INFO =  "Error saving resume info: {}";

    public static final String ERROR_CHECKING_APPLICATION = "Error finding if the user has applied for the job: {} ";

    public static final String ERROR_FINDING_APPLIED_CANDIDATES = "Error while finding the details of the applied candidates for the job id {} . The error is {}";
    public static final String MSG_EXCEPTION_SAVING_USER_PERSONALITY =  "Exception occurred while saving user's personality";
    public static final String MSG_EXCEPTION_UPDATING_JOB_STATUS =  "Exception occurred while updating job status";
    public static final String SIGNUP_FAILED = "Exception occurred while signing up user {}: {}";
    public static final String ALREADY_REGISTERED = "The User is already signed Up";
    public static final String USER_NAME_PASSWORD_INCORRECT = "User Name or Password is incorrect";
    public static final String SUCCESSFULLY_LOGGED_IN = "successfully logged in";
    public static final String  STATUS_UPDATED_SUCCESSFULLY = "Status Updated successfully";
    public static final String  USER_HAS_ALREADY_APPLIED = "User has already applied for this position";
    public static final String  ERROR_VERIFYING_USER = "Error verifying user {}";
    public static final String  ERROR_SERIALIZING_USER_ENTITIES = "Error serializing user entities {}";
    public static final String  ERROR_PROCESSING_RESUME = "Error processing resume {}";
    /** ERROR LOGS **/


    /** WARN LOGS **/
    public static final String  FAILED_TO_PARSE_INTEGER = "Failed to parse integer from: {}";
    /** WARN LOGS **/
}
