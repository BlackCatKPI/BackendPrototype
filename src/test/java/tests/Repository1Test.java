package tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*import org.junit.After;
import org.junit.Before;
import org.junit.Test;*/


public class Repository1Test 
{

   /*  private SessionFactory sessionFactory;

     private Session session = null;

     @Before

     public void before() 
     {

      // setup the session factory


    	 Configuration configuration = new Configuration();

      configuration.addAnnotatedClass(SuperHero.class)   
        .addAnnotatedClass(SuperPower.class)

        .addAnnotatedClass(SuperPowerType.class);

      configuration.setProperty("hibernate.dialect",

        "org.hibernate.dialect.H2Dialect");

      configuration.setProperty("hibernate.connection.driver_class",

        "org.h2.Driver");

      configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem");

      configuration.setProperty("hibernate.hbm2ddl.auto", "create");

      sessionFactory = configuration.buildSessionFactory();

      session = sessionFactory.openSession();

     }

     @Test

     public void returnsHerosWithMatchingType() {

      // create the objects needed for testing

      SuperPowerType powerType = new SuperPowerType();

      powerType.name = "TheType";

      powerType.description = "12345678901234567890aDescription";

      SuperPower superpower = new SuperPower();

      superpower.name = "SuperPower";

      superpower.description = "Description";

      superpower.type = powerType;

      SuperHero hero = new SuperHero();

      hero.name = "Name";

      hero.power = superpower;

      hero.weakness = "None";

      hero.secretIdentity = "Mr. Jones";

      // storing the objects for the test in the database

      session.save(powerType);

      session.save(superpower);

      session.save(hero);

      SuperHeroRepository heroRepository = new SuperHeroRepository(session);

      List<SuperHero> heroes = heroRepository.loadBy(superpower);

      assertNotNull(heroes);

      assertEquals(1, heroes.size());

     }

     @After

     public void after() {

      session.close();

      sessionFactory.close();

     }*/

 }