package com.kun.hello;

import com.kun.hello.context.WebConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringJUnitWebConfig(WebConfig.class)
@TestPropertySource(properties = {"environment=local"})
abstract public class SpringBaseTest {

}
