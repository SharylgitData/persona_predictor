package dc.on.persona_predictor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dc.on.persona_predictor.entity.CandidateApplication;
import org.springframework.jdbc.core.RowMapper;

public class CandidateApplicationMapper implements RowMapper<CandidateApplication> {
    @Override
    public CandidateApplication mapRow(ResultSet rs, int rowNum) throws SQLException {
        CandidateApplication job = new CandidateApplication();
        job.setJobId(rs.getInt("job_id"));
        job.setJobTitle(rs.getString("job_title"));
        job.setOrganization(rs.getString("organization"));
        job.setRank(rs.getInt("rank"));
        job.setImprovementArea(rs.getString("improvement_area"));
        return job;
    }
}
