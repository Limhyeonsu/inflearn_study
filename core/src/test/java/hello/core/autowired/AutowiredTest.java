package hello.core.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.lang.reflect.Member;
import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        //1. Mamber 객체는 스프링에서 관리하는 Bean이 아님, "required = false"로 설정하면 의존관계가 없기 때문에 메서드 자체가 호출되지 않는다.
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("setNoBean1 = " + member);
        }

        //2. @Nullable을 붙이면 의존관계가 없는 경우 메서드가 호출은 되지만 null로 들어온다.
        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("setNoBean2 = " + member);
        }

        //3. 스프링 빈이 없으면 Optional.empty가 호출된다.
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> member) {
            System.out.println("setNoBean3 = " + member);
        }
    }
}
