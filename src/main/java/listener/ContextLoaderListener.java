package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.MemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("config/mybatis-config.xml"));
            MemberDao memberDao = new MemberDao(sqlSessionFactory);

            ServletContext sc = sce.getServletContext();
            sc.setAttribute("memberDao", memberDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
