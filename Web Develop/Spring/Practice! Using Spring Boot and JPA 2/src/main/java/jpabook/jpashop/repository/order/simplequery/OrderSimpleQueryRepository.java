package jpabook.jpashop.repository.order.simplequery;

import jpabook.jpashop.repository.OrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDtos() {
        // new 명령어를 사용해서 JPQL의 결과를 DTO로 즉시 변환
        // SELECT 절에서 원하는 데이터를 직접 선택하므로 네트웍 용량 최적화 (허나 생각보다 미비함)
        // 재사용성이 떨어짐 <- API 스펙에 맞게 쿼리를 조회했기 때문에...

        return em.createQuery("select new jpabook.jpashop.repository.OrderSimpleQueryDto(o.id, m.name , o.orderDate , o.status , d.address)" +
                        " from Order o" +
                        " join o.member m " +
                        " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }

}
