package com.clj.panda.mapper.test;

import com.clj.panda.model.entity.test.TestDate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lao on 2015/9/28.
 */
@Repository("testDateMapper")
public interface TestDateMapper {
    /**
     * 添加
     * @param date
     * @return
     */
    int insertDate(TestDate date);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    TestDate selectDateById(@Param("id") int id);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteDateById(@Param("id") int id);
}
