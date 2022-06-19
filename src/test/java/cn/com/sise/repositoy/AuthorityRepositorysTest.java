package cn.com.sise.repositoy;

import cn.com.sise.pojo.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author fgcy
 * @Date 2022/6/16
 */
@SpringBootTest
class AuthorityRepositorysTest {

    @Autowired
    private AuthorityRepositorys authorityRepositorys;
    @Test
    void findAuthorityByUsername() {
        List<Role> root = authorityRepositorys.findAuthorityByUsername("root");
        root.forEach(System.out::println);
    }
}
