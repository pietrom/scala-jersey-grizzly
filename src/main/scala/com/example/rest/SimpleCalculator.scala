package com.example.rest

class SimpleCalculator extends Calculator with Calculator2  {
  def add(x: Int, y: Int): Int = {
    x + y
  }
}