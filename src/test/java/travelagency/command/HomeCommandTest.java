package travelagency.command;


import org.junit.Test;
import travelagency.controller.command.HomeCommand;
import travelagency.service.impl.CountryServiceImpl;
import travelagency.service.impl.HotelServiceImpl;
import travelagency.service.impl.TourServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class HomeCommandTest {

    @Test
    public void testHomeCommandReturnsProperPage() {
        final HomeCommand homeCommand = new HomeCommand(new TourServiceImpl(), new CountryServiceImpl(), new HotelServiceImpl());
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpSession session = mock(HttpSession.class);

        when(request.getParameter("country")).thenReturn("");
        when(request.getParameter("hotel")).thenReturn("");
        when(request.getParameter("lowerprice")).thenReturn("");
        when(request.getParameter("higherprice")).thenReturn("");
        when(request.getParameter("lowergroup")).thenReturn("");
        when(request.getSession()).thenReturn(session);

        String url = homeCommand.execute(request);

        verify(request, times(4)).getSession();
        assertEquals("/home_page.jsp", url);
    }
}
