package org.example.springsecurityproject.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SampleServiceTest {

//    if sample service depends on repository then use
    //if SampleRepo sampleRepo;
//    @InjectMocks  //annotation for injecting dependencies
    @InjectMocks
    SampleService sampleService;

    @BeforeAll  // class level setup
    static void  beforeAllMethod(){
        System.out.println("before all");
    }


    @BeforeEach  //method level setup
    void beforeEachMethod(){
        System.out.println("before each ");
    }
    @Test //test setup
    void sampleMethodGreetsUser(){
        System.out.println("first test");

//       example of mockito returning db enquiry   so not db call hits db fake repo directly return value
        //User fakeuser=new User(1,"yash");
//       Mockito.when(userRepository.findById(1)).thenReturn(fakeuser);//if using repository and calling save then what was added could be come returning from here


//        assertEquals("yash",sampleService.sampleMethod("yash"));
    }

    @Test
    void TestMethod2(){
        System.out.println("method 2 of test");
    }
        @AfterEach
                void afterEachMethod(){
            System.out.println("after each");
        }

        @AfterAll //class level cleanup
                static void afterAllMethod(){
            System.out.println("after all ");

    }
}
