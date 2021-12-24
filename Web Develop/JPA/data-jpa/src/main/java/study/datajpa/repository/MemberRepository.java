package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member , Long> { // 엔티티 , 엔티티 Id 타입

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age); // 필드명 변경 시, 메서드 이름도 변경해야 함.
}
