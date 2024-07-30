package com.luck.cloud.system.controller;

import com.luck.cloud.base.controller.BaseController;
import com.luck.cloud.base.utils.ConvertUtils;
import com.luck.cloud.base.vo.PageVO;
import com.luck.cloud.base.vo.ResultVO;
import com.luck.cloud.core.entity.RoleMenu;
import com.luck.cloud.core.service.IRoleMenuService;
import com.luck.cloud.core.vo.RoleMenuVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;



/**
 * 系统角色权限
 *
 * @author luck
 * @since 2024-07-30
 */
@RestController("coreRoleMenuController")
@RequestMapping("/core/roleMenu")
public class RoleMenuController extends BaseController {

    @Autowired
    private IRoleMenuService roleMenuService;

    @ApiOperation("分页获取数据")
    @PostMapping("page")
    public PageVO<RoleMenuVO> pageRoleMenu(@RequestBody PageVO<RoleMenu> PageVO) {
        return ConvertUtils.convertPage(roleMenuService.queryPage(PageVO), RoleMenuVO.class);
    }

    @ApiOperation("根据id获取唯一数据")
    @GetMapping("/get/{id}")
    public ResultVO<RoleMenuVO> get(@PathVariable("id") String id) {
        RoleMenuVO roleMenuVO = ConvertUtils.convert(roleMenuService.queryOneById(id), RoleMenuVO.class);
        return ResultVO.success(roleMenuVO);
    }

    @ApiOperation("添加数据")
    @PostMapping("save")
    public ResultVO save(@RequestBody RoleMenuVO roleMenuVO) {
        RoleMenu roleMenu = ConvertUtils.convert(roleMenuVO, RoleMenu.class);
        roleMenuService.saveOrUpdate(roleMenu);
        return ResultVO.success(roleMenu.getId());
    }

    @ApiOperation("更改数据")
    @PostMapping("update")
    public ResultVO update(@RequestBody RoleMenuVO roleMenuVO) {
        RoleMenu roleMenu = ConvertUtils.convert(roleMenuVO, RoleMenu.class);
        roleMenuService.saveOrUpdate(roleMenu);
        return ResultVO.success();
    }

    @ApiOperation("删除数据")
    @PostMapping("/delete/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        roleMenuService.deleteById(id);
        return ResultVO.success(id);
    }

    @ApiOperation("批量删除数据")
    @PostMapping("/delete/batch")
    public ResultVO deleteBatch(@RequestBody String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        boolean deleteCount = roleMenuService.deleteByIds(idList);
        return ResultVO.success(deleteCount);
    }

}
