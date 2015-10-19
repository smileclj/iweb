package com.clj.panda.dao.impl;

import com.clj.panda.dao.StudentDao;
import com.clj.panda.model.entity.test.TestStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wana on 2015/10/14.
 */
@Repository
public class StudentDaoImpl implements StudentDao{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String TABLE = "test_student";

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertStudent(TestStudent student) throws Exception{
        Map<String,Object> map = getAvaiableAttr(student);
        StringBuilder sql = new StringBuilder();
        StringBuilder names = new StringBuilder();
        StringBuilder values = new StringBuilder();
        List<Object> args = new ArrayList<Object>(map.size());
        sql.append("insert into ").append(TABLE).append(" (");
        for(Map.Entry<String,Object> entry:map.entrySet()){
            names.append(entry.getKey()).append(",");
            values.append("?,");
            args.add(entry.getValue());
        }
        sql.append(names.substring(0,names.length()-1)).append(") values (").append(values.substring(0,values.length()-1)).append(")");
        logger.info("sql==" + sql.toString());
        return jdbcTemplate.update(sql.toString(),args.toArray());
    }

    @Override
    public int deleteStudent(String id) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> findStudentById(String id) {
        return null;
    }

    @Override
    public int updateStudent(TestStudent student) {
        return 0;
    }

    private Map<String,Object> getAvaiableAttr(TestStudent student) throws Exception{
        Map<String,Object> result = new HashMap<String,Object>();
        Field[] fields = student.getClass().getDeclaredFields();
        for(int i = 0;i<fields.length;i++){
            Field f = fields[i];
            f.setAccessible(true);
            String name = f.getName();
            Object value = f.get(student);
            if(!name.equals("serialVersionUID") && value != null){
                result.put(name,value);
            }
        }
        return result;
    }
}
