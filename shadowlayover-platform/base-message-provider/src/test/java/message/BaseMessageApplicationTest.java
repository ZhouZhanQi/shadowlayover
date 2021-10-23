package message;

import cn.hutool.core.lang.generator.UUIDGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author CarlosChou
 * @date 2021/7/27 1:00
 * @desc 启动类
 */
@SpringBootTest
class BaseMessageApplicationTest {

    @Test
    public void testAAA() {
        System.out.println(new UUIDGenerator().next().replace("-", ""));
    }
}
