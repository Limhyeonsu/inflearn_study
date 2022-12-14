package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        //ThreadA : A 사용자 10,000원 주문
        statefulService1.order("userA", 10000);

        //ThreadB : B 사용자 20,000원 주문
        statefulService1.order("userB", 20000);

        int price = statefulService1.getPrice();
        //사용자A는 10,000원을 기대했지만 20,000원이 출력됨!! 공유필드는 항상 조심해야한다! 스프링 빈은 항상 "무상태"로 설계하자
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }


    static class TestConfig {
        @Bean
        public StatefulService statefulService () {
            return new StatefulService();
        }
    }
}
