package club.ragdollhouse.config;

import club.ragdollhouse.service.PermissionsCheckService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求过滤器
 */
@Component
public class UrlFilter implements Filter {

    @Autowired
    PermissionsCheckService permissionsCheckService;


    private Logger logger = Logger.getLogger(UrlFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("UrlFilter初始化执行。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //下转型为我们需要的HttpServletRequest，HttpServletResponse
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求url
        String uri = request.getRequestURI();

        //匹配到/EditBlogCheck请求的核实身份
        if (uri.equals("/gotoEdit")) {
            String nickname = null;
            String buildtoken = null;

            // 获取cookie信息
            Cookie[] cookies = request.getCookies();

            for (int i = 0; i < cookies.length; i++) {

                if (cookies[i].getName().equals("nickname")) {
                    nickname = cookies[i].getValue();
                }
                if (cookies[i].getName().equals("buildtoken")) {
                    buildtoken = cookies[i].getValue();
                }
            }

            if (nickname == null || buildtoken == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                logger.info("过滤器恶意访问重定向");
            } else {
                if (permissionsCheckService.loginStatu(nickname, buildtoken) == null) {
                    response.sendRedirect(request.getContextPath() + "/login");
                    logger.info("过滤器恶意访问重定向");
                } else {
                    if (!permissionsCheckService.editblogcheck(nickname).equals("SYS_ADMIN")) {
                        response.sendRedirect(request.getContextPath()+"/nopermission");
                    }
                }
            }
        }
        //别的url全部放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("UrlFilter销毁时执行。。。");
    }
}
