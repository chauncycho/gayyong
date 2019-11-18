package my.app.gayyong;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
class GayyongApplicationTests {

    private final Logger log = LoggerFactory.getLogger(GayyongApplicationTests.class);

    @Test
    void demo1() {
        log.debug("这是Log测试");
    }

}
