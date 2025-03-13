### Declarative java agent byte code patcher   
Uses **org.javassist:javassist** inside

### It is fork from [ytsaurus-patch](https://github.com/ytsaurus/ytsaurus-spyt/blob/main/spark-patch/src/main/java/tech/ytsaurus/spyt/patch/)
[ytsaurus Joker 2024](https://vkvideo.ru/playlist/-796_56/video-796_456240553)


- [declarative-patcher](declarative-patcher) - Main library project
- [samples](samples) samples for usage. [javaagent-sample](samples/javaagent-sample) - agent sample, [spring-print-bean-sample](samples/spring-print-bean-sample) project for agent testing

#### Available on Maven Central   
```xml
<dependency>
    <groupId>io.github.grisha9</groupId>
    <artifactId>declarative-patcher</artifactId>
    <version>0.1</version>
</dependency>
```

#### Documentation and samles

Classes with byte code patches must contain any substrings from {"patch", "subclass", "decorat"} in the class name in any case.   
Examples: AbstractApplicationContextDecorator.java, ContextPatcher.java, ContextSubclass.java.

Example agent for Spring application for printing all bean definitions: [javaagent-sample](samples/javaagent-sample/src/main/java/com/example/SpringContextPatcher.java)   
Instrumenting Spring method [registerBeanPostProcessors](https://github.com/spring-projects/spring-framework/blob/3.0.x/org.springframework.context/src/main/java/org/springframework/context/support/AbstractApplicationContext.java#L410) and printing beans in our custom method *printBeans*
```java
@Decorate
@OriginClass("org.springframework.context.support.AbstractApplicationContext")
public class SpringContextPatcher {

    @DecoratedMethod
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        __registerBeanPostProcessors(beanFactory);
        printBeans(beanFactory);
        throw new RuntimeException("Agent error");
    }

    @AddMethod
    private void printBeans(ConfigurableListableBeanFactory beanFactory) {
        //print beans
    }

    protected void __registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
    }
}
```

Example for usages:
 - Spring application for test [spring-print-bean-sample](samples/spring-print-bean-sample/src/main/java/org/springframework/sample/Application.java)   
 - for run: ```java -javaagent:/path/to/javaagent-sample-1.0-SNAPSHOT.jar -jar /path/to/spring-print-bean-sample-3.0.0.jar```