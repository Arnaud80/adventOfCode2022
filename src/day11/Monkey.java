package day11;

import java.util.ArrayList;

public class Monkey {
    private Integer id;
    private ArrayList<Integer> items;
    private String operand;
    private String value;
    private Integer divisibleTestValue;
    private Integer trueMonkey;
    private Integer falseMonkey;
    private Integer inspectedItem;
    private Boolean isFirstRound;

    public Monkey(String[] data) {
        this.id = Integer.valueOf(data[0].substring(7,8));
        String strItems[] = data[1].substring(17, data[1].length()).split(",");
        this.items = new ArrayList<>();
        for(String strItem: strItems) {
            this.items.add(Integer.valueOf(strItem.trim()));
        }
        this.operand = data[2].substring(23,24);
        this.value = data[2].substring(25,data[2].length());

        this.divisibleTestValue = Integer.valueOf(data[3].substring(21, data[3].length()));

        this.trueMonkey = Integer.valueOf(data[4].substring(29, data[4].length()));
        this.falseMonkey = Integer.valueOf(data[5].substring(30, data[5].length()));

        this.inspectedItem = this.items.size();

        this.isFirstRound = true;

        System.out.println("Monkey " + this.id + " whith items " + this.items +
                "\n Operation : old " + this.operand + " " + this.value +
                "\n Test divisible by " + this.divisibleTestValue +
                "\n true Monkey " + this.trueMonkey +
                "\n false Monkey " + this.falseMonkey);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Integer> getItems() {
        return items;
    }

    public void setItems(ArrayList<Integer> items) {
        this.items = items;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getDivisibleTestValue() {
        return divisibleTestValue;
    }

    public void setDivisibleTestValue(Integer divisibleTestValue) {
        this.divisibleTestValue = divisibleTestValue;
    }

    public Integer getTrueMonkey() {
        return trueMonkey;
    }

    public void setTrueMonkey(Integer trueMonkey) {
        this.trueMonkey = trueMonkey;
    }

    public Integer getFalseMonkey() {
        return falseMonkey;
    }

    public void setFalseMonkey(Integer falseMonkey) {
        this.falseMonkey = falseMonkey;
    }

    public Boolean isFirstRound() {
        return isFirstRound;
    }

    public void setFirstRound(Boolean firstRound) {
        isFirstRound = firstRound;
    }

    public Integer[] getTargetMonkeyAndValue(int index) {
        Integer newValue=0;
        Integer actualValue=0;
        Integer targetMonkey=0;

        if (value.equals("old")) actualValue = items.get(index);
        else actualValue = Integer.valueOf(value);

        switch (operand) {
            case "+":
                newValue = items.get(index) + actualValue;
                break;
            case "*":
                newValue = items.get(index) * actualValue;
                break;
        }

        //if(isFirstRound)
        newValue = newValue / 3;

        if(newValue % divisibleTestValue == 0) targetMonkey = trueMonkey;
        else targetMonkey = falseMonkey;

        Integer[] returnValue = {targetMonkey, newValue};
        return(returnValue);
    }

    public void addItem(Integer value) {
        items.add(value);
        inspectedItem++;
    }

    public void delItem(int index) {
        items.remove(index);
    }

    public Integer getInspectedItem() {
        return inspectedItem;
    }
}
