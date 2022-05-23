package com.ms.blogserver.model.vo;

import com.ms.blogserver.core.base.BaseVO;
import com.ms.blogserver.core.constant.contexts.DigitalContexts;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/2
 */
@Data
public class CardValueVO implements BaseVO {
    private Integer visit;
    private Integer user;
    private Integer articles;
    private Integer comments;

    public CardValueVO() {
        this.visit = DigitalContexts.ZERO;
        this.user = DigitalContexts.ZERO;
        this.articles = DigitalContexts.ZERO;
        this.comments = DigitalContexts.ZERO;
    }

    public CardValueVO(Integer visit, Integer user, Integer articles, Integer comments) {
        this.visit = visit;
        this.user = user;
        this.articles = articles;
        this.comments = comments;
    }

    public boolean available(){
        return Objects.nonNull(this.visit) && Objects.nonNull(this.user) &&
                Objects.nonNull(this.articles) && Objects.nonNull(this.comments);
    }
}
