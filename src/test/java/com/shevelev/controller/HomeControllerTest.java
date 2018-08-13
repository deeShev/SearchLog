package com.shevelev.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    @InjectMocks
    private HomeController homeController;

    @Test
    public void home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/homeSearch");
        ModelAndView home = homeController.home();
        Assert.assertEquals(modelAndView.getViewName(), home.getViewName());

    }
}