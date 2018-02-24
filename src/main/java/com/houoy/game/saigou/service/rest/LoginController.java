package com.houoy.game.saigou.service.rest;

import com.houoy.common.exception.AppLoginException;
import com.houoy.common.vo.RequestResultVO;
import com.houoy.common.vo.SessionRootUserVO;
import com.houoy.common.vo.UserVO;
import com.houoy.game.saigou.config.SessionData;
import com.houoy.game.saigou.config.URLInterceptor;
import com.houoy.game.saigou.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author andyzhao
 *         spring boot
 */
@Api(description = "用户登录")
@RestController
@RequestMapping("/api/login")
public class LoginController {
    private static final Log logger = LogFactory.getLog(LoginController.class);

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userVO", value = "用户信息", required = true, paramType = "body", dataType = "UserVO")
    })
    @PostMapping("/signin")
    public RequestResultVO signin(HttpServletRequest request, @RequestBody UserVO userVO) throws IOException {
        String user_code = userVO.getUser_code();
        String password = userVO.getUser_password();
        String pk_role = userVO.getPk_role();
        RequestResultVO resultVO = new RequestResultVO();
        if (!StringUtils.isEmpty(pk_role) && pk_role.equals("1")) {
            //是登录超级管理员外挂，只能admin用户登录
            if (!user_code.equals("admin")) {
                resultVO.setSuccess(false);
                resultVO.setMsg("只能超级管理员登录");
                return resultVO;
            }
        }

        List<UserVO> users = loginService.retrieveByCodeAndPwd(user_code, password);
//        List<UserVO> users = new ArrayList<>();
//        UserVO u = new UserVO();
//        u.setuser_code("admin");
//        u.setUser_code("admin");
//        u.setUser_password("1");
//        users.add(u);

        if (users != null && users.size() > 0) {
            if (users.size() > 1) {
                throw new AppLoginException("系统内部错误，找到多个用户");
            } else {
                HttpSession httpSession = request.getSession();
                SessionRootUserVO sessionRootUserVO = new SessionRootUserVO();
                sessionRootUserVO.setUser(users.get(0));
                httpSession.setAttribute(URLInterceptor.Default_Session_Key, sessionRootUserVO);

                //3在sessionIDMap中存放此用户sessionID
                String sessionID = httpSession.getId();
                if (!SessionData.getSessionIDMap().containsKey(user_code)) { //不存在，首次登陆，放入Map
                    SessionData.getSessionIDMap().put(user_code, sessionID);
                } else {//存在
                    SessionData.getSessionIDMap().remove(user_code);
                    SessionData.getSessionIDMap().put(user_code, sessionID);
                }

                resultVO.setSuccess(true);
                resultVO.setMsg("查询成功");
                resultVO.setResultData(users.get(0));
            }
        } else {
            resultVO.setSuccess(false);
            resultVO.setMsg("登录失败，请检查用户名和密码");
        }

        return resultVO;
    }
}


