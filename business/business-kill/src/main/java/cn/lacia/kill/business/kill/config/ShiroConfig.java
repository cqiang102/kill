package cn.lacia.kill.business.kill.config;

import cn.lacia.kill.business.kill.domain.User;
import cn.lacia.kill.business.kill.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author 你是电脑
 * @create 2020/2/3 - 18:52
 */
@Configuration
@Slf4j
public class ShiroConfig {

    @Resource
    private UserMapper userMapper;

    @Bean
    public CustomRealm customRealm(){
        return new CustomRealm();
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customRealm());
        return defaultSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(new HashMap<String, String>() {{
            put("/toLogin","anon");
            put("/**","anon");
            put("/item/list","anon");
            put("/item/kill","authc");
            put("/item/show/**","authc");
        }
        });
        return shiroFilterFactoryBean;
    }

    class CustomRealm extends AuthorizingRealm{

        /**
         * 授权
         * @param principalCollection
         * @return
         */
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            return null;
        }

        /**
         * 认证
         * @param authenticationToken
         * @return
         * @throws AuthenticationException
         */
        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
            String username = usernamePasswordToken.getUsername();
            char[] password = usernamePasswordToken.getPassword();
            log.info("username : {} password : {}",username,new String(password));
            User user = userMapper.selectOne(User.builder().userName(username).build());
            if (user == null ) {
//                账号不存在
                throw new UnknownAccountException("用户名不存在");
            }
            if (! Objects.equals(user.getActive().intValue(),1)){
                throw new DisabledAccountException("当前用户被禁用");
            }
            if (!Objects.equals(user.getPassword(),new String(password))){
                throw new IncorrectCredentialsException("密码错误");
            }
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getId(),password,getName());
            setSession("uid",user.getId());
            return simpleAuthenticationInfo;
        }

        private void setSession(String key ,Object val){
            Session session = SecurityUtils.getSubject().getSession();
            if (session != null) {
                session.setAttribute(key,val);
                session.setTimeout(30_000L);
            }
        }
    }
}
