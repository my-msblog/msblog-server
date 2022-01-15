package com.ms.blogserver.model.vo;

import com.ms.blogserver.core.base.BaseVO;
import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/2
 */
@Data
@ToString
public class StatisticsVO implements BaseVO {
    private CardValueVO value;
    private CardValueVO total;

    public StatisticsVO() {
    }

    public StatisticsVO(CardValueVO value, CardValueVO total) {
        if (value.available() && total.available()) {
            this.value = value;
            this.total = total;
        }else {
            this.value = new CardValueVO();
            this.total = new CardValueVO();
        }
    }
}
