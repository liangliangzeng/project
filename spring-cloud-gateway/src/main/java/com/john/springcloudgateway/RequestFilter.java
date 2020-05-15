package com.john.springcloudgateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StreamUtils;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

public class RequestFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;//要打印返回信息，必须得用"post"
}

@Override
public int filterOrder() {
return 0;
}

@Override
public boolean shouldFilter() {
return true;
}

@Override
public Object run() {
try {
RequestContext ctx = RequestContext.getCurrentContext();
HttpServletRequest request = ctx.getRequest();
InputStream in = request.getInputStream();
String reqBbody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
// 打印userId，获取其他用户信息
if (reqBbody != null) {
JSONObject json = JSONObject.fromObject(reqBbody);
Object userId = json.get("userId");
if (userId != null) {
RequestFilter.LOGGER.info("request userId:\t" + userId);
}
}
// 打印请求方法，路径
RequestFilter.LOGGER.info("request url:\t" + request.getMethod() + "\t" + request.getRequestURL().toString());
Map<String, String[]> map = request.getParameterMap();
// 打印请求url参数
if (map != null) {
StringBuilder sb = new StringBuilder();
sb.append("request parameters:\t");
for (Map.Entry<String, String[]> entry : map.entrySet()) {
sb.append("[" + entry.getKey() + "=" + printArray(entry.getValue()) + "]");
}
RequestFilter.LOGGER.info(sb.toString());
}
// 打印请求json参数
if (reqBbody != null) {
RequestFilter.LOGGER.info("request body:\t" + reqBbody);
}

// 打印response
InputStream out = ctx.getResponseDataStream();
String outBody = StreamUtils.copyToString(out, Charset.forName("UTF-8"));
if (outBody != null) {
RequestFilter.LOGGER.info("response body:\t" + outBody);
}

ctx.setResponseBody(outBody);//重要！！！

} catch (IOException e) {
e.printStackTrace();
}

return null;
}

String printArray(String[] arr) {
StringBuilder sb = new StringBuilder();
for (int i = 0; i < arr.length; i++) {
sb.append(arr[i]);
if (i < arr.length - 1) {
sb.append(",");
}
}
return sb.toString();
}

}
