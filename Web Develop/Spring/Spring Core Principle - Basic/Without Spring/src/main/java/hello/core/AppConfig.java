package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    // 생성자 주입

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
        // 픽스 되는 부분
    }

    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
        // 변경될 수 있는 부분
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        // 픽스 되는 부분
    }

    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
        // 변경될 수 있는 부분
    }
}
