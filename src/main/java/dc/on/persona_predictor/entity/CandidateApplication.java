package dc.on.persona_predictor.entity;

public class CandidateApplication {

    private int jobId;
    private String jobTitle;
    private String organization;
    private int rank;
        private String improvementArea;

    // Getters and setters
    public int getJobId() {
        return jobId;
    }
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOrganization() {
        return organization;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getImprovementArea() {
        return improvementArea;
    }
    public void setImprovementArea(String improvementArea) {
        this.improvementArea = improvementArea;
    }
}
