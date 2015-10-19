package com.clj.panda.mapper.test;

import com.clj.panda.model.entity.test.TestStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lao on 2015/9/28.
 */
@Repository("testStudentMapper")
public interface TestStudentMapper {
    /**
     * 添加
     * @param student
     * @return
     */
    int insertStudent(TestStudent student);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    TestStudent selectStudentById(@Param("id") String id);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteStudentById(@Param("id") String id);
}
