package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member , Long> , MemberRepositoryCustom { // 엔티티 , 엔티티 Id 타입

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

    // Fetch Join 기본
    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    // @EntityGraph 를 통해 fetch join 사용
    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    // 쿼리 힌트
    @QueryHints(@QueryHint(name = "org.hibernate.readOnly" , value= "true"))
    Member findReadOnlyByUsername(String username);

    // select for update
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);

    // 네이티브 쿼리
    // 네이티브 쿼리를 사용하기 보다는, 별도의 리포지토리를 생성하여 Jdbc Template 또는 MyBatis 를 사용하는 것을 추천
    @Query(value = "select * from member where username = ?",nativeQuery = true)
    Member findByNativeQuery(String username);

    @Query(value = "select m.member_id as id, m.username, t.name as teamName" +
            " from member m left outer join team t "
            ,countQuery = "select count(*) from member"
            ,nativeQuery = true)
    Page<MemberProjection> findByNativeProjection(Pageable pageable); //프로젝션을 사용하여 정적 네이티브쿼리를 작성
}
