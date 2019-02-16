package com.authstr.ff.utils.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.authstr.ff.utils.exception.Assert;

@Component
public class SpringUtils implements ApplicationContextAware {
    private static Log log = LogFactory.getLog(SpringUtils.class);
    private static ApplicationContext applicationContext;

    public   void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtils.applicationContext = applicationContext;
    }

    private static ApplicationContext getContext() {
        return applicationContext;
    }

    public static Object getBean(String beanId) {
        return SpringUtils.getBean(Object.class, beanId);
    }

    public static <T> T getBean(Class<T> clazz, String beanId) throws ClassCastException {
        ApplicationContext context = SpringUtils.getContext();
        Assert.isTrue(StringUtils.hasText(beanId), "beanId must not null!");
        System.out.println(context);
        boolean a=context.containsBean(beanId);
        Assert.isTrue(context.containsBean(beanId), "beanId :[" + beanId + "] is not exist!");
        Object bean = null;
        bean = context.getBean(beanId);
        return (T)bean;
    }
}

