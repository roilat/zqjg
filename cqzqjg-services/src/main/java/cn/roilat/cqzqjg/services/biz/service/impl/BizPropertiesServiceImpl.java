package cn.roilat.cqzqjg.services.biz.service.impl;

import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.core.page.MybatisPageHelper;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.biz.dao.BizPropertiesMapper;
import cn.roilat.cqzqjg.services.biz.model.BizProperties;
import cn.roilat.cqzqjg.services.biz.model.BizPropertiesHistory;
import cn.roilat.cqzqjg.services.biz.service.BizPropertiesHistoryService;
import cn.roilat.cqzqjg.services.biz.service.BizPropertiesService;
import cn.roilat.cqzqjg.services.biz.vo.AssetsReqVo;
import cn.roilat.cqzqjg.services.biz.vo.BizPropertiesReqVo;
import cn.roilat.cqzqjg.services.biz.vo.BizPropertiesRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ---------------------------
 * 资产信息表 (BizPropertiesServiceImpl)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizPropertiesServiceImpl implements BizPropertiesService {

    @Autowired
    private BizPropertiesMapper bizPropertiesMapper;
    @Autowired
    private BizPropertiesHistoryService bizPropertiesHistoryService;

    /**
     * 新增成功
     */
    private static final int SAVE_SUCCESS = 0;
    /**
     * 新增失败
     */
    private static final int SAVE_FAIL = 1;
    /**
     * 更新成功
     */
    private static final int UPDATE_SUCCESS = 2;
    /**
     * 更新失败
     */
    private static final int UPDATE_FAIL = 3;
    /**
     * 传入id错误
     */
    private static final int ID_ERROR = 4;


    @Override
    public int save(BizProperties record) {
        if (record.getId() == null || record.getId() == 0) {
            //新增资产
            record.setCreateTime(new Date());
            int i = bizPropertiesMapper.add(record);
            if (i > 0) {
                //新增成功
                return SAVE_SUCCESS;
            } else {
                //新增失败
                return SAVE_FAIL;
            }
        }
        //更新资产
        BizProperties bizProperties = findById(record.getId());
        if (null != bizProperties) {
            //改资产已存在,做更新处理
            BizPropertiesHistory bizPropertiesHistory = generateHistoryVo(bizProperties, record);
            record.setLastUpdateTime(new Date());
            int i = bizPropertiesMapper.update(record);
            bizPropertiesHistoryService.save(bizPropertiesHistory);
            if (i > 0) {
                //更新成功
                return UPDATE_SUCCESS;
            } else {
                //更新失败
                return UPDATE_FAIL;
            }
        } else {
            //id错误
            return ID_ERROR;
        }
    }

    @Override
    public int delete(BizProperties record) {
        return bizPropertiesMapper.delete(record.getId());
    }

    @Override
    public int delete(List<BizProperties> records) {
        for (BizProperties record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public BizProperties findById(Long id) {
        return bizPropertiesMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, bizPropertiesMapper);
    }

    @Override
    public PageResult findByName(AssetsReqVo assetsReqVo) {
        PageResult pageResult = MybatisPageHelper.findPage(assetsReqVo, bizPropertiesMapper, "findByName", assetsReqVo.getName());
        List<BizProperties> bizPropertiesList = (List<BizProperties>) pageResult.getContent();
        List<BizPropertiesRespVo> respVoList = new ArrayList<>();
        for (BizProperties bizProperties : bizPropertiesList) {
            if (null == bizProperties) {
                respVoList.add(new BizPropertiesRespVo());
            } else {
                BizPropertiesRespVo bizPropertiesRespVo = castVo(bizProperties);
                respVoList.add(bizPropertiesRespVo);
            }
        }
        pageResult.setContent(respVoList);
        return pageResult;
    }

    @Override
    public PageResult findByNamePc(BizPropertiesReqVo bizPropertiesReqVo) {
        PageResult pageResult = MybatisPageHelper.findPage(bizPropertiesReqVo, bizPropertiesMapper, "findByName", bizPropertiesReqVo.getName());
        List<BizProperties> bizPropertiesList = (List<BizProperties>) pageResult.getContent();
        List<BizPropertiesRespVo> respVoList = new ArrayList<>();
        for (BizProperties bizProperties : bizPropertiesList) {
            if (null == bizProperties) {
                respVoList.add(new BizPropertiesRespVo());
            } else {
                BizPropertiesRespVo bizPropertiesRespVo = castVo(bizProperties);
                respVoList.add(bizPropertiesRespVo);
            }
        }
        pageResult.setContent(respVoList);
        return pageResult;
    }

    @Override
    public BizPropertiesRespVo findDetailById(Long id) {
        BizProperties bizProperties = findById(id);
        if (null == bizProperties) {
            return new BizPropertiesRespVo();
        }
        BizPropertiesRespVo bizPropertiesRespVo = castVo(bizProperties);
        return bizPropertiesRespVo;
    }

    @Override
    public BizPropertiesRespVo findDetailByIdPc(Long id) {
        BizProperties bizProperties = findById(id);
        if (null == bizProperties) {
            return new BizPropertiesRespVo();
        }
        BizPropertiesRespVo bizPropertiesRespVo = castVo(bizProperties);
        return bizPropertiesRespVo;
    }


    private BizPropertiesRespVo castVo(BizProperties bizProperties) {
        BizPropertiesRespVo bizPropertiesRespVo = new BizPropertiesRespVo();
        bizPropertiesRespVo.setAddress(bizProperties.getAddress());
        bizPropertiesRespVo.setContent(bizProperties.getContent());
        bizPropertiesRespVo.setCover(bizProperties.getCover());
        bizPropertiesRespVo.setCreateBy(bizProperties.getCreateBy());
        bizPropertiesRespVo.setDelFlag(bizProperties.getDelFlag());
        bizPropertiesRespVo.setId(bizProperties.getId());
        bizPropertiesRespVo.setLastUpdateBy(bizProperties.getLastUpdateBy());
        bizPropertiesRespVo.setName(bizProperties.getName());
        bizPropertiesRespVo.setType(bizProperties.getType());
        bizPropertiesRespVo.setUnit(bizProperties.getUnit());
        Date lastUpTime = bizProperties.getLastUpdateTime();
        if (null != lastUpTime) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String lastUpTimeStr = simpleDateFormat.format(lastUpTime);
            bizPropertiesRespVo.setLastUpdateTime(lastUpTimeStr);
        }
        bizPropertiesRespVo.setQuantity(bizProperties.getQuantity());
        return bizPropertiesRespVo;
    }

    private BizPropertiesHistory generateHistoryVo(BizProperties oldBizProperties, BizProperties newBizProperties) {
        BizPropertiesHistory bizPropertiesHistory = new BizPropertiesHistory();
        bizPropertiesHistory.setProperyId(oldBizProperties.getId());
        bizPropertiesHistory.setNewName(newBizProperties.getName());
        bizPropertiesHistory.setOldName(oldBizProperties.getName());
        bizPropertiesHistory.setNewCover(newBizProperties.getCover());
        bizPropertiesHistory.setOldCover(oldBizProperties.getCover());
        bizPropertiesHistory.setNewType(newBizProperties.getType());
        bizPropertiesHistory.setOldType(oldBizProperties.getType());
        bizPropertiesHistory.setNewUnit(newBizProperties.getUnit());
        bizPropertiesHistory.setOldUnit(oldBizProperties.getUnit());
        bizPropertiesHistory.setNewQuantity(newBizProperties.getQuantity());
        bizPropertiesHistory.setOldQuantity(oldBizProperties.getQuantity());
        bizPropertiesHistory.setNewAddress(newBizProperties.getAddress());
        bizPropertiesHistory.setOldAddress(oldBizProperties.getAddress());
        bizPropertiesHistory.setCreateTime(new Date());
        bizPropertiesHistory.setCreateBy(newBizProperties.getCreateBy());
        return bizPropertiesHistory;

    }

}
