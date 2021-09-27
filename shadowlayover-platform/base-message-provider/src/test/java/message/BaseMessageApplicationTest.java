package message;

import com.shadowlayover.common.db.generator.CodeGenerator;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author CarlosChou
 * @date 2021/7/27 1:00
 * @desc 启动类
 */
@SpringBootTest
class BaseMessageApplicationTest {
    
    
    public static void main(String[] args) {
        CodeGenerator.generatorCode("zhouzhanqi", "jdbc:mysql://192.168.1.98:13306/shadowlayover_message", "root", "123456");
    }
}
