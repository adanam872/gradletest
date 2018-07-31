package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.MemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener { //ServletContextListener를 상속한다.

    //contextInitialized 는 context가 시작될 때 수행되는 메서드이다.
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MemberDao memberDao = new MemberDao("jdbc:mysql://localhost:3306/testdb", "test", "1111");
        
        ServletContext sc = sce.getServletContext(); // ServletContext 객체를 받아서
        sc.setAttribute("memberDao", memberDao);     // memberDao 객체를 attribute에 추가해 준다.
    }
}
