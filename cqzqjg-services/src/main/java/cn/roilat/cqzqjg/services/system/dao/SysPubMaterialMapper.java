package cn.roilat.cqzqjg.services.system.dao;

import cn.roilat.cqzqjg.services.system.model.SysPubMaterial;

import java.util.List;

public interface SysPubMaterialMapper {

    List<SysPubMaterial> findPage();

    List<SysPubMaterial> findAll();

    int insert(SysPubMaterial record);

    int deleteByPrimaryKey(Long id);

    SysPubMaterial selectByPrimaryKey(Long id);

    SysPubMaterial selectByMediaId(String mediaId);

    int updateByMaterialId(SysPubMaterial sysPubMaterial);
}
