package life.majiang.community;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    UserMapper mapper;

    @Test
    void contextLoads() {
        final List<User> user = mapper.queryAll();
        for (User user1 : user) {
            System.out.println(user1);
        }

    }

}
