package cn.com.sise.config;


import cn.com.sise.pojo.Person;
import cn.com.sise.pojo.Role;
import cn.com.sise.service.PersonService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Resource
    public PersonService personService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person=personService.getUser(username);
        List<Role> authorities=personService.getAuthority(username);
        List<SimpleGrantedAuthority> collect = authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getRole())).collect(Collectors.toList());
        if(person!=null){
            UserDetails userDetails=new User(person.getAccount(),person.getPassword(),collect);
            return userDetails;
        }else {
            throw new UsernameNotFoundException("用户名不存在！");
        }
  }
}
