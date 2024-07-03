package com.luck.cloud.base.utils;

import com.luck.cloud.base.vo.PageVO;
import com.luck.cloud.common.utils.ReflectUtils;
import io.github.linpeilie.Converter;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 对象转换工具
 * @author luck
 * @date 2023-10-26
 */
public class ConvertUtils {

    private static Converter converter;

    private static Converter getConverter() {
        if (ConvertUtils.converter == null) {
            converter = new Converter();
        }
        return converter;
    }

    /**
     * 执行单个对象转换
     *
     * @param source       源对象
     * @param targetType   目标对象类型
     * @param <S>          源对象类型
     * @param <T>          目标对象类型
     */
    public static <S, T> T convert(S source, Class<T> targetType) {
        T target = ConvertUtils.getConverter().convert(source, targetType);
        // 根据@AutoMappingIgnore过滤掉忽略转换的字段
        Arrays.stream(targetType.getDeclaredFields()).forEach(field -> {
            ReflectUtils.setFieldValue(target, field.getName(), null);
        });
        return target;
    }

    /**
     * 执行列表对象转换
     *
     * @param source       源对象
     * @param targetType   目标对象类型
     * @param <S>          源对象类型
     * @param <T>          目标对象类型
     */
    public static <S, T> List<T> convertList(List<S> source, Class<T> targetType) {
        List<T> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)) {
            source.forEach(record -> {
                T target = ConvertUtils.convert(record, targetType);
                resultList.add(target);
            });
        }
        return resultList;
    }

    /**
     * 对PageVO包装的数据对象进行转换
     *
     * @param source       源对象
     * @param targetType   目标对象类型
     * @param <S>          源对象类型
     * @param <T>          目标对象类型
     */
    public static <S, T> PageVO<T> convertPage(PageVO<S> source, Class<T> targetType) {
        PageVO<T> resultPage = PageVO.emptyPageVO();
        BeanUtils.copyProperties(source, resultPage, "records");
        resultPage.setRecords(new ArrayList<>());
        if (!CollectionUtils.isEmpty(source.getRecords())) {
            source.getRecords().forEach(record -> {
                T target = ConvertUtils.convert(record, targetType);
                resultPage.getRecords().add(target);
            });
        }
        return resultPage;
    }

}
