package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member , Long> { // 엔티티 , 엔티티 Id 타입

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age); // 필드명 변경 시, 메서드 이름도 변경해야 함.

    @Query("select m from Member m where m.username = :username and m.age =:age") // 어플리케이션 로딩 시점에 파싱함 -> 문법 오류 시 에러
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto(m.id , m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    List<Member> findListByUsername(String username); // 컬렉션
    Member findMemberByUsername(String username);  // 단건
    Optional<Member> findOptionalByUsername(String username); // 단건 Optional

    /*
    * Spring Data JPA 페이징
    * */

//    @Query(value = "select m from Member m left join m.team t" ,
//            countQuery = "select count(m) from Member m" // Count Query를 별도로 설정
//    )
    Page<Member> findByAge(int age, Pageable pageable);

    /*
    * 벌크성 수정 쿼리
    * */
    @Modifying(clearAutomatically = true) // 수정쿼리 작성 시, Modifying 어노테이션이 필요
    // clearAutomatically 를 통해 영속성 컨텍스트를 clear 시킨다.
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);
}
