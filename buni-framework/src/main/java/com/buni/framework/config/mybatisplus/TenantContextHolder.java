package com.buni.framework.config.mybatisplus;

/**
 * @author zp.wei
 * @date 2024/6/21 10:51
 */
public class TenantContextHolder {
    /**
     * 当前租户编号
     */
    private static final ThreadLocal<Integer> TENANT_ID = new InheritableThreadLocal<>();

    /**
     * 是否忽略租户
     */
    private static final ThreadLocal<Boolean> IGNORE = new InheritableThreadLocal<>();


    /**
     * 获得租户编号。如果不存在，则抛出 NullPointerException 异常
     *
     * @return 租户编号
     */
    public static Integer getTenantId() {
        Integer tenantId = TENANT_ID.get();
        if (tenantId == null) {
            throw new NullPointerException("不存在租户编号");
        }

        return tenantId;
    }

    /**
     * 设置租户编号
     *
     * @param tenantId 租户编号
     */
    public static void setTenantId(Integer tenantId) {
        TENANT_ID.set(tenantId);
    }

    /**
     * 设置是否忽略租户
     *
     * @param ignore 是否忽略
     */
    public static void setIgnore(Boolean ignore) {
        IGNORE.set(ignore);
    }

    /**
     * 当前是否忽略租户
     *
     * @return 是否忽略
     */
    public static boolean isIgnore() {
        return Boolean.TRUE.equals(IGNORE.get());
    }

    /**
     * 清理上下文
     */
    public static void clear() {
        TENANT_ID.remove();
        IGNORE.remove();
    }


}
