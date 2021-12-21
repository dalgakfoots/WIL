package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> { // JpaRepository<Entity Type , id Type>
    // select m from Member m where m.name = :name
    // findByXxx

    // 애플리케이션 실행 시점에 Spring Data JPA 가 주입해줌
    List<Member> findByName(String name);
}
