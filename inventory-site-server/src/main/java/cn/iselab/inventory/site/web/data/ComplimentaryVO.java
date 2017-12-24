package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private String customName;

    private List<ComplimentaryItem> items;

    private Double total;

    private String extra;

    private Long status;
}
