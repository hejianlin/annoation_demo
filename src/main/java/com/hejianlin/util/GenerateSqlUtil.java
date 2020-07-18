package com.hejianlin.util;

import com.hejianlin.annoation.Column;
import com.hejianlin.annoation.SqlState;

import java.lang.reflect.Field;

public class GenerateSqlUtil {

    /**
     * sql语句生成器：select 字段名 from 表名 where 条件
     * @param tableObject
     * @return
     * @throws Exception
     */
    public static String query(Object tableObject) throws Exception{
        //select 语句
        StringBuffer sb = new StringBuffer();
        sb.append("select");

        StringBuffer whereSb = new StringBuffer();
        whereSb.append(" 1=1 ");

        //获取类类型
        Class<?> clazz = tableObject.getClass();
        //获取表名注解
        SqlState sqlState = clazz.getAnnotation(SqlState.class);
        if(sqlState == null){
            throw new RuntimeException("表名映射失败！");
        }
        //获取属性
        Field[] declaredFields = clazz.getDeclaredFields();

        //获取表名
        String tableName = sqlState.value();
        //获取select语句
        String selectSqlStr = generateSelectSql(declaredFields);
        //获取where语句
        String whereSqlStr = generateWhereSql(declaredFields, tableObject);
        return selectSqlStr+" from "+tableName+" "+whereSqlStr+" ;";
    }

    /**
     * 生成select语句
     * @param declaredFields
     * @return
     */
    private static String generateSelectSql(Field[] declaredFields){

        StringBuffer sb = new StringBuffer();
        sb.append("select");
        for (Field field : declaredFields) {
            //获取字段名注解
            Column column = field.getAnnotation(Column.class);
            if(column == null){
                throw new RuntimeException("字段名映射失败:"+field.getName());
            }
            String columnName = column.value();
            if(columnName.equals("")){
                //默认使用属性名
                sb.append(" "+field.getName()+",");
            }else{
                //使用字段名
                sb.append(" "+columnName+",");
            }
        }
        //去除末尾的逗号
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    /**
     * 生成where语句
     * @param declaredFields
     * @param tableObject
     * @return
     * @throws Exception
     */
    private static String generateWhereSql(Field[] declaredFields,Object tableObject) throws Exception {

        StringBuffer sb = new StringBuffer();
        sb.append("where 1=1");
        for (Field field : declaredFields) {
            //获取字段名注解
            Column column = field.getAnnotation(Column.class);
            if(column == null){
                throw new RuntimeException("字段名映射失败:"+field.getName());
            }

            String columnName = column.value();
            String name = field.getName();
            field.setAccessible(true);
            Object columnValue = field.get(tableObject);
            if(columnValue == null ){
                continue;
            }

            //如果是字符串类，则添加单引号
            if(columnValue instanceof String){
                columnValue="'" + columnValue + "'";
            }

            if(columnName.equals("")){
                //默认使用属性名
                sb.append(" and "+name+" = "+columnValue);
            }else{
                //使用字段名
                sb.append(" and "+columnName+" = "+columnValue);
            }
        }
        return sb.toString();
    }

}
