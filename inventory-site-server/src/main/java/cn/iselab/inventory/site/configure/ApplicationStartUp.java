package cn.iselab.inventory.site.configure;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by ROGK on 2017/9/14.
 */
public class ApplicationStartUp implements ApplicationListener<ContextRefreshedEvent>{

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("success");
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}
