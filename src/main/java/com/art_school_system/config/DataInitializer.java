package com.art_school_system.config;

import com.art_school_system.model.*;
import com.art_school_system.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final DirectionRepository directionRepository;
    private final SubjectRepository subjectRepository;
    private final NewsRepository newsRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final ApplicationRepository applicationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // Создание администратора
        if (userRepository.findByEmail("admin@artschoolsystem.com").isEmpty()) {
            User admin = User.builder()
                    .fullName("Администратор")
                    .email("admin@artschoolsystem.com")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ADMIN)
                    .createdAt(LocalDateTime.now())
                    .build();
            userRepository.save(admin);
            System.out.println("✅ Администратор создан: admin@artschoolsystem.com / admin");
        }

        // Создание преподавателя
        if (userRepository.findByEmail("teacher@artschoolsystem.com").isEmpty()) {
            User teacher = User.builder()
                    .fullName("Преподаватель Иван Иванов")
                    .email("teacher@artschoolsystem.com")
                    .password(passwordEncoder.encode("teacher"))
                    .role(Role.TEACHER)
                    .createdAt(LocalDateTime.now())
                    .build();
            userRepository.save(teacher);
            System.out.println("✅ Преподаватель создан: teacher@artschoolsystem.com / teacher");
        }

        // Создание обычного пользователя
        if (userRepository.findByEmail("user@artschoolsystem.com").isEmpty()) {
            User user = User.builder()
                    .fullName("Пользователь Петр Петров")
                    .email("user@artschoolsystem.com")
                    .password(passwordEncoder.encode("user"))
                    .role(Role.USER)
                    .createdAt(LocalDateTime.now())
                    .build();
            userRepository.save(user);
            System.out.println("✅ Пользователь создан: user@artschoolsystem.com / user");
        }

        // Создание направлений и предметов
        if (directionRepository.count() == 0) {
            User teacher = userRepository.findByEmail("teacher@artschoolsystem.com").orElseThrow();

            Direction painting = Direction.builder()
                    .name("Живопись")
                    .description("Направление художественной живописи")
                    .build();
            directionRepository.save(painting);

            Subject oilPainting = Subject.builder()
                    .name("Масляная живопись")
                    .description("Техника масляной живописи")
                    .direction(painting)
                    .teacher(teacher)
                    .build();
            subjectRepository.save(oilPainting);

            Subject watercolor = Subject.builder()
                    .name("Акварельная живопись")
                    .description("Техника акварельной живописи")
                    .direction(painting)
                    .teacher(teacher)
                    .build();
            subjectRepository.save(watercolor);

            Direction sculpture = Direction.builder()
                    .name("Скульптура")
                    .description("Направление скульптуры")
                    .build();
            directionRepository.save(sculpture);

            Subject clayModeling = Subject.builder()
                    .name("Лепка из глины")
                    .description("Основы лепки скульптур из глины")
                    .direction(sculpture)
                    .build();
            subjectRepository.save(clayModeling);

            System.out.println("✅ Направления и предметы созданы");
        }

        // Создание новостей
        if (newsRepository.count() == 0) {
            News news1 = News.builder()
                    .title("Открытие нового направления скульптуры")
                    .content("Мы рады объявить об открытии нового направления скульптуры в нашей художественной школе. Теперь вы можете записаться на курсы лепки из глины и создания скульптур.")
                    .createdAt(LocalDateTime.now().minusDays(5))
                    .build();
            newsRepository.save(news1);

            News news2 = News.builder()
                    .title("Мастер-класс по акварельной живописи")
                    .content("В ближайшую субботу состоится мастер-класс по акварельной живописи. Ведущий преподаватель Иван Иванов поделится секретами техники и ответит на все вопросы.")
                    .createdAt(LocalDateTime.now().minusDays(2))
                    .build();
            newsRepository.save(news2);

            System.out.println("✅ Новости созданы");
        }
    }
}
