import com.github.pagehelper.PageHelper;
import com.jiepi.dao.EmployeeMapper;
import com.jiepi.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

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

    @Test
    public void test2() throws Exception {
        SqlSession session = this.getSqlSession();
        EmployeeMapper employee = session.getMapper(EmployeeMapper.class);
        PageHelper.startPage(1,3);
        List<Employee> list = employee.getEmps();
        for (Employee emp:
             list) {
            System.out.println(emp);
        }
        session.close();

    }

    @Test
    public void test3() throws Exception {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        EmployeeMapper employee = session.getMapper(EmployeeMapper.class);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {

            employee.addEmp(new Employee(UUID.randomUUID().toString().substring(5).toString(),"dd","d"));
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        session.close();

    }
}
