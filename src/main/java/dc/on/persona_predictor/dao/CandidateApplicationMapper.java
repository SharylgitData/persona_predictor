package dc.on.persona_predictor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import dc.on.persona_predictor.constants.Constant;
import dc.on.persona_predictor.entity.CandidateApplication;
import org.springframework.jdbc.core.RowMapper;

public class CandidateApplicationMapper implements RowMapper<CandidateApplication> {
    @Override
    public CandidateApplication mapRow(ResultSet rs, int rowNum) throws SQLException {
        CandidateApplication job = new CandidateApplication();
        job.setJobId(rs.getInt(Constant.JOB_ID));
        job.setJobTitle(rs.getString(Constant.JOB_TITLE));
        job.setOrganization(rs.getString(Constant.ORG));
        job.setRank(rs.getInt(Constant.RANK));
        job.setImprovementArea(rs.getString(Constant.IMPROVEMENT_AREA));
        return job;
    }
}
