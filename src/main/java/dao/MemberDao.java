package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Member;

public class MemberDao {

    SqlSessionFactory sqlSessionFactory;

    public MemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<Member> selectList() throws Exception {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("member.selectList"); // member는 namespace를 selectlist는 id를 나타낸다
        }
    }
}
