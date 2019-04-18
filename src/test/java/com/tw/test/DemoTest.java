package com.tw.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoTest {

  @Test
  public void shouldReturnFizzBuzz(){
    assertEquals("FizzBuzz", Demo.procNumber(15));
  }

  @Test
  public void shouldReturnFizz(){
    assertEquals("Fizz", Demo.procNumber(6));
  }
  @Test
  public void shouldReturnBuzz(){
    assertEquals("Buzz", Demo.procNumber(55));
  }

  @Test
  public void shouldReturnNumber(){
    int number = 23;
    assertEquals("" + number, Demo.procNumber(number));
  }
}
