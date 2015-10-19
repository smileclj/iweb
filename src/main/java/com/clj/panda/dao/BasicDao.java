//package com.clj.panda.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.SortedMap;
//import java.util.SortedSet;
//import java.util.TreeSet;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class BasicDao<T> {
//    protected static final Logger log = LoggerFactory.getLogger(BasicDao.class.getSimpleName());
//
//    /**
//     * @param tbName
//     *            表名
//     * @param map
//     *            参数
//     * */
//    protected void replace(String tbName, SortedMap<String, Object> map) {
//        initConnenction();
//        synchronized (atomicInteger) {
//            StringBuffer sbKey = new StringBuffer();
//            StringBuffer sbValue = new StringBuffer();
//            List<Object> values = new ArrayList<Object>();
//            for (Map.Entry<String, Object> entry : map.entrySet()) {
//                sbKey.append(entry.getKey()).append(",");
//                sbValue.append("?,");
//                values.add(entry.getValue());
//            }
//            StringBuffer sql = new StringBuffer();
//            sql.append("replace into ").append(tbName).append("(").append(sbKey.substring(0, sbKey.length() - 1))
//                    .append(") values (").append(sbValue.subSequence(0, sbValue.length() - 1)).append(")");
//            PreparedStatement pstmt = null;
//            try {
//                pstmt = conn.prepareStatement(sql.toString());
//                for (int i = 1; i <= values.size(); i++) {
//                    pstmt.setObject(i, values.get(i - 1));
//                }
//                pstmt.executeUpdate();
//            }
//            catch (SQLException e) {
//                e.printStackTrace();
//            }
//            log.info(sql.toString());
//        }
//    }
//
//    /**
//     * @param tbName
//     *            表名
//     * @param map
//     *            参数
//     */
//    protected void insert(String tbName, SortedMap<String, Object> map) {
//        initConnenction();
//        synchronized (atomicInteger) {
//            StringBuffer sbKey = new StringBuffer();
//            StringBuffer sbValue = new StringBuffer();
//            List<Object> values = new ArrayList<Object>();
//            for (Map.Entry<String, Object> entry : map.entrySet()) {
//                sbKey.append(entry.getKey()).append(",");
//                sbValue.append("?,");
//                values.add(entry.getValue());
//            }
//            StringBuffer sql = new StringBuffer();
//            sql.append("insert into ").append(tbName).append("(").append(sbKey.substring(0, sbKey.length() - 1))
//                    .append(") values (").append(sbValue.subSequence(0, sbValue.length() - 1)).append(")");
//            try {
//                pstmt = conn.prepareStatement(sql.toString());
//                for (int i = 1; i <= values.size(); i++) {
//                    pstmt.setObject(i, values.get(i - 1));
//                }
//                pstmt.executeUpdate();
//            }
//            catch (SQLException e) {
//                e.printStackTrace();
//            }
//            log.info(sql.toString());
//        }
//    }
//
//    /**
//     * @param tbName
//     *            表名
//     * @param map
//     *            参数
//     * */
//    public void executeBantch(String sql, List<SortedMap<String, Object>> values) {
//        initConnenction();
//        synchronized (atomicInteger) {
//            PreparedStatement pstmt = null;
//            try {
//                pstmt = conn.prepareStatement(sql);
//                for (SortedMap<String, Object> map : values) {
//                    int index = 1;
//                    for (Object value : map.values()) {
//                        pstmt.setObject(index, value);
//                        index++;
//                    }
//                    pstmt.addBatch();
//                }
//                pstmt.executeBatch();
//            }
//            catch (SQLException e) {
//                e.printStackTrace();
//            }
//            log.info(sql.toString());
//        }
//
//    }
//
//    /**
//     * 获取SQL
//     *
//     * @param tbName
//     *            表明
//     * @param map
//     *            参数
//     * */
//    public String getReplacePreparedSQL(String tbName, String... columnNames) {
//        SortedSet<String> columns = new TreeSet<String>(Arrays.asList(columnNames));
//        StringBuffer sbKey = new StringBuffer();
//        StringBuffer sbValue = new StringBuffer();
//        for (String column : columns) {
//            sbKey.append(column).append(",");
//            sbValue.append("?").append(",");
//        }
//        StringBuffer sql = new StringBuffer();
//        sql.append("replace into ").append(tbName).append("(").append(sbKey.substring(0, sbKey.length() - 1))
//                .append(") values (").append(sbValue.subSequence(0, sbValue.length() - 1)).append(")");
//        return sql.toString();
//    }
//
//    /**
//     * @param tbName
//     *            表名
//     * @param whereSql
//     *            查询条件 例如 a=? and b=?
//     * @param map
//     *            参数
//     * @param param
//     *            数组参数
//     * */
//    protected void update(String tbName, String whereSql, SortedMap<String, Object> map, Object... param) {
//        initConnenction();
//        synchronized (atomicInteger) {
//            StringBuffer sbSet = new StringBuffer();
//            List<Object> values = new ArrayList<Object>();
//            for (Map.Entry<String, Object> entry : map.entrySet()) {
//                sbSet.append(entry.getKey()).append("= ? ").append(",");
//                values.add(entry.getValue());
//                log.info("key= " + entry.getKey() + " and value= " + entry.getValue());
//            }
//            StringBuffer sql = new StringBuffer();
//            sql.append("update  ").append(tbName).append(" set ").append(sbSet.subSequence(0, sbSet.length() - 1))
//                    .append(" where ").append(whereSql);
//            log.info(sql.toString());
//            PreparedStatement pstmt = null;
//            try {
//                pstmt = conn.prepareStatement(sql.toString());
//                for (int i = 1; i <= values.size(); i++) {
//                    pstmt.setObject(i, values.get(i - 1));
//                }
//                for (int i = values.size() + 1; i <= values.size() + param.length; i++) {
//                    pstmt.setObject(i, param[i - values.size() - 1]);
//                }
//                pstmt.executeUpdate();
//            }
//            catch (SQLException e) {
//                e.printStackTrace();
//            }
//            log.info(sql.toString());
//        }
//    }
//
//    /**
//     * 查询
//     *
//     * @param sql
//     *            sql语句
//     * @param params
//     *            参数列表
//     * @return
//     */
//    public List<Map<String, Object>> query(String sql, Object... params) {
//        log.debug(sql);
//        getConnection();
//        Map<String, Object> map = null;
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        PreparedStatement pstmt = null;
//        try {
//            pstmt = conn.prepareStatement(sql);
//            bindParameters(pstmt, params);
//            ResultSet rs = pstmt.executeQuery();
//            ResultSetMetaData meta = rs.getMetaData();
//            if (rs != null) {
//                while (rs.next()) {
//                    map = new HashMap<String, Object>();
//                    for (int i = 0; i < meta.getColumnCount(); i++) {
//                        map.put(meta.getColumnName(i + 1), rs.getObject(i + 1));
//                    }
//                    list.add(map);
//                }
//            }
//            return list;
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//        }
//        return null;
//    }
//
//    /**
//     * 根据实体查询
//     *
//     * @param sql
//     *            sql 语句
//     * @param entity
//     *            实体类
//     * @param param
//     *            数组参数
//     * */
//    @SuppressWarnings("unchecked")
//    public List<T> query(String sql, Entity<?> entity, Object... params) {
//        log.info(sql);
//        getConnection();
//        List<T> entities = new ArrayList<T>();
//        PreparedStatement pstmt = null;
//        try {
//            pstmt = conn.prepareStatement(sql);
//            this.bindParameters(pstmt, params);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs != null) {
//                while (rs.next()) {
//                    entities.add((T) entity.parseObject(rs));
//                }
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//        }
//        finally {
//            try {
//                if (pstmt != null && pstmt.isClosed()) {
//                    pstmt.close();
//                }
//            }
//            catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return entities;
//    }
//
//    /**
//     * 根据实体查询
//     *
//     * @param sql
//     *            sql 语句
//     * @param entity
//     *            实体类
//     * @param param
//     *            数组参数
//     * */
//    @SuppressWarnings("unchecked")
//    public T queryOne(String sql, Entity<?> entity, Object... params) {
//        log.info(sql);
//        getConnection();
//        List<T> entities = new ArrayList<T>();
//        PreparedStatement pstmt = null;
//        try {
//            pstmt = conn.prepareStatement(sql);
//            bindParameters(pstmt, params);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs != null) {
//                while (rs.next()) {
//                    entities.add((T) entity.parseObject(rs));
//                }
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//        }
//        finally {
//            try {
//                if (pstmt != null && pstmt.isClosed()) {
//                    pstmt.close();
//                }
//            }
//            catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if (entities.size() > 1) {
//            log.error("查询得到条数为" + entities.size());
//            return entities.get(0);
//        }
//        else if (entities.size() == 0) {
//            log.info("数据库没有数据");
//            return null;
//        }
//        else {
//            return entities.get(0);
//        }
//    }
//
//    /**
//     * 返回List列表类型
//     *
//     * @param sql
//     *            sql 语句
//     * @param param
//     *            数组参数
//     * */
//    public List<Object> queryList(String sql, Object... params) {
//        log.debug(sql);
//        getConnection();
//        List<Object> list = new ArrayList<Object>();
//        PreparedStatement pstmt = null;
//        try {
//            pstmt = conn.prepareStatement(sql);
//            bindParameters(pstmt, params);
//            ResultSet rs = pstmt.executeQuery();
//            ResultSetMetaData meta = rs.getMetaData();
//            if (rs != null) {
//                while (rs.next()) {
//                    for (int i = 0; i < meta.getColumnCount(); i++) {
//                        list.add(rs.getObject(i + 1));
//                    }
//                }
//            }
//            return list;
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//        }
//        finally {
//            try {
//                if (pstmt != null && pstmt.isClosed()) {
//                    pstmt.close();
//                }
//            }
//            catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 只返回一个值的查询，返回结果集中的第一个数据，假如结果集为空则返回null
//     *
//     * @param sql
//     *            sql语句
//     * @param params
//     *            参数列表
//     * @return
//     */
//    public Object getUniqueResult(String sql, Object... params) {
//        log.debug(sql);
//        initConnenction();
//        PreparedStatement pstmt = null;
//        try {
//            pstmt = conn.prepareStatement(sql);
//            bindParameters(pstmt, params);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                return rs.getObject(1);
//            }
//            else {
//                return null;
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//        }
//        return null;
//    }
//
//    /**
//     * 执行update
//     *
//     * @param sql
//     *            sql语句
//     * @param params
//     *            参数列表，必须和sql语句中的?数量一致
//     * @return
//     */
//    public int update(String sql, Object... params) {
//        log.debug(sql);
//        initConnenction();
//        synchronized (atomicInteger) {
//            PreparedStatement pstmt = null;
//            try {
//                pstmt = conn.prepareStatement(sql);
//                bindParameters(pstmt, params);
//                return pstmt.executeUpdate();
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//                log.error(e.getMessage());
//            }
//        }
//        return 0;
//    }
//
//    /**
//     *
//     * @param sql
//     *            sql语句
//     * @param params
//     *            参数列表，必须和sql语句中的?数量一致
//     * @return
//     */
//    public int delete(String sql, Object... params) {
//        log.debug(sql);
//        initConnenction();
//        synchronized (atomicInteger) {
//            PreparedStatement pstmt = null;
//            try {
//                pstmt = conn.prepareStatement(sql);
//                bindParameters(pstmt, params);
//                return pstmt.executeUpdate();
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//                log.error(e.getMessage());
//            }
//        }
//        return 0;
//    }
//
//    /**
//     * 查询
//     *
//     * @param sql
//     *            sql语句
//     * @param params
//     *            参数列表
//     * @return
//     */
//    public int delete(String sql) {
//        log.debug(sql);
//        initConnenction();
//        synchronized (atomicInteger) {
//            PreparedStatement pstmt = null;
//            try {
//                pstmt = conn.prepareStatement(sql);
//                return pstmt.executeUpdate();
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//                log.error(e.getMessage());
//            }
//        }
//        return 0;
//    }
//
//    /** 批量删除 */
//    public void deleteAll(List<String> sqls) {
//        initConnenction();
//        synchronized (atomicInteger) {
//            try {
//                stmt = conn.createStatement();
//                conn.setAutoCommit(false);
//                for (String sql : sqls) {
//                    log.info(sql);
//                    stmt.addBatch(sql);
//                }
//                stmt.executeBatch();
//                pstmt.executeUpdate();
//            }
//            catch (Exception e) {
//                try {
//                    conn.rollback();
//                }
//                catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//                e.printStackTrace();
//                log.error(e.getMessage());
//            }
//            finally {
//                try {
//                    if (!stmt.isClosed()) {
//                        stmt.close();
//                    }
//                }
//                catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /** 批量更新 */
//    public void updateAll(List<String> sqls) {
//        initConnenction();
//        synchronized (atomicInteger) {
//            try {
//                stmt = conn.createStatement();
//                conn.setAutoCommit(false);
//                for (String sql : sqls) {
//                    log.info(sql);
//                    stmt.addBatch(sql);
//                }
//                stmt.executeBatch();
//                pstmt.executeUpdate();
//            }
//            catch (Exception e) {
//                try {
//                    conn.rollback();
//                }
//                catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//                e.printStackTrace();
//                log.error(e.getMessage());
//            }
//            finally {
//                try {
//                    if (!stmt.isClosed()) {
//                        stmt.close();
//                    }
//                }
//                catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /** 执行所有的sql */
//    public void execAll(List<String> sqls) {
//        initConnenction();
//        synchronized (atomicInteger) {
//            try {
//                stmt = conn.createStatement();
//                conn.setAutoCommit(false);
//                for (String sql : sqls) {
//                    log.info(sql);
//                    stmt.addBatch(sql);
//                }
//                stmt.executeBatch();
//                pstmt.executeUpdate();
//            }
//            catch (Exception e) {
//                try {
//                    conn.rollback();
//                }
//                catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//                e.printStackTrace();
//                log.error(e.getMessage());
//            }
//            finally {
//                try {
//                    if (!stmt.isClosed()) {
//                        stmt.close();
//                    }
//                }
//                catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * 绑定参数
//     *
//     * @param params
//     * @throws SQLException
//     */
//
//    private void bindParameters(PreparedStatement pstmt, Object... params) throws SQLException {
//        if (params != null) {
//            for (int i = 0; i < params.length; i++) {
//                pstmt.setObject(i + 1, params[i]);
//            }
//        }
//    }
//
//}
