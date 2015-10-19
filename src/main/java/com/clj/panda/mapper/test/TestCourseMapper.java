package com.clj.panda.mapper.test;

import com.clj.panda.model.entity.test.TestCourse;
import com.clj.panda.model.entity.test.TestStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lao on 2015/9/28.
 */
@Repository("testCourseMapper")
public interface TestCourseMapper {
    /**
     * 添加
     * @param course
     * @return
     */
    int insertCourse(TestCourse course);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    TestStudent selectCourseById(@Param("id") String id);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteCourseById(@Param("id") String id);
}
