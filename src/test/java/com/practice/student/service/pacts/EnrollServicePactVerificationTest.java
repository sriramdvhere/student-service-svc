package com.practice.student.service.pacts;


import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.HttpsTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import com.practice.student.service.model.Student;
import com.practice.student.service.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Provider("student-service-svc")
@PactFolder("pacts")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnrollServicePactVerificationTest {

    @LocalServerPort
    int port;

    @Autowired
    private StudentRepository studentRepository;


    @BeforeEach
    void setUp(PactVerificationContext pactVerificationContext) {
        pactVerificationContext.setTarget(new HttpTestTarget("localhost", port));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void verifyPact(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("for non-existing student")
    void createStudent() {
        System.out.println("for non-existing student");
    }

    @State("existing student")
    void getStudent() {
        System.out.println("data setup for existing student");
        Student student = new Student();
        student.setName("John Doe");
        student.setAge(23);
        student.setEmail("john@example.com");
        student.setGovtId("IND001");
        studentRepository.save(student);
    }
}
