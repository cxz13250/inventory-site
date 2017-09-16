package cn.iselab.inventory.site.web.data.wrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sean
 * @date 17/3/5.
 */
public abstract class BaseWrapper<VO, DATA> {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    public abstract VO wrap(DATA data);

    public abstract DATA unwrap(VO data);

    public List<VO> wrap(List<DATA> data) {
        return data.stream().map(this::wrap).collect(Collectors.toList());
    }

    public Page<VO> wrap(Page<DATA> data) {
        Page<VO> page = new PageImpl<VO>(wrap(data.getContent()),
                new PageRequest(data.getNumber(), data.getSize()), data.getTotalElements());
        return page;
    }

    public List<DATA> unwrap(List<VO> vos) {
        return vos.stream().map(this::unwrap).collect(Collectors.toList());
    }

}
