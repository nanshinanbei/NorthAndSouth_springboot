package autumndoubleli.northandsouth.controller;
import autumndoubleli.northandsouth.entity.User;
import autumndoubleli.northandsouth.entity.UserInfo;
import autumndoubleli.northandsouth.service.UserService;
import javax.servlet.http.HttpSession;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "用户接口")
@RestController
public class UserController {
    private final HttpSession session;
    @Autowired
    private UserService userService;

    public UserController(HttpSession session) {
        this.session = session;
    }
    @Operation(summary = "用邮件查询用户信息")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "420", description = "Not Found", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content()),
            })
    @GetMapping("/getUserMessage/email={email}")
    public ResponseEntity<UserInfo> getFullUserInfo(@PathVariable String email) {
        if (session.getAttribute("email").equals(email)) {
            return ResponseEntity.status(200).body(userService.getUserInfoByEmail(email));
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }
    @Operation(summary = "修改密码")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "420", description = "Not Found", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content()),
            })
    @GetMapping("/editPassword/{email}/{password}")
    public ResponseEntity<User> editPassword(
            @PathVariable(value = "email", required = true) String email,
            @PathVariable(value = "password", required = true) String password) {
        if (session.getAttribute("email").equals(email)) {
            return ResponseEntity.status(200).body(userService.editPassword(email,password));
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }
}

