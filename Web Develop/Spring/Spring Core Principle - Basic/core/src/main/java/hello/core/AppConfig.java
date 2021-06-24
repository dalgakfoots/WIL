package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정 정보에 사용
public class AppConfig {
    // 생성자 주입

    @Bean // 스프링 컨테이너에 등록
    public MemberService memberService(){ // 기본적으로 메서드 명으로 등록된다.
        return new MemberServiceImpl(memberRepository());
        // 픽스 되는 부분
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
        // 변경될 수 있는 부분
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        // 픽스 되는 부분
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
        // 변경될 수 있는 부분
    }
}
