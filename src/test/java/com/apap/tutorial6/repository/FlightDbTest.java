package com.apap.tutorial6.repository;

import com.apap.tutorial6.model.FlightModel;
import com.apap.tutorial6.model.PilotModel;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Optional;

/**
 * Created by esak on 10/17/2018.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FlightDbTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FlightDb flightDb;

    @Test
    public void whenFindByFlightNumber_thenReturnFlight(){
        //given
        PilotModel pilotModel = new PilotModel();
        pilotModel.setLicenseNumber("4172");
        pilotModel.setName("coki");
        pilotModel.setFlyHour(59);
        entityManager.persist(pilotModel);
        entityManager.flush();

        FlightModel flightModel = new FlightModel();
        flightModel.setFlightNumber("X550");
        flightModel.setOrigin("depok");
        flightModel.setDestination("bekasi");
        flightModel.setTime(new Date(new java.util.Date().getTime()));
        flightModel.setPilot(pilotModel);
        entityManager.persist(flightModel);
        entityManager.flush();

        //when
        Optional<FlightModel> found = flightDb.findByFlightNumber(flightModel.getFlightNumber());

        //then
        assertThat(found.get(), Matchers.notNullValue()); //check if not null
        assertThat(found.get(), Matchers.equalTo(flightModel)); //check if same


    }
}
