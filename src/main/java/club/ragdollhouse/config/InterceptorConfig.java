package club.ragdollhouse.config;

import club.ragdollhouse.util.InterceptorUV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器程序配置类
 */
@SpringBootConfiguration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private InterceptorUV interceptorUV;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(interceptorUV).addPathPatterns("/**");
    }
}
