package dc.on.persona_predictor.dao;

import dc.on.persona_predictor.constants.Constant;
import dc.on.persona_predictor.entity.CandidateApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CandidateApplicationMapperTest {

    @Test
    void testMapRow() throws SQLException {
        // Arrange
        CandidateApplicationMapper mapper = new CandidateApplicationMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        
        // Setup mock data
        int expectedJobId = 1;
        String expectedJobTitle = "Software Engineer";
        String expectedOrg = "Test Organization";
        int expectedRank = 5;
        String expectedImprovementArea = "Communication Skills";

        when(resultSet.getInt(Constant.JOB_ID)).thenReturn(expectedJobId);
        when(resultSet.getString(Constant.JOB_TITLE)).thenReturn(expectedJobTitle);
        when(resultSet.getString(Constant.ORG)).thenReturn(expectedOrg);
        when(resultSet.getInt(Constant.RANK)).thenReturn(expectedRank);
        when(resultSet.getString(Constant.IMPROVEMENT_AREA)).thenReturn(expectedImprovementArea);

        // Act
        CandidateApplication result = mapper.mapRow(resultSet, 1);

        // Assert
        assertEquals(expectedJobId, result.getJobId());
        assertEquals(expectedJobTitle, result.getJobTitle());
        assertEquals(expectedOrg, result.getOrganization());
        assertEquals(expectedRank, result.getRank());
        assertEquals(expectedImprovementArea, result.getImprovementArea());
    }

    @Test
    void testMapRowWithNullValues() throws SQLException {
        // Arrange
        CandidateApplicationMapper mapper = new CandidateApplicationMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(resultSet.getInt(Constant.JOB_ID)).thenReturn(0);
        when(resultSet.getString(Constant.JOB_TITLE)).thenReturn(null);
        when(resultSet.getString(Constant.ORG)).thenReturn(null);
        when(resultSet.getInt(Constant.RANK)).thenReturn(0);
        when(resultSet.getString(Constant.IMPROVEMENT_AREA)).thenReturn(null);

        // Act
        CandidateApplication result = mapper.mapRow(resultSet, 1);

        // Assert
        assertEquals(0, result.getJobId());
        assertEquals(null, result.getJobTitle());
        assertEquals(null, result.getOrganization());
        assertEquals(0, result.getRank());
        assertEquals(null, result.getImprovementArea());
    }
}
