package cn.h3c.user.servlet;

import cn.h3c.user.domain.User;
import cn.h3c.user.service.UserException;
import cn.h3c.user.service.UserService;
import cn.itcast.commons.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 注册RegistServlet的功能
         * 1，将jsp传入的表单数据进行封装成User 对象，这里是javaBean的功能，上对应表单，下对应数据库。
         * 2，调用Service的注册接口
         *      3，返回成功：向页面返回注册成功
         *      4，返回失败，向页面返回注册失败信息
         */
        //如何从表单数据里拿数据呢？
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        request.getParameterMap();

        UserService userService = new UserService();
        //一剑封装：无论表单的数据有多少，一个方法全部搞定：前提是map（）要和User的键名称相等。
        User form = CommonUtils.toBean(request.getParameterMap(), User.class);

        /*
        拿到封装好的数据后不必自己做注册的主流程
        调用Service的注册方法
         */
        try {
            userService.regist(form);
            response.getWriter().print("注册成功");
        }catch (UserException e){
            //如果报错，说明注册失败，这里的失败信息可以从宜昌对象中获取
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/user/regist.jsp").forward(request,response);
        }

    }

}
