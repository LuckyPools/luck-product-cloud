package com.luck.cloud.core.utils;

import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.luck.cloud.base.param.SearchParam;
import com.luck.cloud.common.utils.SpringBeanUtils;
import com.luck.cloud.common.utils.StringUtils;
import com.luck.cloud.core.entity.DictItem;
import com.luck.cloud.core.service.IDictItemService;
import com.luck.cloud.redis.utils.RedisCacheUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 字典工具类
 */
public class DictUtils {

    /**
     * 字典本地缓存key
     */
    private static final String CACHE_DICT_MAP = "LUCK_DICT_MAP";

    private static final class Static {

        private static IDictItemService getIDictItemService() {
            return SpringBeanUtils.getBean(IDictItemService.class);
        }
    }

    /**
     * 获取字典列表
     * @return
     */
    public static List<DictItem> getItemList() {
        Map<String, List<DictItem>> dictMap = RedisCacheUtils.get(CACHE_DICT_MAP);
        if (dictMap == null) {
            DictUtils.refreshCache();
        }
        List<DictItem> dictList = Lists.newArrayList();
        Map<String, List<DictItem>> finalDictMap = dictMap;
        dictMap.keySet().forEach(key -> {
            List<DictItem> sysDictItems = finalDictMap.get(key);
            if (!CollectionUtils.isEmpty(sysDictItems)) {
                dictList.addAll(sysDictItems);
            }
        });
        return dictList;
    }

    /**
     * 获取指定类型的字典项列表
     *
     * @param dictCode 字典类型编码
     */
    public static List<DictItem> getItemList(String dictCode) {
        Map<String, List<DictItem>> dictMap = RedisCacheUtils.get(CACHE_DICT_MAP,new TypeReference<Map<String, List<DictItem>>>() {});
        if (dictMap == null) {
            dictMap = DictUtils.refreshCache();
        }
        List<DictItem> dictList = dictMap.get(dictCode);
        if (dictList == null) {
            dictList = Lists.newArrayList();
        }
        return dictList;
    }

    /**
     * 根据字段类型编码和过滤参数获取字典项列表
     *
     * @param dictCode 字段类型编码
     * @param filter   过滤参数，多个参数需要使用','进行分隔
     */
    public static List<DictItem> getItemList(String dictCode, String filter) {
        List<DictItem> dictList = DictUtils.getItemList(dictCode);
        if (StringUtils.isNotEmpty(filter)) {
            List<String> filterList = Arrays.asList(filter.split(","));
            dictList = dictList.stream().filter(dictItem -> {
                if (StringUtils.isNotEmpty(dictItem.getFilter())) {
                    return !Collections.disjoint(Arrays.asList(dictItem.getFilter().split(",")), filterList);
                }
                return false;
            }).collect(Collectors.toList());
        }
        return dictList;
    }

    /**
     * 获取指定节点的子节点
     *
     * @param dictCode 字典类型编码
     * @param parentId 父节点ID
     * @param filter   分类过滤标识
     */
    public static List<DictItem> getItemList(String dictCode, String parentId, String filter, String grades) {
        List<DictItem> dictItems = DictUtils.getItemList(dictCode, filter);
        // 父节点id过滤
        if (StringUtils.isNotEmpty(parentId)) {
            dictItems = dictItems.stream()
                    .filter(dictItem -> parentId.equals(dictItem.getParentId()))
                    .collect(Collectors.toList());
        }
        // 层级过滤
        if (StringUtils.isNotEmpty(grades)) {
            List<String> gradesList = Lists.newArrayList(grades.split(","));
            dictItems = dictItems.stream()
                    .filter(dictItem -> gradesList.contains(String.valueOf(dictItem.getGrade())))
                    .collect(Collectors.toList());
        }
        return dictItems;
    }


    /**
     * 根据字典类型编码和字典项编码获取字典项值
     * <p>当没有匹配的值时，返回{@code ""}</p>
     *
     * @param dictCode 字典类型编码
     * @param itemCode 字典项编码
     */
    public static String getItemName(String dictCode, String itemCode) {
        String name = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(dictCode) && StringUtils.isNotBlank(itemCode)) {
            for (DictItem dictItem : DictUtils.getItemList(dictCode)) {
                if (dictCode.equals(dictItem.getDictCode())
                        && itemCode.equals(dictItem.getCode())) {
                    name = dictItem.getName();
                }
            }
        }
        return name;
    }

    /**
     * 根据字典类型编码和字典项编码(多值)获取字典项完整路径名称
     * <p>通常使用于获取树形结构的字典项值，普通k-v类型的字典项虽然也会有{@code allNames}属性，但是其属性值于{@code name}无异</p>
     *
     * @param dictCode           字典类型编码
     * @param itemCodes          字典项编码值
     * @param itemCodesSeparator {@code dictValues}的分隔符 如：','
     */
    public static String getItemAllNames(String dictCode, String itemCodes, String itemCodesSeparator) {
        if (StringUtils.isNotEmpty(itemCodesSeparator) && StringUtils.isNotBlank(itemCodes) && itemCodes.contains(itemCodesSeparator)) {
            String[] dictValues = itemCodes.split(itemCodesSeparator);
            itemCodes = dictValues[dictValues.length - 1];
        }
        String allNames = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(dictCode) && StringUtils.isNotBlank(itemCodes)) {
            for (DictItem dictItem : DictUtils.getItemList(dictCode)) {
                if (dictCode.equals(dictItem.getDictCode())
                        && itemCodes.equals(dictItem.getCode())) {
                    allNames = dictItem.getAllNames();
                }
            }
        }
        return allNames;
    }

    /**
     * 根据字典类型编码和字典项值获取字典项编码
     *
     * @param dictCode 字典类型编码
     * @param itemName 字典项值
     */
    public static String getItemCode(String dictCode, String itemName) {
        String code = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(itemName) && StringUtils.isNotBlank(dictCode)) {
            for (DictItem dictItem : DictUtils.getItemList(dictCode)) {
                if (dictCode.equals(dictItem.getDictCode())
                        && itemName.equals(dictItem.getName())) {
                    code = dictItem.getCode();
                }
            }
        }
        return code;
    }


    /**
     * 刷新字典缓存
     */
    public static Map<String, List<DictItem>> refreshCache(){
        Map<String, Object> dictMap = Maps.newHashMap();
        List<DictItem> dictList = Static.getIDictItemService().queryList(SearchParam.getSearchParam());
        dictList.forEach(dict -> {
            List<DictItem> items = (List<DictItem>) dictMap.get(dict.getDictCode());
            if (items == null || items.size() == 0) {
                items = Lists.newArrayList();
            }
            items.add(dict);
            dictMap.put(dict.getDictCode(), items);
        });
        RedisCacheUtils.put(CACHE_DICT_MAP, dictMap);
        return RedisCacheUtils.get(CACHE_DICT_MAP);
    }

    /**
     * 刷新指定类型的字典项
     *
     * @param dictCode 字典类型码
     */
    public static void refreshCache(String dictCode) {
        // 获取指定字典类型最新的字典项列表
        List<DictItem> sysDictItems = Static.getIDictItemService().queryListByDictCode(dictCode);
        if (!CollectionUtils.isEmpty(sysDictItems)) {
            Map<String, List<DictItem>> dictMap = RedisCacheUtils.get(CACHE_DICT_MAP);
            if(CollectionUtils.isEmpty(dictMap)){
                dictMap = new HashMap<>();
            }
            dictMap.put(dictCode, sysDictItems);
            RedisCacheUtils.put(CACHE_DICT_MAP,dictMap);
        }
    }
}
