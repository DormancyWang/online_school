package com.guo.educenter.service;

import com.guo.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author guo
 * @since 2022-05-15
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);
}
