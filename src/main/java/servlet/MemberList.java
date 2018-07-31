package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import domain.Member;

// @는 annotation을 뜻한다, annotation은 서버에 알려주는 역할을 한다.
@WebServlet("/member/list") // "/member/list" 라는 요청이 URL로 오면 이 클래스를 실행한다.
public class MemberList extends HttpServlet {
    // HttpServlet 클래스를 상속받으면 해당 클래스가 servlet으로 동작하게 한다
    // 상속받은 클래스에서 doGet 메서드를 오버라이드 해서 get요청을 받아서 처리한다.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // characterset 을 utf8로 지정

        PrintWriter out = response.getWriter(); // 화면에 직접 html 코드를 출력하는 객체이다
        out.println("<!DOCTYPE html>"); // 여기서 작성된 태그는 버퍼에 저장되었다가
        out.println("<html>"); // 한번에 전송된다.
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>멤버 목록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>멤버 목록</h1>");
        out.println("<p><a href='add.html'>새회원</a></p>");
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("    <th>아이디</th><th>이메일</th>");
        out.println("</tr>");

        try {
            // memberDao 객체를 만들며 생성자에 주소와 아이디 비밀번호를 꽂아준다.
            ServletContext sc = this.getServletContext(); //ServletContext에서 MemberDao를 받는다
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
            List<Member> list = memberDao.selectList();
            // memberDao로부터 받은 객체 리스트를 출력한다.
            for (Member member : list) {
                out.println("<tr>");
                out.printf("    <td><a href='view?id=%s'>%s</a></td><td>%s</td>\n", member.getId(), member.getId(),
                        member.getEmail());
                out.println("</tr>");
            }
        } catch (Exception e) {
            out.println("<p>목록 가져오기 실패!</p>"); // 에러처리
            e.printStackTrace(out);
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
