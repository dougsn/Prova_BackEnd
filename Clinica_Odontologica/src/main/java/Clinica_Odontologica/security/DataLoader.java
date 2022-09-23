package Clinica_Odontologica.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("password");
        String password2 = passwordEncoder.encode("password2");
        String password3 = passwordEncoder.encode("password3");

        userRepository.save(new AppUser("Jose", "jose", "jose@dh.com", password, AppUserRoles.ROLE_USER));
        userRepository.save(new AppUser("Admin", "admin", "admin@dh.com", password2, AppUserRoles.ROLE_ADMIN));
        userRepository.save(new AppUser("Paciente", "paciente", "paciente@dh.com", password3, AppUserRoles.ROLE_PACIENTE));
        userRepository.save(new AppUser("Dentista", "dentista", "paciente@dh.com", password3, AppUserRoles.ROLE_DENTISTA));
        userRepository.save(new AppUser("Clinica", "clinica", "clinica@dh.com", password3, AppUserRoles.ROLE_CLINICA));

    }
}
