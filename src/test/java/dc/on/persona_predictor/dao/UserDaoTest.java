package dc.on.persona_predictor.dao;

import dc.on.persona_predictor.entity.UserInfo;
import dc.on.persona_predictor.entity.JobDetails;
import dc.on.persona_predictor.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDaoTest {

    @Mock
    private UserDataRepository userData;

    @Mock
    private JobsRepository jobsRepository;

    @Mock
    private PersonalityTestRepository personalityTestRepository;

    @Mock
    private Resume_Info_Repository resumeInfoRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserDao userDao;

    @BeforeEach
    void setUp() {
        // MockitoExtension handles initialization
    }

    String companyName = "Morgan Stanley";
    String emailId = "myemail@morganstanley.com";
    String password = "secretPassword";
    String wrongpass = "wrongpass";
    @Test
    void testIsValideUser_Success() {
        UserInfo mockUser = new UserInfo();
        mockUser.id = 1;
        when(userData.validateUser(emailId, password)).thenReturn(mockUser);

        UserInfo result = userDao.isValideUser(emailId, password);
        assertNotNull(result);
        assertEquals(1, result.id);
        verify(userData).validateUser(emailId, password);
    }

    @Test
    void testIsValideUser_InvalidCredentials() {
        when(userData.validateUser(emailId, wrongpass)).thenReturn(null);

        UserInfo result = userDao.isValideUser(emailId, wrongpass);
        assertNull(result);
        verify(userData).validateUser(emailId, wrongpass);
    }

    @Test
    void testGetOrgListedJobs_ReturnsJobs() {
        List<JobDetails> jobs = List.of(new JobDetails(), new JobDetails());
        when(jobsRepository.getOrgData(companyName)).thenReturn(jobs);

        List<JobDetails> result = userDao.getOrgListedJobs(companyName);
        assertEquals(2, result.size());
        assertEquals(jobs, result);
        verify(jobsRepository).getOrgData(companyName);
    }

    @Test
    void testGetOrgListedJobs_OnException() {
        when(jobsRepository.getOrgData(companyName)).thenThrow(new RuntimeException("DB error"));

        List<JobDetails> result = userDao.getOrgListedJobs(companyName);
        assertTrue(result.isEmpty());
        verify(jobsRepository).getOrgData(companyName);
    }

    @Test
    void testGetListedJobs_ReturnsJobs() {
        List<JobDetails> jobs = List.of(new JobDetails());
        when(jobsRepository.getListedJobs()).thenReturn(jobs);

        List<JobDetails> result = userDao.getListedJobs();
        assertEquals(jobs, result);
        verify(jobsRepository).getListedJobs();
    }

    @Test
    void testGetAllJobs_ReturnsRequestedPostings() {
        List<JobDetails> jobs = List.of(new JobDetails());
        when(jobsRepository.getRequestedPostings()).thenReturn(jobs);

        List<JobDetails> result = userDao.getAllJobs();
        assertEquals(jobs, result);
        verify(jobsRepository).getRequestedPostings();
    }

    @Test
    void testCheckApplication_TrueWhenExists() {
        when(resumeInfoRepository.ifAlreadyFiled("J123", emailId)).thenReturn(5L);

        Boolean applied = userDao.checkApplication("J123", emailId);
        assertTrue(applied);
        verify(resumeInfoRepository).ifAlreadyFiled("J123", emailId);
    }

    @Test
    void testCheckApplication_FalseWhenNone() {
        when(resumeInfoRepository.ifAlreadyFiled("J123", emailId)).thenReturn(0L);

        Boolean applied = userDao.checkApplication("J123", emailId);
        assertFalse(applied);
        verify(resumeInfoRepository).ifAlreadyFiled("J123", emailId);
    }

    @Test
    void testGetPersonality_ReturnsValue() {
        when(personalityTestRepository.getPersonality(emailId)).thenReturn("INTJ");

        String personality = userDao.getPersonality(emailId);
        assertEquals("INTJ", personality);
        verify(personalityTestRepository).getPersonality(emailId);
    }

    @Test
    void testGetPersonality_OnException() {
        when(personalityTestRepository.getPersonality(emailId)).thenThrow(new RuntimeException("Error"));

        String personality = userDao.getPersonality(emailId);
        assertNull(personality);
        verify(personalityTestRepository).getPersonality(emailId);
    }
}
