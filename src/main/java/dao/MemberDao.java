package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Member;

public class MemberDao {

    private String jdbcUrl;
    private String username;
    private String password;

    // 생성자를 통해 Dao에 필요한 필드변수를 꽂아준다.
    public MemberDao(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;

        try {
            // 처음 객체가 생성될 때 드라이브를 로딩한다.
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Member> selectList() throws Exception {

        try (
                // mysqldb에 접속
                Connection con = DriverManager.getConnection(jdbcUrl, username, password);
                PreparedStatement stmt = con.prepareStatement("select mid, email from member");
                ResultSet rs = stmt.executeQuery();) {

            List<Member> list = new ArrayList<>();
            while (rs.next()) {
                // 이전 Servlet 버전에서 이 부분에서 바로 출력을 했으나
                // DAO에서는 객체에 값을 집어넣어 리턴한다.
                Member member = new Member();
                member.setId(rs.getString("mid"));
                member.setEmail(rs.getString("email"));
                list.add(member);
            }

            return list;
        }
    }
}
