package cn.com.sise.repositoy;

import cn.com.sise.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    @Query("select u from tb_user u where u.account=?1")
    public Person finUserByUsername (String Username);
}
