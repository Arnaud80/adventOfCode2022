package day1;

public class Elve implements Comparable<Elve> {
    Integer id;
    Integer calories;

    public Elve(Integer id, Integer calories) {
        this.id = id;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Override
    public int compareTo(Elve elve) {
        return calories.compareTo(elve.calories);
    }

    @Override
    public String toString() {
        return ("day1.Elve" + id + " : " + calories);
    }


}
