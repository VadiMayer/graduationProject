package topjava.quest.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = VoteUIController.REST_URL)
public class VoteUIController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/users/votes";

    

}
