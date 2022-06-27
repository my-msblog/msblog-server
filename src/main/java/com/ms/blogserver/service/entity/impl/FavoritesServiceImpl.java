package com.ms.blogserver.service.entity.impl;

import com.ms.blogserver.core.base.EntityServiceImpl;
import com.ms.blogserver.mapper.FavoritesMapper;
import com.ms.blogserver.model.entity.Favorites;
import com.ms.blogserver.service.entity.FavoritesService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Service
public class FavoritesServiceImpl extends EntityServiceImpl<Favorites,FavoritesMapper> implements FavoritesService {
}
