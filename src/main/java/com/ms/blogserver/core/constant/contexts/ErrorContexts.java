package com.ms.blogserver.core.constant.contexts;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/10
 */
public interface ErrorContexts {

    String UNKNOWN_ERR = "未知错误";

    String FILE_ERR = "文件读取异常";

    String NO_FILE = "文件不存在";

    String FILE_NO_DELETE = "文件无法删除，需要管理员权限或尝试关闭文件";

    String ID_IS_NULL = "传入id不能为空";

    String ADD_NEW_ACCOUNT_ERROR = "添加用户异常";

    String PARAMETER_LIST_IS_EMPTY = "参数列表为空";

    String INSTANCE_EXISTS = "实例已存在，无法初始化！";

    // database error

    String DATABASE_INNER_TABLE = "系统内部异常：数据库链表关系异常，请联系管理员";
}
