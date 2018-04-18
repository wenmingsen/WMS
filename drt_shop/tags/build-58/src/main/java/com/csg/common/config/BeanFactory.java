package com.csg.common.config;
import org.springframework.context.ApplicationContext;


//@Component
public class BeanFactory {
    // 获取spring配置文件地址
//    private static ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
//    @Autowired
    private static ApplicationContext ctx =SpringTool.getApplicationContext();

    /**
     * 从当前IOC获取bean
     *
     * @param id bean的id
     * @return
     */
    public static Object getBean(String id) {
        Object object = null;
     if(ctx==null)return null;
        object = ctx.getBean(id);
        return object;
    }
}
