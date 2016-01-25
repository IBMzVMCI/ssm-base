package com.xxx.bean;

import java.util.*;

/**
 * 静态权限部分（模块权限部分，1-10000为系统保留权限模块，10000以上为用户自定义流程部分）
 * Date: 12-8-29 下午3:03
 */
public enum Module {
    IGNORE((byte) 0, ""),
    JOB((byte) 1, "职位管理"),
    RESUME((byte) 8, "简历管理"),
    TEMPLATE_PREVIEW((byte) 12, "简历模板预览"),
    DEADLINE((byte) 13, "申请截止日期"),
    SPACE((byte) 14, "场地管理"),
    TAG((byte) 15, "标签管理"),
    SCORE((byte) 16, "评分规则"),
    MAIL((byte) 17, "信件设置"),
    ORGANIZATIONAL_STRUCTURE((byte) 18, "组织架构管理"),
    USER((byte) 19, "用户管理"),
    ROLE((byte) 20, "角色管理"),
    //    LOG((byte) 21, "日志查询"),
    //    USER_PROFILE((byte) 22, "个人信息"),
//    DATA((byte) 23, "数据统计"),
    SCHEDULE((byte) 24, "宣讲会管理"),
    PAGE((byte) 25, "Page管理"),
    AUTO_FILTER((byte) 26, "自动筛选规则"),
    POSTER_WALL((byte) 27, "海报墙管理"),    // 不在标准功能里
    BOLT((byte) 28, "精准推送管理"),
    PLAN((byte) 29, "招聘进程"),
    H5((byte)30, "H5管理"),
    PROPAGATE((byte)32, "活动管理");

    private static Map<Byte, Module> byteToEnum = new HashMap<Byte, Module>();

    // 标准hr功能。每个合法的公司都拥有这些权限。
    private static List<Integer> standardModuleIds = new ArrayList<Integer>();

    static {
        for (Module module : Module.values()) {
            byteToEnum.put((byte) module.id, module);
        }
        standardModuleIds = (Arrays.asList(1, 8, 12, 13, 14, 15, 16, 17, 18, 19, 20, 24, 25, 26, 28, 32));
    }

    private Module(byte id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<Module> getAllValues() {
        List<Module> list = new ArrayList<Module>();
        Collections.addAll(list, Module.values());
        return list;
    }

    public static Set<Integer> getModuleSet() {
        Set<Integer> list = new HashSet<Integer>();
        Module[] modules = Module.values();
        for (Module module : modules) {
            list.add(module.getId());
        }
        return list;
    }

    public static Module of(byte id) throws NullPointerException {
        return byteToEnum.get(id);
    }

    private int id;
    private String name;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public static List<Integer> getStandardModuleIds() {
        return standardModuleIds;
    }
}