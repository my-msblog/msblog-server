package com.ms.blogserver.core.constant.contexts;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/4
 */

public interface RoleContexts {
    /**
     * 系统管理员
     */
    String SYSTEM_ADMIN = "SYSTEM_ADMIN";
    Long SYSTEM_ADMIN_ID = 1L;
    /**
     * 内容管理员
     */
    String CONTENT_MANAGER = "CONTENT_MANAGER";
    Long CONTENT_MANAGER_ID = 2L;
    /**
     * 访客
     */
    String VISITOR = "VISITOR";
    Long VISITOR_ID = 3L;
}

