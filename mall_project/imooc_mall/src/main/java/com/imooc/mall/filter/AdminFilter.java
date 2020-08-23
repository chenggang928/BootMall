package com.imooc.mall.filter;

import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.common.Constant;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.pojo.User;
import com.imooc.mall.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 管理员校验过滤器
 */
public class AdminFilter implements Filter {
    @Autowired
    UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpSession session = request1.getSession();
        User currentUser = (User) session.getAttribute(Constant.IMOOC_MALL_USER);
        if (currentUser == null) {
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) response).getWriter();
            out.write("{\n" +
                    " \"status\":1007,\n" +
                    " \"msg\":\"NEED_LOGIN\",\n" +
                    " \"data\":null\n" +
                    " }");
            out.flush();
            out.close();
            return;
        }
        //校验是否是管理员
        boolean adminRole = userService.checkAdmin(currentUser);
        if (adminRole) {
            chain.doFilter(request, response);
        } else {
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) response).getWriter();
            out.write("{\n" +
                    " \"status\":1009,\n" +
                    " \"msg\":\"NEED_ADMIN\",\n" +
                    " \"data\":null\n" +
                    " }");
            out.flush();
            out.close();
        }
    }

    @Override
    public void destroy() {

    }
}
