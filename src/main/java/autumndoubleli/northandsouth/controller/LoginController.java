package autumndoubleli.northandsouth.controller;

import autumndoubleli.northandsouth.entity.User;
import autumndoubleli.northandsouth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "登录接口")
@RestController
public class LoginController {
    private final HttpSession session;
    @Autowired
    private UserService userService;

    public LoginController(HttpSession session) {
        this.session = session;
    }
    @Operation(summary = "欢迎来到南北后端开发文档")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "welcome to the NorthAndSouth backend development"),})
    @GetMapping("/WelcomeToTheNorthAndSouthBackendDevelopmentDocumentation")
    public String WelcomeToTheNorthAndSouthBackendDevelopmentDocumentation(){
        return "welcome to the NorthAndSouth backend development";
    }

    @Operation(summary = "登录")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Login success"),
                    @ApiResponse(responseCode = "210", description = "Can't find email", content = @Content()),
                    @ApiResponse(responseCode = "220", description = "Wrong password", content = @Content()),
                    @ApiResponse(responseCode = "400", description = "Server Error", content = @Content()),
            })
    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<User> login(@PathVariable(value = "email", required = true) String email,
                                      @PathVariable(value = "password", required = true) String password) {
        User user;
        //请求转发，会话管理
        try {
            user = userService.getUser(email, password);
            if (user.equals(new User(-1, "invalid", "ok"))) {
                return ResponseEntity.status(210).body(null);
            } else if (user.equals(new User(-1, "ok", "invalid"))) {
                return ResponseEntity.status(220).body(null);
            } else {
                session.setAttribute("email", user.getEmail());
                return ResponseEntity.status(200).body(user);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(null);
        }
    }
    @Operation(summary = "注销登录")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Logout successfully"),})
    @GetMapping("/logout")
    public String logout() {
        //注销session（在服务器里删除该session）
        session.invalidate();
        return "logout success";
    }
}

