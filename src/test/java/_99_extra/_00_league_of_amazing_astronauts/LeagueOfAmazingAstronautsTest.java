package _99_extra._00_league_of_amazing_astronauts;

import _99_extra._00_league_of_amazing_astronauts.LeagueOfAmazingAstronauts;
import _99_extra._00_league_of_amazing_astronauts.models.Astronaut;
import _99_extra._00_league_of_amazing_astronauts.models.Rocketship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/*

When writing the tests, mock both the Rocketship and Astronaut for the sake of practice.
 */
class LeagueOfAmazingAstronautsTest {

    LeagueOfAmazingAstronauts underTest = new LeagueOfAmazingAstronauts();
    
    @Mock
    Astronaut astronaut;
    
    @Mock
    Rocketship rocketship;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	
    	underTest = new LeagueOfAmazingAstronauts();
    	underTest.setRocketship(rocketship);
    }

    @Test
    void itShouldPrepareAstronaut() {
        //given
    	when(astronaut.isTrained()).thenReturn(true);
    	
        //when
    	underTest.prepareAstronaut(astronaut);

        //then
    	verify(astronaut, times(1)).train();
    	verify(rocketship, times(1)).loadOccupant(astronaut);
    }

    @Test
    void itShouldLaunchRocket() {
        //given
    	String destination = "Mars";
    	int milesToDestination = 68_000_000;
    	
    	when(rocketship.isLoaded()).thenReturn(true);

        //when
    	underTest.launchRocket(destination);

        //then
    	verify(rocketship, times(1)).setDestination(destination, milesToDestination);
    	verify(rocketship, times(1)).launch();
    }


    @Test
    void itShouldThrowWhenDestinationIsUnknown() {
        //given
    	String destination = "Uranus";
    	
    	when(rocketship.isLoaded()).thenReturn(true);

        //when
        //then
    	Exception e = assertThrows(Exception.class, () -> underTest.launchRocket(destination));
    	assertEquals("Destination is unavailable", e.getMessage());
    }

    @Test
    void itShouldThrowNotLoaded() {
        //given
    	String destination = "Mars"; 
    	
    	when(rocketship.isLoaded()).thenReturn(false);

        //when
        //then
    	Exception e = assertThrows(Exception.class, () -> underTest.launchRocket(destination));
    	assertEquals("Rocketship is not loaded", e.getMessage());
    }
}