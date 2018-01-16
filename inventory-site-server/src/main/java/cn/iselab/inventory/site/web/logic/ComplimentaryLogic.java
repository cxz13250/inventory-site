package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.model.Complimentary;
import cn.iselab.inventory.site.web.data.ComplimentaryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:59 2017/12/8
 * @Modified By:
 */
public interface ComplimentaryLogic {

    Page<ComplimentaryVO> getComplimentaries(String keyword, Pageable pageable);

    ComplimentaryVO getComplimentary(long id);

    Long createComplimentary(ComplimentaryVO vo);

    void updateComplimentary(ComplimentaryVO vo);

    void deleteComplimentary(long id);

    void checkComplimentary(ComplimentaryVO vo);
}
