package com.guo.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guo.commutils.JwtUtils;
import com.guo.commutils.MD5;
import com.guo.educenter.entity.UcenterMember;
import com.guo.educenter.entity.vo.RegisterVo;
import com.guo.educenter.mapper.UcenterMemberMapper;
import com.guo.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.servicebase.exception.GuliException;
import feign.QueryMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author guo
 * @since 2022-05-15
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(UcenterMember member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password))
            throw new GuliException(20001, "登陆失败");

        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        if (mobileMember == null)
            throw new GuliException(20001, "手机号不存在");

        //encrypt the password
        password = MD5.encrypt(password);
        if (!StringUtils.equals(password, mobileMember.getPassword()))
            throw new GuliException(20001, "密码错误");
        if (mobileMember.getIsDisabled())
            throw new GuliException(20001, "用户已经被禁用");

        //login success
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册数据
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        if (StringUtils.isEmpty(code) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(nickname) ||
                StringUtils.isEmpty(password)
        )
            throw new GuliException(20001, "登陆失败");

        String s = redisTemplate.opsForValue().get(mobile);
        if (!StringUtils.equals(s, code))
            throw new GuliException(20001, "验证码错误");
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer integer = baseMapper.selectCount(wrapper);
        if (integer >= 1)
            throw new GuliException(20001, "用户已经存在");

        //数据添加数据库中
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));//密码需要加密的
        member.setIsDisabled(false);//用户不禁用
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(member);
    }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        return null;
    }
}
