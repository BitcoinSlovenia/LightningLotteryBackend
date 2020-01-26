package com.grmkris.lightningloterry.service;

import com.grmkris.lightningloterry.repository.WinnersRepository;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WinnerServiceTest{

    @InjectMocks
    WinnersService winnerService;
 
    @Mock
    WinnersRepository winnersRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


}