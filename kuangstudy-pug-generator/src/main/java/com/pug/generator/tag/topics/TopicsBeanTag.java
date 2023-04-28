package com.pug.generator.tag.topics;//package com.xq.tag.topics;
//
//import com.xq.feign.topics.Topics;
//import com.xq.feign.topics.TopicsFeginClient;
//import com.xq.tag.TmTemplateDirectiveModelUtil;
//import freemarker.core.Environment;
//import freemarker.ext.beans.BeansWrapperBuilder;
//import freemarker.template.*;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.ServletContextAware;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.servlet.ServletContext;
//import java.io.IOException;
//import java.util.Map;
//
///**
// * 轮播图管理
// * BannerBeanTag
// * 创建人:中华墨风
// * 时间：2018年09月26日 23:31:54
// *
// * @version 1.0.0
// */
//@Component("topicsBean")
//public class TopicsBeanTag implements TemplateDirectiveModel, ServletContextAware {
//    private ServletContext servletContext;
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    public void execute(Environment env, Map params, TemplateModel[] loopVars,
//                        TemplateDirectiveBody body) throws TemplateException, IOException {
//        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        TopicsFeginClient sepcialFeginClient = (TopicsFeginClient) applicationContext.getBean(TopicsFeginClient.class);
//        String var = TmTemplateDirectiveModelUtil.getString("var", params);
//        String id = TmTemplateDirectiveModelUtil.getString("id", params);
//        if (StringUtils.isEmpty(var)) var = "topics";
//        Topics topics = sepcialFeginClient.getTopics(id);
//        if (topics != null) {
//            env.setVariable(var, new BeansWrapperBuilder(Configuration.VERSION_2_3_28).build().wrap(topics));
//        }
//
//        if (body != null) {
//            if (topics != null) {
//                body.render(env.getOut());
//            }
//        }
//    }
//
//    @Override
//    public void setServletContext(ServletContext servletContext) {
//        this.servletContext = servletContext;
//    }
//}
