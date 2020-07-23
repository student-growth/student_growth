package com.info.converter;

import com.info.dto.QuesDTO;
import com.info.entity.QuesEntity;
import org.mapstruct.Mapper;

/**
 * @author : yue
 * @Date : 2020/7/21 / 13:03
 */

@Mapper(componentModel = "spring")
public interface QuesConverter {

    QuesDTO entityConv(QuesEntity entity);

}
