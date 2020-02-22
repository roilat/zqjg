package cn.roilat.cqzqjg.services.system.sevice;

import cn.roilat.cqzqjg.core.service.CurdService;
import cn.roilat.cqzqjg.services.system.model.SysPubMaterial;

/**
 * @program: zqjg
 * @description:
 * @author: liujing
 * @create: 2020-02-10 14:49
 **/
public interface SysPubMaterialService extends CurdService<SysPubMaterial> {
    int update(SysPubMaterial sysPubMaterial);

    SysPubMaterial selectByMediaId(String mediaId);

    int deleteById(Long id);
}
