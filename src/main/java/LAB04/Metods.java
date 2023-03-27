package LAB04;

import org.w3c.dom.css.CSSUnknownRule;

import java.util.ArrayList;

public class Metods {
    public static void main(String[] args) {
        String [] names = new String[2];
        names[0] = "rivera";
        names[1] = "matias";

        System.out.println(names[0]);
        changeplace(names, 0, 1);
        System.out.println(names[0]);

        Integer[] numbers = new Integer[2];
        numbers[0] = 12;
        numbers[1] = 5;

        System.out.println(numbers[0]);
        changeplace(numbers, 0, 1);
        System.out.println(numbers[0]);

        Person[] people = new Person[2];
        people[0] = new Person("melvin", "river");
        people[1] = new Person("enrique", "soto");

        System.out.println(people[0]);
        changeplace(people, 0, 1);
        System.out.println(people[0]);

        ArrayList<String> people2 = new ArrayList<>();

        people2.add("person1");
        people2.add("person2");

        // person 1 is deleted
        System.out.println(removeItem(people2, 0));



        // comparable

        ArrayList<Person> people3 = new ArrayList<>();

        people3.add(new Person("adin", "fer"));
        people3.add(new Person("reti", "fasf"));
        people3.add(new Person("adrian", "some"));
        people3.add(new Person("stabple", "rendsr"));

        // АТД «динамический массив».
        ArrayList<Integer> dinamicStrings = new ArrayList<>();

        // dynamic array of 10000 elements
        for(int i = 0; i < 10000; i++){
            dinamicStrings.add(i);
        }

        for(Integer a: dinamicStrings) {
            System.out.println(a);
        }
    }

    public static <T> void changeplace(T [] arr, int from, int to){
        T elementFrom = arr[from];
        T elementTo = arr[to];

        arr[from] = elementTo;
        arr[to] = elementFrom;
    }

    public static <T> ArrayList<T> removeItem(ArrayList<T> list, int index){
        ArrayList<T> result = (ArrayList<T>)list.clone();
        result.remove(index);
        return  result;
    }

}

class Person implements Comparable<Person> {
    private String name;
    private String surname;

    public Person(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        if (this.name.compareTo(o.name) != 0) {
            return this.name.compareTo(o.name);
        }
        else{
            return 1;
        }
    }
}
