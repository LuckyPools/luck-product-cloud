package com.luck.cloud.core.controller;

import com.luck.cloud.base.vo.ResultVO;
import com.luck.cloud.base.vo.PageVO;
import com.luck.cloud.core.entity.DictType;
import com.luck.cloud.core.vo.DictTypeVO;
import com.luck.cloud.base.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.luck.cloud.base.controller.BaseController;
import com.luck.cloud.core.service.IDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;



/**
 * 系统管理-字典类型
 *
 * @author luck
 * @since 2024-10-17
 */
@RestController("coreDictTypeController")
@RequestMapping("/core/dictType")
public class DictTypeController extends BaseController {

    @Autowired
    private IDictTypeService dictTypeService;

    @ApiOperation("分页获取数据")
    @PostMapping("page")
    public PageVO<DictTypeVO> pageDictType(@RequestBody PageVO<DictType> PageVO) {
        return ConvertUtils.convertPage(dictTypeService.queryPage(PageVO), DictTypeVO.class);
    }

    @ApiOperation("根据id获取唯一数据")
    @GetMapping("/get/{id}")
    public ResultVO<DictTypeVO> get(@PathVariable("id") String id) {
        DictTypeVO dictTypeVO = ConvertUtils.convert(dictTypeService.queryOneById(id), DictTypeVO.class);
        return ResultVO.success(dictTypeVO);
    }

    @ApiOperation("添加数据")
    @PostMapping("save")
    public ResultVO save(@RequestBody DictTypeVO dictTypeVO) {
        DictType dictType = ConvertUtils.convert(dictTypeVO, DictType.class);
        dictTypeService.saveOrUpdate(dictType);
        return ResultVO.success(dictType.getId());
    }

    @ApiOperation("更改数据")
    @PostMapping("update")
    public ResultVO update(@RequestBody DictTypeVO dictTypeVO) {
        DictType dictType = ConvertUtils.convert(dictTypeVO, DictType.class);
        dictTypeService.saveOrUpdate(dictType);
        return ResultVO.success();
    }

    @ApiOperation("删除数据")
    @PostMapping("/delete/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        dictTypeService.deleteById(id);
        return ResultVO.success(id);
    }

    @ApiOperation("批量删除数据")
    @PostMapping("/delete/batch")
    public ResultVO deleteBatch(@RequestBody String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        boolean deleteCount = dictTypeService.deleteByIds(idList);
        return ResultVO.success(deleteCount);
    }

}
