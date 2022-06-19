package cn.com.sise.repositoy;

import cn.com.sise.pojo.Person;
import cn.com.sise.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorityRepositorys extends JpaRepository<Role,Integer> {
    @Query("select r from tb_user u,tb_role r where u.roleId=r.roleId AND u.account=?1")
    List<Role> findAuthorityByUsername(String Username);
}
