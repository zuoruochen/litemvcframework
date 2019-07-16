# litemvcframework
lite mvc framwork to understand IOC and AOP

## 简单功能分析
 * 框架实现bean的加载
 * 框架提供注解方式注入bean
 * 框架实现http request的route
 * 框架实现AOP，但不保证顺序
 
## 设计
 ### http request路由
 框架基于java servlet api，本身不实现server容器。框架实现了一个servlet，通过uri与handler的mapping将请求转发到指定的handler进行处理。
 ### bean的自动加载
 框架通过读取配置*lite.framework.app.base_package*，加载对应的类文件，对包含特定注解的类进行实例化，创建全局唯一的bean，目前自动创建由@Controller，@Service和@Componet
 注解修饰的类
 ### bean的自动注入
 框架自动识别类中由@Autowired注解的field，自动将对应的bean实例注入到目标类bean。注入规则
 * class 类型的field注入同类型的bean
 * interface 类型的field优先注入与field name名字相同的bean
 ### AOP
 框架通过cglib实现AOP，切面类可以叠加（目前执行顺序不保证），用户通过实现抽象类AspectProxy提供切面，override以下方法
 * begin()，拦截前 
 * end()，拦截后
 * intercept()， 用于判断是否执行拦截
 * before()， 方法执行前
 * after()，方法执行后
 * error()，执行方法出错
 
 ## 实现
  ### http request 路由
  ControllerMapping.java 实现{uri，http method}到{Controller.class, Method.class}的mapping。DispatchServlet.java匹配所有请求，override doGet(), doPost(), doPut(), doDelete()等方法，将请求转发给对应的controller的method
  ### bean的自动加载
  ClassHelper.class通过classloader读取所有的类，BeanContainer.class对指定注解的类创建bean，创建一个class和bean的map
  ### bean的注入
  Ioc.class对所有bean，check是否有field包含@Autowired注解，将对应的bean设置到目标bean
  
