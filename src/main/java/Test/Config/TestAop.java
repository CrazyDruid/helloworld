package Test.Config;

import Test.Annotation.MyInfoAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/6/25.
 */
@Aspect
@Component
public class TestAop {



    @Pointcut("@annotation(Test.Annotation.MyInfoAnnotation)")
    public void myInfoAnnotation() {
    }

    @Before("@annotation(Test.Annotation.MyInfoAnnotation)")
    public void deBefore() throws Throwable {
        System.out.println("second before");
    }

    @After("@annotation(Test.Annotation.MyInfoAnnotation)")
    public void deAfter() throws Throwable {
        System.out.println("second after");
    }

    @Around("@annotation(myInfoAnnotation)")
    public Object around(ProceedingJoinPoint pjp, MyInfoAnnotation myInfoAnnotation) {
        //获取注解里的值
        System.out.println("second around:" + myInfoAnnotation.value());
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }


//    // 用@Pointcut来注解一个切入方法
//    @Pointcut("execution(* Test.Controller.*.**(..))")
//    public void excudeController() {
//        System.out.println("=============");
//    }
    public String testA(){
        System.out.println("github test");
        return null;
    }
}
