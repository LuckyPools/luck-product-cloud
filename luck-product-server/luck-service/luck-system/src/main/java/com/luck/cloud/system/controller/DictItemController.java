package com.luck.cloud.system.controller;

import com.luck.cloud.base.vo.ResultVO;
import com.luck.cloud.base.vo.PageVO;
import com.luck.cloud.core.entity.DictItem;
import com.luck.cloud.core.utils.DictUtils;
import com.luck.cloud.core.vo.DictItemVO;
import com.luck.cloud.base.utils.ConvertUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.luck.cloud.base.controller.BaseController;
import com.luck.cloud.core.service.IDictItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;



/**
 * 系统管理-字典项
 *
 * @author luck
 * @since 2024-10-17
 */
@RestController("coreDictItemController")
@RequestMapping("/dictItem")
public class DictItemController extends BaseController {

    @Autowired
    private IDictItemService dictItemService;

    @ApiOperation("分页获取数据")
    @PostMapping("page")
    public ResultVO<DictItemVO> pageDictItem(@RequestBody PageVO<DictItem> PageVO) {
        return ConvertUtils.convertPage(dictItemService.queryPage(PageVO), DictItemVO.class);
    }

    @ApiOperation("获取字典项列表")
    @GetMapping("getDictItems")
    public ResultVO<List<DictItemVO>> getDictItems(String dictCode, String parentId, String filter, String grades) {
        return ResultVO.success(ConvertUtils.convertList(DictUtils.getItemList(dictCode,parentId,filter,grades), DictItemVO.class));
    }

    @ApiOperation("根据id获取唯一数据")
    @GetMapping("/get/{id}")
    public ResultVO<DictItemVO> get(@PathVariable("id") String id) {
        DictItemVO dictItemVO = ConvertUtils.convert(dictItemService.queryOneById(id), DictItemVO.class);
        return ResultVO.success(dictItemVO);
    }

    @ApiOperation("添加数据")
    @PostMapping("save")
    public ResultVO save(@RequestBody DictItemVO dictItemVO) {
        DictItem dictItem = ConvertUtils.convert(dictItemVO, DictItem.class);
        dictItemService.saveOrUpdate(dictItem);
        return ResultVO.success(dictItem.getId());
    }

    @ApiOperation("更改数据")
    @PostMapping("update")
    public ResultVO update(@RequestBody DictItemVO dictItemVO) {
        DictItem dictItem = ConvertUtils.convert(dictItemVO, DictItem.class);
        dictItemService.saveOrUpdate(dictItem);
        return ResultVO.success();
    }

    @ApiOperation("删除数据")
    @PostMapping("/delete/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        dictItemService.deleteById(id);
        return ResultVO.success(id);
    }

    @ApiOperation("批量删除数据")
    @PostMapping("/delete/batch")
    public ResultVO deleteBatch(@RequestBody String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        boolean deleteCount = dictItemService.deleteByIds(idList);
        return ResultVO.success(deleteCount);
    }

}
