package me.eting.server.core.service.user;

import me.eting.TestConfig;
import me.eting.common.domain.user.Device;
import me.eting.common.domain.user.Incognito;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testRegister() throws Exception {
        //유저 생성. tom.
        Device tom = new Device();
        tom.setUuid(UUID.randomUUID().toString());
        tom.setTs(new Date());
        tom.setOs("A");
        tom.setPushKey(RandomStringUtils.randomAscii(160));

        //등록.
        Incognito tomsIncognito = userService.register(tom);
        //확인.
        assertNotNull(tomsIncognito);
    }
}