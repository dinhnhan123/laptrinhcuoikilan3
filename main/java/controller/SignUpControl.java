package controller;

import Dao.Dao;
import model.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "signup", value = "/signup")
public class SignUpControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("repass");
        if(!pass.equals(re_pass)){ // nếu pass khác với re_pass chuyển về trang login
            request.setAttribute("mess","Mật khẩu bạn vừa nhập lại không khớp, vui lòng kiểm tra lại");
            request.getRequestDispatcher("Login.jsp").forward(request,response);

        }else{
             Dao dao = new Dao();
            Account a = dao.CheckUserExist(user);
             if(a == null){
              dao.signUp(user,pass);
              response.sendRedirect("homeControl");

             }else {
                 response.sendRedirect("Login.jsp");
             }
        }

    }
}
