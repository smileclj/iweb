package com.clj.panda.dao;

import com.clj.panda.model.entity.test.TestStudent;

import java.util.List;
import java.util.Map;

/**
 * Created by wana on 2015/10/14.
 */
public interface StudentDao {
    /**
     * 添加学生
     * @param student
     * @return
     */
    int insertStudent(TestStudent student) throws Exception;

    /**
     * 删除学生
     * @param id
     * @return
     */
    int deleteStudent(String id);

    /**
     * 查找学生
     * @param id
     * @return
     */
    List<Map<String,Object>> findStudentById(String id);

    /**
     * 修改学生
     * @param student
     * @return
     */
    int updateStudent(TestStudent student);
}
