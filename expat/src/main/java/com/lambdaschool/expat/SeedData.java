package com.lambdaschool.expat;

import com.lambdaschool.expat.models.Photo;
import com.lambdaschool.expat.models.Story;
import com.lambdaschool.expat.models.User;
import com.lambdaschool.expat.models.UserStories;
import com.lambdaschool.expat.services.PhotoService;
import com.lambdaschool.expat.services.StoryService;
import com.lambdaschool.expat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData implements CommandLineRunner {

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    StoryService storyService;

    @Autowired
    PhotoService photoService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws Exception {
        userService.deleteAll();
        storyService.deleteAll();
        photoService.deleteAll();


        User u1 = new User("Tom Jones",
                "password",
                "tom@lambdaschool.local");
        u1 = userService.save(u1);

        User u2 = new User("Bob Jones",
                "password",
                "bob@lambdaschool.local");
        u2 = userService.save(u2);

        User u3 = new User("Jon Jones",
                "password",
                "jon@lambdaschool.local");
        u3 = userService.save(u3);

        User u4 = new User("Jim Jones",
                "password",
                "jim@school.lambda");

        u4 = userService.save(u4);

        User u5 = new User("Liz Jones",
                "password",
                "liz@school.lambda");
        u5 = userService.save(u5);


        Story s1 = new Story("My Trip", "New Zealand", "We had a great time in Auckland...");
        s1 = storyService.save(s1);

        Story s2 = new Story("Another Trip", "Australia", "We had a great time in Sydney...");
        s2 = storyService.save(s1);

        Story s3 = new Story("My Trip", "China", "We had a great time in Shanghai...");
        s3 = storyService.save(s1);

        Story s4 = new Story("My Trip", "Thailand", "We had a great time in Bangkok...");
        s4 = storyService.save(s1);

        Story s5 = new Story("My Trip", "New Zealand", "We had a great time in Auckland...");
        s5 = storyService.save(s1);

//
        Photo p1 = new Photo("https://picsum.photos/id/10/200/300", "A new pic", u1, s1);
        p1 = photoService.save(p1);

        Photo p2 = new Photo("https://picsum.photos/id/100/200/300", "A new pic", u2, s2);
        p2 = photoService.save(p2);

        Photo p3 = new Photo("https://picsum.photos/id/1001/200/300", "A new pic", u2, s2);
        p3 = photoService.save(p3);

        Photo p4 = new Photo("https://picsum.photos/id/1002/200/300", "A new pic", u3, s3);
        p4 = photoService.save(p4);

        Photo p5 = new Photo("https://picsum.photos/id/1003/200/300", "A new pic", u4, s4);
        p5 = photoService.save(p5);

        Photo p6 = new Photo("https://picsum.photos/id/1004/200/300", "A new pic", u1, s1);
        p6 = photoService.save(p6);

        Photo p7 = new Photo("https://picsum.photos/id/1005/200/300", "A new pic", u1, s1);
        p7 = photoService.save(p7);

        Photo p8 = new Photo("https://picsum.photos/id/1006/200/300", "A new pic", u5, s5);
        p8 = photoService.save(p8);

        Photo p9 = new Photo("https://picsum.photos/id/1007/200/300", "A new pic", u5, s5);
        p9 = photoService.save(p9);

        Set<UserStories> userstories = new HashSet<>();
        userstories.add(new UserStories(u1, s1));

        userstories = new HashSet<>();
        userstories.add(new UserStories(u2, s2));

        userstories = new HashSet<>();
        userstories.add(new UserStories(u3, s3));

        userstories = new HashSet<>();
        userstories.add(new UserStories(u4, s4));

        userstories = new HashSet<>();
        userstories.add(new UserStories(u5, s5));
    }
}