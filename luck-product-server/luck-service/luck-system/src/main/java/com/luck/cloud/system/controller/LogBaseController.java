package com.luck.cloud.system.controller;

import com.luck.cloud.base.vo.ResultVO;
import com.luck.cloud.base.vo.PageVO;
import com.luck.cloud.core.entity.LogBase;
import com.luck.cloud.core.vo.LogBaseVO;
import com.luck.cloud.base.utils.ConvertUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.luck.cloud.base.controller.BaseController;
import com.luck.cloud.core.service.ILogBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;



/**
 * 系统请求日志
 *
 * @author luck
 * @since 2024-08-01
 */
@RestController("sysLogBaseController")
@RequestMapping("/logBase")
public class LogBaseController extends BaseController {

    @Autowired
    private ILogBaseService logBaseService;

    @ApiOperation("分页获取数据")
    @PostMapping("page")
    public PageVO<LogBaseVO> pageLogBase(@RequestBody PageVO<LogBase> PageVO) {
        return ConvertUtils.convertPage(logBaseService.queryPage(PageVO), LogBaseVO.class);
    }

    @ApiOperation("根据id获取唯一数据")
    @GetMapping("/get/{id}")
    public ResultVO<LogBaseVO> get(@PathVariable("id") String id) {
        LogBaseVO logRequestVO = ConvertUtils.convert(logBaseService.queryOneById(id), LogBaseVO.class);
        return ResultVO.success(logRequestVO);
    }

    @ApiOperation("添加数据")
    @PostMapping("save")
    public ResultVO save(@RequestBody LogBaseVO logRequestVO) {
        LogBase logBase = ConvertUtils.convert(logRequestVO, LogBase.class);
        logBaseService.saveOrUpdate(logBase);
        return ResultVO.success(logBase.getId());
    }

    @ApiOperation("更改数据")
    @PostMapping("update")
    public ResultVO update(@RequestBody LogBaseVO logRequestVO) {
        LogBase logBase = ConvertUtils.convert(logRequestVO, LogBase.class);
        logBaseService.saveOrUpdate(logBase);
        return ResultVO.success();
    }

    @ApiOperation("删除数据")
    @PostMapping("/delete/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        logBaseService.deleteById(id);
        return ResultVO.success(id);
    }

    @ApiOperation("批量删除数据")
    @PostMapping("/delete/batch")
    public ResultVO deleteBatch(@RequestBody String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        boolean deleteCount = logBaseService.deleteByIds(idList);
        return ResultVO.success(deleteCount);
    }

}
