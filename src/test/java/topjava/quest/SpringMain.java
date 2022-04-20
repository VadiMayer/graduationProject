package topjava.quest;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try(GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/inmemory.xml");
            appCtx.refresh();

            System.out.println("Bean definition names " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        }
    }
}
