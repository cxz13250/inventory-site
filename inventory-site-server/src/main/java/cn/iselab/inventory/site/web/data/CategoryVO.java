package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午1:51 2017/12/5
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVO{

    private Long id;

    private String name;

    private Long superId;

    private String superName;

    private String description;

    private Long createTime;
}
