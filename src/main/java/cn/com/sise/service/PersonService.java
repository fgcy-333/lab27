package cn.com.sise.service;

import cn.com.sise.pojo.Person;
import cn.com.sise.pojo.Role;
import cn.com.sise.repositoy.AuthorityRepositorys;
import cn.com.sise.repositoy.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AuthorityRepositorys authorityRepository;
    @Autowired
    private PersonRepository personRepository;

    public Person getUser(String username){
        Person result=null;
        Object obj=redisTemplate.opsForValue().get("user_"+username);
        if(obj!=null){
            result=(Person)obj;
        }else {
            result=personRepository.finUserByUsername(username);
            if(result!=null){
                redisTemplate.opsForValue().set("user_"+username,result);
            }
        }
        return result;
    }
    public List<Role> getAuthority(String username){
        List<Role> AuthorityList=null;
        Object obj=redisTemplate.opsForValue().get("authorities_"+username);
        if(obj!=null){
            AuthorityList= (List<Role>) obj;
        }else {
            AuthorityList=authorityRepository.findAuthorityByUsername(username);
            if(AuthorityList!=null){
                redisTemplate.opsForValue().set("authorities_"+username,AuthorityList);
            }
        }

        return AuthorityList;
    }
}
