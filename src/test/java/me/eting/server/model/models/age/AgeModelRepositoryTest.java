package me.eting.server.model.models.age;

import me.eting.server.core.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
public class AgeModelRepositoryTest {

    @Autowired
    AgeModelRepository ageModelRepository;
    
    @Test
    public void test(){
        assertNotNull("");
    }
    
}