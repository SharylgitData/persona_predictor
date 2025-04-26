//package dc.on.persona_predictor.service;
//
//import dc.on.persona_predictor.dao.UserDao;
//import dc.on.persona_predictor.entity.ResumeInfo;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//@ExtendWith(MockitoExtension.class)
//class AsyncServiceTest {
//
//    @Mock
//    private UserDao userDao;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @InjectMocks
//    private AsyncService asyncService;
//
//    @BeforeEach
//    void setUp() {
//
//        //ReflectionTestUtils.setField(asyncService, "restTemplate", restTemplate);
//    }
//
//    @Test
//    void testGetResumeDetails_Success() throws Exception {
//        MockMultipartFile file = new MockMultipartFile(
//            "test.pdf",
//            "test.pdf",
//            "application/pdf",
//            "test content".getBytes()
//        );
//        String jobId = "1";
//        String emailId = "sharyl@ms.com";
//        String jobDescription = "Software Engineer";
//        String jobTitle = "Java Developer";
//
//        when(userDao.getPersonality(emailId)).thenReturn("openness");
//
//        Map<String, Object> responseBody = new HashMap<>();
//        responseBody.put("name", "John Doe");
//        responseBody.put("reason", "Good fit");
//        responseBody.put("rank", "8");
//        responseBody.put("area_of_improvement", "cloud architecture");
//        responseBody.put("area_of_improvement", "cloud architecture");
//
//        ResponseEntity<Map<String, Object>> mockResponse = new ResponseEntity<>(responseBody, HttpStatus.OK);
//        when(restTemplate.exchange(
//            anyString(),
//            any(),
//            any(),
//            any(ParameterizedTypeReference.class)
//        )).thenReturn(mockResponse);
//
//
//        CompletableFuture<String> result = asyncService.getResumeDetails(file, jobId, emailId, jobDescription, jobTitle);
//
//        assertNotNull(result);
//        assertEquals("Completed processing", result.get());
//
//        verify(userDao).getPersonality(emailId);
//        verify(userDao).saveResumeInfo(any(ResumeInfo.class));
//    }
//
//    @Test
//    void testGetResumeDetails_Error() throws Exception {
//        // Prepare test data
//        MockMultipartFile file = new MockMultipartFile(
//            "test.pdf",
//            "test.pdf",
//            "application/pdf",
//            "test content".getBytes()
//        );
//        String jobId = "1";
//        String emailId = "test@example.com";
//        String jobDescription = "Software Engineer";
//        String jobTitle = "Senior Developer";
//
//        when(userDao.getPersonality(emailId)).thenThrow(new RuntimeException("Database error"));
//
//        CompletableFuture<String> result = asyncService.getResumeDetails(file, jobId, emailId, jobDescription, jobTitle);
//
//        assertNotNull(result);
//        assertTrue(result.get().startsWith("Error while calling or storing the resume entities"));
//    }
//}
