package com.pug.generator.tag.topics;//package com.xq.tag.topics;
//
//import com.xq.dto.PageTemplate;
//import com.xq.feign.topics.Topics;
//import com.xq.feign.topics.TopicsFeginClient;
//import com.xq.feign.topics.TopicsVo;
//import com.xq.tag.TmTemplateDirectiveModelUtil;
//import freemarker.core.Environment;
//import freemarker.ext.beans.BeansWrapper;
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
//import java.util.List;
//import java.util.Map;
//
///**
// *轮播图管理
// *BannerListTag
// *  创建人:中华墨风
// *  时间：2018年09月26日 23:31:54
// * @version 1.0.0
// *
// */
//@Component("topicsList")
//public class TopicsListTag implements TemplateDirectiveModel, ServletContextAware {
//    private ServletContext servletContext;
//
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
//            throws TemplateException, IOException {
//        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        TopicsFeginClient sepcialFeginClient = (TopicsFeginClient) applicationContext.getBean(TopicsFeginClient.class);
//        String var = TmTemplateDirectiveModelUtil.getString("var", params);
//        Integer pageNo = TmTemplateDirectiveModelUtil.getInteger("pageNo", params);
//        Integer pageSize = TmTemplateDirectiveModelUtil.getInteger("pageSize", params);
//        String orderby = TmTemplateDirectiveModelUtil.getString("sort", params);
//        if (StringUtils.isEmpty(var))
//            var = "topics";
//        if (pageNo == null)
//            pageNo = 0;
//        if (pageSize == null)
//            pageSize = 20;
//        if (orderby == null)
//            orderby = " create_time desc ";
//
//        TopicsVo topicsVo = new TopicsVo();
//        topicsVo.setPageNo(pageNo);
//        topicsVo.setPageSize(pageSize);
//
//        BeansWrapper beansWrapper =  new BeansWrapperBuilder(Configuration.VERSION_2_3_28).build();
//        PageTemplate<Topics> topicsPage = sepcialFeginClient.queryTopics(topicsVo);
//        List<Topics> topicsList = topicsPage.getRecords();
//        env.setVariable(var+"_page", beansWrapper.wrap(topicsPage));
//        if (body != null) {
//            int i = 0;
//            env.setVariable(var + "_size", beansWrapper.wrap(topicsList.size()));
//            for (Topics topics : topicsList) {
//                env.setVariable(var,beansWrapper.wrap(topics));
//                env.setVariable(var + "_index", beansWrapper.wrap(i));
//                body.render(env.getOut());
//                i++;
//            }
//        } else {
//        }
//    }
//
//    @Override
//    public void setServletContext(ServletContext servletContext) {
//        this.servletContext = servletContext;
//    }
//}