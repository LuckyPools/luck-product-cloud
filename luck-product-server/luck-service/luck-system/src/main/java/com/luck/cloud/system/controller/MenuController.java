package com.luck.cloud.system.controller;

import com.luck.cloud.base.controller.BaseController;
import com.luck.cloud.base.utils.ConvertUtils;
import com.luck.cloud.base.vo.PageVO;
import com.luck.cloud.base.vo.ResultVO;
import com.luck.cloud.core.entity.Menu;
import com.luck.cloud.core.service.IMenuService;
import com.luck.cloud.core.vo.MenuVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;



/**
 * 系统目录
 *
 * @author luck
 * @since 2024-07-30
 */
@RestController("coreMenuController")
@RequestMapping("/core/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation("分页获取数据")
    @PostMapping("page")
    public PageVO<MenuVO> pageMenu(@RequestBody PageVO<Menu> PageVO) {
        return ConvertUtils.convertPage(menuService.queryPage(PageVO), MenuVO.class);
    }

    @ApiOperation("根据id获取唯一数据")
    @GetMapping("/get/{id}")
    public ResultVO<MenuVO> get(@PathVariable("id") String id) {
        MenuVO menuVO = ConvertUtils.convert(menuService.queryOneById(id), MenuVO.class);
        return ResultVO.success(menuVO);
    }

    @ApiOperation("添加数据")
    @PostMapping("save")
    public ResultVO save(@RequestBody MenuVO menuVO) {
        Menu menu = ConvertUtils.convert(menuVO, Menu.class);
        menuService.saveOrUpdate(menu);
        return ResultVO.success(menu.getId());
    }

    @ApiOperation("更改数据")
    @PostMapping("update")
    public ResultVO update(@RequestBody MenuVO menuVO) {
        Menu menu = ConvertUtils.convert(menuVO, Menu.class);
        menuService.saveOrUpdate(menu);
        return ResultVO.success();
    }

    @ApiOperation("删除数据")
    @PostMapping("/delete/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        menuService.deleteById(id);
        return ResultVO.success(id);
    }

    @ApiOperation("批量删除数据")
    @PostMapping("/delete/batch")
    public ResultVO deleteBatch(@RequestBody String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        boolean deleteCount = menuService.deleteByIds(idList);
        return ResultVO.success(deleteCount);
    }

}
