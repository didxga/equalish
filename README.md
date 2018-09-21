# equalish
equal^ish, compare two lists to know if they are structurally in equal (alike)

Two list is considered equalish, when:

~~~~ 
[1, 2, [3, 4], 5] equalish [1,2,[3,4],5] true

[{"a":"1", "b":"2"}, 1, 2, [8, 9, [10, 12]], 12] equalish [{"a":"1", "b":"2"}, 1, 2, [8, 9, [10, 12]], 12] true
~~~~ 

Or it could even be more ~ish by allowing element in different order

~~~~
[1, 2, [3, 4], 5] equalish [1,2,5, [4,3]] true
~~~~

It could compare nested list/map structure
~~~~ 
[1, 2, [5,[3,2]], 7] equalish [1, 2, [5,[3,2]], 7] true
~~~~ 


API
~~~~
public static <T> boolean equalish(List<T> a, List<T> b, boolean honorOrder)
a,b: the two list to compare to
honorOrder: if or not comparing order
~~~~


Usage
~~~~
List<Object> l11 = new ArrayList<>();
        l11.add(8);
        l11.add(7);

        List<Object> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(3);
        l1.add(2);
        l1.add(l11);
        l1.add(7);



        List<Object> l21 = new ArrayList<>();
        l21.add(7);
        l21.add(8);

        List<Object> l2 = new ArrayList<>();
        l2.add(1);
        l2.add(l21);
        l2.add(3);
        l2.add(2);
        l2.add(7);

        boolean isqualish = ListEqualish.equalish(l1, l2, false);
~~~~ 
