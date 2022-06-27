package topjava.quest.service;


import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//import topjava.quest.ActiveDbProfileResolver;
import topjava.quest.util.ValidationUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;

//Содержит в себе две аннотации @ExtendWith(SpringExtension.class) позволяет JUnit 5 запускать тесты в контексте Spring
//@ContextConfiguration из Spring тестирования для загрузки Spring контекста
@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml"})

//Аннотация для тестов, она относится к Spring тест модулю и задает профили при поднятии Spring в тестах.
//ActiveDbProfileResolver в результате выбор базы данных в зависимости от наличия драйвера в classpath-e и профиль для выбора технологий, будут складываться.
//Каждый профиль будет работать с общим профилем для выбора базы данных и со своим профилем для выбора репозитория.
//(clean необходимо делать при смене базы данных или если у нас какие-то классы удаляются)
//@ActiveProfiles("postgres")

//@Sql используется для аннотирования тестового класса или метода тестирования для настройки сценариев SQL,
// которые будут запускаться в данной базе данных во время интеграционных тестов.
// Исполняет её перед каждым тестом, то есть база будет всегда возвращается в исходное положение. Считывает её с кодировкой UTF-8.
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class AbstractServiceTest {
    protected <T extends Throwable> void validateRootCause(Class<T> rootExceptionClass, Runnable runnable) {
        assertThrows(rootExceptionClass, () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw ValidationUtil.getRootCause(e);
            }
        });
    }
}
