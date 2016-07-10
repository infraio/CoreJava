package com.xiaohao.inheritance;

public class EqualManager extends EqualEmployee {
  private double bonus;
  
  public EqualManager(String name, double salary, int year, int month, int day) {
    super(name, salary, year, month, day);
    bonus = 0;
  }
  
  public double getBonus() {
    return this.bonus;
  }
  
  public void setBonus(double bonus) {
    this.bonus = bonus;
  }
  
  @Override
  public boolean equals(Object otherObject) {
    if (!super.equals(otherObject)) {
      return false;
    }
    
    EqualManager other = (EqualManager) otherObject;
    
    return bonus == other.bonus;
  }
  
  @Override
  public int hashCode() {
    return super.hashCode() + 17 * new Double(bonus).hashCode();
  }
  
  public String toString() {
    return super.toString() + ", bonus=" + bonus;
  }
}
