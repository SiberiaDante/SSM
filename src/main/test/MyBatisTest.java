import com.alibaba.fastjson.JSON;
import com.dante.ssm.bean.User;
import com.dante.ssm.service.IUserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MyBatisTest {
    private static Logger logger = Logger.getLogger(MyBatisTest.class);
    @Resource
    private IUserService userService = null;

    @Test
    public void test1() {
        User user = userService.getUserById(1);
        logger.info("值：" + user.getUsername());
        logger.info(JSON.toJSONString(user));
    }
}
