package servlets;

import businesslogic.AccountService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 642202
 */
public class ResetPasswordServlet extends HttpServlet {    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        if (uuid != null && !uuid.equals("")){
            request.setAttribute("uuid", uuid);
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
        }
        else {
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        }
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action != null && !action.equals("")){
            if (action.equals("sendEmail")){
                String email = request.getParameter("email");
                String url = request.getRequestURL().toString();
                String path = getServletContext().getRealPath("/WEB-INF");
                new AccountService().resetPassword(email, url, path);
                request.setAttribute("message", "email sent");
                getServletContext().getRequestDispatcher("/WEB-INF/resetMessage.jsp").forward(request, response);
            }
            else if (action.equals("resetPassword")){
                String uuid = request.getParameter("uuid");
                String password = request.getParameter("password");
                AccountService as = new AccountService();
                if (as.changePassword(uuid, password)){
                    request.setAttribute("message", "password changed");
                }
                else {
                    request.setAttribute("message", "password not changed");
                }
                getServletContext().getRequestDispatcher("/WEB-INF/resetMessage.jsp").forward(request, response);
            }
            else {
                response.sendRedirect("reset");
            }
        }
        else {
            response.sendRedirect("reset");
        }
    }

}
