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
 * 1.基于{@see <a href="https://mapstruct.plus">mapstruct.plus</a>}
 * 2.源或目标对象的类需要使用{@link io.github.linpeilie.annotations.AutoMapper}注解进行标注后才能使用本
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
     * @param doEhrConvert 是否执行自定义转换，自定义转换包括字典值、日期格式、数字格式等
     * @param <S>          源对象类型
     * @param <T>          目标对象类型
     */
    public static <S, T> T convert(S source, Class<T> targetType, boolean... doEhrConvert) {
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
     * @param doEhrConvert 是否执行自定义转换，自定义转换包括字典值、日期格式、数字格式等
     * @param <S>          源对象类型
     * @param <T>          目标对象类型
     */
    public static <S, T> List<T> convertList(List<S> source, Class<T> targetType, boolean... doEhrConvert) {
        List<T> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)) {
            source.forEach(record -> {
                T target = ConvertUtils.convert(record, targetType, doEhrConvert);
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
