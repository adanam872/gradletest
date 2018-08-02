package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import domain.Member;

@WebServlet("/member/list")
public class MemberList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            List<Member> list = memberDao.selectList();

            request.setAttribute("list", list);
            RequestDispatcher rd = request.getRequestDispatcher("/view/list.jsp");
            rd.include(request, response); // include 명령은 데이터를 보낸 후 제어권을 다시 가져온다
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
