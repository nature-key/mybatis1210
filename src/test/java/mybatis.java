import com.jiepi.dao.EmployeeMapper;
import com.jiepi.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class mybatis {


    public SqlSession getSqlSession() throws Exception {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        return session;
    }

    @Test
    public void test1() throws Exception {
        SqlSession session = this.getSqlSession();
        EmployeeMapper employee = session.getMapper(EmployeeMapper.class);
        Employee employee1 = employee.getEmployeeById(1);
        try {
            System.out.println(employee1.getClass());
            System.out.println(employee1);
        } finally {
            session.close();
        }
    }
}
