package com.zrc;

import com.zrc.annotation.RequestParam;
import com.zrc.annotation.ResponseBody;
import com.zrc.util.CastUtil;
import com.zrc.util.ObjectMapperUtil;
import com.zrc.util.ReflectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet(urlPatterns = "/*")
public class DispatchServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(DispatchServlet.class);
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServerInit.init();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestHandler requestHandler = ControllerMapping.getRequestHandler(req.getMethod(), req.getPathInfo());
        if(requestHandler == null) {
            resp.sendError(SC_NOT_FOUND);
        } else {
            Method method = requestHandler.getMethod();
            Parameter[] parameters = method.getParameters();
            Object[] params = new Object[parameters.length];
            for(int i = 0 ; i < parameters.length ; i ++) {
                Parameter parameter = parameters[i];
                boolean paramRequired = true;
                String parameterName;
                if(parameter.isAnnotationPresent(RequestParam.class)) {
                    RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                    parameterName = requestParam.name() != "" ? requestParam.name() : parameter.getName();
                    paramRequired = requestParam.required();
                } else {
                    parameterName = parameter.getName();
                }
                String paramValue = req.getParameter(parameterName);
                Class<?> paramType = parameter.getType();
                if(StringUtils.isEmpty(paramValue)) {
                    if(paramRequired) {
                        resp.sendError(SC_BAD_REQUEST, "param " + parameterName + " is required");
                        return;
                    } else {
                        params[i] = ReflectionUtil.newInstance(paramType);
                    }
                } else {
                    LOGGER.info("cast {} to type : {}", paramValue, paramType);
                    try {
                        params[i] = CastUtil.castStringWithException(paramType, paramValue);
                    } catch (Exception e) {
                        LOGGER.error("cast error", e);
                        resp.sendError(SC_BAD_REQUEST, "the value of param " + parameterName + " is illegal");
                        return;
                    }
                }
            }

            Object controllerBean = BeanContainer.getBean(requestHandler.getControllerClass());
            Object result = ReflectionUtil.invokeMethod(controllerBean, method, params);
            ResponseBody annotation = method.getAnnotation(ResponseBody.class);
            if(annotation != null) {
                resp.setContentType("application/json");
            } else {
                resp.setContentType("text/plain");
            }
            resp.setCharacterEncoding("UTF-8");
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(ObjectMapperUtil.getObjectMapper().writeValueAsString(result));
            printWriter.flush();
            printWriter.close();
        }

    }
}
