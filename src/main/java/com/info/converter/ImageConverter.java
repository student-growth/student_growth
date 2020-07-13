package com.info.converter;

import com.info.dto.pc.ImageDTO;
import com.info.entity.ImageEntity;
import org.mapstruct.Mapper;

/**
 * @author : yue
 * 2020/7/13 / 17:46
 */
@Mapper(componentModel = "spring")
public interface ImageConverter {

    ImageDTO imageConver(ImageEntity entity);
}
