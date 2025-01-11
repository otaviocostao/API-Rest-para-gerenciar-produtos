package dio.cadastro_produtos;

import dio.cadastro_produtos.auth.entity.Role;
import dio.cadastro_produtos.auth.entity.User;
import dio.cadastro_produtos.auth.service.RoleService;
import dio.cadastro_produtos.auth.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Initializer {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void criaUsuariosEPermissoes(){
        // Verifica se o papel ADMIN já existe
        Role roleAdmin = roleService.findByName("ADMIN");
        if (roleAdmin == null) {
            roleAdmin = new Role();
            roleAdmin.setName("ADMIN");
            roleService.save(roleAdmin);
        }

        // Verifica se o usuário com o nome de usuário "otaviocostao" já existe
        User existingUser = userService.findByUsername("otaviocostao");
        if (existingUser == null) {
            User user = new User();
            user.setAtivo(true);
            user.setEmail("teste@gmail.com");
            user.setNome("Otavio Costa");
            user.setSenha(new BCryptPasswordEncoder().encode("123456"));
            user.setUsername("otaviocostao");
            user.setRoles(Arrays.asList(roleAdmin));

            userService.save(user);
        }
    }
}
