package cn.roilat.cqzqjg.admin.wechat.comtroller;

import cn.roilat.cqzqjg.common.security.JwtAuthenticatioToken;
import cn.roilat.cqzqjg.common.util.PasswordUtils;
import cn.roilat.cqzqjg.common.util.SecurityUtils;
import cn.roilat.cqzqjg.common.utils.StringUtils;
import cn.roilat.cqzqjg.common.vo.LoginBean;
import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.services.system.model.SysUser;
import cn.roilat.cqzqjg.services.system.sevice.SysUserService;
import com.google.code.kaptcha.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: zqjg
 * @description: 微信后台管理员登录
 * @author: liujing
 * @create: 2020-01-09 14:49
 **/
@RestController
@RequestMapping("wx/admin")
public class WxAdminLoginController {

    private static Logger logger = LoggerFactory.getLogger(WxAdminLoginController.class);

    @Autowired
    SysUserService sysUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 密码登录
     *
     * @param loginBean
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
        logger.info("管理员登录请求开始: " + loginBean.toString());
        if (loginBean == null) {
            return HttpResult.error("输入参数错误！");
        }
        String username = loginBean.getAccount();
        String password = loginBean.getPassword();
        String captcha = loginBean.getCaptcha();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return HttpResult.error("用户名或密码为空！");
        }
        // 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        /*
         * if (kaptcha == null) { return HttpResult.error("验证码已失效"); } if
         * (!kaptcha.equals(captcha)) { return HttpResult.error("验证码不正确"); }
         */

        // 用户信息
        SysUser user = sysUserService.findByName(username);

        // 账号不存在、密码错误
        if (user == null) {
            return HttpResult.error("账号不存在");
        }

        if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
            return HttpResult.error("密码不正确");
        }

        // 账号锁定
       /* if (user.get() == "1") {
            return HttpResult.error("账号已被锁定,请联系管理员");
        }*/

        // 系统登录认证
        user.setPassword(password);
        JwtAuthenticatioToken token = SecurityUtils.loginWechat(request, user, authenticationManager);
        token.eraseCredentials();
        return HttpResult.ok(token);
    }

}
