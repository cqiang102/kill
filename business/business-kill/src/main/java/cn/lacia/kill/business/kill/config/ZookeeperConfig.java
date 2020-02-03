package cn.lacia.kill.business.kill.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 你是电脑
 * @create 2020/2/3 - 15:49
 */
@Configuration
public class ZookeeperConfig {

    @Value("${zk.host}")
    public String zkHost;
    @Value("${zk.namespace}")
    public String zkNamespace;
    @Bean
    public CuratorFramework curatorFramework(){
        CuratorFramework build = CuratorFrameworkFactory.builder()
                .connectString(zkHost)
                .namespace(zkNamespace)
                .retryPolicy(new RetryNTimes(5, 1000)).build();
        build.start();
        return build;
    }
}
