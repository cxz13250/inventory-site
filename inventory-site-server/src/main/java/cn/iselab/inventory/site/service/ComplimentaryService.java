package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.Complimentary;
import cn.iselab.inventory.site.model.SaleStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:52 2017/11/6
 * @Modified By:
 */
public interface ComplimentaryService {

    Complimentary createComplimentary(Complimentary complimentary);

    Page<Complimentary> getComplimentaries(String keyword, Pageable pageable);

    Complimentary getComplimentary(long complimentaryId);

    void updateComplimentary(Complimentary complimentary);

    void deleteComplimentary(Complimentary complimentary);
}
