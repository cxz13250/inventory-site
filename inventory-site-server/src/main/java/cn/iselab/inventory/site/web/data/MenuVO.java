package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:17 2017/12/4
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO {

    private String menu;

    private String detail;

    private String url;
}
