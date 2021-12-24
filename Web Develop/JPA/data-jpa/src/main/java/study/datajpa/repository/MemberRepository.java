package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member , Long> { // 엔티티 , 엔티티 Id 타입

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age); // 필드명 변경 시, 메서드 이름도 변경해야 함.

    @Query("select m from Member m where m.username = :username and m.age =:age") // 어플리케이션 로딩 시점에 파싱함 -> 문법 오류 시 에러
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto(m.id , m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();
}
