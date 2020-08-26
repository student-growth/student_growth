package com.info.common.sysenum;

/**
 * @author : yue
 * @Date : 2020/7/11 / 21:10
 * 成果申请项目分类
 */
public enum ProjectCategory {

    /**
     * 成果申请项目分类,6大类枚举
     */
    CATEGORY_001("001","研究创新"),
    CATEGORY_002("002","品德纪实"),
    CATEGORY_003("003","专业技能"),
    CATEGORY_004("004","文体特长"),
    CATEGORY_005("005","社会实践"),
    CATEGORY_006("006","组织领导"),
    ;


    private String code;
    private String name;

    ProjectCategory(String code,String name){
        this.name=name;
        this.code=code;
    }

    public static String getName(String code){
        for(ProjectCategory category: ProjectCategory.values()){
            if(category.code.equals(code)){
                return category.name;
            }
        }
        return "";
    }


}
