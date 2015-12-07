package com.clj.panda.mapper.test;

import com.clj.panda.model.entity.test.TestStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    TestStudent selectStudentById(String id);

    /**
     * 根据年龄和姓名查询
     * @param age
     * @param name
     * @return
     */
    TestStudent selectStudentByNameAndAge(String name,int age);



    /**
     * 根据年龄查询
     * @param age
     * @return
     */
    List<TestStudent> selectStudentByAge(@Param("age") int age);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteStudentById(@Param("id") String id);

    /***
     * 根据主键修改年龄
     */
    int updateAgeStudentById(@Param("id") String id,@Param("age") int age);
}
