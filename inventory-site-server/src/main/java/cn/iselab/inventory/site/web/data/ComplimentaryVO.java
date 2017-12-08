package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午12:04 2017/12/8
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplimentaryVO {

    private Long id;

    private Long createTime;

    private Long customId;

    private String content;

    private Double total;
}
